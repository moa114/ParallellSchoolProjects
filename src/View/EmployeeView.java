package View;

import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EmployeeView extends AnchorPane implements Observer {
    public void addCertificate(){};
    Employee employee;
    @FXML Label name, personalID;
    @FXML CheckBox select;

    public EmployeeView(Employee employee) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.employee = employee;
        name.setText(employee.name);
        personalID.setText(employee.personalId);
    }

    @Override
    public void update() {

    }
}
