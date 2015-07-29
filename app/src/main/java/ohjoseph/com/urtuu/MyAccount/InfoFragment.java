package ohjoseph.com.urtuu.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.MyAccount.Settings.MyDeliveriesActivity;
import ohjoseph.com.urtuu.MyAccount.Settings.MyInfoActivity;
import ohjoseph.com.urtuu.MyAccount.Settings.MyPaymentsActivity;
import ohjoseph.com.urtuu.MyAccount.Settings.MyRatingsActivity;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class InfoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, parent, false);

        Button editProfile = (Button) v.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyRatingsActivity.EditProfileActivity.class);
                startActivity(i);
            }
        });

        LinearLayout myRatings = (LinearLayout) v.findViewById(R.id.ratings);
        myRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyRatingsActivity.class);
                startActivity(i);
            }
        });

        RelativeLayout myPayments = (RelativeLayout) v.findViewById(R.id.payments);
        myPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyPaymentsActivity.class);
                startActivity(i);
            }
        });

        RelativeLayout myDeliveries = (RelativeLayout) v.findViewById(R.id.deliveries);
        myDeliveries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyDeliveriesActivity.class);
                startActivity(i);
            }
        });

        RelativeLayout myInfo = (RelativeLayout) v.findViewById(R.id.info);
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MyInfoActivity.class);
                startActivity(i);
            }
        });

        RelativeLayout confirmation = (RelativeLayout) v.findViewById(R.id.confirmation);
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataSource data = DataSource.get(getActivity().getApplicationContext());
                User user = User.get();
                String url = data.url + "/mconfirmemail?";
                url += "email=" + user.getEmail();
                url += "&password=" + user.getPassword();

                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(getActivity().getApplicationContext(), "Confirmation Sent", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                // Add the request to the RequestQueue.
                VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
            }
        });


        return v;
    }
}