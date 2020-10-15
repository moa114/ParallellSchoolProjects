package View;

import Model.Department;
import Model.WorkShift;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class SchemaDepartment extends FlowPane {
    public SchemaDepartment(Department d, List<WorkShift> workShifts) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SchemaDepartment.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.setVgap(10);
        for (WorkShift w : workShifts)
            this.getChildren().add(new SchemaWorkshift(w, d.getColor()));
    }
}
