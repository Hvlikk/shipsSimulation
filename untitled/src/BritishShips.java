import java.util.Random;

public class BritishShips extends Ship{

    private final String flag;
    public BritishShips (Integer posX, Integer posY, Integer attack, String direction, Integer movement, Integer id, String flag)
    {
        super(posX, posY, attack, direction, null, id);
        this.movement = 1;
        this.flag = "BRITISH";
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
