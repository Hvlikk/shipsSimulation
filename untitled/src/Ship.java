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

    public Boolean isCollision(Integer newX, Integer newY, ArrayList<Ship> ships){
        for (Ship ship : ships){
            if (ship != this && ship.getPosX() == newX && ship.getPosY() == newY)
                return true;
        }
        return false;
    }

    public Boolean isValidMove(Integer newX, Integer newY, char map[][], ArrayList<Ship> ships)
    {
        Integer mapWidth = map[0].length - 1;
        Integer mapHeight = map.length - 1;

        if (newX <= 0 || newX >= mapWidth || newY <= 0 || newY >= mapHeight)
            return false;

        return map[newX][newY] == (char) 32 && !isCollision(newX, newY, ships);
    }

    public abstract void shipAttack(ArrayList<Ship> ships);

    public abstract void shipMovement(char map[][], ArrayList<Ship> ships);

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

    public Integer getId() {
        return id;
    }

    public abstract Integer getHEALTH();

    protected abstract void recieveAttack(Integer damage);

}