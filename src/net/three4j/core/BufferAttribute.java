package net.three4j.core;

import static net.three4j.THREE.console;
import static net.three4j.constants.StaticDrawUsage;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;
import org.mozilla.types.Float32Array;
import org.mozilla.types.Int32Array;
import org.mozilla.types.TypedArray;
import org.mozilla.types.Uint16Array;
import org.mozilla.types.Uint32Array;

import net.three4j.math.Color;
import net.three4j.math.Matrix3;
import net.three4j.math.Matrix4;
import net.three4j.math.Vector2;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;

public class BufferAttribute {
	static final Vector3 _vector = new Vector3();
	static final Vector2 _vector2 = new Vector2();

	static class UpdateRange {
		public UpdateRange(int offset, int count) {
			this.offset = offset;
			this.count = count;
		}

		int offset;
		int count;

		public String toString() {
			SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
			//sortedReflectionToStringBuilder.setExcludeFieldNames("_onUploadCallback");
			return sortedReflectionToStringBuilder.toString();
		}

	}

	public TypedArray _array;

	public TypedArray array() {
		return _array;
	}

	public String _name = "";
	public String name() {
		return _name;
	}

	public int _itemSize;
	public int itemSize() {
		return _itemSize;
	}

	public int _count;
	public int count() {
		return _count;
	}

	public boolean _normalized;
	public boolean normalized() {
		return _normalized;
	}

	public int _usage;
	public int usage() {
		return _usage;
	}

	public int _version;
	public int version() {
		return _version;
	}

	public UpdateRange _updateRange;
	public UpdateRange updateRange() {
		return _updateRange;
	}


	public BufferAttribute() {
		_name = "";
		_usage = StaticDrawUsage;
		_updateRange = new UpdateRange(0, 1);
	}

	public BufferAttribute(Float32Array array, int itemSize) {
		this(array, itemSize, false);
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Float32Array array, int itemSize, boolean normalized) {
		this._array = array;
		this._itemSize = itemSize;
		this._normalized = normalized;
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Int32Array array, int itemSize) {
		this(array, itemSize, false);
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Int32Array array, int itemSize, boolean normalized) {
		this._array = array;
		this._itemSize = itemSize;
		this._normalized = normalized;
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Uint16Array array, int itemSize) {
		this(array, itemSize, false);
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Uint16Array array, int itemSize, boolean normalized) {
		this._array = array;
		this._itemSize = itemSize;
		this._normalized = normalized;
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Uint32Array array, int itemSize) {
		this(array, itemSize, false);
		this._count = array.length / _itemSize;
	}

	public BufferAttribute(Uint32Array array, int itemSize, boolean normalized) {
		this._array = array;
		this._itemSize = itemSize;
		this._normalized = normalized;
		this._count = array.length / _itemSize;
	}

	public void needsUpdate(boolean needsUpdate) {
		if (needsUpdate)
			this._version++;
	}

	private void noop() {}

	private Runnable _onUploadCallback = this::noop;

	public void onUploadCallback() {
		_onUploadCallback.run();
	}

	public BufferAttribute setUsage( int value ) {

		this._usage = value;

		return this;

	}

	public BufferAttribute copy( BufferAttribute source ) {

		this._name = source._name;
		this._array = source._array.clone();
		this._itemSize = source._itemSize;
		this._count = source._count;
		this._normalized = source._normalized;

		this._usage = source._usage;

		return this;

	}

	public BufferAttribute copyAt( int index1, BufferAttribute attribute, int index2 ) {

		index1 *= this._itemSize;
		index2 *= attribute._itemSize;

		for ( int i = 0, l = this._itemSize; i < l; i ++ ) {

			if (_array instanceof Float32Array) {
				((Float32Array)this._array).array()[ index1 + i ] =
						((Float32Array)attribute._array).array()[ index2 + i ];
			}
			// DPP: TODO: Add more here.

		}

		return this;

	}

	// DPP: TODO: More like this.
	public BufferAttribute copyArray( Float32Array array ) {

		this._array = array;

		return this;

	}

