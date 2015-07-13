package ohjoseph.com.urtuu.Data;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/7/15.
 */
public class Subcategory {

    String mName;
    String mCategory;
    ArrayList<Item> mItems;

    public Subcategory(String name, String catName) {
        mName = name;
        mCategory = catName;
        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item("Item " + i, mName, mCategory);
            mItems.add(item);
        }
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
