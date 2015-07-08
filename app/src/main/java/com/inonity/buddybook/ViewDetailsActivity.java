package com.inonity.buddybook;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

import Database.DatabaseHelper;

public class ViewDetailsActivity extends ActionBarActivity {

    private ArrayList<String> PhoneNumber = new ArrayList<>(), allEmail = new ArrayList<>();
    private String Name;
    private String Image, Address;
    private ListView phoneNoList;
    private TextView name, address, note;
    private ImageView image;
    DatabaseHelper db;
    EditText reminder;

    String pId;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        db = new DatabaseHelper(this);
//        phoneNoList = (ListView) findViewById(R.id.phoneNoList);
        name = (TextView) findViewById(R.id.name);
        Name = getIntent().getExtras().getString("Name");
        name.setText(Name);

        id = getIntent().getExtras().getInt("id");


        image = (ImageView) findViewById(R.id.imageView);
        Image = getIntent().getExtras().getString("Image");

        /*try {
            byte[] encodeByte = Base64.decode(Image, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
        }*/

        if (Image != null) {
            image.setImageURI(Uri.parse(Image));
        }

        address = (TextView) findViewById(R.id.fullAddress);
        Address = getIntent().getExtras().getString("Address");
        if (!Address.equals("null null null null null")) {
            address.setText(Address);
        } else {
            address.setText(null);
        }
        note = (TextView) findViewById(R.id.note);
        note.setText(getIntent().getExtras().getString("Note"));

        //For phone numbers
        PhoneNumber = getIntent().getExtras().getStringArrayList("Phone Numbers");

        LinearLayout phoneNoLayout = (LinearLayout) findViewById(R.id.phoneNoLayout);
        LinearLayout callLayout = (LinearLayout) findViewById(R.id.callButtonLayout);
        LinearLayout smsLayout = (LinearLayout) findViewById(R.id.smsButtonLayout);
        int i = 0;
        try {
            for (String p : PhoneNumber) {

                //Creating textView
                if (p != null) {
                    final TextView phoneNo = new TextView(this);
                    phoneNo.setId(i);
                    phoneNo.setText(p);
                    phoneNo.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 2));
                    phoneNo.setTextSize(20);
                    phoneNo.setHeight(100);
                    phoneNo.setPadding(0, 10, 0, 0);
                    phoneNoLayout.addView(phoneNo);

                    //Creating imageButton for call
                    ImageButton callButton = new ImageButton(this);
                    callButton.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 10));
                    callButton.setImageResource(R.drawable.ic_call);
                    callButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + phoneNo.getText()));
                            startActivity(intent);
                            //Toast.makeText(getApplicationContext(), phoneNo.getText(), Toast.LENGTH_LONG).show();
                        }
                    });
                    callLayout.addView(callButton);

                    //Creating imageButton for sms
                    ImageButton smsButton = new ImageButton(this);
                    smsButton.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    smsButton.setImageResource(R.drawable.ic_sms);
                    smsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        /*try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(String.valueOf(phoneNo.getText()), null, null, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setData(Uri.parse("sms:" + phoneNo.getText()));
                            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(sendIntent);
                        }
                    });
                    smsLayout.addView(smsButton);

                    i++;
                }
            }

        } catch (Exception e) {
            int a = PhoneNumber.lastIndexOf(PhoneNumber);
            Log.i("Name and phone", String.valueOf(a));
        }

        //For email
        allEmail = getIntent().getExtras().getStringArrayList("Email");
        //Log.i("TEST", allEmail.toString());
        LinearLayout emailLayout = (LinearLayout) findViewById(R.id.emailLayout);
        LinearLayout emailButtonLayout = (LinearLayout) findViewById(R.id.emailButtonLayout);
        i = 0;
        try {
            for (String e : allEmail) {

                if (e != null) {
                    //Creating textView
                    final TextView Email = new TextView(this);
                    Email.setId(i);
                    Email.setText(e);
                    Email.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 2));
                    Email.setTextSize(20);
                    Email.setPadding(0, 10, 0, 0);
                    emailLayout.addView(Email);

                    //Creating imageButton for sending email
                    ImageButton emailButton = new ImageButton(this);
                    emailButton.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    emailButton.setImageResource(R.drawable.email);
                    emailButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
                            sendEmailIntent.setType("message/rfc822");
                            sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Email.getText().toString()});
                            sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                            sendEmailIntent.putExtra(Intent.EXTRA_TEXT, "");
                            sendEmailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(sendEmailIntent, "Send email using...."));
                        }
                    });
                    emailButtonLayout.addView(emailButton);

                    i++;
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }



        //floating action button
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_settings);
        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        ImageView iconItem1 = new ImageView(this);

        iconItem1.setImageResource(R.drawable.ic_delete);
        ImageView iconItem2 = new ImageView(this);
        iconItem2.setImageResource(R.drawable.ic_edit);


        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton buttonDelete = itemBuilder.setContentView(iconItem1).build();
        SubActionButton buttonEdit = itemBuilder.setContentView(iconItem2).build();



        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)

                .addSubActionView(buttonDelete)
                .addSubActionView(buttonEdit)
                .attachTo(actionButton)
                .build();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(db.delete(id)) {
                    Toast.makeText(getApplication(), "Deleted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ViewDetailsActivity.this,HomeViewActivity_1.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplication(), "not Delete", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ViewDetailsActivity.this,HomeViewActivity_1.class);
                    startActivity(i);
                }

            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        //end of floating fab
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_details, menu);
        return true;
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
}
