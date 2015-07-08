package ohjoseph.com.urtuu.ShopScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.Data.Subcategory;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class BuyItemListFragment extends Fragment {

    public static final String EXTRA_SUBCATEGORY = "Subcategory name";
    public static final String EXTRA_CATEGORY = "Category name";

    ArrayList<Item> mItems;
    ItemListAdapter mAdapter;
    RecyclerView mRecyclerView;
    GridLayoutManager glm;
    android.support.v7.app.ActionBar mToolbar;

    public static BuyItemListFragment newInstance(Subcategory s) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SUBCATEGORY, s.getItems());

        BuyItemListFragment frag = new BuyItemListFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mItems = (ArrayList) args.get(EXTRA_SUBCATEGORY);
        } else {
            mItems = new ArrayList<>();
            Toast.makeText(getActivity(), "Empty Subcategory list", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_buy_item_list, container, false);

        mAdapter = new ItemListAdapter(mItems);
        glm = new GridLayoutManager(getActivity(), 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.item_list_rv);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setHasFixedSize(true);

        // Replace navbar with buy button

        return v;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        TextView nameTv;

        public ItemHolder(View v) {
            super(v);
            nameTv = (TextView) v.findViewById(R.id.item_name);
        }
    }

    public class ItemListAdapter extends RecyclerView.Adapter<ItemHolder> {

        ArrayList<Item> items;

        public ItemListAdapter(ArrayList<Item> items) {
            this.items = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View holder = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_holder, viewGroup, false);

            return new ItemHolder(holder);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item i = items.get(position);
            holder.nameTv.setText(i.getName());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}
