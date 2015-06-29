package com.inonity.buddybook;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import CustomAdapter.PhoneDetailsAdapter;

public class ViewDetailsActivity extends ActionBarActivity {

    private ArrayList<String> PhoneNumber = new ArrayList<>();
    private String Name;
    private ListView phoneNoList;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
//        phoneNoList = (ListView) findViewById(R.id.phoneNoList);
        name = (TextView) findViewById(R.id.name);

        Name = getIntent().getExtras().getString("Name");
        name.setText(Name);

        PhoneNumber = getIntent().getExtras().getStringArrayList("Phone Numbers");

        LinearLayout layout = (LinearLayout) findViewById(R.id.phoneNoList);
        int i = 0;
        try {
            for (String p : PhoneNumber) {
                Log.i("Name and phone", p);
                TextView valueTV = new TextView(this);
                valueTV.setText(p);
                //valueTV[i].setId(5);
                valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                layout.addView(valueTV);
                i++;
            }
        } catch (Exception e) {
            int a = PhoneNumber.lastIndexOf(PhoneNumber);
            Log.i("Name and phone", String.valueOf(a));
        }


        //PhoneDetailsAdapter phoneAdapter = new PhoneDetailsAdapter(this, PhoneNumber);
        //ArrayAdapter<String> phoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, PhoneNumber);
        //phoneNoList.setAdapter(phoneAdapter);
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
