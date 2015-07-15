package ohjoseph.com.urtuu.Shop;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class CartFragment extends DialogFragment {

    ArrayList<Item> mCart;
    int subtotal;
    TextView subtotalView;
    TextView numItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize items
        mCart = DataSource.get(getActivity()).getCart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Remove the title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize the List View
        ListView lv = (ListView) v.findViewById(R.id.cart_items_list);
        ItemAdapter adapter = new ItemAdapter(mCart);
        lv.setAdapter(adapter);

        // Cancel button
        ImageView cancelButton = (ImageView) v.findViewById(R.id.x_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                getDialog().dismiss();
            }
        });

        // Set Paypal buttons
        RelativeLayout paypalButton = (RelativeLayout) v.findViewById(R.id.paypal_button);
        paypalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open paypal
                getDialog().dismiss();
            }
        });

        // Set Normal Checkout Button
        TextView checkoutButton = (TextView) v.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open payments menu
                getDialog().dismiss();
            }
        });

        // Set subtotal
        subtotalView = (TextView) v.findViewById(R.id.items_price);
        for (Item i : mCart) {
            subtotal += i.getPrice();
        }
        subtotalView.setText("$" + subtotal);

        // Set total number of items
        numItems = (TextView) v.findViewById(R.id.number_items);
        numItems.setText(mCart.size() + " Item(s)");

        return v;
    }

    private class ItemAdapter extends ArrayAdapter<Item> {

        public ItemAdapter(ArrayList<Item> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_cart, parent, false);
            }

            Item i = mCart.get(position);

            // Set the list item details with current Item information
            TextView name = (TextView) convertView.findViewById(R.id.item_name);
            name.setText(i.getName());
            TextView price = (TextView) convertView.findViewById(R.id.price);
            price.setText(i.getPrice() + " USD");

            return convertView;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Make Dialog fill parent width and height
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
