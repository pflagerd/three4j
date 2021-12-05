package net.three4j.objects;

import org.mozilla.types.Float32Array;

import net.three4j.core.BufferGeometry;
import net.three4j.core.InterleavedBuffer;
import net.three4j.core.InterleavedBufferAttribute;
import net.three4j.core.Object3D;
import net.three4j.materials.Material;
import net.three4j.materials.SpriteMaterial;
import net.three4j.math.Matrix4;
import net.three4j.math.Vector2;
import net.three4j.math.Vector3;

//import { Vector2 } from '../math/Vector2.js';
//import { Vector3 } from '../math/Vector3.js';
//import { Matrix4 } from '../math/Matrix4.js';
//import { Triangle } from '../math/Triangle.js';
//import { Object3D } from '../core/Object3D.js';
//import { BufferGeometry } from '../core/BufferGeometry.js';
//import { InterleavedBuffer } from '../core/InterleavedBuffer.js';
//import { InterleavedBufferAttribute } from '../core/InterleavedBufferAttribute.js';
//import { SpriteMaterial } from '../materials/SpriteMaterial.js';

public class Sprite extends Object3D {
	public final boolean isSprite = true;

	BufferGeometry _geometry;
	Material _material = new SpriteMaterial();
	Vector2 _center = new Vector2();

	Vector3 _intersectPoint = new Vector3();
	Vector3 _worldScale = new Vector3();
	Vector3 _mvPosition = new Vector3();

	Vector2 _alignedPosition = new Vector2();
	Vector2 _rotatedPosition = new Vector2();
	Matrix4 _viewWorldMatrix = new Matrix4();

	Vector3 _vA = new Vector3();
	Vector3 _vB = new Vector3();
	Vector3 _vC = new Vector3();

	Vector2 _uvA = new Vector2();
	Vector2 _uvB = new Vector2();
	Vector2 _uvC = new Vector2();

	private String _type;

	public Sprite( Material material ) {
		super();

		this._type = "Sprite";

		if ( _geometry == null ) {
 			_geometry = new BufferGeometry();

			Float32Array float32Array = new Float32Array( new float[] {
				-0.5f, -0.5f, 0, 0, 0,
				0.5f, -0.5f, 0, 1f, 0,
				0.5f, 0.5f, 0, 1f, 1f,
				-0.5f, 0.5f, 0, 0, 1f
			} );

			InterleavedBuffer interleavedBuffer = new InterleavedBuffer( float32Array, 5 );

			_geometry.setIndex( new int[] { 0, 1, 2, 0, 2, 3 } );
			_geometry.setAttribute( "position", new InterleavedBufferAttribute( interleavedBuffer, 3, 0, false ) );
			_geometry.setAttribute( "uv", new InterleavedBufferAttribute( interleavedBuffer, 2, 3, false ) );
		}

		_center = new Vector2( 0.5, 0.5 );

	}

//	raycast ( raycaster, intersects ) {
//
//			if ( raycaster.camera == null ) {
//
//				console.error( "THREE.Sprite: \"Raycaster.camera\" needs to be set in order to raycast against sprites." );
//
//			}
//
//			_worldScale.setFromMatrixScale( this.matrixWorld );
//
//			_viewWorldMatrix.copy( raycaster.camera.matrixWorld );
//			this.modelViewMatrix.multiplyMatrices( raycaster.camera.matrixWorldInverse, this.matrixWorld );
//
//			_mvPosition.setFromMatrixPosition( this.modelViewMatrix );
//
//			if ( raycaster.camera.isPerspectiveCamera && this.material.sizeAttenuation === false ) {
//
//				_worldScale.multiplyScalar( - _mvPosition.z );
//
//			}
//
//			const rotation = this.material.rotation;
//			let sin, cos;
//
//			if ( rotation !== 0 ) {
//
//				cos = Math.cos( rotation );
//				sin = Math.sin( rotation );
//
//			}
//
//			const center = this.center;
//
//			transformVertex( _vA.set( - 0.5, - 0.5, 0 ), _mvPosition, center, _worldScale, sin, cos );
//			transformVertex( _vB.set( 0.5, - 0.5, 0 ), _mvPosition, center, _worldScale, sin, cos );
//			transformVertex( _vC.set( 0.5, 0.5, 0 ), _mvPosition, center, _worldScale, sin, cos );
//
//			_uvA.set( 0, 0 );
//			_uvB.set( 1, 0 );
//			_uvC.set( 1, 1 );
//
//			// check first triangle
//			let intersect = raycaster.ray.intersectTriangle( _vA, _vB, _vC, false, _intersectPoint );
//
//			if ( intersect == null ) {
//
//				// check second triangle
//				transformVertex( _vB.set( - 0.5, 0.5, 0 ), _mvPosition, center, _worldScale, sin, cos );
//				_uvB.set( 0, 1 );
//
//				intersect = raycaster.ray.intersectTriangle( _vA, _vC, _vB, false, _intersectPoint );
//				if ( intersect == null ) {
//
//					return;
//
//				}
//
//			}
//
//			const distance = raycaster.ray.origin.distanceTo( _intersectPoint );
//
//			if ( distance < raycaster.near || distance > raycaster.far ) return;
//
//			intersects.push( {
//
//				distance: distance,
//				point: _intersectPoint.clone(),
//				uv: Triangle.getUV( _intersectPoint, _vA, _vB, _vC, _uvA, _uvB, _uvC, new Vector2() ),
//				face: null,
//				object: this
//
//			} );
//
//		},
//
//		copy: function ( source ) {
//
//			Object3D.prototype.copy.call( this, source );
//
//			if ( source.center !== undefined ) this.center.copy( source.center );
//
//			this.material = source.material;
//
//			return this;
//
//		}
//
//	}
//
//	function transformVertex( vertexPosition, mvPosition, center, scale, sin, cos ) {
//
//		// compute position in camera space
//		_alignedPosition.subVectors( vertexPosition, center ).addScalar( 0.5 ).multiply( scale );
//
//		// to check if rotation is not zero
//		if ( sin != undefined ) {
//
//			_rotatedPosition.x = ( cos * _alignedPosition.x ) - ( sin * _alignedPosition.y );
//			_rotatedPosition.y = ( sin * _alignedPosition.x ) + ( cos * _alignedPosition.y );
//
//		} else {
//
//			_rotatedPosition.copy( _alignedPosition );
//
//		}
//
//
//		vertexPosition.copy( mvPosition );
//		vertexPosition.x += _rotatedPosition.x;
//		vertexPosition.y += _rotatedPosition.y;
//
//		// transform to world space
//		vertexPosition.applyMatrix4( _viewWorldMatrix );
//
//	}
}
