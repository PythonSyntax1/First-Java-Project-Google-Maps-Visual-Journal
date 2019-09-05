package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class GoalPostList extends PostList {


    public GoalPostList() {
        super();
    }
    


    //Saves all posts in HashMap to a text file
    public void save(String s) throws IOException {


        File postlist = new File(s);
        FileOutputStream fos = new FileOutputStream(postlist);
        PrintWriter pw = new PrintWriter(fos);

        for (Map.Entry<String, Post> m : postList.entrySet()) {
            double lat = m.getValue().getLocation().getLatitude();
            double lon = m.getValue().getLocation().getLongitude();
            Post p = m.getValue();
            pw.println(lat +  "=" + lon + "=" + p.getPostType() + "="
                    + p.getPostContent().replace("\n", " ") + "=" + p.getPostDate() + "="
                    + p.getCompletedStatus() + "=" + p.getPostTitle());
        }
        pw.flush();
        pw.close();
        fos.close();
    }



    //Loads saved Posts into new HashMap
    public void load(String s) throws IOException {
        File currentList = new File(s);
        FileInputStream fis = new FileInputStream(currentList);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {

            String currentLine = sc.nextLine();
            String[] words = currentLine.split("=");
            LatLong currentLocation = new LatLong(Double.parseDouble(words[0]), Double.parseDouble(words[1]));
            LocalDate currenttime = LocalDate.parse(words[4]);
            Post newpost = new Post(currentLocation, words[6], words[2], words[3]);
            newpost.setPostDate(currenttime);
            newpost.setCompletedStatus(words[5]);
            postList.put(words[6], newpost);
        }
        fis.close();
    }


    //Adds new GoalPost to GoalPostList if there are under 15 goals. Otherwise, throws Exception.
    public void addPost(String title, Post e) throws TooManyGoalsException {
        if (postList.size() >= 15) {
            throw new TooManyGoalsException();
        }
        postList.put(title, e);
    }




    public class TooManyGoalsException extends Exception {

    }

}
