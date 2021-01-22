package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.math.Box3;
import net.three4j.math.Plane;
import net.three4j.math.Ray;
import net.three4j.math.Sphere;
import net.three4j.math.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static net.three4j.unit.src.math.ConstantsTests.zero3;
import static net.three4j.unit.src.math.ConstantsTests.one3;
import static net.three4j.unit.src.math.ConstantsTests.two3;
import static net.three4j.unit.src.math.ConstantsTests.eps;
import static net.three4j.unit.src.math.ConstantsTests.posInf3;
import static net.three4j.unit.src.math.ConstantsTests.negInf3;


public class RayTests {
	
		@Test
		public void Instancing() {

			Ray a = new Ray();
			assertTrue( a.origin().equals( zero3 ), "Passed!" );
			assertTrue( a.direction().equals( new Vector3( 0, 0, -1 ) ), "Passed!" );

			a = new Ray( two3.clone(), one3.clone() );
			assertTrue( a.origin().equals( two3 ), "Passed!" );
			assertTrue( a.direction().equals( one3 ), "Passed!" );

		}

//		@Test
//		public void isRay() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

		@Test
		public void set() {

			Ray a = new Ray();

			a.set( one3, one3 );
			assertTrue( a.origin().equals( one3 ), "Passed!" );
			assertTrue( a.direction().equals( one3 ), "Passed!" );

		}

