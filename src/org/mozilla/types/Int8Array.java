package org.mozilla.types;

public class Int8Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 1;
	public static final String name = "Int8Array";

	public Int8Array(double[] array) {
		super(array);
	}
}
