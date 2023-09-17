package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Peg extends Object2D
{

    protected Peg(World world, float x, float y, float radius) {
        super(world, new Circle(x, y, radius), Float.MAX_VALUE, 0);
        setFixed(true);
        
        getShape().setFill(Color.RED); //TODO to remove
    }

}
