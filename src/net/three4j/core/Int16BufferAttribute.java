package net.three4j.core;

import org.mozilla.types.Int16Array;
import org.mozilla.types.Int8Array;

public class Int16BufferAttribute extends BufferAttribute {

	public Int16BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Int16BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Int16BufferAttribute(double[] _array, int i, boolean normalized) {
		this._array = new Int16Array(_array);
	}

}
