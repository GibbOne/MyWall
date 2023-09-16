import org.junit.Test;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.assertTrue;
import myWall.Object2D;
import myWall.World;

public class Object2DTests {
    
    @Test 
    public void ItShouldNotCollides() {
        //ARRANGE
        var o1 = new MockObject2D(null, new Rectangle(5, 5, 10, 10), 1, 0);
        var o2 = new MockObject2D(null, new Circle(0, 0, 3), 2, 0);

        // ACT and ASSERT
        assertTrue(o1.getCollisionPointWith(o2) == null);
    }

}

class MockObject2D extends Object2D {
    public MockObject2D(World w, Shape s, float mass, float elasticity) 
    {
        super(w, s, mass, elasticity);
    }
}