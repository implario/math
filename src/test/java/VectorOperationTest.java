import linear_algebra.Ort;
import linear_algebra.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author func 07.10.2020
 * @project math
 */
public class VectorOperationTest {

	@Test
	public void testMultiply() {
		Assertions.assertEquals(new Vector(0, 2, 1F), new Vector(0, 1, .5).multiply(2));
	}

	@Test
	public void testAdd() {
		Assertions.assertEquals(new Vector(0, 0, 0), new Vector(-2, -.5, 0).add(new Vector(2, .5, 0)));
	}

	@Test
	public void testAddWithDiffDimension() {
		Assertions.assertEquals(new Vector(1, 2, 3, 4, 5), new Vector(1, 2, 3).add(new Vector(0, 0, 0, 4, 5)));
	}

	@Test
	public void testNormalize() {
		Assertions.assertEquals(new Ort(0, 1, 0), new Vector(0, 5353, 0).normalize());
	}

	@Test
	public void testDenormalize() {
		Assertions.assertEquals(new Vector(0, 4, 0), new Ort(0, 1, 0).denormalize(4));
	}

	@Test
	public void testOrtException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Ort(0, 1.3, 0));
	}
}
