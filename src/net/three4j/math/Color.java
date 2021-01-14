package net.three4j.math;

import net.three4j.math.MathUtils;

public class Color {
	private double _r;
	private double _g;
	private double _b;

//const _colorKeywords = { 'aliceblue': 0xF0F8FF, 'antiquewhite': 0xFAEBD7, 'aqua': 0x00FFFF, 'aquamarine': 0x7FFFD4, 'azure': 0xF0FFFF,
//	'beige': 0xF5F5DC, 'bisque': 0xFFE4C4, 'black': 0x000000, 'blanchedalmond': 0xFFEBCD, 'blue': 0x0000FF, 'blueviolet': 0x8A2BE2,
//	'brown': 0xA52A2A, 'burlywood': 0xDEB887, 'cadetblue': 0x5F9EA0, 'chartreuse': 0x7FFF00, 'chocolate': 0xD2691E, 'coral': 0xFF7F50,
//	'cornflowerblue': 0x6495ED, 'cornsilk': 0xFFF8DC, 'crimson': 0xDC143C, 'cyan': 0x00FFFF, 'darkblue': 0x00008B, 'darkcyan': 0x008B8B,
//	'darkgoldenrod': 0xB8860B, 'darkgray': 0xA9A9A9, 'darkgreen': 0x006400, 'darkgrey': 0xA9A9A9, 'darkkhaki': 0xBDB76B, 'darkmagenta': 0x8B008B,
//	'darkolivegreen': 0x556B2F, 'darkorange': 0xFF8C00, 'darkorchid': 0x9932CC, 'darkred': 0x8B0000, 'darksalmon': 0xE9967A, 'darkseagreen': 0x8FBC8F,
//	'darkslateblue': 0x483D8B, 'darkslategray': 0x2F4F4F, 'darkslategrey': 0x2F4F4F, 'darkturquoise': 0x00CED1, 'darkviolet': 0x9400D3,
//	'deeppink': 0xFF1493, 'deepskyblue': 0x00BFFF, 'dimgray': 0x696969, 'dimgrey': 0x696969, 'dodgerblue': 0x1E90FF, 'firebrick': 0xB22222,
//	'floralwhite': 0xFFFAF0, 'forestgreen': 0x228B22, 'fuchsia': 0xFF00FF, 'gainsboro': 0xDCDCDC, 'ghostwhite': 0xF8F8FF, 'gold': 0xFFD700,
//	'goldenrod': 0xDAA520, 'gray': 0x808080, 'green': 0x008000, 'greenyellow': 0xADFF2F, 'grey': 0x808080, 'honeydew': 0xF0FFF0, 'hotpink': 0xFF69B4,
//	'indianred': 0xCD5C5C, 'indigo': 0x4B0082, 'ivory': 0xFFFFF0, 'khaki': 0xF0E68C, 'lavender': 0xE6E6FA, 'lavenderblush': 0xFFF0F5, 'lawngreen': 0x7CFC00,
//	'lemonchiffon': 0xFFFACD, 'lightblue': 0xADD8E6, 'lightcoral': 0xF08080, 'lightcyan': 0xE0FFFF, 'lightgoldenrodyellow': 0xFAFAD2, 'lightgray': 0xD3D3D3,
//	'lightgreen': 0x90EE90, 'lightgrey': 0xD3D3D3, 'lightpink': 0xFFB6C1, 'lightsalmon': 0xFFA07A, 'lightseagreen': 0x20B2AA, 'lightskyblue': 0x87CEFA,
//	'lightslategray': 0x778899, 'lightslategrey': 0x778899, 'lightsteelblue': 0xB0C4DE, 'lightyellow': 0xFFFFE0, 'lime': 0x00FF00, 'limegreen': 0x32CD32,
//	'linen': 0xFAF0E6, 'magenta': 0xFF00FF, 'maroon': 0x800000, 'mediumaquamarine': 0x66CDAA, 'mediumblue': 0x0000CD, 'mediumorchid': 0xBA55D3,
//	'mediumpurple': 0x9370DB, 'mediumseagreen': 0x3CB371, 'mediumslateblue': 0x7B68EE, 'mediumspringgreen': 0x00FA9A, 'mediumturquoise': 0x48D1CC,
//	'mediumvioletred': 0xC71585, 'midnightblue': 0x191970, 'mintcream': 0xF5FFFA, 'mistyrose': 0xFFE4E1, 'moccasin': 0xFFE4B5, 'navajowhite': 0xFFDEAD,
//	'navy': 0x000080, 'oldlace': 0xFDF5E6, 'olive': 0x808000, 'olivedrab': 0x6B8E23, 'orange': 0xFFA500, 'orangered': 0xFF4500, 'orchid': 0xDA70D6,
//	'palegoldenrod': 0xEEE8AA, 'palegreen': 0x98FB98, 'paleturquoise': 0xAFEEEE, 'palevioletred': 0xDB7093, 'papayawhip': 0xFFEFD5, 'peachpuff': 0xFFDAB9,
//	'peru': 0xCD853F, 'pink': 0xFFC0CB, 'plum': 0xDDA0DD, 'powderblue': 0xB0E0E6, 'purple': 0x800080, 'rebeccapurple': 0x663399, 'red': 0xFF0000, 'rosybrown': 0xBC8F8F,
//	'royalblue': 0x4169E1, 'saddlebrown': 0x8B4513, 'salmon': 0xFA8072, 'sandybrown': 0xF4A460, 'seagreen': 0x2E8B57, 'seashell': 0xFFF5EE,
//	'sienna': 0xA0522D, 'silver': 0xC0C0C0, 'skyblue': 0x87CEEB, 'slateblue': 0x6A5ACD, 'slategray': 0x708090, 'slategrey': 0x708090, 'snow': 0xFFFAFA,
//	'springgreen': 0x00FF7F, 'steelblue': 0x4682B4, 'tan': 0xD2B48C, 'teal': 0x008080, 'thistle': 0xD8BFD8, 'tomato': 0xFF6347, 'turquoise': 0x40E0D0,
//	'violet': 0xEE82EE, 'wheat': 0xF5DEB3, 'white': 0xFFFFFF, 'whitesmoke': 0xF5F5F5, 'yellow': 0xFFFF00, 'yellowgreen': 0x9ACD32 };
//
//const _hslA = { h: 0, s: 0, l: 0 };
//const _hslB = { h: 0, s: 0, l: 0 };
//
public static double hue2rgb( double p, double q, double t ) {

	if ( t < 0 ) t += 1;
	if ( t > 1 ) t -= 1;
	if ( t < 1 / 6 ) return p + ( q - p ) * 6 * t;
	if ( t < 1 / 2 ) return q;
	if ( t < 2 / 3 ) return p + ( q - p ) * 6 * ( 2 / 3 - t );
	return p;
}

public static double SRGBToLinear( double c ) {

	return ( c < 0.04045 ) ? c * 0.0773993808 : Math.pow( c * 0.9478672986 + 0.0521327014, 2.4 );

}

public static double LinearToSRGB( double c ) {

	return ( c < 0.0031308 ) ? c * 12.92 : 1.055 * ( Math.pow( c, 0.41666 ) ) - 0.055;

}

