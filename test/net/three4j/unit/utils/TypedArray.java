package net.three4j.unit.utils;

public abstract class TypedArray {
	public TypedArray(int length) {
		this.length = length;
	}
	
	public abstract TypedArray clone();
	
	public final int length;
}
