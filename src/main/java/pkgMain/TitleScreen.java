package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TitleScreen {
	
	/**
	 * The Scene for the TitleScreen
	 */
	public static Scene scene;

	private double canvasWidth;
	private double canvasHeight;
	private int buttonWidth=200;
	private int buttonHeight=50;
	/**
	 * The space between each button on the Button Pane.
	 */
	private int buttonSpace=300;
	/**
	 * The X location of the right edge of the tip button.
	 */
	private int tipButtonXloc=200;
	/**
	 * The button pane containing all the buttons.
	 */
	private Pane buttonPane;
	
	/**
	 * The Row for the Garden Designer Title
	 */
	public double titleRow = -1/4;
	
	/**
	 * Set Font Sizes
	 */
	private int buttonFont = 14, inputFont = 100, descriptionFont = 25;
	/**
	 * Set Final Conditions
	 */
	private int buttonStartX = 0, buttonStartY = 0, layoutX = 0, layoutY = 0;
	/**
	 * Button used to move to the next scene
	 */
	public Button forwardButton;
	/**
	 * Button used to move to the Tips Scene
	 */
	public Button tipsButton;
	/**
	 * Button used to move to the ProgramPurpose Scene
	 */
	public Button infoButton;
	/**
	 * The Background image used on the Title Screen
	 */
	public Image background;
	/**
	 * The BorderPane for the scene.
	 */
	private BorderPane borderPane;
	/**
	 * The title of the screen which is also the name of our program.
	 */
	private Text inputText = new Text("Garden Designer");
	/**
	 * Description Text to be placed underneath Title
	 */
	private Text descriptionText = new Text("Build your own Garden using Native Plants!");

	/**
	 * Class Constructor
	 * @param canvasWidth1 the canvas width of the program window
	 * @param canvasHeight1 the canvas height of the program window
	 */

	public TitleScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();

		this.titleRow = -1.0/4.0;
		
		background = new Image(getClass().getResourceAsStream("/img/mt-cuba-background.jpg"));
    	ImageView iv = new ImageView(background);
		iv.setPreserveRatio(true);
		iv.setLayoutX(layoutX);
		iv.setLayoutY(layoutY);
		
		inputText.setFill(Color.WHITE);
		inputText.setTextAlignment(TextAlignment.CENTER);
		inputText.setTranslateY(canvasHeight*titleRow);
		inputText.setFont(Font.font ("Verdana", inputFont));
		
		descriptionText.setFill(Color.WHITE);
		descriptionText.setTextAlignment(TextAlignment.CENTER);
		descriptionText.setFont(Font.font ("Verdana", descriptionFont));
		descriptionText.setTranslateX(canvasWidth/2.0 - (descriptionText.getLayoutBounds().getWidth()/2.0));
		descriptionText.setTranslateY(canvasHeight/2.0);
		
		this.borderPane = new BorderPane();
		this.borderPane.getChildren().add(iv);
		this.borderPane.getChildren().add(descriptionText);
		this.borderPane.setBottom(buttonPane);
		this.borderPane.setCenter(inputText);
		this.scene = new Scene(borderPane, canvasWidth, canvasHeight);
		
	}
	

	/**
	 * Returns a Button pane containing the buttons used to navigate to preceding and following
	 * screens. 
	 * <p>
	 * This a default method used across all screens. For this screen, the pane only
	 * contains a Back button since this is an annex screen.
	 * @return the Button pane with the Back button on the left corner
	 * 
	 */
	private Pane initializeButtonPane() { //used for initializing Button Pane used in all classes..

		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:lightgreen");
		pane.setPrefSize(canvasWidth, 50);

		this.forwardButton = initializeButton(canvasWidth-buttonWidth, buttonStartY, buttonWidth, buttonHeight, "Next");
		this.tipsButton = initializeButton(buttonStartX, buttonStartY, buttonWidth, buttonHeight, "Gardening Tips");
		this.infoButton = initializeButton(tipButtonXloc+buttonSpace, buttonStartY, buttonWidth, buttonHeight, "About us");
		this.tipsButton.setStyle("-fx-background-color: lightblue");
		this.infoButton.setStyle("-fx-background-color: lightblue");
		this.forwardButton.setStyle("-fx-background-color: green");
		
		pane.getChildren().addAll(forwardButton, tipsButton, infoButton);

		pane.setLayoutX(layoutX);
		pane.setLayoutY(canvasHeight-50);
		return pane;
		
	}
	
	/**
	 * Returns a Button that can be added to a ButtonPane. It is the method used to initialize
	 * buttons in the <a href="#{@link}">{@link initializeButtonPane()}</a> method.
	 * 
	 * @param XCoor the x coordinate
	 * @param YCoor the y coordinate
	 * @param Width the width of the button
	 * @param Height the height of the button
	 * @param text the label on the button
	 * @return the Button with the specified characteristics
	 */
	private Button initializeButton(double XCoor, double YCoor, 
			double Width, double Height, String text) { 
		Button button = new Button(text);
		button.setTextFill(Color.WHITE);
		button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, buttonFont));
		button.setLayoutX(XCoor);
		button.setLayoutY(YCoor);
		button.setClip(new Circle(Width/2.0, Height/2.0, Width/3.0));
		button.setPrefSize(Width, Height);
		button.setStyle("-fx-background-color: red");
		return button;
	}
}
