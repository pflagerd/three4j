package net.three4j.math;

public class MathUtils {
	private static final String[] _lut = new String[256];

	static {
		for (int i = 0; i < 256; i++) {
			_lut[i] = (i < 16 ? "0" : "" ) + Integer.toString(i, 16);
		}
	}

	private static final int _seed = 1234567;

	public final double DEG2RAD = Math.PI / 180;
	public final double RAD2DEG = Math.PI;

	public String generateUUID() {
		// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136

		final int d0 = (int)(Math.random() * 0xffffffff);
		final int d1 = (int)(Math.random() * 0xffffffff);
		final int d2 = (int)(Math.random() * 0xffffffff);
		final int d3 = (int)(Math.random() * 0xffffffff);
		final String uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];

		// .toUpperCase() here flattens concatenated strings to save heap memory space.
		return uuid.toUpperCase();
	}

	public double clamp(double value, double min, double max) {
		return Math.max( min, Math.min( max, value ) );
	}

	// compute euclidian modulo of m % n
	// https://en.wikipedia.org/wiki/Modulo_operation
	double euclideanModulo(double n, double m) {
		return ( ( n % m ) + m ) % m;
	}

	// Linear mapping from range <a1, a2> to range <b1, b2>
	double mapLinear(double x, double a1, double a2, double b1, double b2) {
		return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
	};
	
	// https://en.wikipedia.org/wiki/Linear_interpolation
	double lerp(double x,double y,double t) {
		return ( 1 - t ) * x + t * y;
	};

//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	//	const _lut = [];
//	
//	for ( let i = 0; i < 256; i ++ ) {
//	
//		_lut[ i ] = ( i < 16 ? '0' : '' ) + ( i ).toString( 16 );
//	
//	}
//	
//	let _seed = 1234567;
//	
//	const MathUtils = {
//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	//	const _lut = [];
//	
//	for ( let i = 0; i < 256; i ++ ) {
//	
//		_lut[ i ] = ( i < 16 ? '0' : '' ) + ( i ).toString( 16 );
//	
//	}
//	
//	let _seed = 1234567;
//	
//	const MathUtils = {
//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radi//	const _lut = [];
//	
//	for ( let i = 0; i < 256; i ++ ) {
//	
//		_lut[ i ] = ( i < 16 ? '0' : '' ) + ( i ).toString( 16 );
//	
//	}
//	
//	let _seed = 1234567;
//	
//	const MathUtils = {
//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller al//	const _lut = [];
//	
//	for ( let i = 0; i < 256; i ++ ) {
//	
//		_lut[ i ] = ( i < 16 ? '0' : '' ) + ( i ).toString( 16 );
//	
//	}
//	
//	let _seed = 1234567;
//	
//	const MathUtils = {
//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	//	const _lut = [];
//	
//	for ( let i = 0; i < 256; i ++ ) {
//	
//		_lut[ i ] = ( i < 16 ? '0' : '' ) + ( i ).toString( 16 );
//	
//	}
//	
//	let _seed = 1234567;
//	
//	const MathUtils = {
//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };

//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };

//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };

//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
//	
//	
//	export { MathUtils };

