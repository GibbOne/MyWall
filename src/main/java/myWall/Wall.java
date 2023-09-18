package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Wall extends Object2D
{
    private static final float HEIGHT = 1000f;
    private static final float RATIO = 0.7f;

    public Wall(World world) 
    {
        super(
            world,
            new Path(
                new MoveTo(0, 0),
                new LineTo(0, HEIGHT),
                new LineTo(HEIGHT*RATIO, HEIGHT),
                new LineTo(HEIGHT*RATIO, 0)
                ), 
            Float.MAX_VALUE, 0);
        
        setFixed(true);

        world.addObject(new BallPipe(world,248, 130, 36, 30));
        world.addObject(new Peg(world, 266, 230, 4));
        world.addObject(new Ball(world, 266, 130, 17, 10, new Color(0, 1, 0, 1) ));
    }
}
