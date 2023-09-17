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

        world.addObject(new BallPipe(world,150, 0, 40, 30));
        world.addObject(new Peg(world, 170, 75, 4));
        world.addObject(new Ball(world, 170, 0, 18, 10, new Color(0, 1, 0, 1) ));
    }

}
