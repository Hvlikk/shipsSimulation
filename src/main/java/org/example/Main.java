package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description of Main
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Podaj wymiar (X,Y) mapy - mapa to kwadrat: ");
        Scanner in = new Scanner(System.in);
        Integer mapMaxX = in.nextInt() + 2;
        Integer mapMaxY = mapMaxX;
        Float thunder = (float) (mapMaxX / 3);
        boolean CheckMap;
        boolean CheckThunders;
        boolean CheckTurns;
        boolean RunProgram;

        Integer thunders = 0;

        System.out.println("Podaj liczbe piratow :");
        Integer piratesCount = in.nextInt();
        System.out.println("Podaj liczbe statkow brytyjskich: ");
        Integer britishShipsCount = in.nextInt();
        System.out.println("Podaj liczbe gór lodowych: ");
        Integer icebergsCount = in.nextInt();
        System.out.println("Podaj częstotliwość burzy: (wpisanie 0 wyłącza je w symulacji)");
        Integer turns = in.nextInt();

        if (turns > 0) {
            System.out.println("Podaj ilosć piorunów w burzy (max " + thunder.intValue() + "):");
            thunders = in.nextInt();
        }


        MapCreator mapCreator = new MapCreator(mapMaxX, mapMaxY, piratesCount, britishShipsCount);
        CheckMap = MapCreator.checkMap(mapMaxX, piratesCount, britishShipsCount, icebergsCount);
        CheckThunders = MapCreator.checkThunders(thunders, thunder, turns);
        CheckTurns = MapCreator.checkTurns(turns);
        RunProgram = MapCreator.displayBugs(CheckMap, CheckThunders, CheckTurns);

        if(RunProgram == true) {
            System.out.println("Statki brytyjskie = B, statki piratow = P, góry lodowe = I");
            System.out.println("Podgląd mapy przed rozpoczęciem bitwy:");
            System.out.println("=======================================");

            char[][] map = mapCreator.createMap(mapMaxX, mapMaxY);
            ArrayList<Ship> ships = mapCreator.createShips(britishShipsCount, piratesCount, mapCreator.getMap());
            ArrayList<Iceberg> icebergs = mapCreator.createIcebergs(icebergsCount, mapCreator.getMap());
            BattleSimulator battleSimulator = new BattleSimulator(mapMaxY, mapMaxX, ships, icebergs, map, britishShipsCount, piratesCount);
            battleSimulator.showMap();
            battleSimulator.simulateBattle(turns, thunders);
            in.close();
        }

    }

}