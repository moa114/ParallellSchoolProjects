package View;

import Controller.AdminController;
import Model.Admin;
import Model.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class StartPage implements Observer, Initializable {
    @FXML AnchorPane backGround;
    @FXML AnchorPane startPage;
    @FXML AnchorPane defaultPage;
    @FXML Button buttonNewFile;
    @FXML Button buttonSaveAndExit; //TODO Implement load and save functionality
    @FXML Button buttonLoadFile; //TODO Implement load and save functionality
    @FXML Tab tabSchedule;
    @FXML Tab tabEmployees;
    @FXML Tab tabDepartments;
    @FXML Tab tabCertificates;
    @FXML AnchorPane tabEmployeesPane;
    private Admin admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
        admin = Admin.getInstance();
        setTabs();
    }

    private void setTabs(){
        //tabSchedule.setContent(); //TODO fixa scheduleView
        tabEmployees.setContent(new PersonList(admin.getEmployees()));
        tabEmployeesPane.getChildren().clear();
        PersonList personList = new PersonList(admin.getEmployees());
        tabEmployeesPane.getChildren().clear();
        tabEmployeesPane.getChildren().add(personList);
    }
    private void setButtons(){
        buttonSaveAndExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveAndExit();
            }
        });
        buttonNewFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startNewFile();
            }
        });
    }

    private void startNewFile(){
        startPage.toFront();
        defaultPage.toBack();
        defaultPage.setVisible(false);
    }
    public void loadPersonnelView(){}

    public void loadScheduleView(){}

    private void exit(){
        System.exit(0);
    }

    private void save(){
        //TODO implement
    }

    void saveAndExit(){
        save();
        exit();
    }

    @Override
    public void update() {

    }
}
