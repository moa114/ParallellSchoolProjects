package View;

import Controller.AdminController;
import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class SelectedEmployeeView extends AnchorPane implements Observer {
    AdminController controller;
    private Employee employee;
    @FXML Button buttonClose;
    @FXML Label labelName;
    @FXML Label labelPersonalID;
    @FXML ListView<String> certificates;

    public SelectedEmployeeView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

    @Override
    public void update() {

    }
}
