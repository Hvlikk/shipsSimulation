package org.example;

import java.util.Random;
import java.util.ArrayList;

/**
 * Description of MapCreator
 */
public class MapCreator {
    private ArrayList<Ship> ships;
    private ArrayList<Iceberg> icebergs;
    private final char map[][];
    private final Integer mapWidth;
    private final Integer mapHeight;
    private Integer piratesStartCount;
    private Integer britishShipsStartCount;

    public MapCreator(Integer mapHeight, Integer mapWidth, Integer piratesStartCount, Integer britishShipsStartCount) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.piratesStartCount = piratesStartCount;
        this.britishShipsStartCount = britishShipsStartCount;
        icebergs = new ArrayList<>();
        ships = new ArrayList<>();
        map = new char[mapHeight][mapWidth];
    }

    /**
     * This method creates map, which is two-dimensional char array
     * @param mapHeight
     * @param mapWidth
     * @return
     */
    public char[][] createMap(Integer mapHeight, Integer mapWidth){
        for (int i = 0; i < mapHeight; i++)
        {
            for(int j = 0 ; j < mapWidth; j++)
                map[i][j] = (char) 32;

            for(int j = 0 ; j < mapWidth; j++)
            {
                map[j][0] = '*';
                map[j][mapWidth-1] = '*';
                map[0][j] = '*';
                map[mapHeight-1][j] = '*';
            }
        }
        return map;
    }

    /**
     * map getter
     * @return
     */
    public char[][] getMap() {
        return map;
    }


    /**
     * Method creates ArrayList of icebergs
     * @param icebergCount
     * @param map
     * @return
     */
    public ArrayList<Iceberg> createIcebergs(Integer icebergCount, char map[][]) {
        Random random = new Random();
        for (int i = 1; i <= icebergCount; i++)
        {
            Integer posX;
            Integer posY;
            boolean icebergCreated = false;

            while (!icebergCreated){
                posX = random.nextInt(map[0].length);
                posY = random.nextInt(map.length);

                if(map[posY][posX] == (char) 32)
                {
                    icebergs.add(new Iceberg(i, posX, posY));
                    map[posY][posX] = 'I';
                    icebergCreated = true;
                }
            }
        }
        return icebergs;
    }

    /**
     * Method creates ArrayList of ships
     * @param britishShipsStartCount
     * @param piratesStartCount
     * @param map
     * @return
     */
    public ArrayList<Ship> createShips(Integer britishShipsStartCount, Integer piratesStartCount, char map[][])
    {
        Random random = new Random();
        for (int i = 1; i <= piratesStartCount; i++)
        {
            Integer posX;
            Integer posY;
            Integer MoveSpeed;
            boolean shipCreated = false;

            while (!shipCreated){
                posX = random.nextInt(map[0].length);
                posY = random.nextInt(map.length);
                MoveSpeed = random.nextInt(301);

                if(map[posY][posX] == (char) 32)
                {
                    ships.add(new PirateShip(posX, posY, i, null, MoveSpeed));
                    map[posY][posX] = 'P';
                    shipCreated = true;
                }
            }
        }

        for (int i = 1; i <= britishShipsStartCount; i++)
        {
            Integer posX;
            Integer posY;
            Integer MoveSpeed;
            boolean shipCreated = false;

            while (!shipCreated){
                posX = random.nextInt(map[0].length);
                posY = random.nextInt(map.length);
                MoveSpeed = random.nextInt(201);

                if(map[posY][posX] == (char) 32)
                {
                    ships.add(new BritishShip(posX, posY, i, null, MoveSpeed));
                    map[posY][posX] = 'B';
                    shipCreated = true;
                }
            }
        }
        Ship.sortShipsByMoveSpeed(ships);

        return ships;
    }

    /**
     * Method checks if the number of objects is not larger than the map size
     * @param mapMaxX
     * @param PiratesCount
     * @param britishShipsCount
     * @param IcebergCount
     * @return
     */
    public static boolean checkMap (Integer mapMaxX, Integer PiratesCount, Integer britishShipsCount , Integer IcebergCount)
    {
        Integer mapsize;
        mapsize = (mapMaxX-2) * (mapMaxX-2);
        Integer NumberOfObjects;
        NumberOfObjects = britishShipsCount + PiratesCount + IcebergCount;
        if (mapsize < NumberOfObjects)
            return false;
        else return true;
    }

    /**
     * Method checks if the amount of thunders is allowed
     * @param thunders
     * @param thunder
     * @param turns
     * @return
     */
    public static boolean checkThunders (Integer thunders, Float thunder, Integer turns){
        if (thunders > thunder)
            return false;

        if (turns > 0 && thunders < 1)
            return false;
        else return true;
    }

    /**
     * Method checks for negative number of turns
     * @param turn
     * @return
     */
    public static boolean checkTurns (Integer turn)
    {
        if (turn < 0)
            return false;
        else return true;

    }

    /**
     * Method displays messages if any of starting requirements are not met
     * @param map
     * @param storm
     * @param turns
     * @return
     */
    public static Boolean displayBugs(boolean map, boolean storm, boolean turns){
        int bugCount = 0;
        if (map == false)
        {
            System.out.println("Za dużo obiektów na mapie.");
            bugCount++;
        }
        if (storm == false)
        {
            System.out.println("Ilość błyskawic jest mniejsza od 1 lub jest za wysoka. ");
            bugCount++;
        }
        if (turns == false)
        {
            System.out.println("Negatywna wartość parametru turns. ");
            bugCount++;
        }
        if (bugCount > 0)
        {
            System.out.println("Ilość niespełnionych warunków startu: " + bugCount);
            return false;
        }
        return true;
    }

}
