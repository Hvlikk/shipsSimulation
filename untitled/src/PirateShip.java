import java.util.ArrayList;
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
        String[] directions = {"North", "East", "West", "South", "South-East", "South-West", "North-East", "North-West"};
        Random random = new Random();
        int index = random.nextInt(directions.length);
        this.direction = directions[index];
    }


    @Override
    public void shipMovement(Integer map[][], ArrayList<Ship> ships)
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