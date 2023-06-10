import java.util.ArrayList;

public abstract class Ship {
    protected Integer id;
    protected Integer posX;
    protected Integer posY;
    protected String direction;


    public Ship (Integer posX, Integer posY, Integer id, String direction){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }
    public abstract void shipAttack();
    public abstract void shipMovement(Integer map[][], ArrayList<Ship> ships);

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }


}