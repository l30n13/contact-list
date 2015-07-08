package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import HelperClasses.ContactHelper;

/**
 * Created by Mobile App Develop on 21-6-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "INFORMATION";

    //table columns
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String EMAIL1 = "email1";
    private static final String EMAIL2 = "email2";
    private static final String EMAIL3 = "email3";
    private static final String EMAIL4 = "email4";
    private static final String EMAIL5 = "email5";
    private static final String EMAIL_TYPE1 = "email_type1";
    private static final String EMAIL_TYPE2 = "email_type2";
    private static final String EMAIL_TYPE3 = "email_type3";
    private static final String EMAIL_TYPE4 = "emai1_type4";
    private static final String EMAIL_TYPE5 = "emai1_type5";
    private static final String PHONE1 = "phone1";
    private static final String PHONE2 = "phone2";
    private static final String PHONE3 = "phone3";
    private static final String PHONE4 = "phone4";
    private static final String PHONE5 = "phone5";
    private static final String PHONE6 = "phone6";
    private static final String PHONE7 = "phone7";
    private static final String PHONE8 = "phone8";
    private static final String PHONE9 = "phone9";
    private static final String PHONE_TYPE1 = "phone_type1";
    private static final String PHONE_TYPE2 = "phone_type2";
    private static final String PHONE_TYPE3 = "phone_type3";
    private static final String PHONE_TYPE4 = "phone_type4";
    private static final String PHONE_TYPE5 = "phone_type5";
    private static final String PHONE10 = "phone10";
    private static final String ADDRESS = "address";
    private static final String STREET = "street";
    private static final String PO_BOX = "po_box";
    private static final String CITY = "city";
    private static final String DATE = "date";
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
            IMAGE + " TEXT, " +
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
            PHONE_TYPE1 + " TEXT, " +
            PHONE_TYPE2 + " TEXT, " +
            PHONE_TYPE3 + " TEXT, " +
            PHONE_TYPE4 + " TEXT, " +
            PHONE_TYPE5 + " TEXT, " +
            EMAIL1 + " TEXT, " +
            EMAIL2 + " TEXT, " +
            EMAIL3 + " TEXT, " +
            EMAIL4 + " TEXT, " +
            EMAIL5 + " TEXT, " +
            EMAIL_TYPE1 + " TEXT, " +
            EMAIL_TYPE2 + " TEXT, " +
            EMAIL_TYPE3 + " TEXT, " +
            EMAIL_TYPE4 + " TEXT, " +
            EMAIL_TYPE5 + " TEXT, " +
            ADDRESS + " TEXT, " +
            STREET + " TEXT, " +
            PO_BOX + " TEXT, " +
            CITY + " TEXT, " +
            STATE + " TEXT, " +
            ZIP_CODE + " TEXT, " +
            DATE + " TEXT, " +
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
        try {
            int j = 1;
            for (String email : info.getEmails()) {
                values.put("email" + j, email);
                j++;
            }
            int k = 1;
            for (String phoneTypes : info.getPhoneTypes()) {
                values.put("phone_type" + k, phoneTypes);
                k++;
            }
            int l = 1;
            for (String emailTypes : info.getEmailTypes()) {
                values.put("email_type" + l, emailTypes);
                l++;
            }
        } catch (Exception e) {

        }
        values.put(IMAGE, info.getImage());
        //values.put(ADDRESS, info.getAddress());
        values.put(STATE, info.getState());
        values.put(STREET, info.getStreet());
        values.put(PO_BOX, info.getPoBox());
        values.put(CITY, info.getCity());
        values.put(ZIP_CODE, info.getZipCode());
        values.put(EVENTS, info.getEvents());
        values.put(NOTE, info.getNote());
        values.put(GROUPS, info.getGroups());
        values.put(DATE, info.getDate());

        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert;

    }

    /**
     * Retrieving all data from database
     *
     * @return List<ContactHelper> with all data
     */
    public ArrayList<ContactHelper> getAllData() {
        ArrayList<ContactHelper> dataArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ContactHelper info = new ContactHelper();
                info.setId(c.getInt(c.getColumnIndex(_ID)));
                info.setName(c.getString(c.getColumnIndex(NAME)));
                info.setImage(c.getString(c.getColumnIndex(IMAGE)));
                info.setCity(c.getString(c.getColumnIndex(CITY)));
                info.setState(c.getString(c.getColumnIndex(STATE)));
                info.setStreet(c.getString(c.getColumnIndex(STREET)));
                info.setPoBox(c.getString(c.getColumnIndex(PO_BOX)));
                info.setZipCode(c.getString(c.getColumnIndex(ZIP_CODE)));
                info.setNote(c.getString(c.getColumnIndex(NOTE)));

                ArrayList<String> phoneNo = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    phoneNo.add(c.getString(c.getColumnIndex("phone" + i)));
                }
                info.setPhone(phoneNo);
                ArrayList<String> email = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    email.add(c.getString(c.getColumnIndex("email" + i)));
                }
                info.setEmails(email);

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

    public boolean delete(int id){
        String deleteQuery = "DELETE  FROM " + TABLE_NAME+" WHERE _id = "+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(deleteQuery, null);
        if(c.getCount()>=1){
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }
    }

}
