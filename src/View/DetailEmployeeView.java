package View;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Oliver Andersson
 * Interface for editing and viewing information about an employee
 * @since 2020-10-07
 */

public class DetailEmployeeView extends AnchorPane implements Observer {
    Employee employee;

    @FXML DatePicker datePicker;
    @FXML javafx.scene.control.TextField firstName, lastName, personalID;
    @FXML Button saveChanges, deleteEmployee, addCertificate, removeCertificate, createCertificate, discardCertificate;
    @FXML ListView<EmployeeCertificateObject> certificateList;
    @FXML ListView<CertificateObject> availableCertificates;
    @FXML AnchorPane certificatePicker, information;
    Certificate selected;

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
        generateCertificates();
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
        generateCertificates();
        Admin.getInstance().addObserver(this);
    }

    private void generateCertificates(){
        availableCertificates.getItems().clear();
        Iterator<Certificate> certificateIterator = Admin.getInstance().getCertificatehandler().getAllCertificates();
        while (certificateIterator.hasNext()) {
            CertificateObject tmp = new CertificateObject(certificateIterator.next());
            tmp.checked.setDisable(true);
            tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selected = tmp.certificate;
                }
            });
            availableCertificates.getItems().add(tmp);
        }
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
        removeCertificate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (EmployeeCertificateObject e: certificateList.getItems()){
                    if (e.checked.isSelected()){
                        Admin.getInstance().removeEmployeeCertificate(e.certificate.getCertificate(), employee);
                        e.checked.setSelected(false);
                    }
                }
            }
        });
        addCertificate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                information.toBack();
                certificatePicker.toFront();
            }
        });
        discardCertificate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                information.toFront();
                certificatePicker.toBack();
            }
        });
        createCertificate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate localDate = datePicker.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                Admin.getInstance().createEmployeeCertificate(selected, employee, date);
                information.toFront();
                certificatePicker.toBack();
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
            this.certificateList.getItems().clear();
            for (EmployeeCertificate employeeCertificate: employee.getAllCertificates()){
                this.certificateList.getItems().add(new EmployeeCertificateObject(employeeCertificate));
            }
        }
    }

    @Override
    public void update() {
        generateFXMLObjects();
    }
}