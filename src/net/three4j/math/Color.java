package net.three4j.math;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

import static net.three4j.THREE.console;

import net.three4j.core.BufferAttribute;

public class Color {
	private double _r;
	private double _g;
	private double _b;

	private final static HashMap<String, Integer> _colorKeywords = new HashMap<>();

	static {
		_colorKeywords.put("aliceblue", 0xF0F8FF);
		_colorKeywords.put("antiquewhite", 0xFAEBD7);
		_colorKeywords.put("aqua", 0x00FFFF);
		_colorKeywords.put("aquamarine", 0x7FFFD4);
		_colorKeywords.put("azure", 0xF0FFFF);
		_colorKeywords.put("beige", 0xF5F5DC);
		_colorKeywords.put("bisque", 0xFFE4C4);
		_colorKeywords.put("black", 0x000000);
		_colorKeywords.put("blanchedalmond", 0xFFEBCD);
		_colorKeywords.put("blue", 0x0000FF);
		_colorKeywords.put("blueviolet", 0x8A2BE2);
		_colorKeywords.put("brown", 0xA52A2A);
		_colorKeywords.put("burlywood", 0xDEB887);
		_colorKeywords.put("cadetblue", 0x5F9EA0);
		_colorKeywords.put("chartreuse", 0x7FFF00);
		_colorKeywords.put("chocolate", 0xD2691E);
		_colorKeywords.put("coral", 0xFF7F50);
		_colorKeywords.put("cornflowerblue", 0x6495ED);
		_colorKeywords.put("cornsilk", 0xFFF8DC);
		_colorKeywords.put("crimson", 0xDC143C);
		_colorKeywords.put("cyan", 0x00FFFF);
		_colorKeywords.put("darkblue", 0x00008B);
		_colorKeywords.put("darkcyan", 0x008B8B);
		_colorKeywords.put("darkgoldenrod", 0xB8860B);
		_colorKeywords.put("darkgray", 0xA9A9A9);
		_colorKeywords.put("darkgreen", 0x006400);
		_colorKeywords.put("darkgrey", 0xA9A9A9);
		_colorKeywords.put("darkkhaki", 0xBDB76B);
		_colorKeywords.put("darkmagenta", 0x8B008B);
		_colorKeywords.put("darkolivegreen", 0x556B2F);
		_colorKeywords.put("darkorange", 0xFF8C00);
		_colorKeywords.put("darkorchid", 0x9932CC);
		_colorKeywords.put("darkred", 0x8B0000);
		_colorKeywords.put("darksalmon", 0xE9967A);
		_colorKeywords.put("darkseagreen", 0x8FBC8F);
		_colorKeywords.put("darkslateblue", 0x483D8B);
		_colorKeywords.put("darkslategray", 0x2F4F4F);
		_colorKeywords.put("darkslategrey", 0x2F4F4F);
		_colorKeywords.put("darkturquoise", 0x00CED1);
		_colorKeywords.put("darkviolet", 0x9400D3);
		_colorKeywords.put("deeppink", 0xFF1493);
		_colorKeywords.put("deepskyblue", 0x00BFFF);
		_colorKeywords.put("dimgray", 0x696969);
		_colorKeywords.put("dimgrey", 0x696969);
		_colorKeywords.put("dodgerblue", 0x1E90FF);
		_colorKeywords.put("firebrick", 0xB22222);
		_colorKeywords.put("floralwhite", 0xFFFAF0);
		_colorKeywords.put("forestgreen", 0x228B22);
		_colorKeywords.put("fuchsia", 0xFF00FF);
		_colorKeywords.put("gainsboro", 0xDCDCDC);
		_colorKeywords.put("ghostwhite", 0xF8F8FF);
		_colorKeywords.put("gold", 0xFFD700);
		_colorKeywords.put("goldenrod", 0xDAA520);
		_colorKeywords.put("gray", 0x808080);
		_colorKeywords.put("green", 0x008000);
		_colorKeywords.put("greenyellow", 0xADFF2F);
		_colorKeywords.put("grey", 0x808080);
		_colorKeywords.put("honeydew", 0xF0FFF0);
		_colorKeywords.put("hotpink", 0xFF69B4);
		_colorKeywords.put("indianred", 0xCD5C5C);
		_colorKeywords.put("indigo", 0x4B0082);
		_colorKeywords.put("ivory", 0xFFFFF0);
		_colorKeywords.put("khaki", 0xF0E68C);
		_colorKeywords.put("lavender", 0xE6E6FA);
		_colorKeywords.put("lavenderblush", 0xFFF0F5);
		_colorKeywords.put("lawngreen", 0x7CFC00);
		_colorKeywords.put("lemonchiffon", 0xFFFACD);
		_colorKeywords.put("lightblue", 0xADD8E6);
		_colorKeywords.put("lightcoral", 0xF08080);
		_colorKeywords.put("lightcyan", 0xE0FFFF);
		_colorKeywords.put("lightgoldenrodyellow", 0xFAFAD2);
		_colorKeywords.put("lightgray", 0xD3D3D3);
		_colorKeywords.put("lightgreen", 0x90EE90);
		_colorKeywords.put("lightgrey", 0xD3D3D3);
		_colorKeywords.put("lightpink", 0xFFB6C1);
		_colorKeywords.put("lightsalmon", 0xFFA07A);
		_colorKeywords.put("lightseagreen", 0x20B2AA);
		_colorKeywords.put("lightskyblue", 0x87CEFA);
		_colorKeywords.put("lightslategray", 0x778899);
		_colorKeywords.put("lightslategrey", 0x778899);
		_colorKeywords.put("lightsteelblue", 0xB0C4DE);
		_colorKeywords.put("lightyellow", 0xFFFFE0);
		_colorKeywords.put("lime", 0x00FF00);
		_colorKeywords.put("limegreen", 0x32CD32);
		_colorKeywords.put("linen", 0xFAF0E6);
		_colorKeywords.put("magenta", 0xFF00FF);
		_colorKeywords.put("maroon", 0x800000);
		_colorKeywords.put("mediumaquamarine", 0x66CDAA);
		_colorKeywords.put("mediumblue", 0x0000CD);
		_colorKeywords.put("mediumorchid", 0xBA55D3);
		_colorKeywords.put("mediumpurple", 0x9370DB);
		_colorKeywords.put("mediumseagreen", 0x3CB371);
		_colorKeywords.put("mediumslateblue", 0x7B68EE);
		_colorKeywords.put("mediumspringgreen", 0x00FA9A);
		_colorKeywords.put("mediumturquoise", 0x48D1CC);
		_colorKeywords.put("mediumvioletred", 0xC71585);
		_colorKeywords.put("midnightblue", 0x191970);
		_colorKeywords.put("mintcream", 0xF5FFFA);
		_colorKeywords.put("mistyrose", 0xFFE4E1);
		_colorKeywords.put("moccasin", 0xFFE4B5);
		_colorKeywords.put("navajowhite", 0xFFDEAD);
		_colorKeywords.put("navy", 0x000080);
		_colorKeywords.put("oldlace", 0xFDF5E6);
		_colorKeywords.put("olive", 0x808000);
		_colorKeywords.put("olivedrab", 0x6B8E23);
		_colorKeywords.put("orange", 0xFFA500);
		_colorKeywords.put("orangered", 0xFF4500);
		_colorKeywords.put("orchid", 0xDA70D6);
		_colorKeywords.put("palegoldenrod", 0xEEE8AA);
		_colorKeywords.put("palegreen", 0x98FB98);
		_colorKeywords.put("paleturquoise", 0xAFEEEE);
		_colorKeywords.put("palevioletred", 0xDB7093);
		_colorKeywords.put("papayawhip", 0xFFEFD5);
		_colorKeywords.put("peachpuff", 0xFFDAB9);
		_colorKeywords.put("peru", 0xCD853F);
		_colorKeywords.put("pink", 0xFFC0CB);
		_colorKeywords.put("plum", 0xDDA0DD);
		_colorKeywords.put("powderblue", 0xB0E0E6);
		_colorKeywords.put("purple", 0x800080);
		_colorKeywords.put("rebeccapurple", 0x663399);
		_colorKeywords.put("red", 0xFF0000);
		_colorKeywords.put("rosybrown", 0xBC8F8F);
		_colorKeywords.put("royalblue", 0x4169E1);
		_colorKeywords.put("saddlebrown", 0x8B4513);
		_colorKeywords.put("salmon", 0xFA8072);
		_colorKeywords.put("sandybrown", 0xF4A460);
		_colorKeywords.put("seagreen", 0x2E8B57);
		_colorKeywords.put("seashell", 0xFFF5EE);
		_colorKeywords.put("sienna", 0xA0522D);
		_colorKeywords.put("silver", 0xC0C0C0);
		_colorKeywords.put("skyblue", 0x87CEEB);
		_colorKeywords.put("slateblue", 0x6A5ACD);
		_colorKeywords.put("slategray", 0x708090);
		_colorKeywords.put("slategrey", 0x708090);
		_colorKeywords.put("snow", 0xFFFAFA);
		_colorKeywords.put("springgreen", 0x00FF7F);
		_colorKeywords.put("steelblue", 0x4682B4);
		_colorKeywords.put("tan", 0xD2B48C);
		_colorKeywords.put("teal", 0x008080);
		_colorKeywords.put("thistle", 0xD8BFD8);
		_colorKeywords.put("tomato", 0xFF6347);
		_colorKeywords.put("turquoise", 0x40E0D0);
		_colorKeywords.put("violet", 0xEE82EE);
		_colorKeywords.put("wheat", 0xF5DEB3);
		_colorKeywords.put("white", 0xFFFFFF);
		_colorKeywords.put("whitesmoke", 0xF5F5F5);
		_colorKeywords.put("yellow", 0xFFFF00);
		_colorKeywords.put("yellowgreen", 0x9ACD32);
	};

