package View;

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Schema extends AnchorPane implements Observer {
    @FXML Button next, previous;
    @FXML GridPane monthGrid, weekGrid;
    @FXML AnchorPane dayView, monthView, weekView;
    @FXML ComboBox<String> viewSelector;
    @FXML Label currentFormatInfo, year;
    @FXML ListView listOfWorkshifts;

    private int dateIndex;
    private Date currentIndex;

    public Schema() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Schema.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        generateDate();
        generateComboBox();
    }

    private void generateDate(){
        currentIndex = new Date();
        currentIndex.setHours(0);
        currentIndex.setMinutes(0);
        currentIndex.setSeconds(0);
        dateIndex = 13;
    }

    private void generateComboBox(){
        viewSelector.getItems().clear();
        viewSelector.getItems().add("Månad");
        viewSelector.getItems().add("Vecka");
        viewSelector.getItems().add("Dag");
        viewSelector.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                switch (newValue) {
                    case "Månad":
                        monthView.toFront();
                        updateMonth();
                        break;
                    case "Vecka":
                        weekView.toFront();
                        updateWeek();
                        break;
                    case "Dag":
                        dayView.toFront();
                        updateDay();
                        break;
                }
            }
        });
    }

    private void updateMonth(){
        for (int i = 0; i < YearMonth.of(currentIndex.getYear(), currentIndex.getMonth() + 1).lengthOfMonth(); i++){

        }
    }
    private void updateWeek(){
        int index = Admin.getInstance().getWorkday(dateIndex).getDayOfWeekOffset();
        int secondIndex = 0;
        for (int i = -index; i<=7-index; i++){
            weekGrid.add(new DayScheduleView(Admin.getInstance().getWorkday(i+dateIndex)), 0, secondIndex);
        }
    }
    private void updateDay(){
        Admin.getInstance().getWorkday(dateIndex).setWorkDay();
        listOfWorkshifts.getItems().clear();
        for (Department d : Admin.getInstance().getDepartments()){
            for (WorkShift w : Admin.getInstance().getWorkday(dateIndex).getWorkShifts(d))
                listOfWorkshifts.getItems().add(new SchemaWorkshift(w, d.getColor()));
        }
    }

    private void generateButtons(){
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    private void sortEmployeesAlphabetically(List<Employee> employees){
        employees.sort(Comparator.comparing(Employee::getName));
    }

    private void generateMonth(){

    }

    private void generateWeek(){

    }

    private void generateDay(){

    }
    @Override
    public void update() {

    }
}
