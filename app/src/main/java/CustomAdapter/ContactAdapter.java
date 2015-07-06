package CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
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

    public ContactAdapter(Context context, List<ContactHelper> items) {
        super(context, R.layout.custom_layout_name_pic_display, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_layout_name_pic_display, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textViewContactName);
        textView.setText(getItem(position).getName());

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewContactImage);

        String image_uri = getItem(position).getImage();

        if (image_uri != null) {
            imageView.setImageURI(Uri.parse(image_uri));
        }
        return view;
    }
}

