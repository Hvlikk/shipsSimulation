import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj wymiary mapy (X,Y): ");
        Scanner in = new Scanner(System.in);
        Integer mapMaxX = in.nextInt();
        Integer mapMaxY = mapMaxX;

        System.out.println("Podaj liczbe piratow :");
        Integer piratesCount = in.nextInt();

        System.out.println("Podaj liczbe statkow brytyjskich: ");
        Integer britishShipsCount = in.nextInt();

        MapCreator mapCreator = new MapCreator(mapMaxX, mapMaxY, piratesCount, britishShipsCount);

        System.out.println("Statki brytyjskie = 1, statki piratow = 2");
        System.out.println("Podgląd mapy przed rozpoczęciem bitwy:");
        System.out.println("=======================================");
        mapCreator.createMap();
        mapCreator.createShips(piratesCount, britishShipsCount, mapCreator.getMap());
        mapCreator.showMap();
        in.close();
    }

}