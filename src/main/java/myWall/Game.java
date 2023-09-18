package myWall;

import java.util.List;

import javafx.scene.paint.Color;

public class Game {
    private World world = new World();

    public Game() 
    {

        world.addObject(new Peg(world, 256, 230, 4));
        world.addObject(new Peg(world, 296, 270, 4));
        world.addObject(new Peg(world, 336, 310, 4));
        world.addObject(new Peg(world, 356, 350, 4));
        world.addObject(new Peg(world, 396, 390, 4));
        world.addObject(new Peg(world, 436, 430, 4));
        world.addObject(new Peg(world, 516, 470, 4));

        world.addObject(new Ball(world, 266, 130, 17, 10, new Color(0, 1, 0, 1) ));
    }

    public List<Object2D>  getObjects()
    {
        return world.getObjects();
    }

	public void update() {
		world.update();
	}
}
