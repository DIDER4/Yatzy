package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class YatzyGui extends Application {
    Label lblCelcius = new Label("Celcius");
    Label lblFahrenheit = new Label("Fahrenheit");
    private final TextField txfCelcius = new TextField("0");
    private final TextField txfFahrenheit = new TextField("0");


    @Override
    public void start(Stage stage) {
        stage.setTitle("Opgave 4 Grader");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(300);
        stage.show();
    }
    private void initContent(GridPane pane) {
        // enable this to show grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(0);
        // set vertical gap between components
        pane.setVgap(0);

        // add a label to the pane (at col=0, row=0)
        pane.add(lblCelcius, 0, 0);
        pane.add(lblFahrenheit, 4, 0);
        pane.add(txfCelcius, 0, 1, 2, 1);
        pane.add(txfFahrenheit, 4, 1, 2, 1);



        // add a button to the pane (at col=1, row=1)
        Button btnCtoF = new Button("<<");
        pane.add(btnCtoF, 3, 1);
        GridPane.setMargin(btnCtoF, new Insets(10, 10, 10, 10));
        btnCtoF.setOnAction(event -> this.CtoF());


        // add a button to the pane (at col=2, row=1)
        Button btnFtoC = new Button(">>");
        pane.add(btnFtoC, 3, 0);
        GridPane.setMargin(btnFtoC, new Insets(10, 10, 10, 10));
        btnFtoC.setOnAction(event -> this.FtoC());


    }
}
