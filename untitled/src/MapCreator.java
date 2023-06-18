import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;

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

    public char[][] getMap() {
        return map;
    }

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
        public static boolean CheckMap (Integer mapMaxX, Integer PiratesCount, Integer britishShipsCount , Integer IcebergCount, Integer thunders)
        {
            if (thunders > 3)
                return false;

            Integer mapsize;
            mapsize = (mapMaxX-2) * (mapMaxX-2);
            Integer NumberOfObjects;
            NumberOfObjects = britishShipsCount + PiratesCount + IcebergCount;
            if (mapsize < NumberOfObjects)
                return false;
            else return true;
        }
}
