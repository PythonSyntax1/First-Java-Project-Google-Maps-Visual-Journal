package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.time.LocalDate;

public class Post {

    protected LatLong postLocation;
    protected String postType;
    protected String postContent;
    protected LocalDate postDate;
    protected String title;
    private String completed;


    // Effect : Creates a new Post
    public Post(LatLong location, String title, String type, String post) {
        this.title = title;
        this.postLocation = location;
        this.postType = type;
        this.postContent = post;
        LocalDate postDate = LocalDate.now();
        this.postDate = postDate;
        this.completed = null;

    }

    // EFFECT : Returns Post Location
    public LatLong getLocation() {
        return postLocation;
    }

    // EFFECT : Returns Post Content
    public String getPostContent() {
        return postContent;
    }

    // EFFECT : Returns Post Type
    public String getPostType() {
        return postType;
    }

    // EFFECT : Returns Post Date
    public LocalDate getPostDate() {
        return postDate;
    }

    public String getPostTitle() {
        return title;
    }

    // EFFECT : Changes Post Date to entered Date
    public void setPostDate(LocalDate date) {
        this.postDate = date;
    }

    //Returns the completion status of the Post. Either False, In Progress, or True.
    public String getCompletedStatus() {
        return completed;
    }

    //Sets the completion status of the Post. Either False, In Progress, or True.
    public void setCompletedStatus(String b) {
        this.completed = b;
    }

}
