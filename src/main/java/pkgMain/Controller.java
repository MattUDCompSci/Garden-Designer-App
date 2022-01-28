package pkgMain;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The Controller Class for the Garden Designer Software
 * <p>
 * This class controls all interation between the model (Plant and Leps) and the View
 * (Different Screens)
 * @author Zack Luan, Matthew Weis, Mayson Light, Bryan Paliska, Ben Newlin
 *
 */
public class Controller extends Application {
	
	/**
	 * Check if certain point in program is running
	 */
	boolean tCIR = true;
	
	/**
	 * Stage
	 */
	
	Stage gardenDesign;
	
	/**
	 * Model 
	 */
	Model model;
	/**
	 * View
	 */
	View view;
	
	/**
	 * Height of the Canvas
	 */
	final double canvasWidth = 1200;
	/**
	 * Width of the Canvas
	 */
	final double canvasHeight = 600;
	/**
	 * Scale of displayed Garden plot
	 */
	final double gardenSize = canvasHeight * (5.0/6.0);
	/**
	 * Button handler for FormatScreen
	 */
	ButtonHandler bh = new ButtonHandler();
	/**
	 * Color handler for FormatScreen
	 */
	ColorHandler ch = new ColorHandler();
	/**
	 * MouseHandler for FormatScreen and GardenScreen
	 */
	MouseHandler mh = new MouseHandler();
	

	/**
	 * Start method of the Application
	 * @param primaryStage the Stage used in the Application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		gardenDesign = primaryStage; //Initialize gardenDisgn as the main Stage
		
		/*
		 * Initializing Model and View
		 */
		model = new Model();
		model.initializePlants();
		view = new View(canvasWidth, canvasHeight);
		
		
		//gardenDesign.setScene(view.formatScreen.scene);
		//model.setScreenIndex(1);
		
