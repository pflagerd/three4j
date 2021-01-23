package net.three4j.materials;

import net.three4j.core.Event;
import net.three4j.core.EventDispatcher;
import static net.three4j.constants.FrontSide;
import static net.three4j.constants.FlatShading;
import static net.three4j.constants.NormalBlending;
import static net.three4j.constants.LessEqualDepth;
import static net.three4j.constants.AddEquation;
import static net.three4j.constants.OneMinusSrcAlphaFactor;
import static net.three4j.constants.SrcAlphaFactor;
import static net.three4j.constants.AlwaysStencilFunc;
import static net.three4j.constants.KeepStencilOp;
import net.three4j.math.MathUtils;
import net.three4j.math.Plane;

public class Material extends EventDispatcher {

	private static int materialId = 0;

	public final int id = ++materialId;

	public Material needsUpdate(boolean value) {
		if (value == true)
			this._version++;
		return this;
	}

	private String _uuid = MathUtils.generateUUID();

	private String _name = "";
	private String _type = "Material";

	private boolean _fog = true;

	private int _blending = NormalBlending;
	private int _side = FrontSide;
	private boolean _flatShading = false;
	private boolean _vertexColors = false;

	private double _opacity = 1;
	private boolean _transparent = false;

	private int _blendSrc = SrcAlphaFactor;
	private int _blendDst = OneMinusSrcAlphaFactor;
	private int _blendEquation = AddEquation;
	private Object _blendSrcAlpha = null;
	private Object _blendDstAlpha = null;
	private Object _blendEquationAlpha = null;

	private int _depthFunc = LessEqualDepth;
	private boolean _depthTest = true;
	private boolean _depthWrite = true;

	private double _stencilWriteMask = 0xff;
	private int _stencilFunc = AlwaysStencilFunc;
	private double _stencilRef = 0;
	private double _stencilFuncMask = 0xff;
	private int _stencilFail = KeepStencilOp;
	private int _stencilZFail = KeepStencilOp;
	private int _stencilZPass = KeepStencilOp;
	private boolean _stencilWrite = false;

	private Plane[] _clippingPlanes = null;
	private boolean _clipIntersection = false;
	private boolean _clipShadows = false;

	private Object _shadowSide = null;

	private boolean _colorWrite = true;

	private Object _precision = null; // override the renderer's default precision for this material

	private boolean _polygonOffset = false;
	private double _polygonOffsetFactor = 0;
	private double _polygonOffsetUnits = 0;

	private boolean _dithering = false;

	private double _alphaTest = 0;
	private boolean _premultipliedAlpha = false;

	private boolean _visible = true;

	private boolean _toneMapped = true;

	Object userData = new Object();

	private double _version = 0;
	
	public Material() {
		
	}

	public String onBeforeCompile( /* shaderobject, renderer */ ) {
		return "";
	}

