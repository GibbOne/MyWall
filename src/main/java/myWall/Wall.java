package myWall;

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

        world.addObject(new BallPipe(world,150, 0, 20, 30));
        world.addObject(new Peg(world, 160, 75, 2));

    }

}
