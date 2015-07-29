package ohjoseph.com.urtuu.MyAccount.Settings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.net.URLEncoder;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Payment;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class PaymentFragment extends DialogFragment {

    private User mUser;
    private int mElement;
    private Payment mPayment;
    private EditText mTypeEt;
    private EditText mBankAccountEt;
    private EditText mCardNumberEt;
    private EditText mCVVEt;
    private EditText mCardHolderEt;
    private EditText mExpMonthEt;
    private EditText mExpYearEt;
    private boolean mNewPayment = false;

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
        mPayment = ((MyPaymentsActivity)getActivity()).getPayments().get(mElement);

        View v;

        if(mPayment == null) {
            v = inflater.inflate(R.layout.fragment_new_payment, container, false);
            mTypeEt = (EditText) v.findViewById(R.id.type_et);
            mBankAccountEt = (EditText) v.findViewById(R.id.bank_account_et);
            mCardNumberEt = (EditText) v.findViewById(R.id.card_number_et);
            mCVVEt = (EditText) v.findViewById(R.id.cvv_et);
            mCardHolderEt = (EditText) v.findViewById(R.id.card_holder_et);
            mExpMonthEt = (EditText) v.findViewById(R.id.exp_month_et);
            mExpYearEt = (EditText) v.findViewById(R.id.exp_year_et);

        }
        else if(mPayment.payment_type.equals("wire") || mPayment.payment_type.equals("deposit")) {
            v = inflater.inflate(R.layout.fragment_bank_payment, container, false);
            mTypeEt = (EditText) v.findViewById(R.id.type_et);
            mBankAccountEt = (EditText) v.findViewById(R.id.bank_account_et);
        }
        else {
            v = inflater.inflate(R.layout.fragment_card_payment, container, false);
            mTypeEt = (EditText) v.findViewById(R.id.type_et);
            mCardNumberEt = (EditText) v.findViewById(R.id.card_number_et);
            mCVVEt = (EditText) v.findViewById(R.id.cvv_et);
            mCardHolderEt = (EditText) v.findViewById(R.id.card_holder_et);
            mExpMonthEt = (EditText) v.findViewById(R.id.exp_month_et);
            mExpYearEt = (EditText) v.findViewById(R.id.exp_year_et);
        }

        // Cancel button
        ImageView cancelButton = (ImageView) v.findViewById(R.id.x_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                getDialog().dismiss();
            }
        });

        if(mElement > -1) {
            mPayment = ((MyPaymentsActivity)getActivity()).getPayments().get(mElement);
            mTypeEt.setText(mPayment.payment_type);

            if(mPayment.payment_type.equals("wire") || mPayment.payment_type.equals("deposit")) {
                mBankAccountEt.setText(mPayment.bank_account);
            }
            else if(mPayment.payment_type.equals("card")) {
                mCardNumberEt.setText(mPayment.card_number);
                mCVVEt.setText(mPayment.cvv);
                mCardHolderEt.setText(" " + mPayment.holder);
                mExpMonthEt.setText(" " + mPayment.exp_month);
                mExpYearEt.setText("" + mPayment.exp_year);
            }
        }
        else {
            ((TextView)v.findViewById(R.id.title_bar)).setText("Add Payment Method");
        }

        Button acceptButton = (Button) v.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource data = DataSource.get(getActivity().getApplicationContext());
                String url;
                if(mNewPayment) {
                    url = data.url + "maddpayment?";
                }
                else {
                    url = data.url + "mupdatepayment?";
                    url += "id=" + mPayment.id + "&";
                }
                url += "email=" + mUser.getEmail();
                url += "&password=" + mUser.getPassword();
                url += "&payment_type=" + URLEncoder.encode(mTypeEt.getText().toString());
                if(mTypeEt.getText().toString().equals("wire") || mTypeEt.getText().toString().equals("deposit")) {
                    url += "&bank_account=" + URLEncoder.encode(mBankAccountEt.getText().toString());
                }
                else if(mTypeEt.getText().toString().equals("card")) {
                    url += "&card_number=" + URLEncoder.encode(mCardNumberEt.getText().toString());
                    url += "&cvv=" + URLEncoder.encode(mCVVEt.getText().toString());
                    url += "&holder=" + URLEncoder.encode(mCardHolderEt.getText().toString());
                    url += "&exp_month=" + URLEncoder.encode(mExpMonthEt.getText().toString());
                    url += "&exp_year=" + URLEncoder.encode(mExpYearEt.getText().toString());
                }
                url.trim();

                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
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