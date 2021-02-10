package net.three4j.math;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;

//import { Quaternion } from './Quaternion.js';
//import { Vector3 } from './Vector3.js';
//import { Matrix4 } from './Matrix4.js';
//import { _Math } from './Math.js';
//
///**
// * @author mrdoob / http://mrdoob.com/
// * @author WestLangley / http://github.com/WestLangley
// * @author bhouston / http://clara.io
// */
//
public class Euler {
	double _x;
	double _y;
	double _z;
	String _order;

	public final boolean isEuler = true;

	public Euler() {
		this._x = this._y = this._z = 0.0;
		this._order = DefaultOrder;
	}

	public Euler(double x, double y, double z) {
		this(x, y, z, DefaultOrder);
	}

	public Euler(double x, double y, double z, String order) {
		this._x = x;
		this._y = y;
		this._z = z;
		this._order = order;
	}

	public double x() {

		return this._x;

	}

	public void x( double value) {

		this._x = value;
		this._onChangeCallback.run();

	}

	public double y() {

		return this._y;

	}

	public void y( double value) {

		this._y = value;
		this._onChangeCallback.run();

	}

	public double z() {

		return this._z;

	}

	public void z( double value) {

		this._z = value;
		this._onChangeCallback.run();

	}

	public String order() {

		return this._order;

	}

	public void order( String value ) {

		this._order = value;
		this._onChangeCallback.run();

	}

	public Euler set( double x, double y, double z) {
		return set(x, y, z, DefaultOrder);
	}

	public Euler set( double x, double y, double z, String order ) {

		this._x = x;
		this._y = y;
		this._z = z;
		this._order = order;

		this._onChangeCallback.run();

		return this;

	}

	public Euler clone() {

		return new Euler( this._x, this._y, this._z, this._order );

	}

	public Euler copy( Euler euler ) {

		this._x = euler._x;
		this._y = euler._y;
		this._z = euler._z;
		this._order = euler._order;

		this._onChangeCallback.run();

		return this;
	}

	public Euler setFromRotationMatrix( Matrix4 m, String order) {
		return setFromRotationMatrix(m, order, true);
	}

	public Euler setFromRotationMatrix( Matrix4 m, String order, boolean update ) {

		// assumes the upper 3x3 of m is a pure rotation matrix (i.e, unscaled)

		final double[] te = m.elements;
		final double m11 = te[ 0 ], m12 = te[ 4 ], m13 = te[ 8 ];
		final double m21 = te[ 1 ], m22 = te[ 5 ], m23 = te[ 9 ];
		final double m31 = te[ 2 ], m32 = te[ 6 ], m33 = te[ 10 ];

		_order = order;

		switch ( order ) {

			case "XYZ":

				this._y = Math.asin( MathUtils.clamp( m13, - 1, 1 ) );

				if ( Math.abs( m13 ) < 0.9999999 ) {

					this._x = Math.atan2( - m23, m33 );
					this._z = Math.atan2( - m12, m11 );

				} else {

					this._x = Math.atan2( m32, m22 );
					this._z = 0;

				}

				break;

			case "YXZ":

				this._x = Math.asin( - MathUtils.clamp( m23, - 1, 1 ) );

				if ( Math.abs( m23 ) < 0.9999999 ) {

					this._y = Math.atan2( m13, m33 );
					this._z = Math.atan2( m21, m22 );

				} else {

					this._y = Math.atan2( - m31, m11 );
					this._z = 0;

				}

				break;

			case "ZXY":

				this._x = Math.asin( MathUtils.clamp( m32, - 1, 1 ) );

				if ( Math.abs( m32 ) < 0.9999999 ) {

					this._y = Math.atan2( - m31, m33 );
					this._z = Math.atan2( - m12, m22 );

				} else {

					this._y = 0;
					this._z = Math.atan2( m21, m11 );

				}

				break;

			case "ZYX":

				this._y = Math.asin( - MathUtils.clamp( m31, - 1, 1 ) );

				if ( Math.abs( m31 ) < 0.9999999 ) {

					this._x = Math.atan2( m32, m33 );
					this._z = Math.atan2( m21, m11 );

				} else {

					this._x = 0;
					this._z = Math.atan2( - m12, m22 );

				}

				break;

			case "YZX":

				this._z = Math.asin( MathUtils.clamp( m21, - 1, 1 ) );

				if ( Math.abs( m21 ) < 0.9999999 ) {

					this._x = Math.atan2( - m23, m22 );
					this._y = Math.atan2( - m31, m11 );

				} else {

					this._x = 0;
					this._y = Math.atan2( m13, m33 );

				}

				break;

			case "XZY":

				this._z = Math.asin( - MathUtils.clamp( m12, - 1, 1 ) );

				if ( Math.abs( m12 ) < 0.9999999 ) {

					this._x = Math.atan2( m32, m22 );
					this._y = Math.atan2( m13, m11 );

				} else {

					this._x = Math.atan2( - m23, m33 );
					this._y = 0;

				}

				break;

			default:

				throw new RuntimeException( "THREE.Euler: .setFromRotationMatrix() encountered an unknown order: " + order );

		}

		this._order = order;

		if ( update )
			this._onChangeCallback.run();

		return this;
	}

