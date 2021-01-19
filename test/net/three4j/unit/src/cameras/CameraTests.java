package net.three4j.unit.src.cameras;

import org.junit.jupiter.api.Test;

import net.three4j.cameras.Camera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import { Camera } from '../../../../src/cameras/Camera';
//import { Vector3 } from '../../../../src/math/Vector3';

public class CameraTests {

//		// INHERITANCE
//		QUnit.todo( "Extending", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		// INSTANCING
//		QUnit.todo( "Instancing", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		// PUBLIC STUFF
//		QUnit.todo( "isCamera", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "copy", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "getWorldDirection", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "updateMatrixWorld", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );

	@Test
	public void $clone() {

		Camera cam = new Camera();

		// fill the matrices with any nonsense values just to see if they get copied
		cam.matrixWorldInverse().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
		cam.projectionMatrix().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

		Camera clonedCam = cam.clone();

		assertTrue(cam.matrixWorldInverse().equals(clonedCam.matrixWorldInverse()), "matrixWorldInverse is equal");
		assertTrue(cam.projectionMatrix().equals(clonedCam.projectionMatrix()), "projectionMatrix is equal");

	}

}
