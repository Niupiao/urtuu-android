package ohjoseph.com.urtuu.Shared;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.ShopScreen.ViewPagerFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Inflate the viewpager fragment
        if (findViewById(R.id.fragment_container) != null) {
            ViewPagerFragment viewFrag = new ViewPagerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, viewFrag)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
