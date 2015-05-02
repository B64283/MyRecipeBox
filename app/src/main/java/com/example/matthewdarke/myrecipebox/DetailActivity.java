package com.example.matthewdarke.myrecipebox;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by matthewdarke on 4/6/15.
 */
public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // If no state bundle exists, this is the first launch.
        // Add your fragments just this one time.

        if (savedInstanceState == null){

            DetailFragment detFrag = new DetailFragment();

            getFragmentManager().beginTransaction().

                    add(R.id.detail_activity, detFrag).commit();



        }

//creates/Nav bar

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId()== android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
