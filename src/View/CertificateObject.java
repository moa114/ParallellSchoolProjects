package View;

import Model.EmployeeCertificate;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CertificateObject extends AnchorPane implements Observer {
    EmployeeCertificate certificate;
    @FXML Label labelName;
    @FXML Button remove;

    public CertificateObject(EmployeeCertificate certificate) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CertificateObject.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.certificate = certificate;
        this.labelName.setText(certificate.getCertificateName());
    }

    @Override
    public void update() {

    }
}
