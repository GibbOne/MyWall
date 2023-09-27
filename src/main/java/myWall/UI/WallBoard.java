package myWall.UI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import myWall.Game;
import myWall.Main;
import myWall.Object2D;

public class WallBoard extends Application {

	private AnimationTimer tmr;
	private Color ballColor = new Color(0, 1, 0, 1);
	private Label resultLabel;

    /**
	 * Launch the application.
	 */
	public static void launch(String[] args) {
        Application.launch(args);
	}

	public void start(Stage stage) {
		game = new Game();
        Pane root = new Pane();
		root.setBackground(new Background(new BackgroundImage(new Image(Main.class.getClassLoader().getResource("wallboard.png").toString()),
        	BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          	new BackgroundSize(700, 1000, true, true, true, true))));

		GenerateSlotMoneys(root);

		root.getChildren().add(resultLabel = GenerateSumLabel(0, 10, 50));

		for (Object2D o : game.getObjects()) {
			root.getChildren().add(o.getShape());
		}

        Scene scene = new Scene(root, 700, 1000);
        stage.setTitle("MyWall game");
        stage.setScene(scene);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.NUMPAD1) {
					root.getChildren().add(game.AddBallOnPipe(1, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else if (ke.getCode() == KeyCode.NUMPAD2) {
					root.getChildren().add(game.AddBallOnPipe(2, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else 	if (ke.getCode() == KeyCode.NUMPAD3) {
					root.getChildren().add(game.AddBallOnPipe(3, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else if (ke.getCode() == KeyCode.NUMPAD4) {
					root.getChildren().add(game.AddBallOnPipe(4, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else if (ke.getCode() == KeyCode.NUMPAD5) {
					root.getChildren().add(game.AddBallOnPipe(5, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else 	if (ke.getCode() == KeyCode.NUMPAD6) {
					root.getChildren().add(game.AddBallOnPipe(6, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else 	if (ke.getCode() == KeyCode.NUMPAD7) {
					root.getChildren().add(game.AddBallOnPipe(7, ballColor).getShape());
					ke.consume(); // <-- stops passing the event to next node
				} else 	if (ke.getCode() == KeyCode.R) {
					ballColor = new Color(1,0,0, 1);
					ke.consume(); // <-- stops passing the event to next node
				} else 	if (ke.getCode() == KeyCode.G) {
					ballColor = new Color(0,1,0, 1);
					ke.consume(); // <-- stops passing the event to next node
				}  			
			}
		});
        stage.show();

		tmr = new TimerMethod(game, this);
		tmr.start();
    }
		
	private int[] SlotAmounts = new int[15];

	private void GenerateSlotMoneys(Pane root) 
	{
		root.getChildren().add(GenerateSlotMoney("   1", 25, 850)); SlotAmounts[0] = 1;
		root.getChildren().add(GenerateSlotMoney(" 5000", 71, 850)); SlotAmounts[1] = 5000;
		root.getChildren().add(GenerateSlotMoney(" 100", 117, 850)); SlotAmounts[2] = 100;
		root.getChildren().add(GenerateSlotMoney(" 10000", 163, 850)); SlotAmounts[3] = 10000;
		root.getChildren().add(GenerateSlotMoney("  10", 209, 850)); SlotAmounts[4] = 10;
		root.getChildren().add(GenerateSlotMoney(" 20000", 255, 850)); SlotAmounts[5] = 20000;
		root.getChildren().add(GenerateSlotMoney("   1", 301, 850)); SlotAmounts[6] = 1;
		root.getChildren().add(GenerateSlotMoney(" 30000", 347, 850)); SlotAmounts[7] = 30000;
		root.getChildren().add(GenerateSlotMoney("   1", 393, 850)); SlotAmounts[8] = 1;
		root.getChildren().add(GenerateSlotMoney(" 40000", 439, 850)); SlotAmounts[9] = 40000;
		root.getChildren().add(GenerateSlotMoney("  10", 485, 850)); SlotAmounts[10] = 10;
		root.getChildren().add(GenerateSlotMoney(" 50000", 531, 850)); SlotAmounts[11] = 50000;
		root.getChildren().add(GenerateSlotMoney(" 100", 577, 850)); SlotAmounts[12] = 100;
		root.getChildren().add(GenerateSlotMoney("100000", 623, 850)); SlotAmounts[13] = 100000;
		root.getChildren().add(GenerateSlotMoney("   1", 669, 850)); SlotAmounts[14] = 1;

	}

	private Label GenerateSlotMoney(String amount, double x, double y)
	{
		Label label = new Label(amount);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        label.setWrapText(true);
        label.setMinWidth(1);
        label.setPrefWidth(1);
        label.setMaxWidth(1);
		label.setLayoutX(x);
		label.setLayoutY(y);
		label.setTextFill(Color.WHITE);
		return label;
	}

	private Label GenerateSumLabel(long amount, double x, double y)
	{
		Label label = new Label(amount + " $");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	label.setLayoutX(x);
		label.setLayoutY(y);
		label.setTextFill(Color.WHITE);
		return label;
	}

	@Override
	public void stop(){
		System.out.println("Stage is closing");
	}

	private Game game;

	public void SetResult()
	{

		long sum = 0;
		for (int i = 0; i < game.GetSlotsCount(); i++)
		{
			sum += game.GetBallsInSlot(i) * SlotAmounts[i];
		}

		resultLabel.setText(sum + " $");
		if (sum == 0)
			resultLabel.setTextFill(Color.WHITE);
		else if (sum < 0)
			resultLabel.setTextFill(Color.RED);
		else
			resultLabel.setTextFill(Color.GREEN);
	}
}

class TimerMethod extends AnimationTimer {
	private Game game;
	private WallBoard board;

	public TimerMethod(Game game, WallBoard board) {
		this.game = game;
		this.board = board;
	}

	//define the handle method
	@Override
	public void handle(long now) {
		//call the method
		game.update();

		board.SetResult();
	}
}