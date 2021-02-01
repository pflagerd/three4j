package net.three4j.core;

import org.apache.commons.lang3.ArrayUtils;

import net.three4j.unit.utils.Int8Array;

public class Uint32BufferAttribute extends BufferAttribute {

	private double[] _array;

	public Uint32BufferAttribute(double[] array, int itemSize) {
		this(array, itemSize, false);
	}

	public Uint32BufferAttribute(double[] array, int itemSize, boolean normalized) {
		_array = ArrayUtils.clone(array);
	}

	public Uint32BufferAttribute(Int8Array array, int itemSize) {
		this(array, itemSize, false);
	}

	public Uint32BufferAttribute(Int8Array array, int itemSize, boolean normalized) {

	}

}
