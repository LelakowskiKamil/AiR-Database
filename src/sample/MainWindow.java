package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class MainWindow extends Application {
    public static Connection myConn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("mainWindowSample.fxml"));

        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Projekt Okno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
