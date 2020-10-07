package linear_algebra;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Vector implements Tensor, Comparable<Vector> {

	public static final Vector ZERO_DIMENSION = new Vector();

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
		return new Ort(multiply(1D / length()).coords);
	}

	public Vector add(Vector term) {
		double[] resultVector;
		if (dimension() >= term.dimension()) {
			resultVector = getCoordsClone();
			for (int i = 0; i < term.dimension(); i++)
				resultVector[i] += term.coords[i];
		} else {
			resultVector = term.getCoordsClone();
			for (int i = 0; i < dimension(); i++)
				resultVector[i] += coords[i];
		}
		return new Vector(resultVector);
	}

	public Vector multiply(double multiplier) {
		Vector term = new Vector(multiplier);
		double[] resultVector = getCoordsClone();
		for (int i = 0; i < dimension(); i++)
			resultVector[i] *= term.coords[0];
		return new Vector(resultVector);
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
}