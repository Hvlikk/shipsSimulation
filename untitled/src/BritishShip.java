import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BritishShip extends Ship{
    private Integer HEALTH = 2;
    private final static Integer MOVEMENT = 1;
    private final static Integer ACCURACY = 75;
    private final static Integer CANNON_DAMAGE = 1;

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



    public void shipAttack() {
    }

}
