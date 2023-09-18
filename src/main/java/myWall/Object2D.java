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
    private Vector2D speed;

    protected Object2D(World world, Shape shape, float mass, float elasticity)
    {
        this.shape = shape;
        this.mass = mass;
        this.elasticity = elasticity;
        this.speed = new Vector2D(0, 0);
    }

    public Vector2D getSpeed() 
    {
        return speed;
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

    public void moveOf(double deltaX, double deltaY)
    {
        shape.setLayoutX(shape.getLayoutX() + deltaX);
        shape.setLayoutY(shape.getLayoutY() + deltaY);
    }
}
