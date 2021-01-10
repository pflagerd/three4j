package net.three4j.unit.src.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import net.three4j.math.Euler;
import net.three4j.math.Vector3;

///* global QUnit */
//
//import { Vector3 } from '../../../../src/math/Vector3';
//import { Vector4 } from '../../../../src/math/Vector4';
//import { Matrix3 } from '../../../../src/math/Matrix3';
//import { Matrix4 } from '../../../../src/math/Matrix4';
//import { Spherical } from '../../../../src/math/Spherical';
//import { Quaternion } from '../../../../src/math/Quaternion';
//import { Euler } from '../../../../src/math/Euler';
//import { Cylindrical } from '../../../../src/math/Cylindrical';
//import { BufferAttribute } from '../../../../src/core/BufferAttribute';
//import { PerspectiveCamera } from '../../../../src/cameras/PerspectiveCamera';
//import {
//	x,
//	y,
//	z,
//	w,
//	eps
//} from './Constants.tests';
//
//export default QUnit.module( 'Maths', () => {
//
//	QUnit.module( 'Vector3', () => {
public class Vector3Tests {

	final double x = ThreadLocalRandom.current().nextDouble(-Double.MAX_VALUE, Double.MAX_VALUE);
	final double y = ThreadLocalRandom.current().nextDouble(-Double.MAX_VALUE, Double.MAX_VALUE);
	final double z = ThreadLocalRandom.current().nextDouble(-Double.MAX_VALUE, Double.MAX_VALUE);
	final double eps = 0.0001;
	
	@Test
	public void Instancing() {

		Vector3 a = new Vector3();
		assertTrue(a.x == 0, "Passed!");
		assertTrue(a.y == 0, "Passed!");
		assertTrue(a.z == 0, "Passed!");
	}

	@Test
	public void isVector3() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void set() {

		Vector3 a = new Vector3();
		assertTrue(a.x == 0, "Passed!");
		assertTrue(a.y == 0, "Passed!");
		assertTrue(a.z == 0, "Passed!");

		a.set(x, y, z);
		assertTrue(a.x == x, "Passed!");
		assertTrue(a.y == y, "Passed!");
		assertTrue(a.z == z, "Passed!");

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

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3().copy(a);
		b.copy(a);
		assertTrue(b.x == x, "Passed!");
		assertTrue(b.y == y, "Passed!");
		assertTrue(b.z == z, "Passed!");

		// ensure that it is a true copy
		a.x = 0;
		a.y = -1;
		a.z = -2;
		assertTrue(b.x == x, "Passed!");
		assertTrue(b.y == y, "Passed!");
		assertTrue(b.z == z, "Passed!");

	}

	@Test
	public void add() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(-x, -y, -z);

		a.add(b);
		assertTrue(a.x == 0, "Passed!");
		assertTrue(a.y == 0, "Passed!");
		assertTrue(a.z == 0, "Passed!");

		Vector3 c = new Vector3().addVectors(b, b);
		assertTrue(c.x == -2 * x, "Passed!");
		assertTrue(c.y == -2 * y, "Passed!");
		assertTrue(c.z == -2 * z, "Passed!");

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

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(2, 3, 4);
		double s = 3;

		a.addScaledVector(b, s);
		assertEquals(a.x + b.x * s, a.x, "Check x");
		assertEquals(a.y + b.y * s, a.y, "Check y");
		assertEquals(a.z + b.z * s, a.z, "Check z");

	}

	@Test
	public void sub() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(x, y, z);

		a.sub(b);
		assertTrue(a.x == 2 * x, "Passed!");
		assertTrue(a.y == 2 * y, "Passed!");
		assertTrue(a.z == 2 * z, "Passed!");

		Vector3 c = new Vector3();
		assertTrue(c.x == 0, "Passed!");
		assertTrue(c.y == 0, "Passed!");
		assertTrue(c.z == 0, "Passed!");

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
	public void multiply() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void multiplyScalar() {

		assertTrue(false, "everything's gonna be alright");

	}

	@Test
	public void multiplyVectors() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(2, 3, -5);

		Vector3 c = new Vector3().multiplyVectors(a, b);
		assertEquals(x * 2, c.x, "Check x");
		assertEquals(y * 3, c.y, "Check y");
		assertEquals(z * -5, c.z, "Check z");

	}