	public BufferAttribute copyColorsArray( Color[] colors ) {

		final double[] array = ((Float32Array)this._array).array();
		int offset = 0;

		for ( int i = 0, l = colors.length; i < l; i ++ ) {

			Color color = colors[ i ];

			if ( color == null ) {

				console.warn( "THREE.BufferAttribute.copyColorsArray(): color is undefined" + i );
				color = new Color();

			}

			array[ offset ++ ] = color.r();
			array[ offset ++ ] = color.g();
			array[ offset ++ ] = color.b();

		}

		return this;

	}

	public BufferAttribute copyVector2sArray( Vector2[] vectors ) {

		double[] array = ((Float32Array)this._array).array();
		int offset = 0;

		for ( int i = 0, l = vectors.length; i < l; i ++ ) {

			Vector2 vector = vectors[ i ];

			if ( vector == null ) {

				console.warn( "THREE.BufferAttribute.copyVector2sArray(): vector is undefined " + i);
				vector = new Vector2();

			}

			array[ offset ++ ] = vector.x();
			array[ offset ++ ] = vector.y();

		}

		return this;

	}

	public BufferAttribute copyVector3sArray( Vector3[] vectors ) {

		final double[] array = ((Float32Array)this._array).array();
		int offset = 0;

		for ( int i = 0, l = vectors.length; i < l; i ++ ) {

			Vector3 vector = vectors[ i ];

			if ( vector == null ) {

				console.warn( "THREE.BufferAttribute.copyVector3sArray(): vector is undefined", i );
				vector = new Vector3();

			}

			array[ offset ++ ] = vector.x();
			array[ offset ++ ] = vector.y();
			array[ offset ++ ] = vector.z();

		}

		return this;

	}

	public BufferAttribute copyVector4sArray( Vector4[] vectors ) {

		final double[] array = ((Float32Array)this._array).array();
		int offset = 0;

		for ( int i = 0, l = vectors.length; i < l; i ++ ) {

			Vector4 vector = vectors[ i ];

			if ( vector == null ) {

				console.warn( "THREE.BufferAttribute.copyVector4sArray(): vector is undefined " + i );
				vector = new Vector4();

			}

			array[ offset ++ ] = vector.x();
			array[ offset ++ ] = vector.y();
			array[ offset ++ ] = vector.z();
			array[ offset ++ ] = vector.w();

		}

		return this;

	}

	public BufferAttribute applyMatrix3( Matrix3 m ) {

		if ( this._itemSize == 2 ) {

			for ( int i = 0, l = this._count; i < l; i ++ ) {

				_vector2.fromBufferAttribute( this, i );
				_vector2.applyMatrix3( m );

				this.setXY( i, _vector2.x(), _vector2.y() );

			}

		} else if ( this._itemSize == 3 ) {

			for ( int i = 0, l = this._count; i < l; i ++ ) {

				_vector.fromBufferAttribute( this, i );
				_vector.applyMatrix3( m );

				this.setXYZ( i, _vector.x(), _vector.y(), _vector.z() );

			}

		}

		return this;

	}

	public BufferAttribute applyMatrix4( Matrix4 m ) {

		for ( int i = 0, l = this._count; i < l; i ++ ) {

			_vector.set(this.getX( i ), this.getY( i ), this.getZ( i ));

			_vector.applyMatrix4( m );

			this.setXYZ( i, _vector.x(), _vector.y(), _vector.z() );

		}

		return this;

	}

	public BufferAttribute applyNormalMatrix( Matrix3 m ) {

		for ( int i = 0, l = this._count; i < l; i ++ ) {

			_vector.set(this.getX( i ), this.getY( i ), this.getZ( i ));

			_vector.applyNormalMatrix( m );

			this.setXYZ( i, _vector.x(), _vector.y(), _vector.z() );

		}

		return this;

	}

	public BufferAttribute transformDirection( Matrix4 m ) {

		for ( int i = 0, l = this._count; i < l; i ++ ) {

			_vector.set(this.getX( i ), this.getY( i ), this.getZ( i ));

			_vector.transformDirection( m );

			this.setXYZ( i, _vector.x(), _vector.y(), _vector.z() );

		}

		return this;

	}

	public BufferAttribute set( double[] value ) {
		return set(value, 0);
	}

	public BufferAttribute set( double[] value, int offset ) {

		((Float32Array)this._array).set( value, offset );

		return this;

	}

