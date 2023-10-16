package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Object2D
{

    public Wall(World world, float xLeft, float yTop, float width, float height) {
        super(world, new Rectangle(width, height), 
        new Vector2D(xLeft, yTop), Float.MAX_VALUE, 0);  //TODO POSIZIONE DEL RETTANGOLO NON E' IL CENTRO!!!
        setFixed(true);
        
        getShape().setFill(Color.TRANSPARENT);
    }

}
