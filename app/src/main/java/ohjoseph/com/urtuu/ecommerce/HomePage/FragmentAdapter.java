package ohjoseph.com.urtuu.ecommerce.HomePage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ohjoseph.com.urtuu.ecommerce.Tabs.AccountFragment;
import ohjoseph.com.urtuu.ecommerce.Tabs.BrowseFragment;
import ohjoseph.com.urtuu.ecommerce.Tabs.ExploreFragment;
import ohjoseph.com.urtuu.ecommerce.Tabs.SellFragment;
import ohjoseph.com.urtuu.ecommerce.Tabs.ShopFragment;

/**
 * Created by Joseph on 7/3/15.
 */
public class FragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    final String[] Titles = {"Browse", "Shop", "Explore", "Sell", "Account"};
    private Context mContext;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BrowseFragment();
        } else if (position == 1) {
            return new ShopFragment();
        } else if (position == 2) {
            return new ExploreFragment();
        } else if (position == 3) {
            return new SellFragment();
        } else {
            return new AccountFragment();
        }
    }
}
