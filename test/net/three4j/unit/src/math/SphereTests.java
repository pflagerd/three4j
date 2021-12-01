package net.three4j.unit.src.math;

import static net.three4j.unit.src.math.ConstantsTests.eps;
import static net.three4j.unit.src.math.ConstantsTests.one3;
import static net.three4j.unit.src.math.ConstantsTests.two3;
import static net.three4j.unit.src.math.ConstantsTests.zero3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.three4j.math.Box3;
import net.three4j.math.Matrix4;
import net.three4j.math.Plane;
import net.three4j.math.Sphere;
import net.three4j.math.Vector3;

public class SphereTests {

	@Test
	public void Instancing() {
		Sphere a = new Sphere();
		assertTrue(a.center().equals(zero3), "Passed!");
		assertTrue(a.radius() == -1, "Passed!");

		a = new Sphere(one3.clone(), 1);
		assertTrue(a.center().equals(one3), "Passed!");
		assertTrue(a.radius() == 1, "Passed!");
	}

	@Test
	public void set() {

		Sphere a = new Sphere();
		assertTrue(a.center().equals(zero3), "Passed!");
		assertTrue(a.radius() == -1, "Passed!");

		a.set(one3, 1);
		assertTrue(a.center().equals(one3), "Passed!");
		assertTrue(a.radius() == 1, "Passed!");

	}

	@Test
	public void setFromPoints() {

		Sphere a = new Sphere();
		Vector3 expectedCenter = new Vector3(0.9330126941204071, 0, 0);
		double expectedRadius = 1.3676668773461689;
		Vector3 optionalCenter = new Vector3(1, 1, 1);
		Vector3[] points = { new Vector3(1, 1, 0), new Vector3(1, 1, 0), new Vector3(1, 1, 0), new Vector3(1, 1, 0), new Vector3(1, 1, 0), new Vector3(0.8660253882408142, 0.5, 0), new Vector3(-0, 0.5, 0.8660253882408142), new Vector3(1.8660253882408142, 0.5, 0), new Vector3(0, 0.5, -0.8660253882408142), new Vector3(0.8660253882408142, 0.5, -0), new Vector3(0.8660253882408142, -0.5, 0), new Vector3(-0, -0.5, 0.8660253882408142), new Vector3(1.8660253882408142, -0.5, 0), new Vector3(0, -0.5, -0.8660253882408142), new Vector3(0.8660253882408142, -0.5, -0), new Vector3(-0, -1, 0), new Vector3(-0, -1, 0), new Vector3(0, -1, 0), new Vector3(0, -1, -0), new Vector3(-0, -1, -0), };

		a.setFromPoints(points);
		assertTrue(Math.abs(a.center().x() - expectedCenter.x()) <= eps, "Default center: check center.x()");
		assertTrue(Math.abs(a.center().y() - expectedCenter.y()) <= eps, "Default center: check center.y()");
		assertTrue(Math.abs(a.center().z() - expectedCenter.z()) <= eps, "Default center: check center.z()");
		assertTrue(Math.abs(a.radius() - expectedRadius) <= eps, "Default center: check radius");

		double expectedRadius2 = 2.5946195770400102;
		a.setFromPoints(points, optionalCenter);
		assertTrue(Math.abs(a.center().x() - optionalCenter.x()) <= eps, "Optional center: check center.x()");
		assertTrue(Math.abs(a.center().y() - optionalCenter.y()) <= eps, "Optional center: check center.y()");
		assertTrue(Math.abs(a.center().z() - optionalCenter.z()) <= eps, "Optional center: check center.z()");
		assertTrue(Math.abs(a.radius() - expectedRadius2) <= eps, "Optional center: check radius");

	}

//		@Test
//		public void clone() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

	@Test
	public void copy() {

		Sphere a = new Sphere(one3.clone(), 1);
		Sphere b = new Sphere().copy(a);

		assertTrue(b.center().equals(one3), "Passed!");
		assertTrue(b.radius() == 1, "Passed!");

		// ensure that it is a true copy
		a.center(zero3);
		a.radius(0);
		assertTrue(b.center().equals(one3), "Passed!");
		assertTrue(b.radius() == 1, "Passed!");

	}

	@Test
	public void isEmpty() {

		Sphere a = new Sphere();
		assertTrue(a.isEmpty(), "Passed!");

		a.set(one3, 1);
		assertTrue(!a.isEmpty(), "Passed!");

		// Negative radius contains no points
		a.set(one3, -1);
		assertTrue(a.isEmpty(), "Passed!");

		// Zero radius contains only the center point
		a.set(one3, 0);
		assertTrue(!a.isEmpty(), "Passed!");

	}

