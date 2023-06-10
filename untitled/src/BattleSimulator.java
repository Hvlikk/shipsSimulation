import java.util.ArrayList;
public class BattleSimulator {

    private final Integer mapHeight;
    private final Integer mapWidth;
    private Integer map[][];
    private ArrayList<Ship> ships;


    public BattleSimulator(Integer mapWidth, Integer mapHeight, ArrayList<Ship> ships, Integer map[][]) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.ships = ships;
        this.map = map;
    }


    public void simulateBattle() {
        for (int k = 0; k < 5; k++) {
            for (Ship ship : ships) {
                ship.shipMovement(map, ships);
                ship.shipAttack(ships);
            }


            for (int i = 0; i < mapHeight; i++) {
                for (int j = 0; j < mapWidth; j++)
                    System.out.print(map[i][j]);
                System.out.println(" ");


            }
            System.out.println("========");
        }

    }
}
