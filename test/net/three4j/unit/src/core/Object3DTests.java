package net.three4j.unit.src.core;

import static net.three4j.unit.src.math.ConstantsTests.eps;
import static net.three4j.unit.src.math.ConstantsTests.w;
import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.three4j.core.Object3D;
import net.three4j.math.Euler;
import net.three4j.math.Matrix4;
import net.three4j.math.Quaternion;
import net.three4j.math.Vector3;

public class Object3DTests {

	private static final double RadToDeg = 180 / Math.PI;

	boolean eulerEquals(Euler a, Euler b) {
		return eulerEquals(a, b, 0.0001);
	}

	boolean eulerEquals(Euler a, Euler b, double tolerance) {

		if (a.order() != b.order()) {

			return false;

		}

		return (Math.abs(a.x() - b.x()) <= tolerance && Math.abs(a.y() - b.y()) <= tolerance && Math.abs(a.z() - b.z()) <= tolerance);

	};

//		// INHERITANCE
//			@Test
//	public void Extending() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////		// INSTANCING
////			@Test
//	public void Instancing() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////		// STATIC STUFF
////			@Test
//	public void DefaultUp() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////			@Test
//	public void DefaultMatrixAutoUpdate() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////		// PUBLIC STUFF
////			@Test
//	public void isObject3D() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////			@Test
//	public void onBeforeRender() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////			@Test
//	public void onAfterRender() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
	
	@Test
	public void applyMatrix4() {

		Object3D a = new Object3D();
		Matrix4 m = new Matrix4();
		Vector3 expectedPos = new Vector3(x, y, z);
		Quaternion expectedQuat = new Quaternion(0.5 * Math.sqrt(2), 0, 0, 0.5 * Math.sqrt(2));

		m.makeRotationX(Math.PI / 2);
		m.setPosition(new Vector3(x, y, z));

		a.applyMatrix4(m);

		assertTrue(a.position().equals(expectedPos), "Position has the expected values");
		assertTrue(Math.abs(a.quaternion().x() - expectedQuat.x()) <= eps && Math.abs(a.quaternion().y() - expectedQuat.y()) <= eps && Math.abs(a.quaternion().z() - expectedQuat.z()) <= eps, "Quaternion has the expected values");

	}

	@Test
	public void applyQuaternion() {

		Object3D a = new Object3D();
		double sqrt = 0.5 * Math.sqrt(2);
		Quaternion quat = new Quaternion(0, sqrt, 0, sqrt);
		Quaternion expected = new Quaternion(sqrt / 2, sqrt / 2, 0, 0);

		a.quaternion().set(0.25, 0.25, 0.25, 0.25);
		a.applyQuaternion(quat);

		assertTrue(Math.abs(a.quaternion().x() - expected.x()) <= eps && Math.abs(a.quaternion().y() - expected.y()) <= eps && Math.abs(a.quaternion().z() - expected.z()) <= eps, "Quaternion has the expected values");

	}

	@Test
	public void setRotationFromAxisAngle() {

		Object3D a = new Object3D();
		Vector3 axis = new Vector3(0, 1, 0);
		double angle = Math.PI;
		Euler expected = new Euler(-Math.PI, 0, -Math.PI);
		Euler euler = new Euler();

		a.setRotationFromAxisAngle(axis, angle);
		euler.setFromQuaternion(a.getWorldQuaternion(new Quaternion()));
		assertTrue(eulerEquals(euler, expected), "Correct values after rotation");

		axis.set(1, 0, 0);
		angle = 0;
		expected.set(0, 0, 0);

		a.setRotationFromAxisAngle(axis, angle);
		euler.setFromQuaternion(a.getWorldQuaternion(new Quaternion()));
		assertTrue(eulerEquals(euler, expected), "Correct values after zeroing");

	}

	@Test
	public void setRotationFromEuler() {

		Object3D a = new Object3D();
		Euler rotation = new Euler((45 / RadToDeg), 0, Math.PI);
		Euler expected = rotation.clone(); // bit obvious
		Euler euler = new Euler();

		a.setRotationFromEuler(rotation);
		euler.setFromQuaternion(a.getWorldQuaternion(new Quaternion()));
		assertTrue(eulerEquals(euler, expected), "Correct values after rotation");

	}

