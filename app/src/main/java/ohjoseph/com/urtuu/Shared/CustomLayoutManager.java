package ohjoseph.com.urtuu.Shared;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by Joseph on 7/7/15.
 */
public class CustomLayoutManager extends LinearLayoutManager {

    private static final float MILLISECONDS_PER_INCH = 50f;
    private Context mContext;
    private LinearSmoothScroller mScroller;

    public CustomLayoutManager(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {
        super.smoothScrollToPosition(recyclerView, state, position);

        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(mContext) {

            //Controls the direction in which smoothScroll looks for your view
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return new PointF(0, 1);
            }

            //Returns the milliseconds it takes to scroll one pixel.
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };

        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }
}
