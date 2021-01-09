package net.three4j.core;

//import { Quaternion } from '../math/Quaternion.js';
import net.three4j.math.Quaternion;
import static net.three4j.THREE.console;
//import { Vector3 } from '../math/Vector3.js';

import net.three4j.math.Vector3;

import java.util.ArrayList;

//import { Matrix4 } from '../math/Matrix4.js';
//import { EventDispatcher } from './EventDispatcher.js';
import net.three4j.core.EventDispatcher;
//import { Euler } from '../math/Euler.js';
import net.three4j.math.Euler;
//import { Layers } from './Layers.js';
//import { Matrix3 } from '../math/Matrix3.js';
//import { _Math } from '../math/Math.js';
//
///**
// * @author mrdoob / http://mrdoob.com/
// * @author mikael emtinger / http://gomo.se/
// * @author alteredq / http://alteredqualia.com/
// * @author WestLangley / http://github.com/WestLangley
// * @author elephantatwork / www.elephantatwork.ch
// */
//
import net.three4j.math.MathUtils;
import net.three4j.math.Matrix3;
import net.three4j.math.Matrix4;

public class Object3D extends EventDispatcher {
	// let _object3DId = 0;
	static int _object3DId = 0;
	
	private final Vector3 _v1 = new Vector3();
	private final Quaternion _q1 = new Quaternion();
	private final Matrix4 _m1 = new Matrix4();
	private final Vector3 _target = new Vector3();

	private final Vector3 _position = new Vector3();

	private final Vector3 _scale = new Vector3();
	private final Quaternion _quaternion = new Quaternion();

	private final Vector3 _xAxis = new Vector3( 1, 0, 0 );
	private final Vector3 _yAxis = new Vector3( 0, 1, 0 );
	private final Vector3 _zAxis = new Vector3( 0, 0, 1 );

//	const _addedEvent = { type: 'added' };
//	const _removedEvent = { type: 'removed' };

	public final long id;
	public final Vector3 position;
	public Euler rotation;
	public Object3D parent;
	public ArrayList<Object3D> children;
	public final String uuid;
	public Quaternion quaternion;
	public String name;
	public Vector3 scale;
	public Vector3 up;
	public Matrix4 modelViewMatrix;
	public Matrix3 normalMatrix;
	public Matrix4 matrix;
	public Matrix4 matrixWorld;
	public boolean matrixAutoUpdate;
	public boolean matrixWorldNeedsUpdate;
	public boolean castShadow;
	public boolean receiveShadow;
	public boolean frustumCulled;
	public int renderOrder;
	public boolean visible;
	public Layers layers;
	
	
	public Object3D() {
		id = _object3DId++;

		uuid = MathUtils.generateUUID();
	
		this.name = "";
	
		this.parent = null;
		this.children = new ArrayList<>();
	
		this.up = new Vector3(DefaultUp);
	
		position = new Vector3();
		rotation = new Euler();
		quaternion = new Quaternion();
		scale = new Vector3( 1, 1, 1 );
		
		this.matrix = new Matrix4();
		this.matrixWorld = new Matrix4();
	
		this.matrixAutoUpdate = DefaultMatrixAutoUpdate;
		this.matrixWorldNeedsUpdate = false;
	
		this.layers = new Layers();
		this.visible = true;
	
		this.castShadow = false;
		this.receiveShadow = false;
	
		this.frustumCulled = true;
		this.renderOrder = 0;
	
//		this.animations = [];
//	
//		this.userData = {};
	
	}

	public void onRotationChange() {
		quaternion.setFromEuler( rotation, false );
	}

	public void onQuaternionChange() {
		rotation.setFromQuaternion( quaternion, null, false );
	}

//	rotation._onChange( onRotationChange );
//	quaternion._onChange( onQuaternionChange );

	static final Vector3 DefaultUp = new Vector3( 0, 1, 0 );
	boolean DefaultMatrixAutoUpdate = true;

	public boolean isObject3D = true;

//Object3D.prototype = Object.assign( Object.create( EventDispatcher.prototype ), {
//
//	constructor: Object3D,
//
//	isObject3D: true,
//
//	onBeforeRender: function () {},
//	onAfterRender: function () {},
//
//	applyMatrix4: function ( matrix ) {
//
//		if ( this.matrixAutoUpdate ) this.updateMatrix();
//
//		this.matrix.premultiply( matrix );
//
//		this.matrix.decompose( this.position, this.quaternion, this.scale );
//
//	},

