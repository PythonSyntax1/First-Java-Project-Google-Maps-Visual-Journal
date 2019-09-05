package model;

import java.util.HashMap;
import java.util.Iterator;

public abstract class PostList implements Iterable<Post> {

    protected HashMap<String, Post> postList;

    public PostList() {
        postList = new HashMap<>();
    }

    @Override
    public Iterator<Post> iterator() {
        return postList.values().iterator();
    }

    public Post get(String title) {
        return postList.get(title);
    }

    public void remove(String title) {
        postList.remove(title);
    }

    public Boolean contains(String title) {
        return postList.containsKey(title);
    }

    public int getSize() {
        return postList.size();
    }

    public HashMap<String, Post> getPostList() {
        return postList;
    }
}
