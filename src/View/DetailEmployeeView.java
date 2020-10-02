package View;

import Model.Admin;
import Model.Employee;
import Model.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


public class DetailEmployeeView extends AnchorPane implements Observer {
    Employee employee;
    @FXML javafx.scene.control.TextField firstName, lastName, personalID;
    @FXML Button saveChanges, deleteEmployee;
    @FXML ListView<EmployeeCertificateObject> certificateList;

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
        generateButtons();
        Admin.getInstance().addObserver(this);
    }

    public DetailEmployeeView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailEmployeeView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        generateButtons();
        Admin.getInstance().addObserver(this);
    }

    private void generateButtons(){
        saveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (employee == null) {
                    Admin.getInstance().createNewEmployee(firstName.getText() + " " + lastName.getText(), personalID.getText());
                }
                else{
                    Admin.getInstance().changeEmployeeName(employee, firstName.getText() + " " + lastName.getText());
                }
            }
        });
        deleteEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteAction();
            }
        });
    }

    private void deleteAction(){
        if (employee == null) {
            firstName.setText("");
            lastName.setText("");
            personalID.setText("");
            certificateList.getItems().clear();
        }
        else
            Admin.getInstance().removeEmployee(employee);
        Admin.getInstance().removeObserver(this);
    }

    private void generateFXMLObjects(){
        if (employee != null){
            this.firstName.setText(employee.getName().split(" ")[0]);
            this.lastName.setText(employee.getName().split(" ")[1]);
            this.personalID.setText(employee.getPersonalId());
        }
    }

    @Override
    public void update() {
        generateFXMLObjects();
    }
}