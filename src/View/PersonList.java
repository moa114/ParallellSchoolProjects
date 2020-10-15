
package View;


import Model.Admin;
import Model.Employee;
import Model.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.lang.reflect.Array;
import java.util.*;

public class PersonList extends AnchorPane implements Observer {
    private Map<Employee, EmployeeView> employeeEmployeeViewMap;
    private List<EmployeeView> employeeViews;
    @FXML FlowPane employeeViewPane;
    @FXML Button buttonCreateEmployee;
    @FXML AnchorPane paneDetailView;
    private int sizeOfEmployees = 0;

    public PersonList() {
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
        this.employeeViews = new ArrayList<>();
        generatePersonViews();
        generateButtons();
        Admin.getInstance().addObserver(this);
    }

    private void generateButtons(){
        buttonCreateEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createEmployee();
            }
        });
    }

    private void createEmployee(){
        paneDetailView.getChildren().clear();
        paneDetailView.getChildren().add(new DetailEmployeeView());
        paneDetailView.setVisible(true);
    }

    private void sortEmployeesAlphabetically(List<Employee> employees){
        employees.sort(Comparator.comparing(Employee::getName));
    }

    private void generatePersonViews(){
        List<Employee> employees = new ArrayList<>();
        sizeOfEmployees = Admin.getInstance().getEmployeeListSize();
        for (int i = 0; i < sizeOfEmployees; i++)
            employees.add(Admin.getInstance().getEmployee(i));
        sortEmployeesAlphabetically(employees);
        employeeViewPane.getChildren().clear();
        for (Employee e : employees) {
            if (employeeEmployeeViewMap.get(e) == null) {
                EmployeeView employeeView = new EmployeeView(e);
                employeeEmployeeViewMap.put(e, employeeView);
                employeeViews.add(employeeView);
                employeeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        paneDetailView.getChildren().clear();
                        paneDetailView.getChildren().add(new DetailEmployeeView(e));
                    }
                });
            }
            employeeViewPane.getChildren().add(employeeEmployeeViewMap.get(e));
        }
    }

    @Override
    public void update() {
        generatePersonViews();
    }
}
