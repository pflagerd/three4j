package net.three4j.materials;

import static net.three4j.constants.TangentSpaceNormalMap;

import net.three4j.KV;
import net.three4j.math.Vector2;
import net.three4j.textures.Texture;

public class MeshNormalMaterial extends Material {
	public final boolean isMeshNormalMaterial = true;

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

	public MeshNormalMaterial() {
		this(null);
	}

	public MeshNormalMaterial(KV parameters) {
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

	public MeshNormalMaterial copy( MeshNormalMaterial source ) {
		super.copy( source );

		this._bumpMap = source._bumpMap;
		this._bumpScale = source._bumpScale;

		this._normalMap = source._normalMap;
		this._normalMapType = source._normalMapType;
		this._normalScale.copy( source._normalScale );

		this._displacementMap = source._displacementMap;
		this._displacementScale = source._displacementScale;
		this._displacementBias = source._displacementBias;

		this._wireframe = source._wireframe;
		this._wireframeLinewidth = source._wireframeLinewidth;

		this._skinning = source._skinning;
		this._morphTargets = source._morphTargets;
		this._morphNormals = source._morphNormals;

		return this;
	}

	public void setValues(KV values) {

		for ( String key : values.keySet() ) {

			Object newValue = values.get(key);

			if ( newValue == null ) {

				// console.warn( 'THREE.Material: \'' + key + '\' parameter is undefined.' );
				continue;

			}

			// for backward compatibility if shading is set in the constructor
			if ( key.contentEquals("shading")) {
				throw new RuntimeException( this._type + ".shading has been removed. Use " + this._type + ".flatShading instead." );
				// DPP: Obsolete. Used to do this:
				// this.flatShading = ( newValue === FlatShading ) ? true : false;
				// continue;
			}

			switch (key) {

//				const currentValue = this[ key ];

//				if ( currentValue && currentValue.isColor ) {

//					currentValue.set( newValue );

//				} else if ( ( currentValue && currentValue.isVector3 ) && ( newValue && newValue.isVector3 ) ) {

//					currentValue.copy( newValue );

//				} else {

//					this[ key ] = newValue;

//				}

				case "bumpMap":
				case "bumpScale":
				case "displacementBias":
				case "displacementMap":
				case "displacementScale":
				case "fog":
				case "morphNormals":
				case "morphTargets":
				case "normalMap":
				case "normalMapType":
				case "normalScale":
				case "skinning":
				case "wireframe":
				case "wireframeLinewidth":

				default:
					super.setValues(values);
					break;
			}
		}

	}

}
