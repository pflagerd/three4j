package org.mozilla.types;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

public class TypedArray {
	public TypedArray(int[] array) {
		this._iarray = ArrayUtils.clone(array);
		this.length = array.length;
	}

	public TypedArray(double[] array) {
		this._darray = ArrayUtils.clone(array);
		this.length = array.length;
	}

	public TypedArray(int length) {
		this._darray = new double[length];
		this.length = length;
	}

	public TypedArray clone() {
		return new TypedArray(this._darray);
	}

	public String toString() {
		Three4jToStringStyle three4jToStringStyle = new Three4jToStringStyle();
		three4jToStringStyle.setUseFieldNames(false);

		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, three4jToStringStyle);
		sortedReflectionToStringBuilder.setExcludeFieldNames("length");
		return sortedReflectionToStringBuilder.toString().replace("[", "").replace("]", "");
	}

	public double[] array() {
		return _darray;
	}

	public int[] array(int t) {
		return _iarray;
	}

	public TypedArray set(double[] array, int offset) {
		System.arraycopy(array, 0,  this._darray, offset, array.length);
		return this;
	}

	public TypedArray set(double[] array) {
		this.length = array.length;
		this._darray = ArrayUtils.clone(array);
		return this;
	}

	// Only one of these may be non-null
	protected double[] _darray;
	protected int[] _iarray;

	public int length;


}
