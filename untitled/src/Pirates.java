import java.util.Random;

///Pirates movement = 2;
//Pirates accuracy = 50%


public class Pirates extends Ship{

    public Pirates (Integer id, Integer posX, Integer posY, Integer attack, String direction, Integer hp, Integer movement)
    {
        super(id, posX, posY, attack, direction, 1, 2);
        this.movement = 2;
    }

    private void setRandomDirection()
    {
        String[] directions = {"North", "East", "West", "South", "South-East", "South-West", "North-East", "North-West"};
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
            case "East" -> posX += movement;
            case "North-East" -> {
                posX += movement;
                posY += movement;
            }
            case "North-West" -> {
                posX -= movement;
                posY += movement;
            }
            case "South-East" -> {
                posX += movement;
                posY -= movement;
            }
            default -> {
                posX -= movement;
                posY -= movement;
            }
        }
    }
}