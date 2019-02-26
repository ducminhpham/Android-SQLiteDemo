package dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlantDbManipulator {

    // The PlantDatabase OpenHelper
    private PlantDbOpenHelper helper;

    // The actual PlantDatabase
    private SQLiteDatabase db;

    // Constructor with context
    public PlantDbManipulator(Context context) {
        helper = new PlantDbOpenHelper(context);
        // Get database for read and write.
        // This operation may take a long period of time, so call it at appropriate time
        // or in the background.
        db = helper.getWritableDatabase();
    }

    // Close database
    public void close() {
        db.close();
    }

    // Insert new data, return the id
    public long insertToPlantEntry(String name, String type) {
        return db.insert(
                PlantDbContract.PlantEntry.TABLE_NAME,
                null,
                // Create ContentValues from given name and type
                PlantDbContract.PlantEntry.fromData(name, type)
        );
    }

    // Select all data
    public List<ContentValues> selectAllFromPlantEntry() {
        // Use cursor to iterate row-by-row of the result
        Cursor cursor = db.query(
                PlantDbContract.PlantEntry.TABLE_NAME,
                PlantDbContract.PlantEntry.COLUMN_LIST,
                null, null, null, null, null);

        return readAllValuesFromCursor(cursor);
    }

    // Select rows based on given plant type in plant entry
    public List<ContentValues> selectTypeFromPlantEntry(String plantType) {
        // Filtering condition
        String condition = String.format("%s=?", PlantDbContract.PlantEntry.COLUMN_TYPE);
        String[] args = new String[] { plantType };

        // Use cursor to iterate row-by-row of the result
        Cursor cursor = db.query(
                PlantDbContract.PlantEntry.TABLE_NAME,
                PlantDbContract.PlantEntry.COLUMN_LIST,
                condition, args, null, null, null);

        return readAllValuesFromCursor(cursor);
    }

    // Clear all rows in plant entry
    public void clearPlantEntry() {
        // Execute delete command
        db.execSQL(String.format("DELETE FROM %s", PlantDbContract.PlantEntry.TABLE_NAME));
    }

    // Read all rows (results) from given cursor
    private List<ContentValues> readAllValuesFromCursor(Cursor cursor) {
        // Iterate
        List<ContentValues> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(PlantDbContract.PlantEntry.fromCursor(cursor));
        }

        // Remember to close the cursor to free up memory
        cursor.close();
        return result;
    }
}