	public static class HSL {
		double _h;
		double _s;
		double _l;

		public HSL(double h, double s, double l) {
			_h = h;
			_s = s;
			_l = l;
		}

		public double h() {
			return _h;
		}

		public double s() {
			return _s;
		}

		public double l() {
			return _l;
		}

		public double h(double h) {
			return _h = h;
		}

		public double s(double s) {
			return _s = s;
		}

		public double l(double l) {
			return _l = l;
		}

	}

	public final static HSL _hslA = new HSL(0, 0, 0);
	public final static HSL _hslB = new HSL(0, 0, 0);

	public static double hue2rgb(double p, double q, double t) {

		if (t < 0.)
			t += 1.;
		if (t > 1.)
			t -= 1.;
		if (t < 1 / 6.)
			return p + (q - p) * 6. * t;
		if (t < 1. / 2.)
			return q;
		if (t < 2. / 3.)
			return p + (q - p) * 6. * (2. / 3. - t);
		return p;
	}

	public static double SRGBToLinear(double c) {

		return (c < 0.04045) ? c * 0.0773993808 : Math.pow(c * 0.9478672986 + 0.0521327014, 2.4);

	}

	public static double LinearToSRGB(double c) {

		return (c < 0.0031308) ? c * 12.92 : 1.055 * (Math.pow(c, 0.41666)) - 0.055;

	}

