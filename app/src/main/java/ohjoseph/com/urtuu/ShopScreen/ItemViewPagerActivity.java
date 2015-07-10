package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class ItemViewPagerActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ViewPager mViewPager;
    ArrayList<Item> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_viewpager);

        Bundle extras = getIntent().getExtras();
        String itemName = extras.getString(ItemFragment.EXTRA_NAME);

        // Initialize and set up the toolbar
        mToolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize and set up the view pager
        mItems = DataSource.get(getApplicationContext()).getItems();
        mViewPager = (ViewPager) findViewById(R.id.item_viewpager);
        mViewPager.setAdapter(new android.support.v4.app.FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(mItems.get(position));
            }

            @Override
            public int getCount() {
                return mItems.size();
            }
        });
    }
}
