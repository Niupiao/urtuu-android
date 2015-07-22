package ohjoseph.com.urtuu.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Shop.CartFragment;


public class MainActivity extends AppCompatActivity {

    TabListener mTabListener;
    TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        mTitleText = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize the tab listener
        mTabListener = new TabListener(getSupportFragmentManager());

        // Initialize the TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.bottom_tabs);
        tabs.setTabsFromPagerAdapter(new FragmentAdapter(getSupportFragmentManager(), this));

        // Set up the tab listener
        tabs.setOnTabSelectedListener(mTabListener);
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

    public void setTitle(String title) {
        mTitleText.setText(title);
    }
}