	public Object3D applyQuaternion( Quaternion q ) {

		this.quaternion.premultiply( q );

		return this;
	}

	public void setRotationFromAxisAngle(Vector3 axis, double angle ) {
		// assumes axis is normalized
		this.quaternion.setFromAxisAngle( axis, angle );
	}

	public void setRotationFromEuler( Euler euler ) {

		this.quaternion.setFromEuler( euler, true );

	}

	public void setRotationFromMatrix( Matrix4 m ) {

		// assumes the upper 3x3 of m is a pure rotation matrix (i.e, unscaled)

		this.quaternion.setFromRotationMatrix( m );

	}

	public void setRotationFromQuaternion( Quaternion q ) {

		// assumes q is normalized

		this.quaternion.copy( q );

	}

	public Object3D rotateOnAxis( Vector3 axis, double angle ) {

		// rotate object on axis in object space
		// axis is assumed to be normalized

		_q1.setFromAxisAngle( axis, angle );

		this.quaternion.multiply( _q1 );

		return this;

	}

	public Object3D rotateOnWorldAxis( Vector3 axis, double angle ) {
		// rotate object on axis in world space
		// axis is assumed to be normalized
		// method assumes no rotated parent
		_q1.setFromAxisAngle( axis, angle );
		this.quaternion.premultiply( _q1 );
		return this;
	}

	public Object3D rotateX( double angle ) {

		return this.rotateOnAxis( _xAxis, angle );

	}

	public Object3D rotateY( double angle ) {

		return this.rotateOnAxis( _yAxis, angle );

	}

	public Object3D rotateZ( double angle ) {

		return this.rotateOnAxis( _zAxis, angle );

	}

	public Object3D translateOnAxis( Vector3 axis, double distance ) {

		// translate object by distance along axis in object space
		// axis is assumed to be normalized

//		_v1.copy( axis ).applyQuaternion( this.quaternion );
//
//		this.position.add( _v1.multiplyScalar( distance ) );

		return this;

	}

	public Object3D translateX( double distance ) {

		return this.translateOnAxis( _xAxis, distance );

	}

	public Object3D translateY(double distance ) {

		return this.translateOnAxis( _yAxis, distance );

	}

	public Object3D translateZ( double distance ) {

		return this.translateOnAxis( _zAxis, distance );

	}

//	public Vector3 localToWorld( Vector3 vector ) {
//
////		return vector.applyMatrix4( this.matrixWorld );
//
//	}

//	public Vector3 worldToLocal( Vector3 vector ) {
//
//		return vector.applyMatrix4( _m1.copy( this.matrixWorld ).invert() );
//
//	}
	
	public void lookAt(Vector3 v) {
		lookAt(v.x, v.y, v.z);
	}

	public void lookAt(double x, double y, double z ) {

		// This method does not support objects having non-uniformly-scaled parent(s)

//		_target.set( x, y, z );

		this.updateWorldMatrix( true, false );

//		_position.setFromMatrixPosition( this.matrixWorld );

//		if ( this.isCamera || this.isLight ) {
//
//			_m1.lookAt( _position, _target, this.up );
//
//		} else {
//
//			_m1.lookAt( _target, _position, this.up );
//
//		}

		this.quaternion.setFromRotationMatrix( _m1 );

		if ( this.parent != null ) {

//			_m1.extractRotation( parent.matrixWorld );
//			_q1.setFromRotationMatrix( _m1 );
//			this.quaternion.premultiply( _q1.invert() );

		}
	}

	public Object3D add(Object3D... objects) {

		for ( int i = 0; i < objects.length; i ++ ) {

			if ( objects[i] == this ) {
				throw new RuntimeException( "THREE.Object3D.add: object can't be added as a child of itself.");
			}

			if ( objects[i] != null && objects[i].isObject3D ) {

				if ( objects[i].parent != null ) {

					objects[i].parent.remove( objects[i] );

				}

				objects[i].parent = this;
				this.children.add( objects[i] );

//				objects.dispatchEvent( _addedEvent );

			} else {
				throw new RuntimeException( "THREE.Object3D.add: object not an instance of THREE.Object3D.");
			}

			this.add( objects[ i ] );
		}

		return this;
	}