//	
//		DEG2RAD: Math.PI / 180,
//		RAD2DEG: 180 / Math.PI,
//	
//		generateUUID: function () {
//	
//			// http://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid-in-javascript/21963136#21963136
//	
//			const d0 = Math.random() * 0xffffffff | 0;
//			const d1 = Math.random() * 0xffffffff | 0;
//			const d2 = Math.random() * 0xffffffff | 0;
//			const d3 = Math.random() * 0xffffffff | 0;
//			const uuid = _lut[ d0 & 0xff ] + _lut[ d0 >> 8 & 0xff ] + _lut[ d0 >> 16 & 0xff ] + _lut[ d0 >> 24 & 0xff ] + '-' +
//				_lut[ d1 & 0xff ] + _lut[ d1 >> 8 & 0xff ] + '-' + _lut[ d1 >> 16 & 0x0f | 0x40 ] + _lut[ d1 >> 24 & 0xff ] + '-' +
//				_lut[ d2 & 0x3f | 0x80 ] + _lut[ d2 >> 8 & 0xff ] + '-' + _lut[ d2 >> 16 & 0xff ] + _lut[ d2 >> 24 & 0xff ] +
//				_lut[ d3 & 0xff ] + _lut[ d3 >> 8 & 0xff ] + _lut[ d3 >> 16 & 0xff ] + _lut[ d3 >> 24 & 0xff ];
//	
//			// .toUpperCase() here flattens concatenated strings to save heap memory space.
//			return uuid.toUpperCase();
//	
//		},
//	
//		clamp: function ( value, min, max ) {
//	
//			return Math.max( min, Math.min( max, value ) );
//	
//		},
//	
//		// compute euclidian modulo of m % n
//		// https://en.wikipedia.org/wiki/Modulo_operation
//	
//		euclideanModulo: function ( n, m ) {
//	
//			return ( ( n % m ) + m ) % m;
//	
//		},
//	
//		// Linear mapping from range <a1, a2> to range <b1, b2>
//	
//		mapLinear: function ( x, a1, a2, b1, b2 ) {
//	
//			return b1 + ( x - a1 ) * ( b2 - b1 ) / ( a2 - a1 );
//	
//		},
//	
//		// https://en.wikipedia.org/wiki/Linear_interpolation
//	
//		lerp: function ( x, y, t ) {
//	
//			return ( 1 - t ) * x + t * y;
//	
//		},
//	
//		// http://en.wikipedia.org/wiki/Smoothstep
//	
//		smoothstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * ( 3 - 2 * x );
//	
//		},
//	
//		smootherstep: function ( x, min, max ) {
//	
//			if ( x <= min ) return 0;
//			if ( x >= max ) return 1;
//	
//			x = ( x - min ) / ( max - min );
//	
//			return x * x * x * ( x * ( x * 6 - 15 ) + 10 );
//	
//		},
//	
//		// Random integer from <low, high> interval
//	
//		randInt: function ( low, high ) {
//	
//			return low + Math.floor( Math.random() * ( high - low + 1 ) );
//	
//		},
//	
//		// Random float from <low, high> interval
//	
//		randFloat: function ( low, high ) {
//	
//			return low + Math.random() * ( high - low );
//	
//		},
//	
//		// Random float from <-range/2, range/2> interval
//	
//		randFloatSpread: function ( range ) {
//	
//			return range * ( 0.5 - Math.random() );
//	
//		},
//	
//		// Deterministic pseudo-random float in the interval [ 0, 1 ]
//	
//		seededRandom: function ( s ) {
//	
//			if ( s !== undefined ) _seed = s % 2147483647;
//	
//			// Park-Miller algorithm
//	
//			_seed = _seed * 16807 % 2147483647;
//	
//			return ( _seed - 1 ) / 2147483646;
//	
//		},
//	
//		degToRad: function ( degrees ) {
//	
//			return degrees * MathUtils.DEG2RAD;
//	
//		},
//	
//		radToDeg: function ( radians ) {
//	
//			return radians * MathUtils.RAD2DEG;
//	
//		},
//	
//		isPowerOfTwo: function ( value ) {
//	
//			return ( value & ( value - 1 ) ) === 0 && value !== 0;
//	
//		},
//	
//		ceilPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.ceil( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		floorPowerOfTwo: function ( value ) {
//	
//			return Math.pow( 2, Math.floor( Math.log( value ) / Math.LN2 ) );
//	
//		},
//	
//		setQuaternionFromProperEuler: function ( q, a, b, c, order ) {
//	
//			// Intrinsic Proper Euler Angles - see https://en.wikipedia.org/wiki/Euler_angles
//	
//			// rotations are applied to the axes in the order specified by 'order'
//			// rotation by angle 'a' is applied first, then by angle 'b', then by angle 'c'
//			// angles are in radians
//	
//			const cos = Math.cos;
//			const sin = Math.sin;
//	
//			const c2 = cos( b / 2 );
//			const s2 = sin( b / 2 );
//	
//			const c13 = cos( ( a + c ) / 2 );
//			const s13 = sin( ( a + c ) / 2 );
//	
//			const c1_3 = cos( ( a - c ) / 2 );
//			const s1_3 = sin( ( a - c ) / 2 );
//	
//			const c3_1 = cos( ( c - a ) / 2 );
//			const s3_1 = sin( ( c - a ) / 2 );
//	
//			switch ( order ) {
//	
//				case 'XYX':
//					q.set( c2 * s13, s2 * c1_3, s2 * s1_3, c2 * c13 );
//					break;
//	
//				case 'YZY':
//					q.set( s2 * s1_3, c2 * s13, s2 * c1_3, c2 * c13 );
//					break;
//	
//				case 'ZXZ':
//					q.set( s2 * c1_3, s2 * s1_3, c2 * s13, c2 * c13 );
//					break;
//	
//				case 'XZX':
//					q.set( c2 * s13, s2 * s3_1, s2 * c3_1, c2 * c13 );
//					break;
//	
//				case 'YXY':
//					q.set( s2 * c3_1, c2 * s13, s2 * s3_1, c2 * c13 );
//					break;
//	
//				case 'ZYZ':
//					q.set( s2 * s3_1, s2 * c3_1, c2 * s13, c2 * c13 );
//					break;
//	
//				default:
//					console.warn( 'THREE.MathUtils: .setQuaternionFromProperEuler() encountered an unknown order: ' + order );
//	
//			}
//	
//		}
//	
//	};
}
//	
//	export { MathUtils };
