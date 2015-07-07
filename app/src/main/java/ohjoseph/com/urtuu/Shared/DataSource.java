package ohjoseph.com.urtuu.Shared;

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

        mCategories.add(new Category("All", R.drawable.all, subs));
        mCategories.add(new Category("Baby", R.drawable.baby_products, subs));
        mCategories.add(new Category("Beauty", R.drawable.beauty_products, subs));
        mCategories.add(new Category("Recommended", R.drawable.all, subs));
    }

    public ArrayList getCategories() {
        return mCategories;
    }
}
