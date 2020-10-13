package View;

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    @FXML DatePicker datePicker, date1, date2;
    @FXML javafx.scene.control.TextField firstName, lastName, personalID;
    @FXML Button saveChanges, deleteEmployee, addCertificate, removeCertificate, createCertificate, discardCertificate, addVacation, registerVacationButton, discardVacationButton;
    @FXML ListView<EmployeeCertificateObject> certificateList;
    @FXML ListView<CertificateObject> availableCertificates;
    @FXML AnchorPane certificatePicker, information, registerVacation;
    @FXML TextField hour1, hour2, min1, min2;
    @FXML Label confirmVacText;

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
        generateTextFields();
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
        generateTextFields();
        generateCertificates();
        Admin.getInstance().addObserver(this);
    }

    private void generateCertificates(){
        availableCertificates.getItems().clear();
        Iterator<Certificate> certificateIterator = Admin.getInstance().getCertificatehandler().getAllCertificates();
        while (certificateIterator.hasNext()) {
            CertificateObject tmp = new CertificateObject(certificateIterator.next());
            tmp.checked.setVisible(false);
            tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selected = tmp.certificate;
                }
            });
            availableCertificates.getItems().add(tmp);
        }
    }


    private  void generateTextFields(){
        hour1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    hour1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        hour2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    hour2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        min1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    min1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        min2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    min2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    private void generateButtons(){

        saveChanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (employee == null) {
                    Admin.getInstance().createNewEmployee(firstName.getText() + " " + lastName.getText(), personalID.getText(), "email@com"); //TODO add emails
                }
                else{
                    Admin.getInstance().changeEmployeeName(employee, firstName.getText() + " " + lastName.getText());
                }
            }
        });

        registerVacationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate localDate = date1.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                long vacStart= date.getTime()+ ((Long.parseLong(min1.getText()))*1000*60) + ((Long.parseLong(hour1.getText()))*1000*60*60); //TODO weekhandlder
                long vacStop= date.getTime()+ ((Long.parseLong(min2.getText()))*1000*60) + ((Long.parseLong(hour2.getText()))*1000*60*60);
                Admin.getInstance().setVacation(employee,vacStart,vacStop);
                confirmVacText.setVisible(true);
                registerVacation.toBack();
            }
        });

        addVacation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                registerVacation.toFront();
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
        discardVacationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                information.toFront();
                registerVacation.toBack();
            }
        });
        createCertificate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate localDate = datePicker.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                Admin.getInstance().createEmployeeCertificate(selected, employee, date);
                registerVacation.toBack();
                certificatePicker.toBack();
                information.toFront();
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
            addVacation.setVisible(true);
        }
    }

    @Override
    public void update() {
        generateFXMLObjects();
    }
}