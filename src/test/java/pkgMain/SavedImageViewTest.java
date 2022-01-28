package pkgMain;

import static org.junit.jupiter.api.Assertions.*;
import pkgMain.SavedImageView;
import pkgMain.SavedImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SavedImageViewTest {
	
	SavedImageView siv = new SavedImageView(10,10,50,50,"ilex-opaca.png");

	@Test
	public void savedImageViewTest() {
		assertEquals(10, siv.xloc);
		assertEquals(10, siv.yloc);
		assertEquals(50, siv.height);
		assertEquals(50, siv.width);
		assertEquals("ilex-opaca.png", siv.imageLoc);
	}

}
