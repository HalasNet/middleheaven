package org.middleheaven.quantity.math.structure;

import org.middleheaven.quantity.math.vectorspace.Matrix;

public class DefaultMatrixInvertion implements MatrixInvertionAlgorithm{

	@Override
	public <F extends FieldElement<F, ?>> Matrix<F> invert(Matrix<F> matrix) {
		
		F det = matrix.determinant();
		
		if (!matrix.isSquare() || det.isZero()){
			throw new ArithmeticException("Inverse matrix does not exist");
		}

		return matrix.adjoint().times(det.inverse()).transpose();
		
	}

}