	public Color() {
		this._r = this._g = this._b = Double.NEGATIVE_INFINITY;
	}

	public Color(Color color) {
		this.copy(color);
	}

	public Color(String s) {
		this.setStyle(s);
	}

	public Color(int x) {
		this.setHex(x);
	}

	public Color(int r, int g, int b) {
		this.setRGB(r, g, b);
	}

	public Color(double r, double g, double b) {
		this._r = r;
		this._g = g;
		this._b = b;
	}

	public double r() {
		return _r;
	}

	public double b() {
		return _b;
	}

	public double g() {
		return _g;
	}

	public void r(double r) {
		this._r = r;
	}

	public void g(double g) {
		this._g = g;
	}

	public void b(double b) {
		this._b = b;
	}

	public Color set(Color value) {

		return this.copy(value);
	}

	public Color set(int value) {
		return this.setHex(value);
	}

	public Color set(String value) {

		return this.setStyle(value);

	}

	public Color setScalar(double scalar) {

		this._r = scalar;
		this._g = scalar;
		this._b = scalar;

		return this;

	}

	public Color setHex(int hex) {

		this._r = ((hex >> 16) & 255) / 255.;
		this._g = ((hex >> 8) & 255) / 255.;
		this._b = ((hex & 255)) / 255.;

		return this;

	}

	public Color setRGB(int r, int g, int b) {

		this._r = r / 255.;
		this._g = g / 255.;
		this._b = b / 255.;

		return this;

	}

	public Color setRGB(double r, double g, double b) {

		this._r = r;
		this._g = g;
		this._b = b;

		return this;

	}

