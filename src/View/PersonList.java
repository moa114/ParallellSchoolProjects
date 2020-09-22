package View;

import Controller.AdminController;
import Model.Employee;
import Model.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;

public class PersonList extends AnchorPane implements Observer {
    private AdminController controller;
    @FXML ListView<EmployeeView> employeeViewListView;
    private ObservableList<EmployeeView> employeeViews = FXCollections.observableArrayList();

    public void selectAll(){}
    public void unSelectAll(){}

    public PersonList(List<Employee> employees) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        sortEmployeesAlphabetically(employees);
        generatePersonViews(employees);
    }

    private void sortEmployeesAlphabetically(List<Employee> employees){
        employees.sort(Comparator.comparing(Employee::getName));
    }

    private void generatePersonViews(List<Employee> employees){

        employeeViews.clear();
        for (Employee e : employees){
            employeeViews.add(new EmployeeView(e));
        }
        employeeViewListView.setItems(employeeViews);
    }

    @Override
    public void update() {

    }
}
