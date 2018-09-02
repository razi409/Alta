package com.altashcools.altaschools;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private boolean fabMenuOpen = false;
    public static FloatingActionButton fab1;
    public static FloatingActionButton fab2;
    public static FloatingActionButton fab3;
    public static FloatingActionButton fab4;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /***********************************************************
         * Handles the View pager
         ************************************************************/
        final ViewPager viewPager = findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);

        //Handles the fab and its fab_children

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);


        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        fab4 = (FloatingActionButton) findViewById(R.id.fab4);

        fab = findViewById(R.id.fab);
        fab.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
                animateFAB();
            }
        });


        TabLayout tabLayout = findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.main_list_selector);
        tabLayout.getTabAt(1).setIcon(R.drawable.infinite_selector);
        tabLayout.getTabAt(2).setIcon(R.drawable.profile_selector);

        //change page listener for viewpager
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position) {

                if(fabMenuOpen)
                {
                    if(position==0 || position==2)
                    {
                        animateFAB();
                    }
                }

            }
        });



    }

    /***************************************************************************
     * This method, handles the animation of floating action buttons for search
     ***************************************************************************/
    public void animateFAB() {

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        View view =  this.getWindow().getDecorView().findViewById(android.R.id.content);
        float width= (float) 0.75 *(view.getWidth());
        width =width/2;


        if (fabMenuOpen) {

            //fab1.startAnimation(hide_fab_1);
            //fab1.setClickable(false);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);

            fab1.animate().translationY(0).translationX(0).alpha(0f).start();
            fab2.animate().translationY(0).translationX(0).alpha(0f).start();
            fab3.animate().translationY(0).translationX(0).alpha(0f).start();
            fab4.animate().translationY(0).translationX(0).alpha(0f).start();

            fab.animate().translationY(0).translationX(0).start();

            fabMenuOpen = false;

        } else {

           // fab1.startAnimation(show_fab_1);
           // fab1.setClickable(true);


            fab1.setVisibility(View.VISIBLE);
            fab2.setVisibility(View.VISIBLE);
            fab3.setVisibility(View.VISIBLE);
            fab4.setVisibility(View.VISIBLE);

            fab2.setClickable(true);
            fab1.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);

            fab.animate().translationY(0).translationX(-(width)).setDuration(500).start();
            fab1.animate().alpha(1f).setDuration(500).translationY(-20).translationX(-(width+250)).setDuration(500).start();
            fab2.animate().alpha(1f).setDuration(500).translationY(-180).translationX(-(width+120)).setDuration(500).start();
            fab3.animate().alpha(1f).setDuration(500).translationY(-180).translationX(-(width-120)).setDuration(500).start();
            fab4.animate().alpha(1f).setDuration(500).translationY(-20).translationX(-(width-250)).setDuration(500).start();

            fabMenuOpen = true;

        }
    }
    @SuppressLint("PrivateResource")
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new pagerFragment(R.layout.main_fragment));
        adapter.addFrag(new pagerFragment(R.layout.main_search));
        adapter.addFrag(new pagerFragment(R.layout.main_login));
        viewPager.setAdapter(adapter);
    }

    /*********************************************************
     * Handles the setting button on the main view
     * @param menu
     * @return
     *********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
