package View;

import Controller.AdminController;
import Model.Admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartPage extends AnchorPane{
    @FXML Button buttonManagePersonnel;
    @FXML Button buttonViewSchedule;
    @FXML Button buttonSaveAndExit; //TODO Implement load and save functionality
    @FXML Button buttonLoadFile; //TODO Implement load and save functionality

    AdminController adminController;

    public StartPage(AdminController adminController) {
        this.adminController = adminController;
    }

    public void loadPersonnelView(){
        this.getChildren().add(new PersonList());
    }
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
}
