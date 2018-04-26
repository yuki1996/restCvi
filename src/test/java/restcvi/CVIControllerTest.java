package restcvi;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.urouen.model.DateEven;

public class CVIControllerTest {
	@Test
    public final void dateValide() {
		String s = "12/12/2014";
		String[] els = s.split("/");
		int j = Integer.parseInt(els[0]);
		int m = Integer.parseInt(els[1]);
		int a = Integer.parseInt(els[2]);
		assertEquals(j, 12);
		assertEquals(els[0], "12");
    }
}
