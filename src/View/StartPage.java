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

/**
 * @author Oliver Andersson
 * Root node in the view, everything is build upon this
 * @since 2020-10-07
 */
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
    @FXML AnchorPane tabDepartmentsPane;
    private Admin admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
        admin = Admin.getInstance();
        setTabs();
        startPage.setVisible(false);
    }

    private void setTabs(){
        //tabSchedule.setContent(); //TODO fixa scheduleView
        PersonList personList = new PersonList(admin.getEmployees());
        DepartmentList departmentList = new DepartmentList(admin.getDepartments());
        tabEmployees.setContent(personList);
        tabEmployeesPane.getChildren().clear();
        tabEmployeesPane.getChildren().add(personList);
        tabCertificates.setContent(new CertificateList());
        tabDepartmentsPane.getChildren().clear();
        tabDepartmentsPane.getChildren().add(departmentList);

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
        startPage.setVisible(true);
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