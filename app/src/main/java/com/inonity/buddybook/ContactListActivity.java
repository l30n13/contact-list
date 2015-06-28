package com.inonity.buddybook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import CustomAdapter.ContactAdapter;
import Database.DatabaseHelper;
import HelperClasses.ContactHelper;

public class ContactListActivity extends Activity implements AdapterView.OnItemClickListener {

    private DatabaseHelper db;
    private ListView listView;
    private ArrayList<ContactHelper> list = new ArrayList<>();
    private ContactHelper objContact;
    String duplicateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);

        //Checking if the database is empty
        db = new DatabaseHelper(this);
        if (db.isEmpty()) {
            showDataFromDatabase();
            //Toast.makeText(this, "DataBase not empty", Toast.LENGTH_SHORT).show();
        } else {
            getDataFromPhone();
            showDataFromDatabase();
        }
    }


    /**
     * retrieve data from phone
     */
    private void getDataFromPhone() {
        db = new DatabaseHelper(this);

        String sortName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, sortName);
        phones.moveToFirst();

        ArrayList<String> phoneNo = new ArrayList<>();
        duplicateName = null;
        objContact = new ContactHelper();
        for (int i = 0; i < phones.getCount(); i++) {

            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            if (duplicateName == null) {
                duplicateName = name;

                objContact = new ContactHelper();
                objContact.setName(name);
                phoneNo.add(phoneNumber);

            } else if (duplicateName.equals(name)) {
                phoneNo.add(phoneNumber);
            } else {
                objContact.setPhone(phoneNo);
                duplicateName = null;
                db.addDetail(objContact);
                Log.i("Name and phone", name + " " + phoneNo.toString());
                phoneNo.clear();
                //phones.moveToPrevious();
            }
            phones.moveToNext();


        }

        phones.close();
    }

    /**
     * Retrieve saved data from database
     */
    private void showDataFromDatabase() {
        listView = (ListView) findViewById(R.id.listViewContacts);
        listView.setOnItemClickListener(this);
        list = (ArrayList<ContactHelper>) db.getAllData();
        print(list);


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactHelper>() {

                @Override
                public int compare(ContactHelper lhs, ContactHelper rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });
            AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this).create();
            alert.setTitle("");

            alert.setMessage(list.size() + " Contact Found!!!");

            alert.setButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();

        } else {
            showToast("No Contact Found!!!");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position, long id) {
        Intent i = new Intent(this, ViewDetailsActivity.class);

        Log.i("TEST", list.get(position).getName());


        i.putExtra("Name", list.get(position).getName());
        i.putExtra("Phone Numbers", list.get(position).getPhone());
        startActivity(i);

        //showCallDialog(list.get(position).getName(), list.get(position).getPhone());
    }

    private void showCallDialog(String name, final String phoneNo) {
        AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this).create();
        alert.setTitle("Call?");

        alert.setMessage("Are you sure want to call " + name + " ?");

        alert.setButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setButton2("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phoneNumber = "tel:" + phoneNo;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
                startActivity(intent);
            }
        });
        alert.show();
    }

    private void print(List<ContactHelper> list) {
        ContactAdapter objAdapter = new ContactAdapter(this, 0, list);
        listView.setAdapter(objAdapter);
    }
}
