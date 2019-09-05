package ui;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static ui.Main.program;

public class ViewWindowControl implements Initializable {

    private Stage viewWindow;

    @FXML
    private Label titlelabel;

    @FXML
    private Label typelabel;

    @FXML
    private Label postlabel;

    @FXML
    private ChoiceBox<String> completebox;

    @FXML
    private Button removebutton;

    private String title;

    private Post post;


    //Initializes view window based on the current mode of the program. If Journal mode, complete box is removed.
    public void initialize(URL location, ResourceBundle resources) {
        completebox.getItems().addAll("False", "In Progress", "True");


        completebox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> resetStatus(newValue) );


        if (program.getMode() == "1") {
            post = program.getCurrentJournalList().get(program.viewTitle);
            completebox.setVisible(false);
        } else {
            post = program.getCurrentGoalList().get(program.viewTitle);
            completebox.setValue(String.valueOf(post.getCompletedStatus()));
        }
        title = post.getPostTitle();
        titlelabel.setText(title);
        typelabel.setText(post.getPostType());
        postlabel.setText(post.getPostContent());
    }

    public void display() throws IOException {



        viewWindow = new Stage();
        Parent postWindowRoot = FXMLLoader.load(getClass().getResource("ViewWindow.fxml"));
        viewWindow.initModality(Modality.APPLICATION_MODAL);
        viewWindow.setTitle("View Window");
        viewWindow.setScene(new Scene(postWindowRoot));
        viewWindow.show();

    }

    @FXML
    //Deletes Post when delete button is pressed.
    public void delete() {
        if (program.getMode() == "1") {
            program.getCurrentJournalList().remove(title);
            program.loadMarkers(1);
        } else {
            program.getCurrentGoalList().remove(title);
            program.loadMarkers(2);
        }
        Stage stage = (Stage) removebutton.getScene().getWindow();
        stage.close();
    }

    //Changes the post status when ChoiceBox is changed.
    public void resetStatus(String newValue) {
        if (program.getMode() == "2") {
            post.setCompletedStatus(newValue);
            if (newValue.equals("True")) {
                if (!program.getCurrentJournalList().contains(post.getPostTitle())) {
                    program.setJournalMode();
                    program.makeAPost(post.getLocation(), post.getPostType(), post.getPostTitle(), post.getPostContent(), LocalDate.now());
                    program.setGoalMode();
                }
            }
        }
    }

}
