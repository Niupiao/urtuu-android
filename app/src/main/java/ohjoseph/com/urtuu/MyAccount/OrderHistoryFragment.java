package ohjoseph.com.urtuu.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class OrderHistoryFragment extends Fragment {

    ArrayList<Item> mOrderItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderItems = DataSource.get(getActivity()).getSellerList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_history, parent, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.orders_rv);
        OrderItemAdapter adapter = new OrderItemAdapter(mOrderItems);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);

        return v;
    }

    public class OrderHolder extends RecyclerView.ViewHolder {

        public OrderHolder(View v) {
            super(v);

        }
    }

    public class OrderItemAdapter extends RecyclerView.Adapter<OrderHolder> {

        ArrayList<Item> mItems;

        public OrderItemAdapter(ArrayList<Item> items) {
            mItems = items;
        }

        @Override
        public OrderHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_holder, viewGroup, false);

            return new OrderHolder(v);
        }

        @Override
        public void onBindViewHolder(OrderHolder holder, int position) {
            Item item = mItems.get(position);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}
