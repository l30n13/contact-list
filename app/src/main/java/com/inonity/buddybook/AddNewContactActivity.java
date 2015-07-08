package com.inonity.buddybook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DatabaseHelper;
import HelperClasses.ContactHelper;


public class AddNewContactActivity extends Activity {

    Button buttonPhoneAdd;
    LinearLayout container, containerEmail;
    Button emailAdd;
    int i = 0, j = 0, k = 0;
    ArrayList<EditText> allEds;
    ArrayList<Spinner> allPhoneSp;
    ArrayList<EditText> emailAllEt;
    ArrayList<Spinner> emailAllSp;
    ArrayList<String> phones;
    ArrayList<String> phoneTypes;
    ArrayList<String> emailTypes;
    String phType1, emailType1;
    Spinner phoneSp, emailSp;
    Spinner phone_spinner, email_spinner;
    Spinner date_type;
    Spinner group_spinner;
    EditText name_et, phone_et, email_et, street_et, po_box_et, city_et, date_et,state_et, zip_code_et, note_et, reminder_et;
    String name, phone,date, email, street, po_box, city, state, zip_code, note, reminder;
    ImageView person_iv;
    Button submit_btn;
    ContactHelper contactHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        try {
            initialization();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            contactHelper = new ContactHelper();
            ArrayAdapter phoneNoType = ArrayAdapter.createFromResource(this, R.array.phoneNoTypes, android.R.layout.simple_spinner_item);
            ArrayAdapter dateType = ArrayAdapter.createFromResource(this, R.array.dateTypes, android.R.layout.simple_spinner_item);
            ArrayAdapter emailType = ArrayAdapter.createFromResource(this, R.array.emailTypes, android.R.layout.simple_spinner_item);
            Button sumb = (Button) findViewById(R.id.buttonSubmit);
            phone_spinner.setAdapter(phoneNoType);
            date_type.setAdapter(dateType);
            email_spinner.setAdapter(emailType);
            buttonPhoneAdd = (Button) findViewById(R.id.add);
            emailAdd = (Button) findViewById(R.id.addEmail);
            container = (LinearLayout) findViewById(R.id.container);
            containerEmail = (LinearLayout) findViewById(R.id.containerEmail);
            date_et = (EditText) findViewById(R.id.editTextDate);
            phones = new ArrayList<String>();
            allEds = new ArrayList<EditText>();
            allPhoneSp = new ArrayList<Spinner>();
            emailAllEt = new ArrayList<EditText>();
            emailAllSp = new ArrayList<Spinner>();
            final ArrayAdapter phoneNoType1 = ArrayAdapter.createFromResource(this, R.array.phoneNoTypes, android.R.layout.simple_spinner_item);
            final ArrayAdapter emailType1 = ArrayAdapter.createFromResource(this, R.array.emailTypes, android.R.layout.simple_spinner_item);

            buttonPhoneAdd.setOnClickListener(new View.OnClickListener() {


                public void onClick(View arg0) {
                    if (k < 5) {
                        LayoutInflater layoutInflater =
                                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View addView = layoutInflater.inflate(R.layout.row, null);
                        EditText etPhone = (EditText) addView.findViewById(R.id.editTextPhone1);
                        etPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
                        phoneSp = (Spinner) addView.findViewById(R.id.spinnerPhone1);


                        //etPhone.setId(i);
                        allEds.add(etPhone);
                        phoneSp.setAdapter(phoneNoType1);
                        allPhoneSp.add(phoneSp);


                        //etPhone.setText(textIn.getText().toString());
                        final Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                        // buttonRemove.setId(i);
                        // i++;
                        k++;


                        buttonRemove.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                ((LinearLayout) addView.getParent()).removeView(addView);
                                //allEds.remove(buttonRemove.getId());
                                k--;


                            }
                        });


                        container.addView(addView);
                    }
                }
            });
            emailAdd.setOnClickListener(new View.OnClickListener() {


                public void onClick(View arg0) {
                    if (j < 5) {
                        LayoutInflater layoutInflater =
                                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View addView = layoutInflater.inflate(R.layout.row, null);
                        EditText etEmail = (EditText) addView.findViewById(R.id.editTextPhone1);
                        emailSp = (Spinner) addView.findViewById(R.id.spinnerPhone1);


                        //etEmail.setId(i);
                        emailAllEt.add(etEmail);
                        emailSp.setAdapter(emailType1);
                        emailAllSp.add(emailSp);


                        //etEmail.setText(textIn.getText().toString());
                        final Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                        //buttonRemove.setId(i);
                        // i++;
                        j++;


                        buttonRemove.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                ((LinearLayout) addView.getParent()).removeView(addView);
                                //allEds.remove(buttonRemove.getId());
                                j--;


                            }
                        });


                        containerEmail.addView(addView);
                    }
                }
            });
        }


    }

    public void initialization() {
        phone_spinner = (Spinner) findViewById(R.id.spinnerPhone);
        email_spinner = (Spinner) findViewById(R.id.spinnerEmail);
        date_type = (Spinner) findViewById(R.id.spinnerDate);
        group_spinner = (Spinner) findViewById(R.id.spinnerGroup);
        name_et = (EditText) findViewById(R.id.editTextNameFirst);
        phone_et = (EditText) findViewById(R.id.editTextPhone);
        email_et = (EditText) findViewById(R.id.editTextEmail);
        street_et = (EditText) findViewById(R.id.editTextStreet);
        po_box_et = (EditText) findViewById(R.id.editTextPoBox);
        city_et = (EditText) findViewById(R.id.editTextCity);
        state_et = (EditText) findViewById(R.id.editTextState);
        zip_code_et = (EditText) findViewById(R.id.editTextZipCode);
        note_et = (EditText) findViewById(R.id.editTextNote);
        reminder_et = (EditText) findViewById(R.id.editTextReminder);
        person_iv = (ImageView) findViewById(R.id.imageView);
        submit_btn = (Button) findViewById(R.id.buttonSubmit);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* public  void setAllInformation(){

         contactHelper.setName(name);
         contactHelper.setPhone1(phone);
         contactHelper.setEmail1(email);
         contactHelper.setCity(city);
         contactHelper.setPoBox(po_box);
         contactHelper.setState(state);
         contactHelper.setStreet(street);
         contactHelper.setZipCode(zip_code);
         contactHelper.setNote(note);

     }*/
    public void show(View view) {
        name = name_et.getText().toString();
        phone = phone_et.getText().toString();
        email = email_et.getText().toString();
        phType1 = phone_spinner.getSelectedItem().toString();
        emailType1 = email_spinner.getSelectedItem().toString();
        ArrayList<String> phones = new ArrayList<>();
        phones.add(phone);

        for (EditText p : allEds) {
            phones.add(p.getText().toString());
        }
        ArrayList<String> emails = new ArrayList<>();
        emails.add(email);
        //Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
        for (EditText e : emailAllEt) {
            emails.add(e.getText().toString());

        }
        phoneTypes = new ArrayList<>();
        phoneTypes.add(phType1);
        //Toast.makeText(getApplicationContext(), phType1, Toast.LENGTH_SHORT).show();
        for (Spinner pt : allPhoneSp) {
            phoneTypes.add(pt.getSelectedItem().toString());

        }
        emailTypes = new ArrayList<>();
        emailTypes.add(emailType1);
        //Toast.makeText(getApplicationContext(), emailType1, Toast.LENGTH_SHORT).show();
        for (Spinner et : emailAllSp) {
            emailTypes.add(et.getSelectedItem().toString());

        }

///       phType1 = phone_spinner.getSelectedItem().toString();
       // email = email_et.getText().toString();
        state = state_et.getText().toString();
        street = street_et.getText().toString();
        po_box = po_box_et.getText().toString();
        city = city_et.getText().toString();
        zip_code = zip_code_et.getText().toString();
        note = note_et.getText().toString();
        date = date_et.getText().toString();

        //Toast.makeText(getApplication(),ph2+" "+phType2+"           "+ph3+" "+phType3+"  "+ph4+"  "+phType4, Toast.LENGTH_LONG).show();
        //setAllInformation();
        //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
        contactHelper.setName(name);
        //Toast.makeText(getApplicationContext(), contactHelper.getName(), Toast.LENGTH_SHORT).show();
        contactHelper.setPhone(phones);
        contactHelper.setPhoneTypes(phoneTypes);
        contactHelper.setEmails(emails);
        contactHelper.setEmailTypes(emailTypes);
        contactHelper.setCity(city);
        contactHelper.setState(state);
        contactHelper.setZipCode(zip_code);
        contactHelper.setStreet(street);
        contactHelper.setPoBox(po_box);
        contactHelper.setDate(date);
        contactHelper.setNote(note);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        long insert = databaseHelper.addDetail(contactHelper);
        if (insert >= 1)
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddNewContactActivity.this,HomeViewActivity_1.class);
        startActivity(intent);
    }
}