package ohjoseph.com.urtuu.Main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ohjoseph.com.urtuu.Tabs.AccountFragment;
import ohjoseph.com.urtuu.Tabs.BrowseFragment;
import ohjoseph.com.urtuu.Tabs.ExploreFragment;
import ohjoseph.com.urtuu.Tabs.SellFragment;
import ohjoseph.com.urtuu.ShopScreen.ShopFragment;

/**
 * Created by Joseph on 7/3/15.
 */
public class FragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    final String[] Titles = {"Look", "Shop", "Spark", "Sell", "Me"};
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
            return BrowseFragment.newInstance(position + 1);
        } else if (position == 1) {
            return ShopFragment.newInstance(position + 1);
        } else if (position == 2) {
            return ExploreFragment.newInstance(position + 1);
        } else if (position == 3) {
            return SellFragment.newInstance(position + 1);
        } else {
            return AccountFragment.newInstance(position + 1);
        }
    }

    @Override
    public String getPageTitle(int position) {
        return Titles[position];
    }
}
