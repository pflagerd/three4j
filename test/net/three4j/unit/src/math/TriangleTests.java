package net.three4j.unit.src.math;

import static net.three4j.unit.src.math.ConstantsTests.one3;
import static net.three4j.unit.src.math.ConstantsTests.two3;
import static net.three4j.unit.src.math.ConstantsTests.zero3;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.three4j.math.Box3;
import net.three4j.math.Plane;
import net.three4j.math.Triangle;
import net.three4j.math.Vector3;

public class TriangleTests {

	@Test
	public void Instancing() {

		Triangle a = new Triangle();
		assertTrue(a.a().equals(zero3), "Passed!");
		assertTrue(a.b().equals(zero3), "Passed!");
		assertTrue(a.c().equals(zero3), "Passed!");

		a = new Triangle(one3.clone().negate(), one3.clone(), two3.clone());
		assertTrue(a.a().equals(one3.clone().negate()), "Passed!");
		assertTrue(a.b().equals(one3), "Passed!");
		assertTrue(a.c().equals(two3), "Passed!");

	}

//		@Test
//		public void getNormal() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void getBarycoord() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void containsPoint() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

	@Test
	public void set() {

		Triangle a = new Triangle();

		a.set(one3.clone().negate(), one3, two3);
		assertTrue(a.a().equals(one3.clone().negate()), "Passed!");
		assertTrue(a.b().equals(one3), "Passed!");
		assertTrue(a.c().equals(two3), "Passed!");

	}

	@Test
	public void setFromPointsAndIndices() {

		Triangle a = new Triangle();

		Vector3[] points = { one3, one3.clone().negate(), two3 };
		a.setFromPointsAndIndices(points, 1, 0, 2);
		assertTrue(a.a().equals(one3.clone().negate()), "Passed!");
		assertTrue(a.b().equals(one3), "Passed!");
		assertTrue(a.c().equals(two3), "Passed!");

	}

//		@Test
//		public void clone() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

	@Test
	public void copy() {

		Triangle a = new Triangle(one3.clone().negate(), one3.clone(), two3.clone());
		Triangle b = new Triangle().copy(a);
		assertTrue(b.a().equals(one3.clone().negate()), "Passed!");
		assertTrue(b.b().equals(one3), "Passed!");
		assertTrue(b.c().equals(two3), "Passed!");

		// ensure that it is a true copy
		a.a(one3);
		a.b(zero3);
		a.c(zero3);
		assertTrue(b.a().equals(one3.clone().negate()), "Passed!");
		assertTrue(b.b().equals(one3), "Passed!");
		assertTrue(b.c().equals(two3), "Passed!");

	}

	@Test
	public void getArea() {

		Triangle a = new Triangle();

		assertTrue(a.getArea() == 0, "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		assertTrue(a.getArea() == 0.5, "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		assertTrue(a.getArea() == 2, "Passed!");

		// colinear triangle.
		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(3, 0, 0));
		assertTrue(a.getArea() == 0, "Passed!");

	}

