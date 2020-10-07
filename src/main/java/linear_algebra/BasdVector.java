package linear_algebra;

import lombok.AllArgsConstructor;

/**
 * @author func 07.10.2020
 * @project math
 */
@AllArgsConstructor
public class BasdVector extends Vector {

	private final Basis basis;

	public BasdVector(Basis basis, Vector vector) {
		super(vector.getCoordsClone());
		this.basis = basis;
		for (int i = 0; i < this.basis.getVectors().length; i++)
			vector.getCoords()[i] *= basis.getVectors()[i].getCoords()[i];
	}
}
