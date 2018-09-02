package com.altashcools.altaschools;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ActivityTeacherProfileView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Razi Akbari");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*******************************************
         * handles the Contact information Card view
         *******************************************/
        CardView cardViewContactInfo = findViewById(R.id.profile_course_contact_info);

        /********************************************
         * Handles the work experience card view
         ******************************************/
        CardView cardViewExpInfo = findViewById(R.id.profile_course_exp_info);


        /*******************************************
         * Handles the Academic background cardview
         ******************************************/
        CardView cardViewAcademicInfo = findViewById(R.id.profile_course_academic_info);


        /******************************************
         * Handles Language proficiency cardview
         *****************************************/
        CardView cardViewLanguageInfo = findViewById(R.id.profile_course_language_info);


        /*********************************************
         * handles the recycler view for list courses
         ********************************************/
        final RecyclerView recyclerView = findViewById(R.id.prfile_course_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplication().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ArrayList<Post> dataToShow = new ArrayList<>();
        Post val = new Post();

        val.ratings = 2;
        val.courseName = "English";
        val.courseLevel= "University";

        dataToShow.add(val);
        MyAdapter mAdapter = new MyAdapter(dataToShow,"course_list");
        recyclerView.setAdapter(mAdapter);


    }
}
