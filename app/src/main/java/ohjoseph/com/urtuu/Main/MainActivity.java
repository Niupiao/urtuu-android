package ohjoseph.com.urtuu.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Shop.CartFragment;


public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get ViewPager and set its PagerAdapter to handle Fragments
        mViewPager = (ViewPager) findViewById(R.id.viewPager_main_tabs);
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), this));

        // Connect TabLayout with the Viewpager
        TabLayout tabs = (TabLayout) findViewById(R.id.bottom_tabs);
        tabs.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) { // Show the cart
            CartFragment cartFrag = new CartFragment();
            cartFrag.show(getSupportFragmentManager(), "My Cart");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Pops the fragment at the top of the stack
        getSupportFragmentManager().popBackStack();
        return true;
    }

}
