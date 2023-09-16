package myWall;

import myWall.UI.WallBoard;

public class Main {
        /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        WallBoard.launch(args); // due to https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174
	}
}
