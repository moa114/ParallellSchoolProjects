package View;

import Model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class EmployeeView {
    public void addCertificate(){};
    Employee e;

    public EmployeeView(Employee e) {
        this.e = e;
    }

    @FXML CheckBox select;

}
