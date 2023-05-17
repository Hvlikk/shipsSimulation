import java.util.Random;
import java.util.ArrayList;

//do dopisania kod tworzący statki, nadający im id i randomowe, unikatowe pozycje na start.
public class MapCreator {

    private final Integer map[][];

    public MapCreator(Integer maxWidth, Integer maxHeight){
        this.map = new Integer[maxWidth][maxHeight];
    }

    public Integer[][] getMap() {
        return map;
    }

}
