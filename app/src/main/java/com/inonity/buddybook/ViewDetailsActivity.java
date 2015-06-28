package com.inonity.buddybook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import CustomAdapter.PhoneAdapter;
import HelperClasses.ContactHelper;

public class ViewDetailsActivity extends ActionBarActivity {

    private ArrayList<String> PhoneNumber = new ArrayList<>();
    private String Name;
    private ListView phoneNoList;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        phoneNoList = (ListView) findViewById(R.id.phoneNoList);
        name = (TextView) findViewById(R.id.name);

        Name = getIntent().getExtras().getString("Name");
        name.setText(Name);

        PhoneNumber = getIntent().getExtras().getStringArrayList("Phone Numbers");
        PhoneAdapter phoneAdapter = new PhoneAdapter(this, 0, PhoneNumber);
        phoneNoList.setAdapter(phoneAdapter);
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
