package View;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;

/**
 * @author
 *
 *
 */

public class DetailDepartmentView extends AnchorPane implements Observer {
    Department department;

    @FXML javafx.scene.control.TextField name;
    @FXML Spinner maxPersonsOnBreak;
    @FXML Button saveChanges, deleteDepartment;
    @FXML ColorPicker colorPicker;


    public DetailDepartmentView(Department department) {
        this.department = department;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailDepartmentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        generateButtons();
        generateFXMLObjects();
        Admin.getInstance().addObserver(this);
    }

    public DetailDepartmentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailDepartmentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        generateButtons();
        generateFXMLObjects();
        Admin.getInstance().addObserver(this);
    }



    private void generateButtons(){
        saveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (department == null) {
                    Admin.getInstance().createNewDepartment(name.getText(), Integer.parseInt(maxPersonsOnBreak.getEditor().getText()),colorPicker.getValue());
                    department= Admin.getInstance().getDepartmentByName(name.getText());
                    System.out.println( Admin.getInstance().getDepartmentByName(name.getText()).getColor());
                }
                else{
                    Admin.getInstance().changeDepartmentName(department, name.getText());
                    Admin.getInstance().getDepartmentByName(name.getText()).setColor(colorPicker.getValue());
                    //TODO change maxPersonsOnBreak
                }
            }
        });
        deleteDepartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteAction();
            }
        });

    }

    private void deleteAction(){
        if (department == null) {
            name.setText("");
            maxPersonsOnBreak.getEditor().setText("");
        }
        else
            Admin.getInstance().deleteDepartment(department);
        Admin.getInstance().removeObserver(this);
    }

    private void generateFXMLObjects(){
        if (department != null){
            this.name.setText(department.getName().split(" ")[0]);
            this.maxPersonsOnBreak.getEditor().setText(String.valueOf(department.getMaxPersonsOnBreak()));
            this.colorPicker.setValue(department.getColor());
        }
    }

    @Override
    public void update() {
        generateFXMLObjects();
    }
}