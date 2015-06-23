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
    private static final String PHONE = "phone";
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
            PHONE + " TEXT, " +
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
        values.put(PHONE, info.getPhone());
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


    public List<ContactHelper> getAllData() {
        List<ContactHelper> dataArrayList = new ArrayList<ContactHelper>();
        String selectQuery = "SELECT " + _ID + ", " + NAME + " FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ContactHelper info = new ContactHelper();
                info.setId(c.getInt(c.getColumnIndex(_ID)));
                info.setName(c.getString(c.getColumnIndex(NAME)));

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
