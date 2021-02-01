package org.mozilla.types;

public class Uint8Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 1;
	public static final String name = "Uint8Array";

	public Uint8Array(double[] array) {
		super(array);
	}

}
