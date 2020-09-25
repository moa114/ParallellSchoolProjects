package View;

import Model.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class EmployeeCertificateObject extends AnchorPane implements Observer {
    public EmployeeCertificateObject() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeCertificateObject.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

    }
}
