package ohjoseph.com.urtuu.MyAccount.Deliveries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Receipt;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

public class MyDeliveriesActivity extends ActionBarActivity {

    private User mUser;
    private ListView mDeliveryLV;
    private ArrayList<Receipt> mDeliveries;
    private ArrayList<HashMap<String, String>> mDeliveryMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deliveries);

        mUser = User.get();
        mDeliveryLV = (ListView)findViewById(R.id.delivery_lv);
        mDeliveries = new ArrayList<>();
        mDeliveryMap = new ArrayList<>();

        final SimpleAdapter deliveryAdapter = new SimpleAdapter(this, mDeliveryMap, R.layout.list_delivery,
                new String[] {"item_name", "item_quantity", "status", "charge"},
                new int[] {R.id.name_view, R.id.quantity_view, R.id.status_view, R.id.charge_view});

        mDeliveryLV.setAdapter(deliveryAdapter);

        DataSource data = DataSource.get(getApplicationContext());
        String url = data.url + "msalereceipts?";
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
                        Type deliveryType = new TypeToken<ArrayList<Receipt>>() {}.getType();
                        mDeliveries = gson.fromJson(response.toString(), deliveryType);
                        for(Receipt r : mDeliveries) {
                            mDeliveryMap.add(deriveReceiptMap(r));
                        }
                        deliveryAdapter.notifyDataSetChanged();
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


    private HashMap<String, String> deriveReceiptMap(Receipt receipt) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("item_name", receipt.item_name);
        hashMap.put("item_quantity", " x" + receipt.item_quantity);
        hashMap.put("status", " (" + receipt.status + ")");
        hashMap.put("charge", "$" + receipt.charge);
        return hashMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_deliveries, menu);
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
