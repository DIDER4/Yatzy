package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class YatzyGui extends Application {
    private TextField[] scoreFelt;

    @Override
    public void start(Stage primaryStage) {
        // GridPane for terninger
        GridPane terningeGrid = new GridPane();
        terningeGrid.setPadding(new Insets(10));
        terningeGrid.setHgap(10);
        terningeGrid.setVgap(10);

        // Terningerne og hold checkbokse
        Label[] terningeLabels = new Label[5];
        CheckBox[] holdCheckBoxes = new CheckBox[5];
        for (int i = 0; i < 5; i++) {
            terningeLabels[i] = new Label("0");
            terningeLabels[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
            terningeLabels[i].setPadding(new Insets(10));
            holdCheckBoxes[i] = new CheckBox("Hold");
            terningeGrid.add(terningeLabels[i], i, 0);
            terningeGrid.add(holdCheckBoxes[i], i, 1);
        }

        // Kast-knap og antal kast tilbage
        Button rulKnap = new Button("Kast terningerne");
        Label rultilbage = new Label("Antal kast tilbage: ");
        Label antalRulTilbage = new Label("0");

        VBox terningeBox = new VBox(10, terningeGrid, rulKnap, rultilbage, antalRulTilbage);
        terningeBox.setPadding(new Insets(10));

        // GridPane for scorekortet
        GridPane scoreGrid = new GridPane();
        scoreGrid.setPadding(new Insets(10));
        scoreGrid.setHgap(10);
        scoreGrid.setVgap(10);

        String[] pointMuligheder = {"1'ere", "2'ere", "3'ere", "4'ere", "5'ere", "6'ere", "Sum", "Bonus", "Et par", "To par", "3 ens", "4 ens", "Lille straight", "Store straight", "Fuldt hus", "Chance", "Yatzy"};
        scoreFelt = new TextField[pointMuligheder.length];
        for (int i = 0; i < pointMuligheder.length; i++) {
            scoreGrid.add(new Label(pointMuligheder[i]), 0, i);
            scoreFelt[i] = new TextField();
            scoreGrid.add(scoreFelt[i], 1, i);
        }


        Label totalLabel = new Label("Total:");
        TextField totalFelt = new TextField();
        scoreGrid.add(totalLabel, 0, pointMuligheder.length+1);
        scoreGrid.add(totalFelt, 1, pointMuligheder.length+1);

        VBox root = new VBox(20, terningeBox, scoreGrid);
        Scene scene = new Scene(root, 400, 900);

        primaryStage.setTitle("Yatzy App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}