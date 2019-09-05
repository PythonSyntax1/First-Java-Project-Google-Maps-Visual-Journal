package ui;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import model.GoalPostList;
import model.JournalPostList;
import model.Post;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Program {

    private String mode;
    private static JournalPostList CurrentList = new JournalPostList();
    private static GoalPostList CurrentGoalList = new GoalPostList();
    private static Markers CurrentMarkers = new Markers();

    public static String PostType;
    public static String PostContent;
    public static LocalDate PostDate;
    public static String PostTitle;
    public static String viewTitle;


    // Creates program backend. Program houses all fields that are static to the application.
    public Program() {

        System.out.println("In Journal Mode");

        mode = "1";

    }


    //Sets the mode to Journal Mode
    public void setJournalMode() {
        this.mode = "1";
    }

    //Sets the mode to Goal Mode
    public void setGoalMode() {
        this.mode = "2";
    }

    //Returns the mode of the program
    public String getMode() {
        return mode;
    }

    //Returns the GoalPostList
    public GoalPostList getCurrentGoalList() {
        return CurrentGoalList;
    }

    //Returns the Journal Post List
    public JournalPostList getCurrentJournalList() {
        return CurrentList;
    }


    //Makes a new Post and adds it to either the Goal List or Journal List. Also creates a new Marker and adds it to
    //the map.
    public void makeAPost(LatLong curLocation, String curType, String curTitle, String curPost, LocalDate date) {

        if (mode.equals("1")) {
            Post newJournalPost = new Post(curLocation, curTitle, curType, curPost);
            newJournalPost.setPostDate(date);
            CurrentList.addPost(curTitle, newJournalPost);
            CurrentMarkers.addJournalMarkerList(curLocation);

        } else {
            Post newGoalPost = new Post(curLocation, curTitle, curType, curPost);
            newGoalPost.setPostDate(date);
            newGoalPost.setCompletedStatus("False");
            try {
                CurrentGoalList.addPost(curTitle, newGoalPost);
                CurrentMarkers.addGoalMarkerList(curLocation);

            } catch (GoalPostList.TooManyGoalsException e) {
                System.out.println("Too many goals set! Mark some complete.");
            }
        }
    }

    //Returns post by searching respective Post-List with title.
    public Post getPost(String title) {
        if (mode == "1") {
            return CurrentList.get(title);
        } else {
            return CurrentGoalList.get(title);
        }
    }


    //Checks if any items in the Goal Post list are overdue and returns them in a ListView. Runs every time mode is
    //set to Goal, or anytime a new Goal is made in Post.
    public ArrayList<String> checkDueDates() {
        ArrayList<String> overDueTitles = new ArrayList<>();
        LocalDate todaysdate = LocalDate.now();

        for (Post post: CurrentGoalList) {
            if (todaysdate.compareTo(post.getPostDate()) > 0 && !post.getCompletedStatus().equals("True")) {
                overDueTitles.add(post.getPostTitle());
            }
        }

        return overDueTitles;
    }

    //Saves all Posts in both Journal List and Goal List to a text file.
    public void save() {
        try {
            CurrentList.save("postlist.txt");
            CurrentGoalList.save("postlist2.txt");
            System.out.println("Files Saved!");
        } catch (IOException e) {
            System.out.println("Saving files could not be located!");
        }
    }

    //Loads all posts in both Journal List and Goal List.
    public void load() {

        try {
            CurrentList.load("postlist.txt");
            loadMarkers(1);
            CurrentGoalList.load("postlist2.txt");
            loadMarkers(2);
        } catch (IOException e) {
            System.out.println("Files not found!");
        }
    }

    //Iterates through PostLists and creates a marker out of each Post's location
    public void loadMarkers(int i) {

        if (i == 1) {
            CurrentMarkers.getJournalMarkerList().clear();
            for (Post jp: CurrentList.getPostList().values()) {
                CurrentMarkers.addJournalMarkerList(jp.getLocation());
            }

        } else {
            CurrentMarkers.getGoalMarkerList().clear();
            for (Post gp: CurrentGoalList.getPostList().values()) {
                CurrentMarkers.addGoalMarkerList(gp.getLocation());
            }
        }
    }


    public ArrayList<Marker> getJournalMarkerList() {
        return CurrentMarkers.getJournalMarkerList();
    }

    public ArrayList<Marker> getGoalMarkerList() {
        return CurrentMarkers.getGoalMarkerList();
    }



}


























/*    public void ViewPostsInOrder() {

        System.out.println("Please Enter an Area");
        Scanner inviewlocation = new Scanner(System.in);
        ViewLocation = inviewlocation.nextLine();

        if (mode.equals("1")) {
            ArrayList<JournalPost> ListAtLocation = CurrentList.GetPostList(ViewLocation);

            int currentindex = ListAtLocation.size() - 1;
            while (currentindex >= 0) {
                System.out.println(ListAtLocation.get(currentindex).getPostContent());
                System.out.println("Enter next to continue");
                Scanner inviewingstatus = new Scanner(System.in);
                String ViewingStatus = inviewingstatus.nextLine();
                if (ViewingStatus.equals("next")) {
                    currentindex -= 1;
                } else {
                    break;
                }
            }
        }   else { ArrayList<GoalPost> ListAtLocation = CurrentGoalList.GetPostList(ViewLocation);

            int currentindex = ListAtLocation.size() - 1;
            while (currentindex >= 0) {
                System.out.println(ListAtLocation.get(currentindex).getPostContent());
                System.out.println("Enter next to continue");
                Scanner inviewingstatus = new Scanner(System.in);
                String ViewingStatus = inviewingstatus.nextLine();
                if (ViewingStatus.equals("next")) {
                    currentindex -= 1;
                } else {
                    break;
                }
            }
        }
    }*/









