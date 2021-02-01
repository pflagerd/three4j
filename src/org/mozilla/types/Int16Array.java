package org.mozilla.types;


public class Int16Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 2;
	public static final String name = "Int16Array";

	public Int16Array(double[] array) {
		super(array.length);
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
