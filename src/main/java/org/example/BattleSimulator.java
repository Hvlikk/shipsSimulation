package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Description of BattleSimulator
 */
public class BattleSimulator {
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private final Integer mapHeight;
    private final Integer mapWidth;
    private char map[][];
    private ArrayList<Ship> ships;
    private final ArrayList<Iceberg> icebergs;
    public Integer TurnCount = 0;
    private final Integer startingBritishCount;
    private final Integer startingPiratesCount;

    public BattleSimulator(Integer mapWidth, Integer mapHeight, ArrayList<Ship> ships, ArrayList<Iceberg> icebergs, char map[][], Integer startingBritishCount, Integer startingPiratesCount) {
        this.icebergs = icebergs;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.ships = ships;
        this.map = map;
        this.startingBritishCount = startingBritishCount;
        this.startingPiratesCount = startingPiratesCount;
    }

    /**
     * <var>
     *     This method deletes ships after each turn, which health points are equal to 0
     * </var>
     * @param map
     */
    public void removeDestroyedShips(char[][] map) {
        ArrayList<Ship> destroyedShips = new ArrayList<>();

        for (Ship ship : ships) {
            if (ship.getHEALTH() <= 0) {
                destroyedShips.add(ship);
                map[ship.getPosY()][ship.getPosX()] = ' ';
            }
        }
        ships.removeAll(destroyedShips);
    }

    /**
     * This method prints map in console.
     */
    public void showMap() {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++)
                System.out.print(map[i][j]);
            System.out.println(" ");
        }
    }

    /**
     * <var>
     *    Method is checking ending conditions of the simulation
     * </var>
     *
     *
     * @return
     */
    private Boolean checkEndingCondition() {
        Boolean britishShipsRemaining = false;
        Boolean piratesShipRemaining = false;

        for (Ship ship : ships) {
            if (ship instanceof BritishShip) {
                britishShipsRemaining = true;
            } else if (ship instanceof PirateShip) {
                piratesShipRemaining = true;
            }

            if (britishShipsRemaining && piratesShipRemaining) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Needs to be  filled
     *
     * @param turns
     * @param thunders
     * @param turnCount
     * @param map
     * @return
     */
    public Integer thunderstorm(Integer turns, Integer thunders, Integer turnCount, char map[][]) {
        Integer stormcount = 0;
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
                                stormcount++;
                            }
                        }
                    }
                }
            }
        }
        return stormcount;
    }


    /**
     * Method prints ships that were destroyed in last round.
     * @param ships
     */
    public void printCasualities(ArrayList<Ship> ships) {
        for (Ship ship : ships) {
            if (ship.getHEALTH() <= 0) {
                System.out.println("Statek " + ship.getName() + " " + ship.getId() + " został zatopiony.");
            }
        }
    }

    /**
     *
     * @param turns
     * @param thunders
     */
    public void simulateBattle(Integer turns, Integer thunders) {
        Integer stormcount = 0;
        try {
            checkIfFileExist();
            Boolean battleInProgress = true;
            while (battleInProgress) {
                TurnCount++;
                System.out.println("==========================================================");
                System.out.println("ROUND NUMBER: " + COLOR_RED + TurnCount + COLOR_RESET);
                System.out.println("==========================================================");

                for (Ship ship : ships) {
                    ship.shipMovement(map, ships);
                }
                showMap();
                for (Ship ship: ships){
                    ship.shipAttack(ships);
                }
                System.out.println("=======" + COLOR_GREEN + " *BATTLE LOG* " + COLOR_RESET + "=======");
                stormcount += thunderstorm(turns, thunders, TurnCount, map);
                printCasualities(ships);
                removeDestroyedShips(map);

                if (checkEndingCondition()) {
                    battleInProgress = false;
                }
                System.out.println("=======" + COLOR_GREEN + " *END OF TURN* " + COLOR_RESET + "=======");
                System.out.println(COLOR_GREEN + "==========================================================" + COLOR_RESET);
                printDataToFile(battleInProgress, TurnCount, map, ships, startingBritishCount, startingPiratesCount, stormcount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        displaySimulationResult(stormcount);
    }

    /**
     * Method is responsible for printing results of the simulation.
     * @param stormcount
     */
    public void displaySimulationResult(Integer stormcount)
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
        System.out.println("MAPA PO ZAKONCZENIU BITWY");
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
        System.out.println("Ilość statków zatopiona przez burzę: " + stormcount);
    }

    /**
     * Method prints collected data to .txt file
     * @param battleInProgress
     * @param turnCount
     * @param map
     * @param ships
     * @param startingBritishCount
     * @param startingPiratesCount
     * @param stormcount
     * @throws IOException
     */
    public void printDataToFile(Boolean battleInProgress, Integer turnCount, char map[][], ArrayList<Ship> ships, Integer startingBritishCount, Integer startingPiratesCount,Integer stormcount) throws IOException {
        int britishShipsRemaining = 0;
        int pirateShipsRemaining = 0;
        int pirateCasualties = 0;
        int britishCasualties = 0;
        FileWriter fileWriter = new FileWriter("simulationStats.txt", true);
        BufferedWriter out = new BufferedWriter(fileWriter);
        if(battleInProgress) {
            try {
                for (Ship ship : ships) {
                    if (ship instanceof BritishShip) {
                        britishShipsRemaining++;
                    } else if (ship instanceof PirateShip) {
                        pirateShipsRemaining++;
                    }
                }
                pirateCasualties = startingPiratesCount - pirateShipsRemaining;
                britishCasualties = startingBritishCount - britishShipsRemaining;
                out.write("Turn number: " + turnCount + "\n");
                out.write("British ships remaining: " + britishShipsRemaining + " Pirate ships remaining: " + pirateShipsRemaining + "\n");
                out.write("British ships destroyed: " + britishCasualties + " Pirate ships destroyed: " + pirateCasualties + "\n");
                out.write("Map status: \n");
                for (int i = 0; i < mapHeight; i++) {
                    for (int j = 0; j < mapWidth; j++)
                        out.write(map[i][j]);
                    out.write("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        else {
            try {
                for (Ship ship : ships) {
                    if (ship instanceof BritishShip) {
                        britishShipsRemaining++;
                    } else if (ship instanceof PirateShip) {
                        pirateShipsRemaining++;
                    }
                }
                out.write("Wynik symulacji:\n");
                out.write("Statki brytyjskie pozostałe: " + britishShipsRemaining + "\n");
                out.write("Statki pirackie pozostałe: " + pirateShipsRemaining + "\n");
                out.write("Statki zatopione przez burzę: " + stormcount + "\n");

                out.write("==================================\n");
                out.write("MAPA PO ZAKONCZENIU BITWY\n");
                out.write("==================================\n");

                for (int i = 0; i < mapHeight; i++) {
                    for (int j = 0; j < mapWidth; j++)
                        out.write(map[i][j]);
                    out.write("\n");
                }

                if (britishShipsRemaining == 0 && pirateShipsRemaining == 0)
                    out.write("Wszystkie statki zostały zatopione przez burzę. \n");

                else if (britishShipsRemaining == 0) {
                    out.write("Zwycięstwo piratów!\n");
                } else {
                    out.write("Zwycięstwo brytyjczyków!\n");
                }
                out.write("Całkowita ilość tur: " + TurnCount + "\n");
            }
            catch (IOException e){
                throw new RuntimeException();
            }

        }
        out.close();
    }

    /**
     * Method checks if .txt file for data already exists, if there is one it deletes it.
     */
    public void checkIfFileExist()
    {
        File file = new File("simulationStats.txt");
        if (file.exists())
            file.delete();
    }
}