	@Test
	public void setRotationFromMatrix() {

		Object3D a = new Object3D();
		Matrix4 m = new Matrix4();
		Vector3 eye = new Vector3(0, 0, 0);
		Vector3 target = new Vector3(0, 1, -1);
		Vector3 up = new Vector3(0, 1, 0);
		Euler euler = new Euler();

		m.lookAt(eye, target, up);
		a.setRotationFromMatrix(m);
		euler.setFromQuaternion(a.getWorldQuaternion(new Quaternion()));
		assertEquals(45., euler.x() * RadToDeg, eps, "Correct rotation angle");

	}

	@Test
	public void setRotationFromQuaternion() {

		Object3D a = new Object3D();
		Quaternion rotation = new Quaternion().setFromEuler(new Euler(Math.PI, 0, -Math.PI));
		Euler euler = new Euler();

		a.setRotationFromQuaternion(rotation);
		euler.setFromQuaternion(a.getWorldQuaternion(new Quaternion()));
		assertTrue(eulerEquals(euler, new Euler(Math.PI, 0, -Math.PI)), "Correct values after rotation");

	}

////
////			@Test
//	public void rotateOnAxis() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//
////
////			@Test
//	public void rotateOnWorldAxis() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}
//

	@Test
	public void rotateX() {

		Object3D obj = new Object3D();

		double angleInRad = 1.562;
		obj.rotateX(angleInRad);

		assertEquals(angleInRad, obj.rotation().x(), eps, "x is equal");

	}

//
	@Test
	public void rotateY() {

		Object3D obj = new Object3D();

		double angleInRad = -0.346;
		obj.rotateY(angleInRad);

		assertEquals(angleInRad, obj.rotation().y(), eps, "y is equal");

	}

	@Test
	public void rotateZ() {

		Object3D obj = new Object3D();

		double angleInRad = 1;
		obj.rotateZ(angleInRad);

		assertEquals(angleInRad, obj.rotation().z(), eps, "z is equal");

	}

	@Test
	public void translateOnAxis() {

		Object3D obj = new Object3D();

		obj.translateOnAxis(new Vector3(1, 0, 0), 1);
		obj.translateOnAxis(new Vector3(0, 1, 0), 1.23);
		obj.translateOnAxis(new Vector3(0, 0, 1), -4.56);

		assertTrue(obj.position().equals(new Vector3(1, 1.23, -4.56)));

	}

	@Test
	public void translateX() {

		Object3D obj = new Object3D();
		obj.translateX(1.234);

		assertEquals(1.234, obj.position().x(), eps, "x is equal");

	}

	@Test
	public void translateY() {

		Object3D obj = new Object3D();
		obj.translateY(1.234);

		assertEquals(1.234, obj.position().y(), eps, "y is equal");

	}

	@Test
	public void translateZ() {

		Object3D obj = new Object3D();
		obj.translateZ(1.234);

		assertEquals(1.234, obj.position().z(), eps, "z is equal");

	}

	@Test
	public void localToWorld() {

			Vector3 v = new Vector3();
			Vector3 expectedPosition = new Vector3( 5, - 1, - 4 );

			Object3D parent = new Object3D();
			Object3D child = new Object3D();

			parent.position().set( 1, 0, 0 );
			parent.rotation().set( 0, Math.PI / 2, 0 );
			parent.scale().set( 2, 1, 1 );

			child.position().set( 0, 1, 0 );
			child.rotation().set( Math.PI / 2, 0, 0 );
			child.scale().set( 1, 2, 1 );

			parent.add( child );
			parent.updateMatrixWorld();

			child.localToWorld( v.set( 2, 2, 2 ) );

			assertTrue(
				Math.abs( v.x() - expectedPosition.x() ) <= eps &&
				Math.abs( v.y() - expectedPosition.y() ) <= eps &&
				Math.abs( v.z() - expectedPosition.z() ) <= eps,
				"local vector is converted to world"
			);

	}

