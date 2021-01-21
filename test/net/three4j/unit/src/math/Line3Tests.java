package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.math.Line3;
import net.three4j.math.Matrix4;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static net.three4j.unit.src.math.ConstantsTests.zero3;
import static net.three4j.unit.src.math.ConstantsTests.one3;
import static net.three4j.unit.src.math.ConstantsTests.two3;
import static net.three4j.unit.src.math.ConstantsTests.eps;

public class Line3Tests {

	@Test
	public void Instancing() {

		Line3 a = new Line3();
		assertTrue(a.start().equals(zero3), "Passed!");
		assertTrue(a.end().equals(zero3), "Passed!");

		a = new Line3(two3.clone(), one3.clone());
		assertTrue(a.start().equals(two3), "Passed!");
		assertTrue(a.end().equals(one3), "Passed!");

	}

	@Test
	public void set() {

		Line3 a = new Line3();

		a.set(one3, one3);
		assertTrue(a.start().equals(one3), "Passed!");
		assertTrue(a.end().equals(one3), "Passed!");

	}

	@Test
	public void copy_equals() {

		Line3 a = new Line3(zero3.clone(), one3.clone());
		Line3 b = new Line3().copy(a);
		assertTrue(b.start().equals(zero3), "Passed!");
		assertTrue(b.end().equals(one3), "Passed!");

		// ensure that it is a true copy
		a.start(zero3);
		a.end(one3);
		assertTrue(b.start().equals(zero3), "Passed!");
		assertTrue(b.end().equals(one3), "Passed!");

	}

	@Test
	public void clone_equal() {

		Line3 a = new Line3();
		Line3 b = new Line3(zero3, new Vector3(1, 1, 1));
		Line3 c = new Line3(zero3, new Vector3(1, 1, 0));

		assertFalse(a.equals(b), "Check a and b aren't equal");
		assertFalse(a.equals(c), "Check a and c aren't equal");
		assertFalse(b.equals(c), "Check b and c aren't equal");

		a = b.clone();
		assertTrue(a.equals(b), "Check a and b are equal after clone()");
		assertFalse(a.equals(c), "Check a and c aren't equal after clone()");

		a.set(zero3, zero3);
		assertFalse(a.equals(b), "Check a and b are not equal after modification");

	}

	@Test
	public void getCenter() {

		Vector3 center = new Vector3();

		Line3 a = new Line3(zero3.clone(), two3.clone());
		assertTrue(a.getCenter(center).equals(one3.clone()), "Passed");

	}

	@Test
	public void delta() {

		Vector3 delta = new Vector3();

		Line3 a = new Line3(zero3.clone(), two3.clone());
		assertTrue(a.delta(delta).equals(two3.clone()), "Passed");

	}

	@Test
	public void distanceSq() {

		Line3 a = new Line3(zero3, zero3);
		Line3 b = new Line3(zero3, one3);
		Line3 c = new Line3(one3.clone().negate(), one3);
		Line3 d = new Line3(two3.clone().multiplyScalar(-2), two3.clone().negate());

		assertEquals(0, a.distanceSq(), eps, "Check squared distance for zero-length line");
		assertEquals(3, b.distanceSq(), eps, "Check squared distance for simple line");
		assertEquals(12, c.distanceSq(), eps, "Check squared distance for negative to positive endpoints");
		assertEquals(12, d.distanceSq(), eps, "Check squared distance for negative to negative endpoints");

	}

	@Test
	public void distance() {

		Line3 a = new Line3(zero3, zero3);
		Line3 b = new Line3(zero3, one3);
		Line3 c = new Line3(one3.clone().negate(), one3);
		Line3 d = new Line3(two3.clone().multiplyScalar(-2), two3.clone().negate());

		assertEquals(0, a.distance(), eps, "Check distance for zero-length line");
		assertEquals(Math.sqrt(3), b.distance(), eps, "Check distance for simple line");
		assertEquals(Math.sqrt(12), c.distance(), eps, "Check distance for negative to positive endpoints");
		assertEquals(Math.sqrt(12), d.distance(), eps, "Check distance for negative to negative endpoints");

	}

