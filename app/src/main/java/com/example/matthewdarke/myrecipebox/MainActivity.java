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
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Serializable, AppetizerFragment.OnFragmentMediaControllInteractionListener, SaladFragment.OnFragmentMediaControllInteractionListener, MexicanFragment.OnFragmentMediaControllInteractionListener, BeefFragment.OnFragmentMediaControllInteractionListener, ChickenFragment.OnFragmentMediaControllInteractionListener, ShoppingFragment.OnFragmentMediaControllInteractionListener       {



    Button bnAdd;
    private static final long serialVersionUID = 2736847634070552888L;

    //set up arrayList and array adapter with model data
    public static ArrayList<Items> itemsArray = new ArrayList<>();

    public static ArrayAdapter<Items> adapter;


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

        fragment1 = (AppetizerFragment)android.app.Fragment.instantiate(this, AppetizerFragment.class.getName(), null);
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


        if (fragment1 != null) {

            super.onActivityResult(requestCode, resultCode, data);

            // Indicate state of results
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {


                ArrayList<Items> first = (ArrayList<Items>) data.getSerializableExtra("conDat");

                if (itemsArray != null) {

                    itemsArray.addAll(first);

                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

                    fragment1.setListAdapter(adapter);


                    saveAppatizerData();
                }


                if (fragment4 != null) {




                    fragment4.setListAdapter(adapter);
                    saveBeefData();
                }


                if (fragment3 != null) {




                            fragment3.setListAdapter(adapter);
                            saveChickenData();
                        }




                if (fragment5 != null) {




                            fragment5.setListAdapter(adapter);
                            saveMexicanData();
                        }



                if (fragment2 != null) {




                            fragment2.setListAdapter(adapter);
                            saveSaladData();
                        }




                if (fragment6 != null) {





                            fragment6.setListAdapter(adapter);
                            saveShoppingData();
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
            for (Items iItemsArray : itemsArray) {

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
            for (Items iItemsArray : itemsArray) {

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
            for (Items iItemsArray : itemsArray) {

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
            for (Items iItemsArray : itemsArray) {

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
            for (Items iItemsArray : itemsArray) {

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
            for (Items iItemsArray : itemsArray) {

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

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            mainFragment.setListAdapter(adapter);




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

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            saladFragment.setListAdapter(adapter);




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

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            saladFragment.setListAdapter(adapter);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    //reads saved data
    public void loadSavedChickenData() {

        ChickenFragment saladFragment = (ChickenFragment)
                getFragmentManager().findFragmentById(R.id.container);



        try {

            FileInputStream inputStream = openFileInput("ChickenDat");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            //needs while loop

            while (inputStream.available() != 0) {

                //cast data
                mItmData = (Items) objectInputStream.readObject();

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            saladFragment.setListAdapter(adapter);




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

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            mexFragment.setListAdapter(adapter);




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

                itemsArray.add(mItmData);
                //close input stream
            }

            //close input stream

            objectInputStream.close();
//add objects to array adapter
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsArray);

            shopFragment.setListAdapter(adapter);




        } catch (Exception e) {

            e.printStackTrace();
        }
    }




    @Override
    public void onFragmentControllInteraction(View v) {

        if (v.getId() == R.id.bnAdd) {

            Intent addTo = new Intent(this, AddActivity.class);
            startActivityForResult(addTo, REQUEST_CODE);


        }
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
