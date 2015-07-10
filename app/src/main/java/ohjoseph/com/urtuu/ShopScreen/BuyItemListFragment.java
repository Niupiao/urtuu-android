package ohjoseph.com.urtuu.ShopScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.Data.Subcategory;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class BuyItemListFragment extends Fragment {

    public static final String EXTRA_SUBCATEGORY = "Subcategory items";
    public static final String EXTRA_NAME = "Subcategory name";
    public static final String EXTRA_CATEGORY = "Cateogry name";

    ArrayList<Item> mItems;
    ItemListAdapter mAdapter;
    RecyclerView mRecyclerView;
    GridLayoutManager glm;
    android.support.v7.app.ActionBar mToolbar;

    public static BuyItemListFragment newInstance(Subcategory s) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SUBCATEGORY, s.getItems());
        args.putString(EXTRA_NAME, s.getName());
        BuyItemListFragment frag = new BuyItemListFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize item list
        mItems = DataSource.get(getActivity()).getItems();
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

        return v;
    }

    // Holder class for RecyclerView Objects
    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        ImageView image;
        ImageView heart;

        public ItemHolder(View v) {
            super(v);
            nameTv = (TextView) v.findViewById(R.id.item_name);
            image = (ImageView) v.findViewById(R.id.item_picture);
            heart = (ImageView) v.findViewById(R.id.heart_icon);
        }
    }
    // Adapter for the RecyclerView
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
        public void onBindViewHolder(final ItemHolder holder, int position) {
            final Item i = items.get(position);
            holder.nameTv.setText(i.getName());
            holder.image.setImageResource(i.getPictureId());
            holder.heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Change the heart status
                    if (i.isHeart()) {
                        holder.heart.setImageResource(android.R.color.transparent);
                        i.setHeart(false);
                    } else {
                        holder.heart.setImageResource(R.drawable.heart_filled);
                        i.setHeart(true);
                    }
                }
            });
            holder.image.setOnTouchListener(new View.OnTouchListener() {
                // Listen for double taps
                private GestureDetector detector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        // Open item page on single tap
                        Intent intent = new Intent(getActivity(), ItemViewPagerActivity.class);
                        intent.putExtra(ItemFragment.EXTRA_NAME, i.getName());
                        startActivity(intent);
                        return true;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        // Set the liked button
                        if (i.isHeart()) {
                            Toast.makeText(getActivity(), "Unliked", Toast.LENGTH_SHORT).show();
                            holder.heart.setImageResource(android.R.color.transparent);
                            i.setHeart(false);
                        } else { // Clear the heart
                            Toast.makeText(getActivity(), "Hearted!", Toast.LENGTH_SHORT).show();
                            holder.heart.setImageResource(R.drawable.heart_filled);
                            i.setHeart(true);
                        }
                        return true;
                    }
                });

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    detector.onTouchEvent(event);
                    return true;
                }
            });

            // Set the liked button
            if (i.isHeart()) {
                holder.heart.setImageResource(R.drawable.heart_filled);
            } else {
                holder.heart.setImageResource(android.R.color.transparent);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}
