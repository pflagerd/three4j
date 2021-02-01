package net.three4j.core;

import org.mozilla.types.Int8Array;

public class Int8BufferAttribute extends BufferAttribute {

	public Int8BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Int8BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Int8BufferAttribute(double[] _array, int i, boolean normalized) {
		this._array = new Int8Array(_array);
	}

}
