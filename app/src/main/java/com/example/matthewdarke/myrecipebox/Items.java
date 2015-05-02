package com.example.matthewdarke.myrecipebox;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by matthewdarke on 4/6/15.
 */
public class Items implements Serializable {

    public static final long serialVersionUID = 7877333587982937458L;

    public static Items newInstance(String _name, String _quantity, String _price, String _pic) {

        Items items = new Items();
        items.setmName(_name);
        items.setmAddress(_quantity);
        items.setmNumber(_price);
        items.setmPicture(Drawable.createFromPath(_pic));
        return items;
    }

    private String mName;
    private String mQuantity;
    private String mPrice;
    private String mPic;

    public Items() {

        mPic ="";
        mName = "";
        mQuantity = "";
        mPrice = "";

    }

    private Items(String _name, String _quantity, String _price, String _pic) {

        mName = _name;
        mQuantity = _quantity;
        mPrice = _price;
        mPic = String.valueOf((_pic));


    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mQuantity;
    }

    public void setmAddress(String mAddress) {
        this.mQuantity = mAddress;
    }

    public String getmNumber() {
        return mPrice;
    }

    public void setmNumber(String mNumber) {
        this.mPrice = mNumber;
    }

    public Drawable getmPicture() {
        return Drawable.createFromPath((mPic));
    }

    public void setmPicture(Drawable mPic) {
        this.mPic = String.valueOf(mPic);
    }

    @Override
    public String toString() {
        return mName;

    }


}
