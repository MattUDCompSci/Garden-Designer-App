package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
*This class displays the confirmation screen for the Garden Designer.
*
*@author Ben Newlin
*@author Matthew Weis
*@author Zehe Zack Luan
*@author Bryan Paliska
*@author Mayson Light
*/
public class ConformationScreen {
	
	public static Scene scene;
	
	/*
	 * Starting scene Settings
	 */
	
	private double canvasWidth;
	private double canvasHeight;
	private Pane buttonPane;
	private int labelX = 325;
	
	public Button forwardButton;
	public Button backwardButton; 
	
	/**
	 * Setting Button Dimensions
	 */
	
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50;
	
	/*
	 * Setting Font Sizes
	 */
	private int labelFont = 30, buttonFont = 14;
	
	/*
	 * Setting
	 */
	
	private BorderPane borderPane;
	private Label theLabel = new Label("Are you satisfied with your garden?");
	
	/*
	 * Your code after here
	 */
	
	Pane Test;
	
	/**
	*Constructor for the confirmation screen
	*
	*@param canvasWidth1 the width of the Confirmation Screen's canvas
	*@param canvasHeight1 the height of the Confirmation Screen's canvas
	*/
	
	public ConformationScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
		theLabel.setTextFill(Color.BLACK);
		theLabel.setFont(Font.font ("Verdana", labelFont));
		theLabel.setTextAlignment(TextAlignment.CENTER);
		theLabel.setTranslateX(labelX);
		
		/*
		 * Your code after here
		 */
		
		
		
		/*
		 * Set scene Comment
		 */
		

		this.borderPane = new BorderPane(null, theLabel, null, buttonPane, null);	
		this.borderPane.setStyle("-fx-background-color:lightblue");

		this.scene = new Scene(borderPane, canvasWidth, canvasHeight);
		
	}
	
	
	/**
	 * Default Initialize Button for switching scene, you can change it, as long as there is a button pointed to the
	 * public Button forwardButton line.
	 */
		private Pane initializeButtonPane() { //used for initializing Button Pane used in all classes..
			Pane pane = new Pane();
			pane.setStyle("-fx-background-color:lightgreen");
			pane.setPrefSize(canvasWidth, 50);
			this.backwardButton = initializeButton(buttonStartX, buttonStartY, buttonEndX, buttonEndY, "Back");		
			this.forwardButton = initializeButton(canvasWidth-buttonEndX, buttonStartY, buttonEndX, buttonEndY, "Next");
			this.forwardButton.setStyle("-fx-background-color: green");
			pane.getChildren().addAll(backwardButton, forwardButton);
			pane.setLayoutX(0);
			pane.setLayoutY(canvasHeight-50);
			return pane;
			
		}
	
	/**
	*Initializes the button that is used in the rest of the methods
	*
	*@param XCoor The x coordinate of the button
	*@param YCoor The y coordinate of the button
	*@return A fully initialized button
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
	
	/**
	*Tests the setPane
	*
	*@param pane The pane being tested
	*@param XCoor The x coordinate of the pane
	*@param YCoor The y coordinate of the pane
	*/
	
	public void testSetPane(Pane pane, int x, int y) {
		this.borderPane.setCenter(pane);
	}

}
