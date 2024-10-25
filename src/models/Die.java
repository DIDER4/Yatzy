package models;

import java.util.Random;

public class Die {
    private int eyes = 0;
    private final Random random = new Random();

    /**
     * Creates a new Die object, with face set to eyes. Used for test purpose
     * @param eyes value should be between 1 and 6
     */
    public Die(int eyes) {
        this.eyes = eyes;
    }

    public Die() {
    }

    public void roll() {
        int[] dices = new int[5];
        dices[0] = (int) (Math.random() * 6 + 1);
        dices[1] = (int) (Math.random() * 6 + 1);
        dices[2] = (int) (Math.random() * 6 + 1);
        dices[3] = (int) (Math.random() * 6 + 1);
        dices[4] = (int) (Math.random() * 6 + 1);
        return dices;
    }
}
