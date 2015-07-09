package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/9/15.
 */
public class ItemFragment extends Fragment {

    public static final String EXTRA_NAME = "Item Name";

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_buy_item, container, false);


        return v;
    }
}
