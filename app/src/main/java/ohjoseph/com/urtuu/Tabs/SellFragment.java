package ohjoseph.com.urtuu.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/3/15.
 */
public class SellFragment extends Fragment {

    public static final String ARG_PAGE = "SellFragment";

    private int mPage;

    public static SellFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SellFragment frag = new SellFragment();
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
        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        return view;
    }
}
