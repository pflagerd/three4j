package org.mozilla.types;

public class Uint32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Uint32Array";

	public Uint32Array(double[] array) {
		super(array.length);
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
