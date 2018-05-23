package kvr.bookit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasi_Visu on 4/8/2018.
 */

public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Restaurant";

    // Contacts table name
    private static final String TABLE_RESTAURANT_DETAIL = "restDetails";

    // Contacts Table Columns names
    private static final String KEY_ID = "table_no";
    private static final String KEY_REST_NO = "cus_no";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_number";

    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DETAIL_TABLE = "CREATE TABLE " + TABLE_RESTAURANT_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REST_NO + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE_NO + " TEXT " + ")";

        db.execSQL(CREATE_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT_DETAIL);

        // Create tables again
        onCreate(db);
    }


    // Adding new Information
    void addNew(Restaurant newRES) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_REST_NO, newRES.get_cus_no());
        values.put(KEY_NAME, newRES.get_name());
        values.put(KEY_PHONE_NO, newRES.get_phone_number());


        // Inserting Row
        db.insert(TABLE_RESTAURANT_DETAIL, null, values);
        db.close(); // Closing database connection
    }


    public boolean updateInfo(int updId, int updcusNo, String updName, String updPhoneNo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_REST_NO, updcusNo);
        args.put(KEY_NAME, updName);
        args.put(KEY_PHONE_NO, updPhoneNo);

        return db.update(TABLE_RESTAURANT_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
    }


    public boolean delete(int delID){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_RESTAURANT_DETAIL, KEY_ID + "=" + delID, null) > 0;

    }



    // Getting All Details
    public List<Restaurant> getAllList() {


        List<Restaurant> restList = new ArrayList<Restaurant>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Restaurant resnt = new Restaurant();
                resnt.set_id(Integer.parseInt(cursor.getString(0)));
                resnt.set_cus_no(Integer.parseInt(cursor.getString(1)));
                resnt.set_name(cursor.getString(2));
                resnt.set_phone_number(cursor.getString(3));

                // Adding contact to list
                restList.add(resnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return restList;
    }


}
