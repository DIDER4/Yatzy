package models;
/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    // Data fields
    private Die[] dice;
    int[] dicesArray = new int[6];

    // Constructor
    public YatzyResultCalculator(Die[] dice) {
        this.dice=dice;
        for (int i = 0; i < dicesArray.length - 1; i++){
            dicesArray[dice[i].getEyes()-1]++;

        }
    }

    /**
     * Calculates the score for Yatzy uppersection
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */

    public int upperSectionScore(int eyes) {
        return dicesArray[eyes - 1] * eyes;
    }

    public int onePairScore() {
        for (int i = 5; i <= dicesArray.length && i >= 0; i--){
            if (dicesArray[i] >= 2){
                return (i + 1) * 2;
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
        for (int i = 5; i <= dicesArray.length && i >=0; i--){
            if (dicesArray[i]>=3){
                return (i+1)*3;
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        for (int i = 5; i <= dicesArray.length && i >=0; i--){
            if (dicesArray[i]>=4){
                return (i+1)*4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        int sum = 0;
        for (int i = 0; i < dicesArray.length-1; i++){
            if (dicesArray[i]==1){
                sum++;
            }
        }
        if (sum==5){
            return 15;
        }
        else {
            return 0;
        }
    }

    public int largeStraightScore() {
        int sum = 0;
        for (int i = 1; i < dicesArray.length; i++){
            if (dicesArray[i]==1){
                sum++;
            }
        }
        if (sum==5){
            return 20;
        }
        else {
            return 0;
        }
    }

    public int fullHouseScore() {
        int sum = 0;
        for (int i = 0; i < dicesArray.length; i++){
            if (dicesArray[i]==2){
                sum=(i+1)*2;
            }
            if (sum>0) {
                for (int j = 0; j < dicesArray.length && j >= 0; j++) {
                    if (dicesArray[j] == 3) {
                        sum+=(j+1)*3;
                        return sum;


                    }
                }
            }
        }
        return 0;
    }

    public int chanceScore() {
        int sum = 0;
        for (int i = 0; i < dicesArray.length; i++){
            sum+=dicesArray[i]*(i+1);
        }

        return sum;
    }

    public int yatzyScore() {
        for (int i = 5; i <= dicesArray.length && i >=0; i--){
            if (dicesArray[i]>=5){
                return 50;
            }
        }
        return 0;
    }

    public int upperSectionSum(){
        int sum = 0;
        for(int index = 0; index < dicesArray.length; index++){
            sum += ((index + 1) * dicesArray[index]);
        }
        return sum;
    }

    private int sumOfPair(int diceFace, int pair){
        return diceFace * pair;
    }
}
