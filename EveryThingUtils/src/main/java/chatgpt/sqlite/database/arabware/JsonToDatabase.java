package chatgpt.sqlite.database.arabware;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonToDatabase {

    public interface ProgressListener {
        void onProgressUpdate(int progress, int max);
        void onConversionComplete(File outputFile);
        void onConversionError(Exception e);
    }

    private final Context mContext;
    private final ProgressListener mListener;

    public JsonToDatabase(Context context, ProgressListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void convertJsonToDatabase(File jsonFile, File dbFile) {
        new Thread(() -> {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);

            try (FileReader reader = new FileReader(jsonFile)) {
                JsonParser parser = new JsonParser();
                JsonArray tablesArray = parser.parse(reader).getAsJsonArray();
                int tablesCount = tablesArray.size();

                for (int i = 0; i < tablesCount; i++) {
                    JsonObject tableObject = tablesArray.get(i).getAsJsonObject();
                    String tableName = tableObject.get("table_name").getAsString();
                    JsonArray tableData = tableObject.get("table_data").getAsJsonArray();
                    int tableRowCount = tableData.size();

                    db.beginTransaction();
                    try {
                        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "(_id INTEGER PRIMARY KEY AUTOINCREMENT)");

                        for (int j = 0; j < tableRowCount; j++) {
                            JsonObject rowObject = tableData.get(j).getAsJsonObject();
                            ContentValues rowValues = new ContentValues();
                            for (String column : rowObject.keySet()) {
                                rowValues.put(column, getFieldValue(rowObject.get(column)));
                            }
                            db.insert(tableName, null, rowValues);
                        }

                        db.setTransactionSuccessful();

                    } catch (Exception e) {
                        e.printStackTrace();
                        mListener.onConversionError(e);
                        return;
                    } finally {
                        db.endTransaction();
                    }

                    mListener.onProgressUpdate(i + 1, tablesCount);
                }

                mListener.onConversionComplete(dbFile);

            } catch (IOException e) {
                e.printStackTrace();
                mListener.onConversionError(e);
            } finally {
                db.close();
            }
        }).start();
    }

    private Object getFieldValue(JsonElement valueElement) {
        if (valueElement.isJsonNull()) {
            return null;
        } else if (valueElement.isJsonPrimitive()) {
            return valueElement.getAsString();
        } else {
            return valueElement.toString();
        }
    }
}
