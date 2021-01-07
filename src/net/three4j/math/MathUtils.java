package net.three4j.math;

public class MathUtils {
	private static final String[] _lut = new String[256];

	static {
		for (int i = 0; i < 256; i++) {
			_lut[i] = (i < 16 ? "0" : "") + Integer.toString(i, 16);
		}
	}

	private static double _seed = 1234567;

	public static final double DEG2RAD = Math.PI / 180;
	public static final double RAD2DEG = Math.PI;

	public static String generateUUID() {
		// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136

		final int d0 = (int) (Math.random() * 0xffffffff);
		final int d1 = (int) (Math.random() * 0xffffffff);
		final int d2 = (int) (Math.random() * 0xffffffff);
		final int d3 = (int) (Math.random() * 0xffffffff);
		final String uuid = _lut[d0 & 0xff] + _lut[d0 >> 8 & 0xff] + _lut[d0 >> 16 & 0xff] + _lut[d0 >> 24 & 0xff] + '-'
				+ _lut[d1 & 0xff] + _lut[d1 >> 8 & 0xff] + '-' + _lut[d1 >> 16 & 0x0f | 0x40] + _lut[d1 >> 24 & 0xff]
				+ '-' + _lut[d2 & 0x3f | 0x80] + _lut[d2 >> 8 & 0xff] + '-' + _lut[d2 >> 16 & 0xff]
				+ _lut[d2 >> 24 & 0xff] + _lut[d3 & 0xff] + _lut[d3 >> 8 & 0xff] + _lut[d3 >> 16 & 0xff]
				+ _lut[d3 >> 24 & 0xff];

		// .toUpperCase() here flattens concatenated strings to save heap memory space.
		return uuid.toUpperCase();
	}

	public static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}

	// compute euclidian modulo of m % n
	// https://en.wikipedia.org/wiki/Modulo_operation
	public static double euclideanModulo(double n, double m) {
		return ((n % m) + m) % m;
	}

	// Linear mapping from range <a1, a2> to range <b1, b2>
	public static double mapLinear(double x, double a1, double a2, double b1, double b2) {
		return b1 + (x - a1) * (b2 - b1) / (a2 - a1);
	};

	// https://en.wikipedia.org/wiki/Linear_interpolation
	public static double lerp(double x, double y, double t) {
		return (1 - t) * x + t * y;
	};

	// http://en.wikipedia.org/wiki/Smoothstep
	public static double smoothstep(double x, double min, double max) {
		if (x <= min)
			return 0;

		if (x >= max)
			return 1;

		x = (x - min) / (max - min);

		return x * x * (3 - 2 * x);
	};

	public static double smootherstep(double x, double min, double max) {
		if (x <= min)
			return 0;

		if (x >= max)
			return 1;

		x = (x - min) / (max - min);

		return x * x * x * (x * (x * 6 - 15) + 10);
	};

	// Random integer from <low, high> interval
	public static double randInt(double low, double high) {
		return low + Math.floor(Math.random() * (high - low + 1));
	};

	// Random float from <low, high> interval
	public static double randFloat(double low, double high) {
		return low + Math.random() * (high - low);
	};

	// Random float from <-range/2, range/2> interval
	public static double randFloatSpread(double range) {
		return range * (0.5 - Math.random());
	};

	// Deterministic pseudo-random float in the interval [ 0, 1 ]
	public static double seededRandom(double  s) {
		_seed = s % 2147483647;

		// Park-Miller algorithm
		_seed = _seed * 16807 % 2147483647;

		return ( _seed - 1 ) / 2147483646;
	};

	public static double degToRad( double degrees ) {
		return degrees * DEG2RAD;
	};

	public static double radToDeg(double radians ) {
		return radians * RAD2DEG;
	};

	public static boolean isPowerOfTwo(double value) {
		return ( (long)value & (long)( value - 1 ) ) == 0 && value != 0;
	};

	private static final double LN2 = Math.log(2);
	
	public static double ceilPowerOfTwo(double value) {
		return Math.pow( 2, Math.ceil( Math.log( value ) / LN2 ) );
	};

	public static double floorPowerOfTwo(double value) {
		return Math.pow( 2, Math.floor( Math.log( value ) / LN2 ) );
	};

	public static void setQuaternionFromProperEuler(Quaternion q, double a, double b, double c, String order) {
		// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles

		// rotations are applied to the axes in the order specified by 'order'
		// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
		// angles are in radians

		final double c2 = Math.cos( b / 2 );
		final double s2 = Math.sin( b / 2 );

		final double c13 = Math.cos( ( a + c ) / 2 );
		final double s13 = Math.sin( ( a + c ) / 2 );

		final double c1_3 = Math.cos( ( a - c ) / 2 );
		final double s1_3 = Math.sin( ( a - c ) / 2 );

		final double c3_1 = Math.cos( ( c - a ) / 2 );
		final double s3_1 = Math.sin( ( c - a ) / 2 );

		switch ( order ) {

			case "XYX":
				q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
				break;

			case "YZY":
				q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
				break;

			case "ZXZ":
				q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
				break;

			case "XZX":
				q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
				break;

			case "YXY":
				q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
				break;

			case "ZYZ":
				q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
				break;

			default:
				throw new RuntimeException( "THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: " + order );
		}
	}
}