	@Test
	public void worldToLocal() {

			final Vector3 v = new Vector3();
			final Vector3 expectedPosition = new Vector3( - 1, 0.5, - 1 );

			final Object3D parent = new Object3D();
			final Object3D child = new Object3D();

			parent.position().set( 1, 0, 0 );
			parent.rotation().set( 0, Math.PI / 2, 0 );
			parent.scale().set( 2, 1, 1 );

			child.position().set( 0, 1, 0 );
			child.rotation().set( Math.PI / 2, 0, 0 );
			child.scale().set( 1, 2, 1 );

			parent.add( child );
			parent.updateMatrixWorld();

			child.worldToLocal( v.set( 2, 2, 2 ) );

			assertTrue(
				Math.abs( v.x() - expectedPosition.x() ) <= eps &&
				Math.abs( v.y() - expectedPosition.y() ) <= eps &&
				Math.abs( v.z() - expectedPosition.z() ) <= eps,
				"world vector is converted to local"
			);

	}

	@Test
	public void lookAt() {

			Object3D obj = new Object3D();
			obj.lookAt( new Vector3( 0, -1, 1 ) );

			assertEquals( 45, obj.rotation().x() * RadToDeg, eps, "x is equal" );

	}

	@Test
	public void add_remove_clear() {

			Object3D a = new Object3D();
			Object3D child1 = new Object3D();
			Object3D child2 = new Object3D();

			assertEquals(0, a.children.length(),  "Starts with no children" );

			a.add( child1 );
			assertEquals(1, a.children.length(),  "The first child was added" );
			assertEquals(child1, a.children.get( 0 ),  "It's the right one" );

			a.add( child2 );
			assertEquals(2, a.children.length(),  "The second child was added" );
			assertEquals(child2, a.children.get( 1 ),  "It's the right one" );
			assertEquals(child1, a.children.get( 0 ),  "The first one is still there" );

			a.remove( child1 );
			assertEquals(1, a.children.length(),  "The first child was removed" );
			assertEquals(child2, a.children.get( 0 ),  "The second one is still there" );

			a.add( child1 );
			a.remove( child1, child2 );
			assertEquals(0, a.children.length(),  "Both children were removed at once" );

			child1.add( child2 );
			assertEquals(1, child1.children.length(),  "The second child was added to the first one" );
			a.add( child2 );
			assertEquals(1, a.children.length(),  "The second one was added to the parent (no remove)" );
			assertEquals(child2, a.children.get( 0 ),  "The second one is now the parent's child again" );
			assertEquals(0, child1.children.length(),  "The first one no longer has any children" );

			a.add( child1 );
			assertEquals(2, a.children.length(),  "The first child was added to the parent" );
			a.clear();
			assertEquals(0, a.children.length(),  "All childrens were removed" );
			assertEquals(null, child1.parent(),  "First child has no parent" );
			assertEquals(null, child2.parent(),  "Second child has no parent" );

	}

//	@Test
//	public void getObjectById_getObjectByName_getObjectByProperty() {
//
//			Object3D parent = new Object3D();
//			Object3D childName = new Object3D();
//			Object3D childId = new Object3D(); // id = parent.id + 2
//			Object3D childNothing = new Object3D();
//
//			parent.prop = true;
//			childName.name = "foo";
//			parent.add( childName, childId, childNothing );
//
//			assertEquals(true , parent.getObjectByProperty( "prop"),  parent, "Get parent by its own property" );
//			assertEquals(childName, parent.getObjectByName( "foo" ),  "Get child by name" );
//			assertEquals(childId, parent.getObjectById( parent.id + 2 ),  "Get child by Id" );
//			assert.strictEqual(
//				parent.getObjectByProperty( "no-property", 'no-value' ), undefined,
//				"Unknown property results in undefined"
//			);
//
//	}

	@Test
	public void getWorldPosition() {
			Object3D a = new Object3D();
			Object3D b = new Object3D();
			Vector3 expectedSingle = new Vector3( x, y, z );
			Vector3 expectedParent = new Vector3( x, y, 0 );
			Vector3 expectedChild = new Vector3( x, y, 7 + ( z - z ) );
			Vector3 position = new Vector3();

			a.translateX( x );
			a.translateY( y );
			a.translateZ( z );

			assertTrue( expectedSingle.equals(a.getWorldPosition( position )), "WorldPosition as expected for single object" );

			// translate child and then parent
			b.translateZ( 7 );
			a.add( b );
			a.translateZ( - z );

			assertTrue( expectedParent.equals(a.getWorldPosition( position )), "WorldPosition as expected for parent" );
			assertTrue( expectedChild.equals(b.getWorldPosition( position )), "WorldPosition as expected for child" );
	}

////
////			@Test
//	public void getWorldQuaternion() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}


