package com.example.matthewdarke.myrecipebox;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by matthewdarke on 4/6/15.
 */
public class BeefFragment extends ListFragment {
    //set up all category fragments like you would the main fragment




        public static final int REQUEST_CODE = 2;
        private OnFragmentMediaControllInteractionListener mListener;

        Button bnAdd;
        private View v;
    private int mPersonSelected = -1;
    private ActionMode mActionMode;
        //public static final int REQUEST_CODE = ;
        public BeefFragment() {

        }

        //use save instance state with bundles

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            v = inflater.inflate(R.layout.fragment_main, container, false);
            ListView contactList = (ListView) v.findViewById(android.R.id.list);
            contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    if(mActionMode != null) {
                        return false;


                    }
                    MainActivity.deleteIndex = position;
                    //mPersonSelected = position;
                    mActionMode = getActivity().startActionMode(mActionModeCallback);

                    return true;

                }



            });



            return v;

        }


    //CONTEXTUAL ACTION CALLBACK
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }


        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.peopleDelete:
                    int deleteIndex = MainActivity.deleteIndex;

                    //delete at index from contacts
                    MainActivity.itemsArrayBeef.remove(deleteIndex);

                    //tell adapter of dataset change
                    MainActivity.adapter4.notifyDataSetChanged();

                    //updatedatacache
                    ((MainActivity) getActivity()).saveBeefData();
                    //DetailFragment.updateCache();


                    //DetailFragment.updateCache();
                    mode.finish();
                    Toast.makeText(getActivity(), getString(R.string.delete), Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mActionMode = null;
        }
    };







    //restore your fragment state in onActivityCreated()
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            // provides your activity with a Bundle object that can be used to store


            super.onActivityCreated(savedInstanceState);

            // check if bundle is null. if not, use it to restore state
//Load cached Data from main activityhgjhgjgjhgjhgjgjhgjhg

            if (MainActivity.itemsArrayBeef.isEmpty()){

                ((MainActivity) getActivity()).loadSavedBeefData();
            }


        }


    //set up onListItemClick
    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        super.onListItemClick(l, view, position, id);


        Items items = (Items) l.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString("mName", items.getmName());
        bundle.putString("mQuantity", items.getmAddress());
        bundle.putString("mPrice", items.getmNumber());

        // requesting the start of a new Activity (DetailActivity) within The application.
        Intent pdIntent = new Intent(view.getContext(), DetailActivity.class);

        //passing data to detail using putExtra in the intent
        pdIntent.putExtra("itemsArray", ((MainActivity) getActivity()).itemsArrayBeef);
        pdIntent.putExtras(bundle);

        //Using startActivity Android will process the intent (pdIntent), and launch the requested component from the Intent.
        startActivity(pdIntent);


    }

    @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (OnFragmentMediaControllInteractionListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implementOnFragmentMediaControllInteractionListener");
            }


        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }


        public interface OnFragmentMediaControllInteractionListener {
            void onFragmentControllInteraction(View v);
        }
    }

