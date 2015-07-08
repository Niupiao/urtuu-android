package ohjoseph.com.urtuu.Data;

import android.content.Context;

import java.util.ArrayList;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/6/15.
 */
public class DataSource {

    public static DataSource sDataSource;
    private Context mContext;
    ArrayList<Category> mCategories;

    public static DataSource get(Context context) {
        if (sDataSource == null) {
            sDataSource = new DataSource(context);
        }

        return sDataSource;
    }

    private DataSource(Context context) {
        mContext = context;
        createMaps();
    }

    private void createMaps() {
        mCategories = new ArrayList<>();
        ArrayList<Subcategory> subs = new ArrayList<>();
        for (int i=1; i<6; i++) {
            Subcategory sc = new Subcategory("Category " + i);
            subs.add(sc);
        }

        mCategories.add(new Category("All", R.drawable.eyes, subs));
        mCategories.add(new Category("Baby", R.drawable.baby, subs));
        mCategories.add(new Category("Beauty", R.drawable.makeup, subs));
        mCategories.add(new Category("Recommended", R.drawable.origins, subs));
    }

    public ArrayList getCategories() {
        return mCategories;
    }

    public Category getCategory(String name) {
        for (Category c : mCategories) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }
}
