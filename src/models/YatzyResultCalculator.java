package models;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {


    private Die[] dice;
    int[] dicesArray = new int[6];

    public YatzyResultCalculator(Die[] dice) {
        this.dice=dice;
        for (int i = 0; i< dicesArray.length-1; i++){
            dicesArray[dice[i].getEyes()-1]++;

        }
        //TODO: implement YatzyResultCalculator constructor.
    }

    /**
     * Calculates the score for Yatzy uppersection
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        return dicesArray[eyes-1]*eyes;
    }

    public int onePairScore() {
        for (int i = 5; i <= dicesArray.length && i >=0; i--){
            if (dicesArray[i]>=2){
                return (i+1)*2;
            }
        }
        return 0;
    }

    public int twoPairScore() {
        int sum = 0;
        for (int i = 0; i < dicesArray.length; i++){
            if (dicesArray[i]>=2){
                sum=(i+1)*2;
            }
            if (sum>0) {
                for (int j = 0; j < dicesArray.length && j >= 0; j++) {
                    if (dicesArray[j] >= 2) {
                        if ((j+1)*2<sum || (j+1)*2>sum){
                            sum+=(j+1)*2;
                            return sum;
                        }

                    }
                }
            }
        }


        return 0;
    }

    public int threeOfAKindScore() {
        //TODO: implement threeOfAKindScore method.
        return 0;
    }

    public int fourOfAKindScore() {
        //TODO: implement fourOfAKindScore method.
        return 0;
    }

    public int smallStraightScore() {
        //TODO: implement smallStraightScore method.
        return 0;
    }

    public int largeStraightScore() {
        //TODO: implement largeStraightScore method.
        return 0;
    }

    public int fullHouseScore() {
        //TODO: implement fullHouseScore method.
        return 0;
    }

    public int chanceScore() {
        //TODO: implement chanceScore method.
        return 0;
    }

    public int yatzyScore() {
        //TODO: implement yatzyScore method.
        return 0;
    }
}
