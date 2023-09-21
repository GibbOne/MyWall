package myWall;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class Object2D {
    private World world;
    private Shape shape;
    private float mass;
    private float elasticity;
    private boolean fixed = false;
    private Vector2D speed;
    private Vector2D position;

    protected Object2D(World world, Shape shape, Vector2D position, float mass, float elasticity)
    {
        this.shape = shape;
        this.mass = mass;
        this.elasticity = elasticity;
        this.speed = new Vector2D(0, 0);
        setPosition(position);
    }

    public Vector2D getPosition()
    {
        return position; // new Vector2D(shape.getBoundsInParent().getCenterX(), shape.getBoundsInParent().getCenterY());
    }

    public void setPosition(Vector2D value)
    {
        this.position = value;
        shape.setLayoutX(position.getComponents()[0]);
        shape.setLayoutY(position.getComponents()[1]);
    }

    public Vector2D getSpeed() 
    {
        return speed;
    }

    public void setSpeed(Vector2D value) 
    {
        speed = value;
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

    public Bounds getCollisionIntersectBounds(Object2D other)
    {
        Bounds intersect = Shape.intersect(shape, other.getShape()).getBoundsInLocal();
        if (intersect.isEmpty())
        {
            return null;
        }
        else
        {
            return intersect;
        }
    }

    public void moveOf(double deltaX, double deltaY)
    {
        position.add(deltaX, deltaY);
        shape.setLayoutX(shape.getLayoutX() + deltaX);
        shape.setLayoutY(shape.getLayoutY() + deltaY);
    }

}
