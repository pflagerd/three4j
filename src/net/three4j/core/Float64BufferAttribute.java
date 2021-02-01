package net.three4j.core;

import org.apache.commons.lang3.ArrayUtils;
import org.mozilla.types.Float64Array;
import org.mozilla.types.Int8Array;

public class Float64BufferAttribute extends BufferAttribute {

	public Float64BufferAttribute(Int8Array array, int itemSize, boolean normalized) {
	}

	public Float64BufferAttribute(double[] _array, int i) {
		this(_array, i, false);
	}

	public Float64BufferAttribute(double[] _array, int i, boolean normalized) {
		this._doubleArray = ArrayUtils.clone(_array);
		this._array = new Float64Array(_array);
	}

}
