package com.example.matthewdarke.myrecipebox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Serializable, AppetizerFragment.OnFragmentMediaControllInteractionListener, SaladFragment.OnFragmentMediaControllInteractionListener, MexicanFragment.OnFragmentMediaControllInteractionListener, BeefFragment.OnFragmentMediaControllInteractionListener, ChickenFragment.OnFragmentMediaControllInteractionListener, ShoppingFragment.OnFragmentMediaControllInteractionListener {


    Button bnAdd;
    private static final long serialVersionUID = 2736847634070552888L;

    //set up arrayList and array adapter with model data
    public static ArrayList<Items> itemsArrayAppatizer = new ArrayList<>();
    public static ArrayList<Items> itemsArrayChicken = new ArrayList<>();
    public static ArrayList<Items> itemsArrayBeef = new ArrayList<>();
    public static ArrayList<Items> itemsArrayMexican = new ArrayList<>();
    public static ArrayList<Items> itemsArrayShopping = new ArrayList<>();
    public static ArrayList<Items> itemsArraySalad = new ArrayList<>();

    public static ArrayAdapter<Items> adapter1;
    public static ArrayAdapter<Items> adapter2;
    public static ArrayAdapter<Items> adapter3;
    public static ArrayAdapter<Items> adapter4;
    public static ArrayAdapter<Items> adapter5;
    public static ArrayAdapter<Items> adapter6;




    public static int deleteIndex;

    //requestCode - the identifier from the launching intent
    public static final int REQUEST_CODE = 1;

    //model data member var
    public static Items mItmData;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */


    private NavigationDrawerFragment mNavigationDrawerFragment;
    public AppetizerFragment fragment1;
    public SaladFragment fragment2;
    public ChickenFragment fragment3;
    public BeefFragment fragment4;
    public MexicanFragment fragment5;
    public ShoppingFragment fragment6;

    public android.app.FragmentManager fragmentManager;
    public FragmentTransaction fragTrans;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,

                (DrawerLayout) findViewById(R.id.drawer_layout));

        fragment1 = (AppetizerFragment) android.app.Fragment.instantiate(this, AppetizerFragment.class.getName(), null);
        fragment2 = (SaladFragment) android.app.Fragment.instantiate(this, SaladFragment.class.getName(), null);
        fragment3 = (ChickenFragment) android.app.Fragment.instantiate(this, ChickenFragment.class.getName(), null);
        fragment4 = (BeefFragment) android.app.Fragment.instantiate(this, BeefFragment.class.getName(), null);
        fragment5 = (MexicanFragment) android.app.Fragment.instantiate(this, MexicanFragment.class.getName(), null);
        fragment6 = (ShoppingFragment) android.app.Fragment.instantiate(this, ShoppingFragment.class.getName(), null);


    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment1);
                fragTrans.commit();

                mTitle = getString(R.string.title_section1);


                break;
            case 2:
                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment2);
                fragTrans.commit();


                mTitle = getString(R.string.title_section2);
                break;
            case 3:

                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment3);
                fragTrans.commit();

                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment4);
                fragTrans.commit();


                mTitle = getString(R.string.title_section4);
                break;
            case 5:

                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment5);
                fragTrans.commit();


                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                fragmentManager = getFragmentManager();
                fragTrans = fragmentManager.beginTransaction();
                fragTrans.replace(R.id.container, fragment6);
                fragTrans.commit();

                mTitle = getString(R.string.title_section6);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (fragment1.isVisible()) {

            super.onActivityResult(requestCode, resultCode, data);

            // Indicate state of results
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                ArrayList<Items> first = (ArrayList<Items>) data.getSerializableExtra("conDat");

                if (itemsArrayAppatizer != null) {
                    saveAppatizerData();
                    itemsArrayAppatizer.addAll(first);

                    adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayAppatizer);

                    fragment1.setListAdapter(adapter1);

                } else {
                    saveAppatizerData();
                    itemsArrayAppatizer = first;
                }


            }
        } else if  (fragment2.isVisible()) {

            super.onActivityResult(requestCode, resultCode, data);

            // Indicate state of results
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                ArrayList<Items> second = (ArrayList<Items>) data.getSerializableExtra("conDat");

                if (itemsArraySalad != null) {
                    saveSaladData();
                    itemsArraySalad.addAll(second);

                    adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArraySalad);

                    fragment2.setListAdapter(adapter2);

                } else {
                    saveSaladData();
                    itemsArraySalad = second;
                }


            }
        } else  if (fragment3.isVisible()) {

            super.onActivityResult(requestCode, resultCode, data);

            // Indicate state of results
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                ArrayList<Items> third = (ArrayList<Items>) data.getSerializableExtra("conDat");

                if (itemsArrayChicken != null) {
                    saveChickenData();
                    itemsArrayChicken.addAll(third);

                    adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayChicken);

                    fragment3.setListAdapter(adapter3);

                } else {
                    saveChickenData();
                    itemsArrayChicken = third;
                }
            }


            }else  if (fragment4.isVisible()) {

                super.onActivityResult(requestCode, resultCode, data);

                // Indicate state of results
                if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                    ArrayList<Items> fourth = (ArrayList<Items>) data.getSerializableExtra("conDat");

                    if (itemsArrayBeef != null) {
                        saveBeefData();
                        itemsArrayBeef.addAll(fourth);

                        adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayBeef);

                        fragment4.setListAdapter(adapter4);

                    } else {
                        saveBeefData();
                        itemsArrayBeef = fourth;
                    }

                }

                }else  if (fragment5.isVisible()) {

                    super.onActivityResult(requestCode, resultCode, data);

                    // Indicate state of results
                    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                        ArrayList<Items> fifth = (ArrayList<Items>) data.getSerializableExtra("conDat");

                        if (itemsArrayMexican != null) {
                            saveMexicanData();
                            itemsArrayMexican.addAll(fifth);

                            adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayMexican);

                            fragment5.setListAdapter(adapter5);

                        } else {
                            saveMexicanData();
                            itemsArrayMexican = fifth;
                        }
                    }

                    }else if (fragment6.isVisible()) {

                        super.onActivityResult(requestCode, resultCode, data);

                        // Indicate state of results
                        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                            ArrayList<Items> sixth = (ArrayList<Items>) data.getSerializableExtra("conDat");

                            if (itemsArrayShopping != null) {
                                saveShoppingData();
                                itemsArrayShopping.addAll(sixth);

                                adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayShopping);

                                fragment6.setListAdapter(adapter6);

                            } else {
                                saveShoppingData();
                                itemsArrayShopping = sixth;
                            }


                        }
                    }
                }





    //saves data using File output stream stores to a file
    public void saveAppatizerData() {


        try {
            FileOutputStream outputStream = openFileOutput("AppatizerDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArrayAppatizer) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //saves data using File output stream stores to a file
    public void saveSaladData() {


        try {
            FileOutputStream outputStream = openFileOutput("SaladDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArraySalad) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //saves data using File output stream stores to a file
    public void saveBeefData() {


        try {
            FileOutputStream outputStream = openFileOutput("BeefDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArrayBeef) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //saves data using File output stream stores to a file
    public void saveChickenData() {


        try {
            FileOutputStream outputStream = openFileOutput("ChickenDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArrayChicken) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //saves data using File output stream stores to a file
    public void saveShoppingData() {


        try {
            FileOutputStream outputStream = openFileOutput("ShoppingDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArrayShopping) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //saves data using File output stream stores to a file
    public void saveMexicanData() {


        try {
            FileOutputStream outputStream = openFileOutput("MexicanDat", Context.MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //add data items to array
            for (Items iItemsArray : itemsArrayMexican) {

                mItmData = iItemsArray;

                //write objects
                objectOutputStream.writeObject(mItmData);
            }

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    //reads saved data
    public void loadSavedData() {

        AppetizerFragment mainFragment = (AppetizerFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("AppatizerDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArrayAppatizer.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayAppatizer);

            mainFragment.setListAdapter(adapter1);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedSaladData() {

        SaladFragment saladFragment = (SaladFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("SaladDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArraySalad.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArraySalad);

            saladFragment.setListAdapter(adapter2);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedBeefData() {

        BeefFragment saladFragment = (BeefFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("BeefDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArrayBeef.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayBeef);

            saladFragment.setListAdapter(adapter4);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedChickenData() {

        ChickenFragment chickFragment = (ChickenFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("ChickenDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArrayChicken.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayChicken);

            chickFragment.setListAdapter(adapter3);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedMexicanData() {

        MexicanFragment mexFragment = (MexicanFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("MexicanDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArrayMexican.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayMexican);

            mexFragment.setListAdapter(adapter5);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedShoppingData() {

        ShoppingFragment shopFragment = (ShoppingFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("ShoppingDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArrayShopping.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArrayShopping);

            shopFragment.setListAdapter(adapter6);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }




    @Override
    public void onFragmentControllInteraction(View v) {



            Intent addTo = new Intent(this, AddActivity.class);
            startActivityForResult(addTo, REQUEST_CODE);


        }





    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.


            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent addTo = new Intent(this,AddActivity.class);
            startActivityForResult(addTo, REQUEST_CODE);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }



        /**
         * A placeholder fragment containing a simple view.
         */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
