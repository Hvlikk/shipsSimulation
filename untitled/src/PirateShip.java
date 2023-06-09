import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

///Pirates movement = 2;
//Pirates accuracy = 50%


public class PirateShip extends Ship{

    public PirateShip(Integer id, Integer posX, Integer posY, Integer attack, String direction, Integer hp, Integer movement)
    {
        super(id, posX, posY, attack, direction, hp, movement);
        this.hp = 1;
        this.direction = direction;
        this.attack = 1;
        this.movement = 2;
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

    @Override
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
        Integer newX = getPosX();
        Integer newY = getPosY();
        List<String> availableDirections = getAvailableDirections();
        Random random = new Random();

        switch (direction) {
            case "North" -> newY += movement;
            case "South" -> newY -= movement;
            case "West" -> newX -= movement;
            case "East" -> newX += movement;
            case "North-East" -> {
                newX += movement;
                newY += movement;
            }
            case "North-West" -> {
                newX -= movement;
                newY += movement;
            }
            case "South-East" -> {
                newX += movement;
                newY -= movement;
            }
            case "South-West" -> {
                newX -= movement;
                newY -= movement;
            }
        }

            if (isValidMove(newX, newY, map, ships)) {
                map[getPosY()][getPosX()] = 0;
                setPosX(newX);
                setPosY(newY);
                map[getPosY()][getPosX()] = 2;
            } else {
                availableDirections.remove(direction);
                if (!availableDirections.isEmpty()) {
                    direction = availableDirections.get(random.nextInt(availableDirections.size()));
                }
                shipMovement(map, ships);
            }

        }
}