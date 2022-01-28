package pkgMain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import javafx.scene.layout.Pane;

class ModelTest {

	Model model = new Model();
	@Test
	void test() throws IOException{
		assertEquals(model.initialPlants.size(), 0);
		assertEquals(model.conditionPlants.size(), 0);
		assertEquals(model.lepidopteras.size(), 0);
		assertEquals(model.sceneIndex, 0);
		assertEquals(model.sunValue, null);
		assertEquals(model.soilValue, null);
		assertEquals(model.sunValue, null);
		assertEquals(model.moistureValue, null);
		assertEquals(model.sunValue, null);
		assertEquals(model.soilValue, null);
		assertEquals(model.budget, 0);
		assertEquals(model.gardenHeight, 0);
		assertEquals(model.budget, 0);
		assertEquals(model.gardenWidth, 0);
		assertEquals(model.plantPicNames, null);
		assertEquals(model.line, null);
		assertEquals(model.plantPrice, 0.0);
		assertEquals(model.plantLepCount, 0);
		assertEquals(model.TranslateX, 0.0);
		assertEquals(model.TranslateY, 0.0);
		assertEquals(model.prefGardenLength, 0.0);
		assertEquals(model.prefGardenWidth, 0.0);
		assertEquals(model.pane, null);
		assertEquals(model.pane2, null);
		assertEquals(model.fbudget, 0);
		assertEquals(model.fleps, 0);
		assertEquals(model.Longer, 0.0);
		assertEquals(model.Shorter, 0.0);
		assertEquals(model.Ratio, 0.0);
		assertEquals(model.longerInFeet, 0.0);
	}
	
	@Test
	void testSetModel() {
		model.setModel(500, 500);
		assertEquals(model.gardenHeight, 500);
		assertEquals(model.gardenWidth, 500);
	}
	@Test
	void testINCSceneIndex() {
		model.INCsceneIndex();
		assertEquals(model.sceneIndex, 1);
	}
	@Test
	void testDecSceneIndex() {
		model.INCsceneIndex();
		model.DECsceneIndex();
		assertEquals(model.sceneIndex, 0);
	}
	@Test
	void testGetBackToTitle() {
		model.INCsceneIndex();
		model.INCsceneIndex();
		model.getBackToTitle();
		assertEquals(model.sceneIndex, 0);
	}
	@Test
	void testGetToTipScreen() {
		model.getToTipScreen();
		assertEquals(model.sceneIndex, 6);
	}
	@Test
	void testGetToPurposeScreen() {
		model.getToPurposeScreen();
		assertEquals(model.sceneIndex, 7);
	}
	@Test
	void testGetToFormatScreen() {
		model.getToFormatScreen();
		assertEquals(model.sceneIndex, 1);
	}
	@Test
	void testInitializePlants() throws IOException {
		model.initializePlants();
		assertEquals("Quercus-alba", model.initialPlants.get(0).scientificName);
	}
	@Test
	void testConditionsPlantsSet() throws IOException {
		model.initializePlants();
		model.setSun("Part Shade");
		model.setSoil("Medium");
		model.setMoisture("Loamy");
		model.shorterInFeet = 100.0;
		model.conditionPlantsSet();
		assertEquals(model.conditionPlants.get(0).scientificName, "Quercus-alba");
		assertEquals(model.plantPicNames.get(0), "Quercus-alba");
	}
	@Test
	void testGetSceneIndex() {
		assertEquals(model.getSceneIndex(), 0);
	}
	@Test
	void testSetTranslateX() {
		model.setTranslateX(50.0);
		assertEquals(model.TranslateX, 50.0);
	}
	@Test
	void testSetTranslateY() {
		model.setTranslateY(75.0);
		assertEquals(model.TranslateY, 75.0);
	}
	@Test
	void testGetTranslateX() {
		model.setTranslateX(50.0);
		assertEquals(model.getTranslateX(), 50.0);
	}
	@Test
	void testGetTranslateY() {
		model.setTranslateY(75.0);
		assertEquals(model.getTranslateY(), 75.0);
	}
	@Test
	void testSetScreenIndex() {
		model.setScreenIndex(3);
		assertEquals(model.getSceneIndex(), 3);
	}
	@Test
	void testSetBudget() {
		model.setBudget("300");
		assertEquals(model.budget, 300);
		Model m = new Model();
		m.setBudget("Hello");
		assertEquals(100, m.budget);
	}
	@Test
	void testSetSun() {
		model.setSun("Full Shade");
		assertEquals(model.sunValue, "Full Shade");
	}
	@Test
	void testSetSoil() {
		model.setSoil("Sandy");
		assertEquals(model.soilValue, "Sandy");
	}
	@Test
	void testSetMoisture() {
		model.setMoisture("Dry");
		assertEquals(model.moistureValue, "Dry");
	}
	@Test
	void testSetPrefLength() {
		model.setPrefLength(3.0);
		assertEquals(model.prefGardenLength, 3.0);
	}
	@Test
	void testSetPrefWidth() {
		model.setPrefWidth(3.0);
		assertEquals(model.prefGardenWidth, 3.0);
	}
	@Test
	void testSetPane() {
		Pane p = new Pane();
		model.setPane(p);
		assertEquals(model.pane, p);
	}
	@Test
	void testClearPane() {
		Pane p = new Pane();
		model.setPane(p);
		model.clearPane();
		assertEquals(model.pane, p);
	}
	@Test
	void testSetPane2() {
		Pane p = new Pane();
		model.setPane2(p);
		assertEquals(model.pane2, p);
	}
	@Test
	void testSetBandL() {
		model.setBandL(100, 5);
		assertEquals(model.fbudget, 100);
		assertEquals(model.fleps, 5);
	}
	@Test
	void testSetLandS() {
		model.setLandS(15.0, 12.0, 12.0/15.0);
		assertEquals(model.Longer, 15.0);
		assertEquals(model.Shorter, 12.0);
		assertEquals(model.Ratio, 12.0/15.0);
	}
}