		@Test
		public void recast_clone() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );

			assertTrue( a.recast( 0 ).equals( a ), "Passed!" );

			Ray b = a.clone();
			assertTrue( b.recast( - 1 ).equals( new Ray( new Vector3( 1, 1, 0 ), new Vector3( 0, 0, 1 ) ) ), "Passed!" );

			Ray c = a.clone();
			assertTrue( c.recast( 1 ).equals( new Ray( new Vector3( 1, 1, 2 ), new Vector3( 0, 0, 1 ) ) ), "Passed!" );

			Ray d = a.clone();
			Ray e = d.clone().recast( 1 );
			assertTrue( d.equals( a ), "Passed!" );
			assertTrue( ! e.equals( d ), "Passed!" );
			assertTrue( e.equals( c ), "Passed!" );

		}

		@Test
		public void copy_equals() {

			Ray a = new Ray( zero3.clone(), one3.clone() );
			Ray b = new Ray().copy( a );
			assertTrue( b.origin().equals( zero3 ), "Passed!" );
			assertTrue( b.direction().equals( one3 ), "Passed!" );

			// ensure that it is a true copy
			a.origin(zero3);
			a.direction(one3);
			assertTrue( b.origin().equals( zero3 ), "Passed!" );
			assertTrue( b.direction().equals( one3 ), "Passed!" );

		}

		@Test
		public void at() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );
			Vector3 point = new Vector3();

			a.at( 0, point );
			assertTrue( point.equals( one3 ), "Passed!" );
			a.at( - 1, point );
			assertTrue( point.equals( new Vector3( 1, 1, 0 ) ), "Passed!" );
			a.at( 1, point );
			assertTrue( point.equals( new Vector3( 1, 1, 2 ) ), "Passed!" );

		}

		@Test
		public void lookAt() {

			Ray a = new Ray( two3.clone(), one3.clone() );
			Vector3 target = one3.clone();
			Vector3 expected = target.sub( two3 ).normalize();

			a.lookAt( target );
			assertTrue( a.direction().equals( expected ), "Check if we're looking in the right direction" );

		}

		@Test
		public void closestPointToPoint() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );
			Vector3 point = new Vector3();

			// behind the ray
			a.closestPointToPoint( zero3, point );
			assertTrue( point.equals( one3 ), "Passed!" );

			// front of the ray
			a.closestPointToPoint( new Vector3( 0, 0, 50 ), point );
			assertTrue( point.equals( new Vector3( 1, 1, 50 ) ), "Passed!" );

			// exactly on the ray
			a.closestPointToPoint( one3, point );
			assertTrue( point.equals( one3 ), "Passed!" );

		}

		@Test
		public void distanceToPoint() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );

			// behind the ray
			double b = a.distanceToPoint( zero3 );
			assertTrue( b == Math.sqrt( 3 ), "Passed!" );

			// front of the ray
			double c = a.distanceToPoint( new Vector3( 0, 0, 50 ) );
			assertTrue( c == Math.sqrt( 2 ), "Passed!" );

			// exactly on the ray
			double d = a.distanceToPoint( one3 );
			assertTrue( d == 0, "Passed!" );

		}

		@Test
		public void distanceSqToPoint() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );

			// behind the ray
			double b = a.distanceSqToPoint( zero3 );
			assertTrue( b == 3, "Passed!" );

			// front of the ray
			double c = a.distanceSqToPoint( new Vector3( 0, 0, 50 ) );
			assertTrue( c == 2, "Passed!" );

			// exactly on the ray
			double d = a.distanceSqToPoint( one3 );
			assertTrue( d == 0, "Passed!" );

		}

		@Test
		public void distanceSqToSegment() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );
			Vector3 ptOnLine = new Vector3();
			Vector3 ptOnSegment = new Vector3();

			//segment in front of the ray
			Vector3 v0 = new Vector3( 3, 5, 50 );
			Vector3 v1 = new Vector3( 50, 50, 50 ); // just a far away point
			double distSqr = a.distanceSqToSegment( v0, v1, ptOnLine, ptOnSegment );

			assertTrue( ptOnSegment.distanceTo( v0 ) < 0.0001, "Passed!" );
			assertTrue( ptOnLine.distanceTo( new Vector3( 1, 1, 50 ) ) < 0.0001, "Passed!" );
			// ((3-1) * (3-1) + (5-1) * (5-1) = 4 + 16 = 20
			assertTrue( Math.abs( distSqr - 20 ) < 0.0001, "Passed!" );

			//segment behind the ray
			v0 = new Vector3( - 50, - 50, - 50 ); // just a far away point
			v1 = new Vector3( - 3, - 5, - 4 );
			distSqr = a.distanceSqToSegment( v0, v1, ptOnLine, ptOnSegment );

			assertTrue( ptOnSegment.distanceTo( v1 ) < 0.0001, "Passed!" );
			assertTrue( ptOnLine.distanceTo( one3 ) < 0.0001, "Passed!" );
			// ((-3-1) * (-3-1) + (-5-1) * (-5-1) + (-4-1) + (-4-1) = 16 + 36 + 25 = 77
			assertTrue( Math.abs( distSqr - 77 ) < 0.0001, "Passed!" );

			//exact intersection between the ray and the segment
			v0 = new Vector3( - 50, - 50, - 50 );
			v1 = new Vector3( 50, 50, 50 );
			distSqr = a.distanceSqToSegment( v0, v1, ptOnLine, ptOnSegment );

			assertTrue( ptOnSegment.distanceTo( one3 ) < 0.0001, "Passed!" );
			assertTrue( ptOnLine.distanceTo( one3 ) < 0.0001, "Passed!" );
			assertTrue( distSqr < 0.0001, "Passed!" );

		}

		@Test
		public void intersectSphere() {

			double TOL = 0.0001;
			Vector3 point = new Vector3();

			// ray a0 origin located at ( 0, 0, 0 ) and points outward in negative-z direction
			Ray a0 = new Ray( zero3.clone(), new Vector3( 0, 0, - 1 ) );
			// ray a1 origin located at ( 1, 1, 1 ) and points left in negative-x direction
			Ray a1 = new Ray( one3.clone(), new Vector3( - 1, 0, 0 ) );

			// sphere (radius of 2) located behind ray a0, should result in null
			Sphere b = new Sphere( new Vector3( 0, 0, 3 ), 2 );
			a0.intersectSphere( b, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// sphere (radius of 2) located in front of, but too far right of ray a0, should result in null
			b = new Sphere( new Vector3( 3, 0, - 1 ), 2 );
			a0.intersectSphere( b, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// sphere (radius of 2) located below ray a1, should result in null
			b = new Sphere( new Vector3( 1, - 2, 1 ), 2 );
			a1.intersectSphere( b, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// sphere (radius of 1) located to the left of ray a1, should result in intersection at 0, 1, 1
			b = new Sphere( new Vector3( - 1, 1, 1 ), 1 );
			a1.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 1, 1 ) ) < TOL, "Passed!" );

			// sphere (radius of 1) located in front of ray a0, should result in intersection at 0, 0, -1
			b = new Sphere( new Vector3( 0, 0, - 2 ), 1 );
			a0.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 0, - 1 ) ) < TOL, "Passed!" );

			// sphere (radius of 2) located in front & right of ray a0, should result in intersection at 0, 0, -1, or left-most edge of sphere
			b = new Sphere( new Vector3( 2, 0, - 1 ), 2 );
			a0.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 0, - 1 ) ) < TOL, "Passed!" );

			// same situation as above, but move the sphere a fraction more to the right, and ray a0 should now just miss
			b = new Sphere( new Vector3( 2.01, 0, - 1 ), 2 );
			a0.intersectSphere( b, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// following QUnit.tests are for situations where the ray origin is inside the sphere

			// sphere (radius of 1) center located at ray a0 origin / sphere surrounds the ray origin, so the first intersect point 0, 0, 1,
			// is behind ray a0.  Therefore, second exit point on back of sphere will be returned: 0, 0, -1
			// thus keeping the intersection point always in front of the ray.
			b = new Sphere( zero3.clone(), 1 );
			a0.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 0, - 1 ) ) < TOL, "Passed!" );

			// sphere (radius of 4) center located behind ray a0 origin / sphere surrounds the ray origin, so the first intersect point 0, 0, 5,
			// is behind ray a0.  Therefore, second exit point on back of sphere will be returned: 0, 0, -3
			// thus keeping the intersection point always in front of the ray.
			b = new Sphere( new Vector3( 0, 0, 1 ), 4 );
			a0.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 0, - 3 ) ) < TOL, "Passed!" );

			// sphere (radius of 4) center located in front of ray a0 origin / sphere surrounds the ray origin, so the first intersect point 0, 0, 3,
			// is behind ray a0.  Therefore, second exit point on back of sphere will be returned: 0, 0, -5
			// thus keeping the intersection point always in front of the ray.
			b = new Sphere( new Vector3( 0, 0, - 1 ), 4 );
			a0.intersectSphere( b, point );
			assertTrue( point.distanceTo( new Vector3( 0, 0, - 5 ) ) < TOL, "Passed!" );

		}

		@Test
		public void intersectsSphere() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );
			Sphere b = new Sphere( zero3, 0.5 );
			Sphere c = new Sphere( zero3, 1.5 );
			Sphere d = new Sphere( one3, 0.1 );
			Sphere e = new Sphere( two3, 0.1 );
			Sphere f = new Sphere( two3, 1 );

			assertTrue( ! a.intersectsSphere( b ), "Passed!" );
			assertTrue( ! a.intersectsSphere( c ), "Passed!" );
			assertTrue( a.intersectsSphere( d ), "Passed!" );
			assertTrue( ! a.intersectsSphere( e ), "Passed!" );
			assertTrue( ! a.intersectsSphere( f ), "Passed!" );

		}

