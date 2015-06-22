package com.inonity.buddybook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class ContactListActivity extends Activity implements
        AdapterView.OnItemClickListener {

    private DatabaseHelper db;
    private ListView listView;
    private ArrayList<ContactHelper> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);


        GetDataFromPhone();
    }

    //retrive data from phone
    private void GetDataFromPhone() {
        db = new DatabaseHelper(this);
        long inserted = 0;
        listView = (ListView) findViewById(R.id.listViewContacts);
        listView.setOnItemClickListener(this);

        Cursor phones = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        while (phones.moveToNext()) {

            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactHelper objContact = new ContactHelper();
            objContact.setName(name);
            objContact.setPhone(phoneNumber);
            // list.add(objContact);
            inserted = 0;
            try {
                inserted = db.addDetail(objContact);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        list = (ArrayList<ContactHelper>) db.getAllData();
        print(list);

        if (inserted >= 0)
            Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
        phones.close();


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactHelper>() {

                @Override
                public int compare(ContactHelper lhs, ContactHelper rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });
            AlertDialog alert = new AlertDialog.Builder(
                    ContactListActivity.this).create();
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
    public void onItemClick(AdapterView<?> listview, View v, int position,
                            long id) {
        ContactHelper contactHelper = (ContactHelper) listview.getItemAtPosition(position);
        showCallDialog(contactHelper.getName(), contactHelper.getPhone());
    }

    private void showCallDialog(String name, final String phoneNo) {
        AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)
                .create();
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
                Intent intent = new Intent(Intent.ACTION_CALL, Uri
                        .parse(phoneNumber));
                startActivity(intent);
            }
        });
        alert.show();
    }

    private void print(List<ContactHelper> list) {
        ContactAdapter objAdapter = new ContactAdapter(
                ContactListActivity.this, R.layout.custom_layout_name_pic_display, list);
        listView.setAdapter(objAdapter);

    }
}
