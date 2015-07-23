package ohjoseph.com.urtuu.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.Data.User;
import ohjoseph.com.urtuu.Data.VolleySingleton;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class OrderHistoryFragment extends Fragment {

    OrderItemAdapter mAdapter;
    RecyclerView rv;
    SwipeRefreshLayout mSwiper;
    ArrayList<Item> mOrderItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderItems = DataSource.get(getActivity()).getSellerList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_history, parent, false);

        mSwiper = (SwipeRefreshLayout) v.findViewById(R.id.refreshswiper);
        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrderHistory();
            }
        });

        rv = (RecyclerView) v.findViewById(R.id.orders_rv);
        mAdapter = new OrderItemAdapter(mOrderItems);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(llm);

        // Fetch data from server
        getOrderHistory();

        return v;
    }

    public void getOrderHistory() {
        String url = DataSource.url + "mreceipts?email=";
        url += User.get().getEmail() + "&password=" + User.get().getPassword();

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                // Retreive data from server
                mOrderItems = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Item item = new Item(obj);
                        mOrderItems.add(item);
                    } catch (JSONException e) {
                        // Do Nothing
                    }
                }
                // Update recyclerview
                mAdapter = new OrderItemAdapter(mOrderItems);
                rv.setAdapter(mAdapter);
                mSwiper.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                return;
            }
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(request);
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
