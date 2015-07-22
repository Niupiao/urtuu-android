package ohjoseph.com.urtuu.Main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Joseph on 7/3/15.
 */
public class FragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    final String[] Titles = {"Shop", "Sell", "Spark", "Look", "Me"};
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
        // Empty method
        return null;
    }

    @Override
    public String getPageTitle(int position) {
        return Titles[position];
    }
}
