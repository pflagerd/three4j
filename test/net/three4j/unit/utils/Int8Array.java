package net.three4j.unit.utils;

import java.util.Arrays;

public class Int8Array extends TypedArray {
	private byte[] _array;
	
	public Int8Array(int size) {
		super(size);
		_array = new byte[size];
	}
	
	public Int8Array(byte[] array) {
		super(array.length);
		_array = Arrays.copyOf(array, array.length);
	}
	
	public byte[] array() {
		return _array;
	}
	
	public void set(byte[] array) {
		set(array, 0);
	}

	public void set(byte[] array, int offset) {
		for (int i = 0; i < Math.min(_array.length, array.length); i++) {
			_array[i + offset] = array[i];
		}
	}

	@Override
	public Int8Array clone() {
		return new Int8Array(_array);
	}
	
	@Override
	public String toString() {
		return super.toString() + " { _array : " + Arrays.toString(_array) + " }";
	}

}
