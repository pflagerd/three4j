package net.three4j.unit.src.core;

import org.junit.jupiter.api.Test;

import net.three4j.core.BufferAttribute;
import net.three4j.math.Color;
import net.three4j.math.Vector2;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;
import net.three4j.unit.utils.Float32Array;
import net.three4j.unit.utils.TypedArray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import { BufferAttribute } from "../../../../src/core/BufferAttribute";
//import { Color } from "../../../../src/math/Color";
//import { Vector2 } from "../../../../src/math/Vector2";
//import { Vector3 } from "../../../../src/math/Vector3";
//import { Vector4 } from "../../../../src/math/Vector4";
//import { DynamicDrawUsage } from "../../../../src/constants";
import static net.three4j.constants.DynamicDrawUsage;

public class BufferAttributeTests {
//	@Test
//	public void Instancing() {
//
//			assert.throws(
//				function () {
//
//					BufferAttribute a = new BufferAttribute( new double[] { 1, 2, 3, 4 }, 2, false );
//
//				},
//				/array should be a Typed Array/,
//				"Calling constructor with a simple array throws Error"
//			);
//
//		}
//
//		// PROPERTIES
//			@Test
//	public void needsUpdate() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// PUBLIC STUFF
//			@Test
//	public void isBufferAttribute() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
	@Test
	public void setUsage() {

		BufferAttribute attr = new BufferAttribute();
		attr.setUsage(DynamicDrawUsage);

		assertEquals(DynamicDrawUsage, attr.usage(), "Usage was set");

	}

	@Test
	public void copy() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(new double[] { 1, 2, 3, 4, 5, 6 }), 3);
		attr.setUsage(DynamicDrawUsage);
		attr.needsUpdate(true);

		BufferAttribute attrCopy = new BufferAttribute().copy(attr);

		assertTrue(attr.count() == attrCopy.count(), "count is equal");
		assertTrue(attr.itemSize() == attrCopy.itemSize(), "itemSize is equal");
		assertTrue(attr.usage() == attrCopy.usage(), "usage is equal");
		assertTrue(attr.array().length == attrCopy.array().length, "array length is equal");
		assertTrue(attr.version() == 1 && attrCopy.version() == 0, "version is not copied which is good");

	}

	@Test
	public void copyAt() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }), 3);
		BufferAttribute attr2 = new BufferAttribute(new Float32Array(9), 3);

		attr2.copyAt(1, attr, 2);
		attr2.copyAt(0, attr, 1);
		attr2.copyAt(2, attr, 0);

		double[] i = ((Float32Array) attr.array()).array();
		double[] i2 = ((Float32Array) attr2.array()).array(); // should be new double[] {4, 5, 6, 7, 8, 9, 1, 2, 3}

		assertTrue(i2[0] == i[3] && i2[1] == i[4] && i2[2] == i[5], "chunck copied to correct place");
		assertTrue(i2[3] == i[6] && i2[4] == i[7] && i2[5] == i[8], "chunck copied to correct place");
		assertTrue(i2[6] == i[0] && i2[7] == i[1] && i2[8] == i[2], "chunck copied to correct place");

	}

	@Test
	public void copyArray() {

		Float32Array f32a = new Float32Array(new double[] { 5, 6, 7, 8 });
		BufferAttribute a = new BufferAttribute(new Float32Array(new double[] { 1, 2, 3, 4 }), 2, false);

		a.copyArray(f32a);

		assertArrayEquals(f32a.array(), ((Float32Array) a.array()).array(), "Check array has new values");

	}

	@Test
	public void copyColorsArray() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(6), 3);

		attr.copyColorsArray(new Color[] { new Color(0, 0.5, 1), new Color(0.25, 1, 0) });

		double[] i = ((Float32Array) attr.array()).array();
		assertTrue(i[0] == 0 && i[1] == 0.5 && i[2] == 1, "first color was copied correctly");
		assertTrue(i[3] == 0.25 && i[4] == 1 && i[5] == 0, "second color was copied correctly");

	}

	@Test
	public void copyVector2sArray() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(4), 2);

		attr.copyVector2sArray(new Vector2[] { new Vector2(1, 2), new Vector2(4, 5) });

		double[] i = ((Float32Array) attr.array()).array();
		assertTrue(i[0] == 1 && i[1] == 2, "first vector was copied correctly");
		assertTrue(i[2] == 4 && i[3] == 5, "second vector was copied correctly");

	}

	@Test
	public void copyVector3sArray() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(6), 2);

		attr.copyVector3sArray(new Vector3[] { new Vector3(1, 2, 3), new Vector3(10, 20, 30) });

		double[] i = ((Float32Array) attr.array()).array();
		assertTrue(i[0] == 1 && i[1] == 2 && i[2] == 3, "first vector was copied correctly");
		assertTrue(i[3] == 10 && i[4] == 20 && i[5] == 30, "second vector was copied correctly");

	}

	@Test
	public void copyVector4sArray() {

		BufferAttribute attr = new BufferAttribute(new Float32Array(8), 2);

		attr.copyVector4sArray(new Vector4[] { new Vector4(1, 2, 3, 4), new Vector4(10, 20, 30, 40) });

		double[] i = ((Float32Array) attr.array()).array();
		assertTrue(i[0] == 1 && i[1] == 2 && i[2] == 3 && i[3] == 4, "first vector was copied correctly");
		assertTrue(i[4] == 10 && i[5] == 20 && i[6] == 30 && i[7] == 40, "second vector was copied correctly");

	}

	@Test
	public void set() {

		Float32Array f32a = new Float32Array(new double[] { 1, 2, 3, 4 });
		BufferAttribute a = new BufferAttribute(f32a, 2, false);
		Float32Array expected = new Float32Array(new double[] { 9, 2, 8, 4 });

		a.set(new double[] { 9 });
		a.set(new double[] { 8 }, 2);

		assertArrayEquals(expected.array(), ((Float32Array) a.array()).array(), "Check array has expected values");

	}

	@Test
	public void get_set() {
			Float32Array f32a = new Float32Array( new double[] { 1, 2, 3, 4, 5, 6, 7, 8 } );
			BufferAttribute a = new BufferAttribute( f32a, 4, false );
			Float32Array expected = new Float32Array( new double[] { 1, 2, - 3, - 4, - 5, - 6, 7, 8 } );

			a.setX( 1, a.getX( 1 ) * - 1 );
			a.setY( 1, a.getY( 1 ) * - 1 );
			a.setZ( 0, a.getZ( 0 ) * - 1 );
			a.setW( 0, a.getW( 0 ) * - 1 );

			assertArrayEquals( expected.array(), ((Float32Array) a.array()).array(), "Check all set* calls set the correct values" );

	}

