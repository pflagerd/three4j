package net.three4j.unit.src.math;

import org.junit.jupiter.api.Test;

import net.three4j.core.BufferAttribute;
import net.three4j.geometries.BoxBufferGeometry;
import net.three4j.math.Box3;
import net.three4j.math.Vector3;
//import net.three4j.objects.Mesh;
//import net.three4j.unit.utils.Float32Array;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//import static net.three4j.unit.src.math.ConstantsTests.x;
//import static net.three4j.unit.src.math.ConstantsTests.y;
//import static net.three4j.unit.src.math.ConstantsTests.z;
//import static net.three4j.unit.src.math.ConstantsTests.zero3;
//import static net.three4j.unit.src.math.ConstantsTests.one3;
//import static net.three4j.unit.src.math.ConstantsTests.two3;
//import static net.three4j.unit.src.math.ConstantsTests.eps;
//import static net.three4j.unit.src.math.ConstantsTests.posInf3;
//import static net.three4j.unit.src.math.ConstantsTests.negInf3;
//
//class Box3Tests {
//	
//
//	boolean compareBox( Box3 a, Box3 b) {
//		return compareBox(a, b, 0.0001);
//	}
//	
//	boolean compareBox( Box3 a, Box3 b, double threshold ) {
//
//		return ( a.min().distanceTo( b.min() ) < threshold && a.max().distanceTo( b.max() ) < threshold );
//
//	}
//
//		// INSTANCING
//		@Test
//		public void Instancing() {
//
//			Box3 a = new Box3();
//			assertTrue( a.min().equals( posInf3 ), "Passed!" );
//			assertTrue( a.max().equals( negInf3 ), "Passed!" );
//
//			a = new Box3( zero3.clone(), zero3.clone() );
//			assertTrue( a.min().equals( zero3 ), "Passed!" );
//			assertTrue( a.max().equals( zero3 ), "Passed!" );
//
//			a = new Box3( zero3.clone(), one3.clone() );
//			assertTrue( a.min().equals( zero3 ), "Passed!" );
//			assertTrue( a.max().equals( one3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void set() {
//
//			Box3 a = new Box3();
//
//			a.set( zero3, one3 );
//			assertTrue( a.min().equals( zero3 ), "Passed!" );
//			assertTrue( a.max().equals( one3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void setFromArray() {
//
//			Box3 a = new Box3();
//
//			a.setFromArray( new double[] { 0, 0, 0, 1, 1, 1, 2, 2, 2 } );
//			assertTrue( a.min().equals( zero3 ), "Passed!" );
//			assertTrue( a.max().equals( two3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void setFromBufferAttribute() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			BufferAttribute bigger = new BufferAttribute( new Float32Array( new double[] {
//				-2, -2, -2, 2, 2, 2, 1.5, 1.5, 1.5, 0, 0, 0
//			} ), 3 );
//			BufferAttribute smaller = new BufferAttribute( new Float32Array( new double[] {
//				- 0.5, - 0.5, - 0.5, 0.5, 0.5, 0.5, 0, 0, 0
//			} ), 3 );
//			Vector3 newMin = new Vector3( - 2, - 2, - 2 );
//			Vector3 newMax = new Vector3( 2, 2, 2 );
//
//			a.setFromBufferAttribute( bigger );
//			assertTrue( a.min().equals( newMin ), "Bigger box: correct new minimum" );
//			assertTrue( a.max().equals( newMax ), "Bigger box: correct new maximum" );
//
//			newMin.set( - 0.5, - 0.5, - 0.5 );
//			newMax.set( 0.5, 0.5, 0.5 );
//
//			a.setFromBufferAttribute( smaller );
//			assertTrue( a.min().equals( newMin ), "Smaller box: correct new minimum" );
//			assertTrue( a.max().equals( newMax ), "Smaller box: correct new maximum" );
//
//		}
//
//		@Test
//		public void setFromPoints() {
//
//			Box3 a = new Box3();
//
//			a.setFromPoints( new Vector3[] { zero3, one3, two3 } );
//			assertTrue( a.min().equals( zero3 ), "Passed!" );
//			assertTrue( a.max().equals( two3 ), "Passed!" );
//
//			a.setFromPoints( new Vector3[] { one3 } );
//			assertTrue( a.min().equals( one3 ), "Passed!" );
//			assertTrue( a.max().equals( one3 ), "Passed!" );
//
//			a.setFromPoints( new Vector3[0] );
//			assertTrue( a.isEmpty(), "Passed!" );
//
//		}
//
//		@Test
//		public void setFromCenterAndSize() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Box3 b = a.clone();
//			Vector3 centerA = new Vector3();
//			Vector3 sizeA = new Vector3();
//			Vector3 sizeB = new Vector3();
//			Vector3 newCenter = one3;
//			Vector3 newSize = two3;
//
//			a.getCenter( centerA );
//			a.getSize( sizeA );
//			a.setFromCenterAndSize( centerA, sizeA );
//			assertTrue( a.equals( b ), "Same values: no changes" );
//
//			a.setFromCenterAndSize( newCenter, sizeA );
//			a.getCenter( centerA );
//			a.getSize( sizeA );
//			b.getSize( sizeB );
//
//			assertTrue( centerA.equals( newCenter ), "Move center: correct new center" );
//			assertTrue( sizeA.equals( sizeB ), "Move center: no change in size" );
//			assertFalse( a.equals( b ), "Move center: no longer equal to old values" );
//
//			a.setFromCenterAndSize( centerA, newSize );
//			a.getCenter( centerA );
//			a.getSize( sizeA );
//			assertTrue( centerA.equals( newCenter ), "Resize: no change to center" );
//			assertTrue( sizeA.equals( newSize ), "Resize: correct new size" );
//			assertFalse( a.equals( b ), "Resize: no longer equal to old values" );
//
//		}
//
//		@Test
//		public void setFromObject_BufferGeometry() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Mesh object = new Mesh( new BoxBufferGeometry( 2, 2, 2 ) );
//			Mesh child = new Mesh( new BoxBufferGeometry( 1, 1, 1 ) );
//			object.add( child );
//
//			a.setFromObject( object );
//			assertTrue( a.min().equals( new Vector3( - 1, - 1, - 1 ) ), "Correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 1, 1, 1 ) ), "Correct new maximum" );
//
//		}
//
//		@Test
//		public Box3 $clone() {
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//
//			var b = a.clone();
//			assertTrue( b.min().equals( zero3 ), "Passed!" );
//			assertTrue( b.max().equals( one3 ), "Passed!" );
//
//			a = new Box3();
//			var b = a.clone();
//			assertTrue( b.min().equals( posInf3 ), "Passed!" );
//			assertTrue( b.max().equals( negInf3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void copy() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Box3 b = new Box3().copy( a );
//			assertTrue( b.min().equals( zero3 ), "Passed!" );
//			assertTrue( b.max().equals( one3 ), "Passed!" );
//
//			// ensure that it is a true copy
//			a.min = zero3;
//			a.max = one3;
//			assertTrue( b.min().equals( zero3 ), "Passed!" );
//			assertTrue( b.max().equals( one3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void empty/makeEmpty() {
//
//			Box3 a = new Box3();
//
//			assertTrue( a.isEmpty(), "Passed!" );
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			assertTrue( ! a.isEmpty(), "Passed!" );
//
//			a.makeEmpty();
//			assertTrue( a.isEmpty(), "Passed!" );
//
//		}
//
//		@Test
//		public void isEmpty() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			assertTrue( ! a.isEmpty(), "Passed!" );
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			assertTrue( ! a.isEmpty(), "Passed!" );
//
//			Box3 a = new Box3( two3.clone(), one3.clone() );
//			assertTrue( a.isEmpty(), "Passed!" );
//
//			Box3 a = new Box3( posInf3.clone(), negInf3.clone() );
//			assertTrue( a.isEmpty(), "Passed!" );
//
//
//		}
//
//		@Test
//		public void getCenter() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Vector3 center = new Vector3();
//
//			assertTrue( a.getCenter( center ).equals( zero3 ), "Passed!" );
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			var midpoint = one3.clone().multiplyScalar( 0.5 );
//			assertTrue( a.getCenter( center ).equals( midpoint ), "Passed!" );
//
//		}
//
//		@Test
//		public void getSize() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Vector3 size = new Vector3();
//
//			assertTrue( a.getSize( size ).equals( zero3 ), "Passed!" );
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			assertTrue( a.getSize( size ).equals( one3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void expandByPoint() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Vector3 center = new Vector3();
//			Vector3 size = new Vector3();
//
//			a.expandByPoint( zero3 );
//			assertTrue( a.getSize( size ).equals( zero3 ), "Passed!" );
//
//			a.expandByPoint( one3 );
//			assertTrue( a.getSize( size ).equals( one3 ), "Passed!" );
//
//			a.expandByPoint( one3.clone().negate() );
//			assertTrue( a.getSize( size ).equals( one3.clone().multiplyScalar( 2 ) ), "Passed!" );
//			assertTrue( a.getCenter( center ).equals( zero3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void expandByVector() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Vector3 center = new Vector3();
//			Vector3 size = new Vector3();
//
//			a.expandByVector( zero3 );
//			assertTrue( a.getSize( size ).equals( zero3 ), "Passed!" );
//
//			a.expandByVector( one3 );
//			assertTrue( a.getSize( size ).equals( one3.clone().multiplyScalar( 2 ) ), "Passed!" );
//			assertTrue( a.getCenter( center ).equals( zero3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void expandByScalar() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Vector3 center = new Vector3();
//			Vector3 size = new Vector3();
//
//			a.expandByScalar( 0 );
//			assertTrue( a.getSize( size ).equals( zero3 ), "Passed!" );
//
//			a.expandByScalar( 1 );
//			assertTrue( a.getSize( size ).equals( one3.clone().multiplyScalar( 2 ) ), "Passed!" );
//			assertTrue( a.getCenter( center ).equals( zero3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void expandByObject() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			var b = a.clone();
//			Mesh bigger = new Mesh( new BoxGeometry( 2, 2, 2 ) );
//			Mesh smaller = new Mesh( new BoxGeometry( 0.5, 0.5, 0.5 ) );
//			Mesh child = new Mesh( new BoxGeometry( 1, 1, 1 ) );
//
//			// just a bigger box to begin with
//			a.expandByObject( bigger );
//			assertTrue( a.min().equals( new Vector3( - 1, - 1, - 1 ) ), "Bigger box: correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 1, 1, 1 ) ), "Bigger box: correct new maximum" );
//
//			// a translated, bigger box
//			a.copy( b );
//			bigger.translateX( 2 );
//			a.expandByObject( bigger );
//			assertTrue( a.min().equals( new Vector3( 0, - 1, - 1 ) ), "Translated, bigger box: correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 3, 1, 1 ) ), "Translated, bigger box: correct new maximum" );
//
//			// a translated, bigger box with child
//			a.copy( b );
//			bigger.add( child );
//			a.expandByObject( bigger );
//			assertTrue( a.min().equals( new Vector3( 0, - 1, - 1 ) ), "Translated, bigger box with child: correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 3, 1, 1 ) ), "Translated, bigger box with child: correct new maximum" );
//
//			// a translated, bigger box with a translated child
//			a.copy( b );
//			child.translateX( 2 );
//			a.expandByObject( bigger );
//			assertTrue( a.min().equals( new Vector3( 0, - 1, - 1 ) ), "Translated, bigger box with translated child: correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 4.5, 1, 1 ) ), "Translated, bigger box with translated child: correct new maximum" );
//
//			// a smaller box
//			a.copy( b );
//			a.expandByObject( smaller );
//			assertTrue( a.min().equals( new Vector3( - 0.25, - 0.25, - 0.25 ) ), "Smaller box: correct new minimum" );
//			assertTrue( a.max().equals( new Vector3( 1, 1, 1 ) ), "Smaller box: correct new maximum" );
//
//			//
//			assertTrue( new Box3().expandByObject( new Mesh() ).isEmpty() == true, "The AABB of a mesh with inital geometry is empty." );
//
//		}
//
//		@Test
//		public void containsPoint() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//
//			assertTrue( a.containsPoint( zero3 ), "Passed!" );
//			assertTrue( ! a.containsPoint( one3 ), "Passed!" );
//
//			a.expandByScalar( 1 );
//			assertTrue( a.containsPoint( zero3 ), "Passed!" );
//			assertTrue( a.containsPoint( one3 ), "Passed!" );
//			assertTrue( a.containsPoint( one3.clone().negate() ), "Passed!" );
//
//		}
//
//		@Test
//		public void containsBox() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//
//			assertTrue( a.containsBox( a ), "Passed!" );
//			assertTrue( ! a.containsBox( b ), "Passed!" );
//			assertTrue( ! a.containsBox( c ), "Passed!" );
//
//			assertTrue( b.containsBox( a ), "Passed!" );
//			assertTrue( c.containsBox( a ), "Passed!" );
//			assertTrue( ! b.containsBox( c ), "Passed!" );
//
//		}
//
//		@Test
//		public void getParameter() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Box3 b = new Box3( one3.clone().negate(), one3.clone() );
//			Vector3 parameter = new Vector3();
//
//			a.getParameter( zero3, parameter );
//			assertTrue( parameter.equals( zero3 ), "Passed!" );
//			a.getParameter( one3, parameter );
//			assertTrue( parameter.equals( one3 ), "Passed!" );
//
//			b.getParameter( one3.clone().negate(), parameter );
//			assertTrue( parameter.equals( zero3 ), "Passed!" );
//			b.getParameter( zero3, parameter );
//			assertTrue( parameter.equals( new Vector3( 0.5, 0.5, 0.5 ) ), "Passed!" );
//			b.getParameter( one3, parameter );
//			assertTrue( parameter.equals( one3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void intersectsBox() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//
//			assertTrue( a.intersectsBox( a ), "Passed!" );
//			assertTrue( a.intersectsBox( b ), "Passed!" );
//			assertTrue( a.intersectsBox( c ), "Passed!" );
//
//			assertTrue( b.intersectsBox( a ), "Passed!" );
//			assertTrue( c.intersectsBox( a ), "Passed!" );
//			assertTrue( b.intersectsBox( c ), "Passed!" );
//
//			b.translate( new Vector3( 2, 2, 2 ) );
//			assertTrue( ! a.intersectsBox( b ), "Passed!" );
//			assertTrue( ! b.intersectsBox( a ), "Passed!" );
//			assertTrue( ! b.intersectsBox( c ), "Passed!" );
//
//		}
//
//		@Test
//		public void intersectsSphere() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Sphere b = new Sphere( zero3.clone(), 1 );
//
//			assertTrue( a.intersectsSphere( b ), "Passed!" );
//
//			b.translate( new Vector3( 2, 2, 2 ) );
//			assertTrue( ! a.intersectsSphere( b ), "Passed!" );
//
//		}
//
//		@Test
//		public void intersectsPlane() {
//
//			Box3 a = new Box3( zero3.clone(), one3.clone() );
//			Plane b = new Plane( new Vector3( 0, 1, 0 ), 1 );
//			Plane c = new Plane( new Vector3( 0, 1, 0 ), 1.25 );
//			Plane d = new Plane( new Vector3( 0, - 1, 0 ), 1.25 );
//			Plane e = new Plane( new Vector3( 0, 1, 0 ), 0.25 );
//			Plane f = new Plane( new Vector3( 0, 1, 0 ), - 0.25 );
//			Plane g = new Plane( new Vector3( 0, 1, 0 ), - 0.75 );
//			Plane h = new Plane( new Vector3( 0, 1, 0 ), - 1 );
//			Plane i = new Plane( new Vector3( 1, 1, 1 ).normalize(), - 1.732 );
//			Plane j = new Plane( new Vector3( 1, 1, 1 ).normalize(), - 1.733 );
//
//			assertTrue( ! a.intersectsPlane( b ), "Passed!" );
//			assertTrue( ! a.intersectsPlane( c ), "Passed!" );
//			assertTrue( ! a.intersectsPlane( d ), "Passed!" );
//			assertTrue( ! a.intersectsPlane( e ), "Passed!" );
//			assertTrue( a.intersectsPlane( f ), "Passed!" );
//			assertTrue( a.intersectsPlane( g ), "Passed!" );
//			assertTrue( a.intersectsPlane( h ), "Passed!" );
//			assertTrue( a.intersectsPlane( i ), "Passed!" );
//			assertTrue( ! a.intersectsPlane( j ), "Passed!" );
//
//		}
//
//		@Test
//		public void intersectsTriangle() {
//
//			Box3 a = new Box3( one3.clone(), two3.clone() );
//			Triangle b = new Triangle( new Vector3( 1.5, 1.5, 2.5 ), new Vector3( 2.5, 1.5, 1.5 ), new Vector3( 1.5, 2.5, 1.5 ) );
//			Triangle c = new Triangle( new Vector3( 1.5, 1.5, 3.5 ), new Vector3( 3.5, 1.5, 1.5 ), new Vector3( 1.5, 1.5, 1.5 ) );
//			Triangle d = new Triangle( new Vector3( 1.5, 1.75, 3 ), new Vector3( 3, 1.75, 1.5 ), new Vector3( 1.5, 2.5, 1.5 ) );
//			Triangle e = new Triangle( new Vector3( 1.5, 1.8, 3 ), new Vector3( 3, 1.8, 1.5 ), new Vector3( 1.5, 2.5, 1.5 ) );
//			Triangle f = new Triangle( new Vector3( 1.5, 2.5, 3 ), new Vector3( 3, 2.5, 1.5 ), new Vector3( 1.5, 2.5, 1.5 ) );
//
//			assertTrue( a.intersectsTriangle( b ), "Passed!" );
//			assertTrue( a.intersectsTriangle( c ), "Passed!" );
//			assertTrue( a.intersectsTriangle( d ), "Passed!" );
//			assertTrue( ! a.intersectsTriangle( e ), "Passed!" );
//			assertTrue( ! a.intersectsTriangle( f ), "Passed!" );
//
//		}
//
//		@Test
//		public void clampPoint() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( one3.clone().negate(), one3.clone() );
//			Vector3 point = new Vector3();
//
//			a.clampPoint( zero3, point );
//			assertTrue( point.equals( zero3 ), "Passed!" );
//			a.clampPoint( one3, point );
//			assertTrue( point.equals( zero3 ), "Passed!" );
//			a.clampPoint( one3.clone().negate(), point );
//			assertTrue( point.equals( zero3 ), "Passed!" );
//
//			b.clampPoint( new Vector3( 2, 2, 2 ), point );
//			assertTrue( point.equals( one3 ), "Passed!" );
//			b.clampPoint( one3, point );
//			assertTrue( point.equals( one3 ), "Passed!" );
//			b.clampPoint( zero3, point );
//			assertTrue( point.equals( zero3 ), "Passed!" );
//			b.clampPoint( one3.clone().negate(), point );
//			assertTrue( point.equals( one3.clone().negate() ), "Passed!" );
//			b.clampPoint( new Vector3( - 2, - 2, - 2 ), point );
//			assertTrue( point.equals( one3.clone().negate() ), "Passed!" );
//
//		}
//
//		@Test
//		public void distanceToPoint() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( one3.clone().negate(), one3.clone() );
//
//			assertTrue( a.distanceToPoint( new Vector3( 0, 0, 0 ) ) == 0, "Passed!" );
//			assertTrue( a.distanceToPoint( new Vector3( 1, 1, 1 ) ) == Math.sqrt( 3 ), "Passed!" );
//			assertTrue( a.distanceToPoint( new Vector3( - 1, - 1, - 1 ) ) == Math.sqrt( 3 ), "Passed!" );
//
//			assertTrue( b.distanceToPoint( new Vector3( 2, 2, 2 ) ) == Math.sqrt( 3 ), "Passed!" );
//			assertTrue( b.distanceToPoint( new Vector3( 1, 1, 1 ) ) == 0, "Passed!" );
//			assertTrue( b.distanceToPoint( new Vector3( 0, 0, 0 ) ) == 0, "Passed!" );
//			assertTrue( b.distanceToPoint( new Vector3( - 1, - 1, - 1 ) ) == 0, "Passed!" );
//			assertTrue( b.distanceToPoint( new Vector3( - 2, - 2, - 2 ) ) == Math.sqrt( 3 ), "Passed!" );
//
//		}
//
//		@Test
//		public void getBoundingSphere() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//			Sphere sphere = new Sphere();
//
//			assertTrue( a.getBoundingSphere( sphere ).equals( new Sphere( zero3, 0 ) ), "Passed!" );
//			assertTrue( b.getBoundingSphere( sphere ).equals( new Sphere( one3.clone().multiplyScalar( 0.5 ), Math.sqrt( 3 ) * 0.5 ) ), "Passed!" );
//			assertTrue( c.getBoundingSphere( sphere ).equals( new Sphere( zero3, Math.sqrt( 12 ) * 0.5 ) ), "Passed!" );
//
//		}
//
//		@Test
//		public void intersect() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//
//			assertTrue( a.clone().intersect( a ).equals( a ), "Passed!" );
//			assertTrue( a.clone().intersect( b ).equals( a ), "Passed!" );
//			assertTrue( b.clone().intersect( b ).equals( b ), "Passed!" );
//			assertTrue( a.clone().intersect( c ).equals( a ), "Passed!" );
//			assertTrue( b.clone().intersect( c ).equals( b ), "Passed!" );
//			assertTrue( c.clone().intersect( c ).equals( c ), "Passed!" );
//
//		}
//
//		@Test
//		public void union() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//
//			assertTrue( a.clone().union( a ).equals( a ), "Passed!" );
//			assertTrue( a.clone().union( b ).equals( b ), "Passed!" );
//			assertTrue( a.clone().union( c ).equals( c ), "Passed!" );
//			assertTrue( b.clone().union( c ).equals( c ), "Passed!" );
//
//		}
//
//		@Test
//		public void applyMatrix4() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//			Box3 d = new Box3( one3.clone().negate(), zero3.clone() );
//
//			Matrix4 m = new Matrix4().makeTranslation( 1, - 2, 1 );
//			Vector3 t1 = new Vector3( 1, - 2, 1 );
//
//			assertTrue( compareBox( a.clone().applyMatrix4( m ), a.clone().translate( t1 ) ), "Passed!" );
//			assertTrue( compareBox( b.clone().applyMatrix4( m ), b.clone().translate( t1 ) ), "Passed!" );
//			assertTrue( compareBox( c.clone().applyMatrix4( m ), c.clone().translate( t1 ) ), "Passed!" );
//			assertTrue( compareBox( d.clone().applyMatrix4( m ), d.clone().translate( t1 ) ), "Passed!" );
//
//		}
//
//		@Test
//		public void translate() {
//
//			Box3 a = new Box3( zero3.clone(), zero3.clone() );
//			Box3 b = new Box3( zero3.clone(), one3.clone() );
//			Box3 c = new Box3( one3.clone().negate(), one3.clone() );
//			Box3 d = new Box3( one3.clone().negate(), zero3.clone() );
//
//			assertTrue( a.clone().translate( one3 ).equals( new Box3( one3, one3 ) ), "Passed!" );
//			assertTrue( a.clone().translate( one3 ).translate( one3.clone().negate() ).equals( a ), "Passed!" );
//			assertTrue( d.clone().translate( one3 ).equals( b ), "Passed!" );
//			assertTrue( b.clone().translate( one3.clone().negate() ).equals( d ), "Passed!" );
//
//		}
//
//		@Test
//		public void equals() {
//
//			Box3 a = new Box3();
//			Box3 b = new Box3();
//			assertTrue( b.equals( a ), "Passed!" );
//			assertTrue( a.equals( b ), "Passed!" );
//
//			a = new Box3( one3, two3 );
//			b = new Box3( one3, two3 );
//			assertTrue( b.equals( a ), "Passed!" );
//			assertTrue( a.equals( b ), "Passed!" );
//
//			a = new Box3( one3, two3 );
//			b = a.clone();
//			assertTrue( b.equals( a ), "Passed!" );
//			assertTrue( a.equals( b ), "Passed!" );
//
//			a = new Box3( one3, two3 );
//			b = new Box3( one3, one3 );
//			assertTrue( ! b.equals( a ), "Passed!" );
//			assertTrue( ! a.equals( b ), "Passed!" );
//
//			a = new Box3();
//			b = new Box3( one3, one3 );
//			assertTrue( ! b.equals( a ), "Passed!" );
//			assertTrue( ! a.equals( b ), "Passed!" );
//
//		}
//
//	}
//
//}
