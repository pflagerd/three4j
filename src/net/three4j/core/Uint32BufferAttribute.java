package net.three4j.core;

import org.mozilla.types.Int8Array;
import org.mozilla.types.Uint32Array;

public class Uint32BufferAttribute extends BufferAttribute {

	public Uint32BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Uint32BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Uint32BufferAttribute(double[] _array, int i, boolean normalized) {
		this._array = new Uint32Array(_array);
	}

}
