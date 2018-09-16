package com.altashcools.altaschools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by raziehakbari on 24/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Post> mDataset;
    private String type;
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Post> myDataset, String type) {
        mDataset = myDataset;
        this.type = type;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
       //Main page data
        public TextView name;
        public TextView course;
        public TextView description;
        public CardView cardView;
        public CircleImageView circleImageView;
        public View view;
        public LinearLayout linearLayout;

        //course list data
        public TextView courseName;
        public TextView courseLevel;
        public TextView coursePrice;
        public RatingBar ratingBar;
        public CardView lectureCardView;

        public ViewHolder(View v, String type) {
            super(v);
            if (type.equals("main")) {
                name = v.findViewById(R.id.user_name);
                course = v.findViewById(R.id.user_course);
                description = v.findViewById(R.id.user_short_desc);
                //circleImageView = v.findViewById(R.id.profile_image);
                cardView = v.findViewById(R.id.card_view);
                linearLayout = v.findViewById(R.id.advertise_layout);
            } else if (type.equals("course_list")) {
                courseName = v.findViewById(R.id.course_list_name);
                courseLevel = v.findViewById(R.id.course_list_level);
                coursePrice = v.findViewById(R.id.course_list_price);
               // ratingBar = v.findViewById(R.id.course_list_rating);
                lectureCardView = v.findViewById(R.id.course_list_detail);
            }
            view = v;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= null;
        // create a new view based on type
        if(type.equals("main")){
             v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_main, parent, false);
        }
        else if(type.equals("course_list")){
            v =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_detail_view, parent, false);
        }

        ViewHolder vh = new ViewHolder(v,type);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (type.equals("main")) {
            holder.name.setText(mDataset.get(position).firstName + "  " + mDataset.get(position).lastName);
            holder.course.setText(mDataset.get(position).courses);
            holder.description.setText(mDataset.get(position).description);
            //Picasso.with(holder.view.getContext().getApplicationContext()).load(mDataset.get(position).profilePic).into(holder.circleImageView);
            final ImageView backgrounImage = new ImageView(holder.view.getContext().getApplicationContext());
            Picasso.with(holder.view.getContext().getApplicationContext()).load(mDataset.get(position).addLink).into(backgrounImage, new Callback() {
                @Override
                public void onSuccess() {

                    holder.linearLayout.setBackgroundDrawable(backgrounImage.getDrawable());
                }

                @Override
                public void onError() {

                }
            });

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ActivityTeacherProfileView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String userIdToPass = Long.toString(mDataset.get(position).userId);
                    intent.putExtra("userId", userIdToPass);
                    view.getContext().getApplicationContext().startActivity(intent);
                }
            });
        } else if (type.equals("course_list")) {
            holder.courseName.setText(mDataset.get(position).courseName);
            holder.courseLevel.setText(mDataset.get(position).courseLevel);
            holder.coursePrice.setText(mDataset.get(position).Price+" $");
           // holder.ratingBar.setNumStars(mDataset.get(position).Rate);
            holder.lectureCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ActivityLectureInfo.class);
                    Bundle options = ActivityOptionsCompat.makeScaleUpAnimation(
                            view, 0, 0, view.getWidth(), view.getHeight()).toBundle();

                    ActivityCompat.startActivity(view.getContext(), intent, options);
                }
            });

        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
