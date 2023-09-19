package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Peg extends Object2D
{

    protected Peg(World world, float xCenter, float yCenter, float radius) {
        super(world, new Circle(0, 0, radius), new Vector2D(xCenter, yCenter), Float.MAX_VALUE, 0);
        setFixed(true);
        
        getShape().setFill(Color.TRANSPARENT); //TODO to remove
    }

}