	@Test
	public void getWorldScale() {

			Object3D a = new Object3D();
			Matrix4 m = new Matrix4().makeScale( x, y, z );
			Vector3 expected = new Vector3( x, y, z );

			a.applyMatrix4( m );

			assertTrue( expected.equals(a.getWorldScale( new Vector3() )), "WorldScale as expected" );

	}


	@Test
	public void getWorldDirection() {

			Object3D a = new Object3D();
			Vector3 expected = new Vector3( 0, - 0.5 * Math.sqrt( 2 ), 0.5 * Math.sqrt( 2 ) );
			Vector3 direction = new Vector3();

			a.lookAt( new Vector3( 0, - 1, 1 ) );
			a.getWorldDirection( direction );

			assertTrue(
				Math.abs( direction.x() - expected.x() ) <= eps &&
				Math.abs( direction.y() - expected.y() ) <= eps &&
				Math.abs( direction.z() - expected.z() ) <= eps,
				"Direction has the expected values"
			);

	}


	@Test
	public void localTransformVariableInstantiation() {

			Object3D a = new Object3D();
			Object3D b = new Object3D();
			Object3D c = new Object3D();
			Object3D d = new Object3D();

			a.getWorldDirection( new Vector3() );
			a.lookAt( new Vector3( 0, - 1, 1 ) );

			assertTrue( true, "Calling lookAt after getWorldDirection does not create errors" );

			b.getWorldPosition( new Vector3() );
			b.lookAt( new Vector3( 0, - 1, 1 ) );

			assertTrue( true, "Calling lookAt after getWorldPosition does not create errors" );

			c.getWorldQuaternion( new Quaternion() );
			c.lookAt( new Vector3( 0, - 1, 1 ) );

			assertTrue( true, "Calling lookAt after getWorldQuaternion does not create errors" );

			d.getWorldScale( new Vector3() );
			d.lookAt( new Vector3( 0, - 1, 1 ) );

			assertTrue( true, "Calling lookAt after getWorldScale does not create errors" );

	}

////
////			@Test
//	public void raycast() {
////
////			assertTrue( false, "everything's gonna be alright" );
////
//	}


//	@Test
//	public void traverse_traverseVisible_traverseAncestors() {
//
//			Object3D a = new Object3D();
//			Object3D b = new Object3D();
//			Object3D c = new Object3D();
//			Object3D d = new Object3D();
//			ArrayList<String> names = new ArrayList<String>();
//			String[] expectedNormal = new String[] { "parent", "child", "childchild 1", "childchild 2" };
//			String[] expectedVisible = new String[] { "parent", "child", "childchild 2" };
//			String[] expectedAncestors = new String[] { "child", "parent" };
//
//			a.name = "parent";
//			b.name = "child";
//			c.name = "childchild 1";
//			c.visible = false;
//			d.name = "childchild 2";
//
//			b.add( c );
//			b.add( d );
//			a.add( b );
//
//			a.traverse( function ( obj ) {
//
//				names.push( obj.name );
//
//			}
//			assertArrayEquals(expectedNormal, names,  "Traversed objects in expected order" );
//
//			var names = [];
//			a.traverseVisible( function ( obj ) {
//
//				names.push( obj.name );
//
//			}
//			assertArrayEquals(expectedVisible, names,  "Traversed visible objects in expected order" );
//
//			var names = [];
//			c.traverseAncestors( function ( obj ) {
//
//				names.push( obj.name );
//
//			}
//			assertArrayEquals(expectedAncestors, names,  "Traversed ancestors in expected order" );
//
//	}


