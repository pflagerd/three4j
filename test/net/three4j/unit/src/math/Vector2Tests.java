package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.three4j.math.Vector2;
import net.three4j.unit.utils.Float32Array;
import net.three4j.math.Matrix3;
import net.three4j.core.BufferAttribute;

import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.eps;

public class Vector2Tests {

	@Test
	public void instancing() {

		Vector2 a = new Vector2();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");

		a = new Vector2(x, y);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");

	}

	@Test
	public void properties() {

		Vector2 a = new Vector2(0, 0);
		double width = 100;
		double height = 200;

		// DPP: 210112065455Z: What's this syntax mean?
//		assertTrue( a.width = width, "Set width" );
//		assertTrue( a.height = height, "Set height" );
		a.width(width);
		a.height(height);

		a.set(width, height);
		assertEquals(a.width(), width, "Get width");
		assertEquals(a.height(), height, "Get height");

	}

//	@Test
//	public void width() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void height() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void isVector2() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void set() {

		Vector2 a = new Vector2();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");

		a.set(x, y);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");

	}

//	@Test
//	public void setScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void setX() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void setY() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void setComponent() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void getComponent() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void $clone() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void copy() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2().copy(a);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");

		// ensure that it is a true copy
		a.x(0);
		a.y(-1);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");

	}

	@Test
	public void add() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(-x, -y);

		a.add(b);
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");

		Vector2 c = new Vector2().addVectors(b, b);
		assertTrue(c.x() == -2 * x, "Passed!");
		assertTrue(c.y() == -2 * y, "Passed!");

	}

//	@Test
//	public void addScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void addVectors() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void addScaledVector() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(2, 3);
		double s = 3;

		a.addScaledVector(b, s);
		assertEquals(a.x(), x + b.x() * s, "Check x");
		assertEquals(a.y(), y + b.y() * s, "Check y");

	}

	@Test
	public void sub() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(-x, -y);

		a.sub(b);
		assertTrue(a.x() == 2 * x, "Passed!");
		assertTrue(a.y() == 2 * y, "Passed!");

		Vector2 c = new Vector2().subVectors(a, a);
		assertTrue(c.x() == 0, "Passed!");
		assertTrue(c.y() == 0, "Passed!");

	}

//	@Test
//	public void subScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void subVectors() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void multiply() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void multiplyScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void divide() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void divideScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void applyMatrix3() {

			Vector2 a = new Vector2( x, y );
			Matrix3 m = new Matrix3().set( 2, 3, 5, 7, 11, 13, 17, 19, 23 );

			a.applyMatrix3( m );
			assertEquals( a.x(), 18, "Check x" );
			assertEquals( a.y(), 60, "Check y" );

	}

//	@Test
//	public void min() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void max() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void clamp() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void clampScalar() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void clampLength() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void floor() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void ceil() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void round() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void roundToZero() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void negate() {

		Vector2 a = new Vector2(x, y);

		a.negate();
		assertTrue(a.x() == -x, "Passed!");
		assertTrue(a.y() == -y, "Passed!");

	}

	@Test
	public void dot() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(-x, -y);
		Vector2 c = new Vector2();

		double result = a.dot(b);
		assertTrue(result == (-x * x - y * y), "Passed!");

		result = a.dot(c);
		assertTrue(result == 0, "Passed!");

	}

	@Test
	public void cross() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(2 * x, -y);
		double answer = -18;
		double crossed = a.cross(b);

		assertTrue(Math.abs(answer - crossed) <= eps, "Check cross");

	}

//	@Test
//	public void lengthSq() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void length() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void manhattanLength() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);
		Vector2 c = new Vector2();

		assertEquals(a.manhattanLength(), x, "Positive component");
		assertEquals(b.manhattanLength(), y, "Negative component");
		assertEquals(c.manhattanLength(), 0, "Empty component");

		a.set(x, y);
		assertEquals(a.manhattanLength(), Math.abs(x) + Math.abs(y), "Two components");

	}

	@Test
	public void normalize() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);

		a.normalize();
		assertTrue(a.length() == 1, "Passed!");
		assertTrue(a.x() == 1, "Passed!");

		b.normalize();
		assertTrue(b.length() == 1, "Passed!");
		assertTrue(b.y() == -1, "Passed!");

	}

//	@Test
//	public void angle() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void distanceTo() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void distanceToSquared() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void manhattanDistanceTo() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void setLength() {

		Vector2 a = new Vector2(x, 0);

		assertTrue(a.length() == x, "Passed!");
		a.setLength(y);
		assertTrue(a.length() == y, "Passed!");

		a = new Vector2(0, 0);
		assertTrue(a.length() == 0, "Passed!");
		
		a.setLength(y);
		assertTrue(a.length() == 0, "Passed!");
		
//			a.setLength();
//			assertTrue( isNaN( a.length() ), "Passed!" );

	}

