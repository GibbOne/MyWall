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
        //TODO Auto-generated constructor stub

        getShape().strokeProperty().setValue(Color.RED); //TODO to remove
    }

}
