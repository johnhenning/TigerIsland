package GameState;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Player {
    private int score;
    private int Meeples;
    private int Totoro;

    public Player() {
        score = 0;
        Meeples = 20;
        Totoro = 3;
    }

    public int getScore() {
        return score;
    }

    public int getMeeplesCount() {
        return Meeples;
    }

    public int getTotoroCount() {
        return Totoro;
    }

    public void removeMeeple() {
        Meeples -= 1;
    }

    public void removeTotoro() {
        Totoro -= 2;
    }
    public void addScore(int score) {
        this.score += score;
    }
}
