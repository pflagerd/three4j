package net.three4j.materials;

import static net.three4j.constants.TangentSpaceNormalMap;

import net.three4j.math.Vector2;
import net.three4j.textures.Texture;

//import { TangentSpaceNormalMap } from '../constants.js';
//import { Material } from './Material.js';
//import { Vector2 } from '../math/Vector2.js';

public class MeshNormalMaterial extends Material {
	Texture _bumpMap;

	double _bumpScale;

	Texture _normalMap;

	int _normalMapType;

	Vector2 _normalScale;

	Texture _displacementMap;

	double _displacementScale;

	double _displacementBias;

	boolean _wireframe;

	double _wireframeLinewidth;

	boolean _skinning;

	boolean _morphTargets;

	boolean _morphNormals;

//	class MeshNormalMaterialParameters extends MaterialParameters {
//		Texture _bumpMap;
//
//		double _bumpScale;
//
//		Texture _normalMap;
//
//		int _normalMapType;
//
//		Vector2 _normalScale;
//
//		Texture _displacementMap;
//
//		double _dispacementScale;
//
//		double _displacementBias;
//
//		boolean _wireframe;
//
//		double _wireframeLineWidth;
//
//		boolean _skinning;
//
//		boolean _morphTargets;
//
//		boolean _morphNormals;
//
//		public MeshNormalMaterialParameters(net.three4j.THREE.KV[] parameters) {
//			this._type = "MeshNormalMaterial";
//
//			this._bumpMap = null;
//			this._bumpScale = 1;
//
//			this._normalMap = null;
//			this._normalMapType = TangentSpaceNormalMap;
//			this._normalScale = new Vector2( 1, 1 );
//
//			this._displacementMap = null;
//			this._displacementScale = 1;
//			this._displacementBias = 0;
//
//			this._wireframe = false;
//			this._wireframeLinewidth = 1;
//
//			this._fog = false;
//
//			this._skinning = false;
//			this._morphTargets = false;
//			this._morphNormals = false;
//
//			this._setValues( parameters );
//		}
//	}

	public MeshNormalMaterial() {
		this(null);
	}


//	function MeshNormalMaterial( parameters ) {
	public MeshNormalMaterial(net.three4j.KV parameters) {
		this._type = "MeshNormalMaterial";

		this._bumpMap = null;
		this._bumpScale = 1;

		this._normalMap = null;
		this._normalMapType = TangentSpaceNormalMap;
		this._normalScale = new Vector2( 1, 1 );

		this._displacementMap = null;
		this._displacementScale = 1;
		this._displacementBias = 0;

		this._wireframe = false;
		this._wireframeLinewidth = 1;

		this._fog = false;

		this._skinning = false;
		this._morphTargets = false;
		this._morphNormals = false;

		this.setValues( parameters );

	}

//
//MeshNormalMaterial.prototype = Object.create( Material.prototype );
//MeshNormalMaterial.prototype.constructor = MeshNormalMaterial;
//
//MeshNormalMaterial.prototype.isMeshNormalMaterial = true;
//
//MeshNormalMaterial.prototype.copy = function ( source ) {
//
//	Material.prototype.copy.call( this, source );
//
//	this.bumpMap = source.bumpMap;
//	this.bumpScale = source.bumpScale;
//
//	this.normalMap = source.normalMap;
//	this.normalMapType = source.normalMapType;
//	this.normalScale.copy( source.normalScale );
//
//	this.displacementMap = source.displacementMap;
//	this.displacementScale = source.displacementScale;
//	this.displacementBias = source.displacementBias;
//
//	this.wireframe = source.wireframe;
//	this.wireframeLinewidth = source.wireframeLinewidth;
//
//	this.skinning = source.skinning;
//	this.morphTargets = source.morphTargets;
//	this.morphNormals = source.morphNormals;
//
//	return this;
//
//};

}
