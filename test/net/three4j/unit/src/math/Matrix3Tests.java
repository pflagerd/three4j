package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.three4j.math.Matrix3;
import net.three4j.math.Matrix4;

public class Matrix3Tests {

	public static boolean matrixEquals3(Matrix3 a, Matrix3 b) {
		return matrixEquals3(a, b, 0.0001);
	}

	public static boolean matrixEquals3(Matrix3 a, Matrix3 b, double tolerance) {

		if (a.elements.length != b.elements.length) {

			return false;

		}

		for (int i = 0, il = a.elements.length; i < il; i++) {

			double delta = a.elements[i] - b.elements[i];
			if (delta > tolerance) {

				return false;

			}

		}

		return true;

	}

	public static boolean matrixEquals4(Matrix4 a, Matrix4 b) {
		return matrixEquals4(a, b, 0.0001);
	}

	public static boolean matrixEquals4(Matrix4 a, Matrix4 b, double tolerance) {

		if (a.elements.length != b.elements.length) {

			return false;

		}

		for (int i = 0, il = a.elements.length; i < il; i++) {

			double delta = a.elements[i] - b.elements[i];
			if (delta > tolerance) {

				return false;

			}

		}

		return true;

	}

	public static Matrix4 toMatrix4(Matrix3 m3) {

		Matrix4 result = new Matrix4();
		final double[] re = result.elements;
		final double[] me = m3.elements;
		re[0] = me[0];
		re[1] = me[1];
		re[2] = me[2];
		re[4] = me[3];
		re[5] = me[4];
		re[6] = me[5];
		re[8] = me[6];
		re[9] = me[7];
		re[10] = me[8];

		return result;

	}

	@Test
	public void instancing() {

		Matrix3 a = new Matrix3();
		assertTrue(a.determinant() == 1, "Passed!");

		Matrix3 b = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		assertTrue(b.elements[0] == 0);
		assertTrue(b.elements[1] == 3);
		assertTrue(b.elements[2] == 6);
		assertTrue(b.elements[3] == 1);
		assertTrue(b.elements[4] == 4);
		assertTrue(b.elements[5] == 7);
		assertTrue(b.elements[6] == 2);
		assertTrue(b.elements[7] == 5);
		assertTrue(b.elements[8] == 8);

		assertTrue(!matrixEquals3(a, b), "Passed!");

	}

	@Test
	public void isMatrix3() {

		Matrix3 a = new Matrix3();
		assertTrue(a.isMatrix3 == true, "Passed!");

		Matrix4 b = new Matrix4();
		assertTrue(b instanceof Matrix4, "Passed!");

	}

	@Test
	public void set() {

		Matrix3 b = new Matrix3();
		assertTrue(b.determinant() == 1, "Passed!");

		b.set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		assertTrue(b.elements[0] == 0);
		assertTrue(b.elements[1] == 3);
		assertTrue(b.elements[2] == 6);
		assertTrue(b.elements[3] == 1);
		assertTrue(b.elements[4] == 4);
		assertTrue(b.elements[5] == 7);
		assertTrue(b.elements[6] == 2);
		assertTrue(b.elements[7] == 5);
		assertTrue(b.elements[8] == 8);

	}

	@Test
	public void identity() {

		Matrix3 b = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		assertTrue(b.elements[0] == 0);
		assertTrue(b.elements[1] == 3);
		assertTrue(b.elements[2] == 6);
		assertTrue(b.elements[3] == 1);
		assertTrue(b.elements[4] == 4);
		assertTrue(b.elements[5] == 7);
		assertTrue(b.elements[6] == 2);
		assertTrue(b.elements[7] == 5);
		assertTrue(b.elements[8] == 8);

		Matrix3 a = new Matrix3();
		assertTrue(!matrixEquals3(a, b), "Passed!");

		b.identity();
		assertTrue(matrixEquals3(a, b), "Passed!");

	}

	@Test
	public void $clone() {

		Matrix3 a = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix3 b = a.clone();

		assertTrue(matrixEquals3(a, b), "Passed!");

		// ensure that it is a true copy
		a.elements[0] = 2;
		assertTrue(!matrixEquals3(a, b), "Passed!");

	}

