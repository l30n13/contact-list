package Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

        String sortName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, sortName);
        phones.moveToFirst();

        ArrayList<String> phoneNo = new ArrayList<>();
        //duplicateName = null;
        objContact = new ContactHelper();
        String name;
        String phoneNumber;
        for (int i = 0; i < phones.getCount(); i++) {

            if (phones.moveToNext()) {
                name = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                if (!duplicateName.contains(name)) {
                    duplicateName.add(name);

                    objContact = new ContactHelper();
                    objContact.setName(name);
                    phoneNo.add(phoneNumber);

                }
                String name1 = null;
                do {
                    String phoneNumber1 = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (!phoneNo.contains(phoneNumber1)) {
                        phoneNo.add(phoneNumber1);
                    }

                    if (phones.moveToNext()) {
                        name1 = phones
                                .getString(phones
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        i++;
                    }
                } while (duplicateName.contains(name1));
                objContact.setPhone(phoneNo);
                db.addDetail(objContact);
                Log.i("Name and phone", name + " " + phoneNo.toString());
                for(String p:phoneNo){
                    Log.i("Name and phone", p);
                }
                phoneNo.clear();
                duplicateName.clear();
                phones.moveToPrevious();
                i--;
            }
        }

        phones.close();
    }

    /**
     * Retrieve saved data from database
     */
    private void showDataFromDatabase() {

        listView.setOnItemClickListener(this);
        list = (ArrayList<ContactHelper>) db.getAllData();
        print(list);


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactHelper>() {

                @Override
                public int compare(ContactHelper lhs, ContactHelper rhs) {
                    return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
                }
            });
       /*     AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
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
        Intent i = new Intent(getActivity(), ViewDetailsActivity.class);

        Log.i("TEST", list.get(position).getName());


        i.putExtra("Name", list.get(position).getName());
        i.putExtra("Phone Numbers", list.get(position).getPhone());
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

