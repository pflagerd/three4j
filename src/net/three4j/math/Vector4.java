package net.three4j.math;

import net.three4j.core.BufferAttribute;
import static net.three4j.THREE.console;

public class Vector4 {
	
	private double _x;
	private double _y;
	private double _z;
	private double _w;

	public Vector4() {
		this(0, 0, 0, 1);
	}
	
	
	public Vector4( double x, double y, double z, double w ) {
		this._x = x;
		this._y = y;
		this._z = z;
		this._w = w;
	}


	public double width() {

		return this._z;

	}

	public void width( double value ) {

		this._z = value;

	}

	public double height() {

		return this._w;

	}

	public void height( double value ) {

		this._w = value;

	}

	public Vector4 set( double x, double y, double z, double w ) {

		this._x = x;
		this._y = y;
		this._z = z;
		this._w = w;

		return this;

	}

	public Vector4 setScalar( double scalar ) {

		this._x = scalar;
		this._y = scalar;
		this._z = scalar;
		this._w = scalar;

		return this;

	}
	
	public double x() {
		return this._x;
	}

	public double y() {
		return this._y;
	}

	public double z() {
		return this._z;
	}

	public double w() {
		return this._w;
	}
	
	public void x(double x) {
		this._x = x;
	}

	public void y(double y) {
		this._y = y;
	}

	public void z(double z) {
		this._z = z;
	}

	public void w(double w) {
		this._w = w;
	}

	public Vector4 setX( double x ) {

		this._x = x;

		return this;

	}

	public Vector4 setY( double y ) {

		this._y = y;

		return this;

	}

	public Vector4 setZ( double z ) {

		this._z = z;

		return this;

	}

	public Vector4 setW( double w ) {

		this._w = w;

		return this;

	}

	public Vector4 setComponent( int index, double value ) {

		switch ( index ) {

			case 0: this._x = value; break;
			case 1: this._y = value; break;
			case 2: this._z = value; break;
			case 3: this._w = value; break;
			default: throw new RuntimeException( "index is out of range: " + index );

		}

		return this;

	}

	public double getComponent( int index ) {

		switch ( index ) {

			case 0: return this._x;
			case 1: return this._y;
			case 2: return this._z;
			case 3: return this._w;
			default: throw new Error( "index is out of range: " + index );

		}

	}

	public Vector4 clone() {

		return new Vector4( this._x, this._y, this._z, this._w );

	}

	public Vector4 copy( Vector4 v ) {

		this._x = v._x;
		this._y = v._y;
		this._z = v._z;
		this._w = v._w;

		return this;

	}

	public Vector4 add( Vector4 v) {

		this._x += v._x;
		this._y += v._y;
		this._z += v._z;
		this._w += v._w;

		return this;

	}

	public Vector4 addScalar( double s ) {

		this._x += s;
		this._y += s;
		this._z += s;
		this._w += s;

		return this;

	}

	public Vector4 addVectors( Vector4 a, Vector4 b ) {

		this._x = a._x + b._x;
		this._y = a._y + b._y;
		this._z = a._z + b._z;
		this._w = a._w + b._w;

		return this;

	}

	public Vector4 addScaledVector( Vector4 v, double s ) {

		this._x += v._x * s;
		this._y += v._y * s;
		this._z += v._z * s;
		this._w += v._w * s;

		return this;

	}

	public Vector4 sub( Vector4 v) {

		this._x -= v._x;
		this._y -= v._y;
		this._z -= v._z;
		this._w -= v._w;

		return this;

	}

	public Vector4 subScalar( double s ) {

		this._x -= s;
		this._y -= s;
		this._z -= s;
		this._w -= s;

		return this;

	}

	public Vector4 subVectors( Vector4 a, Vector4 b ) {

		this._x = a._x - b._x;
		this._y = a._y - b._y;
		this._z = a._z - b._z;
		this._w = a._w - b._w;

		return this;

	}

	public Vector4 multiplyScalar( double scalar ) {

		this._x *= scalar;
		this._y *= scalar;
		this._z *= scalar;
		this._w *= scalar;

		return this;

	}

	public Vector4 applyMatrix4( Matrix4 m ) {

		final double x = this._x, y = this._y, z = this._z, w = this._w;
		final double[] e = m.elements;

		this._x = e[ 0 ] * x + e[ 4 ] * y + e[ 8 ] * z + e[ 12 ] * w;
		this._y = e[ 1 ] * x + e[ 5 ] * y + e[ 9 ] * z + e[ 13 ] * w;
		this._z = e[ 2 ] * x + e[ 6 ] * y + e[ 10 ] * z + e[ 14 ] * w;
		this._w = e[ 3 ] * x + e[ 7 ] * y + e[ 11 ] * z + e[ 15 ] * w;

		return this;

	}

	public Vector4 divideScalar( double scalar ) {
		if (scalar == 0.0 || scalar == -0.0) {
			console.warn("Vector4.divideScalar(0)");
			scalar = 1;
		}

		return this.multiplyScalar( 1 / scalar );

	}

