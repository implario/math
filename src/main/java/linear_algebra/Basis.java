package linear_algebra;

import lombok.Getter;

/**
 * @author func 07.10.2020
 * @project math
 */
@Getter
public class Basis {

	private final Ort[] vectors;

	public Basis(Ort[] vectors) {
		int dimension = vectors[0].dimension();
		for (Ort ort : vectors)
			if (ort.dimension() != dimension)
				throw new IllegalArgumentException("All orts in basis must be in one dimension.");
		this.vectors = vectors;
	}
}