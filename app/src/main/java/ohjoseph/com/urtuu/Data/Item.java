package ohjoseph.com.urtuu.Data;

import java.util.ArrayList;

import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class Item {

    String mName;
    String mCategory;
    String mSubcategory;
    boolean mHeart;
    int mPictureId;
    float mPrice;
    double mSellerRating;
    String mSeller;
    int mPaymentMethod;
    String mBrand;
    String mSize;
    ArrayList<String> mTags;

    public Item(String name, String subName, String catName) {
        mName = name;
        mCategory = catName;
        mSubcategory = subName;
        mPictureId = R.drawable.small_s6;
        mHeart = false;
    }

    public boolean isHeart() {
        return mHeart;
    }

    public void setHeart(boolean heart) {
        mHeart = heart;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPictureId() {
        return mPictureId;
    }

    public void setPictureId(int pictureId) {
        mPictureId = pictureId;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public double getSellerRating() {
        return mSellerRating;
    }

    public void setSellerRating(double sellerRating) {
        mSellerRating = sellerRating;
    }

    public String getSeller() {
        return mSeller;
    }

    public void setSeller(String seller) {
        mSeller = seller;
    }

    public int getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public ArrayList<String> getTags() {
        return mTags;
    }

    public void setTags(ArrayList<String> tags) {
        mTags = tags;
    }
}
