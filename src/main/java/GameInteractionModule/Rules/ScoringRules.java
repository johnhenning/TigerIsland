package GameInteractionModule.Rules;

import GameStateModule.Hex;

/**
 * Created by johnhenning on 3/19/17.
 */
public class ScoringRules extends Rules {

    public static int settlementFounded(){
        return 1;
    }

    public static int settlementExpanded(Hex hex){
        return (int)Math.pow(hex.getLevel(),2);
    }

    public static int totoroSanctuaryBuilt(){
        return 200;
    }

    public static int tigerPlaygroundBuilt(){ return 75; }

}
