package View;

import Model.Employee;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class DetailEmployeeView extends AnchorPane implements Observer {
    Employee employee;
    @FXML TextField firstName, personalID;
    public DetailEmployeeView(Employee employee) {
        this.employee = employee;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailEmployeeView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        generateFXMLObjects();
    }
    private void generateFXMLObjects(){
        this.firstName.setText(employee.name);
        this.personalID.setText(employee.personalId);
    }

    @Override
    public void update() {

    }
}
