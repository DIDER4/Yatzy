package models;

import gui.YatzyGui;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        for(int index = 0; index < dice.length; index++){
            this.dice[index] = new Die();
        }
    }

    public void throwDice() {
        for(int index = 0; index < dice.length; index++){
            boolean[] isHeldArray = YatzyGui.getIsHeld();
            if(!isHeldArray[index]){
                dice[index].roll();
            }
        }
    }

    public Die[] getDice() {
        return dice;
    }
}
