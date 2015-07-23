package ohjoseph.com.urtuu.Sell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/15/15.
 */
public class SellFragment extends Fragment {

    ArrayList<Item> mSellerList;
    SwipeRefreshLayout mSwiper;
    SellItemAdapter mAdapter;
    DataSource mDataSource;
    RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataSource = DataSource.get(getActivity());
        mSellerList = mDataSource.getSellerList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sell, parent, false);

        mSwiper = (SwipeRefreshLayout) v.findViewById(R.id.sell_swipe);
        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the list data
                mAdapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwiper.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        mSwiper.setColorSchemeColors(R.color.colorPrimary, R.color.light_blue, R.color.colorPrimaryDark);

        rv = (RecyclerView) v.findViewById(R.id.sell_list);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new SellItemAdapter(mSellerList);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(glm);

        // Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewItemActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    public class SellItemHolder extends RecyclerView.ViewHolder {
        ImageView picture;

        public SellItemHolder(View v) {
            super(v);
            picture = (ImageView) v.findViewById(R.id.item_picture);
        }
    }

    public class SellItemAdapter extends RecyclerView.Adapter<SellItemHolder> {
        ArrayList<Item> items;

        public SellItemAdapter(ArrayList<Item> items) {
            this.items = items;
        }

        @Override
        public SellItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View holder = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_holder, parent, false);

            return new SellItemHolder(holder);
        }

        @Override
        public void onBindViewHolder(SellItemHolder holder, int position) {
            Item item = items.get(position);

            // Set item picture
            holder.picture.setImageResource(item.getPictureId());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
