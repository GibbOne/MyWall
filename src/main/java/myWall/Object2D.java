package myWall;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class Object2D {
    private World world;
    private Shape shape;
    private float mass;
    private float elasticity;
    private boolean fixed = false;

    protected Object2D(World world, Shape shape, float mass, float elasticity)
    {
        this.shape = shape;
        this.mass = mass;
        this.elasticity = elasticity;
    }

    public boolean getFixed() 
    {
        return fixed;
    }
    public void setFixed(boolean value) 
    {
        this.fixed = value;
    }

    public World getWorld() 
    {
        return world;
    }

    public Shape getShape() 
    {
        return shape;
    }

    public float getMass() 
    {
        return mass;
    }

    public float getElasticity() 
    {
        return elasticity;
    }

    public Point2D getCollisionPointWith(Object2D other)
    {
        Bounds intersect = Shape.intersect(shape, other.getShape()).getBoundsInLocal();
        if (intersect.isEmpty())
        {
            return null;
        }
        else
        {
            return new Point2D(intersect.getCenterX(), intersect.getCenterY());
        }
    }

    public void MoveOf(float deltaX, float deltaY)
    {
        shape.setTranslateX(deltaX);
        shape.setTranslateY(deltaY);
    }
}
