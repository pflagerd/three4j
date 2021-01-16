package net.three4j.textures;

import net.three4j.core.EventDispatcher;

import static net.three4j.THREE.console;

import static net.three4j.constants.MirroredRepeatWrapping;
import static net.three4j.constants.ClampToEdgeWrapping;
import static net.three4j.constants.RepeatWrapping;
import static net.three4j.constants.LinearEncoding;
import static net.three4j.constants.UnsignedByteType;

import java.util.Arrays;

import org.mozilla.dom.Image;

import static net.three4j.constants.RGBAFormat;
import static net.three4j.constants.LinearMipmapLinearFilter;
import static net.three4j.constants.LinearFilter;
import static net.three4j.constants.UVMapping;

import net.three4j.math.MathUtils;
import net.three4j.math.Vector2;
import net.three4j.math.Matrix3;
import net.three4j.extras.ImageUtils;

public class Texture extends EventDispatcher {

	private static int textureId = 0;

	private Image _image = Texture.DEFAULT_IMAGE;
	private int _mapping = Texture.DEFAULT_MAPPING;
	private int _wrapS = ClampToEdgeWrapping;
	private int _wrapT = ClampToEdgeWrapping;
	private int _magFilter = LinearFilter;
	private int _minFilter = LinearMipmapLinearFilter;
	private int _format = RGBAFormat;
	private int _type = UnsignedByteType;
	private int _anisotropy = 1;
	private int _encoding = LinearEncoding;
	private String _name = "";
	private String _uuid = MathUtils.generateUUID();
	private byte[] _mipmaps = new byte[0];
	private Vector2 _offset = new Vector2(0, 0);
	private Vector2 _repeat = new Vector2(1, 1);
	private Vector2 _center = new Vector2(0, 0);
	private double _rotation = 0;
	private Object _internalFormat = null; 

	private boolean _matrixAutoUpdate = true;
	private Matrix3 _matrix = new Matrix3();

	private boolean _generateMipmaps = true;
	private boolean _premultiplyAlpha = false;
	private boolean _flipY = true;
	private int _unpackAlignment = 4; // valid values: 1, 2, 4, 8 (see // http://www.khronos.org/opengles/sdk/docs/man/xhtml/glPixelStorei.xml)
	private int _version = 0;
	
	public Texture() {
		_image = Texture.DEFAULT_IMAGE;
		_mapping = Texture.DEFAULT_MAPPING;
		_wrapS = ClampToEdgeWrapping;
		_wrapT = ClampToEdgeWrapping;
		_magFilter = LinearFilter;
		_minFilter = LinearMipmapLinearFilter;
		_format = RGBAFormat;
		_type = UnsignedByteType;
		_anisotropy = 1;
		_encoding = LinearEncoding;
	}

	public int id() {
		return textureId++;
	}

	public String name() {
		return _name;
	}

	public Texture(Image image, int mapping, int wrapS, int wrapT, int magFilter, int minFilter, int format, int type, int anisotropy, int encoding) {

		this._mapping = mapping;

		this._wrapS = wrapS;
		this._wrapT = wrapT;

		this._magFilter = magFilter;
		this._minFilter = minFilter;

		this._anisotropy = anisotropy;

		this._format = format;
		this._internalFormat = null;
		this._type = type;

		this._offset = new Vector2(0, 0);
		this._repeat = new Vector2(1, 1);
		this._center = new Vector2(0, 0);
		this._rotation = 0;

		this._matrixAutoUpdate = true;
		this._matrix = new Matrix3();

		this._generateMipmaps = true;
		this._premultiplyAlpha = false;
		this._flipY = true;
		this._unpackAlignment = 4; // valid values: 1, 2, 4, 8 (see
									// http://www.khronos.org/opengles/sdk/docs/man/xhtml/glPixelStorei.xml)

		// Values of encoding !== THREE.LinearEncoding only supported on map, envMap and
		// emissiveMap.
		//
		// Also changing the encoding after already used by a Material will not
		// automatically make the Material
		// update. You need to explicitly call Material.needsUpdate to trigger it to
		// recompile.
		this._encoding = encoding;

		this._version = 0;
//		this._onUpdate = null;

	}

	public static Image DEFAULT_IMAGE = null;
	public static int DEFAULT_MAPPING = UVMapping;

	public void updateMatrix() {

		this._matrix.setUvTransform( this._offset.x, this._offset.y, this._repeat.x, this._repeat.y, this._rotation, this._center.x, this._center.y );

	}

	public Texture clone() {

		return new Texture().copy( this );

	}