		updateScene();
		gardenDesign.setTitle("Garden");
		gardenDesign.show();
		
		
	}
	
    /**
     * Switches Scene forward
     * @param e event of forward button being pushed
     * @throws IOException 
     */
	private void switchSceneup(ActionEvent e) throws IOException {
		model.INCsceneIndex();
		
		updateScene();
	}
	
	/**
     * Switches Scene backward
     * @param e event of backward button being pushed
	 * @throws IOException 
     */
	private void switchScenedown(ActionEvent e) throws IOException {
		model.DECsceneIndex();
		
		updateScene();
	}
	
	/**
	 * Switch the scene back to the title
	 * @param e event of back button being pushed on TipScreen or ProgramPurposeScreen
	 * @throws IOException 
	 */
	private void switchBackToTitle(ActionEvent e) throws IOException {
		model.getBackToTitle();
		
		updateScene();
	}
	
	/**
	 * Changes the Screen to the TipScreen
	 * @param e event that Gardening Tips button was pushed
	 * @throws IOException 
	 */
	private void switchToTipScreen(ActionEvent e) throws IOException {
		model.getToTipScreen();
		
		updateScene();
	}
	
	/**
	 * Changes the Screen to the ProgramPurposeScreen
	 * @param e event that About Us button was pushed
	 * @throws IOException 
	 */
	private void switchToPurposeScreen(ActionEvent e) throws IOException {
		model.getToPurposeScreen();
		
		updateScene();
	}
	
	/**
	 * Changes the Screen to the FormatScreen
	 * @param e event that About Us button was pushed
	 * @throws IOException 
	 */
	private void switchToFormatScreen(ActionEvent e) throws IOException {
		model.getToFormatScreen();
		
		updateScene();
	}
	
	/**
	 * Scene action (Set actions)
	 * <p>
	 * This method changes the scene whenever a button is pushed on the button pane
	 * @throws IOException 
	 */
	private void updateScene() throws IOException {
		if (model.getSceneIndex() == 0) {
			gardenDesign.setScene(view.titleScreen.scene);
			try {
				FileInputStream fis = new FileInputStream("gardens/savedGarden0.ser");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Garden g = (Garden)ois.readObject();
	            System.out.println("Garden Width: " + g.gardenWidth + "\nGarden Heiht: " + g.gardenHeight);
	            for(SavedImageView siv : g.gardenPlantPics) {
	            	System.out.println(siv.imageLoc + "\n");
	            	System.out.println(siv.xloc + "\n");
	            	System.out.println(siv.width + "\n");
	            }
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			view.titleScreen.forwardButton.setOnAction(e -> {
				try {
					switchSceneup(e);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			});
			view.titleScreen.tipsButton.setOnAction(e -> {
				try {
					switchToTipScreen(e);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			view.titleScreen.infoButton.setOnAction(e -> {
				try {
					switchToPurposeScreen(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

		}
		
		if (model.getSceneIndex() == 1) {
			gardenDesign.setScene(view.formatScreen.scene);
			view.formatScreen.forwardButton.setOnAction(e -> {
				try {
					updateThenSwitchSceneUp(e);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			view.formatScreen.backwardButton.setOnAction(e -> {
				try {
					switchScenedown(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			view.formatScreen.Confirm.setOnAction(e -> confirm(e));
			view.formatScreen.Clear.setOnAction(e -> clear(e));
		}
		
		if (model.getSceneIndex() == 2) {
			gardenDesign.setScene(view.conditionScreen.scene);
			view.conditionScreen.forwardButton.setOnAction(e -> {
				try {
					switchSceneup(e);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			view.conditionScreen.backwardButton.setOnAction(e -> {
				try {
					switchScenedown(e);
					model.lepCount = 0;
					model.budget = Integer.parseInt(view.conditionScreen.getInputBudget());
					model.cost = 0;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});	
		}
		
		if (model.getSceneIndex() == 3) {
			model.setBudget(view.conditionScreen.getInputBudget());
			model.setSun(view.conditionScreen.getSunCondition());
			model.setSoil(view.conditionScreen.getSoilCondition());
			model.setMoisture(view.conditionScreen.getMoistureCondition());
			model.conditionPlantsSet();
			
			if(gardenDesign.getScene().equals(view.conformationScreen.scene)) {
				model.setBudget(view.gardenScreen.sBudget);
			}
			gardenDesign.setScene(view.gardenScreen.scene);
			
			view.gardenScreen.forwardButton.setOnAction(e -> {
				try {
					updateThenSwitchSceneUp2(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			view.gardenScreen.backwardButton.setOnAction(e -> {
				try {
					switchScenedown(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
//			view.gardenScreen.setPlantsLists(model.conditionPlants, model.plantPics);
//			//view.gardenScreen.setGardenPane(model.pane, model.sunValue, model.soilValue, model.moistureValue, model.budget);
//			
//			//view.gardenScreen.borderPane.setLeft(view.gardenScreen.setPlantPane());
//			view.gardenScreen.borderPane.setCenter(view.gardenScreen.setGardenPane(model.pane, model.prefGardenWidth, 
//					model.prefGardenLength));
//			view.gardenScreen.borderPane.setRight(view.gardenScreen.setConditionPane(model.budget, model.sunValue, 
//					model.soilValue, model.moistureValue));
//			
//
//			
//			/*
//			 * TEST
//			 */
//			
//			view.gardenScreen.testBudget.setOnAction(bh);
//			view.gardenScreen.Clear.setOnAction(bh);
			
			
			view.gardenScreen.Initial(model.conditionPlants, model.plantPicNames,
					model.budget, model.sunValue, model.soilValue, model.moistureValue,
					model.pane, model.Longer, model.Shorter, model.lepCount, model.cost, model.longerInFeet, model.shorterInFeet);
			
			view.gardenScreen.testBudget.setOnAction(e -> {
				System.out.println("Testing Budget");
				view.gardenScreen.ChangeBandL(5, 1, true, model.budget);
			});
			
			view.gardenScreen.testClear.setOnAction(e -> {
				System.out.println("Test Clear");
				view.gardenScreen.ChangeGarden(model.pane, model.prefGardenWidth, model.prefGardenLength, model.budget);
				DnDEvent();
			});
			

			DnDEvent();

			view.gardenScreen.plantPics.setOnDragDetected(new EventHandler <MouseEvent>()
			{
	            public void handle(MouseEvent event)
	            {
	            	view.gardenScreen.plantPics.startFullDrag();
	            	event.consume();
	            }
	        });
			
			view.gardenScreen.Garden.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
			{
	            public void handle(MouseDragEvent event)
	            {
	            	int index = view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex();
	            	ImageView selected = view.gardenScreen.plantPics.getSelectionModel().getSelectedItem();

	            	ImageView iv1 = new ImageView(selected.getImage()); 

	            	double plantScale = gardenSize*view.gardenScreen.plants.get(index).spread/model.longerInFeet;
	            	iv1.setFitHeight(plantScale);
	            	iv1.setFitWidth(plantScale);
	            	double clipScale = plantScale / 2.0;
	            	iv1.setClip(new Circle(clipScale, clipScale, clipScale));
	            	
	            	//put dragged Node back into list in same place
	            	view.gardenScreen.backingList.set(index, selected);

	            	iv1.setTranslateX(event.getSceneX() - view.gardenScreen.Garden.getLayoutX() - 
	            			view.gardenScreen.plantPics.getWidth());
	            	iv1.setTranslateY(event.getSceneY() - view.gardenScreen.Garden.getLayoutY());

	            	iv1.setOnMousePressed(event1 -> pressed(event1));
	            	iv1.setOnMouseDragged(event2 -> drag(event2));
	            	iv1.setOnMouseReleased(event3 -> released(event3));     
			
	            	Tooltip.install(iv1, new Tooltip(view.gardenScreen.plants.get(index).toString()));
			
	            	view.gardenScreen.Garden.getChildren().add(iv1);
	            	iv1.toFront();
	            	if(model.addedPlants.contains(view.gardenScreen.plants.get(index))) {
	            		view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price, 0, true, model.budget);
	            	}
	            	else {
	            		model.addedPlants.add(view.gardenScreen.plants.get(index));
	            		view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount, true, model.budget);
	            	}
	            	iv1.setOnMouseClicked(event4 ->{
	            		if(event4.getButton() == MouseButton.SECONDARY) {
	            			view.gardenScreen.Garden.getChildren().remove(iv1);
	            			view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price * -1, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount * -1, true, model.budget);
	            			boolean hasAnotherPlant = false;
	            			for(Object o : view.gardenScreen.Garden.getChildren()) {
	            				if(o instanceof ImageView) {
	            					ImageView c = (ImageView)o;
	            					if(c.getImage().equals(iv1.getImage())) {
	            						hasAnotherPlant = true;
	            					}
	            				}
	            			}
	            			if(hasAnotherPlant) {
	            				view.gardenScreen.ChangeBandL(0, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount, true, model.budget);
	            			}
	            		}
	            	});
	            	event.consume();
	            	}
			});
			

			view.gardenScreen.plantPics.hoverProperty().addListener(isNowHovered -> {
				int index = view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex();
				ImageView selected = view.gardenScreen.plantPics.getSelectionModel().getSelectedItem();
				if(index != -1) {
				    view.gardenScreen.plantInfo.setText(view.gardenScreen.plants.get(index).toString());
				    view.gardenScreen.plantPics.getTooltip().setText(view.gardenScreen.plants.get(index).toString() + "\nSpread: " + view.gardenScreen.plants.get(index).spread);
				}
			});
			
		}
		
		if (model.getSceneIndex() == 4) {
			gardenDesign.setScene(view.conformationScreen.scene);
			
			view.conformationScreen.testSetPane(model.pane2, model.fbudget, model.fleps);
			view.conformationScreen.forwardButton.setOnAction(e -> {
				try {
					switchSceneup(e);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			view.conformationScreen.backwardButton.setOnAction(e -> {
				try {
					model.cost = view.gardenScreen.iCost;
					model.budget = view.gardenScreen.iBudget;
					model.lepCount = view.gardenScreen.iLep;
					switchScenedown(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});	
		}
		
		if (model.getSceneIndex() == 5) {
			gardenDesign.setScene(view.finalScreen.scene);
			for(Plant p : model.addedPlants) {
				for(Lep l : p.featuredLeps) {
					model.lepidopteras.add(l);
				}
			}
			view.finalScreen.lepListView(model.lepidopteras);
			view.finalScreen.setGardenPane(model.pane2);
			Pane p = new Pane();
			p = view.gardenScreen.Condition;
			view.finalScreen.setGardenInfoPane(p);
			Garden savedGarden = new Garden(((double)model.prefGardenWidth), ((double)model.prefGardenLength), view.gardenScreen.iLep);
			for(Object o : model.pane2.getChildren()) {
				if(o instanceof ImageView) {
					ImageView iv = (ImageView)o;
					for(ImageView iv1 : view.gardenScreen.plantPictures) {
						if(iv.getImage().equals(iv1.getImage())) {
							SavedImageView siv = new SavedImageView(iv.getTranslateX(), iv.getTranslateY(), iv.getImage().getWidth(), iv.getImage().getHeight(), model.conditionPlants.get(view.gardenScreen.plantPictures.indexOf(iv1)).scientificName + ".png");
							savedGarden.gardenPlantPics.add(siv);
							break;
						}
					}
				}
			}
			writeToFile(savedGarden);
			
			disablePane(model.pane2);
			
			view.finalScreen.backwardButton.setOnAction(e -> {
				try {
					switchScenedown(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});	
		}
		
		if (model.getSceneIndex() == 6) {
			gardenDesign.setScene(view.tipScreen.scene);
			view.tipScreen.backwardButton.setOnAction(e -> {
				try {
					switchBackToTitle(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			view.tipScreen.forwardButton.setOnAction(e -> {
				try {
					switchToFormatScreen(e);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			});
			view.tipScreen.infoButton.setOnAction(e -> {
				try {
					switchToPurposeScreen(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
		
		if (model.getSceneIndex() == 7) {
			gardenDesign.setScene(view.programPurposeScreen.scene);
			view.programPurposeScreen.backwardButton.setOnAction(e -> {
				try {
					switchBackToTitle(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			view.programPurposeScreen.forwardButton.setOnAction(e -> {
				try {
					switchToFormatScreen(e);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			});
			view.programPurposeScreen.tipsButton.setOnAction(e -> {
				try {
					switchToTipScreen(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
	}


	/**
	 * Initializes Shape parameters for the FormatScreen
	 * @param e
	 * @return null
	 * @throws IOException 
	 */
	private Object updateThenSwitchSceneUp(ActionEvent e) throws IOException {
		
		if(tCIR) {
			System.out.println("update and switch scene up");
			System.out.println(view.formatScreen.gardenLayoutPane);
		}
		
		System.out.println("I am clicked");
		
		view.formatScreen.rbDraw.setSelected(false);
		view.formatScreen.rbCircle.setSelected(false);
		view.formatScreen.rbRect.setSelected(false);
		
		model.setPane(view.formatScreen.gardenLayoutPane);
		switchSceneup(e);
		return null;
	}
/**
 * Sets up the Garden Pane and Budget pane for the GardenScreen	
 * @param e event that GardenScreen is the screen to be loaded in
 * @return
 * @throws IOException 
 */
private Object updateThenSwitchSceneUp2(ActionEvent e) throws IOException {
		
		if(tCIR) {
			System.out.println("update and switch scene up");
			System.out.println(view.gardenScreen.Garden);
		}
		
		model.setPane2(view.gardenScreen.Garden);
		model.setBandL(view.gardenScreen.iBudget, view.gardenScreen.iLep);
		switchSceneup(e);
		return null;
	}

/**
 * Clears the LepCount and Budget on the GardenScreen
 * @param e event that clear button on GardenScreen is pushed
 * @return
 */
	private Object clear(ActionEvent e) {
		view.formatScreen.gardenLength.setText("0");
		view.formatScreen.gardenWidth.setText("0");
		return null;
	}

	/**
	 * Confirms the Garden length and width input to the FormatScreen
	 */
	private Object confirm(ActionEvent e) {
		
		try {
				
				System.out.println(view.formatScreen.gardenLength.getUserData());
				
				double y = Double.parseDouble(view.formatScreen.gardenLength.getText());
				double x = Double.parseDouble(view.formatScreen.gardenWidth.getText());
				
				if (x == y) {
					model.setPrefLength(y);
					model.setPrefWidth(x);
					model.longerInFeet = x;
					model.shorterInFeet = y;
				} else if (x < y) {
					model.setPrefLength(y);
					model.setPrefWidth(x);
					model.longerInFeet = y;
					model.shorterInFeet = x;
				} else {
					model.setPrefLength(x);
					model.setPrefWidth(y);
					model.longerInFeet = x;
					model.shorterInFeet = y;
				}
				
				

				view.formatScreen.compareLength(x, y);
				
				model.setLandS(view.formatScreen.Longer, view.formatScreen.Shorter, view.formatScreen.ratio);
				
				view.formatScreen.createGarden(view.formatScreen.Longer, view.formatScreen.Shorter, model.prefGardenLength, model.prefGardenWidth);
				
			view.formatScreen.borderPane.setCenter(view.formatScreen.gardenLayoutPane);
			
			if (tCIR) {
				System.out.println(model.prefGardenLength);
				System.out.println(model.prefGardenWidth);
			}
			
			
		} catch(NumberFormatException exp) {
			System.out.println("Please type an integer");
		}
		
		if (tCIR) {
			System.out.println("I am here");
		}
		
		view.formatScreen.borderPane.setLeft(view.formatScreen.leftPane);
		
		formatScreenDDI();

		return null;
	}
	
	/**
	 * Creates the Actions for the FormatScreen
	 */
	private void formatScreenDDI() {
		view.formatScreen.Draw.setOnAction(e -> drawScreen());
		view.formatScreen.Divide.setOnAction(e -> divideScreen());
		view.formatScreen.Import.setOnAction(e -> importScreen());
		view.formatScreen.Back.setOnAction(bh);
	}

	/**
	 * Gets an Image from the File Explorer
	 * @return null
	 */
	private Object importScreen() {
		if (tCIR) {
			System.out.println("I am here import");
		}
		
		view.formatScreen.ddiPane.getChildren().clear();
		view.formatScreen.ddiPane.getChildren().add(view.formatScreen.importPane);
		
		view.formatScreen.eraseBtn3.setOnAction(bh);
		
		view.formatScreen.ImportFrom.setOnAction(bh);
		view.formatScreen.ImportGarden.setOnAction(bh);
		
		return null;
	}

	/**
	 * Divides the FormatScreen and sets the actions for the GardenLayoutPane when Divide is selected
	 * @return null
	 */
	private Object divideScreen() {
		if (tCIR) {
			System.out.println("I am here divide");
		}
		view.formatScreen.ddiPane.getChildren().clear();
		view.formatScreen.ddiPane.getChildren().add(view.formatScreen.dividePane);
		
		view.formatScreen.gardenLayoutPane.setOnMouseClicked(mh);
		view.formatScreen.gardenLayoutPane.setOnMousePressed(mh);
		view.formatScreen.gardenLayoutPane.setOnMouseDragged(mh);
		view.formatScreen.gardenLayoutPane.setOnMouseReleased(mh);
		
		view.formatScreen.eraseBtn.setOnAction(bh);
		view.formatScreen.colorCombo.setOnAction(ch);
		
		return null;
	}

	/**
	 * Divides the FormatScreen and sets the actions for the GardenLayoutPane when Draw is selected
	 * @return null
	 */
	private Object drawScreen() {
		if (tCIR) {
			System.out.println("I am here draw");
		}
		view.formatScreen.ddiPane.getChildren().clear();
		view.formatScreen.ddiPane.getChildren().add(view.formatScreen.drawPane);
		view.formatScreen.startCanvas(view.formatScreen.Longer, view.formatScreen.Shorter);
		
		
		view.formatScreen.eraseBtn2.setOnAction(bh);
		view.formatScreen.cp.setOnAction(e -> {
			view.formatScreen.gc.setStroke(view.formatScreen.cp.getValue());
		});
		
		view.formatScreen.gardenLayoutPane.setOnMouseClicked(mh);
		view.formatScreen.gardenLayoutPane.setOnMousePressed(mh);
		view.formatScreen.gardenLayoutPane.setOnMouseDragged(mh);
		view.formatScreen.gardenLayoutPane.setOnMouseReleased(mh);
		
		return null;
	}
	
	/**
	 * Mouse event handlers for Format Screen
	 * @author Zack Luan
	 */
	private class MouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			
			if (view.formatScreen.rbRect.isSelected()) {
				if (tCIR) {
					System.out.println("rectangle is going to be drawn");
				}
				
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
					view.formatScreen.rect = new Rectangle();
					view.formatScreen.rect.setStroke(Color.BLACK);
					view.formatScreen.rect.setFill(Color.WHITE);
					view.formatScreen.rect.setX(event.getX());
					view.formatScreen.rect.setY(event.getY());

				} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {

					Double dx = event.getX() - view.formatScreen.rect.getX(); 
					Double dy = event.getY() - view.formatScreen.rect.getY();
					if (dx < 0) {
						if (dy < 0) {
							view.formatScreen.rect.setTranslateX(dx);
							view.formatScreen.rect.setWidth(-dx);
							view.formatScreen.rect.setTranslateY(dy);
							view.formatScreen.rect.setHeight(-dy);
						} else if (dy > 0) {
							view.formatScreen.rect.setTranslateX(dx);
							view.formatScreen.rect.setWidth(-dx);
							view.formatScreen.rect.setTranslateY(0);
							view.formatScreen.rect.setHeight(dy);
						}
					} else if (dx > 0) {
						if (dy < 0) {
							view.formatScreen.rect.setTranslateX(0);
							view.formatScreen.rect.setWidth(dx);
							view.formatScreen.rect.setTranslateY(dy);
							view.formatScreen.rect.setHeight(-dy);
						} else if (dy > 0) {
							view.formatScreen.rect.setTranslateX(0);
							view.formatScreen.rect.setWidth(dx);
							view.formatScreen.rect.setTranslateY(0);
							view.formatScreen.rect.setHeight(dy);
						}
					}
					try {

						view.formatScreen.gardenLayoutPane.getChildren().add(view.formatScreen.rect);

					}
					catch( IllegalArgumentException e){
						System.out.println("Something is wrong with adding into gardenLayoutPane");
						
						if (tCIR) {
							System.out.println(view.formatScreen.gardenLayoutPane);
						}
					}

				} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
					view.formatScreen.rect.setFill(view.formatScreen.pickedColor);
					view.formatScreen.shapeList.add(view.formatScreen.rect);

					try{
						view.formatScreen.gardenLayoutPane.getChildren().addAll(view.formatScreen.shapeList);
					}
					catch( IllegalArgumentException e){
						System.out.println("Something is wrong with changing the color of gardenlayoutScreen");
					}
					System.out.println(view.formatScreen.shapeList.size());
				
				}
				event.consume();
			} else if (view.formatScreen.rbCircle.isSelected()) {
				if (tCIR) {
					System.out.println("circle is going to be drawn");
				}
				
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
					view.formatScreen.circle = new Circle();
					view.formatScreen.circle.setStroke(Color.BLACK);
					view.formatScreen.circle.setFill(Color.WHITE);
					view.formatScreen.circle.setCenterX(event.getX());
					view.formatScreen.circle.setCenterY(event.getY());
					
					if (tCIR) {
						System.out.println(view.formatScreen.circle.getCenterX());
						System.out.println(view.formatScreen.circle.getCenterY());
					}

				} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
					
					try {

						Double dx = Math.abs(event.getX() - view.formatScreen.circle.getCenterX());
						//Double dy = event.getY() - view.formatScreen.circle.getCenterX();

						view.formatScreen.circle.setRadius(dx);
						view.formatScreen.gardenLayoutPane.getChildren().add(view.formatScreen.circle);
					} catch (IllegalArgumentException e) {
						System.out.print("");
					}
				} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
					view.formatScreen.shapeList.add(view.formatScreen.circle);
					view.formatScreen.circle.setFill(view.formatScreen.pickedColor);
					view.formatScreen.shapeList.clear();
				}
				event.consume();

			} else if (view.formatScreen.rbDraw.isSelected()) {
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
					view.formatScreen.gc.beginPath();
					view.formatScreen.gc.lineTo(event.getSceneX() - 400 - ((800 - view.formatScreen.Longer) / 2), 
							event.getSceneY() - ((550 - view.formatScreen.Shorter) / 2));
					view.formatScreen.gc.stroke();
				} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
					view.formatScreen.gc.lineTo(event.getSceneX() - 400 - ((800 - view.formatScreen.Longer) / 2), 
							event.getSceneY() - ((550 - view.formatScreen.Shorter) / 2));
					view.formatScreen.gc.stroke();
				}
				event.consume();
			}
			event.consume();
		}
	}

	/**
	 * Button Event handlers for FormatScreen
	 * @author Zack Luan
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if ((event.getSource() == view.formatScreen.eraseBtn) || (event.getSource() == view.formatScreen.eraseBtn2)
					|| (event.getSource() == view.formatScreen.eraseBtn3)) {
				// this will clear the entire canvas
				System.out.println("clear entire canvas expect length and width");
				view.formatScreen.gardenLayoutPane.getChildren().clear();
				view.formatScreen.gardenLayoutPane.setBackground(null);
				
				Text Width = new Text("Width: " + model.prefGardenWidth);
				Text Length = new Text("Length " + model.prefGardenLength);
				Width.setStyle("-fx-font: normal bold 12px ' serif' ");
				Length.setStyle("-fx-font: normal bold 12px ' serif' ");
				
				Width.setLayoutX(0);
				Width.setLayoutY(0);
				
				Length.setLayoutX(100);
				Length.setLayoutY(0);

				view.formatScreen.gardenLayoutPane.getChildren().addAll(Width, Length);
			} else if (event.getSource() == view.formatScreen.ImportFrom) {
				view.formatScreen.anchorpane.setPrefSize(400, 400);
				
				Stage stage = (Stage) view.formatScreen.borderPane.getScene().getWindow();
				
				view.formatScreen.filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image Files", 
						"*.png", "*.jpg"));
				
				
				
				File file = view.formatScreen.filechooser.showOpenDialog(stage);
				
				if (file != null) {
					Image image = new Image(file.toURI().toString(), model.Longer, 
							model.Shorter, false, true);
					
					BackgroundImage bi = new BackgroundImage(image,
							BackgroundRepeat.NO_REPEAT,
							BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.DEFAULT,
							BackgroundSize.DEFAULT);
					
					Background bg = new Background(bi);
					
					view.formatScreen.gardenLayoutPane.setBackground(bg);
					
					
				} else {
					System.out.println("Not a PNG");
				}
			} else if (event.getSource() == view.formatScreen.ImportGarden) {
				view.formatScreen.anchorpane.setPrefSize(400, 400);
				
				Stage stage = (Stage) view.formatScreen.borderPane.getScene().getWindow();
				
				view.formatScreen.serializedChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("serialized Files", 
						"*.ser"));
				
				
				
				File fileSer = view.formatScreen.serializedChooser.showOpenDialog(stage);
				
				if (fileSer != null) {
					try {
						FileInputStream fis = new FileInputStream(fileSer.toString());
			            ObjectInputStream ois = new ObjectInputStream(fis);
			            Garden g = (Garden)ois.readObject();
			            System.out.println("Garden Width: " + g.gardenWidth + "\nGarden Height: " + g.gardenHeight);
			            model.lepCount = (int)(g.lepCount);
			            System.out.println(model.lepCount);
			            for(SavedImageView siv : g.gardenPlantPics) {
			            	System.out.println(siv.imageLoc + "\n");
			            	System.out.println(siv.xloc + "\n");
			            	System.out.println(siv.width + "\n");
			            	ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/img/plants/" + siv.imageLoc)));
			            	String plantName = siv.imageLoc.substring(0, siv.imageLoc.length() - 4);
			            	Plant ivPlant = null;
			            	double ivSpread = 100;
			            	for(Plant p : model.initialPlants) {
			            		if(p.scientificName.equals(plantName)){
			            			ivSpread = p.spread;
			            			ivPlant = p;
			            			break;
			            		}
			            	}
			            	iv.setTranslateX(siv.xloc);
			            	iv.setTranslateY(siv.yloc);
			            	double ivScale = canvasHeight*(5.0/6.0)*ivSpread/model.longerInFeet;
			            	iv.setFitHeight(ivScale);
			            	iv.setFitWidth(ivScale);
			            	iv.setClip(new Circle(ivScale/2.0, ivScale/2.0, ivScale/2.0));
			            	Tooltip.install(iv, new Tooltip(ivPlant.toString()));
			            	if(ivScale <= canvasHeight * 5.0/6.0) {		 
				            	view.formatScreen.gardenLayoutPane.getChildren().add(iv);
				            	iv.setOnMousePressed(event1 -> pressed(event1));
				            	iv.setOnDragDetected(new EventHandler <MouseEvent>()
				    			{
				    	            public void handle(MouseEvent event)
				    	            {
				    	            	iv.startFullDrag();
				    	            	event.consume();
				    	            }
				    	        });
				            	iv.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
				    			{
				    	            public void handle(MouseDragEvent event)
				    	            {
				    	            	iv.setTranslateX(event.getSceneX() - view.formatScreen.gardenLayoutPane.getLayoutX() - 
				    	            			view.formatScreen.gardenLayoutPane.getWidth());
				    	            	iv.setTranslateY(event.getSceneY() - view.formatScreen.gardenLayoutPane.getLayoutY());

				    	            	iv.toFront();
				    	                event.consume();
				    	            }
				    	        });
			            	}
			            }
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					
				} else {
					System.out.println("Not a Garden");
				}
			} else if (event.getSource() == view.formatScreen.Back) {
				if (tCIR) {
					System.out.println("Reset");
				}
				
				view.formatScreen = new FormatScreen(canvasWidth, canvasHeight);
				try {
					updateScene();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			event.consume();
			
		}
		
	}
	
	/**
	 * Color Event handlers for FormatScreen
	 * @author Zack Luan
	 */
	private class ColorHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (tCIR) {
				System.out.println(view.formatScreen.colorCombo.getSelectionModel().getSelectedItem().toUpperCase());
			}
			
			if (view.formatScreen.colorCombo.getSelectionModel().getSelectedItem().toString() == "Beige") {
				if (tCIR) {
					System.out.println("Low currently");
				}
				view.formatScreen.pickedColor = Color.BEIGE;
			} else if (view.formatScreen.colorCombo.getSelectionModel().getSelectedItem().toString() == "Saddle-Brown") {
				if (tCIR) {
					System.out.println("Medium currently ");
				}
				view.formatScreen.pickedColor = Color.SADDLEBROWN;
			} else if (view.formatScreen.colorCombo.getSelectionModel().getSelectedItem().toString() == "Brown") {
				if (tCIR) {
					System.out.println("High currently");
				}
				view.formatScreen.pickedColor = Color.BROWN;
			}	
			
			event.consume();
			
		}
		
	}
	
	/**
	 * Event Handler for GardenScreen on mouse press
	 * @param event the event that mouse is pressed
	 */
	public void pressed(MouseEvent event) {
    	System.out.println("pressed");
		Node n = (Node)event.getSource();
		n.toFront();
    }
    
	/**
	 * Event Handler for GardenScreen on mouse click-and-drag
	 * @param event the event that mouse is clicked and dragged
	 */
	public void drag(MouseEvent event) {
		//System.out.println("ic mouse");
		Node n = (Node)event.getSource();
		n.setTranslateX(n.getTranslateX() - 10 + event.getX());
		n.setTranslateY(n.getTranslateY() - 10 + event.getY());
	}    
	/**
	 * Event Handler for GardenScreen on mouse release
	 * @param event the event that mouse is released
	 */
    public void released(MouseEvent event) {
    	System.out.println("released, Testing here");
		
    	Node n = (Node)event.getSource();
    	if(gardenDesign.getScene().equals(view.gardenScreen.scene)) {
    		if(view.gardenScreen.Garden.getChildren().contains(n)) {
        		System.out.print("Contrin");
        	}
        	
        	if (n.getTranslateX() <= -10 || n.getTranslateX() >= 520 || 
        			n.getTranslateY() <= -10 || n.getTranslateY() >= 430) {
        		view.gardenScreen.Garden.getChildren().remove(n);
        	}
    	}
    	
    	
    	
    	//System.out.println(n.getTranslateX());
    	//System.out.println(n.getTranslateY());
    	
    }
    /**
     * Method used to set up the Drag and Drop Handlers on the GardenScreen
     */
    public void DnDEvent() {
    	view.gardenScreen.plantPics.setOnDragDetected(new EventHandler <MouseEvent>()
		{
            public void handle(MouseEvent event)
            {
            	view.gardenScreen.plantPics.startFullDrag();
            	event.consume();
            }
        });
		
		view.gardenScreen.Garden.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
		{
			public void handle(MouseDragEvent event)
            {
            	int index = view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex();
            	ImageView selected = view.gardenScreen.plantPics.getSelectionModel().getSelectedItem();

            	//make a copy of selected ImageView to put in plot Pane
            	ImageView iv1 = new ImageView(selected.getImage()); 
            	/*if(view.gardenScreen.plants.get(index).price == 20) {
            		iv1.setFitHeight(100);
            		iv1.setFitWidth(100);
            		iv1.setClip(new Circle(50, 50, 50));
            	}
            	else {
            		iv1.setFitHeight(50);
            		iv1.setFitWidth(50);
            		iv1.setClip(new Circle(25, 25, 25));
            	}*/
            	double plantScale = gardenSize*view.gardenScreen.plants.get(index).spread/model.longerInFeet;
            	System.out.println(gardenSize);
            	System.out.println(view.gardenScreen.plants.get(index).spread);
            	System.out.println(model.Longer);
            	iv1.setFitHeight(plantScale);
            	iv1.setFitWidth(plantScale);
            	double clipScale = plantScale / 2.0;
            	iv1.setClip(new Circle(clipScale, clipScale, clipScale));
            	
            	//put dragged Node back into list in same place
            	view.gardenScreen.backingList.set(index, selected);

            	iv1.setTranslateX(event.getSceneX() - view.gardenScreen.Garden.getLayoutX() - 
				view.gardenScreen.plantPics.getWidth());
            	iv1.setTranslateY(event.getSceneY() - view.gardenScreen.Garden.getLayoutY());

            	iv1.setOnMousePressed(event1 -> pressed(event1));
            	iv1.setOnMouseDragged(event2 -> drag(event2));
            	iv1.setOnMouseReleased(event3 -> released(event3));
            	Tooltip.install(iv1, new Tooltip(view.gardenScreen.plants.get(index).toString()));

            	view.gardenScreen.Garden.getChildren().add(iv1);
            	iv1.toFront();
            	if(model.addedPlants.contains(view.gardenScreen.plants.get(index))) {
            		view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price, 0, true, model.budget);
            	}
            	else {
            		model.addedPlants.add(view.gardenScreen.plants.get(index));
            		view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount, true, model.budget);
            	}
            	iv1.setOnMouseClicked(event4 ->{
            		if(event4.getButton() == MouseButton.SECONDARY) {
            			view.gardenScreen.Garden.getChildren().remove(iv1);
            			view.gardenScreen.ChangeBandL((int)model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).price * -1, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount * -1, true, model.budget);
            			boolean hasAnotherPlant = false;
            			for(Object o : view.gardenScreen.Garden.getChildren()) {
            				if(o instanceof ImageView) {
            					ImageView c = (ImageView)o;
            					if(c.getImage().equals(iv1.getImage())) {
            						hasAnotherPlant = true;
            					}
            				}
            			}
            			if(hasAnotherPlant) {
            				view.gardenScreen.ChangeBandL(0, model.conditionPlants.get(view.gardenScreen.plantPics.getSelectionModel().getSelectedIndex()).lepCount, true, model.budget);
            			}
            		}
            	});
                event.consume();
            }
        });
    }
    /**
     * Writes the Garden Pane to a file
     * @param savedGarden the Garden Pane
     * @throws IOException Exception Garden pane can't be read
     */
    public void writeToFile(Garden savedGarden) throws IOException{
		try {
			File tempfile = new File("gardens/savedGarden0.ser");
			File file = new File("garden/png/savedGarden0.png");
			int i = 0;
			while(tempfile.exists()) {
				i++;
				tempfile = new File("gardens/savedGarden" + String.valueOf(i) + ".ser");
				file = new File("gardens/png/savedGarden" + String.valueOf(i) + ".png");
			}
			WritableImage writableImage = new WritableImage((int)model.pane2.getWidth(),
                    (int)model.pane2.getHeight());
            model.pane2.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
			FileOutputStream fos = new FileOutputStream(tempfile);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(savedGarden);
	        oos.close();
	        fos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Disables a pane
     * @param pane the pane to be disabled
     */
    public void disablePane(Pane pane) {
    	for( Node child : pane.getChildren() ){
            child.setDisable(true);
        }
    }
    
}
