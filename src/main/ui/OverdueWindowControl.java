package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ui.Main.program;

public class OverdueWindowControl implements Initializable {

    @FXML
    private ListView listView;


    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> incomplete = program.checkDueDates();
        listView.getItems().addAll(incomplete);
    }

    public void display() throws IOException {


        Stage overdueWindow = new Stage();
        Parent overdueWindowRoot = FXMLLoader.load(getClass().getResource("OverdueWindow.fxml"));
        overdueWindow.initModality(Modality.APPLICATION_MODAL);
        overdueWindow.setTitle("Overdue Posts");
        overdueWindow.setScene(new Scene(overdueWindowRoot));
        overdueWindow.showAndWait();

    }


}
