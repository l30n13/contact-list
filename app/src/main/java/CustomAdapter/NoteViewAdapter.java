package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inonity.buddybook.R;

import java.util.List;

import HelperClasses.ContactHelper;


public class NoteViewAdapter extends ArrayAdapter<ContactHelper> {

    public NoteViewAdapter(Context context, List<ContactHelper> items) {
        super(context, R.layout.custom_layout_note_disply, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_layout_note_disply, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textViewContactNameForNote);
        textView.setText(getItem(position).getName());

        //ImageView imageView = (ImageView) view.findViewById(R.id.imageViewContactImage);
        TextView txtNote = (TextView) view.findViewById(R.id.editTextContactNote);
        txtNote.setText("Note: "+getItem(position).getNote());

        return view;
    }
}

