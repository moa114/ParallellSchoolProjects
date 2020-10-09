package View;


import Model.Admin;
import Model.Department;
import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
    @FXML Pane departmentColor;

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
            Color color=department.getColor(); //här är den vit FEL
            BackgroundFill backgroundFill=new BackgroundFill(color,new CornerRadii(5.0), new Insets(-5));
            departmentColor.setBackground(new Background(backgroundFill));
            Admin.getInstance().addObserver(this);
    }

    @Override
    public void update() {
        name.setText(department.getName());
        Color color=department.getColor();
        BackgroundFill backgroundFill= new BackgroundFill(department.getColor(), new CornerRadii(5.0), new Insets(-5));
        this.departmentColor.setBackground(new Background(backgroundFill));
    }
}