	public Color setHSL(double h, double s, double l) {

		// h,s,l ranges are in 0.0 - 1.0
		h = MathUtils.euclideanModulo(h, 1.);
		s = MathUtils.clamp(s, 0., 1.);
		l = MathUtils.clamp(l, 0., 1.);

		if (s == 0) {

			this._r = this._g = this._b = l;

		} else {

			final double p = l <= 0.5 ? l * (1. + s) : l + s - (l * s);
			final double q = (2. * l) - p;

			this._r = hue2rgb(q, p, h + 1. / 3.);
			this._g = hue2rgb(q, p, h);
			this._b = hue2rgb(q, p, h - 1. / 3.);

		}

		return this;

	}

	void handleAlpha(String style, String string) {

		if (string == null)
			return;

		if (Double.parseDouble(string) < 1.) {

			console.warn("THREE.Color: Alpha component of " + style + " will be ignored.");

		}

	}

	public Color setStyle(String style) {

		Matcher m = Pattern.compile("^((?:rgb|hsl)a?)\\(\\s*([^)]*)\\)").matcher(style);

		if (m.matches()) {

			// rgb / hsl

			Matcher color;
			String name = m.group(1);
			String components = m.group(2);

			switch (name) {

			case "rgb":
			case "rgba":

				color = Pattern.compile("^(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*(?:,\\s*(\\d*\\.?\\d+)\\s*)?$")
						.matcher(components);
				if (color.matches()) {

					// rgb(255,0,0) rgba(255,0,0,0.5)
					this._r = Math.min(255, Integer.parseInt(color.group(1), 10)) / 255.;
					this._g = Math.min(255, Integer.parseInt(color.group(2), 10)) / 255.;
					this._b = Math.min(255, Integer.parseInt(color.group(3), 10)) / 255.;

					handleAlpha(style, color.group(4));

					return this;

				}

				color = Pattern
						.compile("^(\\d+)\\%\\s*,\\s*(\\d+)\\%\\s*,\\s*(\\d+)\\%\\s*(?:,\\s*(\\d*\\.?\\d+)\\s*)?$")
						.matcher(components);
				if (color.matches()) {

					// rgb(100%,0%,0%) rgba(100%,0%,0%,0.5)
					this._r = Math.min(100, Integer.parseInt(color.group(1), 10)) / 100.;
					this._g = Math.min(100, Integer.parseInt(color.group(2), 10)) / 100.;
					this._b = Math.min(100, Integer.parseInt(color.group(3), 10)) / 100.;

					handleAlpha(style, color.group(4));

					return this;

				}

				break;

			case "hsl":
			case "hsla":

				color = Pattern
						.compile("^(\\d*\\.?\\d+)\\s*,\\s*(\\d+)\\%\\s*,\\s*(\\d+)\\%\\s*(?:,\\s*(\\d*\\.?\\d+)\\s*)?$")
						.matcher(components);
				if (color.matches()) {

					// hsl(120,50%,50%) hsla(120,50%,50%,0.5)
					final double h = Double.parseDouble(color.group(1)) / 360.;
					final double s = Integer.parseInt(color.group(2), 10) / 100.;
					final double l = Integer.parseInt(color.group(3), 10) / 100.;

					handleAlpha(style, color.group(4));

					return this.setHSL(h, s, l);

				}

				break;

			}

		} else {

			m = Pattern.compile("^#([A-Fa-f\\d]+)$").matcher(style);
			if (m.matches()) {

				// hex color
				String hex = m.group(1);
				int size = hex.length();

				if (size == 3) {

					// #ff0
					this._r = Integer.parseInt(hex.substring(0, 1) + hex.substring(0, 1), 16) / 255.;
					this._g = Integer.parseInt(hex.substring(1, 2) + hex.substring(1, 2), 16) / 255.;
					this._b = Integer.parseInt(hex.substring(2, 3) + hex.substring(2, 3), 16) / 255.;

					return this;

				}

				if (size == 6) {

					// #ff0000
					this._r = Integer.parseInt(hex.substring(0, 2), 16) / 255.;
					this._g = Integer.parseInt(hex.substring(2, 4), 16) / 255.;
					this._b = Integer.parseInt(hex.substring(4, 6), 16) / 255.;

					return this;

				}
			}
		}

		if (style != null && style.length() > 0) {

			return this.setColorName(style);

		}

		return this;

	}

