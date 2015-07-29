package ohjoseph.com.urtuu.MyAccount.Settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
import ohjoseph.com.urtuu.Data.Review;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

public class MyRatingsActivity extends ActionBarActivity {

    private User mUser;
    private ListView mReviewLV;
    private ArrayList<Review> mReviews;
    private ArrayList<HashMap<String, String>> mReviewMap;

    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ratings);

        // Set up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        mTitleText = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mUser = User.get();
        mReviewLV = (ListView)findViewById(R.id.review_lv);
        mReviews = new ArrayList<>();
        mReviewMap = new ArrayList<>();

        final SimpleAdapter reviewAdapter = new SimpleAdapter(this, mReviewMap, R.layout.list_review,
                new String[] {"reviewer", "body", "rating"},
                new int[] {R.id.reviewer_view, R.id.body_view, R.id.rating_view});

        mReviewLV.setAdapter(reviewAdapter);

        DataSource data = DataSource.get(getApplicationContext());
        String url = data.url + "mselfreviews?";
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
                        Type reviewType = new TypeToken<ArrayList<Review>>() {}.getType();
                        mReviews = gson.fromJson(response.toString(), reviewType);
                        for(Review r : mReviews) {
                            mReviewMap.add(deriveReviewMap(r));
                        }
                        reviewAdapter.notifyDataSetChanged();
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


    private HashMap<String, String> deriveReviewMap(Review review) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("reviewer", "Written by: " + review.reviewer);
        hashMap.put("body", review.body);
        hashMap.put("rating", "Rating: " + review.rating);
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

    public static class EditProfileActivity extends ActionBarActivity {

        public static final String USER_EXTRA = "EditProfileActivity.USER_EXTRA";

        private User mUser;
        private EditText mFirstName;
        private EditText mLastName;
        private Button mUpdateButton;

        private TextView mTitleText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_profile);

            // Set up toolbar as action bar
            Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
            mTitleText = (TextView) findViewById(R.id.title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            mUser = User.get();
            if(mUser.getName() != null) {
                setTitle(mUser.getName());
            }
            mFirstName = (EditText)findViewById(R.id.edit_first_name);
            mLastName = (EditText)findViewById(R.id.edit_last_name);
            mUpdateButton = (Button)findViewById(R.id.edit_update_button);

            mFirstName.setText(mUser.getFirstName());
            mLastName.setText(mUser.getLastName());

            mUpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSource data = DataSource.get(getApplicationContext());
                    String url = data.url + "/mupdateuser?";
                    url += "email=" + mUser.getEmail();
                    url += "&password=" + mUser.getPassword();
                    url += "&first_name=" + mFirstName.getText();
                    url += "&last_name=" + mLastName.getText();

                    // Request a string response from the provided URL.
                    JsonObjectRequest request = new JsonObjectRequest(url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Display the first 500 characters of the response string.
                                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
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
            });
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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
}
