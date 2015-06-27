package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import HelperClasses.ContactHelper;

/**
 * Created by Mobile App Develop on 21-6-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "INFORMATION";

    //table columns
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE1 = "phone1";
    private static final String PHONE2 = "phone2";
    private static final String PHONE3 = "phone3";
    private static final String PHONE4 = "phone4";
    private static final String PHONE5 = "phone5";
    private static final String PHONE6 = "phone6";
    private static final String PHONE7 = "phone7";
    private static final String PHONE8 = "phone8";
    private static final String PHONE9 = "phone9";
    private static final String PHONE10 = "phone10";
    private static final String ADDRESS = "address";
    private static final String STREET = "street";
    private static final String PO_BOX = "po_box";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String ZIP_CODE = "zip_code";
    private static final String EVENTS = "events";
    private static final String NOTE = "note";
    private static final String GROUPS = "groups";

    static final String DB_NAME = "BUDDY_BOOK";
    static final int DB_VERSION = 1;


    private static final String INFORMATION_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PHONE1 + " TEXT, " +
            PHONE2 + " TEXT, " +
            PHONE3 + " TEXT, " +
            PHONE4 + " TEXT, " +
            PHONE5 + " TEXT, " +
            PHONE6 + " TEXT, " +
            PHONE7 + " TEXT, " +
            PHONE8 + " TEXT, " +
            PHONE9 + " TEXT, " +
            PHONE10 + " TEXT, " +
            EMAIL + " TEXT, " +
            ADDRESS + " TEXT, " +
            STREET + " TEXT, " +
            PO_BOX + " TEXT, " +
            CITY + " TEXT, " +
            STATE + " TEXT, " +
            ZIP_CODE + " TEXT, " +
            EVENTS + " TEXT, " +
            NOTE + " TEXT, " +
            GROUPS + " TEXT" +
            ");";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(INFORMATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public long addDetail(ContactHelper info) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, info.getName());

        int i = 1;
        for (String phone : info.getPhone()) {
            values.put("phone" + i, phone);
            i++;
        }
        values.put(EMAIL, info.getEmail());
        values.put(ADDRESS, info.getAddress());
        values.put(STATE, info.getState());
        values.put(STREET, info.getStreet());
        values.put(PO_BOX, info.getPoBox());
        values.put(CITY, info.getCity());
        values.put(ZIP_CODE, info.getZipCode());
        values.put(EVENTS, info.getEvents());
        values.put(NOTE, info.getNote());
        values.put(GROUPS, info.getGroups());

        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert;

    }

    /**
     * Retrieving all data from database
     *
     * @return List<ContactHelper> with all data
     */
    public List<ContactHelper> getAllData() {
        List<ContactHelper> dataArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ContactHelper info = new ContactHelper();
                info.setId(c.getInt(c.getColumnIndex(_ID)));
                info.setName(c.getString(c.getColumnIndex(NAME)));

                ArrayList<String> phoneNo = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    phoneNo.add(c.getString(c.getColumnIndex("phone" + i)));
                }
                info.setPhone(phoneNo);

                dataArrayList.add(info);
            } while (c.moveToNext());
        }

        db.close();
        return dataArrayList;
    }

    /**
     * Checks if the database is empty
     *
     * @return true or false
     */
    public boolean isEmpty() {
        String query = "SELECT " + _ID + " FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        int columnCount = c.getCount();
        if (columnCount >= 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

}
