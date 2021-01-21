package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.math.Box3;
import net.three4j.math.Line3;
import net.three4j.math.Matrix4;
import net.three4j.math.Plane;
import net.three4j.math.Sphere;
import net.three4j.math.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static net.three4j.unit.src.math.ConstantsTests.w;
import static net.three4j.unit.src.math.ConstantsTests.zero3;
import static net.three4j.unit.src.math.ConstantsTests.one3;
import static net.three4j.unit.src.math.ConstantsTests.two3;
import static net.three4j.unit.src.math.ConstantsTests.eps;

public class PlaneTests {

	boolean comparePlane(Plane a, Plane b) {
		return comparePlane(a, b, 0.0001);
	}

	boolean comparePlane(Plane a, Plane b, double threshold) {

		return (a.normal().distanceTo(b.normal()) < threshold && Math.abs(a.constant() - b.constant()) < threshold);

	}

	@Test
	public void Instancing() {

		Plane a = new Plane();
		assertTrue(a.normal().x() == 1, "Passed!");
		assertTrue(a.normal().y() == 0, "Passed!");
		assertTrue(a.normal().z() == 0, "Passed!");
		assertTrue(a.constant() == 0, "Passed!");

		a = new Plane(one3.clone(), 0);
		assertTrue(a.normal().x() == 1, "Passed!");
		assertTrue(a.normal().y() == 1, "Passed!");
		assertTrue(a.normal().z() == 1, "Passed!");
		assertTrue(a.constant() == 0, "Passed!");

		a = new Plane(one3.clone(), 1);
		assertTrue(a.normal().x() == 1, "Passed!");
		assertTrue(a.normal().y() == 1, "Passed!");
		assertTrue(a.normal().z() == 1, "Passed!");
		assertTrue(a.constant() == 1, "Passed!");

	}

	public void set() {

		Plane a = new Plane();
		assertTrue(a.normal().x() == 1, "Passed!");
		assertTrue(a.normal().y() == 0, "Passed!");
		assertTrue(a.normal().z() == 0, "Passed!");
		assertTrue(a.constant() == 0, "Passed!");

		Plane b = a.clone().set(new Vector3(x, y, z), w);
		assertTrue(b.normal().x() == x, "Passed!");
		assertTrue(b.normal().y() == y, "Passed!");
		assertTrue(b.normal().z() == z, "Passed!");
		assertTrue(b.constant() == w, "Passed!");

	}

	@Test
	public void setComponents() {

		Plane a = new Plane();
		assertTrue(a.normal().x() == 1, "Passed!");
		assertTrue(a.normal().y() == 0, "Passed!");
		assertTrue(a.normal().z() == 0, "Passed!");
		assertTrue(a.constant() == 0, "Passed!");

		Plane b = a.clone().setComponents(x, y, z, w);
		assertTrue(b.normal().x() == x, "Passed!");
		assertTrue(b.normal().y() == y, "Passed!");
		assertTrue(b.normal().z() == z, "Passed!");
		assertTrue(b.constant() == w, "Passed!");

	}

	@Test
	public void setFromNormalAndCoplanarPoint() {

		Vector3 normal = one3.clone().normalize();
		Plane a = new Plane().setFromNormalAndCoplanarPoint(normal, zero3);

		assertTrue(a.normal().equals(normal), "Passed!");
		assertTrue(a.constant() == 0, "Passed!");

	}

	@Test
	public void setFromCoplanarPoints() {

		Plane a = new Plane();
		Vector3 v1 = new Vector3(2.0, 0.5, 0.25);
		Vector3 v2 = new Vector3(2.0, -0.5, 1.25);
		Vector3 v3 = new Vector3(2.0, -3.5, 2.2);
		Vector3 normal = new Vector3(1, 0, 0);
		double constant = -2;

		a.setFromCoplanarPoints(v1, v2, v3);

		assertTrue(a.normal().equals(normal), "Check normal");
		assertEquals(constant, a.constant(), "Check constant");

	}

	@Test
	public void $clone() {

		Plane a = new Plane(new Vector3(2.0, 0.5, 0.25));
		Plane b = a.clone();

		assertTrue(a.equals(b), "clones are equal");

	}

