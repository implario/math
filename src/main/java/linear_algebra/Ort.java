package linear_algebra;

public class Ort extends Vector {

	public Ort(double... dots) {
		super(dots);
		if (squaredLength() != 1)
			throw new IllegalArgumentException("ort must have length 1");
	}

	public Vector denormalize(double length) {
		return MULTIPLY_TO_CONSTANT.execute(this, new Vector(length));
	}
}