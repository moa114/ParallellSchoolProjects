
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

import java.util.*;
/**
 * @author Oliver Andersson
 * EmployeeTab of the program. Root node for "employeetab"
 * @since 2020-10-07
 */
public class PersonList extends AnchorPane implements Observer {
    private Map<Employee, EmployeeView> employeeEmployeeViewMap;
    private List<EmployeeView> employeeViews;
    @FXML ListView<EmployeeView> employeeViewPane;
    @FXML Button buttonCreateEmployee;
    @FXML AnchorPane paneDetailView;

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
        this.employeeViews = new ArrayList<>();
        generatePersonViews(employees);
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

    private void generatePersonViews(List<Employee> employees){
        sortEmployeesAlphabetically(employees);
        employeeViewPane.getItems().clear();
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
            employeeViewPane.getItems().add(employeeEmployeeViewMap.get(e));
        }
    }

    @Override
    public void update() {
        generatePersonViews(Admin.getInstance().getEmployees());
    }
}