import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class BattleSimulator {
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_GREEN = "\u001B[32m";
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
        if (turns > 0) {
            Random random = new Random();
            if (turnCount % turns == 0) {
                for (int i = 0; i < thunders; i++) {
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
    }


    public void printCasualities(ArrayList<Ship> ships){
        for (Ship ship: ships)
        {
            if (ship.getHEALTH() <= 0)
            {
                System.out.println("Statek " + ship.getName() + " " + ship.getId() + " został zatopiony.");
            }
        }
    }


    public void simulateBattle(Integer turns, Integer thunders) throws IOException {
        Boolean battleInProgress = true;
        while(battleInProgress) {
            TurnCount++;
            System.out.println("==========================================================");
            System.out.println("ROUND NUMBER: " + COLOR_RED + TurnCount + COLOR_RESET);
            System.out.println("==========================================================");

            for (Ship ship : ships) {
                ship.shipMovement(map, ships);
            }
            showMap();
            for (Ship ship : ships) {
                ship.shipAttack(ships);
            }
            System.out.println("=======" + COLOR_GREEN + " *BATTLE LOG* " + COLOR_RESET + "=======");
            Thunderstorm(turns, thunders, TurnCount, map);
            printCasualities(ships);
            removeDestroyedShips(map);

            if (checkEndingCondition()) {
                battleInProgress = false;
            }
            System.out.println("=======" + COLOR_GREEN + " *END OF TURN* " + COLOR_RESET + "=======");
            System.out.println(COLOR_GREEN + "==========================================================" + COLOR_RESET);
            printDataToFile(TurnCount, map, ships);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
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

    public void printDataToFile(Integer turnCount, char map[][], ArrayList<Ship> ships) throws IOException {
        int britishShipsRemaining = 0;
        int pirateShipsRemaining = 0;

        FileWriter fileWriter = new FileWriter("simulationStats.txt", true);
        BufferedWriter out = new BufferedWriter(fileWriter);
        try {
            for (Ship ship : ships) {
                if (ship instanceof BritishShip) {
                    britishShipsRemaining++;
                } else if (ship instanceof PirateShip) {
                    pirateShipsRemaining++;
                }
            }
            out.write("Turn number: " + turnCount + "\n");
            out.write("British ships remaining: " + britishShipsRemaining + " Pirate ships remaining: " + pirateShipsRemaining + "\n");
            out.write("Map status: \n");
            for (int i = 0; i < mapHeight; i++) {
                for (int j = 0; j < mapWidth; j++)
                    out.write(map[i][j]);
                out.write("\n");
            }
        }
        catch (IOException e){
            return;
        }
        out.close();
    }
}