	public Vector4 setAxisAngleFromQuaternion( Quaternion q ) {

		// http://www.euclideanspace.com/maths/geometry/rotations/conversions/quaternionToAngle/index.htm

		// q is assumed to be normalized

		this._w = 2 * Math.acos( q.w() );

		final double s = Math.sqrt( 1 - q.w() * q.w() );

		if ( s < 0.0001 ) {

			this._x = 1;
			this._y = 0;
			this._z = 0;

		} else {

			this._x = q.x() / s;
			this._y = q.y() / s;
			this._z = q.z() / s;

		}

		return this;

	}

	public Vector4 setAxisAngleFromRotationMatrix( Matrix4 m ) {

		// http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToAngle/index.htm

		// assumes the upper 3x3 of m is a pure rotation matrix (i.e, unscaled)

		double angle, x, y, z; // variables for result
		final double epsilon = 0.01,		// margin to allow for rounding errors
			epsilon2 = 0.1;		// margin to distinguish between 0 and 180 degrees

			final double[] te = m.elements;

			final double m11 = te[ 0 ], m12 = te[ 4 ], m13 = te[ 8 ],
			m21 = te[ 1 ], m22 = te[ 5 ], m23 = te[ 9 ],
			m31 = te[ 2 ], m32 = te[ 6 ], m33 = te[ 10 ];

		if ( ( Math.abs( m12 - m21 ) < epsilon ) &&
		     ( Math.abs( m13 - m31 ) < epsilon ) &&
		     ( Math.abs( m23 - m32 ) < epsilon ) ) {

			// singularity found
			// first check for identity matrix which must have +1 for all terms
			// in leading diagonal and zero in other terms

			if ( ( Math.abs( m12 + m21 ) < epsilon2 ) &&
			     ( Math.abs( m13 + m31 ) < epsilon2 ) &&
			     ( Math.abs( m23 + m32 ) < epsilon2 ) &&
			     ( Math.abs( m11 + m22 + m33 - 3 ) < epsilon2 ) ) {

				// this singularity is identity matrix so angle = 0

				this.set( 1, 0, 0, 0 );

				return this; // zero angle, arbitrary axis

			}

			// otherwise this singularity is angle = 180

			angle = Math.PI;

			final double xx = ( m11 + 1 ) / 2;
			final double yy = ( m22 + 1 ) / 2;
			final double zz = ( m33 + 1 ) / 2;
			final double xy = ( m12 + m21 ) / 4;
			final double xz = ( m13 + m31 ) / 4;
			final double yz = ( m23 + m32 ) / 4;

			if ( ( xx > yy ) && ( xx > zz ) ) {

				// m11 is the largest diagonal term

				if ( xx < epsilon ) {

					x = 0;
					y = 0.707106781;
					z = 0.707106781;

				} else {

					x = Math.sqrt( xx );
					y = xy / x;
					z = xz / x;

				}

			} else if ( yy > zz ) {

				// m22 is the largest diagonal term

				if ( yy < epsilon ) {

					x = 0.707106781;
					y = 0;
					z = 0.707106781;

				} else {

					y = Math.sqrt( yy );
					x = xy / y;
					z = yz / y;

				}

			} else {

				// m33 is the largest diagonal term so base result on this

				if ( zz < epsilon ) {

					x = 0.707106781;
					y = 0.707106781;
					z = 0;

				} else {

					z = Math.sqrt( zz );
					x = xz / z;
					y = yz / z;

				}

			}

			this.set( x, y, z, angle );

			return this; // return 180 deg rotation

		}

		// as we have reached here there are no singularities so we can handle normally

		double s = Math.sqrt( ( m32 - m23 ) * ( m32 - m23 ) +
			( m13 - m31 ) * ( m13 - m31 ) +
			( m21 - m12 ) * ( m21 - m12 ) ); // used to normalize

		if ( Math.abs( s ) < 0.001 )
			s = 1;

		// prevent divide by zero, should not happen if matrix is orthogonal and should be
		// caught by singularity test above, but I've left it in just in case

		this._x = ( m32 - m23 ) / s;
		this._y = ( m13 - m31 ) / s;
		this._z = ( m21 - m12 ) / s;
		this._w = Math.acos( ( m11 + m22 + m33 - 1 ) / 2 );

		return this;

	}

	public Vector4 min( Vector4 v ) {

		this._x = Math.min( this._x, v._x );
		this._y = Math.min( this._y, v._y );
		this._z = Math.min( this._z, v._z );
		this._w = Math.min( this._w, v._w );

		return this;

	}

	public Vector4 max( Vector4 v ) {

		this._x = Math.max( this._x, v._x );
		this._y = Math.max( this._y, v._y );
		this._z = Math.max( this._z, v._z );
		this._w = Math.max( this._w, v._w );

		return this;

	}

