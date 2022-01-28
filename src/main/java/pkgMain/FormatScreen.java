package pkgMain;

import java.util.ArrayList;

import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
*This class displays the format screen for the Garden Designer.
*
*@author Ben Newlin
*@author Matthew Weis
*@author Zehe Zack Luan
*@author Bryan Paliska
*@author Mayson Light
*/

public class FormatScreen {
	
	/**
	 * The Scene used for the FormatScreen
	 */
	public static Scene scene;
	
	/**
	 * Starting scene Settings
	 */
	
	private double canvasWidth;
	private double canvasHeight;
	
	/**
	 * Pane
	 */
	
	private Pane buttonPane;
	
	/**
	 * Setting Button Dimensions
	 */
	
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50, padding = 10, ratio1 = 530;
	
	/**
	 * Setting Font Sizes
	 */
	
	private int buttonFont = 14;
	private int minWidth = 80;
	
	/**
	 * GridPanes
	 */
	GridPane formatChoicePane1, formatChoicePane2;
	
	/**
	 * Pane used for the Draw-Divide-Import Options
	 */
	Pane ddiPane;
	
	/**
	 * Pane for format drawing
	 */
	Pane drawPane;
	/**
	 * Pane for format import
	 */
	Pane importPane;
	/**
	 * Pane for format divide
	 */
	Pane dividePane;
	
	/**
	 * The Set up Garden Pane
	 */
	Pane gardenPane;
	/**
	 * The Pane Containing the Garden
	 */
	Pane gardenLayoutPane;
	/**
	 * BorderPane used for the Scene as a whole
	 */
	BorderPane borderPane;
	
	/**
	 * VBox for all the Panes on the left side of the Screen
	 */
	VBox leftPane;
	
	/**
	 * Buttons to change the Scene
	 */
	public Button forwardButton, backwardButton; 
	
	/**
	 * Button to Choose Draw Option
	 */
	public Button Draw = new Button("Draw");
	/**
	 * Button to Choose Import Option
	 */
	public Button Import = new Button("Import");
	/**
	 * Button to Choose Divide Option
	 */
	public Button Divide = new Button("Divide");
	
	/**
	 * Button for Import From button
	 */
	public Button ImportFrom = new Button("Import a PNG");
	/**
	 * Button to Import a Serialized Garden
	 */
	public Button ImportGarden = new Button("Import Garden");
	
	/**
	 * Button to confirm the Button Length and Width
	 */
	public Button Confirm = new  Button("Make Your Garden");
	/**
	 * Button to clear the Garden of all Drawings, Shapes, and Images
	 */
	public Button Clear = new Button("Clear");

	/**
	 * Buttton to Reset from the "Choose your length and width" section
	 */
	public Button Back = new Button("Reset");
	
	
	
	/**
	 * TextField to represent Garden Dimension
	 */
	
	public TextField gardenLength = new TextField(), gardenWidth = new TextField();
	
	
	
	/**
	 * Text
	 */
	
	
	private Text descText = new Text(
			"Welcome to the Format Screen! We offer the following options for customizing your garden:" 
			+ 
			"\n" + "First, input your preferred length and width of your garden in feet" 
			+ 
			"\n" + "Next click one of the three buttons: " + "Draw, Import, Divide");
	
	private Text formatChoicePaneT1 = new Text("This is where you set your general garden Height and width");
	
	
	/**
	 * divide Pane
	 */
	
	/**
	 * Button for Erasing part of your Drawing
	 */
	Button eraseBtn;
	/**
	 * Combobox used for the different color options in draw
	 */
	ComboBox<String> colorCombo;
	/**
	 * Radio Button for Drawing a Rectangle
	 */
	RadioButton rbRect;
	/**
	 * Radio Button for Drawing a Circle
	 */
	RadioButton rbCircle;
	/**
	 * ArrayList of shapes put onto the Garden
	 */
	ArrayList<Shape> shapeList;
	
	/**
	 * HBox
	 */
	HBox hb;
	/**
	 * VBox
	 */
	VBox vb;
	/**
	 * TogglGroup used for Rectangle and Circle Shape options
	 */
	ToggleGroup tg = new ToggleGroup();
	/**
	 * Rectangle Object created by user
	 */
	Rectangle rect;
	/**
	 * Circle Object made by user
	 */
	Circle circle;
	/**
	 * Color of the Shape based on the soil conditions
	 */
	Color pickedColor;
	
