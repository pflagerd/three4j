package net.three4j.math;

import net.three4j.core.BufferAttribute;

public class Vector2 {
	
	public double x;
	public double y;

	public Vector2() {
		this(0, 0);
	}
	
	public final static boolean isVector2 = true;
	
	public Vector2( double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double width() {

		return this.x;

	}

	public void width( double value ) {

		this.x = value;

	}

	public double height() {

		return this.y;

	}

	public void height( double value ) {

		this.y = value;

	}

	public Vector2 set( double x, double y ) {

		this.x = x;
		this.y = y;

		return this;

	}

	public Vector2 setScalar( double scalar ) {

		this.x = scalar;
		this.y = scalar;

		return this;

	}

	public Vector2 setX( double x ) {

		this.x = x;

		return this;

	}
	
	public double x() {
		return this.x;
	}
	
	public double x(double x) {
		return this.x = x;
	}
	

	public Vector2 setY( double y ) {

		this.y = y;

		return this;

	}
	
	public double y() {
		return this.y;
	}
	
	public double y(double y) {
		return this.y = y;
	}

	public Vector2 setComponent( int index, double value ) {

		switch ( index ) {

			case 0: this.x = value; break;
			case 1: this.y = value; break;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

		return this;

	}

	public double getComponent( int index ) {

		switch ( index ) {

			case 0: return this.x;
			case 1: return this.y;
			default: throw new Error( "index is out of range: " + index );

		}

	}

	public Vector2 clone() {

		return new Vector2( this.x, this.y );

	}

	public Vector2 copy( Vector2 v ) {

		this.x = v.x;
		this.y = v.y;

		return this;

	}

	public Vector2 add( Vector2 v ) {

		this.x += v.x;
		this.y += v.y;

		return this;

	}

	public Vector2 addScalar( double s ) {

		this.x += s;
		this.y += s;

		return this;

	}

	public Vector2 addVectors( Vector2 a, Vector2 b ) {

		this.x = a.x + b.x;
		this.y = a.y + b.y;

		return this;

	}

	public Vector2 addScaledVector( Vector2 v, double s ) {

		this.x += v.x * s;
		this.y += v.y * s;

		return this;

	}

	public Vector2 sub( Vector2 v ) {
		this.x -= v.x;
		this.y -= v.y;

		return this;

	}

	public Vector2 subScalar( double s ) {

		this.x -= s;
		this.y -= s;

		return this;

	}

	public Vector2 subVectors( Vector2 a, Vector2 b ) {

		this.x = a.x - b.x;
		this.y = a.y - b.y;

		return this;

	}

	public Vector2 multiply( Vector2 v ) {

		this.x *= v.x;
		this.y *= v.y;

		return this;

	}

	public Vector2 multiplyScalar( double scalar ) {

		this.x *= scalar;
		this.y *= scalar;

		return this;

	}

	public Vector2 divide( Vector2 v ) {

		this.x /= v.x;
		this.y /= v.y;

		return this;

	}

	public Vector2 divideScalar( double scalar ) {

		return this.multiplyScalar( 1 / scalar );

	}

	public Vector2 applyMatrix3( Matrix3 m ) {

		final double x = this.x, y = this.y;
		final double[] e = m.elements;

		this.x = e[ 0 ] * x + e[ 3 ] * y + e[ 6 ];
		this.y = e[ 1 ] * x + e[ 4 ] * y + e[ 7 ];

		return this;

	}

	public Vector2 min( Vector2 v ) {

		this.x = Math.min( this.x, v.x );
		this.y = Math.min( this.y, v.y );

		return this;

	}

	public Vector2 max( Vector2 v ) {

		this.x = Math.max( this.x, v.x );
		this.y = Math.max( this.y, v.y );

		return this;

	}

	public Vector2 clamp( Vector2 min, Vector2 max ) {

		// assumes min < max, componentwise

		this.x = Math.max( min.x, Math.min( max.x, this.x ) );
		this.y = Math.max( min.y, Math.min( max.y, this.y ) );

		return this;

	}

	public Vector2 clampScalar( double minVal, double maxVal ) {

		this.x = Math.max( minVal, Math.min( maxVal, this.x ) );
		this.y = Math.max( minVal, Math.min( maxVal, this.y ) );

		return this;

	}

	public Vector2 clampLength( double min, double max ) {

		final double length = this.length();

		return this.divideScalar( length ).multiplyScalar( Math.max( min, Math.min( max, length ) ) );

	}

	public Vector2 floor() {

		this.x = Math.floor( this.x );
		this.y = Math.floor( this.y );

		return this;

	}

	public Vector2 ceil() {

		this.x = Math.ceil( this.x );
		this.y = Math.ceil( this.y );

		return this;

	}

	public Vector2 round() {

		this.x = Math.round( this.x );
		this.y = Math.round( this.y );

		return this;

	}

	public Vector2 roundToZero() {

		this.x = ( this.x < 0 ) ? Math.ceil( this.x ) : Math.floor( this.x );
		this.y = ( this.y < 0 ) ? Math.ceil( this.y ) : Math.floor( this.y );

		return this;

	}

	public Vector2 negate() {

		this.x = - this.x;
		this.y = - this.y;

		return this;

	}

	public double dot( Vector2 v ) {

		return this.x * v.x + this.y * v.y;

	}
	
	public double cross(Vector2 v) {
		return determinant(v);
	}

	// It's really a determinant, not a cross
	public double determinant( Vector2 v ) {

		return this.x * v.y - this.y * v.x;

	}
	
	public double lengthSquared() {
		return lengthSq();
	}
	
	public double quadrature() {
		return lengthSq();
	}

	public double lengthSq() {

		return this.x * this.x + this.y * this.y;

	}

	public double length() {

		return Math.sqrt( this.x * this.x + this.y * this.y );

	}

	public double manhattanLength() {

		return Math.abs( this.x ) + Math.abs( this.y );

	}

	public Vector2 normalize() {

		return this.divideScalar( this.length());

	}

	public double angle() {

		// computes the angle in radians with respect to the positive x-axis

		final double angle = Math.atan2( - this.y, - this.x ) + Math.PI;

		return angle;

	}

	public double distanceTo( Vector2 v ) {

		return Math.sqrt( this.distanceToSquared( v ) );

	}

	public double distanceToSquared( Vector2 v ) {

		final double dx = this.x - v.x, dy = this.y - v.y;
		return dx * dx + dy * dy;

	}

	public double manhattanDistanceTo( Vector2 v ) {

		return Math.abs( this.x - v.x ) + Math.abs( this.y - v.y );

	}

	public Vector2 setLength( double length ) {

		return this.normalize().multiplyScalar( length );

	}

	public Vector2 lerp( Vector2 v, double alpha ) {

		this.x += ( v.x - this.x ) * alpha;
		this.y += ( v.y - this.y ) * alpha;

		return this;

	}

	public Vector2 lerpVectors( Vector2 v1, Vector2 v2, double alpha ) {

		this.x = v1.x + ( v2.x - v1.x ) * alpha;
		this.y = v1.y + ( v2.y - v1.y ) * alpha;

		return this;

	}

	public boolean equals( Vector2 v ) {

		return ( ( v.x == this.x ) && ( v.y == this.y ) );

	}
	
	public Vector2 fromArray(double[] array) {
		return fromArray(array, 0);
	}

	public Vector2 fromArray( double[] array, int offset ) {

		this.x = array[ offset ];
		this.y = array[ offset + 1 ];

		return this;

	}
	
	public double[] toArray() {
		return toArray(new double[2], 0);
	}
	
	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray( double[] array, int offset ) {

		array[ offset ] = this.x;
		array[ offset + 1 ] = this.y;

		return array;

	}

	public Vector2 fromBufferAttribute( BufferAttribute attribute, int index) {

		this.x = attribute.getX( index );
		this.y = attribute.getY( index );

		return this;
	}

	public Vector2 rotateAround( Vector2 center, double angle ) {

		final double c = Math.cos( angle ), s = Math.sin( angle );

		final double x = this.x - center.x;
		final double y = this.y - center.y;

		this.x = x * c - y * s + center.x;
		this.y = x * s + y * c + center.y;

		return this;

	}

	public Vector2 random() {

		this.x = Math.random();
		this.y = Math.random();

		return this;

	}

}
