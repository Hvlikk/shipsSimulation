import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PirateShip extends Ship{
    private final static Integer HEALTH = 1;
    private final static Integer MOVEMENT = 2;
    private final static Integer ACCURACY = 50;
    private final static Integer CANNON_DAMAGE = 2;
    public PirateShip(Integer posX, Integer posY, Integer id, String direction)
    {
        super(posX, posY, id, direction);
        setRandomDirection();
    }

    private void setRandomDirection()
    {
        ArrayList<String> directions = new ArrayList<>();
        directions.add("North");
        directions.add("South");
        directions.add("East");
        directions.add("West");
        directions.add("North-East");
        directions.add("North-West");
        directions.add("South-West");
        directions.add("South-East");
        Random random = new Random();
        this.direction = directions.get(random.nextInt(directions.size()));
    }

    public List<String> getAvailableDirections() {
        List<String> availableDirections = new ArrayList<>();
        availableDirections.add("North");
        availableDirections.add("South");
        availableDirections.add("East");
        availableDirections.add("West");
        availableDirections.add("North-East");
        availableDirections.add("North-West");
        availableDirections.add("South-West");
        availableDirections.add("South-East");
        availableDirections.remove(getDirection());
        return availableDirections;
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
                case "North-East" -> {
                    newX += MOVEMENT;
                    newY += MOVEMENT;
                }
                case "North-West" -> {
                    newX -= MOVEMENT;
                    newY += MOVEMENT;
                }
                case "South-East" -> {
                    newX += MOVEMENT;
                    newY -= MOVEMENT;
                }
                case "South-West" -> {
                    newX -= MOVEMENT;
                    newY -= MOVEMENT;
                }
            }

            if (isValidMove(newX, newY, map, ships)) {
                map[getPosY()][getPosX()] = 0;
                setPosX(newX);
                setPosY(newY);
                map[getPosY()][getPosX()] = 1;
                break;
            } else {
                availableDirections.remove(direction);
                if(!availableDirections.isEmpty())
                    direction = availableDirections.get(random.nextInt(availableDirections.size()));
                else return;
            }
        }
    }

    public void shipAttack() {
    }


    public String getDirection()
    {
        return direction;
    }

}