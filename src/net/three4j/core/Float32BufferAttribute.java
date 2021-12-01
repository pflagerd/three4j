package net.three4j.core;

import org.apache.commons.lang3.ArrayUtils;
import org.mozilla.types.Float32Array;
import org.mozilla.types.Int8Array;

public class Float32BufferAttribute extends BufferAttribute {

	public Float32BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Float32BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Float32BufferAttribute(double[] _array, int i, boolean normalized) {
		super(new Float32Array(_array), i, normalized);
	}

}
