package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inonity.buddybook.R;

import java.util.List;

import HelperClasses.ContactHelper;

public class ContactAdapter extends ArrayAdapter<ContactHelper> {

    private final Context context;
    private List<ContactHelper> items;

    public ContactAdapter(Context context, List<ContactHelper> items) {
        super(context, R.layout.custom_layout_name_pic_display, items);
        this.context = context;
        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_layout_name_pic_display, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textViewContactName);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewContactImage);
        textView.setText(items.get(position).getName());
        imageView.setImageResource(R.drawable.contact_temp);

        return view;
    }
}
