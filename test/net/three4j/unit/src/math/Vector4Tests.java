package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.three4j.core.BufferAttribute;
import net.three4j.math.Matrix4;
import net.three4j.math.Vector4;
import net.three4j.unit.utils.Float32Array;

import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static net.three4j.unit.src.math.ConstantsTests.w;
import static net.three4j.unit.src.math.ConstantsTests.eps;

public class Vector4Tests {

	@Test
	public void Instancing() {

		Vector4 a = new Vector4();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
		assertTrue(a.w() == 1, "Passed!");

		a = new Vector4(x, y, z, w);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");
		assertTrue(a.w() == w, "Passed!");

	}

	// PUBLIC STUFF
	@Test
	public void isVector4() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void set() {

		Vector4 a = new Vector4();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
		assertTrue(a.w() == 1, "Passed!");

		a.set(x, y, z, w);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");
		assertTrue(a.w() == w, "Passed!");

	}

	@Test
	public void setScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setX() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setY() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setZ() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setW() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setComponent() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void getComponent() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void $clone() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void copy() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4().copy(a);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");
		assertTrue(b.z() == z, "Passed!");
		assertTrue(b.w() == w, "Passed!");

		// ensure that it is a true copy
		a.x(0);
		a.y(-1);
		a.z(-2);
		a.w(-3);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");
		assertTrue(b.z() == z, "Passed!");
		assertTrue(b.w() == w, "Passed!");

	}

	@Test
	public void add() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(-x, -y, -z, -w);

		a.add(b);
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
		assertTrue(a.w() == 0, "Passed!");

		Vector4 c = new Vector4().addVectors(b, b);
		assertTrue(c.x() == -2 * x, "Passed!");
		assertTrue(c.y() == -2 * y, "Passed!");
		assertTrue(c.z() == -2 * z, "Passed!");
		assertTrue(c.w() == -2 * w, "Passed!");

	}

	@Test
	public void addScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void addVectors() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void addScaledVector() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(6, 7, 8, 9);
		double s = 3;

		a.addScaledVector(b, s);
		assertEquals(x + b.x() * s, a.x(), "Check x");
		assertEquals(y + b.y() * s, a.y(), "Check y");
		assertEquals(z + b.z() * s, a.z(), "Check z");
		assertEquals(w + b.w() * s, a.w(), "Check w");

	}

	@Test
	public void sub() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(-x, -y, -z, -w);

		a.sub(b);
		assertTrue(a.x() == 2 * x, "Passed!");
		assertTrue(a.y() == 2 * y, "Passed!");
		assertTrue(a.z() == 2 * z, "Passed!");
		assertTrue(a.w() == 2 * w, "Passed!");

		Vector4 c = new Vector4().subVectors(a, a);
		assertTrue(c.x() == 0, "Passed!");
		assertTrue(c.y() == 0, "Passed!");
		assertTrue(c.z() == 0, "Passed!");
		assertTrue(c.w() == 0, "Passed!");

	}

	@Test
	public void subScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void subVectors() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void multiplyScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void applyMatrix4() {

		Vector4 a = new Vector4(x, y, z, w);
		Matrix4 m = new Matrix4().makeRotationX(Math.PI);
		Vector4 expected = new Vector4(2, -3, -4, 5);

		a.applyMatrix4(m);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Rotation matrix: check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Rotation matrix: check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Rotation matrix: check z");
		assertTrue(Math.abs(a.w() - expected.w()) <= eps, "Rotation matrix: check w");

		a.set(x, y, z, w);
		m.makeTranslation(5, 7, 11);
		expected.set(27, 38, 59, 5);

		a.applyMatrix4(m);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Translation matrix: check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Translation matrix: check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Translation matrix: check z");
		assertTrue(Math.abs(a.w() - expected.w()) <= eps, "Translation matrix: check w");

		a.set(x, y, z, w);
		m.set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0);
		expected.set(2, 3, 4, 4);

		a.applyMatrix4(m);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Custom matrix: check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Custom matrix: check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Custom matrix: check z");
		assertTrue(Math.abs(a.w() - expected.w()) <= eps, "Custom matrix: check w");

		a.set(x, y, z, w);
		m.set(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53);
		expected.set(68, 224, 442, 664);

		a.applyMatrix4(m);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Bogus matrix: check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Bogus matrix: check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Bogus matrix: check z");
		assertTrue(Math.abs(a.w() - expected.w()) <= eps, "Bogus matrix: check w");

	}

	@Test
	public void divideScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setAxisAngleFromQuaternion() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void setAxisAngleFromRotationMatrix() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void min() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void max() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void clamp() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void clampScalar() {

		Vector4 a = new Vector4(-0.1, 0.01, 0.5, 1.5);
		Vector4 clamped = new Vector4(0.1, 0.1, 0.5, 1.0);

		a.clampScalar(0.1, 1.0);
		assertTrue(Math.abs(a.x() - clamped.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - clamped.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - clamped.z()) <= eps, "Check z");
		assertTrue(Math.abs(a.w() - clamped.w()) <= eps, "Check w");

	}

	@Test
	public void clampLength() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void floor() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void ceil() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void round() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void roundToZero() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void negate() {

		Vector4 a = new Vector4(x, y, z, w);

		a.negate();
		assertTrue(a.x() == -x, "Passed!");
		assertTrue(a.y() == -y, "Passed!");
		assertTrue(a.z() == -z, "Passed!");
		assertTrue(a.w() == -w, "Passed!");

	}

	@Test
	public void dot() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(-x, -y, -z, -w);
		Vector4 c = new Vector4(0, 0, 0, 0);

		double result = a.dot(b);
		assertTrue(result == (-x * x - y * y - z * z - w * w), "Passed!");

		result = a.dot(c);
		assertTrue(result == 0, "Passed!");

	}

	@Test
	public void lengthSq() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void length() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void manhattanLength() {

		Vector4 a = new Vector4(x, 0, 0, 0);
		Vector4 b = new Vector4(0, -y, 0, 0);
		Vector4 c = new Vector4(0, 0, z, 0);
		Vector4 d = new Vector4(0, 0, 0, w);
		Vector4 e = new Vector4(0, 0, 0, 0);

		assertTrue(a.manhattanLength() == x, "Positive x");
		assertTrue(b.manhattanLength() == y, "Negative y");
		assertTrue(c.manhattanLength() == z, "Positive z");
		assertTrue(d.manhattanLength() == w, "Positive w");
		assertTrue(e.manhattanLength() == 0, "Empty initialization");

		a.set(x, y, z, w);
		assertTrue(a.manhattanLength() == Math.abs(x) + Math.abs(y) + Math.abs(z) + Math.abs(w), "All components");

	}

	@Test
	public void normalize() {

		Vector4 a = new Vector4(x, 0, 0, 0);
		Vector4 b = new Vector4(0, -y, 0, 0);
		Vector4 c = new Vector4(0, 0, z, 0);
		Vector4 d = new Vector4(0, 0, 0, -w);

		a.normalize();
		assertTrue(a.length() == 1, "Passed!");
		assertTrue(a.x() == 1, "Passed!");

		b.normalize();
		assertTrue(b.length() == 1, "Passed!");
		assertTrue(b.y() == -1, "Passed!");

		c.normalize();
		assertTrue(c.length() == 1, "Passed!");
		assertTrue(c.z() == 1, "Passed!");

		d.normalize();
		assertTrue(d.length() == 1, "Passed!");
		assertTrue(d.w() == -1, "Passed!");

	}

	@Test
	public void setLength() {

		Vector4 a = new Vector4(x, 0, 0, 0);

		assertTrue(a.length() == x, "Passed!");
		a.setLength(y);
		assertTrue(a.length() == y, "Passed!");

		a = new Vector4(0, 0, 0, 0);
		assertTrue(a.length() == 0, "Passed!");
		a.setLength(y);
		assertTrue(a.length() == 0, "Passed!");
//		a.setLength();
//		assertTrue(isNaN(a.length()), "Passed!");

	}

	@Test
	public void lerp() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void lerpVectors() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void equals() {

		Vector4 a = new Vector4(x, 0, z, 0);
		Vector4 b = new Vector4(0, -y, 0, -w);

		assertTrue(a.x() != b.x(), "Passed!");
		assertTrue(a.y() != b.y(), "Passed!");
		assertTrue(a.z() != b.z(), "Passed!");
		assertTrue(a.w() != b.w(), "Passed!");

		assertTrue(!a.equals(b), "Passed!");
		assertTrue(!b.equals(a), "Passed!");

		a.copy(b);
		assertTrue(a.x() == b.x(), "Passed!");
		assertTrue(a.y() == b.y(), "Passed!");
		assertTrue(a.z() == b.z(), "Passed!");
		assertTrue(a.w() == b.w(), "Passed!");

		assertTrue(a.equals(b), "Passed!");
		assertTrue(b.equals(a), "Passed!");

	}

	@Test
	public void fromArray() {

		Vector4 a = new Vector4();
		final double[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };

		a.fromArray(array);
		assertEquals(1, a.x(), "No offset: check x");
		assertEquals(2, a.y(), "No offset: check y");
		assertEquals(3, a.z(), "No offset: check z");
		assertEquals(4, a.w(), "No offset: check w");

		a.fromArray(array, 4);
		assertEquals(5, a.x(), "With offset: check x");
		assertEquals(6, a.y(), "With offset: check y");
		assertEquals(7, a.z(), "With offset: check z");
		assertEquals(8, a.w(), "With offset: check w");

	}

	@Test
	public void toArray() {

		Vector4 a = new Vector4(x, y, z, w);

		double[] array = a.toArray();
		assertEquals(x, array[0], "No array, no offset: check x");
		assertEquals(y, array[1], "No array, no offset: check y");
		assertEquals(z, array[2], "No array, no offset: check z");
		assertEquals(w, array[3], "No array, no offset: check w");

		array = new double[4];
		a.toArray(array);
		assertEquals(x, array[0], "With array, no offset: check x");
		assertEquals(y, array[1], "With array, no offset: check y");
		assertEquals(z, array[2], "With array, no offset: check z");
		assertEquals(w, array[3], "With array, no offset: check w");

		array = new double[5];
		a.toArray(array, 1);
		assertEquals(0, array[0], "With array and offset: check [0]");
		assertEquals(x, array[1], "With array and offset: check x");
		assertEquals(y, array[2], "With array and offset: check y");
		assertEquals(z, array[3], "With array and offset: check z");
		assertEquals(w, array[4], "With array and offset: check w");

	}

	@Test
	public void fromBufferAttribute() {

			Vector4 a = new Vector4();
			BufferAttribute attr = new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6, 7, 8 } ), 4 );

			a.fromBufferAttribute( attr, 0 );
			assertEquals( 1, a.x(), "Offset 0: check x" );
			assertEquals( 2, a.y(), "Offset 0: check y" );
			assertEquals( 3, a.z(), "Offset 0: check z" );
			assertEquals( 4, a.w(), "Offset 0: check w" );

			a.fromBufferAttribute( attr, 1 );
			assertEquals( 5, a.x(), "Offset 1: check x" );
			assertEquals( 6, a.y(), "Offset 1: check y" );
			assertEquals( 7, a.z(), "Offset 1: check z" );
			assertEquals( 8, a.w(), "Offset 1: check w" );

		}

	@Test
	public void setX_setY_setZ_setW() {

		Vector4 a = new Vector4();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
		assertTrue(a.w() == 1, "Passed!");

		a.setX(x);
		a.setY(y);
		a.setZ(z);
		a.setW(w);

		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");
		assertTrue(a.w() == w, "Passed!");

	}

	@Test
	public void setComponent_getComponent() {

		Vector4 a = new Vector4();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
		assertTrue(a.w() == 1, "Passed!");

		a.setComponent(0, 1);
		a.setComponent(1, 2);
		a.setComponent(2, 3);
		a.setComponent(3, 4);
		assertTrue(a.getComponent(0) == 1, "Passed!");
		assertTrue(a.getComponent(1) == 2, "Passed!");
		assertTrue(a.getComponent(2) == 3, "Passed!");
		assertTrue(a.getComponent(3) == 4, "Passed!");

	}

	@Test
	public void setComponent_getComponent_exceptions() {

		Vector4 a = new Vector4();
//
//			assert.throws(
//
//	function () {
//
//					a.setComponent( 4, 0 );
//
//				},/
//
//	index is
//	out of range/,"setComponent with an out of range index throws Error");assert.throws(
//
//	function () {
//
//					a.getComponent( 4 );
//
//				},/
//
//	index is
//	out of range/,"getComponent with an out of range index throws Error");
//
	}

	@Test
	public void setScalar_addScalar_subScalar() {

		Vector4 a = new Vector4();
		double s = 3;

		a.setScalar(s);
		assertEquals(s, a.x(), "setScalar: check x");
		assertEquals(s, a.y(), "setScalar: check y");
		assertEquals(s, a.z(), "setScalar: check z");
		assertEquals(s, a.w(), "setScalar: check w");

		a.addScalar(s);
		assertEquals(2 * s, a.x(), "addScalar: check x");
		assertEquals(2 * s, a.y(), "addScalar: check y");
		assertEquals(2 * s, a.z(), "addScalar: check z");
		assertEquals(2 * s, a.w(), "addScalar: check w");

		a.subScalar(2 * s);
		assertEquals(0, a.x(), "subScalar: check x");
		assertEquals(0, a.y(), "subScalar: check y");
		assertEquals(0, a.z(), "subScalar: check z");
		assertEquals(0, a.w(), "subScalar: check w");

	}

	@Test
	public void multiply_divide() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(-x, -y, -z, -w);

		a.multiplyScalar(-2);
		assertTrue(a.x() == x * -2, "Passed!");
		assertTrue(a.y() == y * -2, "Passed!");
		assertTrue(a.z() == z * -2, "Passed!");
		assertTrue(a.w() == w * -2, "Passed!");

		b.multiplyScalar(-2);
		assertTrue(b.x() == 2 * x, "Passed!");
		assertTrue(b.y() == 2 * y, "Passed!");
		assertTrue(b.z() == 2 * z, "Passed!");
		assertTrue(b.w() == 2 * w, "Passed!");

		a.divideScalar(-2);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");
		assertTrue(a.w() == w, "Passed!");

		b.divideScalar(-2);
		assertTrue(b.x() == -x, "Passed!");
		assertTrue(b.y() == -y, "Passed!");
		assertTrue(b.z() == -z, "Passed!");
		assertTrue(b.w() == -w, "Passed!");

	}

	@Test
	public void min_max_clamp() {

		Vector4 a = new Vector4(x, y, z, w);
		Vector4 b = new Vector4(-x, -y, -z, -w);
		Vector4 c = new Vector4();

		c.copy(a).min(b);
		assertTrue(c.x() == -x, "Passed!");
		assertTrue(c.y() == -y, "Passed!");
		assertTrue(c.z() == -z, "Passed!");
		assertTrue(c.w() == -w, "Passed!");

		c.copy(a).max(b);
		assertTrue(c.x() == x, "Passed!");
		assertTrue(c.y() == y, "Passed!");
		assertTrue(c.z() == z, "Passed!");
		assertTrue(c.w() == w, "Passed!");

		c.set(-2 * x, 2 * y, -2 * z, 2 * w);
		c.clamp(b, a);
		assertTrue(c.x() == -x, "Passed!");
		assertTrue(c.y() == y, "Passed!");
		assertTrue(c.z() == -z, "Passed!");
		assertTrue(c.w() == w, "Passed!");

	}

	@Test
	public void length_lengthSq() {

		Vector4 a = new Vector4(x, 0, 0, 0);
		Vector4 b = new Vector4(0, -y, 0, 0);
		Vector4 c = new Vector4(0, 0, z, 0);
		Vector4 d = new Vector4(0, 0, 0, w);
		Vector4 e = new Vector4(0, 0, 0, 0);

		assertTrue(a.length() == x, "Passed!");
		assertTrue(a.lengthSq() == x * x, "Passed!");
		assertTrue(b.length() == y, "Passed!");
		assertTrue(b.lengthSq() == y * y, "Passed!");
		assertTrue(c.length() == z, "Passed!");
		assertTrue(c.lengthSq() == z * z, "Passed!");
		assertTrue(d.length() == w, "Passed!");
		assertTrue(d.lengthSq() == w * w, "Passed!");
		assertTrue(e.length() == 0, "Passed!");
		assertTrue(e.lengthSq() == 0, "Passed!");

		a.set(x, y, z, w);
		assertTrue(a.length() == Math.sqrt(x * x + y * y + z * z + w * w), "Passed!");
		assertTrue(a.lengthSq() == (x * x + y * y + z * z + w * w), "Passed!");

	}

	@Test
	public void lerp_clone() {

		Vector4 a = new Vector4(x, 0, z, 0);
		Vector4 b = new Vector4(0, -y, 0, -w);

		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 0.5)), "Passed!");
		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 1)), "Passed!");

		assertTrue(a.clone().lerp(b, 0).equals(a), "Passed!");

		assertTrue(a.clone().lerp(b, 0.5).x() == x * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).y() == -y * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).z() == z * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).w() == -w * 0.5, "Passed!");

		assertTrue(a.clone().lerp(b, 1).equals(b), "Passed!");

	}

}
