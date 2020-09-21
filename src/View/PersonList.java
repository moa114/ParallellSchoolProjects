package View;

import Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class PersonList extends AnchorPane {
    @FXML ListView<EmployeeView> employeeViewListView;
    private ObservableList<EmployeeView> employeeViews = FXCollections.observableArrayList();

    public void selectAll(){}
    public void unSelectAll(){}

    private void generatePersonViews(List<Employee> employees){
        employeeViews.clear();
        for (Employee e : employees){
            employeeViews.add(new EmployeeView(e));
        }
        this.employeeViewListView.setItems(employeeViews);
    }
}
