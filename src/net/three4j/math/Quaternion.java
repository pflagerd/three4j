package net.three4j.math;

/**
 * @author mikael emtinger / http://gomo.se/
 * @author alteredq / http://alteredqualia.com/
 * @author WestLangley / http://github.com/WestLangley
 * @author bhouston / http://clara.io
 */

//import { _Math } from './Math.js';
//import { Vector3 } from './Vector3.js';

public class Quaternion {
	private double _x;
	private double _y;
	private double _z;
	private double _w;
	
	public boolean isQuaternion() {
		return true;
	}
	
	public Quaternion() {
		this(0, 0, 0, 0);
	}

	public Quaternion(double x, double y, double z, double w) {
		this._x = x;
		this._y = y;
		this._z = z;
		this._w = w;
	}
	
	public Quaternion(Quaternion quaternion) {
		quaternion.clone();
	}

	public static Quaternion slerp( Quaternion qa, Quaternion qb, Quaternion qm, double t ) {
		return qm.copy( qa ).slerp( qb, t );
	}

	public static Quaternion slerpFlat( double[] dst, int dstOffset, double[] src0, int srcOffset0, double[] src1, int srcOffset1, double t ) {

		// fuzz-free, array-based Quaternion SLERP operation

		double x0 = src0[ srcOffset0 + 0 ],
			y0 = src0[ srcOffset0 + 1 ],
			z0 = src0[ srcOffset0 + 2 ],
			w0 = src0[ srcOffset0 + 3 ];

		final double x1 = src1[ srcOffset1 + 0 ],
			y1 = src1[ srcOffset1 + 1 ],
			z1 = src1[ srcOffset1 + 2 ],
			w1 = src1[ srcOffset1 + 3 ];

		if ( w0 != w1 || x0 != x1 || y0 != y1 || z0 != z1 ) {

			double s = 1 - t;
			final double cos = x0 * x1 + y0 * y1 + z0 * z1 + w0 * w1,
				dir = ( cos >= 0 ? 1 : - 1 ),
				sqrSin = 1 - cos * cos;

			// Skip the Slerp for tiny steps to avoid numeric problems:
			if ( sqrSin > Math.ulp(sqrSin) ) {

				final double sin = Math.sqrt( sqrSin ),
					len = Math.atan2( sin, cos * dir );

				s = Math.sin( s * len ) / sin;
				t = Math.sin( t * len ) / sin;
			}

			final double tDir = t * dir;

			x0 = x0 * s + x1 * tDir;
			y0 = y0 * s + y1 * tDir;
			z0 = z0 * s + z1 * tDir;
			w0 = w0 * s + w1 * tDir;

			// Normalize in case we just did a lerp:
			if ( s == 1 - t ) {

				final double f = 1 / Math.sqrt( x0 * x0 + y0 * y0 + z0 * z0 + w0 * w0 );

				x0 *= f;
				y0 *= f;
				z0 *= f;
				w0 *= f;

			}

		}

		dst[ dstOffset ] = x0;
		dst[ dstOffset + 1 ] = y0;
		dst[ dstOffset + 2 ] = z0;
		dst[ dstOffset + 3 ] = w0;

		return new Quaternion(x0, y0, z0, w0); // DPP: Not sure about this.
	}

	public static double[] multiplyQuaternionsFlat( double[] dst, int dstOffset, double[] src0, int srcOffset0, double[] src1, int srcOffset1 ) {

		final double x0 = src0[ srcOffset0 ];
		final double y0 = src0[ srcOffset0 + 1 ];
		final double z0 = src0[ srcOffset0 + 2 ];
		final double w0 = src0[ srcOffset0 + 3 ];

		final double x1 = src1[ srcOffset1 ];
		final double y1 = src1[ srcOffset1 + 1 ];
		final double z1 = src1[ srcOffset1 + 2 ];
		final double w1 = src1[ srcOffset1 + 3 ];

		dst[ dstOffset ] = x0 * w1 + w0 * x1 + y0 * z1 - z0 * y1;
		dst[ dstOffset + 1 ] = y0 * w1 + w0 * y1 + z0 * x1 - x0 * z1;
		dst[ dstOffset + 2 ] = z0 * w1 + w0 * z1 + x0 * y1 - y0 * x1;
		dst[ dstOffset + 3 ] = w0 * w1 - x0 * x1 - y0 * y1 - z0 * z1;

		return dst;

	}

