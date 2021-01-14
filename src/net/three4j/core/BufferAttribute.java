package net.three4j.core;

import net.three4j.math.Vector2;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;

//import { Color } from "../math/Color.js";
import static net.three4j.constants.StaticDrawUsage;

import java.util.Arrays;

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
	}

	public String name;
	public double[] array;
	public int itemSize;
	public int count;
	public boolean normalized;
	public int usage;
	public int version;
	public UpdateRange updateRange;

	public BufferAttribute(double[] array, int itemSize, boolean normalized) {

		this.name = "";

		this.array = array;
		this.itemSize = itemSize;
		this.count = array.length / itemSize;
		this.normalized = normalized;

		this.usage = StaticDrawUsage;
		this.updateRange = new UpdateRange(0, -1);

		this.version = 0;

	}
	
	public void needsUpdate(boolean needsUpdate) {
		if (needsUpdate)
			this.version++;
	}

	public void onUploadCallback() {}
	
	public BufferAttribute setUsage( int value ) {

		this.usage = value;

		return this;

	}

	public BufferAttribute copy( BufferAttribute source ) {

		this.name = source.name;
		this.array = Arrays.copyOf( source.array, source.array.length );
		this.itemSize = source.itemSize;
		this.count = source.count;
		this.normalized = source.normalized;

		this.usage = source.usage;

		return this;

	}

	public BufferAttribute copyAt( int index1, BufferAttribute attribute, int index2 ) {

		index1 *= this.itemSize;
		index2 *= attribute.itemSize;

		for ( int i = 0, l = this.itemSize; i < l; i ++ ) {

			this.array[ index1 + i ] = attribute.array[ index2 + i ];

		}

		return this;

	}

	public BufferAttribute copyArray( double[] array ) {

		this.array = Arrays.copyOf( array, array.length );

		return this;

	}

//	public BufferAttribute copyColorsArray( Color[] colors ) {
//
//		final double[] array = this.array;
//		int offset = 0;
//
//		for ( int i = 0, l = colors.length; i < l; i ++ ) {
//
//			Color color = colors[ i ];
//
//			if ( color == null ) {
//
//				console.warn( "THREE.BufferAttribute.copyColorsArray(): color is undefined" + i );
//				color = new Color();
//
//			}
//
//			array[ offset ++ ] = color.r;
//			array[ offset ++ ] = color.g;
//			array[ offset ++ ] = color.b;
//
//		}
//
//		return this;
//
//	}

