import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj wymiary mapy (X,Y): ");
        Scanner in = new Scanner(System.in);
        Integer mapMaxX = in.nextInt();
        Integer mapMaxY = in.nextInt();
        MapCreator map = new MapCreator(mapMaxX, mapMaxY);

        System.out.println("Podaj liczbe piratow :");
        Integer piratesCount = in.nextInt();

        System.out.println("Podaj liczbe statkow brytyjskich: ");
        Integer britishShipsCount = in.nextInt();

    }
}