package gui;

import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.RaffleCup;
import models.Die;
import models.YatzyResultCalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/*
    - Muligt at spille to spillere
        - Lav ekstra tekstfelt
    - Lav hold funktionalitet - radioButton
    - Lav Bonus og Total metode
        - Bonus: Se Bonus metode for beskrivelse
        - Total: sum + value of each label from index 7 - 16
    - Opdel funktionalitet i metoder - simplificer læsbarhed
 */


public class YatzyGui extends Application {
    private final RaffleCup raffleCup = new RaffleCup();
    private final ArrayList<Label> diceFaceLabelList = new ArrayList<>();

    // Integrate
    private final ArrayList<RadioButton> radioButtonList = new ArrayList<>();
    private static final boolean[] isHeld = new boolean[5];

    private int counter;
    private final Label numberOfRolls = new Label("0");
    private final Button rollDiceButton = new Button("Throw dice");

    private final ArrayList<Label> tableLabelList = new ArrayList<>();
    private final ArrayList<TextField> textFieldList = new ArrayList<>();

    private final ArrayList<Integer> resultsList = new ArrayList<>();

    private final Stage threeCountStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane primaryPane = new GridPane();
        this.innerContents(primaryPane);
        Scene firstScene = new Scene(primaryPane, 400, 800);
        primaryStage.setScene(firstScene);
        primaryStage.show();
        primaryPane.setPadding(new Insets(10));
        primaryPane.setHgap(10);
        primaryPane.setVgap(10);
    }

    private void innerContents(GridPane pane) {
        // upper GUI elements
        GridPane diceBox = new GridPane();
        diceBox.setPadding(new Insets(10));
        diceBox.setHgap(10);
        diceBox.setVgap(10);
        for (int row = 0; row < 3; row++) {
            for (int columnn = 0; columnn < 5; columnn++) {
                if (row == 0) {
                    Label diceFaceLabel = new Label("0");
                    diceFaceLabelList.add(diceFaceLabel);
                    diceFaceLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
                    diceFaceLabel.setPadding(new Insets(15));
                    diceBox.add(diceFaceLabel, columnn, row);
                    GridPane.setHalignment(diceFaceLabel, HPos.CENTER);
                } else if (row == 1) {
                    RadioButton radioButton = new RadioButton("Hold");
                    radioButtonList.add(radioButton);
                    diceBox.add(radioButton, columnn, row);
                    radioButton.setOnAction(e -> {
                        int selectedDice = radioButtonList.indexOf(radioButton);
                        isHeld[selectedDice] = radioButton.isSelected();
                    });
                    // radioButton.setOnAction(e -> ); Need to make a method for holding a dice from changing
                }

            }
            if (row == 2) {
                Label numberOfRollsLabel = new Label("Number of throws:");
                HBox hbox = new HBox(numberOfRollsLabel, numberOfRolls, rollDiceButton);
                hbox.setSpacing(10);
                diceBox.add(hbox, 0, 2, 5, 1);
            }
        }
        pane.add(diceBox, 0, 0);

        // User interactions
        rollDiceButton.setOnMouseClicked(e -> {
            if (counter < 3) {
                Die[] raffleCup = throwRaffleCup();
                addDiceToLabel(raffleCup);
                updateNumberOfRolls();
                checkPotentialPoints(raffleCup);
                if (counter == 3){
                    threeCountStage.setTitle("Slå igen");
                    GridPane pane2 = new GridPane();
                    threeCountContent(pane2);
                    Scene scene = new Scene(pane2);
                    threeCountStage.setScene(scene);
                    threeCountStage.showAndWait();




                    counter=0;
                }
            }

        });

        // lower GUI elements
        GridPane scoreBoardTable = new GridPane();
        scoreBoardTable.setPadding(new Insets(10));
        scoreBoardTable.setVgap(10);
        scoreBoardTable.setHgap(10);

        String[] labels = {
                "1'ere", "2'ere", "3'ere", "4'ere", "5'ere", "6'ere", "Sum", "Bonus",
                "Et par", "To par", "3 ens", "4 ens", "Lille straight", "Store straight",
                "Fuldt hus", "Chance", "Yatzy", "Total"
        };

        for (int row = 0; row < labels.length; row++) {
            int index = row;
            Label label = new Label(labels[row]);
            TextField textField = new TextField();
            tableLabelList.add(label);
            textFieldList.add(textField);
            scoreBoardTable.add(label, 0, row);
            scoreBoardTable.add(textField, 1, row);
            textField.setOnMouseClicked(event -> textFieldAction(textFieldList.get(index),index));
        }
        pane.add(scoreBoardTable, 0, 1);

    }

    private Die[] throwRaffleCup() {
        this.raffleCup.throwDice();
        return raffleCup.getDice();
    }

    private void addDiceToLabel(Die[] raffleCup) {
        for (int index = 0; index < raffleCup.length; index++) {
            String currentDiceEyes = toString(raffleCup[index].getEyes());
            diceFaceLabelList.get(index).setText(currentDiceEyes);
        }
    }

    private void updateNumberOfRolls() {
        this.counter++;
        numberOfRolls.setText(toString(counter));
    }

    private void checkPotentialPoints(Die[] raffleCup) {
        YatzyResultCalculator currentThrow = new YatzyResultCalculator(raffleCup);

        resultsList.add(currentThrow.upperSectionSum());
        resultsList.add(bonusScore());
        resultsList.add(currentThrow.onePairScore());
        resultsList.add(currentThrow.twoPairScore());
        resultsList.add(currentThrow.threeOfAKindScore());
        resultsList.add(currentThrow.fourOfAKindScore());
        resultsList.add(currentThrow.smallStraightScore());
        resultsList.add(currentThrow.largeStraightScore());
        resultsList.add(currentThrow.fullHouseScore());
        resultsList.add(currentThrow.chanceScore());
        resultsList.add(currentThrow.yatzyScore());
        resultsList.add(totalScore());

        for (int index = 0; index < textFieldList.size(); index++) {
            if (index < 6) {
                int currentDice = index + 1;
                String upperScore = toString(currentThrow.upperSectionScore(currentDice));
                textFieldList.get(index).setText(upperScore);
            } else {
                String text = toString(resultsList.get(index - 6)); // Defaults the index to 0 from 6
                textFieldList.get(index).setText(text);
            }
        }
    }

    private int bonusScore() {
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            Label diceLabel = diceFaceLabelList.get(i);
            int diceValue = Integer.parseInt(diceLabel.getText());
            sum += diceValue;
        }

        if (sum == 63) {
            return 50;
        } else {
            return 0;
        }
    }

    private int totalScore(){

        int sum = 0;
        for(int index = 0; index < resultsList.size(); index++){
            sum += resultsList.get(index);
        }
        return sum;
    }

    private String toString(int eyes) {
        return Integer.toString(eyes);
    }

    public static boolean[] getIsHeld() {
        return isHeld;
    }

    private void textFieldAction (TextField textField, int index){
        int points = Integer.parseInt(textField.getText());
        resultsList.add(index,points);
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: lightgray;");
    }

    public void threeCountContent(GridPane pane){
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);

        Label label = new Label("!!3 Slag slået!!");
        pane.add(label,0,0);

        Button button = new Button("Slå igen");
        pane.add(button,0,1);

        button.setOnAction(event -> {threeCountStage.close(); numberOfRolls.setText("0");});
    }
}