@Test
public void applyEuler() {

	var a = new Vector3( x, y, z );
	var euler = new Euler( 90, -45, 0 );
	var expected = new Vector3( - 2.352970120501014, - 4.7441750936226645, 0.9779234597246458 );

	a.applyEuler( euler );
	assertTrue( Math.abs( a.x - expected.x ) <= eps, "Check x" );
	assertTrue( Math.abs( a.y - expected.y ) <= eps, "Check y" );
	assertTrue( Math.abs( a.z - expected.z ) <= eps, "Check z" );

}

//@Test
//public void applyAxisAngle() {
//
//	var a = new Vector3( x, y, z );
//	var axis = new Vector3( 0, 1, 0 );
//	var angle = Math.PI / 4.0;
//	var expected = new Vector3( 3 * Math.sqrt( 2 ), 3, Math.sqrt( 2 ) );
//
//	a.applyAxisAngle( axis, angle );
//	assertTrue( Math.abs( a.x - expected.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - expected.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - expected.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void applyMatrix3() {
//
//	var a = new Vector3( x, y, z );
//	var m = new Matrix3().set( 2, 3, 5, 7, 11, 13, 17, 19, 23 );
//
//	a.applyMatrix3( m );
//	assert.strictEqual( a.x, 33, "Check x" );
//	assert.strictEqual( a.y, 99, "Check y" );
//	assert.strictEqual( a.z, 183, "Check z" );
//
//}
//
//@Test
//public void applyMatrix4() {
//
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector4( x, y, z, 1 );
//
//	var m = new Matrix4().makeRotationX( Math.PI );
//	a.applyMatrix4( m );
//	b.applyMatrix4( m );
//	assertTrue( a.x == b.x / b.w, "Passed!" );
//	assertTrue( a.y == b.y / b.w, "Passed!" );
//	assertTrue( a.z == b.z / b.w, "Passed!" );
//
//	var m = new Matrix4().makeTranslation( 3, 2, 1 );
//	a.applyMatrix4( m );
//	b.applyMatrix4( m );
//	assertTrue( a.x == b.x / b.w, "Passed!" );
//	assertTrue( a.y == b.y / b.w, "Passed!" );
//	assertTrue( a.z == b.z / b.w, "Passed!" );
//
//	var m = new Matrix4().set(
//		1, 0, 0, 0,
//		0, 1, 0, 0,
//		0, 0, 1, 0,
//		0, 0, 1, 0
//	);
//	a.applyMatrix4( m );
//	b.applyMatrix4( m );
//	assertTrue( a.x == b.x / b.w, "Passed!" );
//	assertTrue( a.y == b.y / b.w, "Passed!" );
//	assertTrue( a.z == b.z / b.w, "Passed!" );
//
//}
//
//@Test
//public void applyQuaternion() {
//
//	var a = new Vector3( x, y, z );
//
//	a.applyQuaternion( new Quaternion() );
//	assert.strictEqual( a.x, x, "Identity rotation: check x" );
//	assert.strictEqual( a.y, y, "Identity rotation: check y" );
//	assert.strictEqual( a.z, z, "Identity rotation: check z" );
//
//	a.applyQuaternion( new Quaternion( x, y, z, w ) );
//	assert.strictEqual( a.x, 108, "Normal rotation: check x" );
//	assert.strictEqual( a.y, 162, "Normal rotation: check y" );
//	assert.strictEqual( a.z, 216, "Normal rotation: check z" );
//
//}
//
//@Test
//	public void project() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void unproject() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void transformDirection() {
//
//	var a = new Vector3( x, y, z );
//	var m = new Matrix4();
//	var transformed = new Vector3( 0.3713906763541037, 0.5570860145311556, 0.7427813527082074 );
//
//	a.transformDirection( m );
//	assertTrue( Math.abs( a.x - transformed.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - transformed.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - transformed.z ) <= eps, "Check z" );
//
//}
//
//@Test
//	public void divide() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void divideScalar() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void min() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void max() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void clamp() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void clampScalar() {
//
//	var a = new Vector3( - 0.01, 0.5, 1.5 );
//	var clamped = new Vector3( 0.1, 0.5, 1.0 );
//
//	a.clampScalar( 0.1, 1.0 );
//	assertTrue( Math.abs( a.x - clamped.x ) <= 0.001, "Check x" );
//	assertTrue( Math.abs( a.y - clamped.y ) <= 0.001, "Check y" );
//	assertTrue( Math.abs( a.z - clamped.z ) <= 0.001, "Check z" );
//
//}
//
//@Test
//	public void clampLength() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void floor() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void ceil() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void round() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void roundToZero() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void negate() {
//
//	var a = new Vector3( x, y, z );
//
//	a.negate();
//	assertTrue( a.x == - x, "Passed!" );
//	assertTrue( a.y == - y, "Passed!" );
//	assertTrue( a.z == - z, "Passed!" );
//
//}
//
//@Test
//public void dot() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( - x, - y, - z );
//	var c = new Vector3();
//
//	var result = a.dot( b );
//	assertTrue( result == ( - x * x - y * y - z * z ), "Passed!" );
//
//	var result = a.dot( c );
//	assertTrue( result == 0, "Passed!" );
//
//}
//
//@Test
//	public void lengthSq() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void length() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void manhattanLength() {
//
//	var a = new Vector3( x, 0, 0 );
//	var b = new Vector3( 0, - y, 0 );
//	var c = new Vector3( 0, 0, z );
//	var d = new Vector3();
//
//	assertTrue( a.manhattanLength() == x, "Positive x" );
//	assertTrue( b.manhattanLength() == y, "Negative y" );
//	assertTrue( c.manhattanLength() == z, "Positive z" );
//	assertTrue( d.manhattanLength() == 0, "Empty initialization" );
//
//	a.set( x, y, z );
//	assertTrue( a.manhattanLength() == Math.abs( x ) + Math.abs( y ) + Math.abs( z ), "All components" );
//
//}
//
//@Test
//public void normalize() {
//
//	var a = new Vector3( x, 0, 0 );
//	var b = new Vector3( 0, - y, 0 );
//	var c = new Vector3( 0, 0, z );
//
//	a.normalize();
//	assertTrue( a.length() == 1, "Passed!" );
//	assertTrue( a.x == 1, "Passed!" );
//
//	b.normalize();
//	assertTrue( b.length() == 1, "Passed!" );
//	assertTrue( b.y == - 1, "Passed!" );
//
//	c.normalize();
//	assertTrue( c.length() == 1, "Passed!" );
//	assertTrue( c.z == 1, "Passed!" );
//
//}
//
//@Test
//public void setLength() {
//
//	var a = new Vector3( x, 0, 0 );
//
//	assertTrue( a.length() == x, "Passed!" );
//	a.setLength( y );
//	assertTrue( a.length() == y, "Passed!" );
//
//	var a = new Vector3( 0, 0, 0 );
//	assertTrue( a.length() == 0, "Passed!" );
//	a.setLength( y );
//	assertTrue( a.length() == 0, "Passed!" );
//	a.setLength();
//	assertTrue( isNaN( a.length() ), "Passed!" );
//
//}
//
//@Test
//	public void lerp() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void lerpVectors() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void cross() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( 2 * x, - y, 0.5 * z );
//	var crossed = new Vector3( 18, 12, - 18 );
//
//	a.cross( b );
//	assertTrue( Math.abs( a.x - crossed.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - crossed.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - crossed.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void crossVectors() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( x, - y, z );
//	var c = new Vector3();
//	var crossed = new Vector3( 24, 0, - 12 );
//
//	c.crossVectors( a, b );
//	assertTrue( Math.abs( c.x - crossed.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( c.y - crossed.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( c.z - crossed.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void projectOnVector() {
//
//	var a = new Vector3( 1, 0, 0 );
//	var b = new Vector3();
//	var normal = new Vector3( 10, 0, 0 );
//
//	assertTrue( b.copy( a ).projectOnVector( normal ).equals( new Vector3( 1, 0, 0 ) ), "Passed!" );
//
//	a.set( 0, 1, 0 );
//	assertTrue( b.copy( a ).projectOnVector( normal ).equals( new Vector3( 0, 0, 0 ) ), "Passed!" );
//
//	a.set( 0, 0, - 1 );
//	assertTrue( b.copy( a ).projectOnVector( normal ).equals( new Vector3( 0, 0, 0 ) ), "Passed!" );
//
//	a.set( - 1, 0, 0 );
//	assertTrue( b.copy( a ).projectOnVector( normal ).equals( new Vector3( - 1, 0, 0 ) ), "Passed!" );
//
//}
//
//@Test
//public void projectOnPlane() {
//
//	var a = new Vector3( 1, 0, 0 );
//	var b = new Vector3();
//	var normal = new Vector3( 1, 0, 0 );
//
//	assertTrue( b.copy( a ).projectOnPlane( normal ).equals( new Vector3( 0, 0, 0 ) ), "Passed!" );
//
//	a.set( 0, 1, 0 );
//	assertTrue( b.copy( a ).projectOnPlane( normal ).equals( new Vector3( 0, 1, 0 ) ), "Passed!" );
//
//	a.set( 0, 0, - 1 );
//	assertTrue( b.copy( a ).projectOnPlane( normal ).equals( new Vector3( 0, 0, - 1 ) ), "Passed!" );
//
//	a.set( - 1, 0, 0 );
//	assertTrue( b.copy( a ).projectOnPlane( normal ).equals( new Vector3( 0, 0, 0 ) ), "Passed!" );
//
//}
//
//@Test
//public void reflect() {
//
//	var a = new Vector3();
//	var normal = new Vector3( 0, 1, 0 );
//	var b = new Vector3();
//
//	a.set( 0, - 1, 0 );
//	assertTrue( b.copy( a ).reflect( normal ).equals( new Vector3( 0, 1, 0 ) ), "Passed!" );
//
//	a.set( 1, - 1, 0 );
//	assertTrue( b.copy( a ).reflect( normal ).equals( new Vector3( 1, 1, 0 ) ), "Passed!" );
//
//	a.set( 1, - 1, 0 );
//	normal.set( 0, - 1, 0 );
//	assertTrue( b.copy( a ).reflect( normal ).equals( new Vector3( 1, 1, 0 ) ), "Passed!" );
//
//}
//
//@Test
//public void angleTo() {
//
//	var a = new Vector3( 0, - 0.18851655680720186, 0.9820700116639124 );
//	var b = new Vector3( 0, 0.18851655680720186, - 0.9820700116639124 );
//
//	assert.equal( a.angleTo( a ), 0 );
//	assert.equal( a.angleTo( b ), Math.PI );
//
//	var x = new Vector3( 1, 0, 0 );
//	var y = new Vector3( 0, 1, 0 );
//	var z = new Vector3( 0, 0, 1 );
//
//	assert.equal( x.angleTo( y ), Math.PI / 2 );
//	assert.equal( x.angleTo( z ), Math.PI / 2 );
//	assert.equal( z.angleTo( x ), Math.PI / 2 );
//
//	assertTrue( Math.abs( x.angleTo( new Vector3( 1, 1, 0 ) ) - ( Math.PI / 4 ) ) < 0.0000001 );
//
//}
//
//@Test
//	public void distanceTo() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void distanceToSquared() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//	public void manhattanDistanceTo() {
//
//	assertTrue( false, "everything's gonna be alright" );
//
//}
//
//@Test
//public void setFromSpherical() {
//
//	var a = new Vector3();
//	var phi = Math.acos( - 0.5 );
//	var theta = Math.sqrt( Math.PI ) * phi;
//	var sph = new Spherical( 10, phi, theta );
//	var expected = new Vector3( - 4.677914006701843, - 5, - 7.288149322420796 );
//
//	a.setFromSpherical( sph );
//	assertTrue( Math.abs( a.x - expected.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - expected.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - expected.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void setFromCylindrical() {
//
//	var a = new Vector3();
//	var cyl = new Cylindrical( 10, Math.PI * 0.125, 20 );
//	var expected = new Vector3( 3.826834323650898, 20, 9.238795325112868 );
//
//	a.setFromCylindrical( cyl );
//	assertTrue( Math.abs( a.x - expected.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - expected.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - expected.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void setFromMatrixPosition() {
//
//	var a = new Vector3();
//	var m = new Matrix4().set( 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53 );
//
//	a.setFromMatrixPosition( m );
//	assert.strictEqual( a.x, 7, "Check x" );
//	assert.strictEqual( a.y, 19, "Check y" );
//	assert.strictEqual( a.z, 37, "Check z" );
//
//}
//
//@Test
//public void setFromMatrixScale() {
//
//	var a = new Vector3();
//	var m = new Matrix4().set( 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53 );
//	var expected = new Vector3( 25.573423705088842, 31.921779399024736, 35.70714214271425 );
//
//	a.setFromMatrixScale( m );
//	assertTrue( Math.abs( a.x - expected.x ) <= eps, "Check x" );
//	assertTrue( Math.abs( a.y - expected.y ) <= eps, "Check y" );
//	assertTrue( Math.abs( a.z - expected.z ) <= eps, "Check z" );
//
//}
//
//@Test
//public void setFromMatrixColumn() {
//
//	var a = new Vector3();
//	var m = new Matrix4().set( 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53 );
//
//	a.setFromMatrixColumn( m, 0 );
//	assert.strictEqual( a.x, 2, "Index 0: check x" );
//	assert.strictEqual( a.y, 11, "Index 0: check y" );
//	assert.strictEqual( a.z, 23, "Index 0: check z" );
//
//	a.setFromMatrixColumn( m, 2 );
//	assert.strictEqual( a.x, 5, "Index 2: check x" );
//	assert.strictEqual( a.y, 17, "Index 2: check y" );
//	assert.strictEqual( a.z, 31, "Index 2: check z" );
//
//}
//
//@Test
//public void equals() {
//
//	var a = new Vector3( x, 0, z );
//	var b = new Vector3( 0, - y, 0 );
//
//	assertTrue( a.x != b.x, "Passed!" );
//	assertTrue( a.y != b.y, "Passed!" );
//	assertTrue( a.z != b.z, "Passed!" );
//
//	assertTrue( ! a.equals( b ), "Passed!" );
//	assertTrue( ! b.equals( a ), "Passed!" );
//
//	a.copy( b );
//	assertTrue( a.x == b.x, "Passed!" );
//	assertTrue( a.y == b.y, "Passed!" );
//	assertTrue( a.z == b.z, "Passed!" );
//
//	assertTrue( a.equals( b ), "Passed!" );
//	assertTrue( b.equals( a ), "Passed!" );
//
//}
//
//@Test
//public void fromArray() {
//
//	var a = new Vector3();
//	var array = [ 1, 2, 3, 4, 5, 6 ];
//
//	a.fromArray( array );
//	assert.strictEqual( a.x, 1, "No offset: check x" );
//	assert.strictEqual( a.y, 2, "No offset: check y" );
//	assert.strictEqual( a.z, 3, "No offset: check z" );
//
//	a.fromArray( array, 3 );
//	assert.strictEqual( a.x, 4, "With offset: check x" );
//	assert.strictEqual( a.y, 5, "With offset: check y" );
//	assert.strictEqual( a.z, 6, "With offset: check z" );
//
//}
//
//@Test
//public void toArray() {
//
//	var a = new Vector3( x, y, z );
//
//	var array = a.toArray();
//	assert.strictEqual( array[ 0 ], x, "No array, no offset: check x" );
//	assert.strictEqual( array[ 1 ], y, "No array, no offset: check y" );
//	assert.strictEqual( array[ 2 ], z, "No array, no offset: check z" );
//
//	var array = [];
//	a.toArray( array );
//	assert.strictEqual( array[ 0 ], x, "With array, no offset: check x" );
//	assert.strictEqual( array[ 1 ], y, "With array, no offset: check y" );
//	assert.strictEqual( array[ 2 ], z, "With array, no offset: check z" );
//
//	var array = [];
//	a.toArray( array, 1 );
//	assert.strictEqual( array[ 0 ], undefined, "With array and offset: check [0]" );
//	assert.strictEqual( array[ 1 ], x, "With array and offset: check x" );
//	assert.strictEqual( array[ 2 ], y, "With array and offset: check y" );
//	assert.strictEqual( array[ 3 ], z, "With array and offset: check z" );
//
//}
//
//@Test
//public void fromBufferAttribute() {
//
//	var a = new Vector3();
//	var attr = new BufferAttribute( new Float32Array( [ 1, 2, 3, 4, 5, 6 ] ), 3 );
//
//	a.fromBufferAttribute( attr, 0 );
//	assert.strictEqual( a.x, 1, "Offset 0: check x" );
//	assert.strictEqual( a.y, 2, "Offset 0: check y" );
//	assert.strictEqual( a.z, 3, "Offset 0: check z" );
//
//	a.fromBufferAttribute( attr, 1 );
//	assert.strictEqual( a.x, 4, "Offset 1: check x" );
//	assert.strictEqual( a.y, 5, "Offset 1: check y" );
//	assert.strictEqual( a.z, 6, "Offset 1: check z" );
//
//}
//
//// TODO (Itee) refactor/split
//@Test
//public void setX,setY,setZ()
//	{
//
//	var a = new Vector3();
//	assertTrue( a.x == 0, "Passed!" );
//	assertTrue( a.y == 0, "Passed!" );
//	assertTrue( a.z == 0, "Passed!" );
//
//	a.setX( x );
//	a.setY( y );
//	a.setZ( z );
//
//	assertTrue( a.x == x, "Passed!" );
//	assertTrue( a.y == y, "Passed!" );
//	assertTrue( a.z == z, "Passed!" );
//
//}
//@Test
//public void setComponent,getComponent()
//	{
//
//	var a = new Vector3();
//	assertTrue( a.x == 0, "Passed!" );
//	assertTrue( a.y == 0, "Passed!" );
//	assertTrue( a.z == 0, "Passed!" );
//
//	a.setComponent( 0, 1 );
//	a.setComponent( 1, 2 );
//	a.setComponent( 2, 3 );
//	assertTrue( a.getComponent( 0 ) == 1, "Passed!" );
//	assertTrue( a.getComponent( 1 ) == 2, "Passed!" );
//	assertTrue( a.getComponent( 2 ) == 3, "Passed!" );
//
//}
//@Test
//public void setComponent/getComponent exceptions() {
//
//	var a = new Vector3();
//
//	assert.throws(
//
//	function () {
//
//			a.setComponent( 3, 0 );
//
//		},/
//
//	index is
//	out of range/,"setComponent with an out of range index throws Error");assert.throws(
//
//	function () {
//
//			a.getComponent( 3 );
//
//		},/
//
//	index is
//	out of range/,"getComponent with an out of range index throws Error");
//
//	}
//	@Test
//	public void min/max/clamp() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( - x, - y, - z );
//	var c = new Vector3();
//
//	c.copy( a ).min( b );
//	assertTrue( c.x == - x, "Passed!" );
//	assertTrue( c.y == - y, "Passed!" );
//	assertTrue( c.z == - z, "Passed!" );
//
//	c.copy( a ).max( b );
//	assertTrue( c.x == x, "Passed!" );
//	assertTrue( c.y == y, "Passed!" );
//	assertTrue( c.z == z, "Passed!" );
//
//	c.set( - 2 * x, 2 * y, - 2 * z );
//	c.clamp( b, a );
//	assertTrue( c.x == - x, "Passed!" );
//	assertTrue( c.y == y, "Passed!" );
//	assertTrue( c.z == - z, "Passed!" );
//
//}
//
//@Test
//public void distanceTo/distanceToSquared() {
//
//	var a = new Vector3( x, 0, 0 );
//	var b = new Vector3( 0, - y, 0 );
//	var c = new Vector3( 0, 0, z );
//	var d = new Vector3();
//
//	assertTrue( a.distanceTo( d ) == x, "Passed!" );
//	assertTrue( a.distanceToSquared( d ) == x * x, "Passed!" );
//
//	assertTrue( b.distanceTo( d ) == y, "Passed!" );
//	assertTrue( b.distanceToSquared( d ) == y * y, "Passed!" );
//
//	assertTrue( c.distanceTo( d ) == z, "Passed!" );
//	assertTrue( c.distanceToSquared( d ) == z * z, "Passed!" );
//
//}
//
//@Test
//public void setScalar/addScalar/subScalar() {
//
//	var a = new Vector3();
//	var s = 3;
//
//	a.setScalar( s );
//	assert.strictEqual( a.x, s, "setScalar: check x" );
//	assert.strictEqual( a.y, s, "setScalar: check y" );
//	assert.strictEqual( a.z, s, "setScalar: check z" );
//
//	a.addScalar( s );
//	assert.strictEqual( a.x, 2 * s, "addScalar: check x" );
//	assert.strictEqual( a.y, 2 * s, "addScalar: check y" );
//	assert.strictEqual( a.z, 2 * s, "addScalar: check z" );
//
//	a.subScalar( 2 * s );
//	assert.strictEqual( a.x, 0, "subScalar: check x" );
//	assert.strictEqual( a.y, 0, "subScalar: check y" );
//	assert.strictEqual( a.z, 0, "subScalar: check z" );
//
//}
//
//@Test
//public void multiply/divide() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( 2 * x, 2 * y, 2 * z );
//	var c = new Vector3( 4 * x, 4 * y, 4 * z );
//
//	a.multiply( b );
//	assert.strictEqual( a.x, x * b.x, "multiply: check x" );
//	assert.strictEqual( a.y, y * b.y, "multiply: check y" );
//	assert.strictEqual( a.z, z * b.z, "multiply: check z" );
//
//	b.divide( c );
//	assertTrue( Math.abs( b.x - 0.5 ) <= eps, "divide: check z" );
//	assertTrue( Math.abs( b.y - 0.5 ) <= eps, "divide: check z" );
//	assertTrue( Math.abs( b.z - 0.5 ) <= eps, "divide: check z" );
//
//}
//
//@Test
//public void multiply/divide() {
//
//	var a = new Vector3( x, y, z );
//	var b = new Vector3( - x, - y, - z );
//
//	a.multiplyScalar( - 2 );
//	assertTrue( a.x == x * - 2, "Passed!" );
//	assertTrue( a.y == y * - 2, "Passed!" );
//	assertTrue( a.z == z * - 2, "Passed!" );
//
//	b.multiplyScalar( - 2 );
//	assertTrue( b.x == 2 * x, "Passed!" );
//	assertTrue( b.y == 2 * y, "Passed!" );
//	assertTrue( b.z == 2 * z, "Passed!" );
//
//	a.divideScalar( - 2 );
//	assertTrue( a.x == x, "Passed!" );
//	assertTrue( a.y == y, "Passed!" );
//	assertTrue( a.z == z, "Passed!" );
//
//	b.divideScalar( - 2 );
//	assertTrue( b.x == - x, "Passed!" );
//	assertTrue( b.y == - y, "Passed!" );
//	assertTrue( b.z == - z, "Passed!" );
//
//}
//
//@Test
//public void project/unproject() {
//
//	var a = new Vector3( x, y, z );
//	var camera = new PerspectiveCamera( 75, 16 / 9, 0.1, 300.0 );
//	var projected = new Vector3( - 0.36653213611158914, - 0.9774190296309043, 1.0506835611870624 );
//
//	a.project( camera );
//	assertTrue( Math.abs( a.x - projected.x ) <= eps, "project: check x" );
//	assertTrue( Math.abs( a.y - projected.y ) <= eps, "project: check y" );
//	assertTrue( Math.abs( a.z - projected.z ) <= eps, "project: check z" );
//
//	a.unproject( camera );
//	assertTrue( Math.abs( a.x - x ) <= eps, "unproject: check x" );
//	assertTrue( Math.abs( a.y - y ) <= eps, "unproject: check y" );
//	assertTrue( Math.abs( a.z - z ) <= eps, "unproject: check z" );
//
//}
//
//@Test
//public void length/lengthSq() {
//
//	var a = new Vector3( x, 0, 0 );
//	var b = new Vector3( 0, - y, 0 );
//	var c = new Vector3( 0, 0, z );
//	var d = new Vector3();
//
//	assertTrue( a.length() == x, "Passed!" );
//	assertTrue( a.lengthSq() == x * x, "Passed!" );
//	assertTrue( b.length() == y, "Passed!" );
//	assertTrue( b.lengthSq() == y * y, "Passed!" );
//	assertTrue( c.length() == z, "Passed!" );
//	assertTrue( c.lengthSq() == z * z, "Passed!" );
//	assertTrue( d.length() == 0, "Passed!" );
//	assertTrue( d.lengthSq() == 0, "Passed!" );
//
//	a.set( x, y, z );
//	assertTrue( a.length() == Math.sqrt( x * x + y * y + z * z ), "Passed!" );
//	assertTrue( a.lengthSq() == ( x * x + y * y + z * z ), "Passed!" );
//
//}
//
//@Test
//public void lerp/clone() {
//
//	var a = new Vector3( x, 0, z );
//	var b = new Vector3( 0, - y, 0 );
//
//	assertTrue( a.lerp( a, 0 ).equals( a.lerp( a, 0.5 ) ), "Passed!" );
//	assertTrue( a.lerp( a, 0 ).equals( a.lerp( a, 1 ) ), "Passed!" );
//
//	assertTrue( a.clone().lerp( b, 0 ).equals( a ), "Passed!" );
//
//	assertTrue( a.clone().lerp( b, 0.5 ).x == x * 0.5, "Passed!" );
//	assertTrue( a.clone().lerp( b, 0.5 ).y == - y * 0.5, "Passed!" );
//	assertTrue( a.clone().lerp( b, 0.5 ).z == z * 0.5, "Passed!" );
//
//	assertTrue( a.clone().lerp( b, 1 ).equals( b ), "Passed!" );
//
//}

}