	public Color setColorName(String style) {

		// color keywords
		Integer hex = _colorKeywords.get(style);

		if (hex != null) {

			// red
			this.setHex(hex);

		} else {

			// unknown color
			console.warn("THREE.Color: Unknown color " + style);

		}

		return this;

	}

	public Color clone() {

		return new Color(this._r, this._g, this._b);

	}

	public Color copy(Color color) {

		this._r = color._r;
		this._g = color._g;
		this._b = color._b;

		return this;

	}

	public Color copyGammaToLinear(Color color) {
		return copyGammaToLinear(color, 2);
	}

	public Color copyGammaToLinear(Color color, double gammaFactor) {

		this._r = Math.pow((double) color._r, gammaFactor);
		this._g = Math.pow((double) color._g, gammaFactor);
		this._b = Math.pow((double) color._b, gammaFactor);

		return this;

	}

	public Color copyLinearToGamma(Color color) {
		return copyLinearToGamma(color, 2.);
	}

	public Color copyLinearToGamma(Color color, double gammaFactor) {

		final double safeInverse = (gammaFactor > 0) ? (1.0 / gammaFactor) : 1.0;

		this._r = Math.pow(color._r, safeInverse);
		this._g = Math.pow(color._g, safeInverse);
		this._b = Math.pow(color._b, safeInverse);

		return this;

	}

	public Color convertGammaToLinear() {

		this.copyGammaToLinear(this);

		return this;

	}


	public Color convertGammaToLinear(double gammaFactor) {

		this.copyGammaToLinear(this, gammaFactor);

		return this;

	}

	public Color convertLinearToGamma() {

		this.copyLinearToGamma(this);

		return this;

	}

	public Color convertLinearToGamma(double gammaFactor) {

		this.copyLinearToGamma(this, gammaFactor);

		return this;

	}

	public Color copySRGBToLinear(Color color) {

		this._r = SRGBToLinear(color._r);
		this._g = SRGBToLinear(color._g);
		this._b = SRGBToLinear(color._b);

		return this;

	}

	public Color copyLinearToSRGB(Color color) {

		this._r = LinearToSRGB(color._r);
		this._g = LinearToSRGB(color._g);
		this._b = LinearToSRGB(color._b);

		return this;

	}

	public Color convertSRGBToLinear() {

		this.copySRGBToLinear(this);

		return this;

	}

	public Color convertLinearToSRGB() {

		this.copyLinearToSRGB(this);

		return this;

	}

	public int getHex() {

		return (int) (this._r * 255) << 16 ^ (int) (this._g * 255) << 8 ^ (int) (this._b * 255) << 0;

	}

	public String getHexString() {

		final String s = Integer.toString(this.getHex(), 16);

		return ("000000" + s).substring(s.length());

	}

	public HSL getHSL(HSL target) {

		// h,s,l ranges are in 0.0 - 1.0

		final double r = this._r, g = this._g, b = this._b;

		final double max = Math.max(r, Math.max(g, b));
		final double min = Math.min(r, Math.min(g, b));

		double hue, saturation;
		final double lightness = (min + max) / 2.0;

		if (min == max) {

			hue = 0;
			saturation = 0;

		} else {

			final double delta = max - min;

			saturation = lightness <= 0.5 ? delta / (max + min) : delta / (2. - max - min);

			if (max == r) {
				hue = (g - b) / delta + (g < b ? 6. : 0.);
			} else if (max == g) {
				hue = (b - r) / delta + 2.;
			} else {
				hue = (r - g) / delta + 4.;
			}

			hue /= 6.;

		}

		target._h = hue;
		target._s = saturation;
		target._l = lightness;

		return target;
	}

	public String getStyle() {

		return "rgb(" + (int) (this._r * 255) + "," + (int) (this._g * 255) + "," + (int) (this._b * 255) + ")";

	}

	public Color offsetHSL(double h, double s, double l) {

		this.getHSL(_hslA);

		_hslA._h += h;
		_hslA._s += s;
		_hslA._l += l;

		this.setHSL(_hslA._h, _hslA._s, _hslA._l);

		return this;

	}

	public Color add(Color color) {

		this._r += color._r;
		this._g += color._g;
		this._b += color._b;

		return this;

	}

	public Color addColors(Color color1, Color color2) {

		this._r = color1._r + color2._r;
		this._g = color1._g + color2._g;
		this._b = color1._b + color2._b;

		return this;

	}

