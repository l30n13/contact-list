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

        Bitmap bitmap = null;

        TextView textView = (TextView) view.findViewById(R.id.textViewContactName);
        textView.setText(items.get(position).getName());

        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewContactImage);

        //Converting String into Bitmap format
        /*try {
            byte[] encodeByte = Base64.decode(items.get(position).getImage(), Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
        }*/
        String image_uri = items.get(position).getImage();

        if (image_uri != null) {
            imageView.setImageURI(Uri.parse(image_uri));
        }
        return view;
    }
}