	public Color(Color color) {
		this.copy(color);
	}

	public Color(String s) {
		
	}

	public Color(int x) {
		
	}

	public Color ( int r, int g, int b ) {
		this.setRGB( r, g, b );
	}

	public Color(double r, double g, double b) {
		this._r = r;
		this._g = g;
		this._b = b;
	}	
	
//	public Color set( value ) {
//
//		if ( value && value.isColor ) {
//
//			this.copy( value );
//
//		} else if ( typeof value == 'number' ) {
//
//			this.setHex( value );
//
//		} else if ( typeof value == 'string' ) {
//
//			this.setStyle( value );
//
//		}
//
//		return this;
//
//	}
//
	public Color setScalar( double scalar ) {

		this._r = scalar;
		this._g = scalar;
		this._b = scalar;

		return this;

	}

	public Color setHex( int hex ) {

		this._r = ( hex >> 16 & 255 ) / 255;
		this._g = ( hex >> 8 & 255 ) / 255;
		this._b = ( hex & 255 ) / 255;

		return this;

	}

	public Color setRGB( int r, int g, int b ) {

		this._r = r;
		this._g = g;
		this._b = b;

		return this;

	}

//	public Color setHSL( h, s, l ) {
//
//		// h,s,l ranges are in 0.0 - 1.0
//		h = MathUtils.euclideanModulo( h, 1 );
//		s = MathUtils.clamp( s, 0, 1 );
//		l = MathUtils.clamp( l, 0, 1 );
//
//		if ( s == 0 ) {
//
//			this._r = this._g = this._b = l;
//
//		} else {
//
//			const p = l <= 0.5 ? l * ( 1 + s ) : l + s - ( l * s );
//			const q = ( 2 * l ) - p;
//
//			this._r = hue2rgb( q, p, h + 1 / 3 );
//			this._g = hue2rgb( q, p, h );
//			this._b = hue2rgb( q, p, h - 1 / 3 );
//
//		}
//
//		return this;
//
//	}
//
//	public Color setStyle( style ) {
//
//		function handleAlpha( string ) {
//
//			if ( string == undefined ) return;
//
//			if ( parseFloat( string ) < 1 ) {
//
//				console.warn( 'THREE.Color: Alpha component of ' + style + ' will be ignored.' );
//
//			}
//
//		}
//
//
//		let m;
//
//		if ( m = /^((?:rgb|hsl)a?)\(\s*([^\)]*)\)/.exec( style ) ) {
//
//			// rgb / hsl
//
//			let color;
//			const name = m[ 1 ];
//			const components = m[ 2 ];
//
//			switch ( name ) {
//
//				case 'rgb':
//				case 'rgba':
//
//					if ( color = /^(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec( components ) ) {
//
//						// rgb(255,0,0) rgba(255,0,0,0.5)
//						this._r = Math.min( 255, parseInt( color[ 1 ], 10 ) ) / 255;
//						this._g = Math.min( 255, parseInt( color[ 2 ], 10 ) ) / 255;
//						this._b = Math.min( 255, parseInt( color[ 3 ], 10 ) ) / 255;
//
//						handleAlpha( color[ 4 ] );
//
//						return this;
//
//					}
//
//					if ( color = /^(\d+)\%\s*,\s*(\d+)\%\s*,\s*(\d+)\%\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec( components ) ) {
//
//						// rgb(100%,0%,0%) rgba(100%,0%,0%,0.5)
//						this._r = Math.min( 100, parseInt( color[ 1 ], 10 ) ) / 100;
//						this._g = Math.min( 100, parseInt( color[ 2 ], 10 ) ) / 100;
//						this._b = Math.min( 100, parseInt( color[ 3 ], 10 ) ) / 100;
//
//						handleAlpha( color[ 4 ] );
//
//						return this;
//
//					}
//
//					break;
//
//				case 'hsl':
//				case 'hsla':
//
//					if ( color = /^(\d*\.?\d+)\s*,\s*(\d+)\%\s*,\s*(\d+)\%\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec( components ) ) {
//
//						// hsl(120,50%,50%) hsla(120,50%,50%,0.5)
//						const h = parseFloat( color[ 1 ] ) / 360;
//						const s = parseInt( color[ 2 ], 10 ) / 100;
//						const l = parseInt( color[ 3 ], 10 ) / 100;
//
//						handleAlpha( color[ 4 ] );
//
//						return this.setHSL( h, s, l );
//
//					}
//
//					break;
//
//			}
//
//		} else if ( m = /^\#([A-Fa-f\d]+)$/.exec( style ) ) {
//
//			// hex color
//
//			const hex = m[ 1 ];
//			const size = hex.length;
//
//			if ( size == 3 ) {
//
//				// #ff0
//				this._r = parseInt( hex.charAt( 0 ) + hex.charAt( 0 ), 16 ) / 255;
//				this._g = parseInt( hex.charAt( 1 ) + hex.charAt( 1 ), 16 ) / 255;
//				this._b = parseInt( hex.charAt( 2 ) + hex.charAt( 2 ), 16 ) / 255;
//
//				return this;
//
//			} else if ( size == 6 ) {
//
//				// #ff0000
//				this._r = parseInt( hex.charAt( 0 ) + hex.charAt( 1 ), 16 ) / 255;
//				this._g = parseInt( hex.charAt( 2 ) + hex.charAt( 3 ), 16 ) / 255;
//				this._b = parseInt( hex.charAt( 4 ) + hex.charAt( 5 ), 16 ) / 255;
//
//				return this;
//
//			}
//
//		}
//
//		if ( style && style.length > 0 ) {
//
//			return this.setColorName( style );
//
//		}
//
//		return this;
//
//	}
//
//	public Color setColorName( style ) {
//
//		// color keywords
//		const hex = _colorKeywords[ style ];
//
//		if ( hex != undefined ) {
//
//			// red
//			this.setHex( hex );
//
//		} else {
//
//			// unknown color
//			console.warn( 'THREE.Color: Unknown color ' + style );
//
//		}
//
//		return this;
//
//	}

