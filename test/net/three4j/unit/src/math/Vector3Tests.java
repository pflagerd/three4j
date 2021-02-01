package net.three4j.unit.src.math;

import static net.three4j.unit.src.math.ConstantsTests.eps;
import static net.three4j.unit.src.math.ConstantsTests.w;
import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mozilla.types.Float32Array;

import net.three4j.cameras.PerspectiveCamera;
import net.three4j.core.BufferAttribute;
import net.three4j.math.Cylindrical;
import net.three4j.math.Euler;
import net.three4j.math.Matrix3;
import net.three4j.math.Matrix4;
import net.three4j.math.Quaternion;
import net.three4j.math.Spherical;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;

public class Vector3Tests {

	@Test
	public void Instancing() {

		Vector3 a = new Vector3();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");
	}

	@Test
	public void set() {

		Vector3 a = new Vector3();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");

		a.set(x, y, z);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");

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
//	public void setZ() {
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

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3().copy(a);
		b.copy(a);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");
		assertTrue(b.z() == z, "Passed!");

		// ensure that it is a true copy
		a.x(0);
		a.y(-1);
		a.z(-2);
		assertTrue(b.x() == x, "Passed!");
		assertTrue(b.y() == y, "Passed!");
		assertTrue(b.z() == z, "Passed!");

	}

	@Test
	public void add() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(-x, -y, -z);

		a.add(b);
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");

		Vector3 c = new Vector3().addVectors(b, b);
		assertTrue(c.x() == -2 * x, "Passed!");
		assertTrue(c.y() == -2 * y, "Passed!");
		assertTrue(c.z() == -2 * z, "Passed!");

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

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(3, 4, 5);
		double s = 3;

		a.addScaledVector(b, s);
		assertEquals(x + b.x() * s, a.x(), "Check x");
		assertEquals(y + b.y() * s, a.y(), "Check y");
		assertEquals(z + b.z() * s, a.z(), "Check z");

	}

	@Test
	public void sub() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(-x, -y, -z);

		a.sub(b);
		assertTrue(a.x() == 2 * x, "Passed!");
		assertTrue(a.y() == 2 * y, "Passed!");
		assertTrue(a.z() == 2 * z, "Passed!");

		Vector3 c = new Vector3();
		assertTrue(c.x() == 0, "Passed!");
		assertTrue(c.y() == 0, "Passed!");
		assertTrue(c.z() == 0, "Passed!");

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

	@Test
	public void multiplyVectors() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(2, 3, -5);

		Vector3 c = new Vector3().multiplyVectors(a, b);
		assertEquals(x * 2, c.x(), "Check x");
		assertEquals(y * 3, c.y(), "Check y");
		assertEquals(z * -5, c.z(), "Check z");

	}

	@Test
	public void applyEuler() {

		Vector3 a = new Vector3(x, y, z);
		Euler euler = new Euler(90, -45, 0);
		Vector3 expected = new Vector3(-2.352970120501014, -4.7441750936226645, 0.9779234597246458);

		a.applyEuler(euler);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Check z");

	}

	@Test
	public void applyAxisAngle() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 axis = new Vector3(0, 1, 0);
		double angle = Math.PI / 4.0;
		Vector3 expected = new Vector3(3 * Math.sqrt(2), 3, Math.sqrt(2));

		a.applyAxisAngle(axis, angle);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Check z");

	}

	@Test
	public void applyMatrix3() {

		Vector3 a = new Vector3(x, y, z);
		Matrix3 m = new Matrix3().set(2, 3, 5, 7, 11, 13, 17, 19, 23);

		a.applyMatrix3(m);
		assertEquals(33, a.x(), "Check x");
		assertEquals(99, a.y(), "Check y");
		assertEquals(183, a.z(), "Check z");

	}

	@Test
	public void applyMatrix4() {

		Vector3 a = new Vector3(x, y, z);
		Vector4 b = new Vector4(x, y, z, 1);

		Matrix4 m = new Matrix4().makeRotationX(Math.PI);
		a.applyMatrix4(m);
		b.applyMatrix4(m);
		assertTrue(a.x() == b.x() / b.w(), "Passed!");
		assertTrue(a.y() == b.y() / b.w(), "Passed!");
		assertTrue(a.z() == b.z() / b.w(), "Passed!");

		m = new Matrix4().makeTranslation(3, 2, 1);
		a.applyMatrix4(m);
		b.applyMatrix4(m);
		assertTrue(a.x() == b.x() / b.w(), "Passed!");
		assertTrue(a.y() == b.y() / b.w(), "Passed!");
		assertTrue(a.z() == b.z() / b.w(), "Passed!");

		m = new Matrix4().set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0);
		a.applyMatrix4(m);
		b.applyMatrix4(m);
		assertTrue(a.x() == b.x() / b.w(), "Passed!");
		assertTrue(a.y() == b.y() / b.w(), "Passed!");
		assertTrue(a.z() == b.z() / b.w(), "Passed!");
	}

	@Test
	public void applyQuaternion() {

		Vector3 a = new Vector3(x, y, z);

		a.applyQuaternion(new Quaternion());
		assertEquals(a.x(), x, "Identity rotation: check x");
		assertEquals(a.y(), y, "Identity rotation: check y");
		assertEquals(a.z(), z, "Identity rotation: check z");

		a.applyQuaternion(new Quaternion(x, y, z, w));
		assertEquals(a.x(), 108, "Normal rotation: check x");
		assertEquals(a.y(), 162, "Normal rotation: check y");
		assertEquals(a.z(), 216, "Normal rotation: check z");

	}

