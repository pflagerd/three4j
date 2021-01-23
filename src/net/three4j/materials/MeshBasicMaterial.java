package net.three4j.materials;

import static net.three4j.constants.MultiplyOperation;
import net.three4j.math.Color;

/**
 * parameters = { color: <hex>, opacity: <float>, map: new THREE.Texture(
 * <Image> ),
 *
 * lightMap: new THREE.Texture( <Image> ), lightMapIntensity: <float>
 *
 * aoMap: new THREE.Texture( <Image> ), aoMapIntensity: <float>
 *
 * specularMap: new THREE.Texture( <Image> ),
 *
 * alphaMap: new THREE.Texture( <Image> ),
 *
 * envMap: new THREE.CubeTexture( [posx, negx, posy, negy, posz, negz] ),
 * combine: THREE.Multiply, reflectivity: <float>, refractionRatio: <float>,
 *
 * depthTest: <bool>, depthWrite: <bool>,
 *
 * wireframe: <boolean>, wireframeLinewidth: <float>,
 *
 * skinning: <bool>, morphTargets: <bool> }
 */

public class MeshBasicMaterial extends Material {

	private Color _color = new Color(0xffffff); // emissive

	private Object _map = null;

	private Object _lightMap = null;
	private double _lightMapIntensity = 1.0;

	private Object _aoMap = null;
	private double _aoMapIntensity = 1.0;

	private Object _specularMap = null;

	private Object _alphaMap = null;

	private Object _envMap = null;
	private int _combine = MultiplyOperation;
	private double _reflectivity = 1;
	private double _refractionRatio = 0.98;

	private boolean _wireframe = false;
	private double _wireframeLinewidth = 1;
	private String _wireframeLinecap = "round";
	private String _wireframeLinejoin = "round";

	private boolean _skinning = false;
	private boolean _morphTargets = false;

	public MeshBasicMaterial() {

	}

	public MeshBasicMaterial copy(MeshBasicMaterial source) {

		super.copy(source);

		this._color.copy(source._color);

		this._map = source._map;

		this._lightMap = source._lightMap;
		this._lightMapIntensity = source._lightMapIntensity;

		this._aoMap = source._aoMap;
		this._aoMapIntensity = source._aoMapIntensity;

		this._specularMap = source._specularMap;

		this._alphaMap = source._alphaMap;

		this._envMap = source._envMap;
		this._combine = source._combine;
		this._reflectivity = source._reflectivity;
		this._refractionRatio = source._refractionRatio;

		this._wireframe = source._wireframe;
		this._wireframeLinewidth = source._wireframeLinewidth;
		this._wireframeLinecap = source._wireframeLinecap;
		this._wireframeLinejoin = source._wireframeLinejoin;

		this._skinning = source._skinning;
		this._morphTargets = source._morphTargets;

		return this;
	}
}
