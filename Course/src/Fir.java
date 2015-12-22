import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Вероника on 12.10.2015.
 */
public class Fir {
    private Map<Long, FirToy> toys = new HashMap<Long, FirToy>();
    private long nextId;
    private long getNextId() {
        if (nextId < Long.MAX_VALUE && !toys.containsKey(nextId))
            return nextId++;
        nextId = 0;
        while (nextId < Long.MAX_VALUE) {
            if (!toys.containsKey(nextId))
                return nextId;
            ++nextId;
        }
        throw new RangeException((short)111, "No more IDs availiable");
    }
    public FirToy addFirToy(String color, String size)
            throws CloneNotSupportedException {
        long recordId = getNextId();
        FirToy firToy = new FirToy(recordId, color,size);
        toys.put(recordId, firToy);
        return (FirToy)firToy.clone();
    }
    public FirToy getFirToy(long id) throws CloneNotSupportedException {
        if (toys.containsKey(id))
            return (FirToy)toys.get(id).clone();
        return null;
    }
//    public FirToy getFirToyByColor(String color) throws CloneNotSupportedException {
//        for (FirToy firToy : this.toys.values()) {
//            if(firToy.getColor()==)
//        }
//    }
    public FirToy updateFirToy(long id, String color, String size)
            throws CloneNotSupportedException {
       FirToy firToy = new FirToy(id, color, size);
        toys.put(id, firToy);
        return getFirToy(id);
    }
    public void removeFirToy(long id) {
        toys.remove(id);
    }
    int firToysCount;
    public List<FirToy> allFirToys() {
        firToysCount=0;
        List<FirToy> result = new ArrayList<FirToy>();
        for (FirToy firToy : this.toys.values()) {
            result.add(firToy);
            firToysCount++;
        }
        return result;
    }
}
