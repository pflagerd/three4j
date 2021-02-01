package net.three4j.core;

import org.apache.commons.lang3.ArrayUtils;

import net.three4j.unit.utils.Int8Array;

public class Uint16BufferAttribute extends BufferAttribute {

	public Uint16BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Uint16BufferAttribute(double[] index, int i) {
		this._doubleArray = ArrayUtils.clone(index);
	}

}
