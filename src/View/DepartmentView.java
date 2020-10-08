package View;


import Model.Admin;
import Model.Department;
import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
/**
 * @author Oliver Andersson
 * Information on an employee. Is viewed in a ListView
 * @since 2020-10-07
 */
public class DepartmentView extends AnchorPane implements Observer {
    public void addCertificate(){};
    Department department;
    @FXML Label name;
    boolean selected;

    public DepartmentView(Department department) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepartmentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.department = department;
        name.setText(department.getName());

        Admin.getInstance().addObserver(this);
    }

    @Override
    public void update() {
        name.setText(department.getName());

    }
}