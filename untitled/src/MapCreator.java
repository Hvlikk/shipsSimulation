import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

public class MapCreator {
    private ArrayList<Ship> ships;
    private final Integer map[][];
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
        map = new Integer[mapHeight][mapWidth];
    }

    public Integer[][] createMap(Integer mapHeight, Integer mapWidth){
        for (int i = 0; i < mapHeight; i++)
        {
            for(int j = 0 ; j < mapWidth; j++)
                map[i][j] = 0;
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

    public Integer[][] getMap() {
        return map;
    }



    public void deleteDestroyedShips(Integer id){

    }

    public ArrayList<Ship> createShips(Integer britishShipsStartCount, Integer piratesStartCount, Integer map[][])
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

                if(map[posY][posX] == 0)
                {
                    ships.add(new PirateShip(i, posX, posY, 1, null, 1, 2));
                    map[posY][posX] = 2;
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

                if(map[posY][posX] == 0)
                {
                    ships.add(new BritishShip(i, posX, posY, null, null, null, null));
                    map[posY][posX] = 1;
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
