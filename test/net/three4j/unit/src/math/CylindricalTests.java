package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.math.Cylindrical;
import net.three4j.math.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static net.three4j.unit.src.math.ConstantsTests.eps;

public class CylindricalTests {

	@Test
	public void Instancing() {

		Cylindrical a = new Cylindrical();
		double radius = 10.0;
		double theta = Math.PI;
		double y = 5;

		assertEquals(1.0, a.radius(), "Default values: check radius");
		assertEquals(0, a.theta(), "Default values: check theta");
		assertEquals(0, a.y(), "Default values: check y");

		a = new Cylindrical(radius, theta, y);
		assertEquals(radius, a.radius(), "Custom values: check radius");
		assertEquals(theta, a.theta(), "Custom values: check theta");
		assertEquals(y, a.y(), "Custom values: check y");

	}

	// PUBLIC STUFF
	@Test
	public void set() {

		Cylindrical a = new Cylindrical();
		double radius = 10.0;
		double theta = Math.PI;
		double y = 5;

		a.set(radius, theta, y);
		assertEquals(radius, a.radius(), "Check radius");
		assertEquals(theta, a.theta(), "Check theta");
		assertEquals(y, a.y(), "Check y");

	}

	@Test
	public void $clone() {

		double radius = 10.0;
		double theta = Math.PI;
		double y = 5;
		Cylindrical a = new Cylindrical(radius, theta, y);
		Cylindrical b = a.clone();

		assertTrue(a.equals(b), "Check a and b are equal after clone()");

		a.radius(1);
		assertFalse(a.equals(b), "Check a and b are not equal after modification");

	}

	@Test
	public void copy() {

		double radius = 10.0;
		double theta = Math.PI;
		double y = 5;
		Cylindrical a = new Cylindrical(radius, theta, y);
		Cylindrical b = new Cylindrical().copy(a);

		assertTrue(a.equals(b), "Check a and b are equal after copy()");

		a.radius(1);
		assertFalse(a.equals(b), "Check a and b are not equal after modification");

	}

	@Test
	public void setFromVector3() {

		Cylindrical a = new Cylindrical(1, 1, 1);
		Vector3 b = new Vector3(0, 0, 0);
		Vector3 c = new Vector3(3, -1, -3);
		Cylindrical expected = new Cylindrical(Math.sqrt(9 + 9), Math.atan2(3, -3), -1);

		a.setFromVector3(b);
		assertEquals(0, a.radius(), "Zero-length vector: check radius");
		assertEquals(0, a.theta(), "Zero-length vector: check theta");
		assertEquals(0, a.y(), "Zero-length vector: check y");

		a.setFromVector3(c);
		assertTrue(Math.abs(a.radius() - expected.radius()) <= eps, "Normal vector: check radius");
		assertTrue(Math.abs(a.theta() - expected.theta()) <= eps, "Normal vector: check theta");
		assertTrue(Math.abs(a.y() - expected.y()) <= eps, "Normal vector: check y");

	}

}