	@Test
	public void at() {

		Line3 a = new Line3(one3.clone(), new Vector3(1, 1, 2));
		Vector3 point = new Vector3();

		a.at(-1, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 0)) < 0.0001, "Passed!");
		a.at(0, point);
		assertTrue(point.distanceTo(one3.clone()) < 0.0001, "Passed!");
		a.at(1, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 2)) < 0.0001, "Passed!");
		a.at(2, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 3)) < 0.0001, "Passed!");

	}

	@Test
	public void closestPointToPoint_closestPointToPointParameter() {

		Line3 a = new Line3(one3.clone(), new Vector3(1, 1, 2));
		Vector3 point = new Vector3();

		// nearby the ray
		assertTrue(a.closestPointToPointParameter(zero3.clone(), true) == 0, "Passed!");
		a.closestPointToPoint(zero3.clone(), true, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 1)) < 0.0001, "Passed!");

		// nearby the ray
		assertTrue(a.closestPointToPointParameter(zero3.clone(), false) == -1, "Passed!");
		a.closestPointToPoint(zero3.clone(), false, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 0)) < 0.0001, "Passed!");

		// nearby the ray
		assertTrue(a.closestPointToPointParameter(new Vector3(1, 1, 5), true) == 1, "Passed!");
		a.closestPointToPoint(new Vector3(1, 1, 5), true, point);
		assertTrue(point.distanceTo(new Vector3(1, 1, 2)) < 0.0001, "Passed!");

		// exactly on the ray
		assertTrue(a.closestPointToPointParameter(one3.clone(), true) == 0, "Passed!");
		a.closestPointToPoint(one3.clone(), true, point);
		assertTrue(point.distanceTo(one3.clone()) < 0.0001, "Passed!");

	}

	@Test
	public void applyMatrix4() {

		Line3 a = new Line3(zero3.clone(), two3.clone());
		Vector4 b = new Vector4(two3.x(), two3.y(), two3.z(), 1);
		Matrix4 m = new Matrix4().makeTranslation(x, y, z);
		Vector3 v = new Vector3(x, y, z);

		a.applyMatrix4(m);
		assertTrue(a.start().equals(v), "Translation: check start");
		assertTrue(a.end().equals(new Vector3(2 + x, 2 + y, 2 + z)), "Translation: check start");

		// reset starting conditions
		a.set(zero3.clone(), two3.clone());
		m.makeRotationX(Math.PI);

		a.applyMatrix4(m);
		b.applyMatrix4(m);

		assertTrue(a.start().equals(zero3), "Rotation: check start");
		assertEquals(b.x() / b.w(), a.end().x(), eps, "Rotation: check end.x");
		assertEquals(b.y() / b.w(), a.end().y(), eps, "Rotation: check end.y");
		assertEquals(b.z() / b.w(), a.end().z(), eps, "Rotation: check end.z");

		// reset starting conditions
		a.set(zero3.clone(), two3.clone());
		b.set(two3.x(), two3.y(), two3.z(), 1);
		m.setPosition(v);

		a.applyMatrix4(m);
		b.applyMatrix4(m);

		assertTrue(a.start().equals(v), "Both: check start");
		assertEquals(b.x() / b.w(), a.end().x(), eps, "Both: check end.x");
		assertEquals(b.y() / b.w(), a.end().y(), eps, "Both: check end.y");
		assertEquals(b.z() / b.w(), a.end().z(), eps, "Both: check end.z");

	}

	@Test
	public void equals() {

		Line3 a = new Line3(zero3.clone(), zero3.clone());
		Line3 b = new Line3();
		assertTrue(a.equals(b), "Passed");

	}

}
