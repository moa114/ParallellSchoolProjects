package View;

import Model.Admin;
import Model.Certificate;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ListView;

public class CertificateList extends AnchorPane implements Observer {
    @FXML ListView listOfCertificates;
    public CertificateList() {
        while (Admin.getInstance().getCertificatehandler().getAllCertificates().hasNext()){
            
        }
    }

    @Override
    public void update() {

    }
}
