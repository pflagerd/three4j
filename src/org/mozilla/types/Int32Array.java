package org.mozilla.types;

import net.three4j.unit.utils.TypedArray;

public class Int32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Int16Array";

	public Int32Array(double[] array) {
		super(array.length);
	}

	@Override
	public TypedArray clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