	public Color addScalar(double s) {

		this._r += s;
		this._g += s;
		this._b += s;

		return this;

	}

	public Color sub(Color color) {

		this._r = Math.max(0, this._r - color._r);
		this._g = Math.max(0, this._g - color._g);
		this._b = Math.max(0, this._b - color._b);

		return this;

	}

	public Color multiply(Color color) {

		this._r *= color._r;
		this._g *= color._g;
		this._b *= color._b;

		return this;

	}

	public Color multiplyScalar(double s) {

		this._r *= s;
		this._g *= s;
		this._b *= s;

		return this;

	}

	public Color lerp(Color color, double alpha) {

		this._r += (color._r - this._r) * alpha;
		this._g += (color._g - this._g) * alpha;
		this._b += (color._b - this._b) * alpha;

		return this;

	}

	public Color lerpHSL(Color color, double alpha) {

		this.getHSL(_hslA);
		color.getHSL(_hslB);

		final double h = MathUtils.lerp(_hslA._h, _hslB._h, alpha);
		final double s = MathUtils.lerp(_hslA._s, _hslB._s, alpha);
		final double l = MathUtils.lerp(_hslA._l, _hslB._l, alpha);

		this.setHSL(h, s, l);

		return this;

	}

	public boolean equals(Color c) {

		return (c._r == this._r) && (c._g == this._g) && (c._b == this._b);

	}

	public Color fromArray(double[] array) {
		return fromArray(array, 0);
	}

	public Color fromArray(double[] array, int offset) {

		this._r = array[offset];
		this._g = array[offset + 1];
		this._b = array[offset + 2];

		return this;

	}

	public double[] toArray() {
		return toArray(new double[3], 0);
	}

	public double[] toArray(double[] array) {
		return toArray(array, 0);
	}

	public double[] toArray(double[] array, int offset) {

		array[offset] = this._r;
		array[offset + 1] = this._g;
		array[offset + 2] = this._b;

		return array;

	}

	public Color fromBufferAttribute(BufferAttribute attribute, int index) {

		this._r = attribute.getX(index);
		this._g = attribute.getY(index);
		this._b = attribute.getZ(index);

		if (attribute.normalized() == true) {

			// assuming Uint8Array

			this._r /= 255;
			this._g /= 255;
			this._b /= 255;

		}

		return this;

	}

	public double toJSON() {

		return this.getHex();

	}

	@Override
	public String toString() {
		if (_r == Double.NEGATIVE_INFINITY && _g == Double.NEGATIVE_INFINITY && _b == Double.NEGATIVE_INFINITY)
			return "Color {}";

		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		//sortedReflectionToStringBuilder.setExcludeFieldNames("isVector3");
		return sortedReflectionToStringBuilder.toString();
	}

