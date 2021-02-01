package net.three4j.core;

import org.mozilla.types.Int32Array;
import org.mozilla.types.Int8Array;

public class Int32BufferAttribute extends BufferAttribute {

	public Int32BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Int32BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Int32BufferAttribute(double[] _array, int i, boolean normalized) {
		this._array = new Int32Array(_array);
	}

}