	public Vector4 clamp( Vector4 min, Vector4 max ) {

		// assumes min < max, componentwise

		this._x = Math.max( min._x, Math.min( max._x, this._x ) );
		this._y = Math.max( min._y, Math.min( max._y, this._y ) );
		this._z = Math.max( min._z, Math.min( max._z, this._z ) );
		this._w = Math.max( min._w, Math.min( max._w, this._w ) );

		return this;

	}

	public Vector4 clampScalar( double minVal, double maxVal ) {

		this._x = Math.max( minVal, Math.min( maxVal, this._x ) );
		this._y = Math.max( minVal, Math.min( maxVal, this._y ) );
		this._z = Math.max( minVal, Math.min( maxVal, this._z ) );
		this._w = Math.max( minVal, Math.min( maxVal, this._w ) );

		return this;

	}

	public Vector4 clampLength( double min, double max ) {

		final double length = this.length();

		return this.divideScalar( length ).multiplyScalar( Math.max( min, Math.min( max, length ) ) );

	}

	public Vector4 floor() {

		this._x = Math.floor( this._x );
		this._y = Math.floor( this._y );
		this._z = Math.floor( this._z );
		this._w = Math.floor( this._w );

		return this;

	}

	public Vector4 ceil() {

		this._x = Math.ceil( this._x );
		this._y = Math.ceil( this._y );
		this._z = Math.ceil( this._z );
		this._w = Math.ceil( this._w );

		return this;

	}

	public Vector4 round() {

		this._x = Math.round( this._x );
		this._y = Math.round( this._y );
		this._z = Math.round( this._z );
		this._w = Math.round( this._w );

		return this;

	}

	public Vector4 roundToZero() {

		this._x = ( this._x < 0 ) ? Math.ceil( this._x ) : Math.floor( this._x );
		this._y = ( this._y < 0 ) ? Math.ceil( this._y ) : Math.floor( this._y );
		this._z = ( this._z < 0 ) ? Math.ceil( this._z ) : Math.floor( this._z );
		this._w = ( this._w < 0 ) ? Math.ceil( this._w ) : Math.floor( this._w );

		return this;

	}

	public Vector4 negate() {

		this._x = - this._x;
		this._y = - this._y;
		this._z = - this._z;
		this._w = - this._w;

		return this;

	}

	public double dot( Vector4 v ) {

		return this._x * v._x + this._y * v._y + this._z * v._z + this._w * v._w;

	}

	public double lengthSq() {

		return this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w;

	}

	public double length() {

		return Math.sqrt( this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w );

	}

	public double manhattanLength() {

		return Math.abs( this._x ) + Math.abs( this._y ) + Math.abs( this._z ) + Math.abs( this._w );

	}

	public Vector4 normalize() {

		return this.divideScalar( this.length() );

	}

	public Vector4 setLength( double length ) {

		return this.normalize().multiplyScalar( length );

	}

	public Vector4 lerp( Vector4 v, double alpha ) {

		this._x += ( v._x - this._x ) * alpha;
		this._y += ( v._y - this._y ) * alpha;
		this._z += ( v._z - this._z ) * alpha;
		this._w += ( v._w - this._w ) * alpha;

		return this;

	}

	public Vector4 lerpVectors( Vector4 v1, Vector4 v2, double alpha ) {

		this._x = v1._x + ( v2._x - v1._x ) * alpha;
		this._y = v1._y + ( v2._y - v1._y ) * alpha;
		this._z = v1._z + ( v2._z - v1._z ) * alpha;
		this._w = v1._w + ( v2._w - v1._w ) * alpha;

		return this;

	}

	public boolean equals( Vector4 v ) {

		return ( ( v._x == this._x ) && ( v._y == this._y ) && ( v._z == this._z ) && ( v._w == this._w ) );

	}

	public Vector4 fromArray( double[] array) {
		return this.fromArray(array, 0);
	}

	
	public Vector4 fromArray( double[] array, int offset ) {

		this._x = array[ offset ];
		this._y = array[ offset + 1 ];
		this._z = array[ offset + 2 ];
		this._w = array[ offset + 3 ];

		return this;

	}

	public double[] toArray() {
		return toArray(new double[4], 0);
	}
	
	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}
	
	public double[] toArray( double[] array, int offset ) {

		array[ offset ] = this._x;
		array[ offset + 1 ] = this._y;
		array[ offset + 2 ] = this._z;
		array[ offset + 3 ] = this._w;

		return array;
	}

	public Vector4 fromBufferAttribute( BufferAttribute attribute, int index) {

		this._x = attribute.getX( index );
		this._y = attribute.getY( index );
		this._z = attribute.getZ( index );
		this._w = attribute.getW( index );

		return this;

	}

	public Vector4 random() {

		this._x = Math.random();
		this._y = Math.random();
		this._z = Math.random();
		this._w = Math.random();

		return this;

	}
	
	@Override
	public String toString() {
		return super.toString() + " {x=" + this._x + ", y=" + this._y + ", z=" + this._z + ", w=" + this._w + "}";
	}



}