	public String customProgramCacheKey() {

		return this.onBeforeCompile().toString();

	}

//	public setValues  ( values ) {
//
//		if ( values === undefined ) return;
//
//		for ( const key in values ) {
//
//			const newValue = values[ key ];
//
//			if ( newValue === undefined ) {
//
//				console.warn( 'THREE.Material: \'' + key + '\' parameter is undefined.' );
//				continue;
//
//			}
//
//			// for backward compatability if shading is set in the constructor
//			if ( key === 'shading' ) {
//
//				console.warn( 'THREE.' + this.type + ': .shading has been removed. Use the boolean .flatShading instead.' );
//				this.flatShading = ( newValue === FlatShading ) ? true : false;
//				continue;
//
//			}
//
//			const currentValue = this[ key ];
//
//			if ( currentValue === undefined ) {
//
//				console.warn( 'THREE.' + this.type + ': \'' + key + '\' is not a property of this material.' );
//				continue;
//
//			}
//
//			if ( currentValue && currentValue.isColor ) {
//
//				currentValue.set( newValue );
//
//			} else if ( ( currentValue && currentValue.isVector3 ) && ( newValue && newValue.isVector3 ) ) {
//
//				currentValue.copy( newValue );
//
//			} else {
//
//				this[ key ] = newValue;
//
//			}
//
//		}
//
//	}

//	public toJSON  ( meta ) {
//
//		const isRoot = ( meta === undefined || typeof meta === 'string' );
//
//		if ( isRoot ) {
//
//			meta = {
//				textures: {},
//				images: {}
//			};
//
//		}
//
//		const data = {
//			metadata: {
//				version: 4.5,
//				type: 'Material',
//				generator: 'Material.toJSON'
//			}
//		};
//
//		// standard Material serialization
//		data.uuid = this.uuid;
//		data.type = this.type;
//
//		if ( this.name !== '' ) data.name = this.name;
//
//		if ( this.color && this.color.isColor ) data.color = this.color.getHex();
//
//		if ( this.roughness !== undefined ) data.roughness = this.roughness;
//		if ( this.metalness !== undefined ) data.metalness = this.metalness;
//
//		if ( this.sheen && this.sheen.isColor ) data.sheen = this.sheen.getHex();
//		if ( this.emissive && this.emissive.isColor ) data.emissive = this.emissive.getHex();
//		if ( this.emissiveIntensity && this.emissiveIntensity !== 1 ) data.emissiveIntensity = this.emissiveIntensity;
//
//		if ( this.specular && this.specular.isColor ) data.specular = this.specular.getHex();
//		if ( this.shininess !== undefined ) data.shininess = this.shininess;
//		if ( this.clearcoat !== undefined ) data.clearcoat = this.clearcoat;
//		if ( this.clearcoatRoughness !== undefined ) data.clearcoatRoughness = this.clearcoatRoughness;
//
//		if ( this.clearcoatMap && this.clearcoatMap.isTexture ) {
//
//			data.clearcoatMap = this.clearcoatMap.toJSON( meta ).uuid;
//
//		}
//
//		if ( this.clearcoatRoughnessMap && this.clearcoatRoughnessMap.isTexture ) {
//
//			data.clearcoatRoughnessMap = this.clearcoatRoughnessMap.toJSON( meta ).uuid;
//
//		}
//
//		if ( this.clearcoatNormalMap && this.clearcoatNormalMap.isTexture ) {
//
//			data.clearcoatNormalMap = this.clearcoatNormalMap.toJSON( meta ).uuid;
//			data.clearcoatNormalScale = this.clearcoatNormalScale.toArray();
//
//		}
//
//		if ( this.map && this.map.isTexture ) data.map = this.map.toJSON( meta ).uuid;
//		if ( this.matcap && this.matcap.isTexture ) data.matcap = this.matcap.toJSON( meta ).uuid;
//		if ( this.alphaMap && this.alphaMap.isTexture ) data.alphaMap = this.alphaMap.toJSON( meta ).uuid;
//		if ( this.lightMap && this.lightMap.isTexture ) data.lightMap = this.lightMap.toJSON( meta ).uuid;
//
//		if ( this.aoMap && this.aoMap.isTexture ) {
//
//			data.aoMap = this.aoMap.toJSON( meta ).uuid;
//			data.aoMapIntensity = this.aoMapIntensity;
//
//		}
//
//		if ( this.bumpMap && this.bumpMap.isTexture ) {
//
//			data.bumpMap = this.bumpMap.toJSON( meta ).uuid;
//			data.bumpScale = this.bumpScale;
//
//		}
//
//		if ( this.normalMap && this.normalMap.isTexture ) {
//
//			data.normalMap = this.normalMap.toJSON( meta ).uuid;
//			data.normalMapType = this.normalMapType;
//			data.normalScale = this.normalScale.toArray();
//
//		}
//
//		if ( this.displacementMap && this.displacementMap.isTexture ) {
//
//			data.displacementMap = this.displacementMap.toJSON( meta ).uuid;
//			data.displacementScale = this.displacementScale;
//			data.displacementBias = this.displacementBias;
//
//		}
//
//		if ( this.roughnessMap && this.roughnessMap.isTexture ) data.roughnessMap = this.roughnessMap.toJSON( meta ).uuid;
//		if ( this.metalnessMap && this.metalnessMap.isTexture ) data.metalnessMap = this.metalnessMap.toJSON( meta ).uuid;
//
//		if ( this.emissiveMap && this.emissiveMap.isTexture ) data.emissiveMap = this.emissiveMap.toJSON( meta ).uuid;
//		if ( this.specularMap && this.specularMap.isTexture ) data.specularMap = this.specularMap.toJSON( meta ).uuid;
//
//		if ( this.envMap && this.envMap.isTexture ) {
//
//			data.envMap = this.envMap.toJSON( meta ).uuid;
//			data.reflectivity = this.reflectivity; // Scale behind envMap
//			data.refractionRatio = this.refractionRatio;
//
//			if ( this.combine !== undefined ) data.combine = this.combine;
//			if ( this.envMapIntensity !== undefined ) data.envMapIntensity = this.envMapIntensity;
//
//		}
//
//		if ( this.gradientMap && this.gradientMap.isTexture ) {
//
//			data.gradientMap = this.gradientMap.toJSON( meta ).uuid;
//
//		}
//
//		if ( this.size !== undefined ) data.size = this.size;
//		if ( this.sizeAttenuation !== undefined ) data.sizeAttenuation = this.sizeAttenuation;
//
//		if ( this.blending !== NormalBlending ) data.blending = this.blending;
//		if ( this.flatShading === true ) data.flatShading = this.flatShading;
//		if ( this.side !== FrontSide ) data.side = this.side;
//		if ( this.vertexColors ) data.vertexColors = true;
//
//		if ( this.opacity < 1 ) data.opacity = this.opacity;
//		if ( this.transparent === true ) data.transparent = this.transparent;
//
//		data.depthFunc = this.depthFunc;
//		data.depthTest = this.depthTest;
//		data.depthWrite = this.depthWrite;
//
//		data.stencilWrite = this.stencilWrite;
//		data.stencilWriteMask = this.stencilWriteMask;
//		data.stencilFunc = this.stencilFunc;
//		data.stencilRef = this.stencilRef;
//		data.stencilFuncMask = this.stencilFuncMask;
//		data.stencilFail = this.stencilFail;
//		data.stencilZFail = this.stencilZFail;
//		data.stencilZPass = this.stencilZPass;
//
//		// rotation (SpriteMaterial)
//		if ( this.rotation && this.rotation !== 0 ) data.rotation = this.rotation;
//
//		if ( this.polygonOffset === true ) data.polygonOffset = true;
//		if ( this.polygonOffsetFactor !== 0 ) data.polygonOffsetFactor = this.polygonOffsetFactor;
//		if ( this.polygonOffsetUnits !== 0 ) data.polygonOffsetUnits = this.polygonOffsetUnits;
//
//		if ( this.linewidth && this.linewidth !== 1 ) data.linewidth = this.linewidth;
//		if ( this.dashSize !== undefined ) data.dashSize = this.dashSize;
//		if ( this.gapSize !== undefined ) data.gapSize = this.gapSize;
//		if ( this.scale !== undefined ) data.scale = this.scale;
//
//		if ( this.dithering === true ) data.dithering = true;
//
//		if ( this.alphaTest > 0 ) data.alphaTest = this.alphaTest;
//		if ( this.premultipliedAlpha === true ) data.premultipliedAlpha = this.premultipliedAlpha;
//
//		if ( this.wireframe === true ) data.wireframe = this.wireframe;
//		if ( this.wireframeLinewidth > 1 ) data.wireframeLinewidth = this.wireframeLinewidth;
//		if ( this.wireframeLinecap !== 'round' ) data.wireframeLinecap = this.wireframeLinecap;
//		if ( this.wireframeLinejoin !== 'round' ) data.wireframeLinejoin = this.wireframeLinejoin;
//
//		if ( this.morphTargets === true ) data.morphTargets = true;
//		if ( this.morphNormals === true ) data.morphNormals = true;
//		if ( this.skinning === true ) data.skinning = true;
//
//		if ( this.visible === false ) data.visible = false;
//
//		if ( this.toneMapped === false ) data.toneMapped = false;
//
//		if ( JSON.stringify( this.userData ) !== '{}' ) data.userData = this.userData;
//
//		// TODO: Copied from Object3D.toJSON
//
//		public extractFromCache( cache ) {
//
//			const values = [];
//
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
//		if ( isRoot ) {
//
//			const textures = extractFromCache( meta.textures );
//			const images = extractFromCache( meta.images );
//
//			if ( textures.length > 0 ) data.textures = textures;
//			if ( images.length > 0 ) data.images = images;
//
//		}
//
//		return data;
//
//	}

