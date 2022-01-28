package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * The View Class
 * <p>
 * This class holds all the instances of the Garden Screens
 * @author Zehe Zack Luan, Matthew Weis, Mayson Light, Ben Newlin, Bryan Paliska
 *
 */
public class View {
	
	/**
	 * Starter Scene Settings
	 */
	
	Scene testScene;
	private double canvasWidth;
	private double canvasHeight;
	
	/**
	 * Initializing all screens that will be used throughout the program
	 */
	
	TitleScreen titleScreen;
	FormatScreen formatScreen;
	ConditionScreen conditionScreen;
	GardenScreen gardenScreen;
	ConformationScreen conformationScreen;
	FinalScreen finalScreen;
	TipScreen tipScreen;
	ProgramPurposeScreen programPurposeScreen;
	
	/**
	 * Class Constructor
	 * @param canvasWidth1 the canvas width of the program window
	 * @param canvasHeight1 the canvas width of the program window
	 */
	
	public View(double canvasWidth1, double canvasHeight1) {
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
	
		InitializePanes();
		
	}
	

	/**
	 * This is a default method used to initialize all screens with the canvasHeight and 
	 * canvasWidth in the program windows.
	 */
	private void InitializePanes() {
		this.titleScreen = new TitleScreen(canvasWidth, canvasHeight);
		this.formatScreen = new FormatScreen(canvasWidth, canvasHeight);
		this.conditionScreen = new ConditionScreen(canvasWidth, canvasHeight);
		this.gardenScreen = new GardenScreen(canvasWidth, canvasHeight);
		this.conformationScreen = new ConformationScreen(canvasWidth, canvasHeight);
		this.finalScreen = new FinalScreen(canvasWidth, canvasHeight);
		this.tipScreen = new TipScreen(canvasWidth, canvasHeight);
		this.programPurposeScreen = new ProgramPurposeScreen(canvasWidth, canvasHeight);
	}
	

}
