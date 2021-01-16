package net.three4j.unit.utils;

import java.util.Arrays;

public class Float32Array extends TypedArray {
	private double[] _array;
	
	public Float32Array(int size) {
		super(size);
		_array = new double[size];
	}
	
	public Float32Array(double[] array) {
		super(array.length);
		_array = Arrays.copyOf(array, array.length);
	}
	
	public double[] array() {
		return _array;
	}
	
	public void set(double[] array) {
		set(array, 0);
	}

	public void set(double[] array, int offset) {
		for (int i = 0; i < Math.min(_array.length, array.length); i++) {
			_array[i + offset] = array[i];
		}
	}

	@Override
	public Float32Array clone() {
		return new Float32Array(_array);
	}
	
	@Override
	public String toString() {
		return super.toString() + " { _array : " + Arrays.toString(_array) + " }";
	}

}
