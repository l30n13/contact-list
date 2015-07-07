package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.inonity.buddybook.R;
import com.inonity.buddybook.ViewDetailsActivity;

import java.util.ArrayList;

import Database.DatabaseHelper;
import HelperClasses.ContactHelper;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class SearchFragment extends Fragment {



    private DatabaseHelper db;
    private ListView listView;
    private ArrayList<ContactHelper> list = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayAdapter <String> adapter;
    private ContactHelper objContact;

    // Search EditText
    EditText inputSearch;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        listView = (ListView)rootview.findViewById(R.id.list_view);
        //listView.setOnItemSelectedListener(this);
        inputSearch = (EditText)rootview.findViewById(R.id.inputSearch);

        //listView.setOnItemClickListener(getActivity());
        db = new DatabaseHelper(getActivity());
        list =  db.getAllData();

        for(int i = 0 ;i <list.size();i++){
            nameList.add(list.get(i).getName());
        }

        adapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_layout_name_pic_display,R.id.textViewContactName, nameList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(getActivity(), ViewDetailsActivity.class);
                Toast.makeText(getActivity(), "clicked item " + position, Toast.LENGTH_LONG).show();
                Log.i("TEST", list.get(position).getName());


                i.putExtra("Name", list.get(position).getName());
                i.putExtra("Image", list.get(position).getImage());
                i.putExtra("Phone Numbers", list.get(position).getPhone());
                startActivity(i);
            }
        });



        /*ContactAdapter objAdapter = new ContactAdapter(this, list);
        listView.setAdapter(objAdapter);*/

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return rootview;
    }



    @Override
    public void onDetach() {
        super.onDetach();

    }




}
