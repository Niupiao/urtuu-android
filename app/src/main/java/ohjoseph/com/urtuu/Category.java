package ohjoseph.com.urtuu;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/6/15.
 */
public class Category {

    String mName;
    ArrayList<String> mSubCategories;
    int mPicture;

    public Category(String name, int picture, ArrayList<String> subs) {
        mName = name;
        mPicture = picture;
        mSubCategories = subs;
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

    public ArrayList<String> getSubCategories() {
        return mSubCategories;
    }

    public void setSubCategories(ArrayList<String> subCategories) {
        mSubCategories = subCategories;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
    }
}
