package com.altashcools.altaschools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.QuickContactBadge;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by raziehakbari on 25/12/2017.
 */

public  class pagerFragment extends Fragment {
    int curView;
    private DatabaseReference mPostReference;
    private EditText ageNum;
    private int ageCurrent;
    private EditText priceNum;
    private int priceCurrent;
    public View view;
    public Spinner spinner;
    public Spinner spinnerLevel;

    public pagerFragment() {
    }

    @SuppressLint("ValidFragment")
    public pagerFragment(int view) {
        curView = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(curView, container, false);

        switch (curView) {

            case R.layout.main_fragment:
            {
                mainView();
            }
            case R.layout.main_search: {
                mainSearch();
            }
            case R.layout.main_login: {
                mainLogin();}
        }
        return view;
    }
    private View mainLogin(){


        TabLayout tabLayout = view.findViewById(R.id.h_tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.cardview_light_background));

        TabLayout.Tab firstTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        firstTab.setText("Teacher");

        TabLayout.Tab secondTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        secondTab.setText("Student");
        tabLayout.addTab(firstTab);
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        thirdTab.setText("Parent");
        tabLayout.addTab(thirdTab);

        TabLayout.Tab forthTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        forthTab.setText("Profile");
        tabLayout.addTab(forthTab);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0: {
                        replaceFragment(new FragmentTab());
                        break;
                    }

                    case 1: {
                        replaceFragment(new FragmentTab());
                        break;

                    }

                    case 2: {
                        replaceFragment(new FragmentTab());
                        break;

                    }