	@Test
	public void copy() {

		Matrix3 a = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix3 b = new Matrix3().copy(a);

		assertTrue(matrixEquals3(a, b), "Passed!");

		// ensure that it is a true copy
		a.elements[0] = 2;
		assertTrue(!matrixEquals3(a, b), "Passed!");

	}

	@Test
	public void setFromMatrix4() {

		Matrix4 a = new Matrix4().set(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
		Matrix3 b = new Matrix3();
		Matrix3 c = new Matrix3().set(0, 1, 2, 4, 5, 6, 8, 9, 10);
		b.setFromMatrix4(a);
		assertTrue(b.equals(c));

	}

	@Test
	public void multiply_premultiply() {

		// both simply just wrap multiplyMatrices
		Matrix3 a = new Matrix3().set(2, 3, 5, 7, 11, 13, 17, 19, 23);
		Matrix3 b = new Matrix3().set(29, 31, 37, 41, 43, 47, 53, 59, 61);
		final double[] expectedMultiply = { 446, 1343, 2491, 486, 1457, 2701, 520, 1569, 2925 };
		final double[] expectedPremultiply = { 904, 1182, 1556, 1131, 1489, 1967, 1399, 1845, 2435 };

		a.multiply(b);
		assertArrayEquals(a.elements, expectedMultiply, "multiply: check result");

		a.set(2, 3, 5, 7, 11, 13, 17, 19, 23);
		a.premultiply(b);
		assertArrayEquals(a.elements, expectedPremultiply, "premultiply: check result");

	}

	@Test
	public void multiplyMatrices() {

		// Reference:
		//
		// #!/usr/bin/env python
		// from __future__ import print_function
		// import numpy as np
		// print(
		// np.dot(
		// np.reshape([2, 3, 5, 7, 11, 13, 17, 19, 23], (3, 3)),
		// np.reshape([29, 31, 37, 41, 43, 47, 53, 59, 61], (3, 3))
		// )
		// )
		//
		// [[ 446 486 520]
		// [1343 1457 1569]
		// [2491 2701 2925]]
		Matrix3 lhs = new Matrix3().set(2, 3, 5, 7, 11, 13, 17, 19, 23);
		Matrix3 rhs = new Matrix3().set(29, 31, 37, 41, 43, 47, 53, 59, 61);
		Matrix3 ans = new Matrix3();

		ans.multiplyMatrices(lhs, rhs);

		assertTrue(ans.elements[0] == 446);
		assertTrue(ans.elements[1] == 1343);
		assertTrue(ans.elements[2] == 2491);
		assertTrue(ans.elements[3] == 486);
		assertTrue(ans.elements[4] == 1457);
		assertTrue(ans.elements[5] == 2701);
		assertTrue(ans.elements[6] == 520);
		assertTrue(ans.elements[7] == 1569);
		assertTrue(ans.elements[8] == 2925);

	}

	@Test
	public void multiplyScalar() {

		Matrix3 b = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		assertTrue(b.elements[0] == 0);
		assertTrue(b.elements[1] == 3);
		assertTrue(b.elements[2] == 6);
		assertTrue(b.elements[3] == 1);
		assertTrue(b.elements[4] == 4);
		assertTrue(b.elements[5] == 7);
		assertTrue(b.elements[6] == 2);
		assertTrue(b.elements[7] == 5);
		assertTrue(b.elements[8] == 8);

		b.multiplyScalar(2);
		assertTrue(b.elements[0] == 0 * 2);
		assertTrue(b.elements[1] == 3 * 2);
		assertTrue(b.elements[2] == 6 * 2);
		assertTrue(b.elements[3] == 1 * 2);
		assertTrue(b.elements[4] == 4 * 2);
		assertTrue(b.elements[5] == 7 * 2);
		assertTrue(b.elements[6] == 2 * 2);
		assertTrue(b.elements[7] == 5 * 2);
		assertTrue(b.elements[8] == 8 * 2);

	}

	@Test
	public void determinant() {

		Matrix3 a = new Matrix3();
		assertTrue(a.determinant() == 1, "Passed!");

		a.elements[0] = 2;
		assertTrue(a.determinant() == 2, "Passed!");

		a.elements[0] = 0;
		assertTrue(a.determinant() == 0, "Passed!");

		// calculated via
		// http://www.euclideanspace.com/maths/algebra/matrix/functions/determinant/threeD/index.htm
		a.set(2, 3, 4, 5, 13, 7, 8, 9, 11);
		assertTrue(a.determinant() == -73, "Passed!");

	}

	@Test
	public void invert() {

		Matrix3 zero = new Matrix3().set(0, 0, 0, 0, 0, 0, 0, 0, 0);
		Matrix4 identity4 = new Matrix4();
		Matrix3 a = new Matrix3().set(0, 0, 0, 0, 0, 0, 0, 0, 0);
		Matrix3 b = new Matrix3();

		b.copy(a).invert();
		assertTrue(matrixEquals3(b, zero), "Matrix a is zero matrix");

		Matrix4[] testMatrices = { new Matrix4().makeRotationX(0.3), new Matrix4().makeRotationX(-0.3),
				new Matrix4().makeRotationY(0.3), new Matrix4().makeRotationY(-0.3), new Matrix4().makeRotationZ(0.3),
				new Matrix4().makeRotationZ(-0.3), new Matrix4().makeScale(1, 2, 3),
				new Matrix4().makeScale(1. / 8, 1. / 2, 1. / 3) };

		for (int i = 0, il = testMatrices.length; i < il; i++) {

			Matrix4 m = testMatrices[i];

			a.setFromMatrix4(m);
			Matrix3 mInverse3 = b.copy(a).invert();

			Matrix4 mInverse = toMatrix4(mInverse3);

			// the determinant of the inverse should be the reciprocal
			assertTrue(Math.abs(a.determinant() * mInverse3.determinant() - 1) < 0.0001, "Passed!");
			assertTrue(Math.abs(m.determinant() * mInverse.determinant() - 1) < 0.0001, "Passed!");

			Matrix4 mProduct = new Matrix4().multiplyMatrices(m, mInverse);
			assertTrue(Math.abs(mProduct.determinant() - 1) < 0.0001, "Passed!");
			assertTrue(matrixEquals4(mProduct, identity4), "Passed!");

		}

	}

	@Test
	public void transpose() {

		Matrix3 a = new Matrix3();
		Matrix3 b = a.clone().transpose();
		assertTrue(matrixEquals3(a, b), "Passed!");

		b = new Matrix3().set(0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix3 c = b.clone().transpose();
		assertTrue(!matrixEquals3(b, c), "Passed!");
		c.transpose();
		assertTrue(matrixEquals3(b, c), "Passed!");

	}

	@Test
	public void getNormalMatrix() {

		Matrix3 a = new Matrix3();
		Matrix4 b = new Matrix4().set(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 57);
		Matrix3 expected = new Matrix3().set(-1.2857142857142856, 0.7142857142857143, 0.2857142857142857,
				0.7428571428571429, -0.7571428571428571, 0.15714285714285714, -0.19999999999999998, 0.3,
				-0.09999999999999999);

		a.getNormalMatrix(b);
		assertTrue(matrixEquals3(a, expected), "Check resulting Matrix3");

	}

	@Test
	public void transposeIntoArray() {

			Matrix3 a = new Matrix3().set( 0, 1, 2, 3, 4, 5, 6, 7, 8 );
			double[] b = new double[9];
			a.transposeIntoArray( b );

			assertTrue( b[ 0 ] == 0 );
			assertTrue( b[ 1 ] == 1 );
			assertTrue( b[ 2 ] == 2 );
			assertTrue( b[ 3 ] == 3 );
			assertTrue( b[ 4 ] == 4 );
			assertTrue( b[ 5 ] == 5 );
			assertTrue( b[ 5 ] == 5 );
			assertTrue( b[ 6 ] == 6 );
			assertTrue( b[ 7 ] == 7 );
			assertTrue( b[ 8 ] == 8 );

		}

	@Test
	public void setUvTransform() {

			Matrix3 a = new Matrix3().set(
				0.1767766952966369, 0.17677669529663687, 0.32322330470336313,
				- 0.17677669529663687, 0.1767766952966369, 0.5,
				0, 0, 1
			);
			Matrix3 b = new Matrix3();
			class Params {
				double centerX = 0.5;
				double centerY = 0.5;
				double offsetX = 0;
				double offsetY = 0;
				double repeatX = 0.25;
				double repeatY = 0.25;
				double rotation = 0.7753981633974483;
			};
			Params params = new Params();
			
			Matrix3 expected = new Matrix3().set(
				0.1785355940258599, 0.17500011904519763, 0.32323214346447127,
				- 0.17500011904519763, 0.1785355940258599, 0.4982322625096689,
				0, 0, 1
			);

			a.setUvTransform(
				params.offsetX, params.offsetY,
				params.repeatX, params.repeatY,
				params.rotation,
				params.centerX, params.centerY
			);

			b.identity()
			 .translate( - params.centerX, - params.centerY )
			 .rotate( params.rotation )
			 .scale( params.repeatX, params.repeatY )
			 .translate( params.centerX, params.centerY )
			 .translate( params.offsetX, params.offsetY );

			assertTrue( matrixEquals3( a, expected ), "Check direct method" );
			assertTrue( matrixEquals3( b, expected ), "Check indirect method" );

		}

	@Test
	public void scale() {

		Matrix3 a = new Matrix3().set(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix3 expected = new Matrix3().set(0.25, 0.5, 0.75, 1, 1.25, 1.5, 7, 8, 9);

		a.scale(0.25, 0.25);
		assertTrue(matrixEquals3(a, expected), "Check scaling result");

	}

	@Test
	public void rotate() {

		Matrix3 a = new Matrix3().set(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix3 expected = new Matrix3().set(3.5355339059327373, 4.949747468305833, 6.363961030678928, 2.121320343559643,
				2.121320343559643, 2.1213203435596433, 7, 8, 9);

		a.rotate(Math.PI / 4);
		assertTrue(matrixEquals3(a, expected), "Check rotated result");

	}

	@Test
	public void translate() {

		Matrix3 a = new Matrix3().set(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix3 expected = new Matrix3().set(22, 26, 30, 53, 61, 69, 7, 8, 9);

		a.translate(3, 7);
		assertTrue(matrixEquals3(a, expected), "Check translation result");

	}

	@Test
	public void equals() {

			Matrix3 a = new Matrix3().set( 0, 1, 2, 3, 4, 5, 6, 7, 8 );
			Matrix3 b = new Matrix3().set( 0, - 1, 2, 3, 4, 5, 6, 7, 8 );

			assertFalse( a.equals( b ), "Check that a does not equal b" );
			assertFalse( b.equals( a ), "Check that b does not equal a" );

			a.copy( b );
			assertTrue( a.equals( b ), "Check that a equals b after copy()" );
			assertTrue( b.equals( a ), "Check that b equals a after copy()" );

		}

	@Test
	public void fromArray() {

			Matrix3 b = new Matrix3();
			b.fromArray( new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 } );

			assertTrue( b.elements[ 0 ] == 0 );
			assertTrue( b.elements[ 1 ] == 1 );
			assertTrue( b.elements[ 2 ] == 2 );
			assertTrue( b.elements[ 3 ] == 3 );
			assertTrue( b.elements[ 4 ] == 4 );
			assertTrue( b.elements[ 5 ] == 5 );
			assertTrue( b.elements[ 6 ] == 6 );
			assertTrue( b.elements[ 7 ] == 7 );
			assertTrue( b.elements[ 8 ] == 8 );

			b = new Matrix3();
			b.fromArray( new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 }, 10 );

			assertTrue( b.elements[ 0 ] == 10 );
			assertTrue( b.elements[ 1 ] == 11 );
			assertTrue( b.elements[ 2 ] == 12 );
			assertTrue( b.elements[ 3 ] == 13 );
			assertTrue( b.elements[ 4 ] == 14 );
			assertTrue( b.elements[ 5 ] == 15 );
			assertTrue( b.elements[ 6 ] == 16 );
			assertTrue( b.elements[ 7 ] == 17 );
			assertTrue( b.elements[ 8 ] == 18 );

		}

	@Test
	public void toArray() {

			Matrix3 a = new Matrix3().set( 1, 2, 3, 4, 5, 6, 7, 8, 9 );
			double[] noOffset = { 1, 4, 7, 2, 5, 8, 3, 6, 9 };
			double[] withOffset = { 0, 1, 4, 7, 2, 5, 8, 3, 6, 9 };

			double[] array = a.toArray();
			assertArrayEquals( array, noOffset, "No array, no offset" );

			array = new double[9];
			a.toArray( array );
			assertArrayEquals( array, noOffset, "With array, no offset" );

			array = new double[10];
			a.toArray( array, 1 );
			assertArrayEquals( array, withOffset, "With array, with offset" );

		}

}
