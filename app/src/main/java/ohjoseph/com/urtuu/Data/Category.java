package ohjoseph.com.urtuu.Data;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/6/15.
 */
public class Category {

    String mName;
    ArrayList<Subcategory> mSubCategories;
    int mPicture;

    public Category(String name, int picture, ArrayList<Subcategory> subs) {
        mName = name;
        mPicture = picture;
        mSubCategories = subs;
    }

    public Category(String name) {
        mName = name;
    }

    public Subcategory getSubcategory(String name) {
        for (Subcategory s : mSubCategories) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<Subcategory> getSubCategories() {
        return mSubCategories;
    }

    public void setSubCategories(ArrayList<Subcategory> subCategories) {
        mSubCategories = subCategories;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
    }
}