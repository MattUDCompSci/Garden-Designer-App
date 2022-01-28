package pkgMain;
import pkgMain.Plant;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
public class PlantTest {

	//Plant plant = new Plant();
	Plant plant1 = new Plant("Uvularia-grandiflora", "Largeflower bellwort", "https://bs.plantnet.org/image/o/f2ce6563abe5294a1072cf0ece06a6988f9e8672",
			"Loamy-Sandy", "Shade", "Medium", 6, 1, 1.0, new Lep("Danaus-plexippus", "Thing"), "null", "null");
	
	@Test
	public void testPlant() {
		assertEquals("Uvularia-grandiflora", plant1.scientificName);
		assertEquals("Largeflower bellwort", plant1.commonName);
		List<String> soil = Arrays.asList("Loamy-Sandy".split("-"));
		for(String s : soil) {
			assertEquals(true, plant1.soil.contains(s));
		}
		List<String> sun = Arrays.asList("Shade".split("-"));
		for(String s : sun) {
			assertEquals(true, plant1.sun.contains(s));
		}
		List<String> moisture = Arrays.asList("Medium".split("-"));
		for(String s : moisture) {
			assertEquals(true, plant1.moisture.contains(s));
		}
		assertEquals(6, plant1.price);
		assertEquals(1, plant1.lepCount);
		assertEquals(1.0, plant1.spread);
	}
	
	@Test
	public void testGetConditions() {
		assertEquals(true, plant1.getConditions("Loamy", "Shade", "Medium"));
		assertEquals(false, plant1.getConditions("Loamy", "Full Shade", "Medium"));
	}
	
	@Test
	public void testToString() {
		assertEquals("Common Name: Largeflower bellwort\nScientific Name: Uvularia-grandiflora", plant1.toString());
	}


}
