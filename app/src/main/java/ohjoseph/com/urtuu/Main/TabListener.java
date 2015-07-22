package ohjoseph.com.urtuu.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ohjoseph.com.urtuu.MyAccount.AccountFragment;
import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Sell.SellFragment;
import ohjoseph.com.urtuu.Shop.ShopFragment;

/**
 * Created by Joseph on 7/16/15.
 */
public class TabListener implements TabLayout.OnTabSelectedListener {

    FragmentManager mFm;
    ShopFragment mShopFragment;
    SellFragment mSellFragment;
    AccountFragment mAccountFragment;
    Fragment curFragment;
    String tag;

    public TabListener(FragmentManager fm) {
        mFm = fm;
        mShopFragment = new ShopFragment();
        mSellFragment = new SellFragment();
        mAccountFragment = new AccountFragment();
        tag = "ShopFragment";

        mFm.beginTransaction()
                .add(R.id.fragment_container_tab, mShopFragment, "ShopFragment")
                .commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        Fragment frag;

        if (position == 0) {
            tag = "ShopFragment";
            frag = mShopFragment;
        } else  if (position == 1) {
            tag = "SellFragment";
            frag = mSellFragment;
        } else {
            tag = "AccountFragment";
            frag = mAccountFragment;
        }

        if (getSelectedTab(frag)) {
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

    private boolean getSelectedTab(Fragment frag) {
        curFragment = mFm.findFragmentByTag(tag);

        if (curFragment == null) {
            curFragment = frag;
            return false;
        }

        return true;
    }
}
