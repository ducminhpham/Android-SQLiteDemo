package dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.net.ConnectException;

public final class PlantDbContract {

    public static final String DATABASE_NAME = "PlantDatabase";
    public static final int DATABASE_VERSION = 1;

    // Inner class to define Plant Table's schema
    // (Note that table names and other terms in SQLite are case-sensitive)
    public static class PlantEntry {

        // Name of the table
        public static final String TABLE_NAME = "Plant";

        // Columns of the table
        // If this entry class implements the interface BaseColumns,
        // one _ID column will be automatically added.
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_TYPE = "Type";

        // Generate database creation command
        // (CREATE TABLE PlantDatabase (Name TEXT, Type TEXT)
        public static final String CREATE_COMMAND =
                String.format("CREATE TABLE %s ( %s %s, %s %s );",
                        TABLE_NAME,
                        COLUMN_NAME, "TEXT",
                        COLUMN_TYPE, "TEXT");

        // Generate database deletion command
        // (DROP TABLE IF EXISTS PlantDatabase)
        public static final String DELETE_COMMAND =
                String.format("DROP TABLE IF EXISTS %s;", TABLE_NAME);

        // Get column lists (used with SQLiteDatabase)
        // Note: there is a default "_rowid_" column, however, you can ignore it
        public static final String[] COLUMN_LIST = { "_rowid_", COLUMN_NAME, COLUMN_TYPE };

        // Get ContentValues from given data (used with SQLiteDatabase)
        public static ContentValues fromData(String name, String type) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_TYPE, type);
            return values;
        }

        // Get ContentValues from current cursor
        public static ContentValues fromCursor(Cursor cursor) {
            ContentValues values = new ContentValues();
            // Iterate through all columns
            // Name
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            if (nameIndex > -1) {
                values.put(COLUMN_NAME, cursor.getString(nameIndex));
            }

            // Type
            int typeIndex = cursor.getColumnIndex(COLUMN_TYPE);
            if (typeIndex > -1) {
                values.put(COLUMN_TYPE, cursor.getString(typeIndex));
            }
            return values;
        }
    }

}
