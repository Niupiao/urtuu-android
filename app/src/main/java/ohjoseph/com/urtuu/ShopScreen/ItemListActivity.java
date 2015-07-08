package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ohjoseph.com.urtuu.Data.Category;
import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Subcategory;
import ohjoseph.com.urtuu.R;

public class ItemListActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mToolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get the bundle
        Bundle args = getIntent().getExtras();
        String sub = args.getString(BuyItemListFragment.EXTRA_SUBCATEGORY);
        String cat = args.getString(BuyItemListFragment.EXTRA_CATEGORY);

        // Get the subcategory
        Category c = DataSource.get(this).getCategory(cat);
        Subcategory s = c.getSubcategory(sub);

        // Create new Fragment
        BuyItemListFragment frag = BuyItemListFragment.newInstance(s);

        // Add List Item fragment to container
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, frag)
                .addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        // Closes the activity if end of back stack
        if (count == 0) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
