package org.mozilla.types;

public class Uint16Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 2;
	public static final String name = "Uint16Array";

	public Uint16Array(int[] _array) {
		super(_array);
	}
}
