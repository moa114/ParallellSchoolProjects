package View;

import Model.Employee;
import Model.WorkShift;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Date;

public class SchemaWorkshift extends AnchorPane {
    private Employee employee;
    private Color color;
    private double sizeX = 700, fullDay = 1000*60*60*24;;

    @FXML AnchorPane timeBar;
    @FXML Label start, end, name;

    public SchemaWorkshift(WorkShift workShift, Color color) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SchemaWorkshift.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.employee = workShift.getEmployee();
        this.color = color;
        this.start.setText(new Date(workShift.START).toString());
        this.end.setText(new Date(workShift.END).toString());
        this.timeBar.setPrefWidth(sizeX*percentageOfDayFilled(workShift.START, workShift.END));
        this.timeBar.setTranslateX(getOffset(workShift.START)*sizeX);
        this.timeBar.setTranslateX(200);
        if (workShift.getEmployee()==null) {
            this.timeBar.setStyle("-fx-background-color: #BBBBBB");
            this.name.setText("Ledig!");

        }
        else {
            this.timeBar.setStyle("-fx-background-color: #" + Integer.toHexString((int) color.getRed()*255) +
                    Integer.toHexString((int) color.getGreen()*255) + Integer.toHexString((int) color.getBlue()*255));
            this.name.setText(workShift.getEmployee().getPersonalId());
        }
    }

    private double percentageOfDayFilled(long start, long end){
        long difference = end-start;
        return difference/fullDay;
    }

    private double getOffset(long start){
        Date tmp = new Date(start);
        tmp.setHours(0);
        tmp.setMinutes(0);
        tmp.setSeconds(0);
        return (start-tmp.getTime()/fullDay);
    }
}
