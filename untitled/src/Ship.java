import java.util.ArrayList;

public abstract class Ship {
    protected Integer id;
    protected Integer posX;
    protected Integer posY;
    protected Integer attack;
    protected String direction;
    protected Integer hp;
    protected Integer movement;

    public Ship (Integer id, Integer posX, Integer posY, Integer attack, String direction, Integer hp, Integer movement){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.attack = attack;
        this.direction = direction;
        this.hp = hp;
        this.movement = movement;
    }

    public void shipAttack(){

    }
    public void reciveDamage(Integer attack){
        hp -= attack;
    }

    public abstract void shipMovement(Integer map[][], ArrayList<Ship> ships);
}