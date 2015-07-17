package ohjoseph.com.urtuu.Sell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class NewItemFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_item, parent, false);

        ImageButton pic1 = (ImageButton) v.findViewById(R.id.picture1);
        ImageButton pic2 = (ImageButton) v.findViewById(R.id.picture2);
        ImageButton pic3 = (ImageButton) v.findViewById(R.id.picture3);
        ImageButton pic4 = (ImageButton) v.findViewById(R.id.picture4);

        EditText itemName = (EditText) v.findViewById(R.id.item_name);
        EditText itemDesc = (EditText) v.findViewById(R.id.item_desc);

        RelativeLayout category = (RelativeLayout) v.findViewById(R.id.category_button);
        RelativeLayout payment = (RelativeLayout) v.findViewById(R.id.payment_button);
        RelativeLayout brand = (RelativeLayout) v.findViewById(R.id.brand_button);
        RelativeLayout condition = (RelativeLayout) v.findViewById(R.id.condition_button);

        TextView categoryName = (TextView) v.findViewById(R.id.category_name);
        TextView paymentMethod = (TextView) v.findViewById(R.id.payment_method);
        TextView brandName = (TextView) v.findViewById(R.id.brand_name);
        TextView conditionName = (TextView) v.findViewById(R.id.condition);

        RelativeLayout fee = (RelativeLayout) v.findViewById(R.id.fee_button);
        RelativeLayout shipsFrom = (RelativeLayout) v.findViewById(R.id.ships_from_button);
        RelativeLayout shipsWithin = (RelativeLayout) v.findViewById(R.id.ships_within_button);

        EditText feeTV = (EditText) v.findViewById(R.id.shipping_fee);
        TextView shipFromTV = (TextView) v.findViewById(R.id.ships_from);
        TextView shipWithinTV = (TextView) v.findViewById(R.id.ships_within);

        TextView listButton = (TextView) v.findViewById(R.id.list_button);

        return v;
    }
}
