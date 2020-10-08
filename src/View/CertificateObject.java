
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
/**
 * @author Oliver Andersson
 * Visual representation of an certificate. Is viewed in a ListView
 * @since 2020-10-07
 */
public class CertificateObject extends AnchorPane{
    Certificate certificate;
    @FXML Label name;
    @FXML CheckBox checked;

    public CertificateObject(Certificate certificate) {
        this.certificate = certificate;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CertificateObject.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        fixLabels();
    }

    private void fixLabels(){
        this.name.setText(certificate.getName());
    }

}