//	@Test
//	public void lerp() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void lerpVectors() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void equals() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);

		assertTrue(a.x() != b.x(), "Passed!");
		assertTrue(a.y() != b.y(), "Passed!");

		assertTrue(!a.equals(b), "Passed!");
		assertTrue(!b.equals(a), "Passed!");

		a.copy(b);
		assertTrue(a.x() == b.x(), "Passed!");
		assertTrue(a.y() == b.y(), "Passed!");

		assertTrue(a.equals(b), "Passed!");
		assertTrue(b.equals(a), "Passed!");

	}

	@Test
	public void fromArray() {

		Vector2 a = new Vector2();
		double[] array = { 1, 2, 3, 4 };

		a.fromArray(array);
		assertEquals(a.x(), 1, "No offset: check x");
		assertEquals(a.y(), 2, "No offset: check y");

		a.fromArray(array, 2);
		assertEquals(a.x(), 3, "With offset: check x");
		assertEquals(a.y(), 4, "With offset: check y");

	}

	@Test
	public void toArray() {

		Vector2 a = new Vector2(x, y);

		double[] array = a.toArray();
		assertEquals(array[0], x, "No array, no offset: check x");
		assertEquals(array[1], y, "No array, no offset: check y");

		array = new double[4];
		a.toArray(array);
		assertEquals(array[0], x, "With array, no offset: check x");
		assertEquals(array[1], y, "With array, no offset: check y");

		array = new double[4];
		a.toArray(array, 1);
		assertEquals(array[0], 0, "With array and offset: check [0]");
		assertEquals(array[1], x, "With array and offset: check x");
		assertEquals(array[2], y, "With array and offset: check y");

	}

	@Test
	public void fromBufferAttribute() {

			Vector2 a = new Vector2();
			BufferAttribute attr = new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4 } ), 2 );

			a.fromBufferAttribute( attr, 0 );
			assertEquals( a.x(), 1, "Offset 0: check x" );
			assertEquals( a.y(), 2, "Offset 0: check y" );

			a.fromBufferAttribute( attr, 1 );
			assertEquals( a.x(), 3, "Offset 1: check x" );
			assertEquals( a.y(), 4, "Offset 1: check y" );

	}

