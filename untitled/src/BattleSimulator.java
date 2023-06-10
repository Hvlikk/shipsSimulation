import java.lang.reflect.Array;
import java.util.ArrayList;
public class BattleSimulator {
    private final Integer mapHeight;
    private final Integer mapWidth;
    private char map[][];
    private ArrayList<Ship> ships;
    public Integer TurnCount = 0;


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
        while(battleInProgress){
            TurnCount++;
            System.out.println("================");
            System.out.println("Tura numer: " + TurnCount);
            System.out.println("================");


            for (Ship ship : ships) {
                ship.shipMovement(map, ships);
            }
            showMap();
            for (Ship ship : ships) {
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
        System.out.println("==================================");
        System.out.println("MAPA PO ZAKOŃCZENIU BITWY");
        System.out.println("==================================");
        showMap();

        if (britishShipsRemaining == 0) {
            System.out.println("Zwycięstwo piratów!");
        } else {
            System.out.println("Zwycięstwo brytyjczyków!");
        }
        System.out.println("Ilość tur: " + TurnCount);
    }
}