	public Object3D remove( Object3D... objects ) {

		if ( objects.length > 1 ) {

			for ( int i = 0; i < objects.length; i ++ ) {

				this.remove( objects[ i ] );

			}

			return this;

		}

		final int index = this.children.indexOf( objects[0] );

		if ( index != - 1 ) {

			objects[0].parent = null;
			this.children.remove( index );

//			object.dispatchEvent( _removedEvent );

		}

		return this;

	}

	public Object3D clear() {

		for ( int i = 0; i < this.children.size(); i ++ ) {

			final Object3D object = this.children.get(i);

			object.parent = null;

//			object.dispatchEvent( _removedEvent );

		}

		this.children.clear();

		return this;

	}

	public Object3D attach( Object3D object ) {

		// adds object as a child of this, while maintaining the object's world transform

//		this.updateWorldMatrix( true, false );
//
//		_m1.copy( this.matrixWorld ).invert();

		if ( object.parent != null ) {

//			object.parent.updateWorldMatrix( true, false );
//
//			_m1.multiply( object.parent.matrixWorld );

		}

//		object.applyMatrix4( _m1 );
//
//		object.updateWorldMatrix( false, false );

		this.add( object );

		return this;

	}

//	public Object3D getObjectById( String id ) {

//		return this.getObjectByProperty( "id", id );

//	}

//	public Object3D getObjectByName( String name ) {

//		return this.getObjectByProperty( "name", name );

//	}

	public Object3D getObjectByProperty( String name, String value ) {

//		if ( this[ name ] == value ) 
//			return this;

		for ( int i = 0, l = this.children.size(); i < l; i ++ ) {

			final Object3D child = this.children.get(i);
			final Object3D object = child.getObjectByProperty( name, value );

			if ( object != null ) {

				return object;

			}

		}

		return null;

	}

//	public Vector3 getWorldPosition( Vector3 target ) {
//
//		if ( target == null ) {
//
//			console.warn( "THREE.Object3D: .getWorldPosition() target is now required" );
//			target = new Vector3();
//
//		}
//
//		this.updateWorldMatrix( true, false );
//
//		return target.setFromMatrixPosition( this.matrixWorld );
//
//	}

	public Quaternion getWorldQuaternion( Quaternion target ) {

		if ( target == null ) {

			console.warn( "THREE.Object3D: .getWorldQuaternion() target is now required" );
			target = new Quaternion();

		}

//		this.updateWorldMatrix( true, false );
//
//		this.matrixWorld.decompose( _position, target, _scale );

		return target;

	}

	public Vector3 getWorldScale( Vector3 target ) {

		if ( target == null ) {

			console.warn( "THREE.Object3D: .getWorldScale() target is now required" );
			target = new Vector3();

		}

//		this.updateWorldMatrix( true, false );
//
//		this.matrixWorld.decompose( _position, _quaternion, target );

		return target;

	}

//	public Vector3 getWorldDirection( Vector3 target ) {
//
//		if ( target == null ) {
//
//			console.warn( "THREE.Object3D: .getWorldDirection() target is now required" );
//			target = new Vector3();
//
//		}
//
////		this.updateWorldMatrix( true, false );
//
//		final double[] e = this.matrixWorld.elements;
//
//		return target.set( e[ 8 ], e[ 9 ], e[ 10 ] ).normalize();
//
//	}

//	public void raycast(Raycaster raycaster, Intersection[] intersections ) {}

//	public void traverse( callback ) {
//
//		callback( this );
//
//		const children = this.children;
//
//		for ( let i = 0, l = children.length; i < l; i ++ ) {
//
//			children[ i ].traverse( callback );
//
//		}
//
//	}

//	public traverseVisible: function ( callback ) {
//
//		if ( this.visible === false ) return;
//
//		callback( this );
//
//		const children = this.children;
//
//		for ( let i = 0, l = children.length; i < l; i ++ ) {
//
//			children[ i ].traverseVisible( callback );
//
//		}
//
//	},
//
//	public traverseAncestors: function ( callback ) {
//
//		const parent = this.parent;
//
//		if ( parent !== null ) {
//
//			callback( parent );
//
//			parent.traverseAncestors( callback );
//
//		}
//
//	},
//
	void updateMatrix() {

//		this.matrix.compose( this.position, this.quaternion, this.scale );

		this.matrixWorldNeedsUpdate = true;

	}

