import java.lang.reflect.Array;
import java.util.ArrayList;
public class BattleSimulator {

    private final Integer mapHeight;
    private final Integer mapWidth;
    private char map[][];
    private ArrayList<Ship> ships;


    public BattleSimulator(Integer mapWidth, Integer mapHeight, ArrayList<Ship> ships, char map[][]) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.ships = ships;
        this.map = map;
    }

    public void removeDestroyedShips(char[][] map){
        ArrayList<Ship> destroyedShips = new ArrayList<>();

        for (Ship ship: ships){
            if (ship.getHEALTH() <= 0) {
                destroyedShips.add(ship);
                map[ship.getPosY()][ship.getPosX()] = ' ';
            }
        }
        ships.removeAll(destroyedShips);
    }

    public void showMap() {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++)
                System.out.print(map[i][j]);
            System.out.println(" ");


        }
        System.out.println("========");
    }

    public void simulateBattle() {
        for (int k = 0; k < 10; k++) {
            System.out.println("================");
            System.out.println("Tura numer: " + k);
            System.out.println("================");

            showMap();

            for (Ship ship : ships) {
                ship.shipMovement(map, ships);
                ship.shipAttack(ships);
            }

            removeDestroyedShips(map);

            System.out.println("================");
            System.out.println("KONIEC TURY");
        }
    }
}
