package ohjoseph.com.urtuu.Shared;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/7/15.
 */
public class Subcategory {

    String mName;
    ArrayList<Item> mItems;

    public Subcategory(String name) {
        mName = name;
    }

    public ArrayList<Item> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
