package net.three4j.math;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

public class Matrix3 {

	public double[] elements;

	public final boolean isMatrix3 = true;

	public Matrix3() {

		this.elements = new double[] {

				1, 0, 0, 0, 1, 0, 0, 0, 1

		};

	}

	public Matrix3 set(double n11, double n12, double n13, double n21, double n22, double n23, double n31, double n32,
			double n33) {

		final double[] te = this.elements;

		te[0] = n11;
		te[1] = n21;
		te[2] = n31;
		te[3] = n12;
		te[4] = n22;
		te[5] = n32;
		te[6] = n13;
		te[7] = n23;
		te[8] = n33;

		return this;

	}

	public Matrix3 identity() {

		this.set(

				1, 0, 0, 0, 1, 0, 0, 0, 1

		);

		return this;

	}

	public Matrix3 clone() {

		return new Matrix3().fromArray(this.elements);

	}

	public Matrix3 copy(Matrix3 m) {

		final double[] te = this.elements;
		final double[] me = m.elements;

		te[0] = me[0];
		te[1] = me[1];
		te[2] = me[2];
		te[3] = me[3];
		te[4] = me[4];
		te[5] = me[5];
		te[6] = me[6];
		te[7] = me[7];
		te[8] = me[8];

		return this;

	}

	public Matrix3 extractBasis(Vector3 xAxis, Vector3 yAxis, Vector3 zAxis) {

		xAxis.setFromMatrix3Column(this, 0);
		yAxis.setFromMatrix3Column(this, 1);
		zAxis.setFromMatrix3Column(this, 2);

		return this;

	}

	public Matrix3 setFromMatrix4(Matrix4 m) {

		final double[] me = m.elements;

		this.set(

				me[0], me[4], me[8], me[1], me[5], me[9], me[2], me[6], me[10]

		);

		return this;

	}

	public Matrix3 multiply(Matrix3 m) {

		return this.multiplyMatrices(this, m);

	}

	public Matrix3 premultiply(Matrix3 m) {

		return this.multiplyMatrices(m, this);

	}

	public Matrix3 multiplyMatrices(Matrix3 a, Matrix3 b) {

		final double[] ae = a.elements;
		final double[] be = b.elements;
		final double[] te = this.elements;

		final double a11 = ae[0], a12 = ae[3], a13 = ae[6];
		final double a21 = ae[1], a22 = ae[4], a23 = ae[7];
		final double a31 = ae[2], a32 = ae[5], a33 = ae[8];

		final double b11 = be[0], b12 = be[3], b13 = be[6];
		final double b21 = be[1], b22 = be[4], b23 = be[7];
		final double b31 = be[2], b32 = be[5], b33 = be[8];

		te[0] = a11 * b11 + a12 * b21 + a13 * b31;
		te[3] = a11 * b12 + a12 * b22 + a13 * b32;
		te[6] = a11 * b13 + a12 * b23 + a13 * b33;

		te[1] = a21 * b11 + a22 * b21 + a23 * b31;
		te[4] = a21 * b12 + a22 * b22 + a23 * b32;
		te[7] = a21 * b13 + a22 * b23 + a23 * b33;

		te[2] = a31 * b11 + a32 * b21 + a33 * b31;
		te[5] = a31 * b12 + a32 * b22 + a33 * b32;
		te[8] = a31 * b13 + a32 * b23 + a33 * b33;

		return this;

	}

	public Matrix3 multiplyScalar(double s) {

		final double[] te = this.elements;

		te[0] *= s;
		te[3] *= s;
		te[6] *= s;
		te[1] *= s;
		te[4] *= s;
		te[7] *= s;
		te[2] *= s;
		te[5] *= s;
		te[8] *= s;

		return this;

	}

	public double determinant() {

		final double[] te = this.elements;

		final double a = te[0], b = te[1], c = te[2], d = te[3], e = te[4], f = te[5], g = te[6], h = te[7], i = te[8];

		return a * e * i - a * f * h - b * d * i + b * f * g + c * d * h - c * e * g;

	}

