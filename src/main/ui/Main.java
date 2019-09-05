package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Program program;

    private Stage window;


    public static void main(String[] args) {
        program = new Program();
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Mountain Journal");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        primaryStage.setOnCloseRequest(e -> {
            closeProgram(); });

    }

    private void closeProgram() {
        window.close();

    }

    public static Program getProgram() {
        return program;
    }



}
