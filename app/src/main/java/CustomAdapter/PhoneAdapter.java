package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.inonity.buddybook.R;

import java.util.ArrayList;
import java.util.List;

import HelperClasses.ContactHelper;

/**
 * Created by tonmoy on 6/25/15.
 */
public class PhoneAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> items;

    public PhoneAdapter(Context context, int resource, ArrayList<String> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_layout_details_phone_number, parent, false);

        EditText phoneNo = (EditText) view.findViewById(R.id.phoneNo);
        /*ImageButton normalCall = (ImageButton) view.findViewById(R.id.normalCall);
        ImageButton videoCall = (ImageButton) view.findViewById(R.id.videoCall);*/
        phoneNo.setText(items.get(position).toString());
        //normalCall.setImageResource(R.drawable.contact);

        return view;
    }
}