	public double getX(int index) {
		return ((Float32Array)this.array()).array()[ index * this._itemSize ];
	}

	public BufferAttribute setX( int index, double x ) {

		((Float32Array)this.array()).array()[ index * this._itemSize ] = x;

		return this;

	}

	public double getY(int index) {

		return ((Float32Array)this.array()).array()[ index * this._itemSize + 1 ];

	}

	public BufferAttribute setY( int index, double y ) {

		((Float32Array)this.array()).array()[ index * this._itemSize + 1 ] = y;

		return this;

	}

	public double getZ(int index) {

		return ((Float32Array)this.array()).array()[ index * this._itemSize + 2 ];

	}

	public BufferAttribute setZ( int index, double z ) {

		((Float32Array)this.array()).array()[ index * this._itemSize + 2 ] = z;

		return this;

	}

	public double getW(int index) {

		return ((Float32Array)this.array()).array()[ index * this._itemSize + 3 ];

	}

	public BufferAttribute setW( int index, double w ) {

		((Float32Array)this.array()).array()[ index * this._itemSize + 3 ] = w;

		return this;

	}

	public BufferAttribute setXY( int index, double x, double y ) {

		index *= this._itemSize;

		((Float32Array)this.array()).array()[ index + 0 ] = x;
		((Float32Array)this.array()).array()[ index + 1 ] = y;

		return this;

	}

	public BufferAttribute setXYZ( int index, double x, double y, double z ) {

		index *= this._itemSize;

		((Float32Array)this.array()).array()[ index + 0 ] = x;
		((Float32Array)this.array()).array()[ index + 1 ] = y;
		((Float32Array)this.array()).array()[ index + 2 ] = z;

		return this;

	}

	public BufferAttribute setXYZW( int index, double x, double y, double z, double w ) {

		index *= this._itemSize;

		((Float32Array)this.array()).array()[ index + 0 ] = x;
		((Float32Array)this.array()).array()[ index + 1 ] = y;
		((Float32Array)this.array()).array()[ index + 2 ] = z;
		((Float32Array)this.array()).array()[ index + 3 ] = w;

		return this;

	}

	public BufferAttribute onUpload( Runnable callback ) {

		this._onUploadCallback = callback;

		return this;

	}

	public BufferAttribute clone() {

		return new BufferAttribute( (Float32Array)this._array, this._itemSize );

	}

	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("_onUploadCallback");
		return sortedReflectionToStringBuilder.toString();
	}


//	public void toJSON() {
//
//		return {
//			itemSize: this.itemSize,
//			type: this.array.constructor.name,
//			array: Array.prototype.slice.call( this.array ),
//			normalized: this.normalized
//		};
//
//	}
}

//function Int8BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Int8Array( array ), itemSize, normalized );
//
//}
//
//Int8BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Int8BufferAttribute.prototype.constructor = Int8BufferAttribute;
//
//
//function Uint8BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Uint8Array( array ), itemSize, normalized );
//
//}
//
//Uint8BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Uint8BufferAttribute.prototype.constructor = Uint8BufferAttribute;
//
//
//function Uint8ClampedBufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Uint8ClampedArray( array ), itemSize, normalized );
//
//}
//
//Uint8ClampedBufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Uint8ClampedBufferAttribute.prototype.constructor = Uint8ClampedBufferAttribute;
//
//
//function Int16BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Int16Array( array ), itemSize, normalized );
//
//}

//function Float16BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Uint16Array( array ), itemSize, normalized );
//
//}
//
//Float16BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Float16BufferAttribute.prototype.constructor = Float16BufferAttribute;
//Float16BufferAttribute.prototype.isFloat16BufferAttribute = true;
//
//function Float32BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Float32Array( array ), itemSize, normalized );
//
//}

//Float64BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Float64BufferAttribute.prototype.constructor = Float64BufferAttribute;
//
////
//
//export {
//	Float64BufferAttribute,
//	Float32BufferAttribute,
//	Float16BufferAttribute,
//	Uint32BufferAttribute,
//	Int32BufferAttribute,
//	Uint16BufferAttribute,
//	Int16BufferAttribute,
//	Uint8ClampedBufferAttribute,
//	Uint8BufferAttribute,
//	Int8BufferAttribute,
//	BufferAttribute
