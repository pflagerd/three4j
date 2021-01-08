package net.three4j.unit.src.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import net.three4j.math.MathUtils;

//
//import { MathUtils } from '../../../../src/math/MathUtils';
//
//export default QUnit.module( 'Maths', () => {
public class MathUtilsTests {

	@Test
	public void generateUUID() {
		String a = MathUtils.generateUUID();
		String regex = "[A-Z0-9]{8}-[A-Z0-9]{4}-4[A-Z0-9]{3}-[A-Z0-9]{4}-[A-Z0-9]{12}";
		// note the fixed '4' here ----------^

		assertTrue(Pattern.matches(regex, a), "Generated UUID matches the expected pattern");

	}

	@Test
	public void clamp() {
		assertEquals(0.5, MathUtils.clamp(0.5, 0, 1), "Value already within limits");
		assertEquals(0, MathUtils.clamp(0, 0, 1), "Value equal to one limit");
		assertEquals(0, MathUtils.clamp(-0.1, 0, 1), "Value too low");
		assertEquals(1, MathUtils.clamp(1.1, 0, 1), "Value too high");
	}

	@Test
	public void euclideanModulo() {
		assertTrue(Double.isNaN(MathUtils.euclideanModulo(6, 0)), "Division by zero returns NaN");
		assertEquals(0, MathUtils.euclideanModulo(6, 1), "Divison by trivial divisor");
		assertEquals(0, MathUtils.euclideanModulo(6, 2), "Divison by non-trivial divisor");
		assertEquals(1, MathUtils.euclideanModulo(6, 5), "Divison by itself - 1");
		assertEquals(0, MathUtils.euclideanModulo(6, 6), "Divison by itself");
		assertEquals(6, MathUtils.euclideanModulo(6, 7), "Divison by itself + 1");
	}

	@Test
	public void mapLinear() {
		assertEquals(5, MathUtils.mapLinear(0.5, 0, 1, 0, 10), "Value within range");
		assertEquals(0, MathUtils.mapLinear(0.0, 0, 1, 0, 10), "Value equal to lower boundary");
		assertEquals(10, MathUtils.mapLinear(1.0, 0, 1, 0, 10), "Value equal to upper boundary");
	};

	@Test
	public void lerp() {
		assertEquals(1, MathUtils.lerp(1, 2, 0), "Value equal to lower boundary");
		assertEquals(2, MathUtils.lerp(1, 2, 1), "Value equal to upper boundary");
		assertEquals(1.4, MathUtils.lerp(1, 2, 0.4), 1.4, "Value within range");
	}

	@Test
	public void smoothstep() {
		assertEquals(0, MathUtils.smoothstep(-1, 0, 2), "Value lower than minimum");
		assertEquals(0, MathUtils.smoothstep(0, 0, 2), "Value equal to minimum");
		assertEquals(0.15625, MathUtils.smoothstep(0.5, 0, 2), "Value within limits");
		assertEquals(0.5, MathUtils.smoothstep(1, 0, 2), "Value within limits");
		assertEquals(0.84375, MathUtils.smoothstep(1.5, 0, 2), "Value within limits");
		assertEquals(1, MathUtils.smoothstep(2, 0, 2), "Value equal to maximum");
		assertEquals(1, MathUtils.smoothstep(3, 0, 2), "Value highter than maximum");
	}

	@Test
	public void smootherstep() {
		assertEquals(0, MathUtils.smootherstep(-1, 0, 2), "Value lower than minimum");
		assertEquals(0, MathUtils.smootherstep(0, 0, 2), "Value equal to minimum");
		assertEquals(0.103515625, MathUtils.smootherstep(0.5, 0, 2), "Value within limits");
		assertEquals(0.5, MathUtils.smootherstep(1, 0, 2), "Value within limits");
		assertEquals(0.896484375, MathUtils.smootherstep(1.5, 0, 2), "Value within limits");
		assertEquals(1, MathUtils.smootherstep(2, 0, 2), "Value equal to maximum");
		assertEquals(1, MathUtils.smootherstep(3, 0, 2), "Value highter than maximum");
	}

	@Test
	public void randInt() {

			int low = 1, high = 3;
			int a = MathUtils.randInt( low, high );

			assertTrue( a >= low, "Value equal to or higher than lower limit" );
			assertTrue( a <= high, "Value equal to or lower than upper limit" );

		}

	@Test
	public void randFloat() {

			double low = 1, high = 3;
			double a = MathUtils.randFloat( low, high );

			assertTrue( a >= low, "Value equal to or higher than lower limit" );
			assertTrue( a <= high, "Value equal to or lower than upper limit" );

		}

	@Test
	public void randFloatSpread() {

			double a = MathUtils.randFloatSpread( 3 );

			assertTrue( a > - 3 / 2, "Value higher than lower limit" );
			assertTrue( a < 3 / 2, "Value lower than upper limit" );

		}

	@Test
	public void degToRad() {
		assertEquals(0, MathUtils.degToRad(0), "0 degrees");
		assertEquals(Math.PI / 2, MathUtils.degToRad(90), "90 degrees");
		assertEquals(Math.PI, MathUtils.degToRad(180), "180 degrees");
		assertEquals(Math.PI * 2, MathUtils.degToRad(360), "360 degrees");
	}

	@Test
	public void radToDeg() {
		assertEquals(0, MathUtils.radToDeg(0), "0 radians");
		assertEquals(90, MathUtils.radToDeg(Math.PI / 2), "Math.PI / 2 radians");
		assertEquals(180, MathUtils.radToDeg(Math.PI), "Math.PI radians");
		assertEquals(360, MathUtils.radToDeg(Math.PI * 2), "Math.PI * 2 radians");
	}

	@Test
	public void isPowerOfTwo() {
		assertEquals(false, MathUtils.isPowerOfTwo(0), "0 is not a Power of Two");
		assertEquals(true, MathUtils.isPowerOfTwo(1), "1 is a Power of Two");
		assertEquals(true, MathUtils.isPowerOfTwo(2), "2 is a Power of Two");
		assertEquals(false, MathUtils.isPowerOfTwo(3), "3 is not a Power of Two");
		assertEquals(true, MathUtils.isPowerOfTwo(4), "4 is a Power of Two");
	}

	@Test
	public void ceilPowerOfTwo() {
		assertEquals(MathUtils.ceilPowerOfTwo(1), 1, "Closest higher PoT to 1 is 1");
		assertEquals(MathUtils.ceilPowerOfTwo(3), 4, "Closest higher PoT to 3 is 4");
		assertEquals(MathUtils.ceilPowerOfTwo(4), 4, "Closest higher PoT to 4 is 4");
	}

	@Test
	public void floorPowerOfTwo() {
		assertEquals(MathUtils.floorPowerOfTwo(1), 1, "Closest lower PoT to 1 is 1");
		assertEquals(MathUtils.floorPowerOfTwo(3), 2, "Closest lower PoT to 3 is 2");
		assertEquals(MathUtils.floorPowerOfTwo(4), 4, "Closest lower PoT to 4 is 4");
	}
}
