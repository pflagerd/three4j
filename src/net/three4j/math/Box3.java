package net.three4j.math;

import net.three4j.core.BufferAttribute;
import net.three4j.core.Geometry;
import net.three4j.core.Object3D;

import static net.three4j.THREE.console;

public class Box3 {

	public Box3() {
	}

	public Box3(Vector3 min, Vector3 max) {
		_min = min != null ? min : new Vector3();
		_max = max != null ? max : new Vector3();
	}

	private Vector3 _min = new Vector3();

	public Vector3 min() {
		return _min;
	}

	public Box3 min(Vector3 min) {
		this._min = min;
		return this;
	}

	private Vector3 _max = new Vector3();

	public Vector3 max() {
		return _max;
	}

	public Box3 max(Vector3 max) {
		this._max = max;
		return this;
	}

	public Box3 set(Vector3 min, Vector3 max) {

		this._min.copy(min);
		this._max.copy(max);

		return this;

	}

	public Box3 setFromArray(double[] array) {

		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double minZ = Double.POSITIVE_INFINITY;

		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		double maxZ = Double.NEGATIVE_INFINITY;

		for (int i = 0, l = array.length; i < l; i += 3) {

			double x = array[i];
			double y = array[i + 1];
			double z = array[i + 2];

			if (x < minX)
				minX = x;
			if (y < minY)
				minY = y;
			if (z < minZ)
				minZ = z;

			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			if (z > maxZ)
				maxZ = z;

		}

		this._min.set(minX, minY, minZ);
		this._max.set(maxX, maxY, maxZ);

		return this;

	}

	public Box3 setFromBufferAttribute(BufferAttribute attribute) {

		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double minZ = Double.POSITIVE_INFINITY;

		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		double maxZ = Double.NEGATIVE_INFINITY;

		for (int i = 0, l = attribute.count(); i < l; i++) {

			double x = attribute.getX(i);
			double y = attribute.getY(i);
			double z = attribute.getZ(i);

			if (x < minX)
				minX = x;
			if (y < minY)
				minY = y;
			if (z < minZ)
				minZ = z;

			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			if (z > maxZ)
				maxZ = z;

		}

		this._min.set(minX, minY, minZ);
		this._max.set(maxX, maxY, maxZ);

		return this;

	}

	public Box3 setFromPoints( Vector3[] points ) {

		this.makeEmpty();

		for ( int i = 0, il = points.length; i < il; i ++ ) {

			this.expandByPoint( points[ i ] );

		}

		return this;

	}

	public Box3 setFromCenterAndSize( Vector3 center, Vector3 size ) {

		Vector3 halfSize = _vector.copy( size ).multiplyScalar( 0.5 );

		this._min.copy( center ).sub( halfSize );
		this._max.copy( center ).add( halfSize );

		return this;

	}

//	public Box3 setFromObject( Object3D object ) {
//
//		this.makeEmpty();
//
//		return this.expandByObject( object );
//
//	}

	public Box3 $clone() {

		return new Box3().copy(this);

	}

	public Box3 copy(Box3 box) {

		this._min.copy(box._min);
		this._max.copy(box._max);

		return this;

	}

