package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.Main.MainActivity;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class ItemViewPagerFragment extends Fragment {

    public static final String EXTRA_NAME = "Item Name";

    Toolbar mToolbar;
    MainActivity mParent;
    ViewPager mViewPager;
    ArrayList<Item> mItems;

    public static ItemViewPagerFragment newInstance(Item i) {
        ItemViewPagerFragment frag = new ItemViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NAME, i.getName());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Finds the toolbar and parent activity for further reference
        mParent = (MainActivity) getActivity();
        mToolbar = (Toolbar) mParent.findViewById(R.id.action_toolbar);

        mItems = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_buy_item, container, false);

        mViewPager = (ViewPager) v.findViewById(R.id.item_viewpager);
        mViewPager.setAdapter(new android.support.v4.app.FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //return ItemFragment.newInstance();
                return new ItemFragment();
            }

            @Override
            public int getCount() {
                return mItems.size();
            }
        });
        return v;
    }
}
