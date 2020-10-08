
package View;

import Model.Certificate;
import Model.EmployeeCertificate;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CertificateObject extends AnchorPane implements Observer {
    Certificate certificate;
    @FXML Label labelName;
    @FXML Button remove;

    public CertificateObject(Certificate certificate) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CertificateObject.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.certificate = certificate;
        this.labelName.setText(certificate.getName());
    }

    @Override
    public void update() {

    }
}