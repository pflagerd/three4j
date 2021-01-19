package net.three4j.unit.src.cameras;

import org.junit.jupiter.api.Test;

import net.three4j.cameras.PerspectiveCamera;
import net.three4j.math.Matrix4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerspectiveCameraTests {

	static boolean matrixEquals4(Matrix4 a, Matrix4 b) {
		return matrixEquals4(a, b, 0.0001);
	}

	// see e.g. math/Matrix4.js
	static boolean matrixEquals4(Matrix4 a, Matrix4 b, double tolerance) {

		if (a.elements.length != b.elements.length) {

			return false;

		}
		for (int i = 0, il = a.elements.length; i < il; i++) {

			double delta = a.elements[i] - b.elements[i];
			if (delta > tolerance) {

				return false;

			}

		}

		return true;

	};

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
//		QUnit.todo( "isPerspectiveCamera", ( assert ) => {
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
//		QUnit.todo( "setFocalLength", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "getFocalLength", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "getEffectiveFOV", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "getFilmWidth", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "getFilmHeight", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "setViewOffset", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
//		QUnit.todo( "clearViewOffset", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );

	@Test
	public void updateProjectionMatrix() {

		PerspectiveCamera cam = new PerspectiveCamera(75, 16. / 9, 0.1, 300.0);

		// updateProjectionMatrix is called in constructor
		Matrix4 m = cam.projectionMatrix();

		// perspective projection is given my the 4x4 Matrix
		// 2n/r-l 0 l+r/r-l 0
		// 0 2n/t-b t+b/t-b 0
		// 0 0 -(f+n/f-n) -(2fn/f-n)
		// 0 0 -1 0

		// this matrix was calculated by hand via glMatrix.perspective(75, 16 / 9, 0.1,
		// 300.0, pMatrix)
		// to get a reference matrix from plain WebGL
		Matrix4 reference = new Matrix4().set(0.7330642938613892, 0, 0, 0, 0, 1.3032253980636597, 0, 0, 0, 0, -1.000666856765747, -0.2000666856765747, 0, 0, -1, 0);

		// assert.ok( reference.equals(m) );
		assertTrue(matrixEquals4(reference, m, 0.000001));

	}

//		QUnit.todo( "toJSON", ( assert ) => {
//
//			assert.ok( false, "everything's gonna be alright" );
//
//		} );
//
	@Test
	public void $clone() {

		double near = 1, far = 3, aspect = 16 / 9, fov = 90;

		PerspectiveCamera cam = new PerspectiveCamera(fov, aspect, near, far);

		PerspectiveCamera clonedCam = cam.clone();

		assertTrue(cam.fov() == clonedCam.fov(), "fov is equal");
		assertTrue(cam.aspect() == clonedCam.aspect(), "aspect is equal");
		assertTrue(cam.near() == clonedCam.near(), "near is equal");
		assertTrue(cam.far() == clonedCam.far(), "far is equal");
		assertTrue(cam.zoom() == clonedCam.zoom(), "zoom is equal");
		assertTrue(cam.projectionMatrix().equals(clonedCam.projectionMatrix()), "projectionMatrix is equal");

	}

}
