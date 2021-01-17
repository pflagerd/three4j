package net.three4j.math;

//import { _Math } from './Math.js';
//import { Matrix4 } from './Matrix4.js';
//import { Quaternion } from './Quaternion.js';
import static net.three4j.THREE.console;

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
	public double x;
	public double y;
	public double z;
	
	public final boolean isVector3 = true;
	
	public Vector3() {
		this.x = this.y = this.z = 0.0;
	}
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(final Vector3 vector3) {
		this.x = vector3.x;
		this.y = vector3.y;
		this.z = vector3.z;
	}

	public Vector3 set( double x, double y) {
		return set(x, y, this.z);
	}
	
	public Vector3 set( double x, double y, double z ) {

		this.x = x;
		this.y = y;
		this.z = z;

		return this;

	}

	public Vector3 setScalar( double scalar ) {

		this.x = scalar;
		this.y = scalar;
		this.z = scalar;

		return this;

	}

	public Vector3 setX( double x ) {

		this.x = x;

		return this;

	}

	public Vector3 setY( double y ) {

		this.y = y;

		return this;

	}

	public Vector3 setZ( double z ) {

		this.z = z;

		return this;

	}

	public Vector3 setComponent( int index, double value ) {

		switch ( index ) {
			case 0: this.x = value; break;
			case 1: this.y = value; break;
			case 2: this.z = value; break;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

		return this;

	}

	public double getComponent( int index ) {

		switch ( index ) {

			case 0: return this.x;
			case 1: return this.y;
			case 2: return this.z;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

	}

	public Vector3 clone() {

		return new Vector3( this.x, this.y, this.z );

	}

	public Vector3 copy( Vector3 v ) {

		this.x = v.x;
		this.y = v.y;
		this.z = v.z;

		return this;

	}

	public Vector3 add( Vector3 v) {

		this.x += v.x;
		this.y += v.y;
		this.z += v.z;

		return this;

	}

	public Vector3 addScalar( double s ) {

		this.x += s;
		this.y += s;
		this.z += s;

		return this;

	}

	public Vector3 addVectors( Vector3 a, Vector3 b ) {

		this.x = a.x + b.x;
		this.y = a.y + b.y;
		this.z = a.z + b.z;

		return this;

	}

	public Vector3 addScaledVector( Vector3 v, double s ) {

		this.x += v.x * s;
		this.y += v.y * s;
		this.z += v.z * s;

		return this;

	}

	public Vector3 sub( Vector3 v ) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;

		return this;

	}

	public Vector3 subScalar( double s ) {

		this.x -= s;
		this.y -= s;
		this.z -= s;

		return this;

	}

	public Vector3 subVectors( Vector3 a, Vector3 b ) {

		this.x = a.x - b.x;
		this.y = a.y - b.y;
		this.z = a.z - b.z;

		return this;

	}

	public Vector3 multiply( Vector3 v ) {

		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;

		return this;

	}

	public Vector3 multiplyScalar( double scalar ) {

		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;

		return this;

	}

	public Vector3 multiplyVectors( Vector3 a, Vector3 b ) {

		this.x = a.x * b.x;
		this.y = a.y * b.y;
		this.z = a.z * b.z;

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

		final double x = this.x, y = this.y, z = this.z;
		final double[] e = m.elements;

		this.x = e[ 0 ] * x + e[ 3 ] * y + e[ 6 ] * z;
		this.y = e[ 1 ] * x + e[ 4 ] * y + e[ 7 ] * z;
		this.z = e[ 2 ] * x + e[ 5 ] * y + e[ 8 ] * z;

		return this;

	}

	public Vector3 applyNormalMatrix( Matrix3 m ) {

		return this.applyMatrix3( m ).normalize();

	}

	public Vector3 applyMatrix4( Matrix4 m ) {

		final double x = this.x, y = this.y, z = this.z;
		final double[] e = m.elements;

		final double w = 1 / ( e[ 3 ] * x + e[ 7 ] * y + e[ 11 ] * z + e[ 15 ] );

		this.x = ( e[ 0 ] * x + e[ 4 ] * y + e[ 8 ] * z + e[ 12 ] ) * w;
		this.y = ( e[ 1 ] * x + e[ 5 ] * y + e[ 9 ] * z + e[ 13 ] ) * w;
		this.z = ( e[ 2 ] * x + e[ 6 ] * y + e[ 10 ] * z + e[ 14 ] ) * w;

		return this;
	}

	public Vector3 applyQuaternion( Quaternion q ) {

		final double x = this.x, y = this.y, z = this.z;
		final double qx = q.x(), qy = q.y(), qz = q.z(), qw = q.w();

		// calculate quat * vector

		final double ix = qw * x + qy * z - qz * y;
		final double iy = qw * y + qz * x - qx * z;
		final double iz = qw * z + qx * y - qy * x;
		final double iw = - qx * x - qy * y - qz * z;

		// calculate result * inverse quat

		this.x = ix * qw + iw * - qx + iy * - qz - iz * - qy;
		this.y = iy * qw + iw * - qy + iz * - qx - ix * - qz;
		this.z = iz * qw + iw * - qz + ix * - qy - iy * - qx;

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

		final double x = this.x, y = this.y, z = this.z;
		final double[] e = m.elements;

		this.x = e[ 0 ] * x + e[ 4 ] * y + e[ 8 ] * z;
		this.y = e[ 1 ] * x + e[ 5 ] * y + e[ 9 ] * z;
		this.z = e[ 2 ] * x + e[ 6 ] * y + e[ 10 ] * z;

		return this.normalize();

	}

	public Vector3 divide( Vector3 v ) {

		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;

		return this;

	}

	public Vector3 divideScalar( double scalar ) {

		return this.multiplyScalar( 1 / scalar );

	}

	public Vector3 min( Vector3 v ) {

		this.x = Math.min( this.x, v.x );
		this.y = Math.min( this.y, v.y );
		this.z = Math.min( this.z, v.z );

		return this;

	}

	public Vector3 max( Vector3 v ) {

		this.x = Math.max( this.x, v.x );
		this.y = Math.max( this.y, v.y );
		this.z = Math.max( this.z, v.z );

		return this;

	}

	public Vector3 clamp( Vector3 min, Vector3 max ) {

		// assumes min < max, componentwise

		this.x = Math.max( min.x, Math.min( max.x, this.x ) );
		this.y = Math.max( min.y, Math.min( max.y, this.y ) );
		this.z = Math.max( min.z, Math.min( max.z, this.z ) );

		return this;

	}

	public Vector3 clampScalar( double minVal, double maxVal ) {

		this.x = Math.max( minVal, Math.min( maxVal, this.x ) );
		this.y = Math.max( minVal, Math.min( maxVal, this.y ) );
		this.z = Math.max( minVal, Math.min( maxVal, this.z ) );

		return this;

	}

	public Vector3 clampLength( double min, double max ) {

		final double length = this.length();

		return this.divideScalar( length ).multiplyScalar( Math.max( min, Math.min( max, length ) ) );

	}

	public Vector3 floor() {

		this.x = Math.floor( this.x );
		this.y = Math.floor( this.y );
		this.z = Math.floor( this.z );

		return this;

	}

	public Vector3 ceil() {

		this.x = Math.ceil( this.x );
		this.y = Math.ceil( this.y );
		this.z = Math.ceil( this.z );

		return this;

	}

	public Vector3 round() {

		this.x = Math.round( this.x );
		this.y = Math.round( this.y );
		this.z = Math.round( this.z );

		return this;

	}

	public Vector3 roundToZero() {

		this.x = ( this.x < 0 ) ? Math.ceil( this.x ) : Math.floor( this.x );
		this.y = ( this.y < 0 ) ? Math.ceil( this.y ) : Math.floor( this.y );
		this.z = ( this.z < 0 ) ? Math.ceil( this.z ) : Math.floor( this.z );

		return this;

	}

	public Vector3 negate() {

		this.x = - this.x;
		this.y = - this.y;
		this.z = - this.z;

		return this;

	}

	public double dot( Vector3 v ) {
		return this.x * v.x + this.y * v.y + this.z * v.z;

	}

	public double lengthSquared() {
		return lengthSq();
	}
	
	public double quadrature() {
		return lengthSq();
	}	
	
	public double lengthSq() {

		return this.x * this.x + this.y * this.y + this.z * this.z;

	}

	public double length() {

		return Math.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );

	}

	public double manhattanLength() {

		return Math.abs( this.x ) + Math.abs( this.y ) + Math.abs( this.z );

	}

	public Vector3 normalize() {

		return this.divideScalar( this.length() );

	}

	public Vector3 setLength( double length ) {

		return this.normalize().multiplyScalar( length );

	}

	public Vector3 lerp( Vector3 v, double alpha ) {

		this.x += ( v.x - this.x ) * alpha;
		this.y += ( v.y - this.y ) * alpha;
		this.z += ( v.z - this.z ) * alpha;

		return this;

	}

	public Vector3 lerpVectors( Vector3 v1, Vector3 v2, double alpha ) {

		this.x = v1.x + ( v2.x - v1.x ) * alpha;
		this.y = v1.y + ( v2.y - v1.y ) * alpha;
		this.z = v1.z + ( v2.z - v1.z ) * alpha;

		return this;

	}

	public Vector3 cross( Vector3 v) {
		return this.crossVectors( this, v );
	}

	public Vector3 crossVectors( Vector3 a, Vector3 b ) {

		final double ax = a.x, ay = a.y, az = a.z;
		final double bx = b.x, by = b.y, bz = b.z;

		this.x = ay * bz - az * by;
		this.y = az * bx - ax * bz;
		this.z = ax * by - ay * bx;

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

		final double dx = this.x - v.x, dy = this.y - v.y, dz = this.z - v.z;

		return dx * dx + dy * dy + dz * dz;

	}

	public double manhattanDistanceTo( Vector3 v ) {

		return Math.abs( this.x - v.x ) + Math.abs( this.y - v.y ) + Math.abs( this.z - v.z );

	}

	public Vector3 setFromSpherical( Spherical s ) {

		return this.setFromSphericalCoords( s.radius(), s.phi(), s.theta() );

	}

	public Vector3 setFromSphericalCoords( double radius, double phi, double theta ) {

		final double sinPhiRadius = Math.sin( phi ) * radius;

		this.x = sinPhiRadius * Math.sin( theta );
		this.y = Math.cos( phi ) * radius;
		this.z = sinPhiRadius * Math.cos( theta );

		return this;

	}

	public Vector3 setFromCylindrical( Cylindrical c ) {

		return this.setFromCylindricalCoords( c.radius(), c.theta(), c.y() );

	}

	public Vector3 setFromCylindricalCoords( double radius, double theta, double y ) {

		this.x = radius * Math.sin( theta );
		this.y = y;
		this.z = radius * Math.cos( theta );

		return this;

	}

	public Vector3 setFromMatrixPosition( Matrix4 m ) {

		final double[] e = m.elements;

		this.x = e[ 12 ];
		this.y = e[ 13 ];
		this.z = e[ 14 ];

		return this;

	}

	public Vector3 setFromMatrixScale( Matrix4 m ) {

		final double sx = this.setFromMatrixColumn( m, 0 ).length();
		final double sy = this.setFromMatrixColumn( m, 1 ).length();
		final double sz = this.setFromMatrixColumn( m, 2 ).length();

		this.x = sx;
		this.y = sy;
		this.z = sz;

		return this;

	}

	public Vector3 setFromMatrixColumn( Matrix4 m, int index ) {

		return this.fromArray( m.elements, index * 4 );

	}

	public Vector3 setFromMatrix3Column( Matrix3 m, int index ) {

		return this.fromArray( m.elements, index * 3 );

	}

	public boolean equals( Vector3 v ) {

		return ( ( v.x == this.x ) && ( v.y == this.y ) && ( v.z == this.z ) );

	}

	public Vector3 fromArray( double[] array) {
		return fromArray(array, 0);
	}
	
	public Vector3 fromArray( double[] array, int offset ) {

		this.x = array[ offset ];
		this.y = array[ offset + 1 ];
		this.z = array[ offset + 2 ];

		return this;

	}

	public double[] toArray() {
		return toArray(new double[3]);
	}
	
	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}
	
	public double[] toArray( double[] array, int offset ) {

		array[ offset ] = this.x;
		array[ offset + 1 ] = this.y;
		array[ offset + 2 ] = this.z;

		return array;

	}

	public Vector3 fromBufferAttribute( BufferAttribute attribute, int index ) {

		this.x = attribute.getX( index );
		this.y = attribute.getY( index );
		this.z = attribute.getZ( index );

		return this;

	}

	public Vector3 fromBufferAttribute( InterleavedBufferAttribute attribute, int index ) {

		this.x = attribute.getX( index );
		this.y = attribute.getY( index );
		this.z = attribute.getZ( index );

		return this;

	}

	public Vector3 random() {

		this.x = Math.random();
		this.y = Math.random();
		this.z = Math.random();

		return this;

	}
	
	@Override
	public String toString() {
		return super.toString() + " {x:" + x + ", y:" + y + ", z:" + z + "}";
	}

	private final static Vector3 _vector = /*@__PURE__*/ new Vector3();
	private final static Quaternion _quaternion = /*@__PURE__*/ new Quaternion();
}
