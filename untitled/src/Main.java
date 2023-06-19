import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Podaj wymiar (X,Y) mapy - mapa to kwadrat: ");
        Scanner in = new Scanner(System.in);
        Integer mapMaxX = in.nextInt() + 2;
        Integer mapMaxY = mapMaxX;
        boolean RunProgram;

        System.out.println("Podaj liczbe piratow :");
        Integer piratesCount = in.nextInt();

        System.out.println("Podaj liczbe statkow brytyjskich: ");
        Integer britishShipsCount = in.nextInt();
        System.out.println("Podaj liczbe gór lodowych: ");
        Integer icebergsCount = in.nextInt();
        System.out.println("Podaj częstotliwość burzy: ");
        Integer turns = in.nextInt();
        System.out.println("Podaj ilosć piorunów w burzy (max 3): ");
        Integer thunders = in.nextInt();

        MapCreator mapCreator = new MapCreator(mapMaxX, mapMaxY, piratesCount, britishShipsCount);
        RunProgram = MapCreator.CheckMap(mapMaxX, piratesCount, britishShipsCount, icebergsCount, thunders, turns);

        if(RunProgram == true) {
            System.out.println("Statki brytyjskie = B, statki piratow = P, góry lodowe = I");
            System.out.println("Podgląd mapy przed rozpoczęciem bitwy:");
            System.out.println("=======================================");

            char[][] map = mapCreator.createMap(mapMaxX, mapMaxY);
            ArrayList<Ship> ships = mapCreator.createShips(britishShipsCount, piratesCount, mapCreator.getMap());
                ArrayList<Iceberg> icebergs = mapCreator.createIcebergs(icebergsCount, mapCreator.getMap());
                BattleSimulator battleSimulator = new BattleSimulator(mapMaxY, mapMaxX, ships, icebergs, map);
                battleSimulator.simulateBattle(turns, thunders);
                in.close();
            }
            else {
                System.out.println("Za duża ilość przedmiotów na mapie. / Za duża ilość piorunów. ");
            }
    }

}