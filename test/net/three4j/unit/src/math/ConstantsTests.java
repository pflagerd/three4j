package net.three4j.unit.src.math;

import net.three4j.math.Vector2;
import net.three4j.math.Vector3;

public class ConstantsTests {
	public final static int x = 2;
	public final static int y = 3;
	public final static int z = 4;
	public final static int w = 5;

	public final static Vector2 negInf2 = new Vector2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	public final static Vector2 posInf2 = new Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	public final static Vector2 negOne2 = new Vector2(-1, -1);

	public final static Vector2 zero2 = new Vector2();
	public final static Vector2 one2 = new Vector2(1, 1);
	public final static Vector2 two2 = new Vector2(2, 2);

	public final static Vector3 negInf3 = new Vector3(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
			Double.NEGATIVE_INFINITY);
	public final static Vector3 posInf3 = new Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
			Double.POSITIVE_INFINITY);

	public final static Vector3 zero3 = new Vector3();
	public final static Vector3 one3 = new Vector3(1, 1, 1);
	public final static Vector3 two3 = new Vector3(2, 2, 2);

	public final static double eps = 0.0001;
}