	public Color clone() {

		return new Color( this._r, this._g, this._b );

	}

	public Color copy( Color color ) {

		this._r = color._r;
		this._g = color._g;
		this._b = color._b;

		return this;

	}

	public Color copyGammaToLinear( Color color) {
		return copyGammaToLinear(color, 2);
	}
	
	public Color copyGammaToLinear( Color color, double gammaFactor ) {

		this._r = (int)Math.pow( (double)color._r, gammaFactor );
		this._g = (int)Math.pow( (double)color._g, gammaFactor );
		this._b = (int)Math.pow( (double)color._b, gammaFactor );

		return this;

	}

//	public Color copyLinearToGamma( color, gammaFactor = 2.0 ) {
//
//		const safeInverse = ( gammaFactor > 0 ) ? ( 1.0 / gammaFactor ) : 1.0;
//
//		this._r = Math.pow( color._r, safeInverse );
//		this._g = Math.pow( color._g, safeInverse );
//		this._b = Math.pow( color._b, safeInverse );
//
//		return this;
//
//	}
//
//	public Color convertGammaToLinear( gammaFactor ) {
//
//		this.copyGammaToLinear( this, gammaFactor );
//
//		return this;
//
//	}
//
//	public Color convertLinearToGamma( gammaFactor ) {
//
//		this.copyLinearToGamma( this, gammaFactor );
//
//		return this;
//
//	}
//
//	public Color copySRGBToLinear( color ) {
//
//		this._r = SRGBToLinear( color._r );
//		this._g = SRGBToLinear( color._g );
//		this._b = SRGBToLinear( color._b );
//
//		return this;
//
//	}
//
//	public Color copyLinearToSRGB( color ) {
//
//		this._r = LinearToSRGB( color._r );
//		this._g = LinearToSRGB( color._g );
//		this._b = LinearToSRGB( color._b );
//
//		return this;
//
//	}
//
//	public Color convertSRGBToLinear() {
//
//		this.copySRGBToLinear( this );
//
//		return this;
//
//	}
//
//	public Color convertLinearToSRGB() {
//
//		this.copyLinearToSRGB( this );
//
//		return this;
//
//	}