//	@Test
//	public void project() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}
//
//	@Test
//	public void unproject() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void transformDirection() {

		Vector3 a = new Vector3(x, y, z);
		Matrix4 m = new Matrix4();
		Vector3 transformed = new Vector3(0.3713906763541037, 0.5570860145311556, 0.7427813527082074);

		a.transformDirection(m);
		assertTrue(Math.abs(a.x() - transformed.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - transformed.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - transformed.z()) <= eps, "Check z");

	}

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
//
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

	@Test
	public void clampScalar() {

		Vector3 a = new Vector3(-0.01, 0.5, 1.5);
		Vector3 clamped = new Vector3(0.1, 0.5, 1.0);

		a.clampScalar(0.1, 1.0);
		assertTrue(Math.abs(a.x() - clamped.x()) <= 0.001, "Check x");
		assertTrue(Math.abs(a.y() - clamped.y()) <= 0.001, "Check y");
		assertTrue(Math.abs(a.z() - clamped.z()) <= 0.001, "Check z");

	}

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

		Vector3 a = new Vector3(x, y, z);

		a.negate();
		assertTrue(a.x() == -x, "Passed!");
		assertTrue(a.y() == -y, "Passed!");
		assertTrue(a.z() == -z, "Passed!");

	}

	@Test
	public void dot() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(-x, -y, -z);
		Vector3 c = new Vector3();

		double result = a.dot(b);
		assertTrue(result == (-x * x - y * y - z * z), "Passed!");

		result = a.dot(c);
		assertTrue(result == 0, "Passed!");

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

		Vector3 a = new Vector3(x, 0, 0);
		Vector3 b = new Vector3(0, -y, 0);
		Vector3 c = new Vector3(0, 0, z);
		Vector3 d = new Vector3();

		assertTrue(a.manhattanLength() == x, "Positive x");
		assertTrue(b.manhattanLength() == y, "Negative y");
		assertTrue(c.manhattanLength() == z, "Positive z");
		assertTrue(d.manhattanLength() == 0, "Empty initialization");

		a.set(x, y, z);
		assertTrue(a.manhattanLength() == Math.abs(x) + Math.abs(y) + Math.abs(z), "All components");

	}

	@Test
	public void normalize() {

		Vector3 a = new Vector3(x, 0, 0);
		Vector3 b = new Vector3(0, -y, 0);
		Vector3 c = new Vector3(0, 0, z);

		a.normalize();
		assertTrue(a.length() == 1, "Passed!");
		assertTrue(a.x() == 1, "Passed!");

		b.normalize();
		assertTrue(b.length() == 1, "Passed!");
		assertTrue(b.y() == -1, "Passed!");

		c.normalize();
		assertTrue(c.length() == 1, "Passed!");
		assertTrue(c.z() == 1, "Passed!");

	}

	@Test
	public void setLength() {

		Vector3 a = new Vector3(x, 0, 0);

		assertTrue(a.length() == x, "Passed!");
		a.setLength(y);
		assertTrue(a.length() == y, "Passed!");

		a = new Vector3(0, 0, 0);
		assertTrue(a.length() == 0, "Passed!");
		a.setLength(y);
		assertTrue(a.length() == 0, "Passed!");
//	a.setLength(); // DPP: Huh?
//	assertTrue( isNaN( a.length() ), "Passed!" );

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
	public void cross() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(2 * x, -y, 0.5 * z);
		Vector3 crossed = new Vector3(18, 12, -18);

		a.cross(b);
		assertTrue(Math.abs(a.x() - crossed.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - crossed.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - crossed.z()) <= eps, "Check z");

	}

	@Test
	public void crossVectors() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(x, -y, z);
		Vector3 c = new Vector3();
		Vector3 crossed = new Vector3(24, 0, -12);

		c.crossVectors(a, b);
		assertTrue(Math.abs(c.x() - crossed.x()) <= eps, "Check x");
		assertTrue(Math.abs(c.y() - crossed.y()) <= eps, "Check y");
		assertTrue(Math.abs(c.z() - crossed.z()) <= eps, "Check z");

	}

	@Test
	public void projectOnVector() {

		Vector3 a = new Vector3(1, 0, 0);
		Vector3 b = new Vector3();
		Vector3 normal = new Vector3(10, 0, 0);

		assertTrue(b.copy(a).projectOnVector(normal).equals(new Vector3(1, 0, 0)), "Passed!");

		a.set(0, 1, 0);
		assertTrue(b.copy(a).projectOnVector(normal).equals(new Vector3(0, 0, 0)), "Passed!");

		a.set(0, 0, -1);
		assertTrue(b.copy(a).projectOnVector(normal).equals(new Vector3(0, 0, 0)), "Passed!");

		a.set(-1, 0, 0);
		assertTrue(b.copy(a).projectOnVector(normal).equals(new Vector3(-1, 0, 0)), "Passed!");

	}

	@Test
	public void projectOnPlane() {

		Vector3 a = new Vector3(1, 0, 0);
		Vector3 b = new Vector3();
		Vector3 normal = new Vector3(1, 0, 0);

		assertTrue(b.copy(a).projectOnPlane(normal).equals(new Vector3(0, 0, 0)), "Passed!");

		a.set(0, 1, 0);
		assertTrue(b.copy(a).projectOnPlane(normal).equals(new Vector3(0, 1, 0)), "Passed!");

		a.set(0, 0, -1);
		assertTrue(b.copy(a).projectOnPlane(normal).equals(new Vector3(0, 0, -1)), "Passed!");

		a.set(-1, 0, 0);
		assertTrue(b.copy(a).projectOnPlane(normal).equals(new Vector3(0, 0, 0)), "Passed!");

	}

	@Test
	public void reflect() {

		Vector3 a = new Vector3();
		Vector3 normal = new Vector3(0, 1, 0);
		Vector3 b = new Vector3();

		a.set(0, -1, 0);
		assertTrue(b.copy(a).reflect(normal).equals(new Vector3(0, 1, 0)), "Passed!");

		a.set(1, -1, 0);
		assertTrue(b.copy(a).reflect(normal).equals(new Vector3(1, 1, 0)), "Passed!");

		a.set(1, -1, 0);
		normal.set(0, -1, 0);
		assertTrue(b.copy(a).reflect(normal).equals(new Vector3(1, 1, 0)), "Passed!");

	}

	@Test
	public void angleTo() {

		Vector3 a = new Vector3(0, -0.18851655680720186, 0.9820700116639124);
		Vector3 b = new Vector3(0, 0.18851655680720186, -0.9820700116639124);

		assertEquals(0, a.angleTo(a));
		assertEquals(Math.PI, a.angleTo(b));

		Vector3 x = new Vector3(1, 0, 0);
		Vector3 y = new Vector3(0, 1, 0);
		Vector3 z = new Vector3(0, 0, 1);

		assertEquals(Math.PI / 2, x.angleTo(y));
		assertEquals(Math.PI / 2, x.angleTo(z));
		assertEquals(Math.PI / 2, z.angleTo(x));

		assertTrue(Math.abs(x.angleTo(new Vector3(1, 1, 0)) - (Math.PI / 4)) < 0.0000001);

	}

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

