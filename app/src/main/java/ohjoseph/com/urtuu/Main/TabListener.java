package ohjoseph.com.urtuu.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Sell.SellViewPagerFragment;
import ohjoseph.com.urtuu.Shop.ShopFragment;

/**
 * Created by Joseph on 7/16/15.
 */
public class TabListener implements TabLayout.OnTabSelectedListener {

    FragmentManager mFm;
    ShopFragment mShopFragment;
    SellViewPagerFragment mSellFragment;

    String tag;
    boolean test;

    public TabListener(FragmentManager fm) {
        mFm = fm;
        mShopFragment = new ShopFragment();
        mSellFragment = new SellViewPagerFragment();
        tag = "ShopFragment";

        mFm.beginTransaction()
                .add(R.id.fragment_container_tab, mShopFragment, "ShopFragment")
                .commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        Fragment curFragment;
        boolean exists = true;

        if (position == 0) {
            tag = "ShopFragment";
            curFragment = mFm.findFragmentByTag(tag);

            if (curFragment == null) {
                exists = false;
                curFragment = mShopFragment;
            }

        } else {
            tag = "SellFragment";
            curFragment = mFm.findFragmentByTag(tag);

            if (curFragment == null) {
                exists = false;
                curFragment = mSellFragment;
            }
        }

        if (exists) {
            mFm.beginTransaction().show(curFragment).commit();
        } else {
            mFm.beginTransaction().add(R.id.fragment_container_tab, curFragment, tag).commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        Fragment lastFrag;

        if (position == 0) {
            lastFrag = mFm.findFragmentByTag(tag);
        } else {
            lastFrag = mFm.findFragmentByTag(tag);
        }

        if (lastFrag != null)
            mFm.beginTransaction().hide(lastFrag).commit();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}
}
