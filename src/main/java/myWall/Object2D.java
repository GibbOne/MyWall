package myWall;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class Object2D {
    private Shape shape;
    private float mass;

    protected Object2D(Shape shape, float mass)
    {
        this.shape = shape;
        this.mass = mass;
    }

    public Shape getShape() 
    {
        return shape;
    }

    public float getMass() 
    {
        return mass;
    }

    public boolean collidesWith(Object2D other)
    {
        Bounds intersect = Shape.intersect(shape, other.getShape()).getBoundsInLocal();
        if (intersect.isEmpty())
        {
            return false;
        }
        else
        {
            //intersect.getCenterX()
            return true;
        }
    }
}
