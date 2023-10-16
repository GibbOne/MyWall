import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.scene.paint.Color;
import myWall.Ball;
import myWall.Peg;
import myWall.World;

public class WorldTest {
    
    @Test 
    public void ItShouldCollidesAndPegRemainFixed() 
    {
        // ARRANGE
        var world = new World();
        var peg = new Peg(world, 256, 230, 4);
        var ball = new Ball(world, 266, 130, 17, 10, new Color(0, 1, 0, 1));
        world.addObject(peg);
        world.addObject(ball);

        // ACT
        int i = 0;
        for (i = 0; i < 100; i++)
        {
            world.update();
            if (ball.getPosition().x != 266) 
                break; // collision occurs
        }

        // ASSERT
        assertTrue("No collision happens", i != 100);
        assertTrue("Peg should not move", peg.getPosition().x == 256);
        assertTrue("Peg should not move", peg.getPosition().y == 230);
    }
}
