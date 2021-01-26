package net.three4j.objects;

import net.three4j.math.Vector3;
import net.three4j.math.Vector2;
import net.three4j.math.Sphere;
import net.three4j.math.Ray;
import net.three4j.math.Matrix4;
import net.three4j.core.Object3D;
import net.three4j.math.Triangle;
import net.three4j.core.Face3;
import net.three4j.core.Geometry;
import net.three4j.constants;
import net.three4j.materials.Material;
import net.three4j.materials.MeshBasicMaterial;
import net.three4j.core.BufferGeometry;

public class Mesh extends Object3D {

	private final Matrix4 _inverseMatrix = new Matrix4();
	private final Ray _ray = new Ray();
	private final Sphere _sphere = new Sphere();

	private final Vector3 _vA = new Vector3();
	private final Vector3 _vB = new Vector3();
	private final Vector3 _vC = new Vector3();

	private final Vector3 _tempA = new Vector3();
	private final Vector3 _tempB = new Vector3();
	private final Vector3 _tempC = new Vector3();

	private final Vector3 _morphA = new Vector3();
	private final Vector3 _morphB = new Vector3();
	private final Vector3 _morphC = new Vector3();

	private final Vector2 _uvA = new Vector2();
	private final Vector2 _uvB = new Vector2();
	private final Vector2 _uvC = new Vector2();

	private final Vector3 _intersectionPoint = new Vector3();
	private final Vector3 _intersectionPointWorld = new Vector3();

//	private BufferGeometry _bufferGeometry;
//
//	public BufferGeometry bufferGeometry() {
//		return _bufferGeometry;
//	}
//
//	public Mesh geometry(BufferGeometry bufferGeometry) {
//		this._bufferGeometry = bufferGeometry;
//		return this;
//	}
//
	private Material _material = new Material();

	public Material material() {
		return _material;
	}

	public Mesh material(Material material) {
		this._material = material;
		return this;
	}

	public Mesh() {
	}

	public Mesh(Geometry geometry, Material material) {
		super();

		this._geometry = geometry;
		this._material = material;

		this.updateMorphTargets();
	}

//	public Mesh copy  ( Mesh source ) {
//
//		super.copy( source );
//
//		if ( source.morphTargetInfluences !== undefined ) {
//
//			this.morphTargetInfluences = source.morphTargetInfluences.slice();
//
//		}
//
//		if ( source.morphTargetDictionary !== undefined ) {
//
//			this.morphTargetDictionary = Object.assign( {}, source.morphTargetDictionary );
//
//		}
//
//		this._material = source._material;
//		this._geometry = source._geometry;
//
//		return this;
//
//	}

