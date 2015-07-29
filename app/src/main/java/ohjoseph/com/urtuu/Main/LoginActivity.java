package ohjoseph.com.urtuu.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private CheckBox mRememberCheckBox;
    private EditText mPasswordField;
    private EditText mIdField;
    private LinearLayout ll;
    private LinearLayout logo;
    private LinearLayout loader;
    boolean loggedOut;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Checks if user has logged out
        if (getIntent() != null) {
            loggedOut = getIntent().getBooleanExtra("Logged Out", false);
        }
        // Get views
        ll = (LinearLayout) findViewById(R.id.sliding_ll);
        logo = (LinearLayout) findViewById(R.id.image);
        loader = (LinearLayout) findViewById(R.id.loading_circle);
        mIdField = (EditText) findViewById(R.id.username_et);
        mPasswordField = (EditText) findViewById(R.id.password_et);
        // Login button listener
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide keyboard and start animation
                InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                startLoginAnimation();

                // Delayed start
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendLoginRequest();
                    }
                }, 1900);
            }
        });

        // Initialize saved user preferences
        mRememberCheckBox = (CheckBox) findViewById(R.id.remember_checkbox);
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        if (settings.getBoolean("rememberLogin", false)) {
            // Automatically log in user
            if (!loggedOut) {
                //mLoginButton.callOnClick();
            }
            mIdField.setText(settings.getString("login", ""));
            mRememberCheckBox.setChecked(settings.getBoolean("rememberLogin", false));
        }

    }

    // Create and send login request to server
    private void sendLoginRequest() {
        User user = User.create(mIdField.getText().toString(), mPasswordField.getText().toString());

        String url = DataSource.url + "mlogin?email=";
        url += user.getEmail() + "&password=" + user.getPassword();

        // Formulate the request and handle the response.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        end(); /*
                        try {
                            // Returns to login screen
                            String check = response.getString("error");
                            if (check.contains("Invalid key")) {
                                // Wrong login information
                                returnToLogin();
                                Toast.makeText(getApplicationContext(), "Wrong key. Please try again.", Toast.LENGTH_LONG).show();
                            } else {
                                // Start application
                                Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                                startActivity(intent);
                                end();
                            }
                        } catch (Exception e) {
                            // Bad server response
                            returnToLogin();
                            Log.e("JSON login error: ", e.toString());
                            Toast.makeText(getApplicationContext(), "Login error. Please try again.", Toast.LENGTH_LONG).show();
                        }
                        */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Bad server response
                        returnToLogin();
                        Toast.makeText(getApplicationContext(), "Login error. Please try again.", Toast.LENGTH_LONG).show();
                    }
                });
        // Add request to Queue
        VolleySingleton.getInstance(context).addToRequestQueue(jsonRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("rememberLogin", mRememberCheckBox.isChecked());
        if (mRememberCheckBox.isChecked()) {
            editor.putString("login", mIdField.getText().toString());
        }
        // Commit the edits!
        editor.commit();
    }

    // Move views
    private void startLoginAnimation() {
        // Transition to loading animation
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(400);
        fadeOut.setFillAfter(true);
        ll.startAnimation(fadeOut);
        ll.setVisibility(View.GONE);

        // Fade in loader
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(300);
        fadeIn.setStartOffset(300);
        fadeIn.setFillAfter(true);
        loader.startAnimation(fadeIn);
        loader.setVisibility(View.VISIBLE);

        // Move logo down
        Animation moveDown = new TranslateAnimation(0, 0, 0, 300);
        moveDown.setDuration(600);
        moveDown.setStartOffset(200);
        moveDown.setInterpolator(new DecelerateInterpolator());
        moveDown.setFillAfter(true);
        logo.startAnimation(moveDown);
    }

    // Reverse prior animation
    private void returnToLogin() {

        // Fade out loader
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(150);
        fadeOut.setFillAfter(true);
        loader.clearAnimation();
        loader.startAnimation(fadeOut);
        loader.setVisibility(View.GONE);

        // Move logo up
        Animation moveUp = new TranslateAnimation(0, 0, 200, 0);
        moveUp.setDuration(600);
        moveUp.setStartOffset(200);
        moveUp.setInterpolator(new DecelerateInterpolator());
        moveUp.setFillAfter(true);
        logo.startAnimation(moveUp);

        // Fade textviews in
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setStartOffset(300);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        ll.startAnimation(fadeIn);
        ll.setVisibility(View.VISIBLE);
    }

    private void end() {
        finish();
    }
}
