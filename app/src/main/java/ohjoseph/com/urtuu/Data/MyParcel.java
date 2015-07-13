package ohjoseph.com.urtuu.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joseph on 7/8/15.
 */
public class MyParcel implements Parcelable {

    private Subcategory mData;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeValue(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<MyParcel> CREATOR = new Parcelable.Creator<MyParcel>() {
        public MyParcel createFromParcel(Parcel in) {
            return new MyParcel(in);
        }

        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private MyParcel(Parcel in) {
        //mData = in.readValue();
    }
}