	public double x() {

		return this._x;

	}

	public void x( double value ) {

		this._x = value;
		this._onChangeCallback();

	}

	public double y() {

		return this._y;

	}

	public void y( double value ) {

		this._y = value;
		this._onChangeCallback();

	}

	public double z() {

		return this._z;

	}

	public void z( double value ) {

		this._z = value;
		this._onChangeCallback();

	}

	public double w() {

		return this._w;

	}

	public void w( double value ) {

		this._w = value;
		this._onChangeCallback();

	}

	public Quaternion set(double x,double y,double z, double w) {
		this._x = x;
		this._y = y;
		this._z = z;
		this._w = w;

		this._onChangeCallback();

		return this;
	}

	public Quaternion clone() {
		return new Quaternion( this._x, this._y, this._z, this._w );
	}

	public Quaternion copy (Quaternion quaternion) {

		this._x = quaternion._x;
		this._y = quaternion._y;
		this._z = quaternion._z;
		this._w = quaternion._w;

		this._onChangeCallback();

		return this;
	}
	
	public Quaternion setFromEuler( Euler euler) {
		return setFromEuler(euler, true);
	}

	public Quaternion setFromEuler( Euler euler, boolean update ) {

		final double x = euler._x, y = euler._y, z = euler._z; 
		String order = euler.order();

		// http://www.mathworks.com/matlabcentral/fileexchange/
		// 	20696-function-to-convert-between-dcm-euler-angles-quaternions-and-euler-vectors/
		//	content/SpinCalc.m
		final double c1 = Math.cos( x / 2 );
		final double c2 = Math.cos( y / 2 );
		final double c3 = Math.cos( z / 2 );

		final double s1 = Math.sin( x / 2 );
		final double s2 = Math.sin( y / 2 );
		final double s3 = Math.sin( z / 2 );

		switch ( order ) {

			case "XYZ":
				this._x = s1 * c2 * c3 + c1 * s2 * s3;
				this._y = c1 * s2 * c3 - s1 * c2 * s3;
				this._z = c1 * c2 * s3 + s1 * s2 * c3;
				this._w = c1 * c2 * c3 - s1 * s2 * s3;
				break;

			case "YXZ":
				this._x = s1 * c2 * c3 + c1 * s2 * s3;
				this._y = c1 * s2 * c3 - s1 * c2 * s3;
				this._z = c1 * c2 * s3 - s1 * s2 * c3;
				this._w = c1 * c2 * c3 + s1 * s2 * s3;
				break;

			case "ZXY":
				this._x = s1 * c2 * c3 - c1 * s2 * s3;
				this._y = c1 * s2 * c3 + s1 * c2 * s3;
				this._z = c1 * c2 * s3 + s1 * s2 * c3;
				this._w = c1 * c2 * c3 - s1 * s2 * s3;
				break;

			case "ZYX":
				this._x = s1 * c2 * c3 - c1 * s2 * s3;
				this._y = c1 * s2 * c3 + s1 * c2 * s3;
				this._z = c1 * c2 * s3 - s1 * s2 * c3;
				this._w = c1 * c2 * c3 + s1 * s2 * s3;
				break;

			case "YZX":
				this._x = s1 * c2 * c3 + c1 * s2 * s3;
				this._y = c1 * s2 * c3 + s1 * c2 * s3;
				this._z = c1 * c2 * s3 - s1 * s2 * c3;
				this._w = c1 * c2 * c3 - s1 * s2 * s3;
				break;

			case "XZY":
				this._x = s1 * c2 * c3 - c1 * s2 * s3;
				this._y = c1 * s2 * c3 - s1 * c2 * s3;
				this._z = c1 * c2 * s3 + s1 * s2 * c3;
				this._w = c1 * c2 * c3 + s1 * s2 * s3;
				break;

			default:
				throw new RuntimeException( "THREE.Quaternion: .setFromEuler() encountered an unknown order: " + order );

		}

		if ( update != false ) this._onChangeCallback();

		return this;

	}

	public Quaternion setFromAxisAngle( Vector3 axis, double angle ) {

		// http://www.euclideanspace.com/maths/geometry/rotations/conversions/angleToQuaternion/index.htm

		// assumes axis is normalized

		final double halfAngle = angle / 2, s = Math.sin( halfAngle );

		this._x = axis.x * s;
		this._y = axis.y * s;
		this._z = axis.z * s;
		this._w = Math.cos( halfAngle );

		this._onChangeCallback();

		return this;

	}

