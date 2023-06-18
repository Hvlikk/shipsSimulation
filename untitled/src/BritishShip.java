import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BritishShip extends Ship{
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_GREEN = "\u001B[32m";
    protected Integer HEALTH = 2;
    protected final static Integer MOVEMENT = 1;
    protected final static Integer ACCURACY = 75;
    protected final static Integer CANNON_DAMAGE = 1;

    public BritishShip (Integer posX, Integer posY, Integer id, String direction, Integer MoveSpeed)
    {
        super(posX, posY, id, direction, MoveSpeed);
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
    public void shipMovement(char map[][], ArrayList<Ship> ships) {
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
                map[getPosY()][getPosX()] = (char) 32;
                setPosX(newX);
                setPosY(newY);
                map[getPosY()][getPosX()] = 'B';
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
        if (HEALTH <= 0)
            return;

        Random random = new Random();
        ArrayList<Ship> targets = getShipsInRange(ships, 1);
        if (targets.isEmpty())
            return;

        Ship targetShip = targets.get(random.nextInt(targets.size()));
        if (targetShip.getHEALTH() > 0) {
            Integer DAMAGE = calculateDamage();
            targetShip.recieveAttack(DAMAGE);
            System.out.println(COLOR_BLUE + "Statek brytyjski " + COLOR_GREEN + getId() + COLOR_RESET + " zaatakował" + COLOR_RED + " statek piratów " + COLOR_GREEN + targetShip.getId() + COLOR_RESET + " i zadał mu " + COLOR_GREEN + DAMAGE + COLOR_RESET + " obrażeń.");
            System.out.println(COLOR_RED + "HP statku piratów po ataku: " + COLOR_GREEN + targetShip.getHEALTH() + COLOR_RESET);
        }
        {
            System.out.println("Statek piracki" +
                    " " + targetShip.getId() + " został zestrzelony.");
        }
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

    @Override
    public String getName()
    {
        String name = "brytyjski";
        return name;
    }

    public Integer calculateDamage(){

        Random random = new Random();
        Integer isHit = random.nextInt(101);
        if (isHit <= ACCURACY)
            return CANNON_DAMAGE;
        else return 0;
    }

    public Integer getHEALTH(){
        return HEALTH;
    }
}
