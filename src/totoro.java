/**
 * Created by KWong on 3/15/2017.
 */
public class totoro extends entity {

    private int totoroScore = 200;
    private int meeplesLeftInPlayerPool = 1;

    totoro(){

    }

    //To be implemented once player information module created
    public void getMeeplesInPlayerPool(){

    }

    totoro(String entityType,int x,int y,int height){

        if(meeplesLeftInPlayerPool == 0){
            return;
        }


        System.out.print("Totoro created" + " " + x + " " + y + " " +height);
    }


}