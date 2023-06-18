import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
public class BattleSimulator {
    private final Integer mapHeight;
    private final Integer mapWidth;
    private char map[][];
    private ArrayList<Ship> ships;
    private  final ArrayList<Iceberg> icebergs;
    public Integer TurnCount = 0;


    public BattleSimulator(Integer mapWidth, Integer mapHeight, ArrayList<Ship> ships, ArrayList<Iceberg> icebergs, char map[][]) {
        this.icebergs = icebergs;
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

    public void Thunderstorm (Integer turns, Integer thunders, Integer turnCount, char map[][])
    {
        Random random = new Random();
        if (turnCount % turns == 0) {
            for (int i = 0; i < thunders; i++ ){
            Integer XHit = random.nextInt(map[0].length);
            Integer YHit = random.nextInt(map.length);

                for (Ship ship : ships) {
                    if (ship.getPosX() == XHit && ship.getPosY() == YHit) {
                        if (ship.getHEALTH() > 0) {
                            Integer health = ship.getHEALTH();
                            ship.recieveAttack(health);
                            System.out.println("Burza trafiła statek " + ship.getName() + " " + ship.getId() + " i go zatopiła. ");
                            map[XHit][YHit] = (char) 32;
                        }
                    }
                }
            }
        }
    }

    public void simulateBattle(Integer turns, Integer thunders) {
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
            Thunderstorm(turns,thunders, TurnCount, map);

            removeDestroyedShips(map);

            if(checkEndingCondition()){
                battleInProgress = false;
            }
            System.out.println("================");
            System.out.println("KONIEC TURY");

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
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

        if (britishShipsRemaining == 0 && pirateShipsRemaining == 0)
            System.out.println("Wszystkie statki zostały zatopione przez burzę. ");

            else if (britishShipsRemaining == 0) {
                System.out.println("Zwycięstwo piratów!");
            } else {
            System.out.println("Zwycięstwo brytyjczyków!");
            }
            System.out.println("Ilość tur: " + TurnCount);
    }
}
