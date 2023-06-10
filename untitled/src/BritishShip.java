import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BritishShip extends Ship{
    protected Integer HEALTH = 2;
    protected final static Integer MOVEMENT = 1;
    protected final static Integer ACCURACY = 75;
    protected final static Integer CANNON_DAMAGE = 1;

    public BritishShip (Integer posX, Integer posY, Integer id, String direction)
    {
        super(posX, posY, id, direction);
        setRandomDirection();
    }

    public List<String> getAvailableDirections() {
        List<String> availableDirections = new ArrayList<>();
        availableDirections.add("North");
        availableDirections.add("South");
        availableDirections.add("East");
        availableDirections.add("West");
        return availableDirections;
    }
    private void setRandomDirection()
    {
        ArrayList<String> directions = new ArrayList<>();
        directions.add("North");
        directions.add("South");
        directions.add("East");
        directions.add("West");
        Random random = new Random();
        this.direction = directions.get(random.nextInt(directions.size()));
    }

    @Override
    public void shipMovement(Integer map[][], ArrayList<Ship> ships) {
        List<String> availableDirections = getAvailableDirections();
        Random random = new Random();

        while (!availableDirections.isEmpty()) {
            Integer newX = getPosX();
            Integer newY = getPosY();

            switch (direction) {
                case "North" -> newY += MOVEMENT;
                case "South" -> newY -= MOVEMENT;
                case "West" -> newX -= MOVEMENT;
                case "East" -> newX += MOVEMENT;
            }

            if (isValidMove(newX, newY, map, ships)) {
                map[getPosY()][getPosX()] = 0;
                setPosX(newX);
                setPosY(newY);
                map[getPosY()][getPosX()] = 1;
                break;
            } else {
                availableDirections.remove(direction);
                if (!availableDirections.isEmpty())
                    direction = availableDirections.get(random.nextInt(availableDirections.size()));
                else return;
            }
        }
    }



    @Override
    public void shipAttack(ArrayList<Ship> ships) {
        Random random = new Random();
        ArrayList<Ship> targets = getShipsInRange(ships, 1);
        if (targets.isEmpty())
            return;

        Ship targetShip = targets.get(random.nextInt(targets.size()));
        Integer DAMAGE = calculateDamage();
        System.out.println("Statek" + getId() + "zapierdolił statek" + targetShip.getId() + "i wyjebał mu za" + DAMAGE);
        targetShip.recieveAttack(DAMAGE);
    }

    private ArrayList<Ship> getShipsInRange(ArrayList<Ship> ships, Integer range){
        ArrayList<Ship> targets = new ArrayList<>();
        for (Ship ship : ships)
        {
            if (ship != this && ship instanceof PirateShip)
            {
                Integer distanceX = Math.abs(ship.getPosX() - getPosX());
                Integer distanceY = Math.abs(ship.getPosY() - getPosY());
                if(distanceY <= range && distanceX <= range)
                    targets.add(ship);
            }
        }
        return targets;
    }


    public void recieveAttack(Integer DAMAGE) {
        HEALTH -= DAMAGE;
    }

    public Integer calculateDamage(){

        Random random = new Random();
        Integer isHit = random.nextInt(101);
        if (isHit <= ACCURACY)
            return CANNON_DAMAGE;
        else return 0;
    }

    public Integer getHEALTH() {
        return HEALTH;
    }
}