	public void updateMorphTargets  () {
//
//		public final geometry = this.geometry;
//
//		if ( geometry.isBufferGeometry ) {
//
//			const morphAttributes = geometry.morphAttributes;
//			const keys = Object.keys( morphAttributes );
//
//			if ( keys.length > 0 ) {
//
//				const morphAttribute = morphAttributes[ keys[ 0 ] ];
//
//				if ( morphAttribute !== undefined ) {
//
//					this.morphTargetInfluences = [];
//					this.morphTargetDictionary = {};
//
//					for ( let m = 0, ml = morphAttribute.length; m < ml; m ++ ) {
//
//						const name = morphAttribute[ m ].name || String( m );
//
//						this.morphTargetInfluences.push( 0 );
//						this.morphTargetDictionary[ name ] = m;
//
//					}
//
//				}
//
//			}
//
//		} else {
//
//			const morphTargets = geometry.morphTargets;
//
//			if ( morphTargets != undefined && morphTargets.length > 0 ) {
//
//				console.error( "THREE.Mesh.updateMorphTargets() no longer supports THREE.Geometry. Use THREE.BufferGeometry instead." );
//
//			}
//
//		}
//
	}

//	public raycast  ( raycaster, intersects ) {
//
//		const geometry = this.geometry;
//		const material = this.material;
//		const matrixWorld = this.matrixWorld;
//
//		if ( material === undefined ) return;
//
//		// Checking boundingSphere distance to ray
//
//		if ( geometry.boundingSphere === null ) geometry.computeBoundingSphere();
//
//		_sphere.copy( geometry.boundingSphere );
//		_sphere.applyMatrix4( matrixWorld );
//
//		if ( raycaster.ray.intersectsSphere( _sphere ) === false ) return;
//
//		//
//
//		_inverseMatrix.copy( matrixWorld ).invert();
//		_ray.copy( raycaster.ray ).applyMatrix4( _inverseMatrix );
//
//		// Check boundingBox before continuing
//
//		if ( geometry.boundingBox !== null ) {
//
//			if ( _ray.intersectsBox( geometry.boundingBox ) === false ) return;
//
//		}
//
//		let intersection;
//
//		if ( geometry.isBufferGeometry ) {
//
//			const index = geometry.index;
//			const position = geometry.attributes.position;
//			const morphPosition = geometry.morphAttributes.position;
//			const morphTargetsRelative = geometry.morphTargetsRelative;
//			const uv = geometry.attributes.uv;
//			const uv2 = geometry.attributes.uv2;
//			const groups = geometry.groups;
//			const drawRange = geometry.drawRange;
//
//			if ( index !== null ) {
//
//				// indexed buffer geometry
//
//				if ( Array.isArray( material ) ) {
//
//					for ( let i = 0, il = groups.length; i < il; i ++ ) {
//
//						const group = groups[ i ];
//						const groupMaterial = material[ group.materialIndex ];
//
//						const start = Math.max( group.start, drawRange.start );
//						const end = Math.min( ( group.start + group.count ), ( drawRange.start + drawRange.count ) );
//
//						for ( let j = start, jl = end; j < jl; j += 3 ) {
//
//							const a = index.getX( j );
//							const b = index.getX( j + 1 );
//							const c = index.getX( j + 2 );
//
//							intersection = checkBufferGeometryIntersection( this, groupMaterial, raycaster, _ray, position, morphPosition, morphTargetsRelative, uv, uv2, a, b, c );
//
//							if ( intersection ) {
//
//								intersection.faceIndex = Math.floor( j / 3 ); // triangle number in indexed buffer semantics
//								intersection.face.materialIndex = group.materialIndex;
//								intersects.push( intersection );
//
//							}
//
//						}
//
//					}
//
//				} else {
//
//					const start = Math.max( 0, drawRange.start );
//					const end = Math.min( index.count, ( drawRange.start + drawRange.count ) );
//
//					for ( let i = start, il = end; i < il; i += 3 ) {
//
//						const a = index.getX( i );
//						const b = index.getX( i + 1 );
//						const c = index.getX( i + 2 );
//
//						intersection = checkBufferGeometryIntersection( this, material, raycaster, _ray, position, morphPosition, morphTargetsRelative, uv, uv2, a, b, c );
//
//						if ( intersection ) {
//
//							intersection.faceIndex = Math.floor( i / 3 ); // triangle number in indexed buffer semantics
//							intersects.push( intersection );
//
//						}
//
//					}
//
//				}
//
//			} else if ( position !== undefined ) {
//
//				// non-indexed buffer geometry
//
//				if ( Array.isArray( material ) ) {
//
//					for ( let i = 0, il = groups.length; i < il; i ++ ) {
//
//						const group = groups[ i ];
//						const groupMaterial = material[ group.materialIndex ];
//
//						const start = Math.max( group.start, drawRange.start );
//						const end = Math.min( ( group.start + group.count ), ( drawRange.start + drawRange.count ) );
//
//						for ( let j = start, jl = end; j < jl; j += 3 ) {
//
//							const a = j;
//							const b = j + 1;
//							const c = j + 2;
//
//							intersection = checkBufferGeometryIntersection( this, groupMaterial, raycaster, _ray, position, morphPosition, morphTargetsRelative, uv, uv2, a, b, c );
//
//							if ( intersection ) {
//
//								intersection.faceIndex = Math.floor( j / 3 ); // triangle number in non-indexed buffer semantics
//								intersection.face.materialIndex = group.materialIndex;
//								intersects.push( intersection );
//
//							}
//
//						}
//
//					}
//
//				} else {
//
//					const start = Math.max( 0, drawRange.start );
//					const end = Math.min( position.count, ( drawRange.start + drawRange.count ) );
//
//					for ( let i = start, il = end; i < il; i += 3 ) {
//
//						const a = i;
//						const b = i + 1;
//						const c = i + 2;
//
//						intersection = checkBufferGeometryIntersection( this, material, raycaster, _ray, position, morphPosition, morphTargetsRelative, uv, uv2, a, b, c );
//
//						if ( intersection ) {
//
//							intersection.faceIndex = Math.floor( i / 3 ); // triangle number in non-indexed buffer semantics
//							intersects.push( intersection );
//
//						}
//
//					}
//
//				}
//
//			}
//
//		} else if ( geometry.isGeometry ) {
//
//			const isMultiMaterial = Array.isArray( material );
//
//			const vertices = geometry.vertices;
//			const faces = geometry.faces;
//			let uvs;
//
//			const faceVertexUvs = geometry.faceVertexUvs[ 0 ];
//			if ( faceVertexUvs.length > 0 ) uvs = faceVertexUvs;
//
//			for ( let f = 0, fl = faces.length; f < fl; f ++ ) {
//
//				const face = faces[ f ];
//				const faceMaterial = isMultiMaterial ? material[ face.materialIndex ] : material;
//
//				if ( faceMaterial === undefined ) continue;
//
//				const fvA = vertices[ face.a ];
//				const fvB = vertices[ face.b ];
//				const fvC = vertices[ face.c ];
//
//				intersection = checkIntersection( this, faceMaterial, raycaster, _ray, fvA, fvB, fvC, _intersectionPoint );
//
//				if ( intersection ) {
//
//					if ( uvs && uvs[ f ] ) {
//
//						const uvs_f = uvs[ f ];
//						_uvA.copy( uvs_f[ 0 ] );
//						_uvB.copy( uvs_f[ 1 ] );
//						_uvC.copy( uvs_f[ 2 ] );
//
//						intersection.uv = Triangle.getUV( _intersectionPoint, fvA, fvB, fvC, _uvA, _uvB, _uvC, new Vector2() );
//
//					}
//
//					intersection.face = face;
//					intersection.faceIndex = f;
//					intersects.push( intersection );
//
//				}
//
//			}
//
//		}
//
//	}
//
//} );
//
//public checkIntersection( object, material, raycaster, ray, pA, pB, pC, point ) {
//
//	let intersect;
//
//	if ( material.side === BackSide ) {
//
//		intersect = ray.intersectTriangle( pC, pB, pA, true, point );
//
//	} else {
//
//		intersect = ray.intersectTriangle( pA, pB, pC, material.side !== DoubleSide, point );
//
//	}
//
//	if ( intersect === null ) return null;
//
//	_intersectionPointWorld.copy( point );
//	_intersectionPointWorld.applyMatrix4( object.matrixWorld );
//
//	const distance = raycaster.ray.origin.distanceTo( _intersectionPointWorld );
//
//	if ( distance < raycaster.near || distance > raycaster.far ) return null;
//
//	return {
//		distance: distance,
//		point: _intersectionPointWorld.clone(),
//		object: object
//	};
//
//}
//
//public checkBufferGeometryIntersection( object, material, raycaster, ray, position, morphPosition, morphTargetsRelative, uv, uv2, a, b, c ) {
//
//	_vA.fromBufferAttribute( position, a );
//	_vB.fromBufferAttribute( position, b );
//	_vC.fromBufferAttribute( position, c );
//
//	const morphInfluences = object.morphTargetInfluences;
//
//	if ( material.morphTargets && morphPosition && morphInfluences ) {
//
//		_morphA.set( 0, 0, 0 );
//		_morphB.set( 0, 0, 0 );
//		_morphC.set( 0, 0, 0 );
//
//		for ( let i = 0, il = morphPosition.length; i < il; i ++ ) {
//
//			const influence = morphInfluences[ i ];
//			const morphAttribute = morphPosition[ i ];
//
//			if ( influence === 0 ) continue;
//
//			_tempA.fromBufferAttribute( morphAttribute, a );
//			_tempB.fromBufferAttribute( morphAttribute, b );
//			_tempC.fromBufferAttribute( morphAttribute, c );
//
//			if ( morphTargetsRelative ) {
//
//				_morphA.addScaledVector( _tempA, influence );
//				_morphB.addScaledVector( _tempB, influence );
//				_morphC.addScaledVector( _tempC, influence );
//
//			} else {
//
//				_morphA.addScaledVector( _tempA.sub( _vA ), influence );
//				_morphB.addScaledVector( _tempB.sub( _vB ), influence );
//				_morphC.addScaledVector( _tempC.sub( _vC ), influence );
//
//			}
//
//		}
//
//		_vA.add( _morphA );
//		_vB.add( _morphB );
//		_vC.add( _morphC );
//
//	}
//
//	if ( object.isSkinnedMesh ) {
//
//		object.boneTransform( a, _vA );
//		object.boneTransform( b, _vB );
//		object.boneTransform( c, _vC );
//
//	}
//
//	const intersection = checkIntersection( object, material, raycaster, ray, _vA, _vB, _vC, _intersectionPoint );
//
//	if ( intersection ) {
//
//		if ( uv ) {
//
//			_uvA.fromBufferAttribute( uv, a );
//			_uvB.fromBufferAttribute( uv, b );
//			_uvC.fromBufferAttribute( uv, c );
//
//			intersection.uv = Triangle.getUV( _intersectionPoint, _vA, _vB, _vC, _uvA, _uvB, _uvC, new Vector2() );
//
//		}
//
//		if ( uv2 ) {
//
//			_uvA.fromBufferAttribute( uv2, a );
//			_uvB.fromBufferAttribute( uv2, b );
//			_uvC.fromBufferAttribute( uv2, c );
//
//			intersection.uv2 = Triangle.getUV( _intersectionPoint, _vA, _vB, _vC, _uvA, _uvB, _uvC, new Vector2() );
//
//		}
//
//		const face = new Face3( a, b, c );
//		Triangle.getNormal( _vA, _vB, _vC, face.normal );
//
//		intersection.face = face;
//
//	}
//
//	return intersection;
//}
}