	public static class NAMES {
		public final static int
			aliceblue = 0xF0F8FF,
			antiquewhite = 0xFAEBD7,
			aqua = 0x00FFFF,
			aquamarine = 0x7FFFD4,
			azure = 0xF0FFFF,
			beige = 0xF5F5DC,
			bisque = 0xFFE4C4,
			black = 0x000000,
			blanchedalmond = 0xFFEBCD,
			blue = 0x0000FF,
			blueviolet = 0x8A2BE2,
			brown = 0xA52A2A,
			burlywood = 0xDEB887,
			cadetblue = 0x5F9EA0,
			chartreuse = 0x7FFF00,
			chocolate = 0xD2691E,
			coral = 0xFF7F50,
			cornflowerblue = 0x6495ED,
			cornsilk = 0xFFF8DC,
			crimson = 0xDC143C,
			cyan = 0x00FFFF,
			darkblue = 0x00008B,
			darkcyan = 0x008B8B,
			darkgoldenrod = 0xB8860B,
			darkgray = 0xA9A9A9,
			darkgreen = 0x006400,
			darkgrey = 0xA9A9A9,
			darkkhaki = 0xBDB76B,
			darkmagenta = 0x8B008B,
			darkolivegreen = 0x556B2F,
			darkorange = 0xFF8C00,
			darkorchid = 0x9932CC,
			darkred = 0x8B0000,
			darksalmon = 0xE9967A,
			darkseagreen = 0x8FBC8F,
			darkslateblue = 0x483D8B,
			darkslategray = 0x2F4F4F,
			darkslategrey = 0x2F4F4F,
			darkturquoise = 0x00CED1,
			darkviolet = 0x9400D3,
			deeppink = 0xFF1493,
			deepskyblue = 0x00BFFF,
			dimgray = 0x696969,
			dimgrey = 0x696969,
			dodgerblue = 0x1E90FF,
			firebrick = 0xB22222,
			floralwhite = 0xFFFAF0,
			forestgreen = 0x228B22,
			fuchsia = 0xFF00FF,
			gainsboro = 0xDCDCDC,
			ghostwhite = 0xF8F8FF,
			gold = 0xFFD700,
			goldenrod = 0xDAA520,
			gray = 0x808080,
			green = 0x008000,
			greenyellow = 0xADFF2F,
			grey = 0x808080,
			honeydew = 0xF0FFF0,
			hotpink = 0xFF69B4,
			indianred = 0xCD5C5C,
			indigo = 0x4B0082,
			ivory = 0xFFFFF0,
			khaki = 0xF0E68C,
			lavender = 0xE6E6FA,
			lavenderblush = 0xFFF0F5,
			lawngreen = 0x7CFC00,
			lemonchiffon = 0xFFFACD,
			lightblue = 0xADD8E6,
			lightcoral = 0xF08080,
			lightcyan = 0xE0FFFF,
			lightgoldenrodyellow = 0xFAFAD2,
			lightgray = 0xD3D3D3,
			lightgreen = 0x90EE90,
			lightgrey = 0xD3D3D3,
			lightpink = 0xFFB6C1,
			lightsalmon = 0xFFA07A,
			lightseagreen = 0x20B2AA,
			lightskyblue = 0x87CEFA,
			lightslategray = 0x778899,
			lightslategrey = 0x778899,
			lightsteelblue = 0xB0C4DE,
			lightyellow = 0xFFFFE0,
			lime = 0x00FF00,
			limegreen = 0x32CD32,
			linen = 0xFAF0E6,
			magenta = 0xFF00FF,
			maroon = 0x800000,
			mediumaquamarine = 0x66CDAA,
			mediumblue = 0x0000CD,
			mediumorchid = 0xBA55D3,
			mediumpurple = 0x9370DB,
			mediumseagreen = 0x3CB371,
			mediumslateblue = 0x7B68EE,
			mediumspringgreen = 0x00FA9A,
			mediumturquoise = 0x48D1CC,
			mediumvioletred = 0xC71585,
			midnightblue = 0x191970,
			mintcream = 0xF5FFFA,
			mistyrose = 0xFFE4E1,
			moccasin = 0xFFE4B5,
			navajowhite = 0xFFDEAD,
			navy = 0x000080,
			oldlace = 0xFDF5E6,
			olive = 0x808000,
			olivedrab = 0x6B8E23,
			orange = 0xFFA500,
			orangered = 0xFF4500,
			orchid = 0xDA70D6,
			palegoldenrod = 0xEEE8AA,
			palegreen = 0x98FB98,
			paleturquoise = 0xAFEEEE,
			palevioletred = 0xDB7093,
			papayawhip = 0xFFEFD5,
			peachpuff = 0xFFDAB9,
			peru = 0xCD853F,
			pink = 0xFFC0CB,
			plum = 0xDDA0DD,
			powderblue = 0xB0E0E6,
			purple = 0x800080,
			rebeccapurple = 0x663399,
			red = 0xFF0000,
			rosybrown = 0xBC8F8F,
			royalblue = 0x4169E1,
			saddlebrown = 0x8B4513,
			salmon = 0xFA8072,
			sandybrown = 0xF4A460,
			seagreen = 0x2E8B57,
			seashell = 0xFFF5EE,
			sienna = 0xA0522D,
			silver = 0xC0C0C0,
			skyblue = 0x87CEEB,
			slateblue = 0x6A5ACD,
			slategray = 0x708090,
			slategrey = 0x708090,
			snow = 0xFFFAFA,
			springgreen = 0x00FF7F,
			steelblue = 0x4682B4,
			tan = 0xD2B48C,
			teal = 0x008080,
			thistle = 0xD8BFD8,
			tomato = 0xFF6347,
			turquoise = 0x40E0D0,
			violet = 0xEE82EE,
			wheat = 0xF5DEB3,
			white = 0xFFFFFF,
			whitesmoke = 0xF5F5F5,
			yellow = 0xFFFF00,
			yellowgreen = 0x9ACD32;
	};

}
