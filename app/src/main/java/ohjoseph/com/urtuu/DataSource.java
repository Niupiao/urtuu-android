package ohjoseph.com.urtuu;

import android.content.Context;

import java.util.ArrayList;

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
        ArrayList<String> subs = new ArrayList<>();
        for (int i=1; i<6; i++) {
            subs.add("Category " + i);
        }

        mCategories.add(new Category("All", R.drawable.all, null));
        mCategories.add(new Category("Baby", R.drawable.baby_products, subs));
        mCategories.add(new Category("Beauty", R.drawable.beauty_products, subs));
        mCategories.add(new Category("Recommended", R.drawable.all, null));
    }

    public ArrayList getCategories() {
        return mCategories;
    }
}