	@Test
	public void copy() {

		Plane a = new Plane(new Vector3(x, y, z), w);
		Plane b = new Plane().copy(a);
		assertTrue(b.normal().x() == x, "Passed!");
		assertTrue(b.normal().y() == y, "Passed!");
		assertTrue(b.normal().z() == z, "Passed!");
		assertTrue(b.constant() == w, "Passed!");

		// ensure that it is a true copy
		a.normal().x(0);
		a.normal().y(-1);
		a.normal().z(-2);
		a.constant(-3);
		assertTrue(b.normal().x() == x, "Passed!");
		assertTrue(b.normal().y() == y, "Passed!");
		assertTrue(b.normal().z() == z, "Passed!");
		assertTrue(b.constant() == w, "Passed!");

	}

	@Test
	public void normalize() {

		Plane a = new Plane(new Vector3(2, 0, 0), 2);

		a.normalize();
		assertTrue(a.normal().length() == 1, "Passed!");
		assertTrue(a.normal().equals(new Vector3(1, 0, 0)), "Passed!");
		assertTrue(a.constant() == 1, "Passed!");

	}

	@Test
	public void negate_distanceToPoint() {

		Plane a = new Plane(new Vector3(2, 0, 0), -2);

		a.normalize();
		assertTrue(a.distanceToPoint(new Vector3(4, 0, 0)) == 3, "Passed!");
		assertTrue(a.distanceToPoint(new Vector3(1, 0, 0)) == 0, "Passed!");

		a.negate();
		assertTrue(a.distanceToPoint(new Vector3(4, 0, 0)) == -3, "Passed!");
		assertTrue(a.distanceToPoint(new Vector3(1, 0, 0)) == 0, "Passed!");

	}

	@Test
	public void distanceToPoint() {

		Plane a = new Plane(new Vector3(2, 0, 0), -2);
		Vector3 point = new Vector3();

		a.normalize().projectPoint(zero3.clone(), point);
		assertTrue(a.distanceToPoint(point) == 0, "Passed!");
		assertTrue(a.distanceToPoint(new Vector3(4, 0, 0)) == 3, "Passed!");

	}

	@Test
	public void distanceToSphere() {

		Plane a = new Plane(new Vector3(1, 0, 0), 0);

		Sphere b = new Sphere(new Vector3(2, 0, 0), 1);

		assertTrue(a.distanceToSphere(b) == 1, "Passed!");

		a.set(new Vector3(1, 0, 0), 2);
		assertTrue(a.distanceToSphere(b) == 3, "Passed!");
		a.set(new Vector3(1, 0, 0), -2);
		assertTrue(a.distanceToSphere(b) == -1, "Passed!");

	}

	@Test
	public void projectPoint() {

		Plane a = new Plane(new Vector3(1, 0, 0), 0);
		Vector3 point = new Vector3();

		a.projectPoint(new Vector3(10, 0, 0), point);
		assertTrue(point.equals(zero3), "Passed!");
		a.projectPoint(new Vector3(-10, 0, 0), point);
		assertTrue(point.equals(zero3), "Passed!");

		a = new Plane(new Vector3(0, 1, 0), -1);
		a.projectPoint(new Vector3(0, 0, 0), point);
		assertTrue(point.equals(new Vector3(0, 1, 0)), "Passed!");
		a.projectPoint(new Vector3(0, 1, 0), point);
		assertTrue(point.equals(new Vector3(0, 1, 0)), "Passed!");

	}

	@Test
	public void isInterestionLine_intersectLine() {

		Plane a = new Plane(new Vector3(1, 0, 0), 0);
		Vector3 point = new Vector3();

		Line3 l1 = new Line3(new Vector3(-10, 0, 0), new Vector3(10, 0, 0));
		a.intersectLine(l1, point);
		assertTrue(point.equals(new Vector3(0, 0, 0)), "Passed!");

		a = new Plane(new Vector3(1, 0, 0), -3);
		a.intersectLine(l1, point);
		assertTrue(point.equals(new Vector3(3, 0, 0)), "Passed!");

	}

