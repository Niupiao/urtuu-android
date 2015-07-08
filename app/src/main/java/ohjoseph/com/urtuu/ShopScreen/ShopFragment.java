package ohjoseph.com.urtuu.ShopScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ohjoseph.com.urtuu.R;
import ohjoseph.com.urtuu.Shared.Category;
import ohjoseph.com.urtuu.Shared.CustomLayoutManager;
import ohjoseph.com.urtuu.Shared.DataSource;
import ohjoseph.com.urtuu.Shared.ExpandAnimation;
import ohjoseph.com.urtuu.Shared.Subcategory;

/**
 * Created by Joseph on 7/3/15.
 */
public class ShopFragment extends Fragment {

    public static final String ARG_PAGE = "SellFragment";

    private int mPage;
    ShopItemAdapter mShopItemAdapter;
    ArrayList<Category> mCategories;
    CustomLayoutManager clm;
    RecyclerView mRecyclerView;

    public static ShopFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ShopFragment frag = new ShopFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        mCategories = DataSource.get(getActivity()).getCategories();
        mShopItemAdapter = new ShopItemAdapter(mCategories);
        // TODO: Implement Search
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.shop_recycler_view);
        clm = new CustomLayoutManager(getActivity());
        clm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(clm);
        mRecyclerView.setAdapter(mShopItemAdapter);

        return view;
    }

/*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Scrolls the selected list item to the top
        if (position == 0) {
            l.smoothScrollToPositionFromTop(position, 40, 100);
        } else {
            l.smoothScrollToPositionFromTop(position, 0, 100);
        }
    }
    */

    public class CategoryHolder extends RecyclerView.ViewHolder {

        protected TextView titleText;
        protected ImageView pictureView;
        protected RelativeLayout card;
        LinearLayout subCatView;

        public CategoryHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.shop_item_name);
            pictureView = (ImageView) itemView.findViewById(R.id.shop_item_picture);
            card = (RelativeLayout) itemView;
            subCatView = (LinearLayout) itemView.findViewById(R.id.subcategory_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExpandAnimation expand = new ExpandAnimation(subCatView, 200);
                    v.startAnimation(expand);

                    int pos = getAdapterPosition();
                    mRecyclerView.smoothScrollToPosition(pos);
                }
            });
        }
    }

    public class ShopItemAdapter extends RecyclerView.Adapter<CategoryHolder> {

        ArrayList<Category> categories;
        ArrayList<Subcategory> subs;

        public ShopItemAdapter(ArrayList<Category> categories) {
            this.categories = categories;
        }

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.list_item_shop, viewGroup, false);

            return new CategoryHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int i) {
            Category c = categories.get(i);
            holder.titleText.setText(c.getName());
            subs = c.getSubCategories();
            // Calculates size of image to be displayed
            holder.pictureView.setImageResource(c.getPicture());

            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (int x = 0; x < subs.size(); x++) {
                View subcategory = inflater.inflate(R.layout.list_item_subcategory, holder.subCatView, false);
                subcategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),
                                ((TextView) v.findViewById(R.id.subcategory_item)).getText() + "clicked",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                TextView subName = (TextView) subcategory.findViewById(R.id.subcategory_item);
                subName.setText(subs.get(x).getName());
                holder.subCatView.addView(subcategory);
            }
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }
}
