package Fragments;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inonity.buddybook.R;
import com.inonity.buddybook.ViewDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import CustomAdapter.ContactAdapter;
import Database.DatabaseHelper;
import HelperClasses.ContactHelper;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements AdapterView.OnItemClickListener {

    Context thisContext;
    private DatabaseHelper db;
    private ListView listView;
    private ArrayList<ContactHelper> list = new ArrayList<>();
    private ContactHelper objContact;
    ArrayList<String> duplicateName = new ArrayList<>();


    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisContext = container.getContext();
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_contacts, container, false);
        listView = (ListView) rootview.findViewById(R.id.listViewContactsFragment);

        //Checking if the database is empty
        db = new DatabaseHelper(getActivity());
        if (db.isEmpty()) {
            showDataFromDatabase();
            //Toast.makeText(this, "DataBase not empty", Toast.LENGTH_SHORT).show();
        } else {
            getDataFromPhone();
            showDataFromDatabase();
        }


        return rootview;
    }


    /**
     * retrieve data from phone
     */
    private void getDataFromPhone() {
        db = new DatabaseHelper(getActivity());
        ContentResolver cr = getActivity().getContentResolver();

        String sortName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, sortName);

        ArrayList<String> phoneNo = new ArrayList<>();

        objContact = new ContactHelper();
        String phoneNumber, image_uri = null;
        Bitmap image = null;
        if (phones.getCount() > 0) {
            while (phones.moveToNext()) {
                String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                image_uri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    objContact = new ContactHelper();
                    objContact.setName(name);
                    objContact.setImage(image_uri);


                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNo.add(phoneNumber);

                    }
                    pCur.close();
                    Log.i("Name and phone", name + " " + phoneNo.toString() + image_uri);
                    objContact.setPhone(phoneNo);
                    db.addDetail(objContact);
                    phoneNo.clear();
                }

            }
        }

        phones.close();
    }

    /**
     * Retrieve saved data from database
     */
    private void showDataFromDatabase() {

        listView.setOnItemClickListener(this);
        list = db.getAllData();
        print(list);


        if ( list != null && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactHelper>() {

                @Override
                public int compare(ContactHelper lhs, ContactHelper rhs) {
                    return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
                }
            });
            /*AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("");

            alert.setMessage(list.size() + " Contact Found!!!");

            alert.setButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();*/

        } else {
            showToast("No Contact Found!!!");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position, long id) {
        ContactHelper contact = list.get(position);

        Intent i = new Intent(getActivity(), ViewDetailsActivity.class);

        i.putExtra("id",contact.getId());

        i.putExtra("Name", contact.getName());
        i.putExtra("Image", contact.getImage());
        i.putExtra("Phone Numbers", contact.getPhone());
        String address = contact.getCity() + " " +
                contact.getState() + " " +
                contact.getStreet() + " " +
                contact.getPoBox() + " " +
                contact.getZipCode();
        i.putExtra("Address", address);

        /*String address = (contact.getCity() != null) ? contact.getCity() : null + " ";
        address += (contact.getState() != null) ? contact.getState() : null + " ";
        address += (contact.getStreet() != null) ? contact.getStreet() : null + " ";
        address += (contact.getPoBox() != null) ? contact.getPoBox() : null + " ";
        address += (contact.getZipCode() != null) ? contact.getZipCode() : null;
        i.putExtra("Address", address);*/

        i.putExtra("Note", contact.getNote());

        i.putExtra("Email", contact.getEmails());
        startActivity(i);

        //showCallDialog(list.get(position).getName(), list.get(position).getPhone());
    }

    private void showCallDialog(String name, final String phoneNo) {
        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
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
        ContactAdapter objAdapter = new ContactAdapter(getActivity(), list);
        listView.setAdapter(objAdapter);
    }
}