	public void updateMatrixWorld() {
		updateMatrixWorld(false);
	}
	
	public void updateMatrixWorld( boolean force ) {

		if ( this.matrixAutoUpdate ) 
			this.updateMatrix();

		if ( this.matrixWorldNeedsUpdate || force ) {

			if ( this.parent == null ) {

//				this.matrixWorld.copy( this.matrix );

			} else {

//				this.matrixWorld.multiplyMatrices( this.parent.matrixWorld, this.matrix );

			}

			this.matrixWorldNeedsUpdate = false;

			force = true;

		}

		// update children

		for ( int i = 0, l = children.size(); i < l; i ++ ) {

			this.children.get(i).updateMatrixWorld( force );

		}

	}

	public void updateWorldMatrix( boolean updateParents, boolean updateChildren ) {

		if ( updateParents == true && parent != null ) {

			parent.updateWorldMatrix( true, false );

		}

		if ( this.matrixAutoUpdate )
			this.updateMatrix();

		if ( this.parent == null ) {

//			this.matrixWorld.copy( this.matrix );

		} else {

//			this.matrixWorld.multiplyMatrices( this.parent.matrixWorld, this.matrix );

		}

		// update children

		if ( updateChildren == true ) {

			for ( int i = 0, l = children.size(); i < l; i ++ ) {

				children.get(i).updateWorldMatrix( false, true );

			}

		}

	}

//	public toJSON: function ( meta ) {
//
//		// meta is a string when called from JSON.stringify
//		const isRootObject = ( meta === undefined || typeof meta === 'string' );
//
//		const output = {};
//
//		// meta is a hash used to collect geometries, materials.
//		// not providing it implies that this is the root object
//		// being serialized.
//		if ( isRootObject ) {
//
//			// initialize meta obj
//			meta = {
//				geometries: {},
//				materials: {},
//				textures: {},
//				images: {},
//				shapes: {},
//				skeletons: {},
//				animations: {}
//			};
//
//			output.metadata = {
//				version: 4.5,
//				type: 'Object',
//				generator: 'Object3D.toJSON'
//			};
//
//		}
//
//		// standard Object3D serialization
//
//		const object = {};
//
//		object.uuid = this.uuid;
//		object.type = this.type;
//
//		if ( this.name !== '' ) object.name = this.name;
//		if ( this.castShadow === true ) object.castShadow = true;
//		if ( this.receiveShadow === true ) object.receiveShadow = true;
//		if ( this.visible === false ) object.visible = false;
//		if ( this.frustumCulled === false ) object.frustumCulled = false;
//		if ( this.renderOrder !== 0 ) object.renderOrder = this.renderOrder;
//		if ( JSON.stringify( this.userData ) !== '{}' ) object.userData = this.userData;
//
//		object.layers = this.layers.mask;
//		object.matrix = this.matrix.toArray();
//
//		if ( this.matrixAutoUpdate === false ) object.matrixAutoUpdate = false;
//
//		// object specific properties
//
//		if ( this.isInstancedMesh ) {
//
//			object.type = 'InstancedMesh';
//			object.count = this.count;
//			object.instanceMatrix = this.instanceMatrix.toJSON();
//
//		}
//
//		//
//
//		function serialize( library, element ) {
//
//			if ( library[ element.uuid ] === undefined ) {
//
//				library[ element.uuid ] = element.toJSON( meta );
//
//			}
//
//			return element.uuid;
//
//		}
//
//		if ( this.isMesh || this.isLine || this.isPoints ) {
//
//			object.geometry = serialize( meta.geometries, this.geometry );
//
//			const parameters = this.geometry.parameters;
//
//			if ( parameters !== undefined && parameters.shapes !== undefined ) {
//
//				const shapes = parameters.shapes;
//
//				if ( Array.isArray( shapes ) ) {
//
//					for ( let i = 0, l = shapes.length; i < l; i ++ ) {
//
//						const shape = shapes[ i ];
//
//						serialize( meta.shapes, shape );
//
//					}
//
//				} else {
//
//					serialize( meta.shapes, shapes );
//
//				}
//
//			}
//
//		}
//
//		if ( this.isSkinnedMesh ) {
//
//			object.bindMode = this.bindMode;
//			object.bindMatrix = this.bindMatrix.toArray();
//
//			if ( this.skeleton !== undefined ) {
//
//				serialize( meta.skeletons, this.skeleton );
//
//				object.skeleton = this.skeleton.uuid;
//
//			}
//
//		}
//
//		if ( this.material !== undefined ) {
//
//			if ( Array.isArray( this.material ) ) {
//
//				const uuids = [];
//
//				for ( let i = 0, l = this.material.length; i < l; i ++ ) {
//
//					uuids.push( serialize( meta.materials, this.material[ i ] ) );
//
//				}
//
//				object.material = uuids;
//
//			} else {
//
//				object.material = serialize( meta.materials, this.material );
//
//			}
//
//		}
//
//		//
//
//		if ( this.children.length > 0 ) {
//
//			object.children = [];
//
//			for ( let i = 0; i < this.children.length; i ++ ) {
//
//				object.children.push( this.children[ i ].toJSON( meta ).object );
//
//			}
//
//		}
//
//		//
//
//		if ( this.animations.length > 0 ) {
//
//			object.animations = [];
//
//			for ( let i = 0; i < this.animations.length; i ++ ) {
//
//				const animation = this.animations[ i ];
//
//				object.animations.push( serialize( meta.animations, animation ) );
//
//			}
//
//		}
//
//		if ( isRootObject ) {
//
//			const geometries = extractFromCache( meta.geometries );
//			const materials = extractFromCache( meta.materials );
//			const textures = extractFromCache( meta.textures );
//			const images = extractFromCache( meta.images );
//			const shapes = extractFromCache( meta.shapes );
//			const skeletons = extractFromCache( meta.skeletons );
//			const animations = extractFromCache( meta.animations );
//
//			if ( geometries.length > 0 ) output.geometries = geometries;
//			if ( materials.length > 0 ) output.materials = materials;
//			if ( textures.length > 0 ) output.textures = textures;
//			if ( images.length > 0 ) output.images = images;
//			if ( shapes.length > 0 ) output.shapes = shapes;
//			if ( skeletons.length > 0 ) output.skeletons = skeletons;
//			if ( animations.length > 0 ) output.animations = animations;
//
//		}
//
//		output.object = object;
//
//		return output;
//
//		// extract data from the cache hash
//		// remove metadata on each item
//		// and return as array
//		function extractFromCache( cache ) {
//
//			const values = [];
//			for ( const key in cache ) {
//
//				const data = cache[ key ];
//				delete data.metadata;
//				values.push( data );
//
//			}
//
//			return values;
//
//		}
//
//	},
	
