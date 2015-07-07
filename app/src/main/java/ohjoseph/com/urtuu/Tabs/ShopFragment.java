package ohjoseph.com.urtuu.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Category;
import ohjoseph.com.urtuu.DataSource;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/3/15.
 */
public class ShopFragment extends Fragment {

    public static final String ARG_PAGE = "SellFragment";

    private int mPage;
    ShopItemAdapter mShopItemAdapter;
    ArrayList<Category> mCategories;
    LinearLayoutManager llm;
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
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
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
        // Expands subcategories
        LinearLayout subCats = (LinearLayout) v.findViewById(R.id.subcategory_view);
        ExpandAnimation expand = new ExpandAnimation(subCats, 100);
        v.startAnimation(expand);
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
                    int pos = getAdapterPosition();
                    Log.d("position", pos + "");
                    llm.scrollToPositionWithOffset(pos, 10);

                    // Show or hide subcategories
                    if (subCatView.getVisibility() == View.GONE) {
                        subCatView.setVisibility(View.VISIBLE);
                    } else {
                        subCatView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public class ShopItemAdapter extends RecyclerView.Adapter<CategoryHolder> {

        ArrayList<Category> categories;

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
            //holder.pictureView.setImageResource(c.getPicture());
            /* TODO: Scale pictures to size
            ArrayList<String> subs = c.getSubCategories();
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (int x=0; x<subs.size(); x++) {
                View stub = inflater.inflate(R.layout.list_item_subcategory, null);
                stub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),
                                ((TextView) v.findViewById(R.id.subcategory_item)).getText() + "clicked",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                TextView subName = (TextView) stub.findViewById(R.id.subcategory_item);
                subName.setText(subs.get(x));
                holder.subCatView.addView(stub, x);
            } */
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }
    }
}
