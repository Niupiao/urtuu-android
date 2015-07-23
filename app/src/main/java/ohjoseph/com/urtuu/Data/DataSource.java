package ohjoseph.com.urtuu.Data;

import android.content.Context;

import java.util.ArrayList;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/6/15.
 */
public class DataSource {

    public static DataSource sDataSource;
    public static final String url = "https://niupiaomarket.herokuapp.com/";
    private Context mContext;
    ArrayList<Category> mCategories;
    ArrayList<Item> mItems;
    ArrayList<Item> mCart;
    ArrayList<Item> mSellerList;
    ArrayList<Item> mOrderItems;

    public static DataSource get(Context context) {
        if (sDataSource == null) {
            sDataSource = new DataSource(context);
        }

        return sDataSource;
    }

    private DataSource(Context context) {
        mContext = context;
        createItems();
    }

    private void createItems() {
        mItems = new ArrayList<>(15);
        mCart = new ArrayList<>(1);
        mCategories = new ArrayList<>(4);
        mSellerList = new ArrayList<>();
        mOrderItems = new ArrayList<>();

        // Add temp data
        for (int i = 0; i < 15; i++) {
            Item item = new Item("Samsung Galaxy S6 Edge", "All", "All");
            mItems.add(item);
        }

        for (int i = 0; i < 2; i++) {
            Item item = new Item("Samsung Galaxy S6 Edge", "All", "All");
            mCart.add(item);
            mSellerList.add(item);
        }

        // Add temp categories
        mCategories.add(new Category("All", R.drawable.eyes));
        mCategories.add(new Category("Beauty", R.drawable.makeup));
        mCategories.add(new Category("Baby", R.drawable.baby));
        mCategories.add(new Category("Recommended", R.drawable.origins));
    }

    public ArrayList getCategories() {
        return mCategories;
    }

    public ArrayList<Item> getItems() {
        return mItems;
    }

    public ArrayList<Item> getCart() {
        return mCart;
    }

    public void addToCart(Item i) {
        mCart.add(i);
    }

    public ArrayList<Item> getOrderItems() {
        return mOrderItems;
    }

    public void setOrderItems(ArrayList<Item> orderItems) {
        mOrderItems = orderItems;
    }

    public Item getItem(String name) {
        for (Item c : mItems) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public Item getOrder(String name) {
        for (Item c : mOrderItems) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public ArrayList<Item> getSellerList() {
        return mSellerList;
    }

    public void setSellerList(ArrayList<Item> sellerList) {
        mSellerList = sellerList;
    }

    public void addItemToSell(Item item) {
        mSellerList.add(item);
    }
}
