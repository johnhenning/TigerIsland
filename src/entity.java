/**
 * Created by KWong on 3/15/2017.
 */

import java.util.ArrayList;

public class entity{

   private ArrayList<entity> settlements;

   private String entityType;
   private int hexCoOrdinateX;
   private int hexCoOrdinateY;
   private int height;


   entity(){

   }

   public entity(String entityType, int x,int y, int height){

       this.entityType = entityType;
       this.hexCoOrdinateX = x;
       this.hexCoOrdinateY = y;
       this.height = height;

       if(entityType.equalsIgnoreCase("meeple")){
          meeple meepObject;
          meepObject = new meeple(entityType,x,y,height);
       }

       if(entityType.equalsIgnoreCase("totoro")){
           totoro totoroObject;
           totoroObject = new totoro(entityType,x,y,height);
       }

   }


}