	/**
	 * Draw Pane
	 */
	Button eraseBtn2;
	/**
	 * Canvas of the scene
	 */
	Canvas canvas;
	/**
	 * Graphics context of screen
	 */
	GraphicsContext gc;
	
	/**
	 * RadioButton used for the Draw selection option
	 */
	RadioButton rbDraw;
	/**
	 * Color picker for Draw option
	 */
	ColorPicker cp = new ColorPicker();
	
	/**
	 * Node used to choose a png file from File Explorer
	 */
	FileChooser filechooser;
	/**
	 * Node used to choose a ser file from File Explorer
	 */
	FileChooser serializedChooser;
	/**
	 * Erase Button for Import pane
	 */
	Button eraseBtn3;
	/**
	 * Anchorpane for Import pane
	 */
	AnchorPane anchorpane = new AnchorPane();
	
	/**
	 * Longer side of the Garden in Window Pixels
	 */
	double Longer;
	/**
	 * Shorter side of the Garden in Window Pixels
	 */
	double Shorter;
	
	/**
	 * Ratio of Shorter/Longer
	 */
	double ratio; //Ratio to 500.
	/**
	 * Boolean representing whether the Garden is a square or rectangle (true if Longer and Shorter are different lengths)
	 */
	boolean isRect;
	
	
	/**
	*Constructor for the format screen
	*
	*@param canvasWidth1 the width of the Format Screen's canvas
	*@param canvasHeight1 the height of the Format Screen's canvas
	*/
	
	public FormatScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		
		this.buttonPane = initializeButtonPane();
		this.formatChoicePane1 = formatChoicePane1();
		this.formatChoicePane2 = formatChoicePane2();
		
		this.drawPane = drawPane();
		this.importPane = importPane();
		this.dividePane = dividePane();
		
		this.ddiPane = ddiPane();
		
		this.leftPane = new VBox(formatChoicePane2, ddiPane);
		
		
		
		this.gardenPane = gardenPane();
		
