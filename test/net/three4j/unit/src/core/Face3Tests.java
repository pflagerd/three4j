package net.three4j.unit.src.core;

import org.junit.jupiter.api.Test;

import net.three4j.core.Face3;
import net.three4j.math.Color;
import net.three4j.math.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import { Face3 } from "../../../../src/core/Face3";
//import { Color } from "../../../../src/math/Color";
//import { Vector3 } from "../../../../src/math/Vector3";

public class Face3Tests {

//		@Test
//		public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
	@Test
	public void copy() {

		Face3 instance = new Face3(0, 1, 2, new Vector3(0, 1, 0), new Color(0.25, 0.5, 0.75), 2);
		Face3 copiedInstance = new Face3().copy(instance);

		checkCopy(copiedInstance);
		checkVertexAndColors(copiedInstance);

		instance = new Face3(0, 1, 2, new Vector3[] { new Vector3(0, 1, 0), new Vector3(1, 0, 1) }, new Color[] { new Color(0.25, 0.5, 0.75), new Color(1, 0, 0.4) }, 2);

		copiedInstance = new Face3().copy(instance);

		checkCopy(copiedInstance);
		checkVertexAndColorArrays(copiedInstance);

	}

	@Test
	public void $clone() {

		Face3 instance = new Face3(0, 1, 2, new Vector3(0, 1, 0), new Color(0.25, 0.5, 0.75), 2);
		Face3 copiedInstance = instance.clone();

		checkCopy(copiedInstance);
		checkVertexAndColors(copiedInstance);

	}

	void checkCopy(Face3 copiedInstance) {

		assertTrue(copiedInstance.a() == 0 && copiedInstance.b() == 1 && copiedInstance.c() == 2 && copiedInstance.materialIndex() == 2, "properties where copied");

	}

	void checkVertexAndColors(Face3 copiedInstance) {

		assertTrue(copiedInstance.normal().x() == 0 && copiedInstance.normal().y() == 1 && copiedInstance.normal().z() == 0 && copiedInstance.color().r() == 0.25 && copiedInstance.color().g() == 0.5 && copiedInstance.color().b() == 0.75, "properties where copied");

	}

	void checkVertexAndColorArrays(Face3 copiedInstance) {

		assertTrue(copiedInstance.vertexNormals()[0].x() == 0 && copiedInstance.vertexNormals()[0].y() == 1 && copiedInstance.vertexNormals()[0].z() == 0 && copiedInstance.vertexNormals()[1].x() == 1 && copiedInstance.vertexNormals()[1].y() == 0 && copiedInstance.vertexNormals()[1].z() == 1 && copiedInstance.vertexColors()[0].r() == 0.25 && copiedInstance.vertexColors()[0].g() == 0.5 && copiedInstance.vertexColors()[0].b() == 0.75 && copiedInstance.vertexColors()[1].r() == 1 && copiedInstance.vertexColors()[1].g() == 0 && copiedInstance.vertexColors()[1].b() == 0.4, "properties where copied");

	}

}