package org.mozilla.types;

import org.apache.commons.lang3.ArrayUtils;

public class TypedArray {
	public TypedArray(double[] array) {
		this._array = ArrayUtils.clone(array);
		this.length = array.length;
	}

	public TypedArray(int length) {
		this._array = new double[length];
		this.length = length;
	}

	public TypedArray clone() {
		return new TypedArray(this._array);
	}


	public double[] array() {
		return _array;
	}

	public TypedArray set(double[] array, int length) {
		this.length = length;
		this._array = array;
		return this;
	}

	public TypedArray set(double[] array) {
		this.length = array.length;
		this._array = array;
		return this;
	}

	protected double[] _array = new double[0];

	public int length;
}
