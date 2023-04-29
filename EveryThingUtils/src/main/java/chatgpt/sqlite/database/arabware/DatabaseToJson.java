package chatgpt.sqlite.database.arabware;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseToJson {

    public interface ProgressListener {
        void onProgressUpdate(int progress, int max);
        void onConversionComplete(File outputFile);
        void onConversionError(Exception e);
    }

    private final Context mContext;
    private final ProgressListener mListener;

    public DatabaseToJson(Context context, ProgressListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void convertDatabaseToJson(File dbFile, File jsonFile) {
        new Thread(() -> {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);

            Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            int count = cursor.getCount();

            Gson gson = new GsonBuilder().create();

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write("[");
                while (cursor.moveToNext()) {
                    String tableName = cursor.getString(0);
                    Cursor tableCursor = db.rawQuery("SELECT * FROM " + tableName, null);

                    List<ContentValues> tableRows = new ArrayList<>();
                    while (tableCursor.moveToNext()) {
                        tableRows.add(cursorToJSON(tableCursor));
                    }

                    writer.write("{\"table_name\":\"" + tableName + "\",\"table_data\":");
                    gson.toJson(tableRows, writer);
                    writer.write("},");

                    tableCursor.close();

                    mListener.onProgressUpdate(tableRows.size(), count);
                }
                writer.write("]");

                mListener.onConversionComplete(jsonFile);

            } catch (IOException e) {
                e.printStackTrace();
                mListener.onConversionError(e);
            } finally {
                cursor.close();
                db.close();
            }
        }).start();
    }

    private ContentValues cursorToJSON(Cursor cursor) {
        ContentValues contentValues = new ContentValues();
        String[] columns = cursor.getColumnNames();
        for (String column : columns) {
            int columnIndex = cursor.getColumnIndex(column);
            switch (cursor.getType(columnIndex)) {
                case Cursor.FIELD_TYPE_FLOAT:
                    contentValues.put(column, (float)cursor.getFloat(columnIndex));
                    break;
                case Cursor.FIELD_TYPE_INTEGER:
                    contentValues.put(column, (int)cursor.getInt(columnIndex));
                    break;
                case Cursor.FIELD_TYPE_NULL:
                    contentValues.put(column, (Object) null);
                    break;
                case Cursor.FIELD_TYPE_STRING:
                    contentValues.put(column, (String)cursor.getString(columnIndex));
                    break;
            }
        }
        return contentValues;
    }
}
