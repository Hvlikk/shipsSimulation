public class MapCreator {

    private int map[][];

    public MapCreator(Integer maxWidth, Integer maxHeight){
        this.map = new int[maxWidth][maxHeight];
    }

    public int[][] getMap() {
        return map;
    }
}
