package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ui.Main.program;

public class PostWindow implements Initializable {


    @FXML
    ChoiceBox<String> typeBox;

    @FXML
    DatePicker datePicker;

    @FXML
    TextArea postText;

    @FXML
    TextField postTitleField;

    @FXML
    Button saveButton;

    private Stage postWindow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        typeBox.getItems().addAll("Bike", "Hike");
        typeBox.setValue("Bike");

    }




    public void display() throws IOException {
        postWindow = new Stage();


        Parent postWindowRoot = FXMLLoader.load(getClass().getResource("PostWindow.fxml"));
        postWindow.initModality(Modality.APPLICATION_MODAL);
        postWindow.setTitle("Make Post");
        postWindow.setScene(new Scene(postWindowRoot));

        postWindow.showAndWait();


    }

    @FXML
    //Makes a new post
    private void makePost() throws IOException {

        program.PostDate = datePicker.getValue();
        program.PostContent = postText.getText();
        program.PostType =  typeBox.getValue();
        program.PostTitle = postTitleField.getText();


        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }



}
