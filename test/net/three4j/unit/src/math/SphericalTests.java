package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.math.Spherical;
import net.three4j.math.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static net.three4j.unit.src.math.ConstantsTests.eps;

public class SphericalTests {
	@Test
	public void Instancing() {

		Spherical a = new Spherical();
		double radius = 10.0;
		double phi = Math.acos(-0.5);
		double theta = Math.sqrt(Math.PI) * phi;

		assertEquals(1.0, a.radius(), "Default values: check radius");
		assertEquals(0, a.phi(), "Default values: check phi");
		assertEquals(0, a.theta(), "Default values: check theta");

		a = new Spherical(radius, phi, theta);
		assertEquals(radius, a.radius(), "Custom values: check radius");
		assertEquals(phi, a.phi(), "Custom values: check phi");
		assertEquals(theta, a.theta(), "Custom values: check theta");

	}

	@Test
	public void set() {

		Spherical a = new Spherical();
		double radius = 10.0;
		double phi = Math.acos(-0.5);
		double theta = Math.sqrt(Math.PI) * phi;

		a.set(radius, phi, theta);
		assertEquals(radius, a.radius(), "Check radius");
		assertEquals(phi, a.phi(), "Check phi");
		assertEquals(theta, a.theta(), "Check theta");

	}

	@Test
	public void $clone() {

			double radius = 10.0;
			double phi = Math.acos( - 0.5 );
			double theta = Math.sqrt( Math.PI ) * phi;
			Spherical a = new Spherical( radius, phi, theta );
			Spherical b = a.clone();

			assertTrue( a.equals(b), "Check a and b are equal after clone()" );

			a.radius(2.0);
			assertFalse( a.equals(b), "Check a and b are not equal after modification" );

		}

	@Test
	public void copy() {

			double radius = 10.0;
			double phi = Math.acos( - 0.5 );
			double theta = Math.sqrt( Math.PI ) * phi;
			Spherical a = new Spherical( radius, phi, theta );
			Spherical b = new Spherical().copy( a );

			assertTrue( a.equals(b), "Check a and b are equal after copy()" );

			a.radius(2.0);
			assertFalse( a.equals(b), "Check a and b are not equal after modification" );

		}

	@Test
	public void makeSafe() {

		double EPS = 0.000001; // from source
		double tooLow = 0.0;
		double tooHigh = Math.PI;
		double justRight = 1.5;
		Spherical a = new Spherical(1, tooLow, 0);

		a.makeSafe();
		assertEquals(EPS, a.phi(), "Check if small values are set to EPS");

		a.set(1, tooHigh, 0);
		a.makeSafe();
		assertEquals(Math.PI - EPS, a.phi(), "Check if high values are set to (Math.PI - EPS)");

		a.set(1, justRight, 0);
		a.makeSafe();
		assertEquals(justRight, a.phi(), "Check that valid values don't get changed");

	}

	@Test
	public void setFromVector3() {

		Spherical a = new Spherical(1, 1, 1);
		Vector3 b = new Vector3(0, 0, 0);
		Vector3 c = new Vector3(Math.PI, 1, -Math.PI);
		Spherical expected = new Spherical(4.554032147688322, 1.3494066171539107, 2.356194490192345);

		a.setFromVector3(b);
		assertEquals(0, a.radius(), "Zero-length vector: check radius");
		assertEquals(0, a.phi(), "Zero-length vector: check phi");
		assertEquals(0, a.theta(), "Zero-length vector: check theta");

		a.setFromVector3(c);
		assertTrue(Math.abs(a.radius() - expected.radius()) <= eps, "Normal vector: check radius");
		assertTrue(Math.abs(a.phi() - expected.phi()) <= eps, "Normal vector: check phi");
		assertTrue(Math.abs(a.theta() - expected.theta()) <= eps, "Normal vector: check theta");

	}

}

