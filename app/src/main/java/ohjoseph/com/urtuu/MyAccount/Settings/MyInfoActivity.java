package ohjoseph.com.urtuu.MyAccount.Settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ohjoseph.com.urtuu.Data.Address;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.R;

public class MyInfoActivity extends ActionBarActivity {

    private ImageView mAddAddress;
    private ImageView mAddPhone;

    private ListView mAddressLV;
    private ListView mPhoneLV;

    private ArrayList<Address> mAddresses;
    private ArrayList<String> mPhoneNumbers;

    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        mTitleText = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAddAddress = (ImageView) findViewById(R.id.add_address);
        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", -1);
                AddressFragment addressFrag = new AddressFragment();
                addressFrag.setArguments(bundle);
                addressFrag.show(getSupportFragmentManager(), "Add Address");
            }
        });

        mAddressLV = (ListView) findViewById(R.id.address_lv);

        mAddresses = User.get().getAddresses();

        ArrayList<HashMap<String, Address>> addressMapList = new ArrayList<>();

        for(Address a : mAddresses) {
            addressMapList.add(createAddress(a));
        }

        SimpleAdapter addressAdapter = new SimpleAdapter(this, addressMapList, R.layout.list_address, new String[] { "address" }, new int[] { R.id.address_view });

        mAddressLV.setAdapter(addressAdapter);

        mAddressLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", position);
                AddressFragment addressFrag = new AddressFragment();
                addressFrag.setArguments(bundle);
                addressFrag.show(getSupportFragmentManager(), "Update Address");
                    }
        });

        mAddPhone = (ImageView)findViewById(R.id.add_phone);
        mAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", -1);
                PhoneFragment phoneFrag = new PhoneFragment();
                phoneFrag.setArguments(bundle);
                phoneFrag.show(getSupportFragmentManager(), "Add Phone Number");
            }
        });
        mPhoneLV = (ListView)findViewById(R.id.phone_lv);
        mPhoneNumbers = User.get().getPhoneNumbers();
        ArrayList<HashMap<String,String>> phoneMapList = new ArrayList<>();
        for(String s : mPhoneNumbers) {
            phoneMapList.add(createPhoneNumber(s));
        }
        SimpleAdapter phoneAdapter = new SimpleAdapter(this, phoneMapList, R.layout.list_phone, new String[] { "phone" }, new int[] { R.id.phone_view });
        mPhoneLV.setAdapter(phoneAdapter);

        mPhoneLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", position);
                PhoneFragment phoneFrag = new PhoneFragment();
                phoneFrag.setArguments(bundle);
                phoneFrag.show(getSupportFragmentManager(), "Update Phone Number");
            }
        });

    }

    private HashMap<String, Address> createAddress(Address address) {
        HashMap<String, Address> hashMap = new HashMap<>();
        hashMap.put("address", address);
        return hashMap;
    }

    private HashMap<String, String> createPhoneNumber(String phone) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        return hashMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_info, menu);
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
}
