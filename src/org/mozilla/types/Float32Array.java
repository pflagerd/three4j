package org.mozilla.types;

public class Float32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Float32Array";

	private double[] _array = new double[0];

	public Float32Array(int size) {
		super(size);
	}

	public Float32Array(double[] array) {
		super(array);
	}
}