	@Test
	public void getMidpoint() {

		Triangle a = new Triangle();
		Vector3 midpoint = new Vector3();

		assertTrue(a.getMidpoint(midpoint).equals(new Vector3(0, 0, 0)), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		assertTrue(a.getMidpoint(midpoint).equals(new Vector3(1 / 3, 1 / 3, 0)), "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		assertTrue(a.getMidpoint(midpoint).equals(new Vector3(2 / 3, 0, 2 / 3)), "Passed!");

	}

	@Test
	public void getNormal() {

		Triangle a = new Triangle();
		Vector3 normal = new Vector3();

		assertTrue(a.getNormal(normal).equals(new Vector3(0, 0, 0)), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		assertTrue(a.getNormal(normal).equals(new Vector3(0, 0, 1)), "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		assertTrue(a.getNormal(normal).equals(new Vector3(0, 1, 0)), "Passed!");

	}

	@Test
	public void getPlane() {

		Triangle a = new Triangle();
		Plane plane = new Plane();
		Vector3 normal = new Vector3();

		a.getPlane(plane);
		assertFalse(Double.isNaN(plane.distanceToPoint(a.a())), "Passed!");
		assertFalse(Double.isNaN(plane.distanceToPoint(a.b())), "Passed!");
		assertFalse(Double.isNaN(plane.distanceToPoint(a.c())), "Passed!");
		assertFalse(plane.normal().equals(new Vector3(Double.NaN, Double.NaN, Double.NaN)), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		a.getPlane(plane);
		a.getNormal(normal);
		assertTrue(plane.distanceToPoint(a.a()) == 0, "Passed!");
		assertTrue(plane.distanceToPoint(a.b()) == 0, "Passed!");
		assertTrue(plane.distanceToPoint(a.c()) == 0, "Passed!");
		assertTrue(plane.normal().equals(normal), "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		a.getPlane(plane);
		a.getNormal(normal);
		assertTrue(plane.distanceToPoint(a.a()) == 0, "Passed!");
		assertTrue(plane.distanceToPoint(a.b()) == 0, "Passed!");
		assertTrue(plane.distanceToPoint(a.c()) == 0, "Passed!");
		assertTrue(plane.normal().clone().normalize().equals(normal), "Passed!");

	}

	@Test
	public void getBarycoord() {

		Triangle a = new Triangle();

		Vector3 bad = new Vector3(-2, -1, -1);
		Vector3 barycoord = new Vector3();
		Vector3 midpoint = new Vector3();

		a.getBarycoord(a.a(), barycoord);
		assertTrue(barycoord.equals(bad), "Passed!");
		a.getBarycoord(a.b(), barycoord);
		assertTrue(barycoord.equals(bad), "Passed!");
		a.getBarycoord(a.c(), barycoord);
		assertTrue(barycoord.equals(bad), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		a.getMidpoint(midpoint);

		a.getBarycoord(a.a(), barycoord);
		assertTrue(barycoord.equals(new Vector3(1, 0, 0)), "Passed!");
		a.getBarycoord(a.b(), barycoord);
		assertTrue(barycoord.equals(new Vector3(0, 1, 0)), "Passed!");
		a.getBarycoord(a.c(), barycoord);
		assertTrue(barycoord.equals(new Vector3(0, 0, 1)), "Passed!");
		a.getBarycoord(midpoint, barycoord);
		assertTrue(barycoord.distanceTo(new Vector3(1. / 3, 1. / 3, 1. / 3)) < 0.0001, "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		a.getMidpoint(midpoint);

		a.getBarycoord(a.a(), barycoord);
		assertTrue(barycoord.equals(new Vector3(1, 0, 0)), "Passed!");
		a.getBarycoord(a.b(), barycoord);
		assertTrue(barycoord.equals(new Vector3(0, 1, 0)), "Passed!");
		a.getBarycoord(a.c(), barycoord);
		assertTrue(barycoord.equals(new Vector3(0, 0, 1)), "Passed!");
		a.getBarycoord(midpoint, barycoord);
		assertTrue(barycoord.distanceTo(new Vector3(1. / 3, 1. / 3, 1. / 3)) < 0.0001, "Passed!");

	}

	@Test
	public void containsPoint() {

		Triangle a = new Triangle();
		Vector3 midpoint = new Vector3();

		assertTrue(!a.containsPoint(a.a()), "Passed!");
		assertTrue(!a.containsPoint(a.b()), "Passed!");
		assertTrue(!a.containsPoint(a.c()), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		a.getMidpoint(midpoint);
		assertTrue(a.containsPoint(a.a()), "Passed!");
		assertTrue(a.containsPoint(a.b()), "Passed!");
		assertTrue(a.containsPoint(a.c()), "Passed!");
		assertTrue(a.containsPoint(midpoint), "Passed!");
		assertTrue(!a.containsPoint(new Vector3(-1, -1, -1)), "Passed!");

		a = new Triangle(new Vector3(2, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 2));
		a.getMidpoint(midpoint);
		assertTrue(a.containsPoint(a.a()), "Passed!");
		assertTrue(a.containsPoint(a.b()), "Passed!");
		assertTrue(a.containsPoint(a.c()), "Passed!");
		assertTrue(a.containsPoint(midpoint), "Passed!");
		assertTrue(!a.containsPoint(new Vector3(-1, -1, -1)), "Passed!");

	}

	@Test
	public void intersectsBox() {

		Box3 a = new Box3(one3.clone(), two3.clone());
		Triangle b = new Triangle(new Vector3(1.5, 1.5, 2.5), new Vector3(2.5, 1.5, 1.5), new Vector3(1.5, 2.5, 1.5));
		Triangle c = new Triangle(new Vector3(1.5, 1.5, 3.5), new Vector3(3.5, 1.5, 1.5), new Vector3(1.5, 1.5, 1.5));
		Triangle d = new Triangle(new Vector3(1.5, 1.75, 3), new Vector3(3, 1.75, 1.5), new Vector3(1.5, 2.5, 1.5));
		Triangle e = new Triangle(new Vector3(1.5, 1.8, 3), new Vector3(3, 1.8, 1.5), new Vector3(1.5, 2.5, 1.5));
		Triangle f = new Triangle(new Vector3(1.5, 2.5, 3), new Vector3(3, 2.5, 1.5), new Vector3(1.5, 2.5, 1.5));

		assertTrue(b.intersectsBox(a), "Passed!");
		assertTrue(c.intersectsBox(a), "Passed!");
		assertTrue(d.intersectsBox(a), "Passed!");
		assertTrue(!e.intersectsBox(a), "Passed!");
		assertTrue(!f.intersectsBox(a), "Passed!");

	}

	@Test
	public void closestPointToPoint() {

		Triangle a = new Triangle(new Vector3(-1, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		Vector3 point = new Vector3();

		// point lies inside the triangle
		a.closestPointToPoint(new Vector3(0, 0.5, 0), point);
		assertTrue(point.equals(new Vector3(0, 0.5, 0)), "Passed!");

		// point lies on a vertex
		a.closestPointToPoint(a.a(), point);
		assertTrue(point.equals(a.a()), "Passed!");

		a.closestPointToPoint(a.b(), point);
		assertTrue(point.equals(a.b()), "Passed!");

		a.closestPointToPoint(a.c(), point);
		assertTrue(point.equals(a.c()), "Passed!");

		// point lies on an edge
		a.closestPointToPoint(zero3.clone(), point);
		assertTrue(point.equals(zero3.clone()), "Passed!");

		// point lies outside the triangle
		a.closestPointToPoint(new Vector3(-2, 0, 0), point);
		assertTrue(point.equals(new Vector3(-1, 0, 0)), "Passed!");

		a.closestPointToPoint(new Vector3(2, 0, 0), point);
		assertTrue(point.equals(new Vector3(1, 0, 0)), "Passed!");

		a.closestPointToPoint(new Vector3(0, 2, 0), point);
		assertTrue(point.equals(new Vector3(0, 1, 0)), "Passed!");

		a.closestPointToPoint(new Vector3(0, -2, 0), point);
		assertTrue(point.equals(new Vector3(0, 0, 0)), "Passed!");

	}

	@Test
	public void isFrontFacing() {

		Triangle a = new Triangle();
		Vector3 dir = new Vector3();
		assertTrue(!a.isFrontFacing(dir), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		dir = new Vector3(0, 0, -1);
		assertTrue(a.isFrontFacing(dir), "Passed!");

		a = new Triangle(new Vector3(0, 0, 0), new Vector3(0, 1, 0), new Vector3(1, 0, 0));
		assertTrue(!a.isFrontFacing(dir), "Passed!");

	}

	@Test
	public void equals() {

		Triangle a = new Triangle(new Vector3(1, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 0, 1));
		Triangle b = new Triangle(new Vector3(0, 0, 1), new Vector3(0, 1, 0), new Vector3(1, 0, 0));
		Triangle c = new Triangle(new Vector3(-1, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 0, 1));

		assertTrue(a.equals(a), "a equals a");
		assertFalse(a.equals(b), "a does not equal b");
		assertFalse(a.equals(c), "a does not equal c");
		assertFalse(b.equals(c), "b does not equal c");

		a.copy(b);
		assertTrue(a.equals(a), "a equals b after copy()");

	}

}
