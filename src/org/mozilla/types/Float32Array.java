package org.mozilla.types;

public class Float32Array extends TypedArray {

	public static int BYTES_PER_ELEMENT = 4;
	public static final String name = "Float32Array";

	public Float32Array(int size) {
		super(size);
	}

	private static float[] doubleArrayToFloatArray(double[] array) {
		float[] fa = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < Float.MIN_VALUE || array[i] > Float.MAX_VALUE)
				throw new RuntimeException("double value exceeds range of Float32 element.");
			fa[i] = (float)array[i];
		}
		return fa;
	}

	public Float32Array(double[] array) {
		super(doubleArrayToFloatArray(array));
	}

	public Float32Array(float[] array) {
		super(array);
	}
}
