package com.example.matthewdarke.myrecipebox;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matthewdarke on 4/6/15.
 */
public class DetailFragment extends Fragment {

    public ArrayList<Items> mItemsArrayList = new ArrayList<>();
    public String mItm;


    public DetailFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v;
        v = inflater.inflate(R.layout.detail_fragment, container, false);


        TextView mItmName = (TextView) v.findViewById(R.id.textView_Name);
        TextView mItmQuant = (TextView) v.findViewById(R.id.textView_model);
        TextView mItmPrice = (TextView) v.findViewById(R.id.textView_rate);
        ImageView mItmPic = (ImageView) v.findViewById(R.id.imageViewDetails);

        Intent intent = getActivity().getIntent();


        mItemsArrayList = (ArrayList<Items>) intent.getSerializableExtra("itemsArray");


        //bundles data and getSerializableExtras above
        Bundle data = intent.getExtras();


        if (data != null) {
///gets key value & set text


            String firstName = data.getString("mName");
            mItm = data.get("mName").toString();

            String firstAddress = data.getString("mQuantity");
            String firstNumber = data.getString("mPrice");
            String firstPic = data.getString("mPic");

            mItmName.setText(firstName);
            mItmQuant.setText(firstAddress);
            mItmPrice.setText(firstNumber);
            mItmPic.setImageDrawable(Drawable.createFromPath(firstPic));
        }


        return v;
    }


}
