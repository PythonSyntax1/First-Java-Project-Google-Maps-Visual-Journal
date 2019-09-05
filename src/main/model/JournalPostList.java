package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;


public class JournalPostList extends PostList  {




    // EFFECTS : Creates New HashMap called JournalPostList
    public JournalPostList() {
        super();
    }



    public void save(String s) throws IOException {
        File postlist = new File(s);
        FileOutputStream fos = new FileOutputStream(postlist);
        PrintWriter pw = new PrintWriter(fos);

        for (Map.Entry<String, Post> m : postList.entrySet()) {
            double lat = m.getValue().getLocation().getLatitude();
            double lon = m.getValue().getLocation().getLongitude();
            Post p = m.getValue();

            pw.println(lat + "=" + lon + "=" + p.getPostType() + "="
                    + p.getPostContent().replace("\n", " ") + "=" +  p.getPostDate() + "="
                    + p.getCompletedStatus() + "=" + p.getPostTitle());

        }

        pw.flush();
        pw.close();
        fos.close();
    }

    //EFFECTS : Loads saved Posts into HashMap
    public void load(String s) throws IOException {
        File currentList = new File(s);
        FileInputStream fis = new FileInputStream(currentList);
        Scanner sc = new Scanner(fis);


        while (sc.hasNextLine()) {
            String currentline = sc.nextLine();
            String[] words = currentline.split("=");
            LatLong currentLocation = new LatLong(Double.parseDouble(words[0]), Double.parseDouble(words[1]));
            LocalDate currenttime = LocalDate.parse(words[4]);
            Post newpost = new Post(currentLocation, words[6], words[2], words[3]);
            newpost.setPostDate(currenttime);
            newpost.setCompletedStatus(words[5]);
            postList.put(words[6], newpost);

        }
        fis.close();
    }


    //MODIFIES: this
    //EFFECTS: Appends consumed post to JournalPostList.
    public void addPost(String title, Post e) {
        postList.put(title, e);
    }





}