	public double getHex() {

		return ( this._r * 255 ) << 16 ^ ( this._g * 255 ) << 8 ^ ( this._b * 255 ) << 0;

	}

//	public double getHexString() {
//
//		return ( '000000' + this.getHex().toString( 16 ) ).slice( - 6 );
//
//	}
//
//	public double getHSL( target ) {
//
//		// h,s,l ranges are in 0.0 - 1.0
//
//		if ( target == undefined ) {
//
//			console.warn( 'THREE.Color: .getHSL() target is now required' );
//			target = { h: 0, s: 0, l: 0 };
//
//		}
//
//		const r = this._r, g = this._g, b = this._b;
//
//		const max = Math.max( r, g, b );
//		const min = Math.min( r, g, b );
//
//		let hue, saturation;
//		const lightness = ( min + max ) / 2.0;
//
//		if ( min == max ) {
//
//			hue = 0;
//			saturation = 0;
//
//		} else {
//
//			const delta = max - min;
//
//			saturation = lightness <= 0.5 ? delta / ( max + min ) : delta / ( 2 - max - min );
//
//			switch ( max ) {
//
//				case r: hue = ( g - b ) / delta + ( g < b ? 6 : 0 ); break;
//				case g: hue = ( b - r ) / delta + 2; break;
//				case b: hue = ( r - g ) / delta + 4; break;
//
//			}
//
//			hue /= 6;
//
//		}
//
//		target.h = hue;
//		target.s = saturation;
//		target.l = lightness;
//
//		return target;
//
//	}
//
//	public double getStyle() {
//
//		return 'rgb(' + ( ( this._r * 255 ) | 0 ) + ',' + ( ( this._g * 255 ) | 0 ) + ',' + ( ( this._b * 255 ) | 0 ) + ')';
//
//	}
//
//	public Color offsetHSL( h, s, l ) {
//
//		this.getHSL( _hslA );
//
//		_hslA.h += h; _hslA.s += s; _hslA.l += l;
//
//		this.setHSL( _hslA.h, _hslA.s, _hslA.l );
//
//		return this;
//
//	}

