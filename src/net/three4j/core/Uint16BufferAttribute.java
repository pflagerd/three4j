package net.three4j.core;

import org.mozilla.types.Int8Array;
import org.mozilla.types.Uint16Array;

public class Uint16BufferAttribute extends BufferAttribute {

	public Uint16BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Uint16BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Uint16BufferAttribute(double[] _array, int i, boolean normalized) {
		super(new Uint16Array(_array), i, normalized);
	}

}
