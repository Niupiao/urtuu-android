package ohjoseph.com.urtuu.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        Button edit = (Button) v.findViewById(R.id.edit_profile);

        return v;
    }
}