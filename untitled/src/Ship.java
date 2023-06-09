import java.util.ArrayList;
import java.util.List;

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

    public void shipAttack(){

    }
    public void reciveDamage(Integer attack){
        hp -= attack;
    }

    public Boolean isCollision(Integer newX, Integer newY, ArrayList<Ship> ships){
        for (Ship ship : ships){
            if (ship != this && ship.getPosX() == newX && ship.getPosY() == newY)
                return true;
        }
        return false;
    }

    public Boolean isValidMove(Integer newX, Integer newY, Integer map[][], ArrayList<Ship> ships)
    {
        Integer mapWidth = map[0].length;
        Integer mapHeight = map.length;

        if (newX < 0 || newX >= mapWidth || newY < 0 || newY >= mapHeight)
            return false;

        return map[newX][newY] == 0 && !isCollision(newX, newY, ships);
    }

    public abstract void shipMovement(Integer map[][], ArrayList<Ship> ships);
    public abstract List<String> getAvailableDirections();
}