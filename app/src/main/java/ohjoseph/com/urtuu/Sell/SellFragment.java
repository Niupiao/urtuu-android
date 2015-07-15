package ohjoseph.com.urtuu.Sell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class SellFragment extends Fragment {

    FragmentTabHost mTabHost;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Create FragmentTabHost
        mTabHost = new FragmentTabHost(getActivity());
        // Locate fragment1.xml to create FragmentTabHost
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_sell);
        mTabHost.addTab(mTabHost.newTabSpec("groups").setIndicator("Tab 1"), NewItemFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Tab 2"), ItemListingsFragment.class, null);

        return mTabHost;
    }
}
