package GameStateModule;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Player {
    private int score;
    private int numMeeples;
    private int numTotoros;
    private int numTigers;
    private int numShaman;

    public Player() {
        score = 0;
        numMeeples = 19;
        numShaman = 1;
        numTotoros = 3;
        numTigers = 2;
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

    public int getNumTigers(){ return numTigers;}

    public void removeMeeple(int amount) {
        numMeeples -= amount;
    }

    public void removeMeeple() {
        numMeeples -= 1;
    }

    public void removeTotoro() {
        numTotoros -= 1;
    }

    public void removeTiger() { numTigers -= 1; }

    public void addScore(int score) {
        this.score += score;
    }

    public boolean equals(Player player){
        return this == player;
    }

    public void removeShaman() {
        numShaman -= 1;
    }
}