		this.borderPane = new BorderPane(null, null, null, buttonPane, formatChoicePane1);
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
			this.descText.setLayoutY(buttonEndY/5.0);
			this.descText.setLayoutX(buttonEndX + (buttonEndX/4.0));
			pane.getChildren().add(descText);
			pane.getChildren().addAll(backwardButton, forwardButton);
			pane.setLayoutX(0);
			pane.setLayoutY(canvasHeight-buttonEndY);
			return pane;
			
		}

	
	/**
	*Initializes the gardenPane that is used in the rest of the methods
	*
	*@return A fully initialized garden pane
	*/
	
	private Pane gardenPane() {
		Pane pane = new Pane();

		pane.setPrefSize(canvasWidth - 400, canvasHeight - 50);
		pane.setLayoutX(400);
		pane.setLayoutY(0);
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
	*Initializes the first gridPane that is used in the rest of the methods
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized grid pane
	*/
	
	private GridPane formatChoicePane1() {
		GridPane pane = new GridPane();
		
		//pane.setMaxWidth(400);
		//pane.setMaxHeight(550);
		
		Text inputText1 = new Text("Enter preferred garden length:");
		Text inputText2 = new Text("Enter preferred garden width:");
		
		pane.setMaxSize(400, canvasHeight - 50);
		
		pane.setLayoutX(0);
		pane.setLayoutY(0);
		pane.setPadding(new Insets(padding, padding, padding, padding));
		
		pane.setVgap(5);
		pane.setHgap(5);
		
		pane.setAlignment(Pos.CENTER);
		
		pane.add(inputText1, 0, 0);
		pane.add(gardenLength, 1, 0);
		pane.add(inputText2, 0, 1);
		pane.add(gardenWidth, 1, 1);
		
		pane.add(Confirm, 0, 2);
		pane.add(Clear, 1, 2);
		
		this.Confirm.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		this.Clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		
		pane.setStyle("-fx-border-color: black");
		
		inputText1.setStyle("-fx-font: normal bold 15px ' serif' ");
		inputText2.setStyle("-fx-font: normal bold 15px ' serif' ");
		
		
		return pane;
	}
	
	/**
	*Initializes the second gridPane that is used in the rest of the methods
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized grid pane
	*/
	
	private GridPane formatChoicePane2() {
		GridPane pane = new GridPane();
		
		pane.setPrefSize(400, canvasHeight - 400);
		
		pane.setPadding(new Insets(padding, padding, padding, padding));
		
		pane.setVgap(5);
		pane.setHgap(5);
		
		pane.setStyle("-fx-border-color: black");
		
		pane.setLayoutX(0);
		pane.setLayoutY(0);
		
		Text drawText = new Text("Draw your garden here");
		Text importText = new Text("Import a picture of your garden here");
		Text divideText = new Text("Section off pieces of your garden here");
		
		drawText.setStyle("-fx-font: normal bold 15px ' serif' ");
		importText.setStyle("-fx-font: normal bold 15px ' serif' ");
		divideText.setStyle("-fx-font: normal bold 15px ' serif' ");
		
		this.Draw.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		this.Divide.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		this.Import.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		this.Back.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white");
		
		pane.add(this.Draw, 0, 0);
		pane.add(drawText, 1, 0);
		pane.add(this.Divide, 0, 1);
		pane.add(divideText, 1, 1);
		pane.add(this.Import, 0, 2);
		pane.add(importText, 1, 2);
		pane.add(Back, 0, 3);
		
		return pane;
	}
	
	/**
	*Initializes the Draw Pane
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized draw pane
	*/
	
	private Pane drawPane() {
		Pane pane = new Pane();
		pane.setPrefSize(400, 350);
		pane.setStyle("-fx-border-color: black");
		
		eraseBtn2 = new Button("Erase");
		eraseBtn2.setMinWidth(80);
		eraseBtn2.setStyle("-fx-background-color: red");
		eraseBtn2.setFont(Font.font("Verdana", FontWeight.BOLD, buttonFont));
		
		rbDraw = new RadioButton();
		rbDraw.setText("Draw");
		rbDraw.setToggleGroup(tg);
		VBox drawBox = new VBox();
		
		drawBox.getChildren().addAll(rbDraw, cp, eraseBtn2);
		drawBox.setAlignment(Pos.CENTER_LEFT);
		drawBox.setPadding(new Insets(padding, padding, padding, padding));
		drawBox.setSpacing(40);
		
		drawBox.setLayoutX(0);
		drawBox.setLayoutY(0);
		
		pane.getChildren().add(drawBox);
		
		
		return pane;
	}
	
	/**
	*Initializes the Import Pane
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized import pane
	*/
	
	private Pane importPane() {
		Pane pane = new Pane();
		pane.setPrefSize(400, 350);
		pane.setStyle("-fx-border-color: black");
		
		filechooser = new FileChooser();
		filechooser.setTitle("Open A PNG");
		
		serializedChooser = new FileChooser();
		serializedChooser.setTitle("Open a Garden");
		
		ImportFrom.setMinWidth(minWidth);
		ImportFrom.setTextFill(Color.WHITE);
		ImportFrom.setStyle("-fx-background-color: darkslateblue");
		ImportGarden.setMinWidth(minWidth);
		ImportGarden.setTextFill(Color.WHITE);
		ImportGarden.setStyle("-fx-background-color: darkslateblue");
		eraseBtn3 = new Button("Erase");
		eraseBtn3.setMinWidth(minWidth);
		eraseBtn3.setStyle("-fx-background-color: red");
		eraseBtn3.setFont(Font.font("Verdana", FontWeight.BOLD, buttonFont));
		
		VBox importBox = new VBox();
		
		importBox.getChildren().addAll(ImportFrom, ImportGarden, eraseBtn3);
		importBox.setAlignment(Pos.CENTER_LEFT);
		importBox.setPadding(new Insets(padding, padding, padding, padding));
		importBox.setSpacing(40);
		
		importBox.setLayoutX(0);
		importBox.setLayoutY(0);
		
		pane.getChildren().add(importBox);
		
		return pane;
	}
	
	/**
	*Initializes the Divide Pane
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized divide pane
	*/
	
	private Pane dividePane() {
		Pane pane = new Pane();
		
		eraseBtn = new Button("Erase");
		eraseBtn.setMinWidth(minWidth);
		eraseBtn.setStyle("-fx-background-color: red");
		eraseBtn.setFont(Font.font("Verdana", FontWeight.BOLD, buttonFont));
		
		colorCombo = new ComboBox<>();
		colorCombo.getItems().setAll("Beige", "Saddle-Brown", "Brown");
		colorCombo.setValue("Beige");
		
		rbRect = new RadioButton();
		rbCircle = new RadioButton();
		rbRect.setText("Rectangle");
		rbCircle.setText("Circle");


		rbRect.setToggleGroup(tg);
		rbCircle.setToggleGroup(tg);
		
		shapeList = new ArrayList<>();

		pane.setStyle("-fx-background-color: beige;");
		
		vb = new VBox();
		pickedColor = Color.SANDYBROWN; //Low
		
		//Color.SADDLEBROWN // Medium
		//Color.BROWN //High


		vb.getChildren().addAll(colorCombo, rbRect, rbCircle, eraseBtn);
		vb.setAlignment(Pos.CENTER_LEFT);
		vb.setPadding(new Insets(padding, padding, padding, padding));
		vb.setSpacing(40);
		
		vb.setLayoutX(0);
		vb.setLayoutY(0);
		
		pane.setPrefSize(400, 350);
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().add(vb);
		
//		Text inputText = new Text("divide?");
//		inputText.setLayoutX(0);
//		inputText.setLayoutY(20);
//		pane.getChildren().add(inputText);
		
		return pane;
	}
	
	/**
	*Initializes the Pane that draw, import, and divide will be placed on
	*<p>
	*This method also sets the x, y, text, and color of the pane
	*
	*@return A fully initialized ddi pane
	*/
	
	private Pane ddiPane() { //empty pane
		Pane pane = new Pane();
		
		pane.setPrefSize(400, 350);
		pane.setStyle("-fx-border-color: black");
		
		return pane;
	}
	
	
	/**
	*Creates a garden
	*
	*@param W The width of the garden
	*@param L The length of the garden
	*/
	
	public void createGarden(double L, double W, double prefL, double prefW) {
		
		System.out.println("Length is" + L);
		System.out.println("Width is " + W);
		
		Pane pane = new Pane();
		pane.setMaxSize((double)L, (double)W);
		pane.setStyle("-fx-border-color:blue");
		Text Width = new Text("Width: " + prefW);
		Text Length = new Text("Length " + prefL);
		Width.setStyle("-fx-font: normal bold 12px ' serif' ");
		Length.setStyle("-fx-font: normal bold 12px ' serif' ");

		Length.setLayoutX(0);
		Length.setLayoutY(0);
		
		Width.setLayoutX(100);
		Width.setLayoutY(0);



		this.gardenLayoutPane = pane;	
		//this.gardenLayoutPane.getChildren().addAll(Width, Length);

	}
	
	/**
	*Starts the canvas
	*
	*@param width The width of the canvas
	*@param length The length of the canvas
	*/
	
	public void startCanvas(double width, double height) {
		canvas = new Canvas(width, height);
		
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gardenLayoutPane.getChildren().add(canvas);
		
		cp.setValue(Color.BLACK);
	}
	
	public void compareLength(double x, double y) {
		
		x = Math.round(x * 100.0) / 100.0;
		y = Math.round(y * 100.0) / 100.0;
		System.out.println(x);
		System.out.println(y);
		
		if (x == y) {
			System.out.println(x + "+" + y);
			System.out.println("here at equal");
			
			Longer = x;
			Shorter = y;
			
			isRect = false;
			ratio = ratio1/x;
			ratio = Math.round(ratio * 100.0) / 100.0;
			
			System.out.println("Ratio is " + ratio);
			
			Longer = ratio1;
			Shorter = ratio1;
			
		} else if (x > y) {
			System.out.println(x + "+" + y);
			
			Longer = x;
			Shorter = y;
			
			isRect = true;
			ratio = ratio1/x;
			ratio = Math.round(ratio * 100.0) / 100.0;
			System.out.println("Ratio is " + ratio);
			
			Longer = ratio1;
			Shorter = y * ratio;
			
		} else if (x < y) {
			System.out.println(x + "+" + y);
			
			Longer = y;
			Shorter = x;
			
			isRect = true;
			ratio = ratio1/y;
			ratio = Math.round(ratio * 100.0) / 100.0;
			System.out.println("Ratio is " + ratio);
			
			Longer = ratio1;
			Shorter = x * ratio;
		}
		
	}
}