	public Color add( Color color ) {

		this._r += color._r;
		this._g += color._g;
		this._b += color._b;

		return this;

	}

	public Color addColors( Color color1, Color color2 ) {

		this._r = color1._r + color2._r;
		this._g = color1._g + color2._g;
		this._b = color1._b + color2._b;

		return this;

	}

	public Color addScalar( double s ) {

		this._r += s;
		this._g += s;
		this._b += s;

		return this;

	}

	public Color sub( Color color ) {

		this._r = Math.max( 0, this._r - color._r );
		this._g = Math.max( 0, this._g - color._g );
		this._b = Math.max( 0, this._b - color._b );

		return this;

	}

	public Color multiply( Color color ) {

		this._r *= color._r;
		this._g *= color._g;
		this._b *= color._b;

		return this;

	}

	public Color multiplyScalar( double s ) {

		this._r *= s;
		this._g *= s;
		this._b *= s;

		return this;

	}

	public Color lerp( Color color, double alpha ) {

		this._r += ( color._r - this._r ) * alpha;
		this._g += ( color._g - this._g ) * alpha;
		this._b += ( color._b - this._b ) * alpha;

		return this;

	}

//	public Color lerpHSL( color, alpha ) {
//
//		this.getHSL( _hslA );
//		color.getHSL( _hslB );
//
//		const h = MathUtils.lerp( _hslA.h, _hslB.h, alpha );
//		const s = MathUtils.lerp( _hslA.s, _hslB.s, alpha );
//		const l = MathUtils.lerp( _hslA.l, _hslB.l, alpha );
//
//		this.setHSL( h, s, l );
//
//		return this;
//
//	}
//
	public boolean equals( Color c ) {

		return ( c._r == this._r ) && ( c._g == this._g ) && ( c._b == this._b );

	}

//	public Color fromArray( array, offset = 0 ) {
//
//		this._r = array[ offset ];
//		this._g = array[ offset + 1 ];
//		this._b = array[ offset + 2 ];
//
//		return this;
//
//	}
//
	public int[] toArray(int[] array) {
		return toArray(array, 0);
	}
	
	public int[] toArray( int[] array, int offset ) {

		array[ offset ] = this._r;
		array[ offset + 1 ] = this._g;
		array[ offset + 2 ] = this._b;

		return array;

	}

//	public Color fromBufferAttribute( attribute, index ) {
//
//		this._r = attribute.getX( index );
//		this._g = attribute.getY( index );
//		this._b = attribute.getZ( index );
//
//		if ( attribute.normalized == true ) {
//
//			// assuming Uint8Array
//
//			this._r /= 255;
//			this._g /= 255;
//			this._b /= 255;
//
//		}
//
//		return this;
//
//	}
//
//	public double toJSON() {
//
//		return this.getHex();
//
//	}
//
//final static Color.NAMES = _colorKeywords;
//Color.prototype._r = 1;
//Color.prototype._g = 1;
//Color.prototype._b = 1;

}
