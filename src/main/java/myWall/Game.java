package myWall;

import java.util.List;

public class Game {
    private World world = new World();

    public Game() 
    {
        world.addObject(new Wall(world));
    }

    public List<Object2D>  getObjects()
    {
        return world.getObjects();
    }

	public void update() {
		world.update();
	}
}
