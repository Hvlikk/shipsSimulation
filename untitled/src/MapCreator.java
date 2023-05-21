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

    public Integer[][] createMap(){
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

    public void createShips(Integer piratesStartCount, Integer britishShipsStartCount)
    {
        Random random = new Random();
        for (int i = 1; i <= britishShipsStartCount; i++)
        {
            ;
        }

    }
}