	public Object3D clone() {
		return clone(true);
	}

	public Object3D clone( boolean recursive ) {

		return new Object3D().copy( this, recursive );

	}
	
	public Object3D copy( Object3D source) {
		return copy(source);
	}

	public Object3D copy( Object3D source, boolean recursive ) {

		this.name = source.name;

//		this.up.copy( source.up );
//
//		this.position.copy( source.position );
//		this.rotation.order = source.rotation.order;
//		this.quaternion.copy( source.quaternion );
//		this.scale.copy( source.scale );
//
//		this.matrix.copy( source.matrix );
//		this.matrixWorld.copy( source.matrixWorld );

		this.matrixAutoUpdate = source.matrixAutoUpdate;
		this.matrixWorldNeedsUpdate = source.matrixWorldNeedsUpdate;

//		this.layers.mask = source.layers.mask;
		this.visible = source.visible;

		this.castShadow = source.castShadow;
		this.receiveShadow = source.receiveShadow;

		this.frustumCulled = source.frustumCulled;
		this.renderOrder = source.renderOrder;

//		this.userData = JSON.parse( JSON.stringify( source.userData ) );

		if ( recursive == true ) {

			for ( int i = 0; i < source.children.size(); i ++ ) {

				final Object3D child = source.children.get(i);
				this.add( child.clone() );

			}

		}

		return this;

	}

}