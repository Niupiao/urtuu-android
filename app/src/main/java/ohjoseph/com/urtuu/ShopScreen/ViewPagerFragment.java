package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Shared.FragmentAdapter;

/**
 * Created by Joseph on 7/8/15.
 */
public class ViewPagerFragment extends Fragment {

    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        // Get ViewPager and set its PagerAdapter to handle Fragments
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_main_tabs);
        mViewPager.setAdapter(new FragmentAdapter(getActivity().getSupportFragmentManager(), getActivity()));

        // Connect TabLayout with the Viewpager
        TabLayout tabs = (TabLayout) view.findViewById(R.id.bottom_tabs);
        tabs.setupWithViewPager(mViewPager);

        return view;
    }
}