	public Quaternion setFromRotationMatrix( Matrix4 m ) {

		// http://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/index.htm

		// assumes the upper 3x3 of m is a pure rotation matrix (i.e, unscaled)

		final double[] te = m.elements;

		final double m11 = te[ 0 ], m12 = te[ 4 ], m13 = te[ 8 ],
			m21 = te[ 1 ], m22 = te[ 5 ], m23 = te[ 9 ],
			m31 = te[ 2 ], m32 = te[ 6 ], m33 = te[ 10 ],

			trace = m11 + m22 + m33;

		if ( trace > 0 ) {

			final double s = 0.5 / Math.sqrt( trace + 1.0 );

			this._w = 0.25 / s;
			this._x = ( m32 - m23 ) * s;
			this._y = ( m13 - m31 ) * s;
			this._z = ( m21 - m12 ) * s;

		} else if ( m11 > m22 && m11 > m33 ) {

			final double s = 2.0 * Math.sqrt( 1.0 + m11 - m22 - m33 );

			this._w = ( m32 - m23 ) / s;
			this._x = 0.25 * s;
			this._y = ( m12 + m21 ) / s;
			this._z = ( m13 + m31 ) / s;

		} else if ( m22 > m33 ) {

			final double s = 2.0 * Math.sqrt( 1.0 + m22 - m11 - m33 );

			this._w = ( m13 - m31 ) / s;
			this._x = ( m12 + m21 ) / s;
			this._y = 0.25 * s;
			this._z = ( m23 + m32 ) / s;

		} else {

			final double s = 2.0 * Math.sqrt( 1.0 + m33 - m11 - m22 );

			this._w = ( m21 - m12 ) / s;
			this._x = ( m13 + m31 ) / s;
			this._y = ( m23 + m32 ) / s;
			this._z = 0.25 * s;

		}

		this._onChangeCallback();

		return this;

	}

	public Quaternion setFromUnitVectors( Vector3 vFrom, Vector3 vTo ) {

		// assumes direction vectors vFrom and vTo are normalized

		final double EPS = 0.000001;

		double r = vFrom.dot( vTo ) + 1;

		if ( r < EPS ) {

			r = 0;

			if ( Math.abs( vFrom.x ) > Math.abs( vFrom.z ) ) {

				this._x = - vFrom.y;
				this._y = vFrom.x;
				this._z = 0;
				this._w = r;

			} else {

				this._x = 0;
				this._y = - vFrom.z;
				this._z = vFrom.y;
				this._w = r;

			}

		} else {

			// crossVectors( vFrom, vTo ); // inlined to avoid cyclic dependency on Vector3

			this._x = vFrom.y * vTo.z - vFrom.z * vTo.y;
			this._y = vFrom.z * vTo.x - vFrom.x * vTo.z;
			this._z = vFrom.x * vTo.y - vFrom.y * vTo.x;
			this._w = r;

		}

		return this.normalize();

	}

	public double angleTo( Quaternion q ) {

		return 2 * Math.acos( Math.abs( MathUtils.clamp( this.dot( q ), - 1, 1 ) ) );

	}

	public Quaternion rotateTowards( Quaternion q, double step ) {

		final double angle = this.angleTo( q );

		if ( angle == 0 ) return this;

		final double t = Math.min( 1, step / angle );

		this.slerp( q, t );

		return this;

	}

	public Quaternion identity() {

		return this.set( 0, 0, 0, 1 );

	}

	public Quaternion invert() {

		// quaternion is assumed to have unit length

		return this.conjugate();

	}

	public Quaternion conjugate() {

		this._x *= - 1;
		this._y *= - 1;
		this._z *= - 1;

		this._onChangeCallback();

		return this;

	}

	public double dot( Quaternion q ) {

		return this._x * q._x + this._y * q._y + this._z * q._z + this._w * q._w;

	}

	public double lengthSq() {

		return this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w;

	}

	public double length() {

		return Math.sqrt( this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w );

	}

