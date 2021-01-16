package net.three4j.textures;

import net.three4j.core.EventDispatcher;

import static net.three4j.constants.MirroredRepeatWrapping;
import static net.three4j.constants.ClampToEdgeWrapping;
import static net.three4j.constants.RepeatWrapping;
import static net.three4j.constants.LinearEncoding;
import static net.three4j.constants.UnsignedByteType;
import static net.three4j.constants.RGBAFormat;
import static net.three4j.constants.LinearMipmapLinearFilter;
import static net.three4j.constants.LinearFilter;
import static net.three4j.constants.UVMapping;

import net.three4j.math.MathUtils;
import net.three4j.math.Vector2;
import net.three4j.math.Matrix3;
import net.three4j.extras.ImageUtils;


public class Texture {

	int textureId = 0;
	
//function Texture( image = Texture.DEFAULT_IMAGE, mapping = Texture.DEFAULT_MAPPING, wrapS = ClampToEdgeWrapping, wrapT = ClampToEdgeWrapping, magFilter = LinearFilter, minFilter = LinearMipmapLinearFilter, format = RGBAFormat, type = UnsignedByteType, anisotropy = 1, encoding = LinearEncoding ) {
//
//	Object.defineProperty( this, 'id', { value: textureId ++ } );
//
//	this.uuid = MathUtils.generateUUID();
//
//	this.name = '';
//
//	this.image = image;
//	this.mipmaps = [];
//
//	this.mapping = mapping;
//
//	this.wrapS = wrapS;
//	this.wrapT = wrapT;
//
//	this.magFilter = magFilter;
//	this.minFilter = minFilter;
//
//	this.anisotropy = anisotropy;
//
//	this.format = format;
//	this.internalFormat = null;
//	this.type = type;
//
//	this.offset = new Vector2( 0, 0 );
//	this.repeat = new Vector2( 1, 1 );
//	this.center = new Vector2( 0, 0 );
//	this.rotation = 0;
//
//	this.matrixAutoUpdate = true;
//	this.matrix = new Matrix3();
//
//	this.generateMipmaps = true;
//	this.premultiplyAlpha = false;
//	this.flipY = true;
//	this.unpackAlignment = 4;	// valid values: 1, 2, 4, 8 (see http://www.khronos.org/opengles/sdk/docs/man/xhtml/glPixelStorei.xml)
//
//	// Values of encoding !== THREE.LinearEncoding only supported on map, envMap and emissiveMap.
//	//
//	// Also changing the encoding after already used by a Material will not automatically make the Material
//	// update. You need to explicitly call Material.needsUpdate to trigger it to recompile.
//	this.encoding = encoding;
//
//	this.version = 0;
//	this.onUpdate = null;
//
//}
//
//Texture.DEFAULT_IMAGE = undefined;
//Texture.DEFAULT_MAPPING = UVMapping;
//
//Texture.prototype = Object.assign( Object.create( EventDispatcher.prototype ), {
//
//	constructor: Texture,
//
//	isTexture: true,
//
//	updateMatrix: function () {
//
//		this.matrix.setUvTransform( this.offset.x, this.offset.y, this.repeat.x, this.repeat.y, this.rotation, this.center.x, this.center.y );
//
//	},
//
//	clone: function () {
//
//		return new this.constructor().copy( this );
//
//	},
//
//	copy: function ( source ) {
//
//		this.name = source.name;
//
//		this.image = source.image;
//		this.mipmaps = source.mipmaps.slice( 0 );
//
//		this.mapping = source.mapping;
//
//		this.wrapS = source.wrapS;
//		this.wrapT = source.wrapT;
//
//		this.magFilter = source.magFilter;
//		this.minFilter = source.minFilter;
//
//		this.anisotropy = source.anisotropy;
//
//		this.format = source.format;
//		this.internalFormat = source.internalFormat;
//		this.type = source.type;
//
//		this.offset.copy( source.offset );
//		this.repeat.copy( source.repeat );
//		this.center.copy( source.center );
//		this.rotation = source.rotation;
//
//		this.matrixAutoUpdate = source.matrixAutoUpdate;
//		this.matrix.copy( source.matrix );
//
//		this.generateMipmaps = source.generateMipmaps;
//		this.premultiplyAlpha = source.premultiplyAlpha;
//		this.flipY = source.flipY;
//		this.unpackAlignment = source.unpackAlignment;
//		this.encoding = source.encoding;
//
//		return this;
//
//	},
//
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
//
//} );
//
//Object.defineProperty( Texture.prototype, 'needsUpdate', {
//
//	set: function ( value ) {
//
//		if ( value === true ) this.version ++;
//
//	}
//
//} );
//
//function serializeImage( image ) {
//
//	if ( ( typeof HTMLImageElement !== 'undefined' && image instanceof HTMLImageElement ) ||
//		( typeof HTMLCanvasElement !== 'undefined' && image instanceof HTMLCanvasElement ) ||
//		( typeof ImageBitmap !== 'undefined' && image instanceof ImageBitmap ) ) {
//
//		// default images
//
//		return ImageUtils.getDataURL( image );
//
//	} else {
//
//		if ( image.data ) {
//
//			// images of DataTexture
//
//			return {
//				data: Array.prototype.slice.call( image.data ),
//				width: image.width,
//				height: image.height,
//				type: image.data.constructor.name
//			};
//
//		} else {
//
//			console.warn( 'THREE.Texture: Unable to serialize Texture.' );
//			return {};
//
//		}
//
//	}
//
}
