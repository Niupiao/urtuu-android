package ohjoseph.com.urtuu.Sell;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class SellViewPagerFragment extends Fragment {

    final int NUM_PAGES = 2;

    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell_pager, parent, false);

        // Set up the ViewPager with tabs
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                // Initialize Fragments
                if (position == 0) {
                    return new NewItemFragment();
                } else {
                    return new ItemListingsFragment();
                }
            }

            @Override
            public int getCount() {
                return NUM_PAGES;
            }

            @Override
            public String getPageTitle(int position) {
                // Set the tab names
                if (position == 0) {
                    return "New Item";
                } else {
                    return "Listings";
                }
            }
        });

        // Initialize Tabs
        TabLayout tabs = (TabLayout) v.findViewById(R.id.sell_tabs);
        tabs.setupWithViewPager(mViewPager);

        return v;
    }
}
