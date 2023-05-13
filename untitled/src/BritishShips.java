public class BritishShips {

    private String flag;

    public BritishShips (Integer posX, Integer posY, Integer attack, String direction, String flag)
    {
        super(posX, posY, attack, direction);
        this.flag = "BRITISH";
    }
}
