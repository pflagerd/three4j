package net.three4j.scenes;

import net.three4j.math.Color;

//import { Object3D } from '../core/Object3D.js';
//
///**
// * @author mrdoob / http://mrdoob.com/
// */
//
public class Scene extends net.three4j.core.Object3D {

	private Color _background = null;
	private Object _environment = null;
	private Object _fog = null;
	private Object _overrideMaterial = null;
	private boolean _autoUpdate = true;
	private String _type = "Scene";

	public Scene() {
		super();
	}

	Scene copy(Scene source, boolean recursive) {
		super.copy(source, recursive);

		if (source._background != null)
			this._background = source._background.clone();

//		if (source._environment != null)
//			this._environment = source._environment.clone();

//		if ( source._fog != null )
//			this._fog = source._fog.clone();

//		if ( source._overrideMaterial != null )
//			this._overrideMaterial = source._overrideMaterial.clone();

		this._autoUpdate = source._autoUpdate;
		this._matrixAutoUpdate = source._matrixAutoUpdate;

		return this;

	}

	// toJSON: function ( meta ) {
	//
	// var data = Object3D.prototype.toJSON.call( this, meta );
	//
	// if ( this.background !== null ) data.object.background =
	// this.background.toJSON( meta );
	// if ( this.fog !== null ) data.object.fog = this.fog.toJSON();
	//
	// return data;
	//
	// }
	//
}