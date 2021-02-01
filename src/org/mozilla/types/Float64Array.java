package org.mozilla.types;

public class Float64Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 8;
	public static final String name = "Float64Array";

	public Float64Array(double[] array) {
		super(array);
	}

}