                    case 3: {
                        replaceFragment(new FragmentProfile());
                        break;


                    }

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        return view;


    }

    private View mainSearch(){


        /*********************
         *sets the spinner view
         ********************/
        spinner =  view.findViewById(R.id.courseSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.course_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(1,adapter, R.layout.row_spinner,getActivity()));


        spinnerLevel = view.findViewById(R.id.levelSpinner);
        ArrayAdapter<CharSequence> adapterLevel = ArrayAdapter.createFromResource(getActivity(), R.array.level_array, android.R.layout.simple_spinner_item);
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(new NothingSelectedSpinnerAdapter(2,adapterLevel, R.layout.row_spinner,getActivity()));


        Spinner spinnerGender = view.findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getActivity(), R.array.gender_array, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(new NothingSelectedSpinnerAdapter(3,adapterGender, R.layout.row_spinner,getActivity()));

        /*********************
         *sets the Seek bar
         ********************/

        ageNum = view.findViewById(R.id.ageNum);

        SeekBar ageSeek = view.findViewById(R.id.ageSeekBar);
        ageSeek.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        ageSeek.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        ageCurrent = ageSeek.getProgress();

        ageSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                ageCurrent = progress;
                ageNum.setText(Integer.toString(ageCurrent), TextView.BufferType.EDITABLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        priceNum = view.findViewById(R.id.priceNum);

        SeekBar priceSeek = view.findViewById(R.id.priceSeekBar);
        priceSeek.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        priceSeek.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        priceCurrent = priceSeek.getProgress();

        priceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                priceCurrent = progress;
                priceNum.setText(Integer.toString(priceCurrent), TextView.BufferType.EDITABLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*****************
         *sets tab layout
         ****************/
        TabLayout tabLayout = view.findViewById(R.id.search_tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.cardview_light_background));

        TabLayout.Tab firstTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        firstTab.setText("List");

        TabLayout.Tab secondTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        secondTab.setText("Map");

        tabLayout.addTab(firstTab);
        tabLayout.addTab(secondTab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0: {
                        replaceSearchFragment(new FragmentSearchList());
                        break;
                    }
                    case 1: {
                        replaceSearchFragment(new FragmentSearchMap());
                        break;
                    }

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        /********************
         * Handles click of fab1 till fab4 on Main Activity
         *********************/

        MainActivity.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout adLayout = view.findViewById(R.id.advanceSearch);
                if(adLayout.getVisibility() == View.VISIBLE)
                {
                    adLayout.setVisibility(View.GONE);

                }else
                {
                    adLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        MainActivity.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int course = spinner.getSelectedItemPosition();
                int level = spinnerLevel.getSelectedItemPosition();
                //TODO get also advanced values



            }
        });

        return view;

    }

    private View mainView(){
        final FrameLayout frameLayout = view.findViewById(R.id.frameId);

        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        /***********************************************************
         * Connectis to fireabse to get information of the main page
         ***********************************************************/
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Advertise");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Post> dataToShow = new ArrayList<>();
                for (DataSnapshot dataArray : dataSnapshot.getChildren()) {

                    Post val = new Post();
                    Post data = dataArray.getValue(Post.class);
                    val.userId = data.userId;
                    dataToShow.add(val);
                }

                MyAdapter mAdapter = new MyAdapter(dataToShow);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mPostReference.addValueEventListener(postListener);


        return view;
    }

    public void replaceFragment(Fragment fragment) {
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLogin, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

    }

    public void replaceSearchFragment(Fragment fragment) {
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frameSearch, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

    }

    private class NothingSelectedSpinnerAdapter implements SpinnerAdapter, ListAdapter {

        protected static final int EXTRA = 1;
        protected SpinnerAdapter adapter;
        protected Context context;
        protected int nothingSelectedLayout;
        protected int nothingSelectedDropdownLayout;
        protected LayoutInflater layoutInflater;
        protected int type;

        /**
         * Use this constructor to have NO 'Select One...' item, instead use
         * the standard prompt or nothing at all.
         * @param spinnerAdapter wrapped Adapter.
         * @param nothingSelectedLayout layout for nothing selected, perhaps
         * you want text grayed out like a prompt...
         * @param context
         */
        public NothingSelectedSpinnerAdapter(int type,
                SpinnerAdapter spinnerAdapter,
                int nothingSelectedLayout, Context context) {

            this(type,spinnerAdapter, nothingSelectedLayout, -1, context);
        }

        /**
         * Use this constructor to Define your 'Select One...' layout as the first
         * row in the returned choices.
         * If you do this, you probably don't want a prompt on your spinner or it'll
         * have two 'Select' rows.
         * @param spinnerAdapter wrapped Adapter. Should probably return false for isEnabled(0)
         * @param nothingSelectedLayout layout for nothing selected, perhaps you want
         * text grayed out like a prompt...
         * @param nothingSelectedDropdownLayout layout for your 'Select an Item...' in
         * the dropdown.
         * @param context
         */
        public NothingSelectedSpinnerAdapter(int type,SpinnerAdapter spinnerAdapter,
                                             int nothingSelectedLayout, int nothingSelectedDropdownLayout, Context context) {
            this.adapter = spinnerAdapter;
            this.context = context;
            this.nothingSelectedLayout = nothingSelectedLayout;
            this.nothingSelectedDropdownLayout = nothingSelectedDropdownLayout;
            layoutInflater = LayoutInflater.from(context);
            this.type =type;
        }

        @Override
        public final View getView(int position, View convertView, ViewGroup parent) {
            // This provides the View for the Selected Item in the Spinner, not
            // the dropdown (unless dropdownView is not set).
            if (position == 0) {
                return getNothingSelectedView(parent);
            }
            return adapter.getView(position - EXTRA, null, parent); // Could re-use
            // the convertView if possible.
        }

        /**
         * View to show in Spinner with Nothing Selected
         * Override this to do something dynamic... e.g. "37 Options Found"
         * @param parent
         * @return
         */
        protected View getNothingSelectedView(ViewGroup parent) {

            View view =  layoutInflater.inflate(nothingSelectedLayout, parent, false);
            TextView hint = view.findViewById(R.id.text1);
            if(type==1)
            {
                hint.setText("Select Course");

            }else if(type==2)
            {
                hint.setText("Select Level");

            }else if(type==3)
            {
                hint.setText("Select Gender");

            }

            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            // Android BUG! http://code.google.com/p/android/issues/detail?id=17128 -
            // Spinner does not support multiple view types
            if (position == 0) {
                return nothingSelectedDropdownLayout == -1 ?
                        new View(context) :
                        getNothingSelectedDropdownView(parent);
            }

            // Could re-use the convertView if possible, use setTag...
            return adapter.getDropDownView(position - EXTRA, null, parent);
        }

        /**
         * Override this to do something dynamic... For example, "Pick your favorite
         * of these 37".
         * @param parent
         * @return
         */
        protected View getNothingSelectedDropdownView(ViewGroup parent) {
            View view = layoutInflater.inflate(nothingSelectedDropdownLayout, parent, false);

            return view;
        }

        @Override
        public int getCount() {
            int count = adapter.getCount();
            return count == 0 ? 0 : count + EXTRA;
        }

        @Override
        public Object getItem(int position) {
            return position == 0 ? null : adapter.getItem(position - EXTRA);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position >= EXTRA ? adapter.getItemId(position - EXTRA) : position - EXTRA;
        }

        @Override
        public boolean hasStableIds() {
            return adapter.hasStableIds();
        }

        @Override
        public boolean isEmpty() {
            return adapter.isEmpty();
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            adapter.registerDataSetObserver(observer);
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            adapter.unregisterDataSetObserver(observer);
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return position != 0; // Don't allow the 'nothing selected'
            // item to be picked.
        }

    }

}

