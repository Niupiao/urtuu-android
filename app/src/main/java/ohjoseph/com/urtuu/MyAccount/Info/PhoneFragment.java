package ohjoseph.com.urtuu.MyAccount.Info;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.Address;
import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class PhoneFragment extends DialogFragment {

    private User mUser;
    private String mPhone;
    private EditText mPhoneEt;
    private int mElement;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Remove the title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        mUser = User.get();
        mElement = getArguments().getInt("element");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phone, container, false);

        // Cancel button
        ImageView cancelButton = (ImageView) v.findViewById(R.id.x_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                getDialog().dismiss();
            }
        });

        mPhoneEt = (EditText)v.findViewById(R.id.phone_et);

        if(mElement > -1) {
            mPhone = mUser.getPhoneNumbers().get(mElement);
            mPhoneEt.setText(mPhone);
        }
        else {
            ((TextView)v.findViewById(R.id.title_bar)).setText("Add Phone Number");
        }

        Button acceptButton = (Button) v.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource data = DataSource.get(getActivity().getApplicationContext());
                String url = data.url + "mupdatephone?";
                url += "email=" + mUser.getEmail();
                url += "&password=" + mUser.getPassword();
                url += "&=phone" + URLEncoder.encode(mPhoneEt.getText().toString());
                url.trim();

                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Display the first 500 characters of the response string.
                                if(mElement > -1) {
                                    mUser.getPhoneNumbers().set(mElement, mPhoneEt.getText().toString());
                                }
                                else {
                                    mUser.getPhoneNumbers().add(mPhoneEt.getText().toString());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                // Add the request to the RequestQueue.
                VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
                getDialog().dismiss();
            }
        });

        return v;
    }
}