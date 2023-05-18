import java.util.Random;
import java.util.ArrayList;

//do dopisania kod tworzący statki, nadający im id i randomowe, unikatowe pozycje na start.
public class MapCreator {
    private final Integer map[][];
    private Integer piratesStartCount;
    private Integer britishShipsStartCount;

    public MapCreator(Integer maxWidth, Integer maxHeight, Integer piratesStartCount, Integer britishShipsStartCount) {
        this.map = new Integer[maxWidth][maxHeight];
        this.piratesStartCount = piratesStartCount;
        this.britishShipsStartCount = britishShipsStartCount;
    }

    public Integer[][] getMap() {
        return map;
    }


}
