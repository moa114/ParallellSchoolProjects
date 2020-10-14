import Model.Admin;
import Model.Certificate;
import Model.EmployeeCertificate;
import Model.WorkShift;
import View.Schema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Oliver Andersson
 * Runnable class for the project
 * @since 2020-10-07
 */
public class Runnable extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Admin.getInstance().createNewDepartment("Disken", 2, new Color(1, 0, 0.4, 0.6));
        Admin.getInstance().createWorkshift(Admin.getInstance().getDepartmentByName("Disken"), Admin.getInstance().getWorkday(13).DATE+1000*3600*8, Admin.getInstance().getWorkday(13).DATE+1000*3600*16
                , new boolean[7]);
        System.out.println("Debug 1");
        Admin.getInstance().createWorkshift(Admin.getInstance().getDepartmentByName("Disken"), Admin.getInstance().getWorkday(13).DATE+1000*3600*8, Admin.getInstance().getWorkday(13).DATE+1000*3600*16
                , new boolean[7]);
        System.out.println("Debug 2");
        /*
        for (int index = 0; index<10; index++)
            Admin.getInstance().createNewEmployee("Oliver Andersson", Long.toString(200006010000L+index));
        Admin.getInstance().getCertificatehandler().createNewCertificate("Bil");
        Admin.getInstance().getCertificatehandler().createNewCertificate("Kassa");
        Admin.getInstance().createEmployeeCertificate(Admin.getInstance().getCertificatehandler().getCertificate("Bil"), Admin.getInstance().getEmployees().get(0), new Date(9999999999L));
        Admin.getInstance().createEmployeeCertificate(Admin.getInstance().getCertificatehandler().getCertificate("Kassa"), Admin.getInstance().getEmployees().get(0), new Date(9999999999L));
        */
        URL url = new File("src/View/StartPage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                //TODO shutdownhook
            }
        });
    }
}