	public Box3 makeEmpty() {

		this._min.set(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		this._max.set(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

		return this;

	}

	public boolean isEmpty() {

		// this is a more robust check for empty than ( volume <= 0 ) because volume can get positive with two negative axes

		return this._max.x() < this._min.x() || this._max.y() < this._min.y() || this._max.z() < this._min.z();

	}

	public Vector3 getCenter() {
		return getCenter(new Vector3());
	}
	
	public Vector3 getCenter( Vector3 target ) {		

		if ( target == null ) {

			console.warn( "THREE.Box3: Vector3 target should not be null" );
			target = new Vector3();

		}

		return target.addVectors( this._min, this._max ).multiplyScalar( 0.5 );

	}

	public Vector3 getSize( Vector3 target ) {

		if ( target == null ) {

			console.warn( "THREE.Box3.getSize(null) is not valid" );
			target = new Vector3();

		}

		return target.subVectors( this._max, this._min );

	}

	public Box3 expandByPoint( Vector3 point ) {

		this._min.min( point );
		this._max.max( point );

		return this;

	}

	public Box3 expandByVector( Vector3 vector ) {

		this._min.sub( vector );
		this._max.add( vector );

		return this;

	}

	public Box3 expandByScalar( double scalar ) {

		this._min.addScalar( -scalar );
		this._max.addScalar( scalar );

		return this;

	}

//	public Box3 expandByObject( Object3D object ) {
//
//		// Computes the world-axis-aligned bounding box of an object (including its children),
//		// accounting for both the object's, and children's, world transforms
//
//		object.updateWorldMatrix( false, false );
//
//		Geometry geometry = object.geometry;
//
//		if ( geometry !== undefined ) {
//
//			if ( geometry.boundingBox === null ) {
//
//				geometry.computeBoundingBox();
//
//			}
//
//			_box.copy( geometry.boundingBox );
//			_box.applyMatrix4( object.matrixWorld );
//
//			this.union( _box );
//
//		}
//
//		const children = object.children;
//
//		for ( let i = 0, l = children.length; i < l; i ++ ) {
//
//			this.expandByObject( children[ i ] );
//
//		}
//
//		return this;
//
//	}

	public boolean containsPoint( Vector3 point ) {

		return !(point.x() < this._min.x() || point.x() > this._max.x() ||
			point.y() < this._min.y() || point.y() > this._max.y() ||
			point.z() < this._min.z() || point.z() > this._max.z());

	}

	public boolean containsBox( Box3 box ) {

		return this._min.x() <= box._min.x() && box._max.x() <= this._max.x() &&
			this._min.y() <= box._min.y() && box._max.y() <= this._max.y() &&
			this._min.z() <= box._min.z() && box._max.z() <= this._max.z();

	}

	public Vector3 getParameter( Vector3 point, Vector3 target ) {

		// This can potentially have a divide by zero if the box
		// has a size dimension of 0.

		if ( target == null ) {

			console.warn( "THREE.Box3.getParameter(point, null) target is not allowed" );
			target = new Vector3();

		}

		return target.set(
			( point.x() - this._min.x() ) / ( this._max.x() - this._min.x() ),
			( point.y() - this._min.y() ) / ( this._max.y() - this._min.y() ),
			( point.z() - this._min.z() ) / ( this._max.z() - this._min.z() )
		);

	}

	public boolean intersectsBox( Box3 box ) {

		// using 6 splitting planes to rule out intersections.
		return !(box._max.x() < this._min.x() || box._min.x() > this._max.x() ||
			box._max.y() < this._min.y() || box._min.y() > this._max.y() ||
			box._max.z() < this._min.z() || box._min.z() > this._max.z());

	}

	public boolean intersectsSphere( Sphere sphere ) {

		// Find the point on the AABB closest to the sphere center.
		this.clampPoint( sphere.center(), _vector );

		// If that point is inside the sphere, the AABB and sphere intersect.
		return _vector.distanceToSquared( sphere.center() ) <= ( sphere.radius() * sphere.radius() );

	}

	public boolean intersectsPlane( Plane plane ) {

		// We compute the minimum and maximum dot product values. If those values
		// are on the same side (back or front) of the plane, then there is no intersection.

		double min, max;

		if ( plane.normal().x() > 0 ) {

			min = plane.normal().x() * this._min.x();
			max = plane.normal().x() * this._max.x();

		} else {

			min = plane.normal().x() * this._max.x();
			max = plane.normal().x() * this._min.x();

		}

		if ( plane.normal().y() > 0 ) {

			min += plane.normal().y() * this._min.y();
			max += plane.normal().y() * this._max.y();

		} else {

			min += plane.normal().y() * this._max.y();
			max += plane.normal().y() * this._min.y();

		}

		if ( plane.normal().z() > 0 ) {

			min += plane.normal().z() * this._min.z();
			max += plane.normal().z() * this._max.z();

		} else {

			min += plane.normal().z() * this._max.z();
			max += plane.normal().z() * this._min.z();

		}

		return ( min <= - plane.constant() && max >= - plane.constant() );

	}

//	public double intersectsTriangle( triangle ) {
//
//		if ( this.isEmpty() ) {
//
//			return false;
//
//		}
//
//		// compute box center and extents
//		this.getCenter( _center );
//		_extents.subVectors( this._max, _center );
//
//		// translate triangle to aabb origin
//		_v0.subVectors( triangle.a, _center );
//		_v1.subVectors( triangle.b, _center );
//		_v2.subVectors( triangle.c, _center );
//
//		// compute edge vectors for triangle
//		_f0.subVectors( _v1, _v0 );
//		_f1.subVectors( _v2, _v1 );
//		_f2.subVectors( _v0, _v2 );
//
//		// test against axes that are given by cross product combinations of the edges of the triangle and the edges of the aabb
//		// make an axis testing of each of the 3 sides of the aabb against each of the 3 sides of the triangle = 9 axis of separation
//		// axis_ij = u_i x f_j (u0, u1, u2 = face normals of aabb = x,y,z axes vectors since aabb is axis aligned)
//		let axes = [
//			0, - _f0.z, _f0.y, 0, - _f1.z, _f1.y, 0, - _f2.z, _f2.y,
//			_f0.z, 0, - _f0.x, _f1.z, 0, - _f1.x, _f2.z, 0, - _f2.x,
//			- _f0.y, _f0.x, 0, - _f1.y, _f1.x, 0, - _f2.y, _f2.x, 0
//		];
//		if ( ! satForAxes( axes, _v0, _v1, _v2, _extents ) ) {
//
//			return false;
//
//		}
//
//		// test 3 face normals from the aabb
//		axes = [ 1, 0, 0, 0, 1, 0, 0, 0, 1 ];
//		if ( ! satForAxes( axes, _v0, _v1, _v2, _extents ) ) {
//
//			return false;
//
//		}
//
//		// finally testing the face normal of the triangle
//		// use already existing triangle edge vectors here
//		_triangleNormal.crossVectors( _f0, _f1 );
//		axes = [ _triangleNormal.x, _triangleNormal.y, _triangleNormal.z ];
//
//		return satForAxes( axes, _v0, _v1, _v2, _extents );
//
//	}
//
	public Vector3 clampPoint( Vector3 point, Vector3 target ) {

		if ( target == null ) {

			console.warn( "THREE.Box3: .clampPoint() target is now required" );
			target = new Vector3();

		}

		return target.copy( point ).clamp( this._min, this._max );

	}

//	public double distanceToPoint( point ) {
//
//		const clampedPoint = _vector.copy( point ).clamp( this._min, this._max );
//
//		return clampedPoint.sub( point ).length();
//
//	}
//
//	public double getBoundingSphere( target ) {
//
//		if ( target === undefined ) {
//
//			console.error( 'THREE.Box3: .getBoundingSphere() target is now required' );
//			//target = new Sphere(); // removed to avoid cyclic dependency
//
//		}
//
//		this.getCenter( target.center );
//
//		target.radius = this.getSize( _vector ).length() * 0.5;
//
//		return target;
//
//	}
//
//	public Box3 intersect( box ) {
//
//		this._min.max( box._min );
//		this._max.min( box._max );
//
//		// ensure that if there is no overlap, the result is fully empty, not slightly empty with non-inf/+inf values that will cause subsequence intersects to erroneously return valid values.
//		if ( this.isEmpty() ) this.makeEmpty();
//
//		return this;
//
//	}
//
//	public Box3 union( box ) {
//
//		this._min.min( box._min );
//		this._max.max( box._max );
//
//		return this;
//
//	}
//
	public Box3 applyMatrix4( Matrix4 matrix ) {

		// transform of empty box is an empty box.
		if ( this.isEmpty() ) return this;

		// NOTE: I am using a binary pattern to specify all 2^3 combinations below
		_points[ 0 ].set( this._min.x(), this._min.y(), this._min.z() ).applyMatrix4( matrix ); // 000
		_points[ 1 ].set( this._min.x(), this._min.y(), this._max.z() ).applyMatrix4( matrix ); // 001
		_points[ 2 ].set( this._min.x(), this._max.y(), this._min.z() ).applyMatrix4( matrix ); // 010
		_points[ 3 ].set( this._min.x(), this._max.y(), this._max.z() ).applyMatrix4( matrix ); // 011
		_points[ 4 ].set( this._max.x(), this._min.y(), this._min.z() ).applyMatrix4( matrix ); // 100
		_points[ 5 ].set( this._max.x(), this._min.y(), this._max.z() ).applyMatrix4( matrix ); // 101
		_points[ 6 ].set( this._max.x(), this._max.y(), this._min.z() ).applyMatrix4( matrix ); // 110
		_points[ 7 ].set( this._max.x(), this._max.y(), this._max.z() ).applyMatrix4( matrix ); // 111

		this.setFromPoints( _points );

		return this;

	}

//	public Box3 translate( offset ) {
//
//		this._min.add( offset );
//		this._max.add( offset );
//
//		return this;
//
//	}

	public boolean equals( Box3 box ) {

		return box._min.equals( this._min ) && box._max.equals( this._max );

	}

//}
//
//public satForAxes( axes, v0, v1, v2, extents ) {
//
//	for ( let i = 0, j = axes.length - 3; i <= j; i += 3 ) {
//
//		_testAxis.fromArray( axes, i );
//		// project the aabb onto the seperating axis
//		const r = extents.x * Math.abs( _testAxis.x ) + extents.y * Math.abs( _testAxis.y ) + extents.z * Math.abs( _testAxis.z );
//		// project all 3 vertices of the triangle onto the seperating axis
//		const p0 = v0.dot( _testAxis );
//		const p1 = v1.dot( _testAxis );
//		const p2 = v2.dot( _testAxis );
//		// actual test, basically see if either of the most extreme of the triangle points intersects r
//		if ( Math.max( - Math.max( p0, p1, p2 ), Math.min( p0, p1, p2 ) ) > r ) {
//
//			// points of the projected triangle are outside the projected half-length of the aabb
//			// the axis is seperating and we can exit
//			return false;
//
//		}
//
//	}
//
//	return true;
//}

	private static final Vector3[] _points = {
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3(),
		/*@__PURE__*/ new Vector3()
	};
	
	private static final Vector3 _vector = /*@__PURE__*/ new Vector3();
	
	private static final Box3 _box = /*@__PURE__*/ new Box3();
	
	// triangle centered vertices
	
	private static final Vector3 _v0 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _v1 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _v2 = /*@__PURE__*/ new Vector3();
	
	// triangle edge vectors
	
	private static final Vector3 _f0 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _f1 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _f2 = /*@__PURE__*/ new Vector3();
	
	private static final Vector3 _center = /*@__PURE__*/ new Vector3();
	private static final Vector3 _extents = /*@__PURE__*/ new Vector3();
	private static final Vector3 _triangleNormal = /*@__PURE__*/ new Vector3();
	private static final Vector3 _testAxis = /*@__PURE__*/ new Vector3();
}

