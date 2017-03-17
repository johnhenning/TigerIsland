/**
 * Created by KWong on 3/16/2017.
 */
public class Coordinates {

    private int x;
    private int y;
    private Entity entity;
    private int meepleCount;
    private int totoroCount;

    public Coordinates(int x,int y, Entity entity){
        this.x = x;
        this.y = y;
        this.entity = entity;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getMeepleCount(){
        return meepleCount;
    }

    public int getTotoroCount(){
        return totoroCount;
    }

    public Entity getEntity(){
        return entity;
    }

}
