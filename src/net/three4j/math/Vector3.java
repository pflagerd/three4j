package net.three4j.math;

//import { _Math } from './Math.js';
//import { Matrix4 } from './Matrix4.js';
//import { Quaternion } from './Quaternion.js';
import static net.three4j.THREE.console;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

import net.three4j.cameras.Camera;
import net.three4j.core.BufferAttribute;
import net.three4j.core.InterleavedBufferAttribute;

/**
 * @author mrdoob / http://mrdoob.com/
 * @author kile / http://kile.stravaganza.org/
 * @author philogb / http://blog.thejit.org/
 * @author mikael emtinger / http://gomo.se/
 * @author egraether / http://egraether.com/
 * @author WestLangley / http://github.com/WestLangley
 */

public class Vector3 {
	public double _x;
	public double _y;
	public double _z;

	public final boolean isVector3 = true;

	public Vector3() {
		this._x = this._y = this._z = 0.0;
	}

	public Vector3(double x, double y, double z) {
		this._x = x;
		this._y = y;
		this._z = z;
	}

	public Vector3(final Vector3 vector3) {
		this._x = vector3._x;
		this._y = vector3._y;
		this._z = vector3._z;
	}

	public double x() {
		  return _x;
		}

		public Vector3 x(double x) {
		  this._x = x;
		  return this;
		}


		public double y() {
		  return _y;
		}

		public Vector3 y(double y) {
		  this._y = y;
		  return this;
		}


		public double z() {
		  return _z;
		}

		public Vector3 z(double z) {
		  this._z = z;
		  return this;
		}

	public Vector3 set( double x, double y) {
		return set(x, y, this._z);
	}

	public Vector3 set( double x, double y, double z ) {

		this._x = x;
		this._y = y;
		this._z = z;

		return this;

	}

	public Vector3 setScalar( double scalar ) {

		this._x = scalar;
		this._y = scalar;
		this._z = scalar;

		return this;

	}

	public Vector3 setX( double x ) {

		this._x = x;

		return this;

	}

	public Vector3 setY( double y ) {

		this._y = y;

		return this;

	}

	public Vector3 setZ( double z ) {

		this._z = z;

		return this;

	}

