public abstract class Ship {
    private Integer posX;
    private Integer posY;
    private Integer attack;
    private String direction;

    public Ship (Integer posX, Integer posY, Integer attack, String direction){
        this.posX = posX;
        this.posY = posY;
        this.attack = attack;
        this.direction = direction;
    }
}