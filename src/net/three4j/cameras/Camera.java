package net.three4j.cameras;

import net.three4j.math.Matrix4;
import net.three4j.core.Object3D;
import net.three4j.math.Vector3;


public class Camera extends Object3D {
	
	private Matrix4 _matrixWorldInverse;
	private Matrix4 _projectionMatrix;
	private Matrix4 _projectionMatrixInverse;
	
	public Matrix4 matrixWorldInverse() {
		return _matrixWorldInverse;
	}

	public Matrix4 projectionMatrix() {
		return _projectionMatrix;
	}

	public Matrix4 projectionMatrixInverse() {
		return _projectionMatrixInverse;
	}

	public Camera() {
		super();
	
		this._matrixWorldInverse = new Matrix4();
	
		this._projectionMatrix = new Matrix4();
		this._projectionMatrixInverse = new Matrix4();
	
	}

	public Camera copy  ( Camera source, Camera recursive ) {

//		Object3D.prototype.copy.call( this, source, recursive );
//
//		this.matrixWorldInverse.copy( source.matrixWorldInverse );
//
//		this.projectionMatrix.copy( source.projectionMatrix );
//		this.projectionMatrixInverse.copy( source.projectionMatrixInverse );

		return this;

	}

	public Matrix4 getWorldDirection  ( Matrix4 target ) {

//		this.updateWorldMatrix( true, false );
//
//		const e = this.matrixWorld.elements;
//
//		return target.set( - e[ 8 ], - e[ 9 ], - e[ 10 ] ).normalize();
		return null;
	}

//	public void updateMatrixWorld ( force ) {

//		Object3D.prototype.updateMatrixWorld.call( this, force );
//
//		this.matrixWorldInverse.copy( this.matrixWorld ).invert();

//	}

//	public void updateWorldMatrix  ( updateParents, updateChildren ) {
//
//		Object3D.prototype.updateWorldMatrix.call( this, updateParents, updateChildren );
//
//		this.matrixWorldInverse.copy( this.matrixWorld ).invert();
//
//	}

//	public Camera clone  () {
//		return new Camera().copy( this );
//	}

}