/*    public void ViewPostsInArea() {
        ViewMode = "";
        System.out.println("Please Enter an Area");
        Scanner inviewlocation = new Scanner(System.in);
        ViewLocation = inviewlocation.nextLine();


        while (!ViewMode.equals("1") & !ViewMode.equals("2")) {
            System.out.println("Please Enter Viewing Mode");
            System.out.println("1 : Check all posts in area");
            System.out.println("2 : Look at posts in area in Chronological Order");
            Scanner inviewmode = new Scanner(System.in);
            ViewMode = inviewmode.nextLine();
            if (ViewMode.equals("1")) {
                CurrentList.CheckPostsInArea(ViewLocation);
                ViewMode = "";
            } else if (ViewMode.equals("2")) {
                ViewPostsInOrder(ViewLocation);
                ViewMode = "";
            } else if (ViewMode.equals("Quit")) {
                break;
            }
        }
    }*/


/*
    public Program() throws IOException {

        CurrentList.load();
        createview();

        while (true) {
            System.out.println("Welcome to Mountain Assistant. Please enter usage mode.");

            while (!mode.equals("1") & !mode.equals("2")) {
                System.out.println("1 : Make JournalPost");
                System.out.println("2 : View Posts");
                Scanner in = new Scanner(System.in);
                mode = in.nextLine();
                if (mode.equals("1")) {
                    makeAPost();
                    mode = "";
                } else if (mode.equals("2")) {
                    ViewPostsInArea();
                    mode = "";
                } else if (mode.equals("Quit")) {
                    CurrentList.save();
                    break;
                }
            }
            break;
        }
    }
*/


/*    public void createGUI() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        frame.getContentPane().add(panel);

        //Making Buttons
        JButton MakePostButton = new JButton("Make JournalPost");
        JButton ViewPostButton = new JButton("View All Posts");
        JButton SaveButton = new JButton("Save Posts");
        JButton ViewPostInOrderButton = new JButton("View Posts in Order");

        //Adding Action Listeners
        MakePostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                makeAPost();
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CurrentList.save("postlist.txt");}
                catch (IOException f) {}
            }
        });

        ViewPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPostsInArea();
            }
        });

        ViewPostInOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPostsInOrder();
            }
        });

        //Making Labels
        JLabel MenuLabel = new JLabel();
        MenuLabel.setPreferredSize(new Dimension(200, 30));
        MenuLabel.setText("Welcome to Mountain Assistant");

        //Adding Objects
        panel.add(MenuLabel);
        panel.add(MakePostButton);
        panel.add(ViewPostButton);
        panel.add(SaveButton);
        panel.add(ViewPostInOrderButton);

        //Modifying frame settings
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Mountain Assistant");
        frame.setVisible(true);

    }*/


 /*   public void switchMode() {
        if (mode.equals("1")){
            mode = "2";
            System.out.println("Switched to Goal Mode");
        } else {
            mode = "1";
            System.out.println("Switched to Journal Mode");
        }
    }*/






// FORMER MAIN CLASS

/*
package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;



public class Main extends Application {
    private Button AddToJournalButton, ViewEntriesButton,OrderedViewButton, SwitchModeButton;
    private Scene menu, postwindow, viewwindow, orderedviewwindow;
    private Stage window;
    private static Program program;


    public static void main(String[] args) throws IOException {

        program = new Program();
        launch();

    }



    @Override
    public void start(Stage primaryStage) throws IOException{

        window = primaryStage;

        Scene ViewWindow;

        //View Window Scene

        Label label2 = new Label("View Entries");

        //Layout2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(label2);
        ViewWindow = new Scene(layout2, 300, 250);


        // Menu Label
        Label label1 = new Label("Welcome to Mountain Journal");

        //Switch Mode Button
        SwitchModeButton = new Button();
        SwitchModeButton.setText("Switch Mode");
        SwitchModeButton.setOnAction(e -> program.setJournalMode());

        //Add to Journal Button
        AddToJournalButton = new Button();
        AddToJournalButton.setText("Add to Journal");
        AddToJournalButton.setOnAction(e -> {
            program.makeAPost();

        });

        //View Entries Button
        ViewEntriesButton = new Button();
        ViewEntriesButton.setText("View All Posts");
        ViewEntriesButton.setOnAction(e -> {
            program.ViewPostsInArea();
            primaryStage.setScene(ViewWindow);});


        //Menu Layout

        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label1, SwitchModeButton, AddToJournalButton,
        ViewEntriesButton, OrderedViewButton);
        layout1.setAlignment(Pos.CENTER);

        Scene menu = new Scene(layout1, 300, 250);

        //Stage Settings

        primaryStage.setOnCloseRequest(e -> {
            try {closeProgram();}
            catch (IOException f){}
        });
        primaryStage.setTitle("Mountain Journal");
        primaryStage.setScene(menu);
        primaryStage.show();

    }

    private void closeProgram() throws IOException {
        System.out.println("Entries Saved");
        program.save();
        window.close();

    }


    }


*/