//	@Test
//	public void rotateAround() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void setX_setY() {

		Vector2 a = new Vector2();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");

		a.setX(x);
		a.setY(y);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");

	}

	@Test
	public void setComponent_getComponent() {

		Vector2 a = new Vector2();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");

		a.setComponent(0, 1);
		a.setComponent(1, 2);
		assertTrue(a.getComponent(0) == 1, "Passed!");
		assertTrue(a.getComponent(1) == 2, "Passed!");

	}

	@Test
	public void multiply_divide() {

		Vector2 a = new Vector2(x, y);
		Vector2 b = new Vector2(-x, -y);

		a.multiplyScalar(-2);
		assertTrue(a.x() == x * -2, "Passed!");
		assertTrue(a.y() == y * -2, "Passed!");

		b.multiplyScalar(-2);
		assertTrue(b.x() == 2 * x, "Passed!");
		assertTrue(b.y() == 2 * y, "Passed!");

		a.divideScalar(-2);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");

		b.divideScalar(-2);
		assertTrue(b.x() == -x, "Passed!");
		assertTrue(b.y() == -y, "Passed!");

		a = new Vector2(x, y);
		b = new Vector2(2 * x, 2 * y);
		Vector2 c = new Vector2(4 * x, 4 * y);

		a.multiply(b);
		assertEquals(a.x(), x * b.x(), "multiply: check x");
		assertEquals(a.y(), y * b.y(), "multiply: check y");

		b.divide(c);
		assertEquals(b.x(), 0.5, "divide: check x");
		assertEquals(b.y(), 0.5, "divide: check y");

	}

	@Test
	public void min_max_clamp() {

			Vector2 a = new Vector2( x, y );
			Vector2 b = new Vector2( - x, - y );
			Vector2 c = new Vector2();

			c.copy( a ).min( b );
			assertTrue( c.x() == - x, "Passed!" );
			assertTrue( c.y() == - y, "Passed!" );

			c.copy( a ).max( b );
			assertTrue( c.x() == x, "Passed!" );
			assertTrue( c.y() == y, "Passed!" );

			c.set( - 2 * x, 2 * y );
			c.clamp( b, a );
			assertTrue( c.x() == - x, "Passed!" );
			assertTrue( c.y() == y, "Passed!" );

			c.set( - 2 * x, 2 * x );
			c.clampScalar( - x, x );
			assertEquals( -x, c.x(), "scalar clamp x" );
			assertEquals( x, c.y(), "scalar clamp y" );

		}

	@Test
	public void rounding() {

			assertTrue( new Vector2( - 0.1, 0.1 ).floor().equals(new Vector2( - 1, 0 )), "floor .1" );
			assertTrue( new Vector2( - 0.5, 0.5 ).floor().equals(new Vector2( - 1, 0 )), "floor .5" );
			assertTrue( new Vector2( - 0.9, 0.9 ).floor().equals(new Vector2( - 1, 0 )), "floor .9" );

			assertTrue( new Vector2( - 0.1, 0.1 ).ceil().equals(new Vector2( 0, 1 )), "ceil .1" );
			assertTrue( new Vector2( - 0.5, 0.5 ).ceil().equals(new Vector2( 0, 1 )), "ceil .5" );
			assertTrue( new Vector2( - 0.9, 0.9 ).ceil().equals(new Vector2( 0, 1 )), "ceil .9" );

			assertTrue( new Vector2( - 0.1, 0.1 ).round().equals(new Vector2( 0, 0 )), "round .1" );
			assertTrue( new Vector2( - 0.5, 0.5 ).round().equals(new Vector2( 0, 1 )), "round .5" );
			assertTrue( new Vector2( - 0.9, 0.9 ).round().equals(new Vector2( - 1, 1 )), "round .9" );

			assertTrue( new Vector2( - 0.1, 0.1 ).roundToZero().equals(new Vector2( 0, 0 )), "roundToZero .1" );
			assertTrue( new Vector2( - 0.5, 0.5 ).roundToZero().equals(new Vector2( 0, 0 )), "roundToZero .5" );
			assertTrue( new Vector2( - 0.9, 0.9 ).roundToZero().equals(new Vector2( 0, 0 )), "roundToZero .9" );
			assertTrue( new Vector2( - 1.1, 1.1 ).roundToZero().equals(new Vector2( - 1, 1 )), "roundToZero 1.1" );
			assertTrue( new Vector2( - 1.5, 1.5 ).roundToZero().equals(new Vector2( - 1, 1 )), "roundToZero 1.5" );
			assertTrue( new Vector2( - 1.9, 1.9 ).roundToZero().equals(new Vector2( - 1, 1 )), "roundToZero 1.9" );

	}

	@Test
	public void length_lengthSq() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);
		Vector2 c = new Vector2();

		assertTrue(a.length() == x, "Passed!");
		assertTrue(a.lengthSq() == x * x, "Passed!");
		assertTrue(b.length() == y, "Passed!");
		assertTrue(b.lengthSq() == y * y, "Passed!");
		assertTrue(c.length() == 0, "Passed!");
		assertTrue(c.lengthSq() == 0, "Passed!");

		a.set(x, y);
		assertTrue(a.length() == Math.sqrt(x * x + y * y), "Passed!");
		assertTrue(a.lengthSq() == (x * x + y * y), "Passed!");

	}

	@Test
	public void distanceTo_distanceToSquared() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);
		Vector2 c = new Vector2();

		assertTrue(a.distanceTo(c) == x, "Passed!");
		assertTrue(a.distanceToSquared(c) == x * x, "Passed!");

		assertTrue(b.distanceTo(c) == y, "Passed!");
		assertTrue(b.distanceToSquared(c) == y * y, "Passed!");

	}

	@Test
	public void lerp_clone() {

		Vector2 a = new Vector2(x, 0);
		Vector2 b = new Vector2(0, -y);

		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 0.5)), "Passed!");
		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 1)), "Passed!");

		assertTrue(a.clone().lerp(b, 0).equals(a), "Passed!");

		assertTrue(a.clone().lerp(b, 0.5).x() == x * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).y() == -y * 0.5, "Passed!");

		assertTrue(a.clone().lerp(b, 1).equals(b), "Passed!");

	}

//	@Test
//	public void setComponent_getComponent
//
//	exceptions() {
//
//			Vector2 a = new Vector2( 0, 0 );
//
//			assert.throws(
//
//	function () {
//
//					a.setComponent( 2, 0 );
//
//				},/
//
//	index is
//	out of range/,"setComponent with an out of range index throws Error");assert.throws(
//
//	function () {
//
//					a.getComponent( 2 );
//
//				},/
//
//	index is
//	out of range/,"getComponent with an out of range index throws Error");
//
//	}

	@Test
	public void setScalar_addScalar_subScalar() {

			Vector2 a = new Vector2( 1, 1 );
			double s = 3;

			a.setScalar( s );
			assertEquals( a.x(), s, "setScalar: check x" );
			assertEquals( a.y(), s, "setScalar: check y" );

			a.addScalar( s );
			assertEquals( a.x(), 2 * s, "addScalar: check x" );
			assertEquals( a.y(), 2 * s, "addScalar: check y" );

			a.subScalar( 2 * s );
			assertEquals( a.x(), 0, "subScalar: check x" );
			assertEquals( a.y(), 0, "subScalar: check y" );

		}

}