//	@Test
//	public void setXY() {
//
//			Float32Array f32a = new Float32Array( new double[] { 1, 2, 3, 4 } );
//			BufferAttribute a = new BufferAttribute( f32a, 2, false );
//			Float32Array expected = new Float32Array( new double[] { - 1, - 2, 3, 4 } );
//
//			a.setXY( 0, - 1, - 2 );
//
//			assert.deepEqual( a.array, expected, "Check for the correct values" );
//
//		}
//
//	@Test
//	public void setXYZ() {
//
//			Float32Array f32a = new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } );
//			BufferAttribute a = new BufferAttribute( f32a, 3, false );
//			Float32Array expected = new Float32Array( new double[] { 1, 2, 3, - 4, - 5, - 6 } );
//
//			a.setXYZ( 1, - 4, - 5, - 6 );
//
//			assert.deepEqual( a.array, expected, "Check for the correct values" );
//
//		}
//
//	@Test
//	public void setXYZW() {
//
//			Float32Array f32a = new Float32Array( new double[] { 1, 2, 3, 4 } );
//			BufferAttribute a = new BufferAttribute( f32a, 4, false );
//			Float32Array expected = new Float32Array( new double[] { - 1, - 2, - 3, - 4 } );
//
//			a.setXYZW( 0, - 1, - 2, - 3, - 4 );
//
//			assert.deepEqual( a.array, expected, "Check for the correct values" );
//
//		}
//
//	@Test
//	public void onUpload() {
//
//			BufferAttribute a = new BufferAttribute();
//			var func = function () { };
//
//			a.onUpload( func );
//
//			assertEquals(func, a.onUploadCallback,  "Check callback was set properly" );
//
//		}
//
//	@Test
//	public void clone() {
//
//			BufferAttribute attr = new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 0.12, - 12 } ), 2 );
//			var attrCopy = attr.clone();
//
//			assertTrue( attr.array.length == attrCopy.array.length, "attribute was cloned" );
//			for ( var i = 0; i < attr.array.length; i ++ ) {
//
//				assertTrue( attr.arraynew double[] { i } == attrCopy.array[ i ], "array item is equal" );
//
//			}
//
//		}
//
//		// OTHERS
//	@Test
//	public void count() {
//
//			assertTrue(
//				new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } ), 3 ).count == 2,
//				"count is equal to the number of chunks"
//			);
//
//		}
//
//	}
//
//	QUnit.module( "Int8BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Uint8BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Uint8ClampedBufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Int16BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Uint16BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Int32BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Uint32BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Float32BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
//	QUnit.module( "Float64BufferAttribute", () => {
//
//		// INHERITANCE
//			@Test
//	public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//			@Test
//	public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
}
