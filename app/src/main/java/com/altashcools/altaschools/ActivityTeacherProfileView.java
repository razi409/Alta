package com.altashcools.altaschools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivityTeacherProfileView extends AppCompatActivity {

    public ArrayList<Post> dataToShowOnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        /****************************************************
         * loading information of teacher
         ****************************************************/
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query teacherProfileQuery = mFirebaseDatabaseReference.child("Teacher").orderByKey().equalTo(userId);

        final CollapsingToolbarLayout toolbar_layout = findViewById(R.id.toolbar_layout);


        /*******************************************
         * handles the Contact information Card view
         *******************************************/
        CardView cardViewContactInfo = findViewById(R.id.profile_course_contact_info);
        final TextView profile_course_contact_phone = findViewById(R.id.profile_course_contact_phone);
        final TextView profile_course_contact_location = findViewById(R.id.profile_course_contact_location);
        final TextView profile_course_contact_email = findViewById(R.id.profile_course_contact_email);


        /********************************************
         * Handles the work experience card view
         ******************************************/
        CardView cardViewExpInfo = findViewById(R.id.profile_course_exp_info);
        final TextView profile_course_exp_info_subject_one = findViewById(R.id.profile_course_exp_info_subject_one);
        final TextView profile_course_exp_info_level_one = findViewById(R.id.profile_course_exp_info_level_one);
        final TextView profile_course_exp_info_where_one = findViewById(R.id.profile_course_exp_info_where_one);
        final TextView profile_course_exp_info_duration_one = findViewById(R.id.profile_course_exp_info_duration_one);


        /*******************************************
         * Handles the Academic background cardview
         ******************************************/
        CardView cardViewAcademicInfo = findViewById(R.id.profile_course_academic_info);
        final TextView profile_course_academic_info_subject_one = findViewById(R.id.profile_course_academic_info_subject_one);
        final TextView profile_course_academic_info_level_one = findViewById(R.id.profile_course_academic_info_level_one);
        final TextView profile_course_academic_info_where_one = findViewById(R.id.profile_course_academic_info_where_one);
        final TextView profile_course_academic_info_year_one = findViewById(R.id.profile_course_academic_info_year_one);

        /******************************************
         * Handles Language proficiency cardview
         *****************************************/
        CardView cardViewLanguageInfo = findViewById(R.id.profile_course_language_info);
        final TextView profile_course_language_one = findViewById(R.id.profile_course_language_one);
        final TextView profile_course_language_two = findViewById(R.id.profile_course_language_two);
        final TextView profile_course_language_three = findViewById(R.id.profile_course_language_three);


        /*********************************************
         * handles the recycler view for list courses
         ********************************************/
        final RecyclerView recyclerView = findViewById(R.id.prfile_course_list_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplication().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);



        /**********************************************
         * Retreiving information from firebase server
         **********************************************/
        teacherProfileQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataToShowOnSearch = new ArrayList<>();
                for (DataSnapshot dataArray : dataSnapshot.getChildren()) {

                    Post val = new Post();
                    Post data = dataArray.getValue(Post.class);
                    val.firstName        = data.firstName;
                    val.lastName         = data.lastName;
                    val.birthDate        = data.birthDate;
                    val.cap              = data.cap;
                    val.Address          = data.Address;
                    val.city             = data.city;
                    val.Email            = data.Email;
                    val.Gender           = data.Gender;
                    val.Mobile           = data.Mobile;
                    val.Province         = data.Province;
                    val.academic         = data.academic;
                    val.backgroundPic    = data.backgroundPic;
                    val.birthDate        = data.birthDate;
                    val.teacherCourseList           = data.teacherCourseList;
                    val.experience       = data.experience;
                    val.language         = data.language;
                    val.profilePic       = data.profilePic;
                    dataToShowOnSearch.add(data);

                    setTitle(dataToShowOnSearch.get(0).firstName+" "+dataToShowOnSearch.get(0).lastName);

                    //set toolbar background color
                    final ImageView backgrounImage = new ImageView(getApplicationContext());

                    Picasso.with(getApplicationContext()).load(dataToShowOnSearch.get(0).backgroundPic).into(backgrounImage, new Callback() {
                        @Override
                        public void onSuccess() {

                            toolbar_layout.setBackgroundDrawable(backgrounImage.getDrawable());
                        }

                        @Override
                        public void onError() {

                        }
                    });

                    //sets contact info
                    profile_course_contact_phone.setText(val.Mobile);
                    profile_course_contact_location.setText(val.Address);
                    profile_course_contact_email.setText(val.Email);

                    //sets work info
                    profile_course_exp_info_subject_one.setText(val.experience.get("subject").toString());
                    profile_course_exp_info_level_one.setText(val.experience.get("level").toString());
                    profile_course_exp_info_where_one.setText(val.experience.get("where").toString());
                    profile_course_exp_info_duration_one.setText(val.experience.get("duration").toString());

                    //sets academic
                    profile_course_academic_info_subject_one.setText(val.academic.get("subject").toString());
                    profile_course_academic_info_level_one.setText(val.academic.get("level").toString());
                    profile_course_academic_info_where_one.setText(val.academic.get("where").toString());
                    profile_course_academic_info_year_one.setText(val.academic.get("year").toString());

                    //sets language
                    Object[] keys = val.language.keySet().toArray();

                    try{
                        profile_course_language_two.setText(keys[0].toString());
                        profile_course_language_two.setText(keys[1].toString());
                        profile_course_language_three.setText(keys[2].toString());
                    }catch (ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }

                    ArrayList<Post> courseList = new ArrayList<>();
                    Object[] courseKeys = val.teacherCourseList.keySet().toArray();

                    for(int i=0; i<courseKeys.length ; i++){
                        Post courseToPass  = new Post();

                        courseToPass.courseName = val.teacherCourseList.get(courseKeys[i]).get("courseName").toString();
                        courseToPass.courseLevel = val.teacherCourseList.get(courseKeys[i]).get("levelName").toString();
                        courseToPass.Price       = Integer.parseInt(val.teacherCourseList.get(courseKeys[i]).get("price").toString());
                        courseToPass.Rate        = Integer.parseInt(val.teacherCourseList.get(courseKeys[i]).get("rate").toString());
                        courseList.add(courseToPass);
                    }

                    MyAdapter mAdapter = new MyAdapter(courseList,"course_list");
                    recyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
