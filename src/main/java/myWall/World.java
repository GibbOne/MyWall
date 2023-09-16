package myWall;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class World {
    Vector<Object2D> objects = new Vector<Object2D>();

    public List<Object2D> getObjects()
    {
        return Collections.unmodifiableList(objects);
    }

    public void addObject(Object2D o) {
        objects.add(o);
    }
}
