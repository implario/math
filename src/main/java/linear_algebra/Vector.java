package linear_algebra;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Vector implements Tensor, Comparable<Vector> {

	public static final Vector ZERO_DIMENSION = new Vector();

	protected static final VectorFunction MULTIPLY_TO_CONSTANT = (vector, term) -> {
		double[] resultVector = vector.getCoordsClone();
		for (int i = 0; i < vector.dimension(); i++)
			resultVector[i] *= term.coords[0];
		return new Vector(resultVector);
	};

	private static final VectorFunction ADDER = (vector, term) -> {
		double[] resultVector;
		if (vector.dimension() >= term.dimension()) {
			resultVector = vector.getCoordsClone();
			for (int i = 0; i < term.dimension(); i++)
				resultVector[i] += term.coords[i];
		} else {
			resultVector = term.getCoordsClone();
			for (int i = 0; i < vector.dimension(); i++)
				resultVector[i] += vector.coords[i];
		}
		return new Vector(resultVector);
	};

	private static final VectorFunction NORMALIZER = (vector, term) ->
			new Ort(MULTIPLY_TO_CONSTANT.execute(vector, new Vector(1D / vector.length())).coords);

	private final double[] coords;

	public Vector(double... coords) {
		this.coords = coords;
	}

	public int dimension() {
		return coords.length;
	}

	public double length() {
		return Math.sqrt(squaredLength());
	}

	public double squaredLength() {
		double squaredLength = 0;
		for (double value : coords) {
			squaredLength += value * value;
		}
		return squaredLength;
	}

	public Ort normalize() {
		return (Ort) NORMALIZER.execute(this, ZERO_DIMENSION);
	}

	public Vector add(Vector anotherVector) {
		return ADDER.execute(this, anotherVector);
	}

	public Vector multiply(double multiplier) {
		return MULTIPLY_TO_CONSTANT.execute(this, new Vector(multiplier));
	}

	double[] getCoordsClone() {
		double[] clone = new double[coords.length];
		System.arraycopy(coords, 0, clone, 0, coords.length);
		return clone;
	}

	@Override
	public int compareTo(Vector vector) {
		return Double.compare(squaredLength(), vector.squaredLength());
	}

	@FunctionalInterface
	interface VectorFunction {

		Vector execute(Vector vector, Vector term);

	}
}