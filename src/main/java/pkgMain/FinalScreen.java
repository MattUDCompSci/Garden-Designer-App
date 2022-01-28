package pkgMain;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;

/**
*This class displays the final screen for the Garden Designer.
*
*@author Ben Newlin
*@author Matthew Weis
*@author Zehe Zack Luan
*@author Bryan Paliska
*@author Mayson Light
*/

public class FinalScreen {
	/**
	 * Scene used for Final Screen
	 */
	public static Scene scene;
	
	private double canvasWidth;
	private double canvasHeight;
	private Pane buttonPane;
	
	/**
	 * Setting Button Dimensions
	 */
	
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50, buttonFont = 14, labelFont = 30;
	
	/**
	 * Button used to move to next screen
	 */
	public Button forwardButton;
	/**
	 * Button used to move to previous screen
	 */
	public Button backwardButton;
	
	private int labelX = 325;
	
	private double lepScale = 1.0/7.0;
	
	private BorderPane borderPane;
	private Label theLabel = new Label("This is a final overview of your garden:");
	
	public ArrayList<Label> lepLabels;
	ObservableList<Label> backingList;
	ListView<Label> lepList;
	
	/**
	*Constructor for the final screen
	*
	*@param canvasWidth1 the width of the Final Screen's canvas
	*@param canvasHeight1 the height of the Final Screen's canvas
	*/
	
	public FinalScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
		
		theLabel.setTextFill(Color.BLACK);
		theLabel.setFont(Font.font ("Verdana", labelFont));
		theLabel.setTextAlignment(TextAlignment.CENTER);
		theLabel.setTranslateX(labelX);
		
		lepLabels = new ArrayList<Label>();
		
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
			pane.getChildren().addAll(backwardButton);
			pane.setLayoutX(0);
			pane.setLayoutY(canvasHeight-buttonEndY);
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
	*Creates and show list view of the leps that were added into the garden from the plants chosen
	*
	*@param lepDisplays An array list of the leps
	*/

	public void lepListView(ArrayList<Lep> lepidopteras) {
		for(Lep l : lepidopteras) {
			if(l != null) {
				ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/img/Leps/" + l.scientificName + ".png")));
				iv.setFitHeight(canvasHeight*lepScale);
				iv.setFitWidth(canvasHeight*lepScale);
				iv.setClip(new Circle(canvasHeight*lepScale/2.0, canvasHeight*lepScale/2.0, canvasHeight*lepScale/2.0));
				Label label = new Label("" + l.name + "\n[" + l.scientificName + "]\n", iv);
				lepLabels.add(label);
			}			
		}
		backingList = FXCollections.observableArrayList(lepLabels);
		this.lepList = new ListView<Label>(backingList);
		this.borderPane.setLeft(lepList);
	}
	
	
	/**
	*Sets the garden Pane
	*
	*@param pane The pane being set
	 * @throws IOException 
	*/
	public void setGardenPane(Pane pane){
		this.borderPane.setCenter(pane);
	}
	
	/**
	 * Sets the right side of the screen with the garden information from GardenScreen
	 */
	public void setGardenInfoPane(Pane p) {
		ArrayList<Button> buttons = new ArrayList<Button>();
		for(Object o : p.getChildren()) {
			if(o instanceof VBox) {
				VBox v = (VBox)o;
				System.out.println("True");
				for(Object c : v.getChildren()) {
					if(c instanceof Button) {
						Button d = (Button)c;
						buttons.add(d);
					}
				}
				for(Button b : buttons) {
					v.getChildren().remove(b);
				}
			}
		}	
		this.borderPane.setRight(p);
	}

}
