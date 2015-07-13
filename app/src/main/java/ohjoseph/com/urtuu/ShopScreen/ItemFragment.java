package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/9/15.
 */
public class ItemFragment extends Fragment {

    public static final String EXTRA_NAME = "Item Name";

    Item mItem;
    Toolbar mToolbar;

    public static ItemFragment newInstance(Item i) {
        ItemFragment frag = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NAME, i.getName());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get arguments and get item
        Bundle args = getArguments();
        mItem = DataSource.get(getActivity()).getItem(args.getString(EXTRA_NAME));

        // Set the ActionBar name to the item
        mToolbar = (Toolbar) getActivity().findViewById(R.id.action_toolbar);
        TextView titleView = (TextView) mToolbar.findViewById(R.id.title);
        titleView.setText(mItem.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_item, container, false);


        return v;
    }
}
