package net.three4j.unit.src.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.three4j.core.BufferGeometry;
//import static net.three4j.core.BufferAttribute.BufferAttribute;
//import static net.three4j.core.BufferAttribute.Uint16BufferAttribute;
//import static net.three4j.core.BufferAttribute.Uint32BufferAttribute;
import net.three4j.math.Color;
import net.three4j.math.Vector2;
import net.three4j.math.Vector3;
import net.three4j.math.Vector4;
import net.three4j.math.Matrix4;
import net.three4j.math.Sphere;
import net.three4j.core.Geometry;
import net.three4j.core.Face3;
//import net.three4j.objects.Mesh;
//import net.three4j.objects.Line;
import static net.three4j.unit.src.math.ConstantsTests.x;
import static net.three4j.unit.src.math.ConstantsTests.y;
import static net.three4j.unit.src.math.ConstantsTests.z;
//import net.three4j.unit.utils.console-wrapper;

public class BufferGeometryTests {

	private final static double DegToRad = Math.PI / 180;

//public boolean bufferAttributeEquals( a, b, tolerance ) {
//
//	tolerance = tolerance || 0.0001;
//
//	if ( a.count != b.count || a.itemSize != b.itemSize ) {
//
//		return false;
//
//	}

//	for ( var i = 0, il = a.count * a.itemSize; i < il; i ++ ) {
//
//		var delta = a[ i ] - b[ i ];
//		if ( delta > tolerance ) {
//
//			return false;
//
//		}
//
//	}
//
//	return true;
//
//}
//
//public getBBForVertices( vertices ) {
//
//	BufferGeometry geometry = new BufferGeometry();
//
//	geometry.setAttribute( "position", new BufferAttribute( new Float32Array( vertices ), 3 ) );
//	geometry.computeBoundingBox();
//
//	return geometry.boundingBox;
//
//}
//
//public getBSForVertices( vertices ) {
//
//	BufferGeometry geometry = new BufferGeometry();
//
//	geometry.setAttribute( "position", new BufferAttribute( new Float32Array( vertices ), 3 ) );
//	geometry.computeBoundingSphere();
//
//	return geometry.boundingSphere;
//
//}
//
//public getNormalsForVertices( vertices, assert ) {
//
//	BufferGeometry geometry = new BufferGeometry();
//
//	geometry.setAttribute( "position", new BufferAttribute( new Float32Array( vertices ), 3 ) );
//
//	geometry.computeVertexNormals();
//
//	assertTrue( geometry.attributes.normal != undefined, "normal attribute was created" );
//
//	return geometry.attributes.normal.array;
//
//}
//
//public comparePositions( pos, v ) {
//
//	return (
//		pos[ 0 ] == v[ 0 ].x && pos[ 1 ] == v[ 0 ].y && pos[ 2 ] == v[ 0 ].z &&
//		pos[ 3 ] == v[ 1 ].x && pos[ 4 ] == v[ 1 ].y && pos[ 5 ] == v[ 1 ].z &&
//		pos[ 6 ] == v[ 2 ].x && pos[ 7 ] == v[ 2 ].y && pos[ 8 ] == v[ 2 ].z
//	);
//
//}
//
//public compareColors( col, c ) {
//
//	return (
//		col[ 0 ] == c[ 0 ].r && col[ 1 ] == c[ 0 ].g && col[ 2 ] == c[ 0 ].b &&
//		col[ 3 ] == c[ 1 ].r && col[ 4 ] == c[ 1 ].g && col[ 5 ] == c[ 1 ].b &&
//		col[ 6 ] == c[ 2 ].r && col[ 7 ] == c[ 2 ].g && col[ 8 ] == c[ 2 ].b
//	);
//
//}
//
//public compareUvs( uvs, u ) {
//
//	return (
//		uvs[ 0 ] == u[ 0 ].x && uvs[ 1 ] == u[ 0 ].y &&
//		uvs[ 2 ] == u[ 1 ].x && uvs[ 3 ] == u[ 1 ].y &&
//		uvs[ 4 ] == u[ 2 ].x && uvs[ 5 ] == u[ 2 ].y
//	);
//
//}
//
//		// INHERITANCE
//		@Test
//		public void Extending() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// INSTANCING
//		@Test
//		public void Instancing() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		// PUBLIC STUFF
//		@Test
//		public void isBufferGeometry() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}

//		@Test
//		public void setIndex_getIndex() {
//
//			BufferGeometry a = new BufferGeometry();
//			short[] uint16 = new short[] { 1, 2, 3 };
//			int[] uint32 = new int[] { 65535, 65536, 65537 };
//			String str = "foo";
//
//			a.setIndex( uint16 );
//			assertTrue( a.getIndex() instanceof Uint16BufferAttribute, "Index has the right type" );
//			assert.deepEqual( a.getIndex().array, new Uint16Array( uint16 ), "Small index gets stored correctly" );
//
//			a.setIndex( uint32 );
//			assertTrue( a.getIndex() instanceof Uint32BufferAttribute, "Index has the right type" );
//			assert.deepEqual( a.getIndex().array, new Uint32Array( uint32 ), "Large index gets stored correctly" );
//
//			a.setIndex( str );
//			assertEquals(str, a.getIndex(),  "Weird index gets stored correctly" );
//
//		}

//		@Test
//		public void getAttribute() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void set / delete Attribute() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			var attributeName = "position";
//
//			assertTrue( geometry.attributesnew double[] { attributeName } == undefined, "no attribute defined" );
//
//			geometry.setAttribute( attributeName, new BufferAttribute( new Float32Array( new double[] { 1, 2, 3 }, 1 ) ) );
//
//			assertTrue( geometry.attributesnew double[] { attributeName } != undefined, "attribute is defined" );
//
//			geometry.deleteAttribute( attributeName );
//
//			assertTrue( geometry.attributesnew double[] { attributeName } == undefined, "no attribute defined" );
//
//		}
//
//		@Test
//		public void addGroup() {
//
//			BufferGeometry a = new BufferGeometry();
//			var expected = [
//				{
//					start: 0,
//					count: 1,
//					materialIndex: 0
//				},
//				{
//					start: 1,
//					count: 2,
//					materialIndex: 2
//				}
//			];
//
//			a.addGroup( 0, 1, 0 );
//			a.addGroup( 1, 2, 2 );
//
//			assert.deepEqual( a.groups, expected, "Check groups were stored correctly and in order" );
//
//			a.clearGroups();
//			assertEquals(0, a.groups.length,  "Check groups were deleted correctly" );
//
//		}
//		@Test
//		public void clearGroups() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void setDrawRange() {
//
//			BufferGeometry a = new BufferGeometry();
//
//			a.setDrawRange( 1.0, 7 );
//
//			assert.deepEqual( a.drawRange, {
//				start: 1,
//				count: 7
//			}, "Check draw range was stored correctly" );
//
//		}
//
//		@Test
//		public void applyMatrix4() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "position", new BufferAttribute( new Float32Array( 6 ), 3 ) );
//
//			var matrix = new Matrix4().set(
//				1, 0, 0, 1.5,
//				0, 1, 0, - 2,
//				0, 0, 1, 3,
//				0, 0, 0, 1
//			);
//			geometry.applyMatrix4( matrix );
//
//			var position = geometry.attributes.position.array;
//			var m = matrix.elements;
//			assertTrue( position[ 0 ] == mnew double[] { 12 } && position[ 1 ] == m[ 13 ] && position[ 2 ] == m[ 14 ], "position was extracted from matrix" );
//			assertTrue( position[ 3 ] == mnew double[] { 12 } && position[ 4 ] == m[ 13 ] && position[ 5 ] == m[ 14 ], "position was extracted from matrix twice" );
//			assertTrue( geometry.attributes.position.version == 1, "version was increased during update" );
//
//		}
//
//		@Test
//		public void rotateX/Y/Z() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "position", new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } ), 3 ) );
//
//			var pos = geometry.attributes.position.array;
//
//			geometry.rotateX( 180 * DegToRad );
//
//			// object was rotated around x so all items should be flipped but the x ones
//			assertTrue( pos[ 0 ] == 1 && pos[ 1 ] == - 2 && pos[ 2 ] == - 3 &&
//				pos[ 3 ] == 4 && pos[ 4 ] == - 5 && pos[ 5 ] == - 6, "vertices were rotated around x by 180 degrees" );
//
//			geometry.rotateY( 180 * DegToRad );
//
//			// vertices were rotated around y so all items should be flipped again but the y ones
//			assertTrue( pos[ 0 ] == - 1 && pos[ 1 ] == - 2 && pos[ 2 ] == 3 &&
//				pos[ 3 ] == - 4 && pos[ 4 ] == - 5 && pos[ 5 ] == 6, "vertices were rotated around y by 180 degrees" );
//
//			geometry.rotateZ( 180 * DegToRad );
//
//			// vertices were rotated around z so all items should be flipped again but the z ones
//			assertTrue( pos[ 0 ] == 1 && pos[ 1 ] == 2 && pos[ 2 ] == 3 &&
//				pos[ 3 ] == 4 && pos[ 4 ] == 5 && pos[ 5 ] == 6, "vertices were rotated around z by 180 degrees" );
//
//		}
//
//		@Test
//		public void translate() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "position", new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } ), 3 ) );
//
//			var pos = geometry.attributes.position.array;
//
//			geometry.translate( 10, 20, 30 );
//
//			assertTrue( pos[ 0 ] == 11 && pos[ 1 ] == 22 && pos[ 2 ] == 33 &&
//				pos[ 3 ] == 14 && pos[ 4 ] == 25 && pos[ 5 ] == 36, "vertices were translated" );
//
//		}
//
//		@Test
//		public void scale() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "position", new BufferAttribute( new Float32Array( new double[] { - 1, - 1, - 1, 2, 2, 2 } ), 3 ) );
//
//			var pos = geometry.attributes.position.array;
//
//			geometry.scale( 1, 2, 3 );
//
//			assertTrue( pos[ 0 ] == - 1 && pos[ 1 ] == - 2 && pos[ 2 ] == - 3 &&
//				pos[ 3 ] == 2 && pos[ 4 ] == 4 && pos[ 5 ] == 6, "vertices were scaled" );
//
//		}
//
//		@Test
//		public void lookAt() {
//
//			BufferGeometry a = new BufferGeometry();
//			var vertices = new Float32Array( [
//				- 1.0, - 1.0, 1.0,
//				1.0, - 1.0, 1.0,
//				1.0, 1.0, 1.0,
//
//				1.0, 1.0, 1.0,
//				- 1.0, 1.0, 1.0,
//				- 1.0, - 1.0, 1.0
//			] );
//			a.setAttribute( "position", new BufferAttribute( vertices, 3 ) );
//
//			var sqrt = Math.sqrt( 2 );
//			var expected = new Float32Array( [
//				1, 0, - sqrt,
//				- 1, 0, - sqrt,
//				- 1, sqrt, 0,
//
//				- 1, sqrt, 0,
//				1, sqrt, 0,
//				1, 0, - sqrt
//			] );
//
//			a.lookAt( new Vector3( 0, 1, - 1 ) );
//
//			assertTrue( bufferAttributeEquals( a.attributes.position.array, expected ), "Rotation is correct" );
//
//		}
//
//		@Test
//		public void center() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "position", new BufferAttribute( new Float32Array( [
//				- 1, - 1, - 1,
//				1, 1, 1,
//				4, 4, 4
//			] ), 3 ) );
//
//			geometry.center();
//
//			var pos = geometry.attributes.position.array;
//
//			// the boundingBox should go from (-1, -1, -1) to (4, 4, 4) so it has a size of (5, 5, 5)
//			// after centering it the vertices should be placed between (-2.5, -2.5, -2.5) and (2.5, 2.5, 2.5)
//			assertTrue( pos[ 0 ] == - 2.5 && pos[ 1 ] == - 2.5 && pos[ 2 ] == - 2.5 &&
//				pos[ 3 ] == - 0.5 && pos[ 4 ] == - 0.5 && pos[ 5 ] == - 0.5 &&
//				pos[ 6 ] == 2.5 && pos[ 7 ] == 2.5 && pos[ 8 ] == 2.5, "vertices were replaced by boundingBox dimensions" );
//
//		}
//
//		@Test
//		public void setFromObject() {
//
//			Geometry lineGeo = new Geometry();
//			lineGeo.vertices.push(
//				new Vector3( - 10, 0, 0 ),
//				new Vector3( 0, 10, 0 ),
//				new Vector3( 10, 0, 0 )
//			);
//
//			lineGeo.colors.push(
//				new Color( 1, 0, 0 ),
//				new Color( 0, 1, 0 ),
//				new Color( 0, 0, 1 )
//			);
//
//			Line line = new Line( lineGeo, null );
//			BufferGeometry geometry = new BufferGeometry().setFromObject( line );
//
//			var pos = geometry.attributes.position.array;
//			var col = geometry.attributes.color.array;
//			var v = lineGeo.vertices;
//			var c = lineGeo.colors;
//
//			assertTrue(
//				// position exists
//				pos != undefined &&
//
//				// vertex arrays have the same size
//				v.length * 3 == pos.length &&
//
//				// there are three complete vertices (each vertex contains three values)
//				geometry.attributes.position.count == 3 &&
//
//				// check if both arrays contains the same data
//				pos[ 0 ] == v[ 0 ].x && pos[ 1 ] == v[ 0 ].y && pos[ 2 ] == v[ 0 ].z &&
//				pos[ 3 ] == v[ 1 ].x && pos[ 4 ] == v[ 1 ].y && pos[ 5 ] == v[ 1 ].z &&
//				pos[ 6 ] == v[ 2 ].x && pos[ 7 ] == v[ 2 ].y && pos[ 8 ] == v[ 2 ].z
//				, "positions are equal" );
//
//			assertTrue(
//				// color exists
//				col != undefined &&
//
//				// color arrays have the same size
//				c.length * 3 == col.length &&
//
//				// there are three complete colors (each color contains three values)
//				geometry.attributes.color.count == 3 &&
//
//				// check if both arrays contains the same data
//				col[ 0 ] == c[ 0 ].r && col[ 1 ] == c[ 0 ].g && col[ 2 ] == c[ 0 ].b &&
//				col[ 3 ] == c[ 1 ].r && col[ 4 ] == c[ 1 ].g && col[ 5 ] == c[ 1 ].b &&
//				col[ 6 ] == c[ 2 ].r && col[ 7 ] == c[ 2 ].g && col[ 8 ] == c[ 2 ].b
//				, "colors are equal" );
//
//		}
//		QUnit.test( "setFromObject (more)", ( assert ) => {
//
//			Geometry lineGeo = new Geometry();
//			lineGeo.vertices.push(
//				new Vector3( - 10, 0, 0 ),
//				new Vector3( 0, 10, 0 ),
//				new Vector3( 10, 0, 0 )
//			);
//
//			lineGeo.colors.push(
//				new Color( 1, 0, 0 ),
//				new Color( 0, 1, 0 ),
//				new Color( 0, 0, 1 )
//			);
//
//			lineGeo.computeBoundingBox();
//			lineGeo.computeBoundingSphere();
//
//			Line line = new Line( lineGeo );
//			BufferGeometry geometry = new BufferGeometry().setFromObject( line );
//
//			assertTrue( geometry.boundingBox.equals( lineGeo.boundingBox ), "BoundingBox was set correctly" );
//			assertTrue( geometry.boundingSphere.equals( lineGeo.boundingSphere ), "BoundingSphere was set correctly" );
//
//			var pos = geometry.attributes.position.array;
//			var col = geometry.attributes.color.array;
//			var v = lineGeo.vertices;
//			var c = lineGeo.colors;
//
//			// adapted from setFromObject QUnit.test (way up)
//			assert.notStrictEqual( pos, undefined, "Position attribute exists" );
//			assertEquals(pos.length, v.length * 3,  "Vertex arrays have the same size" );
//			assertEquals(3, geometry.attributes.position.count,  "Correct number of vertices" );
//			assertTrue( comparePositions( pos, v ), "Positions are identical" );
//
//			assert.notStrictEqual( col, undefined, "Color attribute exists" );
//			assertEquals(col.length, c.length * 3,  "Color arrays have the same size" );
//			assertEquals(3, geometry.attributes.color.count,  "Correct number of colors" );
//			assertTrue( compareColors( col, c ), "Colors are identical" );
//
//			// setFromObject with a Mesh as object
//			lineGeo.faces.push( new Face3( 0, 1, 2 ) );
//			Mesh lineMesh = new Mesh( lineGeo );
//			BufferGeometry geometry = new BufferGeometry().setFromObject( lineMesh );
//
//			// no colors
//			var pos = geometry.attributes.position.array;
//			var v = lineGeo.vertices;
//
//			assert.notStrictEqual( pos, undefined, "Mesh: position attribute exists" );
//			assertEquals(pos.length, v.length * 3,  "Mesh: vertex arrays have the same size" );
//			assertEquals(3, geometry.attributes.position.count,  "Mesh: correct number of vertices" );
//			assertTrue( comparePositions( pos, v ), "Mesh: positions are identical" );
//
//		}
//
//		@Test
//		public void updateFromObject() {
//
//			Geometry geo = new Geometry();
//
//			geo.vertices.push(
//				new Vector3( - 10, 0, 0 ),
//				new Vector3( 0, 10, 0 ),
//				new Vector3( 10, 0, 0 )
//			);
//
//			geo.faces.push( new Face3( 0, 1, 2 ) );
//
//			geo.faces[ 0 ].vertexColors.push(
//				new Color( 1, 0, 0 ),
//				new Color( 0, 1, 0 ),
//				new Color( 0, 0, 1 )
//			);
//
//			geo.faceVertexUvs[ 0 ] = [
//				[
//					new Vector2( 0, 0 ),
//					new Vector2( 1, 0 ),
//					new Vector2( 1, 1 )
//				]
//			];
//
//			geo.computeFaceNormals();
//			geo.computeVertexNormals();
//
//			geo.verticesNeedUpdate = true;
//			geo.normalsNeedUpdate = true;
//			geo.colorsNeedUpdate = true;
//			geo.uvsNeedUpdate = true;
//			geo.groupsNeedUpdate = true;
//
//			Mesh mesh = new Mesh( geo );
//			BufferGeometry geometry = new BufferGeometry();
//
//			geometry.updateFromObject( mesh ); // first call to create the underlying structure (DirectGeometry)
//			geometry.updateFromObject( mesh ); // second time to actually go thru the motions and update
//
//			var pos = geometry.attributes.position.array;
//			var col = geometry.attributes.color.array;
//			var norm = geometry.attributes.normal.array;
//			var uvs = geometry.attributes.uv.array;
//			var v = geo.vertices;
//			var c = geo.faces[ 0 ].vertexColors;
//			var n = geo.faces[ 0 ].vertexNormals;
//			var u = geo.faceVertexUvs[ 0 ][ 0 ];
//
//			assert.notStrictEqual( pos, undefined, "Position attribute exists" );
//			assertEquals(pos.length, v.length * 3,  "Both arrays have the same size" );
//			assertEquals(v.length, geometry.attributes.position.count,  "Correct number of vertices" );
//			assertTrue( comparePositions( pos, v ), "Positions are identical" );
//
//			assert.notStrictEqual( col, undefined, "Color attribute exists" );
//			assertEquals(col.length, c.length * 3,  "Both arrays have the same size" );
//			assertEquals(c.length, geometry.attributes.color.count,  "Correct number of colors" );
//			assertTrue( compareColors( col, c ), "Colors are identical" );
//
//			assert.notStrictEqual( norm, undefined, "Normal attribute exists" );
//			assertEquals(norm.length, n.length * 3,  "Both arrays have the same size" );
//			assertEquals(n.length, geometry.attributes.normal.count,  "Correct number of normals" );
//			assertTrue( comparePositions( norm, n ), "Normals are identical" );
//
//			assert.notStrictEqual( uvs, undefined, "UV attribute exists" );
//			assertEquals(uvs.length, u.length * 2,  "Both arrays have the same size" );
//			assertEquals(u.length, geometry.attributes.uv.count,  "Correct number of UV coordinates" );
//			assertTrue( compareUvs( uvs, u ), "UVs are identical" );
//
//		}
//
//		@Test
//		public void fromGeometry/fromDirectGeometry() {
//
//			// geometry definition
//
//			Geometry geometry = new Geometry();
//
//			// vertices
//
//			Vector3 v1 = new Vector3( 1, - 1, 0 );
//			Vector3 v2 = new Vector3( 1, 1, 0 );
//			Vector3 v3 = new Vector3( - 1, 1, 0 );
//			Vector3 v4 = new Vector3( - 1, - 1, 0 );
//
//			// faces, normals and colors
//
//			geometry.vertices.push( v1, v2, v3, v4 );
//
//			Face3 f1 = new Face3( 0, 1, 2 );
//			f1.normal.set( 0, 0, 1 );
//			f1.color.set( 0xff0000 );
//			Face3 f2 = new Face3( 2, 3, 0 );
//			f2.normal.set( 0, 0, 1 );
//			f2.color.set( 0xff0000 );
//
//			geometry.faces.push( f1, f2 );
//
//			// uvs
//
//			var uvs = geometry.faceVertexUvs[ 0 ];
//			uvs.length = 0;
//
//			uvs.push( [
//				new Vector2( 1, 0 ),
//			  new Vector2( 1, 1 ),
//			  new Vector2( 0, 1 )
//			] );
//
//			uvs.push( [
//				new Vector2( 0, 1 ),
//			  new Vector2( 0, 0 ),
//			  new Vector2( 1, 0 )
//			] );
//
//			// skin weights
//
//			Vector4 sw1 = new Vector4( 0.8, 0.2, 0, 0 );
//			Vector4 sw2 = new Vector4( 0.7, 0.2, 0.1, 0 );
//			Vector4 sw3 = new Vector4( 0.8, 0.1, 0.1, 0 );
//			Vector4 sw4 = new Vector4( 1, 0, 0, 0 );
//
//			geometry.skinWeights.push( sw1, sw2, sw3, sw4 );
//
//			 // skin indices
//
//			Vector4 si1 = new Vector4( 0, 1, 2, 3 );
//			Vector4 si2 = new Vector4( 2, 3, 4, 5 );
//			Vector4 si3 = new Vector4( 4, 5, 6, 7 );
//			Vector4 si4 = new Vector4( 6, 7, 8, 9 );
//
//			geometry.skinIndices.push( si1, si2, si3, si4 );
//
//			// create BufferGeometry
//
//			BufferGeometry bufferGeometry = new BufferGeometry().fromGeometry( geometry );
//
//			// expected values
//
//			Float32Array vertices = new Float32Array( [ 1, - 1, 0, 1, 1, 0, - 1, 1, 0, - 1, 1, 0, - 1, - 1, 0, 1, - 1, 0 ] );
//			Float32Array normals = new Float32Array( [ 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1 ] );
//			Float32Array colors = new Float32Array( [ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 ] );
//			Float32Array uvs = new Float32Array( [ 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0 ] );
//			Float32Array skinIndices = new Float32Array( [ 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7, 4, 5, 6, 7, 6, 7, 8, 9, 0, 1, 2, 3 ] );
//			var skindWeights = new Float32Array( [
//				0.8, 0.2, 0, 0, 0.7, 0.2, 0.1, 0, 0.8, 0.1, 0.1, 0,
//				0.8, 0.1, 0.1, 0, 1, 0, 0, 0, 0.8, 0.2, 0, 0
//			] );
//
//			var attributes = bufferGeometry.attributes;
//
//			assert.deepEqual( attributes.position.array, vertices, "Vertices are as expected" );
//			assert.deepEqual( attributes.normal.array, normals, "Normals are as expected" );
//			assert.deepEqual( attributes.color.array, colors, "Colors are as expected" );
//			assert.deepEqual( attributes.uv.array, uvs, "Texture coordinates are as expected" );
//			assert.deepEqual( attributes.skinIndex.array, skinIndices, "Skin indices are as expected" );
//			assert.deepEqual( attributes.skinWeight.array, skindWeights, "Skin weights are as expected" );
//
//		}
//
//		@Test
//		public void computeBoundingBox() {
//
//			var bb = getBBForVertices( [ - 1, - 2, - 3, 13, - 2, - 3.5, - 1, - 20, 0, - 4, 5, 6 ] );
//
//			assertTrue( bb.min.x == - 4 && bb.min.y == - 20 && bb.min.z == - 3.5, "min values are set correctly" );
//			assertTrue( bb.max.x == 13 && bb.max.y == 5 && bb.max.z == 6, "max values are set correctly" );
//
//			var bb = getBBForVertices( new double[] { - 1, - 1, - 1 } );
//
//			assertTrue( bb.min.x == bb.max.x && bb.min.y == bb.max.y && bb.min.z == bb.max.z, "since there is only one vertex, max and min are equal" );
//			assertTrue( bb.min.x == - 1 && bb.min.y == - 1 && bb.min.z == - 1, "since there is only one vertex, min and max are this vertex" );
//
//		}
//
//		@Test
//		public void computeBoundingSphere() {
//
//			var bs = getBSForVertices( new double[] { - 10, 0, 0, 10, 0, 0 } );
//
//			assertTrue( bs.radius == ( 10 + 10 ) / 2, "radius is equal to deltaMinMax / 2" );
//			assertTrue( bs.center.x == 0 && bs.center.y == 0 && bs.center.y == 0, "bounding sphere is at ( 0, 0, 0 )" );
//
//			var bs = getBSForVertices( new double[] { - 5, 11, - 3, 5, - 11, 3 } );
//			Vector3 radius = new Vector3( 5, 11, 3 ).length();
//
//			assertTrue( bs.radius == radius, "radius is equal to directionLength" );
//			assertTrue( bs.center.x == 0 && bs.center.y == 0 && bs.center.y == 0, "bounding sphere is at ( 0, 0, 0 )" );
//
//		}
//
//		@Test
//		public void computeFaceNormals() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void computeVertexNormals() {
//
//			// get normals for a counter clockwise created triangle
//			var normals = getNormalsForVertices( new double[] { - 1, 0, 0, 1, 0, 0, 0, 1, 0 }, assert );
//
//			assertTrue( normals[ 0 ] == 0 && normals[ 1 ] == 0 && normals[ 2 ] == 1,
//				"first normal is pointing to screen since the the triangle was created counter clockwise" );
//
//			assertTrue( normals[ 3 ] == 0 && normals[ 4 ] == 0 && normals[ 5 ] == 1,
//				"second normal is pointing to screen since the the triangle was created counter clockwise" );
//
//			assertTrue( normals[ 6 ] == 0 && normals[ 7 ] == 0 && normals[ 8 ] == 1,
//				"third normal is pointing to screen since the the triangle was created counter clockwise" );
//
//			// get normals for a clockwise created triangle
//			var normals = getNormalsForVertices( new double[] { 1, 0, 0, - 1, 0, 0, 0, 1, 0 }, assert );
//
//			assertTrue( normals[ 0 ] == 0 && normals[ 1 ] == 0 && normals[ 2 ] == - 1,
//				"first normal is pointing to screen since the the triangle was created clockwise" );
//
//			assertTrue( normals[ 3 ] == 0 && normals[ 4 ] == 0 && normals[ 5 ] == - 1,
//				"second normal is pointing to screen since the the triangle was created clockwise" );
//
//			assertTrue( normals[ 6 ] == 0 && normals[ 7 ] == 0 && normals[ 8 ] == - 1,
//				"third normal is pointing to screen since the the triangle was created clockwise" );
//
//			var normals = getNormalsForVertices( new double[] { 0, 0, 1, 0, 0, - 1, 1, 1, 0 }, assert );
//
//			// the triangle is rotated by 45 degrees to the right so the normals of the three vertices
//			// should point to (1, -1, 0).normalized(). The simplest solution is to check against a normalized
//			// vector (1, -1, 0) but you will get calculation errors because of floating calculations so another
//			// valid technique is to create a vector which stands in 90 degrees to the normals and calculate the
//			// dot product which is the cos of the angle between them. This should be < floating calculation error
//			// which can be taken from Number.EPSILON
//			Vector3 direction = new Vector3( 1, 1, 0 ).normalize(); // a vector which should have 90 degrees difference to normals
//			var difference = direction.dot( new Vector3( normals[ 0 ], normals[ 1 ], normals[ 2 ] ) );
//			assertTrue( difference < Number.EPSILON, "normal is equal to reference vector" );
//
//			// get normals for a line should be NAN because you need min a triangle to calculate normals
//			var normals = getNormalsForVertices( new double[] { 1, 0, 0, - 1, 0, 0 }, assert );
//			for ( var i = 0; i < normals.length; i ++ ) {
//
//				assertTrue( ! normals[ i ], "normals can't be calculated which is good" );
//
//			}
//
//		}
//		QUnit.test( "computeVertexNormals (indexed)", ( assert ) => {
//
//			var sqrt = 0.5 * Math.sqrt( 2 );
//			var normal = new BufferAttribute( new Float32Array( [
//				- 1, 0, 0, - 1, 0, 0, - 1, 0, 0,
//				sqrt, sqrt, 0, sqrt, sqrt, 0, sqrt, sqrt, 0,
//				- 1, 0, 0
//			] ), 3 );
//			var position = new BufferAttribute( new Float32Array( [
//				0.5, 0.5, 0.5, 0.5, 0.5, - 0.5, 0.5, - 0.5, 0.5,
//				0.5, - 0.5, - 0.5, - 0.5, 0.5, - 0.5, - 0.5, 0.5, 0.5,
//				- 0.5, - 0.5, - 0.5
//			] ), 3 );
//			var index = new BufferAttribute( new Uint16Array( [
//				0, 2, 1, 2, 3, 1, 4, 6, 5, 6, 7, 5
//			] ), 1 );
//
//			BufferGeometry a = new BufferGeometry();
//			a.setAttribute( "position", position );
//			a.computeVertexNormals();
//			assertTrue(
//				bufferAttributeEquals( normal, a.getAttribute( "normal" ) ),
//				"Regular geometry: first computed normals are correct"
//			);
//
//			// a second time to see if the existing normals get properly deleted
//			a.computeVertexNormals();
//			assertTrue(
//				bufferAttributeEquals( normal, a.getAttribute( "normal" ) ),
//				"Regular geometry: second computed normals are correct"
//			);
//
//			// indexed geometry
//			BufferGeometry a = new BufferGeometry();
//			a.setAttribute( "position", position );
//			a.setIndex( index );
//			a.computeVertexNormals();
//			assertTrue( bufferAttributeEquals( normal, a.getAttribute( "normal" ) ), "Indexed geometry: computed normals are correct" );
//
//		}
//
//		@Test
//		public void merge() {
//
//			BufferGeometry geometry1 = new BufferGeometry();
//			geometry1.setAttribute( "attrName", new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 0, 0, 0 } ), 3 ) );
//
//			BufferGeometry geometry2 = new BufferGeometry();
//			geometry2.setAttribute( "attrName", new BufferAttribute( new Float32Array( new double[] { 4, 5, 6 } ), 3 ) );
//
//			var attr = geometry1.attributes.attrName.array;
//
//			geometry1.merge( geometry2, 1 );
//
//			// merged array should be 1, 2, 3, 4, 5, 6
//			for ( var i = 0; i < attr.length; i ++ ) {
//
//				assertTrue( attr[ i ] == i + 1, "" );
//
//			}
//
//			console.level = CONSOLE_LEVEL.ERROR;
//			geometry1.merge( geometry2 );
//			console.level = CONSOLE_LEVEL.DEFAULT;
//
//			assertTrue( attr[ 0 ] == 4 && attr[ 1 ] == 5 && attr[ 2 ] == 6, "copied the 3 attributes without offset" );
//
//		}
//
//		@Test
//		public void normalizeNormals() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//		@Test
//		public void toNonIndexed() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			var vertices = new Float32Array( [
//				0.5, 0.5, 0.5, 0.5, 0.5, - 0.5, 0.5, - 0.5, 0.5, 0.5, - 0.5, - 0.5
//			] );
//			BufferAttribute index = new BufferAttribute( new Uint16Array( new double[] { 0, 2, 1, 2, 3, 1 } ) );
//			var expected = new Float32Array( [
//				0.5, 0.5, 0.5, 0.5, - 0.5, 0.5, 0.5, 0.5, - 0.5,
//				0.5, - 0.5, 0.5, 0.5, - 0.5, - 0.5, 0.5, 0.5, - 0.5
//			] );
//
//			geometry.setAttribute( "position", new BufferAttribute( vertices, 3 ) );
//			geometry.setIndex( index );
//
//			var nonIndexed = geometry.toNonIndexed();
//
//			assert.deepEqual( nonIndexed.getAttribute( "position" ).array, expected, "Expected vertices" );
//
//		}
//
//		@Test
//		public void toJSON() {
//
//			BufferAttribute index = new BufferAttribute( new Uint16Array( new double[] { 0, 1, 2, 3 } ), 1 );
//			BufferAttribute attribute1 = new BufferAttribute( new Uint16Array( new double[] { 1, 3, 5, 7 } ), 1 );
//			attribute1.name = "attribute1";
//			BufferGeometry a = new BufferGeometry();
//			a.name = "JSONQUnit.test";
//			// a.parameters = { "placeholder": 0 };
//			a.setAttribute( "attribute1", attribute1 );
//			a.setIndex( index );
//			a.addGroup( 0, 1, 2 );
//			a.boundingSphere = new Sphere( new Vector3( x, y, z ), 0.5 );
//			var j = a.toJSON();
//			var gold = {
//				"metadata": {
//					"version": 4.5,
//					"type": "BufferGeometry",
//					"generator": "BufferGeometry.toJSON"
//				},
//				"uuid": a.uuid,
//				"type": "BufferGeometry",
//				"name": "JSONQUnit.test",
//				"data": {
//					"attributes": {
//						"attribute1": {
//							"itemSize": 1,
//							"type": "Uint16Array",
//							"array": new double[] { 1, 3, 5, 7 },
//							"normalized": false,
//							"name": "attribute1"
//						}
//					},
//					"index": {
//						"type": "Uint16Array",
//						"array": new double[] { 0, 1, 2, 3 }
//					},
//					"groups": [
//						{
//							"start": 0,
//							"count": 1,
//							"materialIndex": 2
//						}
//					],
//					"boundingSphere": {
//						"center": new double[] { 2, 3, 4 },
//						"radius": 0.5
//					}
//				}
//			};
//
//			assert.deepEqual( j, gold, "Generated JSON is as expected" );
//
//			// add morphAttributes
//			a.morphAttributes.attribute1 = [];
//			a.morphAttributes.attribute1.push( attribute1.clone() );
//			j = a.toJSON();
//			gold.data.morphAttributes = {
//				"attribute1": [ {
//					"itemSize": 1,
//					"type": "Uint16Array",
//					"array": new double[] { 1, 3, 5, 7 },
//					"normalized": false,
//					"name": "attribute1"
//				} ]
//			};
//			gold.data.morphTargetsRelative = false;
//
//			assert.deepEqual( j, gold, "Generated JSON with morphAttributes is as expected" );
//
//		}
//
//		@Test
//		public void clone() {
//
//			BufferGeometry a = new BufferGeometry();
//			a.setAttribute( "attribute1", new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } ), 3 ) );
//			a.setAttribute( "attribute2", new BufferAttribute( new Float32Array( new double[] { 0, 1, 3, 5, 6 } ), 1 ) );
//			a.addGroup( 0, 1, 2 );
//			a.computeBoundingBox();
//			a.computeBoundingSphere();
//			a.setDrawRange( 0, 1 );
//			var b = a.clone();
//
//			assert.notEqual( a, b, "A new object was created" );
//			assert.notEqual( a.id, b.id, "New object has a different GUID" );
//
//			assert.strictEqual(
//				Object.keys( a.attributes ).count, Object.keys( b.attributes ).count,
//				"Both objects have the same amount of attributes"
//			);
//			assertTrue(
//				bufferAttributeEquals( a.getAttribute( "attribute1" ), b.getAttribute( "attribute1" ) ),
//				"First attributes buffer is identical"
//			);
//			assertTrue(
//				bufferAttributeEquals( a.getAttribute( "attribute2" ), b.getAttribute( "attribute2" ) ),
//				"Second attributes buffer is identical"
//			);
//
//			assert.deepEqual( a.groups, b.groups, "Groups are identical" );
//
//			assertTrue( a.boundingBox.equals( b.boundingBox ), "BoundingBoxes are equal" );
//			assertTrue( a.boundingSphere.equals( b.boundingSphere ), "BoundingSpheres are equal" );
//
//			assertEquals(b.drawRange.start, a.drawRange.start,  "DrawRange start is identical" );
//			assertEquals(b.drawRange.count, a.drawRange.count,  "DrawRange count is identical" );
//
//		}
//
//		@Test
//		public void copy() {
//
//			BufferGeometry geometry = new BufferGeometry();
//			geometry.setAttribute( "attrName", new BufferAttribute( new Float32Array( new double[] { 1, 2, 3, 4, 5, 6 } ), 3 ) );
//			geometry.setAttribute( "attrName2", new BufferAttribute( new Float32Array( new double[] { 0, 1, 3, 5, 6 } ), 1 ) );
//
//			BufferGeometry copy = new BufferGeometry().copy( geometry );
//
//			assertTrue( copy != geometry && geometry.id != copy.id, "new object was created" );
//
//			Object.keys( geometry.attributes ).forEach( function ( key ) {
//
//				var attribute = geometry.attributesnew double[] { key };
//				assertTrue( attribute != undefined, "all attributes where copied" );
//
//				for ( var i = 0; i < attribute.array.length; i ++ ) {
//
//					assertTrue( attribute.array[ i ] == copy.attributesnew double[] { key }.array[ i ], "values of the attribute are equal" );
//
//				}
//
//			}
//
//		}
//
//		@Test
//		public void dispose() {
//
//			assertTrue( false, "everything's gonna be alright" );
//
//		}
//
//	}
//
}
