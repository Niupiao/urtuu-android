package ohjoseph.com.urtuu.MyAccount;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Main.MainActivity;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/3/15.
 */
public class AccountFragment extends Fragment {

    final int NUM_PAGES = 2;
    ViewPager mViewPager;
    User mUser;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set title
        mUser = User.get();
        ((MainActivity) getActivity()).setTitle(mUser.getUsername());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        // Set up the ViewPager with tabs
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                // Initialize Fragments
                if (position == 0) {
                    return new InfoFragment();
                } else {
                    return new OrderHistoryFragment();
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
                    return "My Account";
                } else {
                    return "My Orders";
                }
            }
        });

        // Initialize Tabs
        TabLayout tabs = (TabLayout) v.findViewById(R.id.account_tabs);
        tabs.setupWithViewPager(mViewPager);
        return v;
    }
}