	public Vector3 setComponent( int index, double value ) {

		switch ( index ) {
			case 0: this._x = value; break;
			case 1: this._y = value; break;
			case 2: this._z = value; break;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

		return this;

	}

	public double getComponent( int index ) {

		switch ( index ) {

			case 0: return this._x;
			case 1: return this._y;
			case 2: return this._z;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

	}

	public Vector3 clone() {

		return new Vector3( this._x, this._y, this._z );

	}

	public Vector3 copy( Vector3 v ) {

		this._x = v._x;
		this._y = v._y;
		this._z = v._z;

		return this;

	}

	public Vector3 add( Vector3 v) {

		this._x += v._x;
		this._y += v._y;
		this._z += v._z;

		return this;

	}

	public Vector3 addScalar( double s ) {

		this._x += s;
		this._y += s;
		this._z += s;

		return this;

	}

	public Vector3 addVectors( Vector3 a, Vector3 b ) {

		this._x = a._x + b._x;
		this._y = a._y + b._y;
		this._z = a._z + b._z;

		return this;

	}

	public Vector3 addScaledVector( Vector3 v, double s ) {

		this._x += v._x * s;
		this._y += v._y * s;
		this._z += v._z * s;

		return this;

	}

	public Vector3 sub( Vector3 v ) {
		this._x -= v._x;
		this._y -= v._y;
		this._z -= v._z;

		return this;

	}

	public Vector3 subScalar( double s ) {

		this._x -= s;
		this._y -= s;
		this._z -= s;

		return this;

	}

	public Vector3 subVectors( Vector3 a, Vector3 b ) {

		this._x = a._x - b._x;
		this._y = a._y - b._y;
		this._z = a._z - b._z;

		return this;

	}

	public Vector3 multiply( Vector3 v ) {

		this._x *= v._x;
		this._y *= v._y;
		this._z *= v._z;

		return this;

	}

	public Vector3 multiplyScalar( double scalar ) {

		this._x *= scalar;
		this._y *= scalar;
		this._z *= scalar;

		return this;

	}

	public Vector3 multiplyVectors( Vector3 a, Vector3 b ) {

		this._x = a._x * b._x;
		this._y = a._y * b._y;
		this._z = a._z * b._z;

		return this;

	}

	public Vector3 applyEuler( Euler euler ) {

		if ( ! ( euler != null && euler.isEuler ) ) {

			console.error( "THREE.Vector3: .applyEuler() now expects an Euler rotation rather than a Vector3 and order." );

		}

		return this.applyQuaternion( _quaternion.setFromEuler( euler ) );

	}

	public Vector3 applyAxisAngle( Vector3 axis, double angle ) {

		return this.applyQuaternion( _quaternion.setFromAxisAngle( axis, angle ) );

	}

	public Vector3 applyMatrix3( Matrix3 m ) {

		final double x = this._x, y = this._y, z = this._z;
		final double[] e = m.elements;

		this._x = e[ 0 ] * x + e[ 3 ] * y + e[ 6 ] * z;
		this._y = e[ 1 ] * x + e[ 4 ] * y + e[ 7 ] * z;
		this._z = e[ 2 ] * x + e[ 5 ] * y + e[ 8 ] * z;

		return this;

	}

	public Vector3 applyNormalMatrix( Matrix3 m ) {

		return this.applyMatrix3( m ).normalize();

	}

	public Vector3 applyMatrix4( Matrix4 m ) {

		final double x = this._x, y = this._y, z = this._z;
		final double[] e = m.elements;

		final double w = 1 / ( e[ 3 ] * x + e[ 7 ] * y + e[ 11 ] * z + e[ 15 ] );

		this._x = ( e[ 0 ] * x + e[ 4 ] * y + e[ 8 ] * z + e[ 12 ] ) * w;
		this._y = ( e[ 1 ] * x + e[ 5 ] * y + e[ 9 ] * z + e[ 13 ] ) * w;
		this._z = ( e[ 2 ] * x + e[ 6 ] * y + e[ 10 ] * z + e[ 14 ] ) * w;

		return this;
	}

	public Vector3 applyQuaternion( Quaternion q ) {

		final double x = this._x, y = this._y, z = this._z;
		final double qx = q.x(), qy = q.y(), qz = q.z(), qw = q.w();

		// calculate quat * vector

		final double ix = qw * x + qy * z - qz * y;
		final double iy = qw * y + qz * x - qx * z;
		final double iz = qw * z + qx * y - qy * x;
		final double iw = - qx * x - qy * y - qz * z;

		// calculate result * inverse quat

		this._x = ix * qw + iw * - qx + iy * - qz - iz * - qy;
		this._y = iy * qw + iw * - qy + iz * - qx - ix * - qz;
		this._z = iz * qw + iw * - qz + ix * - qy - iy * - qx;

		return this;

	}

	public Vector3 project( Camera camera ) {

		return this.applyMatrix4( camera.matrixWorldInverse() ).applyMatrix4( camera.projectionMatrix() );

	}

	public Vector3 unproject( Camera camera ) {

		return this.applyMatrix4( camera.projectionMatrixInverse() ).applyMatrix4( camera.matrixWorld() );

	}

	public Vector3 transformDirection( Matrix4 m ) {

		// input: THREE.Matrix4 affine matrix
		// vector interpreted as a direction

		final double x = this._x, y = this._y, z = this._z;
		final double[] e = m.elements;

		this._x = e[ 0 ] * x + e[ 4 ] * y + e[ 8 ] * z;
		this._y = e[ 1 ] * x + e[ 5 ] * y + e[ 9 ] * z;
		this._z = e[ 2 ] * x + e[ 6 ] * y + e[ 10 ] * z;

		return this.normalize();

	}

	public Vector3 divide( Vector3 v ) {

		this._x /= v._x;
		this._y /= v._y;
		this._z /= v._z;

		return this;

	}

	public Vector3 divideScalar( double scalar ) {
		if (scalar == 0.0 || scalar == -0.0) {
			console.warn("Vector3.divideScalar(0)");
			scalar = 1;
		}

		return this.multiplyScalar( 1 / scalar );

	}

	public Vector3 min( Vector3 v ) {

		this._x = Math.min( this._x, v._x );
		this._y = Math.min( this._y, v._y );
		this._z = Math.min( this._z, v._z );

		return this;

	}

	public Vector3 max( Vector3 v ) {

		this._x = Math.max( this._x, v._x );
		this._y = Math.max( this._y, v._y );
		this._z = Math.max( this._z, v._z );

		return this;

	}

	public Vector3 clamp( Vector3 min, Vector3 max ) {

		// assumes min < max, componentwise

		this._x = Math.max( min._x, Math.min( max._x, this._x ) );
		this._y = Math.max( min._y, Math.min( max._y, this._y ) );
		this._z = Math.max( min._z, Math.min( max._z, this._z ) );

		return this;

	}

	public Vector3 clampScalar( double minVal, double maxVal ) {

		this._x = Math.max( minVal, Math.min( maxVal, this._x ) );
		this._y = Math.max( minVal, Math.min( maxVal, this._y ) );
		this._z = Math.max( minVal, Math.min( maxVal, this._z ) );

		return this;

	}

	public Vector3 clampLength( double min, double max ) {

		final double length = this.length();

		return this.divideScalar( length ).multiplyScalar( Math.max( min, Math.min( max, length ) ) );

	}

	public Vector3 floor() {

		this._x = Math.floor( this._x );
		this._y = Math.floor( this._y );
		this._z = Math.floor( this._z );

		return this;

	}

	public Vector3 ceil() {

		this._x = Math.ceil( this._x );
		this._y = Math.ceil( this._y );
		this._z = Math.ceil( this._z );

		return this;

	}

	public Vector3 round() {

		this._x = Math.round( this._x );
		this._y = Math.round( this._y );
		this._z = Math.round( this._z );

		return this;

	}

	public Vector3 roundToZero() {

		this._x = ( this._x < 0 ) ? Math.ceil( this._x ) : Math.floor( this._x );
		this._y = ( this._y < 0 ) ? Math.ceil( this._y ) : Math.floor( this._y );
		this._z = ( this._z < 0 ) ? Math.ceil( this._z ) : Math.floor( this._z );

		return this;

	}

	public Vector3 negate() {

		this._x = - this._x;
		this._y = - this._y;
		this._z = - this._z;

		return this;

	}

	public double dot( Vector3 v ) {
		return this._x * v._x + this._y * v._y + this._z * v._z;

	}

	public double lengthSquared() {
		return lengthSq();
	}

	public double quadrature() {
		return lengthSq();
	}

	public double lengthSq() {

		return this._x * this._x + this._y * this._y + this._z * this._z;

	}

	public double length() {

		return Math.sqrt( this._x * this._x + this._y * this._y + this._z * this._z );

	}

	public double manhattanLength() {

		return Math.abs( this._x ) + Math.abs( this._y ) + Math.abs( this._z );

	}

	public Vector3 normalize() {

		return this.divideScalar( this.length() );

	}

	public Vector3 setLength( double length ) {

		return this.normalize().multiplyScalar( length );

	}

	public Vector3 lerp( Vector3 v, double alpha ) {

		this._x += ( v._x - this._x ) * alpha;
		this._y += ( v._y - this._y ) * alpha;
		this._z += ( v._z - this._z ) * alpha;

		return this;

	}

	public Vector3 lerpVectors( Vector3 v1, Vector3 v2, double alpha ) {

		this._x = v1._x + ( v2._x - v1._x ) * alpha;
		this._y = v1._y + ( v2._y - v1._y ) * alpha;
		this._z = v1._z + ( v2._z - v1._z ) * alpha;

		return this;

	}

	public Vector3 cross( Vector3 v) {
		return this.crossVectors( this, v );
	}

	public Vector3 crossVectors( Vector3 a, Vector3 b ) {

		final double ax = a._x, ay = a._y, az = a._z;
		final double bx = b._x, by = b._y, bz = b._z;

		this._x = ay * bz - az * by;
		this._y = az * bx - ax * bz;
		this._z = ax * by - ay * bx;

		return this;

	}

	public Vector3 projectOnVector( Vector3 v ) {

		final double denominator = v.lengthSq();

		if ( denominator == 0 )
			return this.set( 0, 0, 0 );

		final double scalar = v.dot( this ) / denominator;

		return this.copy( v ).multiplyScalar( scalar );

	}

	public Vector3 projectOnPlane( Vector3 planeNormal ) {

		_vector.copy( this ).projectOnVector( planeNormal );

		return this.sub( _vector );

	}

	public Vector3 reflect( Vector3 normal ) {

		// reflect incident vector off plane orthogonal to normal
		// normal is assumed to have unit length

		return this.sub( _vector.copy( normal ).multiplyScalar( 2 * this.dot( normal ) ) );
	}

	public double angleTo( Vector3 v ) {

		final double denominator = Math.sqrt( this.lengthSq() * v.lengthSq() );

		if ( denominator == 0 )
			return Math.PI / 2;

		final double theta = this.dot( v ) / denominator;

		// clamp, to handle numerical problems

		return Math.acos( MathUtils.clamp( theta, - 1, 1 ) );
	}

	public double distanceTo( Vector3 v ) {

		return Math.sqrt( this.distanceToSquared( v ) );

	}

	public double distanceToSquared( Vector3 v ) {

		final double dx = this._x - v._x, dy = this._y - v._y, dz = this._z - v._z;

		return dx * dx + dy * dy + dz * dz;

	}

	public double manhattanDistanceTo( Vector3 v ) {

		return Math.abs( this._x - v._x ) + Math.abs( this._y - v._y ) + Math.abs( this._z - v._z );

	}

	public Vector3 setFromSpherical( Spherical s ) {

		return this.setFromSphericalCoords( s.radius(), s.phi(), s.theta() );

	}

	public Vector3 setFromSphericalCoords( double radius, double phi, double theta ) {

		final double sinPhiRadius = Math.sin( phi ) * radius;

		this._x = sinPhiRadius * Math.sin( theta );
		this._y = Math.cos( phi ) * radius;
		this._z = sinPhiRadius * Math.cos( theta );

		return this;

	}

	public Vector3 setFromCylindrical( Cylindrical c ) {

		return this.setFromCylindricalCoords( c.radius(), c.theta(), c.y() );

	}

	public Vector3 setFromCylindricalCoords( double radius, double theta, double y ) {

		this._x = radius * Math.sin( theta );
		this._y = y;
		this._z = radius * Math.cos( theta );

		return this;

	}

	public Vector3 setFromMatrixPosition( Matrix4 m ) {

		final double[] e = m.elements;

		this._x = e[ 12 ];
		this._y = e[ 13 ];
		this._z = e[ 14 ];

		return this;

	}

	public Vector3 setFromMatrixScale( Matrix4 m ) {

		final double sx = this.setFromMatrixColumn( m, 0 ).length();
		final double sy = this.setFromMatrixColumn( m, 1 ).length();
		final double sz = this.setFromMatrixColumn( m, 2 ).length();

		this._x = sx;
		this._y = sy;
		this._z = sz;

		return this;

	}

	public Vector3 setFromMatrixColumn( Matrix4 m, int index ) {

		return this.fromArray( m.elements, index * 4 );

	}

	public Vector3 setFromMatrix3Column( Matrix3 m, int index ) {

		return this.fromArray( m.elements, index * 3 );

	}

	public boolean equals( Vector3 v ) {

		return ( ( v._x == this._x ) && ( v._y == this._y ) && ( v._z == this._z ) );

	}

	public Vector3 fromArray( double[] array) {
		return fromArray(array, 0);
	}

	public Vector3 fromArray( double[] array, int offset ) {

		this._x = array[ offset ];
		this._y = array[ offset + 1 ];
		this._z = array[ offset + 2 ];

		return this;

	}

	public double[] toArray() {
		return toArray(new double[3]);
	}

	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray( double[] array, int offset ) {

		array[ offset ] = this._x;
		array[ offset + 1 ] = this._y;
		array[ offset + 2 ] = this._z;

		return array;

	}

	public Vector3 fromBufferAttribute( BufferAttribute attribute, int index ) {

		this._x = attribute.getX( index );
		this._y = attribute.getY( index );
		this._z = attribute.getZ( index );

		return this;

	}

	public Vector3 fromBufferAttribute( InterleavedBufferAttribute attribute, int index ) {

		this._x = attribute.getX( index );
		this._y = attribute.getY( index );
		this._z = attribute.getZ( index );

		return this;

	}

	public Vector3 random() {

		this._x = Math.random();
		this._y = Math.random();
		this._z = Math.random();

		return this;

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("isVector3");
		return sortedReflectionToStringBuilder.toString();
	}

	private final static Vector3 _vector = /*@__PURE__*/ new Vector3();
	private final static Quaternion _quaternion = /*@__PURE__*/ new Quaternion();
}
