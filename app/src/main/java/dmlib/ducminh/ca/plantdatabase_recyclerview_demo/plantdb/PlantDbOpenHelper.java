package dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// A helper class to manage database creation and version management.
public class PlantDbOpenHelper extends SQLiteOpenHelper {

    // Tag for logging messages
    private static final String LOGGING_TAG = "PlantDB";

    // Constructor with context
    public PlantDbOpenHelper(Context context) {
        super(context, PlantDbContract.DATABASE_NAME, null, PlantDbContract.DATABASE_VERSION);
    }

    // This method will be called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // Create the tables
            // Execute more commands here to create more entries (tables)
            db.execSQL(PlantDbContract.PlantEntry.CREATE_COMMAND);
            Log.i(LOGGING_TAG, "PlantDatabase has been successfully created!");
        } catch (Exception e) {
            Log.i(LOGGING_TAG, "Failed to create PlantDatabase!");
        }
    }

    // This method will be called when the version of the database has been upgraded
    // (newVersion > oldVersion)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Re-create the plant table
        try {
            // Delete all tables first
            // Execute more commands here to delete other entries (tables)
            db.execSQL(PlantDbContract.PlantEntry.DELETE_COMMAND);
            // Create the tables again
            onCreate(db);
            Log.i(LOGGING_TAG, "PlantDatabase has been successfully upgraded!");
        } catch (Exception e) {
            Log.i(LOGGING_TAG, "Failed to upgrade PlantDatabase!");
        }
    }

    // The method will be called when the version has been downgraded
    // (newVersion < oldVersion)
    // We should do the same thing as onUpgrade

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }
}