//	@Test
//	public void manhattanDistanceTo() {
//
//		assertTrue(false, "everything's gonna be alright");
//
//	}

	@Test
	public void setFromSpherical() {

		Vector3 a = new Vector3();
		double phi = Math.acos( - 0.5 );
		double theta = Math.sqrt( Math.PI ) * phi;
		Spherical sph = new Spherical( 10, phi, theta );
		Vector3 expected = new Vector3( - 4.677914006701843, - 5, - 7.288149322420796 );

		a.setFromSpherical( sph );
		assertTrue( Math.abs( a.x() - expected.x() ) <= eps, "Check x" );
		assertTrue( Math.abs( a.y() - expected.y() ) <= eps, "Check y" );
		assertTrue( Math.abs( a.z() - expected.z() ) <= eps, "Check z" );

	}

	@Test
	public void setFromCylindrical() {

		Vector3 a = new Vector3();
		Cylindrical cyl = new Cylindrical(10, Math.PI * 0.125, 20);
		Vector3 expected = new Vector3(3.826834323650898, 20, 9.238795325112868);

		a.setFromCylindrical(cyl);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Check z");

	}

	@Test
	public void setFromMatrixPosition() {

		Vector3 a = new Vector3();
		Matrix4 m = new Matrix4().set(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53);

		a.setFromMatrixPosition(m);
		assertEquals(a.x(), 7, "Check x");
		assertEquals(a.y(), 19, "Check y");
		assertEquals(a.z(), 37, "Check z");

	}

	@Test
	public void setFromMatrixScale() {

		Vector3 a = new Vector3();
		Matrix4 m = new Matrix4().set(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53);
		Vector3 expected = new Vector3(25.573423705088842, 31.921779399024736, 35.70714214271425);

		a.setFromMatrixScale(m);
		assertTrue(Math.abs(a.x() - expected.x()) <= eps, "Check x");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Check y");
		assertTrue(Math.abs(a.z() - expected.z()) <= eps, "Check z");

	}

	@Test
	public void setFromMatrixColumn() {

		Vector3 a = new Vector3();
		Matrix4 m = new Matrix4().set(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53);

		a.setFromMatrixColumn(m, 0);
		assertEquals(a.x(), 2, "Index 0: check x");
		assertEquals(a.y(), 11, "Index 0: check y");
		assertEquals(a.z(), 23, "Index 0: check z");

		a.setFromMatrixColumn(m, 2);
		assertEquals(a.x(), 5, "Index 2: check x");
		assertEquals(a.y(), 17, "Index 2: check y");
		assertEquals(a.z(), 31, "Index 2: check z");

	}

	@Test
	public void equals() {

		Vector3 a = new Vector3(x, 0, z);
		Vector3 b = new Vector3(0, -y, 0);

		assertTrue(a.x() != b.x(), "Passed!");
		assertTrue(a.y() != b.y(), "Passed!");
		assertTrue(a.z() != b.z(), "Passed!");

		assertTrue(!a.equals(b), "Passed!");
		assertTrue(!b.equals(a), "Passed!");

		a.copy(b);
		assertTrue(a.x() == b.x(), "Passed!");
		assertTrue(a.y() == b.y(), "Passed!");
		assertTrue(a.z() == b.z(), "Passed!");

		assertTrue(a.equals(b), "Passed!");
		assertTrue(b.equals(a), "Passed!");

	}

	@Test
	public void fromArray() {

		Vector3 a = new Vector3();
		double[] array = new double[] { 1, 2, 3, 4, 5, 6 };

		a.fromArray(array);
		assertEquals(a.x(), 1, "No offset: check x");
		assertEquals(a.y(), 2, "No offset: check y");
		assertEquals(a.z(), 3, "No offset: check z");

		a.fromArray(array, 3);
		assertEquals(a.x(), 4, "With offset: check x");
		assertEquals(a.y(), 5, "With offset: check y");
		assertEquals(a.z(), 6, "With offset: check z");

	}

	@Test
	public void toArray() {

		Vector3 a = new Vector3(x, y, z);

		double[] array = a.toArray();
		assertEquals(x, array[0], "No array, no offset: check x");
		assertEquals(y, array[1], "No array, no offset: check y");
		assertEquals(z, array[2], "No array, no offset: check z");

		array = new double[3];
		a.toArray(array);
		assertEquals(x, array[0], "With array, no offset: check x");
		assertEquals(y, array[1], "With array, no offset: check y");
		assertEquals(z, array[2], "With array, no offset: check z");

		array = new double[4];
		a.toArray(array, 1);
		assertEquals(0, array[0], "With array and offset: check [0]");
		assertEquals(x, array[1], "With array and offset: check x");
		assertEquals(y, array[2], "With array and offset: check y");
		assertEquals(z, array[3], "With array and offset: check z");

	}

	@Test
	public void fromBufferAttribute() {

		Vector3 a = new Vector3();
		BufferAttribute attr = new BufferAttribute(new Float32Array(new double[] { 1, 2, 3, 4, 5, 6 }), 3);

		a.fromBufferAttribute(attr, 0);
		assertEquals(a.x(), 1, "Offset 0: check x");
		assertEquals(a.y(), 2, "Offset 0: check y");
		assertEquals(a.z(), 3, "Offset 0: check z");

		a.fromBufferAttribute(attr, 1);
		assertEquals(a.x(), 4, "Offset 1: check x");
		assertEquals(a.y(), 5, "Offset 1: check y");
		assertEquals(a.z(), 6, "Offset 1: check z");
	}

	@Test
	public void setX_setY_setZ() {
		Vector3 a = new Vector3();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");

		a.setX(x);
		a.setY(y);
		a.setZ(z);

		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");
	}

	@Test
	public void setComponent_getComponent() {

		Vector3 a = new Vector3();
		assertTrue(a.x() == 0, "Passed!");
		assertTrue(a.y() == 0, "Passed!");
		assertTrue(a.z() == 0, "Passed!");

		a.setComponent(0, 1);
		a.setComponent(1, 2);
		a.setComponent(2, 3);
		assertTrue(a.getComponent(0) == 1, "Passed!");
		assertTrue(a.getComponent(1) == 2, "Passed!");
		assertTrue(a.getComponent(2) == 3, "Passed!");

	}

	//@Test
	//public void setComponent/getComponent exceptions() {
	//
	//	Vector3 a = new Vector3();
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

	@Test
	public void min_max_clamp() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(-x, -y, -z);
		Vector3 c = new Vector3();

		c.copy(a).min(b);
		assertTrue(c.x() == -x, "Passed!");
		assertTrue(c.y() == -y, "Passed!");
		assertTrue(c.z() == -z, "Passed!");

		c.copy(a).max(b);
		assertTrue(c.x() == x, "Passed!");
		assertTrue(c.y() == y, "Passed!");
		assertTrue(c.z() == z, "Passed!");

		c.set(-2 * x, 2 * y, -2 * z);
		c.clamp(b, a);
		assertTrue(c.x() == -x, "Passed!");
		assertTrue(c.y() == y, "Passed!");
		assertTrue(c.z() == -z, "Passed!");

	}

	@Test
	public void distanceTo_distanceToSquared() {

		Vector3 a = new Vector3(x, 0, 0);
		Vector3 b = new Vector3(0, -y, 0);
		Vector3 c = new Vector3(0, 0, z);
		Vector3 d = new Vector3();

		assertTrue(a.distanceTo(d) == x, "Passed!");
		assertTrue(a.distanceToSquared(d) == x * x, "Passed!");

		assertTrue(b.distanceTo(d) == y, "Passed!");
		assertTrue(b.distanceToSquared(d) == y * y, "Passed!");

		assertTrue(c.distanceTo(d) == z, "Passed!");
		assertTrue(c.distanceToSquared(d) == z * z, "Passed!");

	}

	@Test
	public void setScalar_addScalar_subScalar() {

		Vector3 a = new Vector3();
		double s = 3;

		a.setScalar(s);
		assertEquals(a.x(), s, "setScalar: check x");
		assertEquals(a.y(), s, "setScalar: check y");
		assertEquals(a.z(), s, "setScalar: check z");

		a.addScalar(s);
		assertEquals(a.x(), 2 * s, "addScalar: check x");
		assertEquals(a.y(), 2 * s, "addScalar: check y");
		assertEquals(a.z(), 2 * s, "addScalar: check z");

		a.subScalar(2 * s);
		assertEquals(a.x(), 0, "subScalar: check x");
		assertEquals(a.y(), 0, "subScalar: check y");
		assertEquals(a.z(), 0, "subScalar: check z");

	}

	@Test
	public void multiply_divide() {

		Vector3 a = new Vector3(x, y, z);
		Vector3 b = new Vector3(2 * x, 2 * y, 2 * z);
		Vector3 c = new Vector3(4 * x, 4 * y, 4 * z);

		a.multiply(b);
		assertEquals(a.x(), x * b.x(), "multiply: check x");
		assertEquals(a.y(), y * b.y(), "multiply: check y");
		assertEquals(a.z(), z * b.z(), "multiply: check z");

		b.divide(c);
		assertTrue(Math.abs(b.x() - 0.5) <= eps, "divide: check x");
		assertTrue(Math.abs(b.y() - 0.5) <= eps, "divide: check y");
		assertTrue(Math.abs(b.z() - 0.5) <= eps, "divide: check z");

		a = new Vector3(x, y, z);
		b = new Vector3(-x, -y, -z);

		a.multiplyScalar(-2);
		assertTrue(a.x() == x * -2, "Passed!");
		assertTrue(a.y() == y * -2, "Passed!");
		assertTrue(a.z() == z * -2, "Passed!");

		b.multiplyScalar(-2);
		assertTrue(b.x() == 2 * x, "Passed!");
		assertTrue(b.y() == 2 * y, "Passed!");
		assertTrue(b.z() == 2 * z, "Passed!");

		a.divideScalar(-2);
		assertTrue(a.x() == x, "Passed!");
		assertTrue(a.y() == y, "Passed!");
		assertTrue(a.z() == z, "Passed!");

		b.divideScalar(-2);
		assertTrue(b.x() == -x, "Passed!");
		assertTrue(b.y() == -y, "Passed!");
		assertTrue(b.z() == -z, "Passed!");

	}

	@Test
	public void project_unproject() {

		Vector3 a = new Vector3(x, y, z);
		PerspectiveCamera camera = new PerspectiveCamera(75, 16. / 9, 0.1, 300.0);
		Vector3 projected = new Vector3(-0.36653213611158914, -0.9774190296309043, 1.0506835611870624);

		a.project(camera);
		assertTrue(Math.abs(a.x() - projected.x()) <= eps, "project: check x");
		assertTrue(Math.abs(a.y() - projected.y()) <= eps, "project: check y");
		assertTrue(Math.abs(a.z() - projected.z()) <= eps, "project: check z");

		a.unproject(camera);
		assertTrue(Math.abs(a.x() - x) <= eps, "unproject: check x");
		assertTrue(Math.abs(a.y() - y) <= eps, "unproject: check y");
		assertTrue(Math.abs(a.z() - z) <= eps, "unproject: check z");

	}

	@Test
	public void length_lengthSq() {

		Vector3 a = new Vector3(x, 0, 0);
		Vector3 b = new Vector3(0, -y, 0);
		Vector3 c = new Vector3(0, 0, z);
		Vector3 d = new Vector3();

		assertTrue(a.length() == x, "Passed!");
		assertTrue(a.lengthSq() == x * x, "Passed!");
		assertTrue(b.length() == y, "Passed!");
		assertTrue(b.lengthSq() == y * y, "Passed!");
		assertTrue(c.length() == z, "Passed!");
		assertTrue(c.lengthSq() == z * z, "Passed!");
		assertTrue(d.length() == 0, "Passed!");
		assertTrue(d.lengthSq() == 0, "Passed!");

		a.set(x, y, z);
		assertTrue(a.length() == Math.sqrt(x * x + y * y + z * z), "Passed!");
		assertTrue(a.lengthSq() == (x * x + y * y + z * z), "Passed!");

	}

	@Test
	public void lerp_clone() {

		Vector3 a = new Vector3(x, 0, z);
		Vector3 b = new Vector3(0, -y, 0);

		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 0.5)), "Passed!");
		assertTrue(a.lerp(a, 0).equals(a.lerp(a, 1)), "Passed!");

		assertTrue(a.clone().lerp(b, 0).equals(a), "Passed!");

		assertTrue(a.clone().lerp(b, 0.5).x() == x * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).y() == -y * 0.5, "Passed!");
		assertTrue(a.clone().lerp(b, 0.5).z() == z * 0.5, "Passed!");

		assertTrue(a.clone().lerp(b, 1).equals(b), "Passed!");

	}

}
