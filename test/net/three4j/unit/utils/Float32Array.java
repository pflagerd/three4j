package net.three4j.unit.utils;

import java.util.Arrays;

public class Float32Array extends TypedArray {
	private double[] _array;
	
	public Float32Array(double[] array) {
		super(array.length);
		_array = Arrays.copyOf(array, array.length);
	}
	
	public double[] array() {
		return Arrays.copyOf(_array, _array.length);
	}

	@Override
	public Float32Array clone() {
		return new Float32Array(_array);
	}
}
