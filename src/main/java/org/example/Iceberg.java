package org.example;

public class Iceberg {
    private Integer id;
    private Integer posX;
    private Integer posY;

    public Iceberg(Integer id, Integer posX, Integer posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {return posX; }
    public Integer getPosY() {return posY; }

}
