import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BritishShip extends Ship{
    private final static Integer HEALTH = 2;
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
    public void shipMovement(Integer map[][], ArrayList<Ship> ships)
    {

        switch (direction) {
            case "North" -> posY += MOVEMENT;
            case "South" -> posY -= MOVEMENT;
            case "West" -> posX -= MOVEMENT;
            default -> posX += MOVEMENT;
        }

    }

    @Override
    public void shipAttack() {

    }
}
