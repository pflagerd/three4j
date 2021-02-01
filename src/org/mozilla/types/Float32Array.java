package org.mozilla.types;

import org.apache.commons.lang3.ArrayUtils;

public class Float32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Float32Array";

	private double[] _array = new double[0];

	public Float32Array(int size) {
		super(size);
	}

	public Float32Array(double[] array) {
		super(array.length);
		ArrayUtils.clone(array);
	}

	public double[] array() {
		return _array;
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