	public Texture copy( Texture source ) {

		this._name = source._name;

		this._image = source._image;
		this._mipmaps = Arrays.copyOf(source._mipmaps, source._mipmaps.length);

		this._mapping = source._mapping;

		this._wrapS = source._wrapS;
		this._wrapT = source._wrapT;

		this._magFilter = source._magFilter;
		this._minFilter = source._minFilter;

		this._anisotropy = source._anisotropy;

		this._format = source._format;
		this._internalFormat = source._internalFormat;
		this._type = source._type;

		this._offset.copy( source._offset );
		this._repeat.copy( source._repeat );
		this._center.copy( source._center );
		this._rotation = source._rotation;

		this._matrixAutoUpdate = source._matrixAutoUpdate;
		this._matrix.copy( source._matrix );

		this._generateMipmaps = source._generateMipmaps;
		this._premultiplyAlpha = source._premultiplyAlpha;
		this._flipY = source._flipY;
		this._unpackAlignment = source._unpackAlignment;
		this._encoding = source._encoding;

		return this;

	}

//	toJSON: function ( meta ) {
//
//		const isRootObject = ( meta === undefined || typeof meta === 'string' );
//
//		if ( ! isRootObject && meta.textures[ this.uuid ] !== undefined ) {
//
//			return meta.textures[ this.uuid ];
//
//		}
//
//		const output = {
//
//			metadata: {
//				version: 4.5,
//				type: 'Texture',
//				generator: 'Texture.toJSON'
//			},
//
//			uuid: this.uuid,
//			name: this.name,
//
//			mapping: this.mapping,
//
//			repeat: [ this.repeat.x, this.repeat.y ],
//			offset: [ this.offset.x, this.offset.y ],
//			center: [ this.center.x, this.center.y ],
//			rotation: this.rotation,
//
//			wrap: [ this.wrapS, this.wrapT ],
//
//			format: this.format,
//			type: this.type,
//			encoding: this.encoding,
//
//			minFilter: this.minFilter,
//			magFilter: this.magFilter,
//			anisotropy: this.anisotropy,
//
//			flipY: this.flipY,
//
//			premultiplyAlpha: this.premultiplyAlpha,
//			unpackAlignment: this.unpackAlignment
//
//		};
//
//		if ( this.image !== undefined ) {
//
//			// TODO: Move to THREE.Image
//
//			const image = this.image;
//
//			if ( image.uuid === undefined ) {
//
//				image.uuid = MathUtils.generateUUID(); // UGH
//
//			}
//
//			if ( ! isRootObject && meta.images[ image.uuid ] === undefined ) {
//
//				let url;
//
//				if ( Array.isArray( image ) ) {
//
//					// process array of images e.g. CubeTexture
//
//					url = [];
//
//					for ( let i = 0, l = image.length; i < l; i ++ ) {
//
//						// check cube texture with data textures
//
//						if ( image[ i ].isDataTexture ) {
//
//							url.push( serializeImage( image[ i ].image ) );
//
//						} else {
//
//							url.push( serializeImage( image[ i ] ) );
//
//						}
//
//					}
//
//				} else {
//
//					// process single image
//
//					url = serializeImage( image );
//
//				}
//
//				meta.images[ image.uuid ] = {
//					uuid: image.uuid,
//					url: url
//				};
//
//			}
//
//			output.image = image.uuid;
//
//		}
//
//		if ( ! isRootObject ) {
//
//			meta.textures[ this.uuid ] = output;
//
//		}
//
//		return output;
//
//	},
//
//	dispose: function () {
//
//		this.dispatchEvent( { type: 'dispose' } );
//
//	},
//
//	transformUv: function ( uv ) {
//
//		if ( this.mapping !== UVMapping ) return uv;
//
//		uv.applyMatrix3( this.matrix );
//
//		if ( uv.x < 0 || uv.x > 1 ) {
//
//			switch ( this.wrapS ) {
//
//				case RepeatWrapping:
//
//					uv.x = uv.x - Math.floor( uv.x );
//					break;
//
//				case ClampToEdgeWrapping:
//
//					uv.x = uv.x < 0 ? 0 : 1;
//					break;
//
//				case MirroredRepeatWrapping:
//
//					if ( Math.abs( Math.floor( uv.x ) % 2 ) === 1 ) {
//
//						uv.x = Math.ceil( uv.x ) - uv.x;
//
//					} else {
//
//						uv.x = uv.x - Math.floor( uv.x );
//
//					}
//
//					break;
//
//			}
//
//		}
//
//		if ( uv.y < 0 || uv.y > 1 ) {
//
//			switch ( this.wrapT ) {
//
//				case RepeatWrapping:
//
//					uv.y = uv.y - Math.floor( uv.y );
//					break;
//
//				case ClampToEdgeWrapping:
//
//					uv.y = uv.y < 0 ? 0 : 1;
//					break;
//
//				case MirroredRepeatWrapping:
//
//					if ( Math.abs( Math.floor( uv.y ) % 2 ) === 1 ) {
//
//						uv.y = Math.ceil( uv.y ) - uv.y;
//
//					} else {
//
//						uv.y = uv.y - Math.floor( uv.y );
//
//					}
//
//					break;
//
//			}
//
//		}
//
//		if ( this.flipY ) {
//
//			uv.y = 1 - uv.y;
//
//		}
//
//		return uv;
//
//	}
	
	public void needsUpdate(boolean needsUpdate) {
		if (needsUpdate)
			this._version++;
	}
	
	public static class SerializedImage {
		public byte[] data = new byte[0];
		public int width;
		public int height;
		public String type = "";
	}

	public Object serializeImage( Image image ) {

//		if ( ( typeof HTMLImageElement !== 'undefined' && image instanceof HTMLImageElement ) ||
//			( typeof HTMLCanvasElement !== 'undefined' && image instanceof HTMLCanvasElement ) ||
//			( typeof ImageBitmap !== 'undefined' && image instanceof ImageBitmap ) ) {
//	
//			// default images
//	
//			return ImageUtils.getDataURL( image );
//	
//		} else {
//	
			if ( image.data != null) {
				// images of DataTexture
				SerializedImage serializedImage = new SerializedImage();
				serializedImage.data = Arrays.copyOf( image.data, image.data.length );
				serializedImage.width = image.width;
				serializedImage.height = image.height;
				serializedImage.type = image.dataType;
				return serializedImage;
			} else {
	
				console.warn( "THREE.Texture: Unable to serialize Texture." );
				return new SerializedImage();
	
			}
	
		}
//
//	}
}
