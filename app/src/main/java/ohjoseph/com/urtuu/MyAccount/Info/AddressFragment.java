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
public class AddressFragment extends DialogFragment {

    private User mUser;
    private int mElement;
    private Address mAddress;
    private EditText mCityEt;
    private EditText mDistrictEt;
    private EditText mCommitteeEt;
    private EditText mStreetEt;
    private EditText mDoorEt;

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
        View v = inflater.inflate(R.layout.fragment_address, container, false);

        // Cancel button
        ImageView cancelButton = (ImageView) v.findViewById(R.id.x_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                getDialog().dismiss();
            }
        });

        mCityEt = (EditText) v.findViewById(R.id.city_et);

        mDistrictEt = (EditText) v.findViewById(R.id.district_et);

        mCommitteeEt = (EditText) v.findViewById(R.id.committee_et);

        mStreetEt = (EditText) v.findViewById(R.id.street_et);

        mDoorEt = (EditText) v.findViewById(R.id.door_et);

        if(mElement > -1) {
            mAddress = mUser.getAddresses().get(mElement);
            mCityEt.setText(mAddress.city);
            mDistrictEt.setText(mAddress.district);
            mCommitteeEt.setText(mAddress.committee);
            mStreetEt.setText(mAddress.street);
            mDoorEt.setText(mAddress.door);
        }
        else {
            ((TextView)v.findViewById(R.id.title_bar)).setText("Add Address");
        }

        Button acceptButton = (Button) v.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource data = DataSource.get(getActivity().getApplicationContext());
                String url = data.url + "mupdateaddress?";
                url += "email=" + mUser.getEmail();
                url += "&password=" + mUser.getPassword();
                url += "&city=" + URLEncoder.encode(mCityEt.getText().toString());
                url += "&district=" + URLEncoder.encode(mDistrictEt.getText().toString());
                url += "&committee=" + URLEncoder.encode(mCommitteeEt.getText().toString());
                url += "&street=" + URLEncoder.encode(mStreetEt.getText().toString());
                url += "&door=" + URLEncoder.encode(mDoorEt.getText().toString());
                url.trim();

                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Display the first 500 characters of the response string.
                                if(mElement > -1) {
                                    mUser.getAddresses().set(mElement, new Address(mCityEt.getText().toString(),
                                            mDistrictEt.getText().toString(),
                                            mCommitteeEt.getText().toString(),
                                            mStreetEt.getText().toString(),
                                            mDoorEt.getText().toString()));
                                }
                                else {
                                    mUser.getAddresses().add(new Address(mCityEt.getText().toString(),
                                            mDistrictEt.getText().toString(),
                                            mCommitteeEt.getText().toString(),
                                            mStreetEt.getText().toString(),
                                            mDoorEt.getText().toString()));
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