	public Euler setFromQuaternion(Quaternion q) {
		return setFromQuaternion(q, DefaultOrder);
	}

	public Euler setFromQuaternion(Quaternion q, String order) {
		return setFromQuaternion(q, order, true);
	}

	public Euler setFromQuaternion( Quaternion q, String order, boolean update ) {

		_matrix.makeRotationFromQuaternion( q );

		return this.setFromRotationMatrix( _matrix, order == null ? DefaultOrder : order, update );

	}



	public Euler setFromVector3( Vector3 v, String order ) {

		return this.set( v.x(), v.y(), v.z(), order );

	}

	public Euler reorder( String newOrder ) {

		// WARNING: this discards revolution information -bhouston

		_quaternion.setFromEuler( this );

		return this.setFromQuaternion( _quaternion, newOrder );

	}

	public boolean equals( Euler euler ) {

		return ( euler._x == this._x ) && ( euler._y == this._y ) && ( euler._z == this._z ) && ( euler._order == this._order );
	}

	public Euler fromArray( double[] array ) {

		this._x = array[ 0 ];
		this._y = array[ 1 ];
		this._z = array[ 2 ];
//		if ( array.length !== undefined )
//			this._order = array[ 3 ];

		this._onChangeCallback.run();

		return this;

	}

	public double[] toArray() {
		return toArray(new double[3], 0);
	}

	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray( double[] array, int offset) {

		array[ offset ] = this._x;
		array[ offset + 1 ] = this._y;
		array[ offset + 2 ] = this._z;
		//array[ offset + 3 ] = this._order;

		return array;

	}

//	Vector3 toVector3( optionalResult ) {
//
//		if ( optionalResult ) {
//
//			return optionalResult.set( this._x, this._y, this._z );
//
//		} else {
//
//			return new Vector3( this._x, this._y, this._z );
//
//		}
//
//	}

	public Euler onChange( Runnable callback ) {

		this._onChangeCallback = callback;

		return this;

	}

	private void noop() {}

	private Runnable _onChangeCallback = this::noop;

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("_onChangeCallback", "isEuler");
		return sortedReflectionToStringBuilder.toString();
	}

	public static final String DefaultOrder = "XYZ";
	public static final String[] RotationOrders = new String[] { "XYZ", "YZX", "ZXY", "XZY", "YXZ", "ZYX" };

	private static final Matrix4 _matrix = /*@__PURE__*/ new Matrix4();
	private static final Quaternion _quaternion = /*@__PURE__*/ new Quaternion();

}