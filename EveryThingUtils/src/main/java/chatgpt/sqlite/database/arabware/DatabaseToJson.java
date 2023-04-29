import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

            try (FileWriter writer = new FileWriter(jsonFile)) {
                Gson gson = new Gson();
                JsonArray tablesArray = new JsonArray();
                String[] tableNames = getTableNames(db);
                int tablesCount = tableNames.length;

                for (int i = 0; i < tablesCount; i++) {
                    String tableName = tableNames[i];
                    JsonArray tableData = new JsonArray();

                    Cursor cursor = db.query(tableName, null, null, null, null, null, null);
                    int tableRowCount = cursor.getCount();

                    if (cursor.moveToFirst()) {
                        do {
                            JsonObject rowObject = new JsonObject();
                            int columnCount = cursor.getColumnCount();
                            for (int j = 0; j < columnCount; j++) {
                                String columnName = cursor.getColumnName(j);
                                String columnValue = cursor.getString(j);
                                rowObject.addProperty(columnName, columnValue);
                            }
                            tableData.add(rowObject);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();

                    JsonObject tableObject = new JsonObject();
                    tableObject.addProperty("table_name", tableName);
                    tableObject.add("table_data", tableData);

                    tablesArray.add(tableObject);

                    mListener.onProgressUpdate(i + 1, tablesCount);
                }

                gson.toJson(tablesArray, writer);

                mListener.onConversionComplete(jsonFile);

            } catch (IOException e) {
                e.printStackTrace();
                mListener.onConversionError(e);
            } finally {
                db.close();
            }
        }).start();
    }

    private String[] getTableNames(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        int tableCount = cursor.getCount();
        String[] tableNames = new String[tableCount];
        if (cursor.moveToFirst()) {
            int index = 0;
            do {
                tableNames[index] = cursor.getString(0);
                index++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tableNames;
    }
}
