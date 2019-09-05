package ui;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ui.Main.program;

public class MainWindowControl implements Initializable, MapComponentInitializedListener  {

    @FXML
    public GoogleMap map;

    @FXML
    public GoogleMapView mapView;

    @FXML
    public ChoiceBox choiceBox;

    @FXML
    public ListView<String> listView;

    @FXML
    public Button loadButton;

    @FXML
    public Button saveButton;

    @FXML
    public Button viewButton;

    @FXML
    public TextField searchField;

    public MapOptions mapOptions;





    //Searches for Post by Post Title through Search Bar. Opens view window if found.
    @FXML
    public void searchButtonPressed() throws IOException {
        String title = searchField.getText();
        if (program.getMode().equals("1")) {
            if (program.getCurrentJournalList().contains(title)) {
                program.viewTitle = title;
                ViewWindowControl tempViewWindow = new ViewWindowControl();
                tempViewWindow.display();
            }
        } else {
            if (program.getCurrentGoalList().contains(title)) {
                program.viewTitle = title;
                ViewWindowControl tempViewWindow = new ViewWindowControl();
                tempViewWindow.display();
                program.viewTitle = null;
            } else {
                System.out.println("Post not found!");
            }
        }
    }

    //Opens Post Window when Post button is pressed
    @FXML
    public void postButtonPressed() throws IOException {
        PostWindow tempPostWindow = new PostWindow();
        tempPostWindow.display();
    }


    //Loads posts from TextFile when load button is pressed. Load button disappears afterwards.
    @FXML
    public void loadButtonPressed()  throws IOException {
        listView.getItems().removeAll();
        listView.getItems().clear();
        program.load();
        loadButton.setVisible(false);
        setModeChoiceBox();
        listView.getSelectionModel().getSelectedItems();

    }

    //Saves all posts to text file when save button is pressed.
    @FXML
    public void saveButtonPressed() {
        program.save();

    }




    //Initializes map API key and populates Choice Box with the two modes. Sets mode to Journal.
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapView.setKey("Enter Key here");
        mapView.addMapInializedListener(this);

        // Choice Box
        choiceBox.getItems().addAll("Journal", "Goals");
        //Set Default Value
        choiceBox.setValue("Journal");


    }


    //Initializes the map at specified location and zoom level. Adds a mouse event handler to the map.
    public void mapInitialized() {
        mapOptions = new MapOptions();
        mapOptions.center(new LatLong(49.2827, -123.1207))
                .streetViewControl(false)
                .zoom(10);
        map = mapView.createMap(mapOptions);

        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {

            LatLong curLatLong = event.getLatLong();
            if (program.PostType != null) {
                program.makeAPost(curLatLong, program.PostType, program.PostTitle, program.PostContent, program.PostDate);
                try {
                    setModeChoiceBox();
                } catch (IOException e) { // Do Nothinng
                     }
                System.out.println("Post Made!");
                program.PostType = null;
            } else {
                System.out.println("No Post Made!");
                System.out.println("Latitude: " + curLatLong.getLatitude());
                System.out.println("Longitude: " + curLatLong.getLongitude());
            }
        });


    }

    //Method executes when Reset button is pressed. Resets the program to be in the mode specified by ChoiceBox, by
    //resetting the map and list-view with posts from the specified type.
    @FXML
    public void setModeChoiceBox() throws IOException {
        listView.getItems().removeAll();
        listView.getItems().clear();
        map.clearMarkers();


        if (choiceBox.getValue().equals("Journal")) {
            map.addMarkers(program.getJournalMarkerList());
            program.setJournalMode();
            resetListView(2);
            System.out.println("JM");
        } else {

            map.addMarkers(program.getGoalMarkerList());
            program.setGoalMode();
            resetListView(1);
            System.out.println("GM");

            OverdueWindowControl overdueWindowControl = new OverdueWindowControl();
            overdueWindowControl.display();

        }
    }


    //Resets the List View when mode is changed.
    @FXML

        public void resetListView(int i) {
        if (i == 1) {
            for (Post post: program.getCurrentGoalList()) {
                addListCell(post.getLocation(), post.getPostTitle(), post.getPostContent());
            }
        } else {
            for (Post post: program.getCurrentJournalList()) {
                addListCell(post.getLocation(), post.getPostTitle(), post.getPostContent());
            }
        }
    }



    //Adds a list cell to ListView
    public void addListCell(LatLong location, String title, String  content) {
        listView.getItems().add(location + "\n_________________________________________________\n" + title
                + "\n_________________________________________________\n" +  content
                + "\n_________________________________________________\n");
    }

    @FXML
    //Opens a view window when view button is clicked
    public void viewButtonClicked() throws IOException {
        String t = listView.getSelectionModel().getSelectedItem();
        String[] temp = t.split("\n_________________________________________________\n");
        t = temp[1];
        program.viewTitle = t;

        ViewWindowControl tempViewWindow = new ViewWindowControl();
        tempViewWindow.display();
        program.viewTitle = null;
    }

    @FXML
    //Re-centers and zooms in map at selected post
    public void recenterMap(MouseEvent arg0) {
        String currentItem = listView.getSelectionModel().getSelectedItem();

        if (currentItem != null) {
            String[] temp = currentItem.split("\n_________________________________________________\n");
            currentItem = temp[1];
            Post p = program.getPost(currentItem);
            map.setCenter(p.getLocation());
            map.setZoom(13);
        }
    }


}



/*

    public void showInfoWindow(LatLong ll) {
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();

        if (Main.getProgram().getMode() == "1") {

            JournalPost tempPost = Main.getProgram().getCurrentJournalList().get(ll);
            infoWindowOptions.content("<h2>"+tempPost.getPostTitle() +"</h2>"
                    + tempPost.getPostType()
                    + tempPost.getPostDate()
                    + tempPost.getPostContent());
            InfoWindow infoWindow = new InfoWindow(infoWindowOptions);


        }


    }*/
