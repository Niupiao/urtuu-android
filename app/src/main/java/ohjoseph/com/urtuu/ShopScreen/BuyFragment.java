package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Shared.MainActivity;

/**
 * Created by Joseph on 7/7/15.
 */
public class BuyFragment extends Fragment {

    Toolbar mToolbar;
    MainActivity mParent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Finds the toolbar and parent activity for further reference
        mParent = (MainActivity)getActivity();
        mToolbar = (Toolbar) mParent.findViewById(R.id.action_toolbar);
        // Allows back navigation to main list
        mParent.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_buy_item, container, false);

        return v;
    }
}
