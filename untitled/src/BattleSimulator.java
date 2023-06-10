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

    private Boolean checkEndingCondition(){
        Boolean britishShipsRemaining = false;
        Boolean piratesShipRemaining = false;

        for (Ship ship : ships){
            if (ship instanceof BritishShip) {
                britishShipsRemaining = true;
            }
            else if (ship instanceof  PirateShip) {
                piratesShipRemaining = true;
            }

            if (britishShipsRemaining && piratesShipRemaining) {
                return false;
            }
        }
        return true;
    }

    public void simulateBattle() {
        Boolean battleInProgress = true;
        int k = 0;
        while(battleInProgress){

            k += 1;
            System.out.println("================");
            System.out.println("Tura numer: " + k);
            System.out.println("================");
            showMap();

            for (Ship ship : ships) {
                ship.shipMovement(map, ships);
                ship.shipAttack(ships);
            }
            removeDestroyedShips(map);

            if(checkEndingCondition()){
                battleInProgress = false;
            }
            System.out.println("================");
            System.out.println("KONIEC TURY");
        }

        displaySimulationResult();
    }

    public void displaySimulationResult()
    {
        System.out.println("=================================");
        System.out.println("PODGLĄD MAPY PO ZAKOŃCZENIU BITWY");
        System.out.println("=================================");
        showMap();
        System.out.println("=================================");
        int britishShipsRemaining = 0;
        int pirateShipsRemaining = 0;

        for (Ship ship : ships) {
            if (ship instanceof BritishShip) {
                britishShipsRemaining++;
            } else if (ship instanceof PirateShip) {
                pirateShipsRemaining++;
            }
        }
        System.out.println("Wynik symulacji:");
        System.out.println("Statki brytyjskie pozostałe: " + britishShipsRemaining);
        System.out.println("Statki pirackie pozostałe: " + pirateShipsRemaining);

        if (britishShipsRemaining > pirateShipsRemaining) {
            System.out.println("Zwycięstwo brytyjczyków!");
        } else {
            System.out.println("Zwycięstwo piratów!");
        }
    }
}
