package View;

import Model.Admin;;
import Model.Certificate;
import Model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.Iterator;

public class CertificateList extends AnchorPane implements Observer {
    @FXML
    ListView<CertificateObject> listOfCertificates;
    public CertificateList() {
        Iterator<Certificate> certificateIterator = Admin.getInstance().getCertificatehandler().getAllCertificates();
        while (certificateIterator.hasNext()){
            listOfCertificates.getItems().add(new CertificateObject(certificateIterator.next()));
        }
        Admin.getInstance().addObserver(this);
    }

    @Override
    public void update() {

    }
}
