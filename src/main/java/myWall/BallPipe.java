package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class BallPipe extends Object2D{

    protected BallPipe(World world, float xOfUpperLeftCorner, float yOfUpperLeftCorner, float width, float height) {
        super(
            world,
            new Path(
                new MoveTo(xOfUpperLeftCorner, yOfUpperLeftCorner),
                new LineTo(xOfUpperLeftCorner, yOfUpperLeftCorner + height),
                new MoveTo(xOfUpperLeftCorner + width, yOfUpperLeftCorner + height),
                new LineTo(xOfUpperLeftCorner + width, yOfUpperLeftCorner)
                ),
        Float.MAX_VALUE, 0);
        setFixed(true);
        
        //TODO Auto-generated constructor stub

        getShape().setStroke(Color.RED); //TODO to remove
    }

}
