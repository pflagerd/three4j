package net.three4j.core;

import org.mozilla.types.Int8Array;
import org.mozilla.types.Uint8Array;

public class Uint8BufferAttribute extends BufferAttribute {

	public Uint8BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Uint8BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Uint8BufferAttribute(double[] _array, int i, boolean normalized) {
		this._array = new Uint8Array(_array);
	}

}
