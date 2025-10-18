//Main method to run and execut the program
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        primaryStage.setTitle("Matrix Grid Example");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
