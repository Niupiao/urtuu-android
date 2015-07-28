package ohjoseph.com.urtuu.MyAccount.Profile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

public class EditProfileActivity extends ActionBarActivity {

    public static final String USER_EXTRA = "EditProfileActivity.USER_EXTRA";

    private User mUser;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
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