//	public BufferAttribute copyVector2sArray( vectors ) {
//
//		const array = this.array;
//		let offset = 0;
//
//		for ( let i = 0, l = vectors.length; i < l; i ++ ) {
//
//			let vector = vectors[ i ];
//
//			if ( vector == undefined ) {
//
//				console.warn( "THREE.BufferAttribute.copyVector2sArray(): vector is undefined", i );
//				vector = new Vector2();
//
//			}
//
//			array[ offset ++ ] = vector.x;
//			array[ offset ++ ] = vector.y;
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute copyVector3sArray( vectors ) {
//
//		const array = this.array;
//		let offset = 0;
//
//		for ( let i = 0, l = vectors.length; i < l; i ++ ) {
//
//			let vector = vectors[ i ];
//
//			if ( vector == undefined ) {
//
//				console.warn( "THREE.BufferAttribute.copyVector3sArray(): vector is undefined", i );
//				vector = new Vector3();
//
//			}
//
//			array[ offset ++ ] = vector.x;
//			array[ offset ++ ] = vector.y;
//			array[ offset ++ ] = vector.z;
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute copyVector4sArray( vectors ) {
//
//		const array = this.array;
//		let offset = 0;
//
//		for ( let i = 0, l = vectors.length; i < l; i ++ ) {
//
//			let vector = vectors[ i ];
//
//			if ( vector == undefined ) {
//
//				console.warn( "THREE.BufferAttribute.copyVector4sArray(): vector is undefined", i );
//				vector = new Vector4();
//
//			}
//
//			array[ offset ++ ] = vector.x;
//			array[ offset ++ ] = vector.y;
//			array[ offset ++ ] = vector.z;
//			array[ offset ++ ] = vector.w;
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute applyMatrix3( m ) {
//
//		if ( this.itemSize == 2 ) {
//
//			for ( let i = 0, l = this.count; i < l; i ++ ) {
//
//				_vector2.fromBufferAttribute( this, i );
//				_vector2.applyMatrix3( m );
//
//				this.setXY( i, _vector2.x, _vector2.y );
//
//			}
//
//		} else if ( this.itemSize == 3 ) {
//
//			for ( let i = 0, l = this.count; i < l; i ++ ) {
//
//				_vector.fromBufferAttribute( this, i );
//				_vector.applyMatrix3( m );
//
//				this.setXYZ( i, _vector.x, _vector.y, _vector.z );
//
//			}
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute applyMatrix4( m ) {
//
//		for ( let i = 0, l = this.count; i < l; i ++ ) {
//
//			_vector.x = this.getX( i );
//			_vector.y = this.getY( i );
//			_vector.z = this.getZ( i );
//
//			_vector.applyMatrix4( m );
//
//			this.setXYZ( i, _vector.x, _vector.y, _vector.z );
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute applyNormalMatrix( m ) {
//
//		for ( let i = 0, l = this.count; i < l; i ++ ) {
//
//			_vector.x = this.getX( i );
//			_vector.y = this.getY( i );
//			_vector.z = this.getZ( i );
//
//			_vector.applyNormalMatrix( m );
//
//			this.setXYZ( i, _vector.x, _vector.y, _vector.z );
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute transformDirection( m ) {
//
//		for ( let i = 0, l = this.count; i < l; i ++ ) {
//
//			_vector.x = this.getX( i );
//			_vector.y = this.getY( i );
//			_vector.z = this.getZ( i );
//
//			_vector.transformDirection( m );
//
//			this.setXYZ( i, _vector.x, _vector.y, _vector.z );
//
//		}
//
//		return this;
//
//	},
//
//	public BufferAttribute set( value, offset = 0 ) {
//
//		this.array.set( value, offset );
//
//		return this;
//
//	},
//
	public double getX(int index) {

		// return this.array[ index * this.itemSize ];
		return 0;
	}

//	public BufferAttribute setX( index, x ) {
//
//		this.array[ index * this.itemSize ] = x;
//
//		return this;
//
//	},
//
	public double getY(int index) {

		// return this.array[ index * this.itemSize + 1 ];
		return 0;

	}

//	public BufferAttribute setY( index, y ) {
//
//		this.array[ index * this.itemSize + 1 ] = y;
//
//		return this;
//
//	},

	public double getZ(int index) {

		// return this.array[ index * this.itemSize + 2 ];
		return 0;
	}

//	public BufferAttribute setZ( index, z ) {
//
//		this.array[ index * this.itemSize + 2 ] = z;
//
//		return this;
//
//	},

	public double getW(int index) {
		// return this.array[ index * this.itemSize + 3 ];
		throw new UnsupportedOperationException(
				"Not implemented yet: " + new Throwable().getStackTrace()[0].getMethodName() + ":"
						+ new Throwable().getStackTrace()[0].getLineNumber());
	}

//	public BufferAttribute setW( index, w ) {
//
//		this.array[ index * this.itemSize + 3 ] = w;
//
//		return this;
//
//	},
//
//	public BufferAttribute setXY( index, x, y ) {
//
//		index *= this.itemSize;
//
//		this.array[ index + 0 ] = x;
//		this.array[ index + 1 ] = y;
//
//		return this;
//
//	},
//
//	public BufferAttribute setXYZ( index, x, y, z ) {
//
//		index *= this.itemSize;
//
//		this.array[ index + 0 ] = x;
//		this.array[ index + 1 ] = y;
//		this.array[ index + 2 ] = z;
//
//		return this;
//
//	},
//
//	public BufferAttribute setXYZW( index, x, y, z, w ) {
//
//		index *= this.itemSize;
//
//		this.array[ index + 0 ] = x;
//		this.array[ index + 1 ] = y;
//		this.array[ index + 2 ] = z;
//		this.array[ index + 3 ] = w;
//
//		return this;
//
//	},
//
//	public BufferAttribute onUpload( callback ) {
//
//		this.onUploadCallback = callback;
//
//		return this;
//
//	},
//
//	public BufferAttribute clone() {
//
//		return new this.constructor( this.array, this.itemSize ).copy( this );
//
//	},
//
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
//
//}
//
////
//
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
//
//Int16BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Int16BufferAttribute.prototype.constructor = Int16BufferAttribute;
//
//
//function Uint16BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Uint16Array( array ), itemSize, normalized );
//
//}
//
//Uint16BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Uint16BufferAttribute.prototype.constructor = Uint16BufferAttribute;
//
//
//function Int32BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Int32Array( array ), itemSize, normalized );
//
//}
//
//Int32BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Int32BufferAttribute.prototype.constructor = Int32BufferAttribute;
//
//
//function Uint32BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Uint32Array( array ), itemSize, normalized );
//
//}
//
//Uint32BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Uint32BufferAttribute.prototype.constructor = Uint32BufferAttribute;
//
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
//
//Float32BufferAttribute.prototype = Object.create( BufferAttribute.prototype );
//Float32BufferAttribute.prototype.constructor = Float32BufferAttribute;
//
//
//function Float64BufferAttribute( array, itemSize, normalized ) {
//
//	BufferAttribute.call( this, new Float64Array( array ), itemSize, normalized );
//
//}
//
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
}
