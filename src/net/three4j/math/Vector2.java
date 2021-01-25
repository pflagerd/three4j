package net.three4j.math;

import net.three4j.core.BufferAttribute;

import static net.three4j.THREE.console;

public class Vector2 {
	
	private double _x;
	private double _y;

	public Vector2() {
		this(0, 0);
	}
	
	public final static boolean isVector2 = true;
	
	public Vector2( double x, double y) {
		this._x = x;
		this._y = y;
	}

	public double width() {

		return this._x;

	}

	public void width( double value ) {

		this._x = value;

	}

	public double height() {

		return this._y;

	}

	public void height( double value ) {

		this._y = value;

	}

	public Vector2 set( double x, double y ) {

		this._x = x;
		this._y = y;

		return this;

	}

	public Vector2 setScalar( double scalar ) {

		this._x = scalar;
		this._y = scalar;

		return this;

	}

	public Vector2 setX( double x ) {

		this._x = x;

		return this;

	}
	
	public double x() {
		return this._x;
	}
	
	public double x(double x) {
		return this._x = x;
	}
	

	public Vector2 setY( double y ) {

		this._y = y;

		return this;

	}
	
	public double y() {
		return this._y;
	}
	
	public double y(double y) {
		return this._y = y;
	}

	public Vector2 setComponent( int index, double value ) {

		switch ( index ) {

			case 0: this._x = value; break;
			case 1: this._y = value; break;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

		return this;

	}

	public double getComponent( int index ) {

		switch ( index ) {

			case 0: return this._x;
			case 1: return this._y;
			default: throw new Error( "index is out of range: " + index );

		}

	}

	public Vector2 clone() {

		return new Vector2( this._x, this._y );

	}

	public Vector2 copy( Vector2 v ) {

		this._x = v._x;
		this._y = v._y;

		return this;

	}

	public Vector2 add( Vector2 v ) {

		this._x += v._x;
		this._y += v._y;

		return this;

	}

	public Vector2 addScalar( double s ) {

		this._x += s;
		this._y += s;

		return this;

	}

	public Vector2 addVectors( Vector2 a, Vector2 b ) {

		this._x = a._x + b._x;
		this._y = a._y + b._y;

		return this;

	}

	public Vector2 addScaledVector( Vector2 v, double s ) {

		this._x += v._x * s;
		this._y += v._y * s;

		return this;

	}

	public Vector2 sub( Vector2 v ) {
		this._x -= v._x;
		this._y -= v._y;

		return this;

	}

	public Vector2 subScalar( double s ) {

		this._x -= s;
		this._y -= s;

		return this;

	}

	public Vector2 subVectors( Vector2 a, Vector2 b ) {

		this._x = a._x - b._x;
		this._y = a._y - b._y;

		return this;

	}

	public Vector2 multiply( Vector2 v ) {

		this._x *= v._x;
		this._y *= v._y;

		return this;

	}

	public Vector2 multiplyScalar( double scalar ) {

		this._x *= scalar;
		this._y *= scalar;

		return this;

	}

	public Vector2 divide( Vector2 v ) {

		this._x /= v._x;
		this._y /= v._y;

		return this;

	}

	public Vector2 divideScalar( double scalar ) {
		if (scalar == 0.0 || scalar == -0.0) {
			console.warn("Vector2.divideScalar(0) called.");
			scalar = 1;
		}

		return this.multiplyScalar( 1 / scalar );

	}

	public Vector2 applyMatrix3( Matrix3 m ) {

		final double x = this._x, y = this._y;
		final double[] e = m.elements;

		this._x = e[ 0 ] * x + e[ 3 ] * y + e[ 6 ];
		this._y = e[ 1 ] * x + e[ 4 ] * y + e[ 7 ];

		return this;

	}

	public Vector2 min( Vector2 v ) {

		this._x = Math.min( this._x, v._x );
		this._y = Math.min( this._y, v._y );

		return this;

	}

	public Vector2 max( Vector2 v ) {

		this._x = Math.max( this._x, v._x );
		this._y = Math.max( this._y, v._y );

		return this;

	}

	public Vector2 clamp( Vector2 min, Vector2 max ) {

		// assumes min < max, componentwise

		this._x = Math.max( min._x, Math.min( max._x, this._x ) );
		this._y = Math.max( min._y, Math.min( max._y, this._y ) );

		return this;

	}

	public Vector2 clampScalar( double minVal, double maxVal ) {

		this._x = Math.max( minVal, Math.min( maxVal, this._x ) );
		this._y = Math.max( minVal, Math.min( maxVal, this._y ) );

		return this;

	}

	public Vector2 clampLength( double min, double max ) {

		final double length = this.length();

		return this.divideScalar( length ).multiplyScalar( Math.max( min, Math.min( max, length ) ) );

	}

	public Vector2 floor() {

		this._x = Math.floor( this._x );
		this._y = Math.floor( this._y );

		return this;

	}

	public Vector2 ceil() {

		this._x = Math.ceil( this._x );
		this._y = Math.ceil( this._y );

		return this;

	}

	public Vector2 round() {

		this._x = Math.round( this._x );
		this._y = Math.round( this._y );

		return this;

	}

	public Vector2 roundToZero() {

		this._x = ( this._x < 0 ) ? Math.ceil( this._x ) : Math.floor( this._x );
		this._y = ( this._y < 0 ) ? Math.ceil( this._y ) : Math.floor( this._y );

		return this;

	}

	public Vector2 negate() {

		this._x = - this._x;
		this._y = - this._y;

		return this;

	}

	public double dot( Vector2 v ) {

		return this._x * v._x + this._y * v._y;

	}
	
	public double cross(Vector2 v) {
		return determinant(v);
	}

	// It's really a determinant, not a cross
	public double determinant( Vector2 v ) {

		return this._x * v._y - this._y * v._x;

	}
	
	public double lengthSquared() {
		return lengthSq();
	}
	
	public double quadrature() {
		return lengthSq();
	}

	public double lengthSq() {

		return this._x * this._x + this._y * this._y;

	}

	public double length() {

		return Math.sqrt( this._x * this._x + this._y * this._y );

	}

	public double manhattanLength() {

		return Math.abs( this._x ) + Math.abs( this._y );

	}

	public Vector2 normalize() {

		return this.divideScalar( this.length());

	}

	public double angle() {

		// computes the angle in radians with respect to the positive x-axis

		final double angle = Math.atan2( - this._y, - this._x ) + Math.PI;

		return angle;

	}

	public double distanceTo( Vector2 v ) {

		return Math.sqrt( this.distanceToSquared( v ) );

	}

	public double distanceToSquared( Vector2 v ) {

		final double dx = this._x - v._x, dy = this._y - v._y;
		return dx * dx + dy * dy;

	}

	public double manhattanDistanceTo( Vector2 v ) {

		return Math.abs( this._x - v._x ) + Math.abs( this._y - v._y );

	}

	public Vector2 setLength( double length ) {

		return this.normalize().multiplyScalar( length );

	}

	public Vector2 lerp( Vector2 v, double alpha ) {

		this._x += ( v._x - this._x ) * alpha;
		this._y += ( v._y - this._y ) * alpha;

		return this;

	}

	public Vector2 lerpVectors( Vector2 v1, Vector2 v2, double alpha ) {

		this._x = v1._x + ( v2._x - v1._x ) * alpha;
		this._y = v1._y + ( v2._y - v1._y ) * alpha;

		return this;

	}

	public boolean equals( Vector2 v ) {

		return ( ( v._x == this._x ) && ( v._y == this._y ) );

	}
	
	public Vector2 fromArray(double[] array) {
		return fromArray(array, 0);
	}

	public Vector2 fromArray( double[] array, int offset ) {

		this._x = array[ offset ];
		this._y = array[ offset + 1 ];

		return this;

	}
	
	public double[] toArray() {
		return toArray(new double[2], 0);
	}
	
	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray( double[] array, int offset ) {

		array[ offset ] = this._x;
		array[ offset + 1 ] = this._y;

		return array;

	}

	public Vector2 fromBufferAttribute( BufferAttribute attribute, int index) {

		this._x = attribute.getX( index );
		this._y = attribute.getY( index );

		return this;
	}

	public Vector2 rotateAround( Vector2 center, double angle ) {

		final double c = Math.cos( angle ), s = Math.sin( angle );

		final double x = this._x - center._x;
		final double y = this._y - center._y;

		this._x = x * c - y * s + center._x;
		this._y = x * s + y * c + center._y;

		return this;

	}

	public Vector2 random() {

		this._x = Math.random();
		this._y = Math.random();

		return this;

	}
	
	@Override
	public String toString() {
		return super.toString() + "{x=" + this._x + ", y=" + this._y + "}";
	}


}
