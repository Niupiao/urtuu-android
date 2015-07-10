package ohjoseph.com.urtuu.Data;

/**
 * Created by Joseph on 7/6/15.
 */
public class Category {

    String mName;
    int mPicture;

    public Category(String name, int picture) {
        mName = name;
        mPicture = picture;
    }

    public Category(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
    }
}
