package net.three4j;

public class utils {

	public static double arrayMin( double[] array ) {

		if ( array.length == 0 )
			return Double.POSITIVE_INFINITY;

		double min = array[ 0 ];

		for ( int i = 1, l = array.length; i < l; ++ i ) {

			if ( array[ i ] < min )
				min = array[ i ];

		}

		return min;
	}

	public static double arrayMax( double[] array ) {

		if ( array.length == 0 )
			return Double.NEGATIVE_INFINITY;

		double max = array[ 0 ];

		for ( int i = 1, l = array.length; i < l; ++i ) {

			if ( array[ i ] > max )
				max = array[ i ];

		}

		return max;

	}

	public static double arrayMax( int[] array ) {

		if ( array.length == 0 )
			return Integer.MIN_VALUE;

		int max = array[ 0 ];

		for ( int i = 1, l = array.length; i < l; ++i ) {

			if ( array[ i ] > max )
				max = array[ i ];

		}

		return max;

	}

	//const TYPED_ARRAYS = {
	//	Int8Array: Int8Array,
	//	Uint8Array: Uint8Array,
	//	// Workaround for IE11 pre KB2929437. See #11440
	//	Uint8ClampedArray: typeof Uint8ClampedArray !== 'undefined' ? Uint8ClampedArray : Uint8Array,
	//	Int16Array: Int16Array,
	//	Uint16Array: Uint16Array,
	//	Int32Array: Int32Array,
	//	Uint32Array: Uint32Array,
	//	Float32Array: Float32Array,
	//	Float64Array: Float64Array
	//};
	//
	//public getTypedArray( type, buffer ) {
	//
	//	return new TYPED_ARRAYS[ type ]( buffer );
	//
	//}
	//
}