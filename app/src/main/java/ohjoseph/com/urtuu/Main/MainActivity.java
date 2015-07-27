package ohjoseph.com.urtuu.Main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ohjoseph.com.urtuu.R;


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

        //Initialize Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
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
