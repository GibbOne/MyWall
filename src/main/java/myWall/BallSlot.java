package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class BallSlot extends Object2D
{

    protected BallSlot(World world, float xOfUpperLeftCorner, float yOfUpperLeftCorner, float width) {
        super(
            world,
            new Path(
                new MoveTo(xOfUpperLeftCorner, yOfUpperLeftCorner),
                new LineTo(xOfUpperLeftCorner, yOfUpperLeftCorner + width*5),
                new LineTo(xOfUpperLeftCorner + width, yOfUpperLeftCorner + width*5),
                new LineTo(xOfUpperLeftCorner + width, yOfUpperLeftCorner)
                ), 
            Float.MAX_VALUE, 0);
            setFixed(true);
            getShape().fillProperty().setValue(Color.RED); //TODO to remove
    }

}
