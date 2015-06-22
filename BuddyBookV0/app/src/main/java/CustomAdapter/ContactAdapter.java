package CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
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

	private Activity activity;
	private List<ContactHelper> items;
	private int row;
	private ContactHelper objBean;

	public ContactAdapter(Activity act, int row, List<ContactHelper> items) {
		super(act, row, items);

		this.activity = act;
		this.row = row;
		this.items = items;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		objBean = items.get(position);

		holder.tvname = (TextView) view.findViewById(R.id.textViewContactName);
		//holder.tvPhoneNo = (TextView) view.findViewById(R.id.tvphone);
		holder.ivpic = (ImageView)view.findViewById(R.id.imageViewContactImage);

		if (holder.tvname != null && null != objBean.getName()
				&& objBean.getName().trim().length() > 0) {
			holder.tvname.setText(Html.fromHtml(objBean.getName()));
		}

		return view;
	}

	public class ViewHolder {
		public TextView tvname;
		public ImageView ivpic;
	}

}