	@Test
	public void updateMatrix() {

			Object3D a = new Object3D();
			a.position().set( 2, 3, 4 );
			a.quaternion().set( 5, 6, 7, 8 );
			a.scale().set( 9, 10, 11 );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1},
				a.matrix().elements, "Updating position, quaternion, or scale has no effect to matrix until calling updateMatrix()" );

			a.updateMatrix();

			assertArrayEquals( new double[] {
				-1521, 1548, -234, 0,
				-520, -1470, 1640, 0,
				1826, 44, -1331, 0,
				2, 3, 4, 1}, a.matrix().elements, 
			 "matrix is calculated from position, quaternion, and scale" );

			assertEquals( true, a.matrixWorldNeedsUpdate(), "The flag indicating world matrix needs to be updated should be true" );

	}


	@Test
	public void updateMatrixWorld() {

			Object3D parent = new Object3D();
			Object3D child = new Object3D();

			// -- Standard usage test

			parent.position().set( 1, 2, 3 );
			child.position().set( 4, 5, 6 );
			parent.add( child );

			parent.updateMatrixWorld();

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 2, 3, 1}, parent.matrix().elements, 
			"updateMatrixWorld() updates local matrix" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 2, 3, 1}, parent.matrixWorld().elements, 
			"updateMatrixWorld() updates world matrix" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				4, 5, 6, 1}, child.matrix().elements, 
			"updateMatrixWorld() updates children's local matrix" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				5, 7, 9, 1}, child.matrixWorld().elements, 
			"updateMatrixWorld() updates children's world matrices from their parent world matrix and their local matrices" );

			assertEquals( false, parent.matrixWorldNeedsUpdate() || child.matrixWorldNeedsUpdate(), "The flag indicating world matrix needs to be updated should be false after updating world matrix" );

			// -- No sync between local position/quaternion/scale/matrix and world matrix test

			parent.position().set( 0, 0, 0 );
			parent.updateMatrix();

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 2, 3, 1}, parent.matrixWorld().elements, 
			"Updating position, quaternion, scale, or local matrix has no effect to world matrix until calling updateWorldMatrix()" );

			// -- matrixAutoUpdate = false test
			child.position().set( 0, 0, 0 );
			parent.updateMatrixWorld();

			parent.position().set( 1, 2, 3 );
			parent.matrixAutoUpdate(false);
			child.matrixAutoUpdate(false);
			parent.updateMatrixWorld();

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1}, parent.matrix().elements, 
			"updateMatrixWorld() doesn't update local matrix if matrixAutoUpdate is false" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1}, parent.matrixWorld().elements, 
			"World matrix isn't updated because local matrix isn't updated and the flag indicating world matrix needs to be updated didn't rise" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1}, child.matrixWorld().elements, 
			"No effect to child world matrix if parent local and world matrices and child local matrix are not updated" );

			// -- Propagation to children world matrices test

			parent.matrixAutoUpdate(true);
			parent.updateMatrixWorld();

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 2, 3, 1}, child.matrixWorld().elements, 
			"Updating parent world matrix has effect to children world matrices even if children local matrices aren't changed" );

			// -- force argument test

			// Resetting the local and world matrices to the origin
			child.position().set( 0, 0, 0 );
			child.matrixAutoUpdate(true);
			parent.updateMatrixWorld();

			parent.position().set( 1, 2, 3 );
			parent.updateMatrix();
			parent.matrixAutoUpdate(false);
			parent.matrixWorldNeedsUpdate(false);

			parent.updateMatrixWorld( true );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 2, 3, 1}, parent.matrixWorld().elements, 
			"force = true forces to update world matrix even if local matrix is not changed" );

			// -- Restriction test: No effect to parent matrices

			// Resetting the local and world matrices to the origin
			parent.position().set( 0, 0, 0 );
			child.position().set( 0, 0, 0 );
			parent.matrixAutoUpdate(true);
			child.matrixAutoUpdate(true);
			parent.updateMatrixWorld();

			parent.position().set( 1, 2, 3 );
			child.position().set( 4, 5, 6 );

			child.updateMatrixWorld();

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1}, parent.matrix().elements, 
			"updateMatrixWorld() doesn't update parent local matrix" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1}, parent.matrixWorld().elements, 
			"updateMatrixWorld() doesn't update parent world matrix" );

			assertArrayEquals( new double[] {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				4, 5, 6, 1}, child.matrixWorld().elements, 
			"updateMatrixWorld() calculates world matrix from the current parent world matrix" );
	}

	@Test
	public void updateWorldMatrix() {

		Object3D object = new Object3D();
		Object3D parent = new Object3D();
		Object3D child = new Object3D();

		Matrix4 m = new Matrix4();
		Vector3 v = new Vector3();

			parent.add( object );
			object.add( child );

			parent.position().set( 1, 2, 3 );
			object.position().set( 4, 5, 6 );
			child.position().set( 7, 8, 9 );

			// Update the world matrix of an object

			object.updateWorldMatrix();

			assertArrayEquals(m.elements, parent.matrix().elements,  "No effect to parents' local matrices" );

			assertArrayEquals(m.elements, parent.matrixWorld().elements,  "No effect to parents' world matrices" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrix().elements,  "Object's local matrix is updated" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrixWorld().elements,  "Object's world matrix is updated" );

			assertArrayEquals(m.identity().elements, child.matrix().elements,  "No effect to children's local matrices" );

			assertArrayEquals(m.elements, child.matrixWorld().elements,  "No effect to children's world matrices" );

			// Update the world matrices of an object and its parents

			object.matrix().identity();
			object.matrixWorld().identity();

			object.updateWorldMatrix( true, false );

			assertArrayEquals(m.setPosition( parent.position() ).elements, parent.matrix().elements,  "Parents' local matrices are updated" );

			assertArrayEquals(m.setPosition( parent.position() ).elements, parent.matrixWorld().elements,  "Parents' world matrices are updated" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrix().elements,  "Object's local matrix is updated" );

			assertArrayEquals(m.setPosition( v.copy( parent.position() ).add( object.position() ) ).elements, object.matrixWorld().elements,  "Object's world matrix is updated" );

			assertArrayEquals(m.identity().elements, child.matrix().elements,  "No effect to children's local matrices" );

			assertArrayEquals(m.identity().elements, child.matrixWorld().elements,  "No effect to children's world matrices" );

			// Update the world matrices of an object and its children

			parent.matrix().identity();
			parent.matrixWorld().identity();
			object.matrix().identity();
			object.matrixWorld().identity();

			object.updateWorldMatrix( false, true );

			assertArrayEquals(m.elements, parent.matrix().elements,  "No effect to parents' local matrices" );

			assertArrayEquals(m.elements, parent.matrixWorld().elements,  "No effect to parents' world matrices" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrix().elements,  "Object's local matrix is updated" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrixWorld().elements,  "Object's world matrix is updated" );

			assertArrayEquals(m.setPosition( child.position() ).elements, child.matrix().elements,  "Children's local matrices are updated" );

			assertArrayEquals(m.setPosition( v.copy( object.position() ).add( child.position() ) ).elements, child.matrixWorld().elements,  "Children's world matrices are updated" );

			// Update the world matrices of an object and its parents and children

			object.matrix().identity();
			object.matrixWorld().identity();
			child.matrix().identity();
			child.matrixWorld().identity();

			object.updateWorldMatrix( true, true );

			assertArrayEquals(m.setPosition( parent.position() ).elements, parent.matrix().elements,  "Parents' local matrices are updated" );

			assertArrayEquals(m.setPosition( parent.position() ).elements, parent.matrixWorld().elements,  "Parents' world matrices are updated" );

			assertArrayEquals(m.setPosition( object.position() ).elements, object.matrix().elements,  "Object's local matrix is updated" );

			assertArrayEquals(m.setPosition( v.copy( parent.position() ).add( object.position() ) ).elements, object.matrixWorld().elements,  "Object's world matrix is updated" );

			assertArrayEquals(m.setPosition( child.position() ).elements, child.matrix().elements,  "Children's local matrices are updated" );

			assertArrayEquals(m.setPosition( v.copy( parent.position() ).add( object.position() ).add( child.position() ) ).elements, child.matrixWorld().elements,  "Children's world matrices are updated" );

			object.matrixAutoUpdate(false); // test

			object.matrix().identity();
			object.matrixWorld().identity();

			object.matrixAutoUpdate(false);
			object.updateWorldMatrix( true, false );

			assertArrayEquals(m.identity().elements, object.matrix().elements,  "No effect to object's local matrix if matrixAutoUpdate is false" );

			assertArrayEquals(m.setPosition( parent.position() ).elements, object.matrixWorld().elements,  "object's world matrix is updated even if matrixAutoUpdate is false" );

	}

	@Test
	public void toJSON() {
//
//			Object3D a = new Object3D();
//			Object3D child = new Object3D();
//			Object3D childChild = new Object3D();
//
//			a.name = "a's name";
//			a.matrix.set( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
//			a.visible = false;
//			a.castShadow = true;
//			a.receiveShadow = true;
//			a.userDatanew double[] { "foo" } = "bar";
//
//			child.uuid = "5D4E9AE8-DA61-4912-A575-71A5BE3D72CD";
//			childChild.uuid = "B43854B3-E970-4E85-BD41-AAF8D7BFA189";
//			child.add( childChild );
//			a.add( child );
//
//			var gold = {
//				"metadata": {
//					"version": 4.5,
//					"type": "Object",
//					"generator": "Object3D.toJSON"
//				},
//				"object": {
//					"uuid": "0A1E4F43-CB5B-4097-8F82-DC2969C0B8C2",
//					"type": "Object3D",
//					"name": "a's name",
//					"castShadow": true,
//					"receiveShadow": true,
//					"visible": false,
//					"userData": { "foo": "bar" },
//					"layers": 1,
//					"matrix": .get( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
//					"children": .get(
//						{
//							"uuid": "5D4E9AE8-DA61-4912-A575-71A5BE3D72CD",
//							"type": "Object3D",
//							"layers": 1,
//							"matrix": [ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 );
//							"children": [
//								{
//									"uuid": "B43854B3-E970-4E85-BD41-AAF8D7BFA189",
//									"type": "Object3D",
//									"layers": 1,
//									"matrix": [ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 ]
//								}
//							]
//						}
//					]
//				}
//			};
//
//			// hacks
//			var out = a.toJSON();
//			out.object.uuid = "0A1E4F43-CB5B-4097-8F82-DC2969C0B8C2";
//
//			assertArrayEquals(gold, out,  "JSON is as expected" );
//
	}


	@Test
	public void $clone() {

			Object3D a = null;
			Object3D b = new Object3D();

			assertEquals(null, a,  "Undefined pre-clone()" );

			a = b.clone();
			assertFalse( a.equals(b), "Defined but separate instances post-clone()" );

			a.uuid = b.uuid;
			assertTrue( a.equals(b), "But identical properties" );

	}


	//@Test
	public void copy() {

			Object3D a = new Object3D();
			Object3D b = new Object3D();
			Object3D child = new Object3D();
			Object3D childChild = new Object3D();

			a.name = "original";
			b.name = "to-be-copied";

			b.position().set( x, y, z );
			b.quaternion().set( x, y, z, w );
			b.scale().set( 2, 3, 4 );

			// bogus QUnit.test values
			b.matrix().set( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
			b.matrixWorld().set( 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 );

			b.matrixAutoUpdate(false);
			b.matrixWorldNeedsUpdate(true);

			b.layers.mask = 2;
			b.visible = false;

			b.castShadow = true;
			b.receiveShadow = true;

			b.frustumCulled = false;
			b.renderOrder = 1;

//			b.userData = new double[] { "foo" } = "bar";

			child.add( childChild );
			b.add( child );

			assertTrue( !a.equals(b), "Objects are not equal pre-copy()" );
			a.copy( b, true );

			// check they're all unique instances
			assertTrue(
				a.uuid != b.uuid &&
				a.children.get( 0 ).uuid != b.children.get( 0 ).uuid &&
				a.children.get( 0 ).children.get( 0 ).uuid != b.children.get( 0 ).children.get( 0 ).uuid,
				"UUIDs are all different"
			);

			// and now fix that
			a.uuid = b.uuid;
			a.children.get( 0 ).uuid = b.children.get( 0 ).uuid;
			a.children.get( 0 ).children.get( 0 ).uuid = b.children.get( 0 ).children.get( 0 ).uuid;

			assertTrue( a.equals(b), "Objects are equal post-copy()" );

	}

}
