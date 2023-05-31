import java.util.ArrayList;
import java.util.Random;

public class BritishShip extends Ship{

    public BritishShip (Integer id, Integer posX, Integer posY, Integer attack, String direction, Integer hp, Integer movement)
    {
        super(id, posX, posY, attack, direction, 2, 1);
        this.movement = 1;
        setRandomDirection();
    }

    private void setRandomDirection()
    {
        String[] directions = {"North", "East", "West", "South"};
        Random random = new Random();
        int index = random.nextInt(directions.length);
        direction = directions[index];
    }

    @Override
    public void shipMovement(Integer map[][], ArrayList<Ship> ships)
    {
        Integer currentX = getPosX();
        Integer currentY = getPosY();
        Integer newX = currentX;
        Integer newY = currentY;

        switch (direction) {
            case "North" -> setPosY(newY += movement);
            case "South" -> setPosY(newY -= movement);
            case "West" -> setPosX(newX -= movement);
            default -> setPosX(newX += movement);
        }

        map[currentY][currentX] = 0;

    }
}
