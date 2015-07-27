package ohjoseph.com.urtuu.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/3/15.
 */
public class AccountFragment extends Fragment {

    public static final String ARG_PAGE = "AccountFragment";

    private int mPage;
    private LoginButton mFacebookLoginButton;
    private CallbackManager mCallbackManager;

    public static AccountFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AccountFragment frag = new AccountFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();

        LoginButton mFacebookLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
        mFacebookLoginButton.setReadPermissions("user_friends");
        // If using in a fragment
        mFacebookLoginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        mFacebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), loginResult.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Facebook Login Cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {

                Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
