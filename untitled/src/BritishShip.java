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
    public void shipMovement()
    {
        switch (direction) {
            case "North" -> posY += movement;
            case "South" -> posY -= movement;
            case "West" -> posX -= movement;
            default -> posX += movement;
        }
    }
}
