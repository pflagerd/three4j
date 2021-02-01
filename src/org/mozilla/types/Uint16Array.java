package org.mozilla.types;

import net.three4j.unit.utils.TypedArray;

public class Uint16Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 2;
	public static final String name = "Uint16Array";

	public Uint16Array(double[] array) {
		super(array.length);
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
