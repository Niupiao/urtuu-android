package ohjoseph.com.urtuu.Shared;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/7/15.
 */
public class Item {

    String mName;
    float mPrice;
    double mSellerRating;
    String mSeller;
    int mPaymentMethod;
    String mBrand;
    String mSize;
    ArrayList<String> mTags;

    public Item(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
