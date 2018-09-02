package com.altashcools.altaschools;

/**
 * Created by raziehakbari on 24/02/2018.
 */

public  class Post {

    public   long teacherId;
    public  String teacherName;
    public  int Gender;
    public  int Price;
    public  int Rate;
    public  int age;
    public  String birthDate;
    public  String teacherCap;
    public int teacherCourseLevel;
    public Long userId;
    public int place;
    public String firstName;
    public String lastName;
    public String description;
    public String course;
    public String addLink;
    public String profilePic;
    public String courseName;
    public String courseLevel;
    public int    ratings;
    public float lat;
    public float longitude;
    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(Long userId, int place,String firstName, String lastName, String description, String course, String addLink, String profilePic) {
        this.userId = userId;
        this.place = place;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.course = course;
        this.addLink = addLink;
        this.profilePic = profilePic;

    }

    public Post(String courseName,String courseLevel, int ratings){
        this.courseName = courseName;
        this.courseLevel= courseLevel;
        this.ratings = ratings;
    }

    public Post(int Gender,int Price, int Rate, int age,String birthDate,String profilePic,float longitude, float lat,int teacherCourseLevel,long teacherId,String teacherName){
        this.Gender = Gender;
        this.Price = Price;
        this.Rate = Rate;
        this.age = age;
        this.birthDate = birthDate;
        this.profilePic = profilePic;
        this.lat = lat;
        this.teacherCourseLevel = teacherCourseLevel;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.longitude = longitude;
    }
}
