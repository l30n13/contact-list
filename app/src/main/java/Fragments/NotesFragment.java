package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inonity.buddybook.R;

import java.util.ArrayList;

import CustomAdapter.NoteViewAdapter;
import Database.DatabaseHelper;
import HelperClasses.ContactHelper;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    private DatabaseHelper db;
    private ListView listView;
    private ArrayList<ContactHelper> list = new ArrayList<>();
    private ArrayList<ContactHelper> listNote = new ArrayList<>();
    private ContactHelper objContact;


    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_notes, container, false);
        listView = (ListView) rootView.findViewById(R.id.listViewNotesFragment);

        db = new DatabaseHelper(getActivity());
        list =  db.getAllData();

        for(int i = 0 ;i <list.size();i++){
            String noteCheck = list.get(i).getNote();
            if(noteCheck != null) {
                objContact = new ContactHelper();
                String name = list.get(i).getName();
                String note = list.get(i).getNote();

                objContact.setName(name);
                objContact.setNote(note);

                listNote.add(objContact);

            }
        }

        print(listNote);

        return rootView;

    }

    private void print(ArrayList<ContactHelper> list) {
        NoteViewAdapter noteViewAdapter = new NoteViewAdapter(getActivity(),list);
        listView.setAdapter(noteViewAdapter);
    }


}
