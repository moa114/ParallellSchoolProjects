package View;

import Controller.AdminController;
import Model.Admin;
import Model.Employee;
import Model.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonList extends AnchorPane implements Observer {
    private AdminController controller;
    private Map<Employee, EmployeeView> employeeEmployeeViewMap;
    @FXML ListView<EmployeeView> employeeViewListView;
    @FXML Button buttonDeleteEmployee, buttonCreateEmployee;
    @FXML TextField textFieldName, textFieldPersonalID;
    @FXML AnchorPane paneDetailView;
    private String name="", personalID="";

    public void selectAll(){}
    public void unSelectAll(){}

    public PersonList(List<Employee> employees) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        this.employeeEmployeeViewMap = new HashMap<>();
        generatePersonViews(employees);
        generateButtons();
        generateDetailView();
    }

    private void generateDetailView(){
        paneDetailView.getChildren().clear();
    }

    private void generateButtons(){
        textFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                name = newVal;
            }
        });

        textFieldPersonalID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                personalID = newVal;
            }
        });

        buttonCreateEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createEmployee();
                update();
            }
        });

        buttonDeleteEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteEmployee();
                update();
            }
        });
    }

    private void deleteEmployee(){
        for (Employee e : Admin.getInstance().getEmployees()){
            if (employeeEmployeeViewMap.get(e).select.isSelected())
                Admin.getInstance().removeEmployee(e);
        }
    }

    private void createEmployee(){
        Admin.getInstance().createNewEmployee(name, personalID);
    }

    private void sortEmployeesAlphabetically(List<Employee> employees){
        employees.sort(Comparator.comparing(Employee::getName));
    }

    private void generatePersonViews(List<Employee> employees){
        sortEmployeesAlphabetically(employees);
        employeeViewListView.getItems().clear();
        for (Employee e : employees) {
            EmployeeView employeeView = new EmployeeView(e);
            employeeEmployeeViewMap.put(e, employeeView);
            employeeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    paneDetailView.getChildren().clear();
                    paneDetailView.getChildren().add(new DetailEmployeeView(e));
                }
            });
            employeeViewListView.getItems().add(employeeEmployeeViewMap.get(e));
        }
    }

    @Override
    public void update() {
        generatePersonViews(Admin.getInstance().getEmployees());
    }
}