	public Matrix3 invert() {

		final double[] te = this.elements;

		final double n11 = te[0], n21 = te[1], n31 = te[2], n12 = te[3], n22 = te[4], n32 = te[5], n13 = te[6],
				n23 = te[7], n33 = te[8],

				t11 = n33 * n22 - n32 * n23, t12 = n32 * n13 - n33 * n12, t13 = n23 * n12 - n22 * n13,

				det = n11 * t11 + n21 * t12 + n31 * t13;

		if (det == 0)
			return this.set(0, 0, 0, 0, 0, 0, 0, 0, 0);

		final double detInv = 1 / det;

		te[0] = t11 * detInv;
		te[1] = (n31 * n23 - n33 * n21) * detInv;
		te[2] = (n32 * n21 - n31 * n22) * detInv;

		te[3] = t12 * detInv;
		te[4] = (n33 * n11 - n31 * n13) * detInv;
		te[5] = (n31 * n12 - n32 * n11) * detInv;

		te[6] = t13 * detInv;
		te[7] = (n21 * n13 - n23 * n11) * detInv;
		te[8] = (n22 * n11 - n21 * n12) * detInv;

		return this;

	}

	public Matrix3 transpose() {

		double tmp;
		final double[] m = this.elements;

		tmp = m[1];
		m[1] = m[3];
		m[3] = tmp;
		tmp = m[2];
		m[2] = m[6];
		m[6] = tmp;
		tmp = m[5];
		m[5] = m[7];
		m[7] = tmp;

		return this;

	}

	public Matrix3 getNormalMatrix(Matrix4 matrix4) {

		return this.setFromMatrix4(matrix4).copy(this).invert().transpose();

	}

	public Matrix3 transposeIntoArray(double[] r) {

		final double[] m = this.elements;

		r[0] = m[0];
		r[1] = m[3];
		r[2] = m[6];
		r[3] = m[1];
		r[4] = m[4];
		r[5] = m[7];
		r[6] = m[2];
		r[7] = m[5];
		r[8] = m[8];

		return this;

	}

	public Matrix3 setUvTransform(double tx, double ty, double sx, double sy, double rotation, double cx, double cy) {

		final double c = Math.cos(rotation);
		final double s = Math.sin(rotation);

		this.set(sx * c, sx * s, -sx * (c * cx + s * cy) + cx + tx, -sy * s, sy * c, -sy * (-s * cx + c * cy) + cy + ty,
				0, 0, 1);

		return this;

	}

	public Matrix3 scale(double sx, double sy) {

		final double[] te = this.elements;

		te[0] *= sx;
		te[3] *= sx;
		te[6] *= sx;
		te[1] *= sy;
		te[4] *= sy;
		te[7] *= sy;

		return this;

	}

	public Matrix3 rotate(double theta) {

		final double c = Math.cos(theta);
		final double s = Math.sin(theta);

		final double[] te = this.elements;

		final double a11 = te[0], a12 = te[3], a13 = te[6];
		final double a21 = te[1], a22 = te[4], a23 = te[7];

		te[0] = c * a11 + s * a21;
		te[3] = c * a12 + s * a22;
		te[6] = c * a13 + s * a23;

		te[1] = -s * a11 + c * a21;
		te[4] = -s * a12 + c * a22;
		te[7] = -s * a13 + c * a23;

		return this;

	}

	public Matrix3 translate(double tx, double ty) {

		final double[] te = this.elements;

		te[0] += tx * te[2];
		te[3] += tx * te[5];
		te[6] += tx * te[8];
		te[1] += ty * te[2];
		te[4] += ty * te[5];
		te[7] += ty * te[8];

		return this;

	}

	public boolean equals(Matrix3 matrix) {

		final double[] te = this.elements;
		final double[] me = matrix.elements;

		for (int i = 0; i < 9; i++) {

			if (te[i] != me[i])
				return false;

		}

		return true;

	}

	public Matrix3 fromArray(double[] array) {
		return fromArray(array, 0);
	}

	public Matrix3 fromArray(double[] array, int offset) {

		for (int i = 0; i < 9; i++) {

			this.elements[i] = array[i + offset];

		}

		return this;

	}

	public double[] toArray() {
		return toArray(new double[9]);
	}

	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray(double[] array, int offset) {

		final double[] te = this.elements;

		try {
			array[offset] = te[0];
			array[offset + 1] = te[1];
			array[offset + 2] = te[2];

			array[offset + 3] = te[3];
			array[offset + 4] = te[4];
			array[offset + 5] = te[5];

			array[offset + 6] = te[6];
			array[offset + 7] = te[7];
			array[offset + 8] = te[8];
		} catch (ArrayIndexOutOfBoundsException aioob) {

		}

		return array;

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("isMatrix3");
		return sortedReflectionToStringBuilder.toString();
	}


}
