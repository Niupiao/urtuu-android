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

    public void setName(String name) {
        mName = name;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public void setSellerRating(double sellerRating) {
        mSellerRating = sellerRating;
    }

    public void setSeller(String seller) {
        mSeller = seller;
    }

    public void setPaymentMethod(int paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public void setTags(ArrayList<String> tags) {
        mTags = tags;
    }
}