	@Test
	public void makeEmpty() {

		Sphere a = new Sphere(one3.clone(), 1);

		assertTrue(!a.isEmpty(), "Passed!");

		a.makeEmpty();
		assertTrue(a.isEmpty(), "Passed!");
		assertTrue(a.center().equals(zero3), "Passed!");

	}

	@Test
	public void containsPoint() {

		Sphere a = new Sphere(one3.clone(), 1);

		assertTrue(!a.containsPoint(zero3), "Passed!");
		assertTrue(a.containsPoint(one3), "Passed!");

		a.set(zero3, 0);
		assertTrue(a.containsPoint(a.center()), "Passed!");

	}

	@Test
	public void distanceToPoint() {

		Sphere a = new Sphere(one3.clone(), 1);

		assertTrue((a.distanceToPoint(zero3) - 0.7320) < 0.001, "Passed!");
		assertTrue(a.distanceToPoint(one3) == -1, "Passed!");

	}

	@Test
	public void intersectsSphere() {

		Sphere a = new Sphere(one3.clone(), 1);
		Sphere b = new Sphere(zero3.clone(), 1);
		Sphere c = new Sphere(zero3.clone(), 0.25);

		assertTrue(a.intersectsSphere(b), "Passed!");
		assertTrue(!a.intersectsSphere(c), "Passed!");

	}

	@Test
	public void intersectsBox() {

		Sphere a = new Sphere(zero3, 1);
		Sphere b = new Sphere(new Vector3(-5, -5, -5), 1);
		Box3 box = new Box3(zero3, one3);

		assertEquals(true, a.intersectsBox(box), "Check unit sphere");
		assertEquals(false, b.intersectsBox(box), "Check shifted sphere");

	}

	@Test
	public void intersectsPlane() {

		Sphere a = new Sphere(zero3.clone(), 1);
		Plane b = new Plane(new Vector3(0, 1, 0), 1);
		Plane c = new Plane(new Vector3(0, 1, 0), 1.25);
		Plane d = new Plane(new Vector3(0, -1, 0), 1.25);

		assertTrue(a.intersectsPlane(b), "Passed!");
		assertTrue(!a.intersectsPlane(c), "Passed!");
		assertTrue(!a.intersectsPlane(d), "Passed!");

	}

		@Test
		public void clampPoint() {

			Sphere a = new Sphere( one3.clone(), 1 );
			Vector3 point = new Vector3();

			a.clampPoint( new Vector3( 1, 1, 3 ), point );
			assertTrue( point.equals( new Vector3( 1, 1, 2 ) ), "Passed!" );
			a.clampPoint( new Vector3( 1, 1, - 3 ), point );
			assertTrue( point.equals( new Vector3( 1, 1, 0 ) ), "Passed!" );

		}

		@Test
		public void getBoundingBox() {

			Sphere a = new Sphere( one3.clone(), 1 );
			Box3 aabb = new Box3();

			a.getBoundingBox( aabb );
			assertTrue( aabb.equals( new Box3( zero3, two3 ) ), "Passed!" );

			a.set( zero3, 0 );
			a.getBoundingBox( aabb );
			assertTrue( aabb.equals( new Box3( zero3, zero3 ) ), "Passed!" );

			// Empty sphere produces empty bounding box
			a.makeEmpty();
			a.getBoundingBox( aabb );
			assertTrue( aabb.isEmpty(), "Passed!" );

		}

		@Test
		public void applyMatrix4() {

			Sphere a = new Sphere( one3.clone(), 1 );
			Matrix4 m = new Matrix4().makeTranslation( 1, -2, 1 );
			Box3 aabb1 = new Box3();
			Box3 aabb2 = new Box3();

			a.clone().applyMatrix4( m ).getBoundingBox( aabb1 );
			a.getBoundingBox( aabb2 );

			assertTrue( aabb1.equals( aabb2.applyMatrix4( m ) ), "Passed!" );

		}

		@Test
		public void translate() {

			Sphere a = new Sphere( one3.clone(), 1 );

			a.translate( one3.clone().negate() );
			assertTrue( a.center().equals( zero3 ), "Passed!" );

		}

		@Test
		public void equals() {

			Sphere a = new Sphere();
			Sphere b = new Sphere( new Vector3( 1, 0, 0 ) );
			Sphere c = new Sphere( new Vector3( 1, 0, 0 ), 1.0 );

			assertEquals(false, a.equals( b ),  "a does not equal b" );
			assertEquals(false, a.equals( c ),  "a does not equal c" );
			assertEquals(false, b.equals( c ),  "b does not equal c" );

			a.copy( b );
			assertEquals(true, a.equals( b ),  "a equals b after copy()" );

		}

}
