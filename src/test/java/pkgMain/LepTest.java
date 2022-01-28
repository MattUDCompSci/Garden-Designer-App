package pkgMain;
import pkgMain.Lep;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LepTest {

	@Test
	void testLep() {
		Lep lep = new Lep("Danaus-plexippus", "Monarch Butterfly");
		assertEquals("Monarch Butterfly", lep.name);
		assertEquals("Danaus-plexippus", lep.scientificName);
	}
}
