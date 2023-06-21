package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Ship {
    protected Integer id;
    protected Integer posX;
    protected Integer posY;
    protected String direction;
    public Integer MoveSpeed;


    public Ship (Integer posX, Integer posY, Integer id, String direction, Integer MoveSpeed){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.MoveSpeed = MoveSpeed;
    }

    /**
     * Method checks if there is any collision with other ships or icebergs according to next ship movement
     * @param newX
     * @param newY
     * @param ships
     * @param map
     * @return
     */
    public Boolean isCollision(Integer newX, Integer newY, ArrayList<Ship> ships, char map[][]){
        for (Ship ship : ships){
            if (ship != this && ship.getPosX() == newX && ship.getPosY() == newY)
                return true;
            if (map[newY][newX] == (char) 73)
                return true;
        }
        return false;
    }


    /**
     * Method returns Boolean value which informs if next move is valid
     * @param newX
     * @param newY
     * @param map
     * @param ships
     * @return
     */
    public Boolean isValidMove(Integer newX, Integer newY, char map[][], ArrayList<Ship> ships)
    {
        Integer mapWidth = map[0].length - 1;
        Integer mapHeight = map.length - 1;

        if (newX <= 0 || newX >= mapWidth || newY <= 0 || newY >= mapHeight)
            return false;

        return !isCollision(newX, newY, ships, map);
    }

    /**
     *
     *
     */
    public abstract void shipAttack(ArrayList<Ship> ships);

    /**
     *
     * @param map
     * @param ships
     */
    public abstract void shipMovement(char map[][], ArrayList<Ship> ships);

    /**
     *
     * @param posX
     */
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     *
     * @return
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     *
     * @return
     */
    public abstract String getName();

    /**
     *
     * @return
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     *
     * @param posY
     */
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Integer getMoveSpeed() { return MoveSpeed; }

    /**
     *
     * @param ships
     */
    public static void sortShipsByMoveSpeed(ArrayList<Ship> ships) {
        ships.sort(Comparator.comparingInt(Ship::getMoveSpeed));
    }

    /**
     *
     * @return
     */
    public abstract Integer getHEALTH();

    /**
     *
     * @param damage
     */
    protected abstract void recieveAttack(Integer damage);
}
