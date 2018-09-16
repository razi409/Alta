package com.altashcools.altaschools;

import com.google.firebase.database.Exclude;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raziehakbari on 24/02/2018.
 */

public  class Post {

    public long teacherId;
    public Long userId;

    public int Gender;
    public int Price;
    public int Rate;
    public int age;
    public int teacherCourseLevel;
    public int place;
    public int ratings;
    public int year;

    public String birthDate;
    public String teacherCap;
    public String teacherName;
    public String firstName;
    public String lastName;
    public String description;
    public String courses;
    public String addLink;
    public String profilePic;
    public String courseName;
    public String courseLevel;
    public String street;
    public String number;
    public String cap;
    public String exp_subject;
    public String exp_level;
    public String exp_where;
    public String exp_duration;
    public String academic_subject;
    public String academic_level;
    public String academic_duration;
    public String academic_where;
    public String language_one;
    public String language_two;
    public String language_three;
    public String Address;
    public String Email;
    public String Mobile;
    public String Province;
    public String city;
    public String backgroundPic;

    public Map<String,Map<String ,Object>> teacherCourseList;
    public Map<String,String> academic;
    public Map<String,String> experience;
    public Map<String,String> language;

    public float lat;
    public float longitude;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(Long userId, int place,String firstName, String lastName, String description, String courses, String addLink, String profilePic) {
        this.userId = userId;
        this.place = place;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.courses = courses;
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
    public Post(String firstName, String lastName, String birthDate, String Address, String Cap, Map<String,String> experience, Map<String,String> academic, Map<String,String> language, Map<String,Map<String,Object>> teacherCourseList, String City, String Email, int Gender, String Mobile, String Province, String backgroundPic, String profilePic ) {
        this.firstName        = firstName;
        this.lastName         = lastName;
        this.birthDate        = birthDate;
        this.cap              = Cap;
        this.Address          = Address;
        this.city             = City;
        this.Email            = Email;
        this.Gender           = Gender;
        this.Mobile           = Mobile;
        this.Province         = Province;
        this.academic         = academic;
        this.backgroundPic    = backgroundPic;
        this.birthDate        = birthDate;
        this.teacherCourseList  = teacherCourseList;
        this.experience       = experience;
        this.language         = language;
        this.profilePic       = profilePic;

    }

    public Post(String firstName, String lastName, String birthDate, String street, String number, String cap, String exp_subject, String exp_level, String exp_where, String exp_duration, String academic_subject, String academic_level, String academic_where, String academic_duration, String language_one, String language_two, String language_tree) {
        this.firstName        = firstName;
        this.lastName         = lastName;
        this.birthDate        = birthDate;
        this.street           = street;
        this.number           = number;
        this.cap              = cap;
        this.exp_subject      = exp_subject;
        this.exp_level        = exp_level;
        this.exp_where        = exp_where;
        this.exp_duration     = exp_duration;
        this.academic_subject = academic_subject;
        this.academic_level   = academic_level;
        this.academic_duration= academic_duration;
        this.academic_where   = academic_where;
        this.language_one     = language_one;
        this.language_two     = language_two;
        this.language_three   = language_tree;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstName",         firstName);
        result.put("lastName",          lastName);
        result.put("street",            street);
        result.put("number",            number);
        result.put("cap",               cap);
        result.put("exp_subject",       exp_subject);
        result.put("exp_level",         exp_level);
        result.put("exp_where",         exp_where);
        result.put("exp_duration",      exp_duration);
        result.put("academic_subject",  academic_subject);
        result.put("academic_level",    academic_level);
        result.put("academic_duration", academic_duration);
        result.put("academic_where",    academic_where);
        result.put("language_one",      language_one);
        result.put("language_two",      language_two);
        result.put("language_three",    language_three);

        return result;
    }
}