	@Test
	public void intersectsBox() {

		Box3 a = new Box3(zero3.clone(), one3.clone());
		Plane b = new Plane(new Vector3(0, 1, 0), 1);
		Plane c = new Plane(new Vector3(0, 1, 0), 1.25);
		Plane d = new Plane(new Vector3(0, -1, 0), 1.25);
		Plane e = new Plane(new Vector3(0, 1, 0), 0.25);
		Plane f = new Plane(new Vector3(0, 1, 0), -0.25);
		Plane g = new Plane(new Vector3(0, 1, 0), -0.75);
		Plane h = new Plane(new Vector3(0, 1, 0), -1);
		Plane i = new Plane(new Vector3(1, 1, 1).normalize(), -1.732);
		Plane j = new Plane(new Vector3(1, 1, 1).normalize(), -1.733);

		assertTrue(!b.intersectsBox(a), "Passed!");
		assertTrue(!c.intersectsBox(a), "Passed!");
		assertTrue(!d.intersectsBox(a), "Passed!");
		assertTrue(!e.intersectsBox(a), "Passed!");
		assertTrue(f.intersectsBox(a), "Passed!");
		assertTrue(g.intersectsBox(a), "Passed!");
		assertTrue(h.intersectsBox(a), "Passed!");
		assertTrue(i.intersectsBox(a), "Passed!");
		assertTrue(!j.intersectsBox(a), "Passed!");

	}

	@Test
	public void intersectsSphere() {

		Sphere a = new Sphere(zero3.clone(), 1);
		Plane b = new Plane(new Vector3(0, 1, 0), 1);
		Plane c = new Plane(new Vector3(0, 1, 0), 1.25);
		Plane d = new Plane(new Vector3(0, -1, 0), 1.25);

		assertTrue(b.intersectsSphere(a), "Passed!");
		assertTrue(!c.intersectsSphere(a), "Passed!");
		assertTrue(!d.intersectsSphere(a), "Passed!");

	}

	@Test
	public void coplanarPoint() {

		Vector3 point = new Vector3();

		Plane a = new Plane(new Vector3(1, 0, 0), 0);
		a.coplanarPoint(point);
		assertTrue(a.distanceToPoint(point) == 0, "Passed!");

		a = new Plane(new Vector3(0, 1, 0), -1);
		a.coplanarPoint(point);
		assertTrue(a.distanceToPoint(point) == 0, "Passed!");

	}

	@Test
	public void applyMatrix4_translate() {

		Plane a = new Plane(new Vector3(1, 0, 0), 0);

		Matrix4 m = new Matrix4();
		m.makeRotationZ(Math.PI * 0.5);

		assertTrue(comparePlane(a.clone().applyMatrix4(m), new Plane(new Vector3(0, 1, 0), 0)), "Passed!");

		a = new Plane(new Vector3(0, 1, 0), -1);
		assertTrue(comparePlane(a.clone().applyMatrix4(m), new Plane(new Vector3(-1, 0, 0), -1)), "Passed!");

		m.makeTranslation(1, 1, 1);
		assertTrue(comparePlane(a.clone().applyMatrix4(m), a.clone().translate(new Vector3(1, 1, 1))), "Passed!");

	}

	@Test
	public void equals() {

		Plane a = new Plane(new Vector3(1, 0, 0), 0);
		Plane b = new Plane(new Vector3(1, 0, 0), 1);
		Plane c = new Plane(new Vector3(0, 1, 0), 0);

		assertTrue(a.normal().equals(b.normal()), "Normals: equal");
		assertFalse(a.normal().equals(c.normal()), "Normals: not equal");

		assertNotEquals(a.constant(), b.constant(), eps, "Constants: not equal");
		assertEquals(c.constant(), a.constant(), "Constants: equal");

		assertFalse(a.equals(b), "Planes: not equal");
		assertFalse(a.equals(c), "Planes: not equal");

		a.copy(b);
		assertTrue(a.normal().equals(b.normal()), "Normals after copy(): equal");
		assertEquals(b.constant(), a.constant(), "Constants after copy(): equal");
		assertTrue(a.equals(b), "Planes after copy(): equal");

	}

}
