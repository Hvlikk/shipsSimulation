import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class MapCreator {
    private ArrayList<Ship> ships;
    private final char map[][];
    private final Integer mapWidth;
    private final Integer mapHeight;
    private Integer piratesStartCount;
    private Integer britishShipsStartCount;

    public MapCreator(Integer mapWidth, Integer mapHeight, Integer piratesStartCount, Integer britishShipsStartCount) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.piratesStartCount = piratesStartCount;
        this.britishShipsStartCount = britishShipsStartCount;
        ships = new ArrayList<>();
        map = new char[mapHeight][mapWidth];
    }

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


    public void showMap()
    {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++)
                System.out.print(map[i][j]);
            System.out.println(" ");
        }
    }

    public char[][] getMap() {
        return map;
    }



    public void deleteDestroyedShips(Integer id){

    }

    public ArrayList<Ship> createShips(Integer britishShipsStartCount, Integer piratesStartCount, char map[][])
    {
        Random random = new Random();
        for (int i = 1; i <= piratesStartCount; i++)
        {
            Integer posX;
            Integer posY;
            boolean shipCreated = false;

            while (!shipCreated){
                posX = random.nextInt(map[0].length);
                posY = random.nextInt(map.length);

                if(map[posY][posX] == (char) 32)
                {
                    ships.add(new PirateShip(posX, posY, i, null));
                    map[posY][posX] = 'P';
                    shipCreated = true;
                }
            }
        }

        for (int i = 1; i <= britishShipsStartCount; i++)
        {
            Integer posX;
            Integer posY;
            boolean shipCreated = false;

            while (!shipCreated){
                posX = random.nextInt(map[0].length);
                posY = random.nextInt(map.length);

                if(map[posY][posX] == (char) 32)
                {
                    ships.add(new BritishShip(posX, posY, i, null));
                    map[posY][posX] = 'B';
                    shipCreated = true;
                }
            }
        }
        return ships;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}
