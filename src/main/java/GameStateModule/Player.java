package GameStateModule;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Player {
    private int score;
    private int numMeeples;
    private int numTotoros;

    public Player() {
        score = 0;
        numMeeples = 20;
        numTotoros = 3;
    }

    public int getScore() {
        return score;
    }

    public int getNumMeeples() {
        return numMeeples;
    }

    public int getNumTotoros() {
        return numTotoros;
    }

    public void removeMeeple(int amount) {
        numMeeples -= amount;
    }

    public void removeMeeple() {
        numMeeples -= 1;
    }

    public void removeTotoro() {
        numTotoros -= 1;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
