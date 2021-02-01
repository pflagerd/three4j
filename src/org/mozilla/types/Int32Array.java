package org.mozilla.types;

public class Int32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Int32Array";

	public Int32Array(double[] array) {
		super(array.length);
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
