package ohjoseph.com.urtuu.Shared;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
