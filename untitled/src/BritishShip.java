import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BritishShip extends Ship {

    public BritishShip(Integer id, Integer posX, Integer posY, Integer attack, String direction, Integer hp, Integer movement) {
        super(id, posX, posY, attack, direction, 2, 1);
        this.movement = 1;
        setRandomDirection();
    }

    private void setRandomDirection() {
        String[] directions = {"North", "East", "West", "South"};
        Random random = new Random();
        int index = random.nextInt(directions.length);
        direction = directions[index];
    }

    public List<String> getAvailableDirections() {
        List<String> availableDirections = new ArrayList<>();
        availableDirections.add("North");
        availableDirections.add("South");
        availableDirections.add("East");
        availableDirections.add("West");
        return availableDirections;
    }

    @Override
    public void shipMovement(Integer map[][], ArrayList<Ship> ships) {

            Integer newX = getPosX();
            Integer newY = getPosY();
            List<String> availableDirections = getAvailableDirections();
            Random random = new Random();

            switch (direction) {
                case "North" -> newY += movement;
                case "South" -> newY -= movement;
                case "West" -> newX -= movement;
                case "East" -> newX += movement;

            }

            if (isValidMove(newX, newY, map, ships)) {
                map[getPosY()][getPosX()] = 0;
                setPosX(newX);
                setPosY(newY);
                map[getPosY()][getPosX()] = 1;
            } else {
                availableDirections.remove(direction);
                if (!availableDirections.isEmpty()) {
                    direction = availableDirections.get(random.nextInt(availableDirections.size()));
                }
                shipMovement(map, ships);
            }
        }
}

