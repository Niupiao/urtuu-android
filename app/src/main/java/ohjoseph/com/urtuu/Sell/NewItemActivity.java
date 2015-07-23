package ohjoseph.com.urtuu.Sell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import ohjoseph.com.urtuu.R;

public class NewItemActivity extends AppCompatActivity {

    NewItemFragment mItemFragment;
    SearchFragment mSearchFragment;
    EditText mSearchBar;
    LinearLayout dummy;
    boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // Initialize the searchbar
        mSearchBar = (EditText) findViewById(R.id.search);
        mSearchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Show search fragment if not in search mode
                    if (!isSearch) {
                        getSupportFragmentManager().beginTransaction()
                                .hide(mItemFragment)
                                .add(R.id.fragment_container, mSearchFragment)
                                .addToBackStack(null)
                                .commit();
                        isSearch = true;
                    }
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.stub_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New Item");

        dummy = (LinearLayout) findViewById(R.id.dummy);

        // Host the New Item Fragment
        mSearchFragment = new SearchFragment();
        mItemFragment = new NewItemFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mItemFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_new_item, menu);
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
        super.onBackPressed();

        // Change isSearch back
        isSearch = false;
        mSearchBar.clearFocus();
    }
}
