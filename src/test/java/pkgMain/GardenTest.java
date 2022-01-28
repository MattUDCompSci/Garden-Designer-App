package pkgMain;

import pkgMain.Garden;
import pkgMain.SavedImageView;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GardenTest {
	
	Garden g = new Garden(400, 300, 5);
	SavedImageView sourwood = new SavedImageView(0,0,10,10,"Oxydendrum-arboreum.png");
	SavedImageView inkberry = new SavedImageView(25,25,10,10,"Ilex-glabra.png");
	SavedImageView blackgum = new SavedImageView(30,30,10,10,"Nyssa-sylvatica.png");
	

	@Test
	public void testGarden() {
		assertEquals(400, g.gardenWidth);
		assertEquals(300, g.gardenHeight);
		assertEquals(5, g.lepCount);
	}
	
	@Test
	public void testGetGardenWidth() {
		assertEquals(400, g.getGardenWidth());
	}
	
	@Test
	public void testGetGardenHeight() {
		assertEquals(300, g.getGardenHeight());
	}
	
	@Test
	public void testGetGardenPlantPics() {
		g.gardenPlantPics.add(sourwood);
		g.gardenPlantPics.add(inkberry);
		assertEquals("Oxydendrum-arboreum.png", g.getGardenPlantPics().get(0).imageLoc);
		assertEquals("Ilex-glabra.png", g.getGardenPlantPics().get(1).imageLoc);
	}
	
	@Test
	public void testSetGardenHeight() {
		g.setGardenHeight(250);
		assertEquals(250, g.gardenHeight);
	}
	
	@Test
	public void testSetGardenWidth() {
		g.setGardenWidth(350);
		assertEquals(350, g.gardenWidth);
	}
	
	@Test
	public void testSetGardenPlantPics() {
		ArrayList<SavedImageView> plants = new ArrayList<SavedImageView>();
		plants.add(sourwood);
		plants.add(inkberry);
		plants.add(blackgum);
		g.setGardenPlantPics(plants);
		assertEquals("Oxydendrum-arboreum.png", g.gardenPlantPics.get(0).imageLoc);
		assertEquals("Ilex-glabra.png", g.gardenPlantPics.get(1).imageLoc);
		assertEquals("Nyssa-sylvatica.png", g.gardenPlantPics.get(2).imageLoc);
	}
	
	@Test
	public void testToString() {
		assertEquals("[Garden: gardenWidth=400.0 gardenHeight=300.0 gardenPlantPics=[]]", g.toString());
	}
	
	

}
