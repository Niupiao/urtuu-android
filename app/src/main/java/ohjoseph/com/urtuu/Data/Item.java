package ohjoseph.com.urtuu.Data;

import org.json.JSONObject;

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
    ArrayList<Integer> mImages = new ArrayList<>(5);
    double mPrice;
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
        mPrice = 249.99;
        mHeart = false;
        mImages.add(R.drawable.s6_3);
        mImages.add(R.drawable.small_s6);
        mImages.add(R.drawable.s6_2);
    }

    public Item(String name) {
        mName = name;
        mCategory = "Category";
        mSubcategory = "Subcategory";
        mPrice = 249.99;
        mHeart = false;
        mImages.add(R.drawable.s6_3);
        mImages.add(R.drawable.small_s6);
        mImages.add(R.drawable.s6_2);
    }

    public Item(JSONObject obj) {
        new Item("Item");
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
        return mImages.get(0);
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
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

    public ArrayList<Integer> getImages() {
        return mImages;
    }

    public void setImages(ArrayList<Integer> images) {
        mImages = images;
    }
}
