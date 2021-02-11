package org.mozilla.types;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

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

	public String toString() {
		Three4jToStringStyle three4jToStringStyle = new Three4jToStringStyle();
		three4jToStringStyle.setUseFieldNames(false);

		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, three4jToStringStyle);
		sortedReflectionToStringBuilder.setExcludeFieldNames("length");
		return sortedReflectionToStringBuilder.toString().replace("[", "").replace("]", "");
	}

	public double[] array() {
		return _array;
	}

	public TypedArray set(double[] array, int offset) {
		System.arraycopy(array, 0,  this._array, offset, array.length);
		return this;
	}

	public TypedArray set(double[] array) {
		this.length = array.length;
		this._array = ArrayUtils.clone(array);
		return this;
	}



	protected double[] _array = new double[0];

	public int length;


}
