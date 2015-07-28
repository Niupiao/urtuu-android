package ohjoseph.com.urtuu.MyAccount.Payments;

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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Payment;
import ohjoseph.com.urtuu.Data.Receipt;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

public class MyPaymentsActivity extends ActionBarActivity {

    private ImageView mAddPayment;

    private ListView mPaymentLV;

    private ArrayList<Payment> mPayments;

    private ArrayList<HashMap<String,String>> mPaymentMapList;

    private User mUser;

    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_payments);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        mTitleText = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mUser = User.get();

        mAddPayment = (ImageView) findViewById(R.id.add_payment);
        mAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", -1);
                PaymentFragment paymentFrag = new PaymentFragment();
                paymentFrag.setArguments(bundle);
                paymentFrag.show(getSupportFragmentManager(), "Add Payment Method");
            }
        });

        mPaymentLV = (ListView) findViewById(R.id.payment_lv);

        mPayments = new ArrayList<>();
        mPaymentMapList = new ArrayList<>();

        final SimpleAdapter paymentAdapter = new SimpleAdapter(this, mPaymentMapList, R.layout.list_payment,
                new String[] { "payment_type", "account", "cvv", "holder", "exp_date" },
                new int[] { R.id.payment_type_view,
                    R.id.account_view,
                    R.id.cvv_view,
                    R.id.holder_view,
                    R.id.exp_date_view });

        mPaymentLV.setAdapter(paymentAdapter);

        mPaymentLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("element", position);
                PaymentFragment paymentFrag = new PaymentFragment();
                paymentFrag.setArguments(bundle);
                paymentFrag.show(getSupportFragmentManager(), "Update Payment Method");
            }
        });


        DataSource data = DataSource.get(getApplicationContext());
        String url = data.url + "mgetpaymentmethods?";
        url += "email=" + mUser.getEmail();
        url += "&password=" + mUser.getPassword();

        // Request a string response from the provided URL.
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(), "Request Successful", Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        Type paymentType = new TypeToken<ArrayList<Payment>>() {
                        }.getType();
                        mPayments = gson.fromJson(response.toString(), paymentType);
                        for (Payment p : mPayments) {
                            mPaymentMapList.add(derivePaymentMap(p));
                        }
                        paymentAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    private HashMap<String, String> derivePaymentMap(Payment payment) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("payment_type", payment.payment_type);
        if(payment.payment_type.equals("wire") || payment.payment_type.equals("deposit")) {
            hashMap.put("account", "Account Number: " + payment.bank_account);
        }
        else if(payment.payment_type.equals("card")) {
            hashMap.put("account", "Card Number: " + payment.card_number);
            hashMap.put("cvv", "CVV: " + payment.cvv);
            hashMap.put("holder", "Card Holder: " + payment.holder);
            hashMap.put("exp_date", "" + payment.exp_month + "/" + payment.exp_year);
        }
        return hashMap;
    }

    public ArrayList<Payment> getPayments() {
        return mPayments;
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
