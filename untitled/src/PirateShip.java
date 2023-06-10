import java.util.ArrayList;
import java.util.List;
import java.util.Random;

///Pirates movement = 2;
//Pirates accuracy = 50%


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
        return availableDirections;
    }

    @Override
    public void shipMovement(Integer map[][], ArrayList<Ship> ships)
    {
        switch (direction) {
            case "North" -> posY += MOVEMENT;
            case "South" -> posY -= MOVEMENT;
            case "West" -> posX -= MOVEMENT;
            case "East" -> posX += MOVEMENT;
            case "North-East" -> {
                posX += MOVEMENT;
                posY += MOVEMENT;
            }
            case "North-West" -> {
                posX -= MOVEMENT;
                posY += MOVEMENT;
            }
            case "South-East" -> {
                posX += MOVEMENT;
                posY -= MOVEMENT;
            }
            default -> {
                posX -= MOVEMENT;
                posY -= MOVEMENT;
            }
        }
    }
}