	public Material clone() {

		return new Material().copy(this);

	}

	public Material copy(Material source) {

		this._name = source._name;

		this._fog = source._fog;

		this._blending = source._blending;
		this._side = source._side;
		this._flatShading = source._flatShading;
		this._vertexColors = source._vertexColors;

		this._opacity = source._opacity;
		this._transparent = source._transparent;

		this._blendSrc = source._blendSrc;
		this._blendDst = source._blendDst;
		this._blendEquation = source._blendEquation;
		this._blendSrcAlpha = source._blendSrcAlpha;
		this._blendDstAlpha = source._blendDstAlpha;
		this._blendEquationAlpha = source._blendEquationAlpha;

		this._depthFunc = source._depthFunc;
		this._depthTest = source._depthTest;
		this._depthWrite = source._depthWrite;

		this._stencilWriteMask = source._stencilWriteMask;
		this._stencilFunc = source._stencilFunc;
		this._stencilRef = source._stencilRef;
		this._stencilFuncMask = source._stencilFuncMask;
		this._stencilFail = source._stencilFail;
		this._stencilZFail = source._stencilZFail;
		this._stencilZPass = source._stencilZPass;
		this._stencilWrite = source._stencilWrite;

		Plane[] srcPlanes = source._clippingPlanes;
		Plane[] dstPlanes = null;

		if (srcPlanes != null) {

			int n = srcPlanes.length;
			dstPlanes = new Plane[n];

			for (int i = 0; i != n; ++i) {

				dstPlanes[i] = srcPlanes[i].clone();

			}

		}

		this._clippingPlanes = dstPlanes;
		this._clipIntersection = source._clipIntersection;
		this._clipShadows = source._clipShadows;

		this._shadowSide = source._shadowSide;

		this._colorWrite = source._colorWrite;

		this._precision = source._precision;

		this._polygonOffset = source._polygonOffset;
		this._polygonOffsetFactor = source._polygonOffsetFactor;
		this._polygonOffsetUnits = source._polygonOffsetUnits;

		this._dithering = source._dithering;

		this._alphaTest = source._alphaTest;
		this._premultipliedAlpha = source._premultipliedAlpha;

		this._visible = source._visible;

		this._toneMapped = source._toneMapped;

//		this._userData = JSON.parse( JSON.stringify( source._userData ) );

		return this;

	}

	public void dispose  () {
		this.dispatchEvent( new Event(this, "dispose") );
	}

}