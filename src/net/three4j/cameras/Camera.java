package net.three4j.cameras;

import net.three4j.core.Object3D;
import net.three4j.math.Matrix4;


public class Camera extends Object3D {

	public class View {
		boolean _enabled = true;
		double _fullWidth = 1;
		double _fullHeight = 1;
		double _offsetX = 0;
		double _offsetY = 0;
		double _width = 1;
		double _height = 1;

		public boolean enabled() {
			return _enabled;
		}

		public double fullWidth() {
			return _fullWidth;
		}

		public double fullHeight() {
			return _fullHeight;
		}

		public double offsetX() {
			return _offsetX;
		}

		public double offsetY() {
			return _offsetY;
		}

		public double width() {
			return _width;
		}

		public double height() {
			return _height;
		}

	};

	private Matrix4 _matrixWorldInverse;
	protected Matrix4 _projectionMatrix;
	protected Matrix4 _projectionMatrixInverse;

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

	public Camera clone() {
		return new Camera().copy(this);
	}

	public Camera copy (Camera source) {
		return copy(source, true);
	}

	public Camera copy  ( Camera source, boolean recursive ) {

		super.copy( source, recursive );

		this._matrixWorldInverse.copy( source._matrixWorldInverse );

		this._projectionMatrix.copy( source._projectionMatrix );
		this._projectionMatrixInverse.copy( source._projectionMatrixInverse );

		return this;

	}

//	public Matrix4 getWorldDirection  ( Matrix4 target ) {
//
//		this.updateWorldMatrix( true, false );
//
//		double[] e = Arrays.copyOf(this._matrixWorld.elements, this._matrixWorld.elements.length);
//		// Not sure what to do here.
//
//		return target.set( - e[ 8 ], - e[ 9 ], - e[ 10 ] ).normalize();
//	}

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

