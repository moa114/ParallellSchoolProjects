package View;

import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

public class EmployeeView extends AnchorPane implements Observer {
    public void addCertificate(){};
    Employee e;

    public EmployeeView(Employee e) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.e = e;
    }

    @FXML CheckBox select;

    @Override
    public void update() {

    }
}