	public Quaternion normalize() {

		double l = this.length();

		if ( l == 0 ) {

			this._x = 0;
			this._y = 0;
			this._z = 0;
			this._w = 1;

		} else {

			l = 1 / l;

			this._x = this._x * l;
			this._y = this._y * l;
			this._z = this._z * l;
			this._w = this._w * l;

		}

		this._onChangeCallback();

		return this;
		
	}

	public Quaternion multiply( Quaternion q) {
		return this.multiplyQuaternions( this, q );		
	}
	
	public Quaternion multiply( Quaternion q, Quaternion p ) {

		if ( p != null ) {

			return this.multiplyQuaternions( q, p );

		}

		return this.multiplyQuaternions( this, q );

	}

	public Quaternion premultiply( Quaternion q ) {
		return this.multiplyQuaternions( q, this );
	}

	public Quaternion multiplyQuaternions( Quaternion a, Quaternion b ) {

		// from http://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/code/index.htm

		final double qax = a._x, qay = a._y, qaz = a._z, qaw = a._w;
		final double qbx = b._x, qby = b._y, qbz = b._z, qbw = b._w;

		this._x = qax * qbw + qaw * qbx + qay * qbz - qaz * qby;
		this._y = qay * qbw + qaw * qby + qaz * qbx - qax * qbz;
		this._z = qaz * qbw + qaw * qbz + qax * qby - qay * qbx;
		this._w = qaw * qbw - qax * qbx - qay * qby - qaz * qbz;

		this._onChangeCallback();

		return this;

	}

	public Quaternion slerp( Quaternion qb, double t ) {

		if ( t == 0 ) 
			return this;
		
		if ( t == 1 ) 
			return this.copy( qb );

		final double x = this._x, y = this._y, z = this._z, w = this._w;

		// http://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/slerp/
		double cosHalfTheta = w * qb._w + x * qb._x + y * qb._y + z * qb._z;

		if ( cosHalfTheta < 0 ) {

			this._w = - qb._w;
			this._x = - qb._x;
			this._y = - qb._y;
			this._z = - qb._z;

			cosHalfTheta = - cosHalfTheta;

		} else {

			this.copy( qb );

		}

		if ( cosHalfTheta >= 1.0 ) {

			this._w = w;
			this._x = x;
			this._y = y;
			this._z = z;

			return this;

		}

		final double sqrSinHalfTheta = 1.0 - cosHalfTheta * cosHalfTheta;

		if ( sqrSinHalfTheta <= Math.ulp(sqrSinHalfTheta) ) {

			final double s = 1 - t;
			this._w = s * w + t * this._w;
			this._x = s * x + t * this._x;
			this._y = s * y + t * this._y;
			this._z = s * z + t * this._z;

			this.normalize();
			this._onChangeCallback();

			return this;

		}

		final double sinHalfTheta = Math.sqrt( sqrSinHalfTheta );
		final double halfTheta = Math.atan2( sinHalfTheta, cosHalfTheta );
		final double ratioA = Math.sin( ( 1 - t ) * halfTheta ) / sinHalfTheta,
			ratioB = Math.sin( t * halfTheta ) / sinHalfTheta;

		this._w = ( w * ratioA + this._w * ratioB );
		this._x = ( x * ratioA + this._x * ratioB );
		this._y = ( y * ratioA + this._y * ratioB );
		this._z = ( z * ratioA + this._z * ratioB );
		this._onChangeCallback();

		return this;

	}

	public boolean equals( Quaternion q ) {

		return ( q._x == this._x ) && ( q._y == this._y ) && ( q._z == this._z ) && ( q._w == this._w );

	}
	
	public Quaternion fromArray(double[] array) {
		return fromArray(array, 0);
	}

	public Quaternion fromArray( double[] array, int offset) {

		this._x = array[ offset ];
		this._y = array[ offset + 1 ];
		this._z = array[ offset + 2 ];
		this._w = array[ offset + 3 ];

		this._onChangeCallback();

		return this;

	}

	public double[] toArray() {
		return new double[] { this._x, this._y, this._z, this._w};
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

//	Quaternion fromBufferAttribute( Attribute attribute, int index ) {
//
//		this._x = attribute.getX( index );
//		this._y = attribute.getY( index );
//		this._z = attribute.getZ( index );
//		this._w = attribute.getW( index );
//
//		return this;
//
//	}

//	_onChange( callback ) {
//
//		this._onChangeCallback = callback;
//
//		return this;
//
//	}

	public void _onChangeCallback() {}
}
