package View;

import Model.Admin;
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
    boolean selected;

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
        name.setText(employee.getName());
        personalID.setText(employee.getPersonalId());
        Admin.getInstance().addObserver(this);
    }

    @Override
    public void update() {
        name.setText(employee.getName());

        personalID.setText(employee.getPersonalId());
    }
}