//		@Test
//		public void distanceToPlane() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

		@Test
		public void intersectPlane() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );
			Vector3 point = new Vector3();

			// parallel plane behind
			Plane b = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), new Vector3( 1, 1, - 1 ) );
			a.intersectPlane( b, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// parallel plane coincident with origin
			Plane c = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), new Vector3( 1, 1, 0 ) );
			a.intersectPlane( c, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			// parallel plane infront
			Plane d = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), new Vector3( 1, 1, 1 ) );
			a.intersectPlane( d, point.copy( posInf3 ) );
			assertTrue( point.equals( a.origin() ), "Passed!" );

			// perpendical ray that overlaps exactly
			Plane e = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 1, 0, 0 ), one3 );
			a.intersectPlane( e, point.copy( posInf3 ) );
			assertTrue( point.equals( a.origin() ), "Passed!" );

			// perpendical ray that doesn't overlap
			Plane f = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 1, 0, 0 ), zero3 );
			a.intersectPlane( f, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

		}

		@Test
		public void intersectsPlane() {

			Ray a = new Ray( one3.clone(), new Vector3( 0, 0, 1 ) );

			// parallel plane in front of the ray
			Plane b = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), one3.clone().sub( new Vector3( 0, 0, - 1 ) ) );
			assertTrue( a.intersectsPlane( b ), "Passed!" );

			// parallel plane coincident with origin
			Plane c = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), one3.clone().sub( new Vector3( 0, 0, 0 ) ) );
			assertTrue( a.intersectsPlane( c ), "Passed!" );

			// parallel plane behind the ray
			Plane d = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 0, 0, 1 ), one3.clone().sub( new Vector3( 0, 0, 1 ) ) );
			assertTrue( ! a.intersectsPlane( d ), "Passed!" );

			// perpendical ray that overlaps exactly
			Plane e = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 1, 0, 0 ), one3 );
			assertTrue( a.intersectsPlane( e ), "Passed!" );

			// perpendical ray that doesn't overlap
			Plane f = new Plane().setFromNormalAndCoplanarPoint( new Vector3( 1, 0, 0 ), zero3 );
			assertTrue( ! a.intersectsPlane( f ), "Passed!" );

		}

		@Test
		public void intersectBox() {

			double TOL = 0.0001;

			Box3 box = new Box3( new Vector3( - 1, - 1, - 1 ), new Vector3( 1, 1, 1 ) );
			Vector3 point = new Vector3();

			Ray a = new Ray( new Vector3( - 2, 0, 0 ), new Vector3( 1, 0, 0 ) );
			//ray should intersect box at -1,0,0
			assertTrue( a.intersectsBox( box ) == true, "Passed!" );
			a.intersectBox( box, point );
			assertTrue( point.distanceTo( new Vector3( - 1, 0, 0 ) ) < TOL, "Passed!" );

			Ray b = new Ray( new Vector3( - 2, 0, 0 ), new Vector3( - 1, 0, 0 ) );
			//ray is point away from box, it should not intersect
			assertTrue( b.intersectsBox( box ) == false, "Passed!" );
			b.intersectBox( box, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

			Ray c = new Ray( new Vector3( 0, 0, 0 ), new Vector3( 1, 0, 0 ) );
			// ray is inside box, should return exit point
			assertTrue( c.intersectsBox( box ) == true, "Passed!" );
			c.intersectBox( box, point );
			assertTrue( point.distanceTo( new Vector3( 1, 0, 0 ) ) < TOL, "Passed!" );

			Ray d = new Ray( new Vector3( 0, 2, 1 ), new Vector3( 0, - 1, - 1 ).normalize() );
			//tilted ray should intersect box at 0,1,0
			assertTrue( d.intersectsBox( box ) == true, "Passed!" );
			d.intersectBox( box, point );
			assertTrue( point.distanceTo( new Vector3( 0, 1, 0 ) ) < TOL, "Passed!" );

			Ray e = new Ray( new Vector3( 1, - 2, 1 ), new Vector3( 0, 1, 0 ).normalize() );
			//handle case where ray is coplanar with one of the boxes side - box in front of ray
			assertTrue( e.intersectsBox( box ) == true, "Passed!" );
			e.intersectBox( box, point );
			assertTrue( point.distanceTo( new Vector3( 1, - 1, 1 ) ) < TOL, "Passed!" );

			Ray f = new Ray( new Vector3( 1, - 2, 0 ), new Vector3( 0, - 1, 0 ).normalize() );
			//handle case where ray is coplanar with one of the boxes side - box behind ray
			assertTrue( f.intersectsBox( box ) == false, "Passed!" );
			f.intersectBox( box, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "Passed!" );

		}

//		@Test
//		public void intersectsBox() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
		@Test
		public void intersectTriangle() {

			Ray ray = new Ray();
			Vector3 a = new Vector3( 1, 1, 0 );
			Vector3 b = new Vector3( 0, 1, 1 );
			Vector3 c = new Vector3( 1, 0, 1 );
			Vector3 point = new Vector3();

			// DdN == 0
			ray.set( ray.origin(), zero3.clone() );
			ray.intersectTriangle( a, b, c, false, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection if direction == zero" );

			// DdN > 0, backfaceCulling = true
			ray.set( ray.origin(), one3.clone() );
			ray.intersectTriangle( a, b, c, true, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection with backside faces if backfaceCulling is true" );

			// DdN > 0
			ray.set( ray.origin(), one3.clone() );
			ray.intersectTriangle( a, b, c, false, point );
			assertTrue( Math.abs( point.x() - 2. / 3 ) <= eps, "Successful intersection: check x" );
			assertTrue( Math.abs( point.y() - 2. / 3 ) <= eps, "Successful intersection: check y" );
			assertTrue( Math.abs( point.z() - 2. / 3 ) <= eps, "Successful intersection: check z" );

			// DdN > 0, DdQxE2 < 0
			b.multiplyScalar( - 1 );
			ray.intersectTriangle( a, b, c, false, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection" );

			// DdN > 0, DdE1xQ < 0
			a.multiplyScalar( - 1 );
			ray.intersectTriangle( a, b, c, false, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection" );

			// DdN > 0, DdQxE2 + DdE1xQ > DdN
			b.multiplyScalar( - 1 );
			ray.intersectTriangle( a, b, c, false, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection" );

			// DdN < 0, QdN < 0
			a.multiplyScalar( - 1 );
			b.multiplyScalar( - 1 );
			ray.direction().multiplyScalar( - 1 );
			ray.intersectTriangle( a, b, c, false, point.copy( posInf3 ) );
			assertTrue( point.equals( posInf3 ), "No intersection when looking in the wrong direction" );

		}

//		@Test
//		public void equals() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
}
