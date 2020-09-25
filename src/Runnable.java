import Model.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Runnable extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int index = 0; index<10; index++)
            Admin.getInstance().createNewEmployee("Oliver Andersson", Long.toString(200006010000L+index));
        URL url = new File("src/View/StartPage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                //TODO shutdownhook
            }
        });
    }
}
