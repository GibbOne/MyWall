import org.junit.Test;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static org.junit.Assert.assertTrue;
import myWall.Object2D;

public class Object2DTests {
    
    @Test 
    public void ItShouldNotCollides() {
        //ARRANGE
        var o1 = new MockObject2D(new Rectangle(5, 5, 10, 10), 1);
        var o2 = new MockObject2D(new Circle(0, 0, 3), 2);

        // ACT and ASSERT
        assertTrue(o1.getCollisionPointWith(o2) == null);
    }

}

class MockObject2D extends Object2D {
    public MockObject2D(Shape s, float mass) 
    {
        super(s, mass);
    }
}