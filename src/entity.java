/**
 * Created by KWong on 3/15/2017.
 */

public abstract class entity{

   private String entityType;
   private int hexCoOrdinateX;
   private int hexCoOrdinateY;
   private int hexCoOrdinateZ;

   public entity(){
        System.out.print("Meeple created");
   }

   public entity(String entityType,int hexCoOrdinateX,int hexCoOrdinateY,int hexCoOrdinateZ){

       System.out.println("Creating entity");

       this.entityType = entityType;
       this.hexCoOrdinateX = hexCoOrdinateX;
       this.hexCoOrdinateY = hexCoOrdinateY;
       this.hexCoOrdinateZ = hexCoOrdinateZ;

   }

}

