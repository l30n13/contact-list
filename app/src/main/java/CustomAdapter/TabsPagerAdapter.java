package CustomAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Fragments.ContactsFragment;
import Fragments.NotesFragment;
import Fragments.SearchFragment;


public class TabsPagerAdapter extends FragmentStatePagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}


	//int pos = getIntent().getExtras().getString("Name");

	@Override
	public Fragment getItem(int position) {
		// HomeViewActivity_1.MyFragment myFragment = HomeViewActivity_1.MyFragment.newInstance(position);
		Fragment myFragment=null;


		if(position == 0){
			myFragment = new ContactsFragment();

		}
		else if(position == 1){
			myFragment = new SearchFragment();

		}
		else if(position == 2){
			myFragment = new NotesFragment();

		}
		return myFragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		//return "Tab " + (position + 1);
		String tab = "";
		if(position == 0){
			tab = "   Contacts";
		}
		else if(position == 1){
			tab = "      Search";
		}
		else  if(position == 2){
			tab = "      Notes";
		}
		return tab;
	}
}
