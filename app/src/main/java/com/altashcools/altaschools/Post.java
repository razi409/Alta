package com.altashcools.altaschools;

/**
 * Created by raziehakbari on 24/02/2018.
 */

public  class Post {

    public Long userId;
    public int place;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(Long uid, int place) {
        this.userId = uid;
        this.place = place;

    }
}
