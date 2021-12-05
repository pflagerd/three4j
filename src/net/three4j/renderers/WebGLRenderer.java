package net.three4j.renderers;

import static net.three4j.Document.document;
import static net.three4j.constants.LinearEncoding;
import static net.three4j.constants.NoToneMapping;

import net.three4j.HTMLCanvasElement;
import net.three4j.cameras.Camera;
import net.three4j.math.Frustum;
import net.three4j.math.Matrix4;
import net.three4j.math.Vector4;
import net.three4j.scenes.Scene;

public class WebGLRenderer {

	class WebGLRenderingContext {

	}

	private HTMLCanvasElement _canvas = createCanvasElement();

	public HTMLCanvasElement canvas() {
		return _canvas;
	}

	public WebGLRenderer canvas(HTMLCanvasElement canvas) {
		_canvas = canvas;
		return this;
	}

	private HTMLCanvasElement createCanvasElement() {

		_canvas = new HTMLCanvasElement(document.createElementNS("http://www.w3.org/1999/xhtml", "canvas"));
		((HTMLCanvasElement) _canvas).style.display = "block";
		return (HTMLCanvasElement) _canvas;

	}

	private WebGLRenderingContext _context;

	public WebGLRenderingContext context() {
		return _context;
	}

	public WebGLRenderer context(WebGLRenderingContext webGLRenderingContext) {
		_context = webGLRenderingContext;
		return this;
	}

	private boolean _alpha;

	public boolean alpha() {
		return _alpha;
	}

	public WebGLRenderer alpha(boolean alpha) {
		_alpha = alpha;
		return this;
	}

	private boolean _depth = true;

	public boolean depth() {
		return _depth;
	}

	public WebGLRenderer depth(boolean depth) {
		this._depth = depth;
		return this;
	}

	private boolean _stencil = true;

	public boolean stencil() {
		return _stencil;
	}

	public WebGLRenderer stencil(boolean stencil) {
		this._stencil = stencil;
		return this;
	}

	private boolean _antialias;

	public boolean antialias() {
		return _antialias;
	}

	public WebGLRenderer antialias(boolean antialias) {
		this._antialias = antialias;
		return this;
	}

	private boolean _premultipliedAlpha = true;

	public boolean premultipliedAlpha() {
		return _premultipliedAlpha;
	}

	public WebGLRenderer premultipliedAlpha(boolean premultipliedAlpha) {
		this._premultipliedAlpha = premultipliedAlpha;
		return this;
	}

	private boolean _preserveDrawingBuffer;

	public boolean preserveDrawingBuffer() {
		return _preserveDrawingBuffer;
	}

	public WebGLRenderer preserveDrawingBuffer(boolean preserveDrawingBuffer) {
		this._preserveDrawingBuffer = preserveDrawingBuffer;
		return this;
	}

	private String _powerPreference = "default";

	public String powerPreference() {
		return _powerPreference;
	}

	public WebGLRenderer powerPreference(String powerPreference) {
		this._powerPreference = powerPreference;
		return this;
	}

	private boolean _failIfMajorPerformanceCaveat;

	public boolean failIfMajorPerformanceCaveat() {
		return _failIfMajorPerformanceCaveat;
	}

	public WebGLRenderer failIfMajorPerformanceCaveat(boolean failIfMajorPerformanceCaveat) {
		this._failIfMajorPerformanceCaveat = failIfMajorPerformanceCaveat;
		return this;
	}

	Object currentRenderList = null; // DPP: Need more specific class than Object
	Object currentRenderState = null; // DPP: Need more specific class than Object

//	// render() can be called from within a callback triggered by another render.
//	// We track this so that the nested render call gets its state isolated from the parent render call.

//	const renderStateStack = [];

	private HTMLCanvasElement _domElement = (HTMLCanvasElement) _canvas;

	class WebGLDebug {
		public WebGLDebug(boolean checkShaderErrors) {
			this._checkShaderErrors = checkShaderErrors;
		}

		private boolean _checkShaderErrors;

		public boolean checkShaderErrors() {
			return _checkShaderErrors;
		}

		public WebGLDebug checkShaderErrors(boolean checkShaderErrors) {
			this._checkShaderErrors = checkShaderErrors;
			return this;
		}
	}

	public final WebGLDebug debug = new WebGLDebug(true);

	private boolean _autoClear;

	public boolean autoClear() {
		return _autoClear;
	}

	public WebGLRenderer autoClear(boolean autoClear) {
		this._autoClear = autoClear;
		return this;
	}

	private boolean _autoClearColor;

	public boolean autoClearColor() {
		return _autoClearColor;
	}

	public WebGLRenderer autoClearColor(boolean autoClearColor) {
		this._autoClearColor = autoClearColor;
		return this;
	}

	private boolean _autoClearDepth;

	public boolean autoClearDepth() {
		return _autoClearDepth;
	}

	public WebGLRenderer autoClearDepth(boolean autoClearDepth) {
		this._autoClearDepth = autoClearDepth;
		return this;
	}

	private boolean _autoClearStencil;

	public boolean autoClearStencil() {
		return _autoClearStencil;
	}

	public WebGLRenderer autoClearStencil(boolean autoClearStencil) {
		this._autoClearStencil = autoClearStencil;
		return this;
	}

	private boolean _sortObjects;

	public boolean sortObjects() {
		return _sortObjects;
	}

	public WebGLRenderer sortObjects(boolean sortObjects) {
		this._sortObjects = sortObjects;
		return this;
	}

//	this.clippingPlanes = [];

	private boolean _localClippingEnabled;

	public boolean localClippingEnabled() {
		return _localClippingEnabled;
	}

	public WebGLRenderer localClippingEnabled(boolean localClippingEnabled) {
		this._localClippingEnabled = localClippingEnabled;
		return this;
	}

	private double _gammaFactor = 2.0;

	public double gammaFactor() {
		return _gammaFactor;
	}

	public WebGLRenderer gammaFactor(double gammaFactor) {
		this._gammaFactor = gammaFactor;
		return this;
	}

	private int _outputEncoding = LinearEncoding;

	public int outputEncoding() {
		return _outputEncoding;
	}

	public WebGLRenderer outputEncoding(int outputEncoding) {
		this._outputEncoding = outputEncoding;
		return this;
	}

	private boolean _physicallyCorrectLights;

	public boolean physicallyCorrectLights() {
		return _physicallyCorrectLights;
	}

	public WebGLRenderer physicallyCorrectLights(boolean physicallyCorrectLights) {
		this._physicallyCorrectLights = physicallyCorrectLights;
		return this;
	}

	private int _toneMapping = NoToneMapping;

	public int toneMapping() {
		return _toneMapping;
	}

	public WebGLRenderer toneMapping(int toneMapping) {
		this._toneMapping = toneMapping;
		return this;
	}

	private double _toneMappingExposure = 1.0;

	public double toneMappingExposure() {
		return _toneMappingExposure;
	}

	public WebGLRenderer toneMappingExposure(double toneMappingExposure) {
		this._toneMappingExposure = toneMappingExposure;
		return this;
	}

	private int _maxMorphTargets = 8;

	public int maxMorphTargets() {
		return _maxMorphTargets;
	}

	public WebGLRenderer maxMorphTargets(int maxMorphTargets) {
		this._maxMorphTargets = maxMorphTargets;
		return this;
	}

	private int _maxMorphNormals = 4;

	public int maxMorphNormals() {
		return _maxMorphNormals;
	}

	public WebGLRenderer maxMorphNormals(int maxMorphNormals) {
		this._maxMorphNormals = maxMorphNormals;
		return this;
	}

	// const _this = this;

	private boolean _isContextLost;

	public boolean isContextLost() {
		return _isContextLost;
	}

	public WebGLRenderer isContextLost(boolean isContextLost) {
		this._isContextLost = isContextLost;
		return this;
	}

// internal state cache

	// let _framebuffer = null;
	private int _currentActiveCubeFace = 0;

	public int currentActiveCubeFace() {
		return _currentActiveCubeFace;
	}

	public WebGLRenderer currentActiveCubeFace(int currentActiveCubeFace) {
		this._currentActiveCubeFace = currentActiveCubeFace;
		return this;
	}

	private int _currentActiveMipmapLevel;

	public int currentActiveMipmapLevel() {
		return _currentActiveMipmapLevel;
	}

	public WebGLRenderer currentActiveMipmapLevel(int currentActiveMipmapLevel) {
		this._currentActiveMipmapLevel = currentActiveMipmapLevel;
		return this;
	}

//	let _currentRenderTarget = null;
//	let _currentFramebuffer = null;
	private int _CurrentMaterialId;

	public int CurrentMaterialId() {
		return _CurrentMaterialId;
	}

	public WebGLRenderer CurrentMaterialId(int CurrentMaterialId) {
		this._CurrentMaterialId = CurrentMaterialId;
		return this;
	}

//	let _currentCamera = null;

	private Vector4 _currentViewport = new Vector4();

	public Vector4 currentViewport() {
		return _currentViewport;
	}

	public WebGLRenderer currentViewport(Vector4 currentViewport) {
		this._currentViewport = currentViewport;
		return this;
	}

	private Vector4 _currentScissor = new Vector4();

	public Vector4 currentScissor() {
		return _currentScissor;
	}

	public WebGLRenderer currentScissor(Vector4 currentScissor) {
		this._currentScissor = currentScissor;
		return this;
	}

	// let _currentScissorTest = null;

	int _width = _canvas.width();
	int _height = _canvas.height();

	private double _pixelRatio;

	public double pixelRatio() {
		return _pixelRatio;
	}

	public WebGLRenderer pixelRatio(double pixelRatio) {
		this._pixelRatio = pixelRatio;
		return this;
	}

//	let _opaqueSort = null;
//	let _transparentSort = null;
//

	private Vector4 _viewPort = new Vector4(0, 0, _width, _height);

	public Vector4 viewPort() {
		return _viewPort;
	}

	public WebGLRenderer viewPort(Vector4 viewPort) {
		this._viewPort = viewPort;
		return this;
	}

	private Vector4 _scissor = new Vector4();

	public Vector4 scissor() {
		return _scissor;
	}

	public WebGLRenderer scissor(Vector4 scissor) {
		this._scissor = scissor;
		return this;
	}

	private boolean _scissorTest;

	public boolean scissorTest() {
		return _scissorTest;
	}

	public WebGLRenderer scissorTest(boolean scissorTest) {
		this._scissorTest = scissorTest;
		return this;
	}

	private Frustum _frustum = new Frustum();

	public Frustum frustum() {
		return _frustum;
	}

	public WebGLRenderer frustum(Frustum frustum) {
		this._frustum = frustum;
		return this;
	}

	private boolean _clippingEnabled;

	public boolean clippingEnabled() {
		return _clippingEnabled;
	}

	public WebGLRenderer clippingEnabled(boolean clippingEnabled) {
		this._clippingEnabled = clippingEnabled;
		return this;
	}

	// DPP: Duplicate?
	// let _localClippingEnabled = false;

	private Matrix4 _projScreenMatrix = new Matrix4();

	public Matrix4 projScreenMatrix() {
		return _projScreenMatrix;
	}

	public WebGLRenderer projScreenMatrix(Matrix4 projScreenMatrix) {
		this._projScreenMatrix = projScreenMatrix;
		return this;
	}

//	const _vector3 = new Vector3();
//
//	const _emptyScene = { background: null, fog: null, environment: null, overrideMaterial: null, isScene: true };
//
//	public getTargetPixelRatio() {
//
//		return _currentRenderTarget === null ? _pixelRatio : 1;
//
//	}
//
//	// initialize

	WebGLRenderingContext _gl = _context;

//	public getContext( contextNames, contextAttributes ) {
//
//		for ( let i = 0; i < contextNames.length; i ++ ) {
//
//			const contextName = contextNames[ i ];
//			const context = _canvas.getContext( contextName, contextAttributes );
//			if ( context !== null ) return context;
//
//		}
//
//		return null;
//
//	}
//
	public WebGLRenderer() {

		try {

//		const contextAttributes = {
//			alpha: _alpha,
//			depth: _depth,
//			stencil: _stencil,
//			antialias: _antialias,
//			premultipliedAlpha: _premultipliedAlpha,
//			preserveDrawingBuffer: _preserveDrawingBuffer,
//			powerPreference: _powerPreference,
//			failIfMajorPerformanceCaveat: _failIfMajorPerformanceCaveat
//		};
//
//		// event listeners must be registered before WebGL context is created, see #12753
//
//		_canvas.addEventListener( 'webglcontextlost', onContextLost, false );
//		_canvas.addEventListener( 'webglcontextrestored', onContextRestore, false );
//
//		if ( _gl === null ) {
//
//			const contextNames = [ 'webgl2', 'webgl', 'experimental-webgl' ];
//
//			if ( _this.isWebGL1Renderer === true ) {
//
//				contextNames.shift();
//
//			}
//
//			_gl = getContext( contextNames, contextAttributes );
//
//			if ( _gl === null ) {
//
//				if ( getContext( contextNames ) ) {
//
//					throw new Error( 'Error creating WebGL context with your selected attributes.' );
//
//				} else {
//
//					throw new Error( 'Error creating WebGL context.' );
//
//				}
//
//			}
//
//		}
//
//		// Some experimental-webgl implementations do not have getShaderPrecisionFormat
//
//		if ( _gl.getShaderPrecisionFormat === undefined ) {
//
//			_gl.getShaderPrecisionFormat = function () {
//
//				return { 'rangeMin': 1, 'rangeMax': 1, 'precision': 1 };
//
//			};
//
//		}
//
		} catch (Exception exception) {

			throw new RuntimeException("THREE.WebGLRenderer: " + exception.getMessage());

		}
	}
//
//	let extensions, capabilities, state, info;
//	let properties, textures, cubemaps, attributes, geometries, objects;
//	let programCache, materials, renderLists, renderStates, clipping;
//
//	let background, morphtargets, bufferRenderer, indexedBufferRenderer;
//
//	let utils, bindingStates;
//
//	public initGLContext() {
//
//		extensions = new WebGLExtensions( _gl );
//
//		capabilities = new WebGLCapabilities( _gl, extensions, parameters );
//
//		if ( capabilities.isWebGL2 === false ) {
//
//			extensions.get( 'WEBGL_depth_texture' );
//			extensions.get( 'OES_texture_float' );
//			extensions.get( 'OES_texture_half_float' );
//			extensions.get( 'OES_texture_half_float_linear' );
//			extensions.get( 'OES_standard_derivatives' );
//			extensions.get( 'OES_element_index_uint' );
//			extensions.get( 'OES_vertex_array_object' );
//			extensions.get( 'ANGLE_instanced_arrays' );
//
//		}
//
//		extensions.get( 'OES_texture_float_linear' );
//
//		utils = new WebGLUtils( _gl, extensions, capabilities );
//
//		state = new WebGLState( _gl, extensions, capabilities );
//		state.scissor( _currentScissor.copy( _scissor ).multiplyScalar( _pixelRatio ).floor() );
//		state.viewport( _currentViewport.copy( _viewport ).multiplyScalar( _pixelRatio ).floor() );
//
//		info = new WebGLInfo( _gl );
//		properties = new WebGLProperties();
//		textures = new WebGLTextures( _gl, extensions, state, properties, capabilities, utils, info );
//		cubemaps = new WebGLCubeMaps( _this );
//		attributes = new WebGLAttributes( _gl, capabilities );
//		bindingStates = new WebGLBindingStates( _gl, extensions, attributes, capabilities );
//		geometries = new WebGLGeometries( _gl, attributes, info, bindingStates );
//		objects = new WebGLObjects( _gl, geometries, attributes, info );
//		morphtargets = new WebGLMorphtargets( _gl );
//		clipping = new WebGLClipping( properties );
//		programCache = new WebGLPrograms( _this, cubemaps, extensions, capabilities, bindingStates, clipping );
//		materials = new WebGLMaterials( properties );
//		renderLists = new WebGLRenderLists( properties );
//		renderStates = new WebGLRenderStates( extensions, capabilities );
//		background = new WebGLBackground( _this, cubemaps, state, objects, _premultipliedAlpha );
//
//		bufferRenderer = new WebGLBufferRenderer( _gl, extensions, info, capabilities );
//		indexedBufferRenderer = new WebGLIndexedBufferRenderer( _gl, extensions, info, capabilities );
//
//		info.programs = programCache.programs;
//
//		_this.capabilities = capabilities;
//		_this.extensions = extensions;
//		_this.properties = properties;
//		_this.renderLists = renderLists;
//		_this.state = state;
//		_this.info = info;
//
//	}
//
//	initGLContext();
//
//	// xr
//
//	const xr = new WebXRManager( _this, _gl );
//
//	private int _xr = xr;
//
//	// shadow map
//
//	const shadowMap = new WebGLShadowMap( _this, objects, capabilities.maxTextureSize );
//
//	private int _shadowMap = shadowMap;
//
//	// API
//
//	this.getContext = function () {
//
//		return _gl;
//
//	};
//
//	this.getContextAttributes = function () {
//
//		return _gl.getContextAttributes();
//
//	};
//
//	this.forceContextLoss = function () {
//
//		const extension = extensions.get( 'WEBGL_lose_context' );
//		if ( extension ) extension.loseContext();
//
//	};
//
//	this.forceContextRestore = function () {
//
//		const extension = extensions.get( 'WEBGL_lose_context' );
//		if ( extension ) extension.restoreContext();
//
//	};
//
//	this.getPixelRatio = function () {
//
//		return _pixelRatio;
//
//	};
//
//	this.setPixelRatio = function ( value ) {
//
//		if ( value === undefined ) return;
//
//		_pixelRatio = value;
//
//		this.setSize( _width, _height, false );
//
//	};
//
//	this.getSize = function ( target ) {
//
//		if ( target === undefined ) {
//
//			console.warn( 'WebGLRenderer: .getsize() now requires a Vector2 as an argument' );
//
//			target = new Vector2();
//
//		}
//
//		return target.set( _width, _height );
//
//	};

	public WebGLRenderer size(int width, int height) {
		return setSize(width, height, false);
	}

	public WebGLRenderer size(int width, int height, boolean updateStyle) {
		return setSize(width, height, updateStyle);
	}

	public WebGLRenderer setSize(int width, int height) {
		return setSize(width, height, true);
	}

	public WebGLRenderer setSize(int width, int height, boolean updateStyle) {

//		if ( xr.isPresenting ) {
//
//			console.warn( "THREE.WebGLRenderer: Can\'t change size while VR device is presenting." );
//			return this;
//
//		}
//
//		_width = width;
//		_height = height;
//
//		_canvas.width = Math.floor( width * _pixelRatio );
//		_canvas.height = Math.floor( height * _pixelRatio );
//
//		if ( updateStyle !== false ) {
//
//			_canvas.style.width = width + "px";
//			_canvas.style.height = height + "px";
//
//		}
//
//		this.setViewport( 0, 0, width, height );
		return this;

	};

//	this.getDrawingBufferSize = function ( target ) {
//
//		if ( target === undefined ) {
//
//			console.warn( 'WebGLRenderer: .getdrawingBufferSize() now requires a Vector2 as an argument' );
//
//			target = new Vector2();
//
//		}
//
//		return target.set( _width * _pixelRatio, _height * _pixelRatio ).floor();
//
//	};
//
//	this.setDrawingBufferSize = function ( width, height, pixelRatio ) {
//
//		_width = width;
//		_height = height;
//
//		_pixelRatio = pixelRatio;
//
//		_canvas.width = Math.floor( width * pixelRatio );
//		_canvas.height = Math.floor( height * pixelRatio );
//
//		this.setViewport( 0, 0, width, height );
//
//	};
//
//	this.getCurrentViewport = function ( target ) {
//
//		if ( target === undefined ) {
//
//			console.warn( 'WebGLRenderer: .getCurrentViewport() now requires a Vector4 as an argument' );
//
//			target = new Vector4();
//
//		}
//
//		return target.copy( _currentViewport );
//
//	};
//
//	this.getViewport = function ( target ) {
//
//		return target.copy( _viewport );
//
//	};
//
//	this.setViewport = function ( x, y, width, height ) {
//
//		if ( x.isVector4 ) {
//
//			_viewport.set( x.x, x.y, x.z, x.w );
//
//		} else {
//
//			_viewport.set( x, y, width, height );
//
//		}
//
//		state.viewport( _currentViewport.copy( _viewport ).multiplyScalar( _pixelRatio ).floor() );
//
//	};
//
//	this.getScissor = function ( target ) {
//
//		return target.copy( _scissor );
//
//	};
//
//	this.setScissor = function ( x, y, width, height ) {
//
//		if ( x.isVector4 ) {
//
//			_scissor.set( x.x, x.y, x.z, x.w );
//
//		} else {
//
//			_scissor.set( x, y, width, height );
//
//		}
//
//		state.scissor( _currentScissor.copy( _scissor ).multiplyScalar( _pixelRatio ).floor() );
//
//	};
//
//	this.getScissorTest = function () {
//
//		return _scissorTest;
//
//	};
//
//	this.setScissorTest = function ( boolean ) {
//
//		state.setScissorTest( _scissorTest = boolean );
//
//	};
//
//	this.setOpaqueSort = function ( method ) {
//
//		_opaqueSort = method;
//
//	};
//
//	this.setTransparentSort = function ( method ) {
//
//		_transparentSort = method;
//
//	};
//
//	// Clearing
//
//	this.getClearColor = function ( target ) {
//
//		if ( target === undefined ) {
//
//			console.warn( 'WebGLRenderer: .getClearColor() now requires a Color as an argument' );
//
//			target = new Color();
//
//		}
//
//		return target.copy( background.getClearColor() );
//
//	};
//
//	this.setClearColor = function () {
//
//		background.setClearColor.apply( background, arguments );
//
//	};
//
//	this.getClearAlpha = function () {
//
//		return background.getClearAlpha();
//
//	};
//
//	this.setClearAlpha = function () {
//
//		background.setClearAlpha.apply( background, arguments );
//
//	};
//
//	this.clear = function ( color, depth, stencil ) {
//
//		double bits = 0;
//
//		if ( color === undefined || color ) bits |= _gl.COLOR_BUFFER_BIT;
//		if ( depth === undefined || depth ) bits |= _gl.DEPTH_BUFFER_BIT;
//		if ( stencil === undefined || stencil ) bits |= _gl.STENCIL_BUFFER_BIT;
//
//		_gl.clear( bits );
//
//	};
//
//	this.clearColor = function () {
//
//		this.clear( true, false, false );
//
//	};
//
//	this.clearDepth = function () {
//
//		this.clear( false, true, false );
//
//	};
//
//	this.clearStencil = function () {
//
//		this.clear( false, false, true );
//
//	};
//
//	//
//
//	this.dispose = function () {
//
//		_canvas.removeEventListener( 'webglcontextlost', onContextLost, false );
//		_canvas.removeEventListener( 'webglcontextrestored', onContextRestore, false );
//
//		renderLists.dispose();
//		renderStates.dispose();
//		properties.dispose();
//		cubemaps.dispose();
//		objects.dispose();
//		bindingStates.dispose();
//
//		xr.dispose();
//
//		animation.stop();
//
//	};
//
//	// Events
//
//	public onContextLost( event ) {
//
//		event.preventDefault();
//
//		console.log( 'THREE.WebGLRenderer: Context Lost.' );
//
//		_isContextLost = true;
//
//	}
//
//	public onContextRestore( /* event */ ) {
//
//		console.log( 'THREE.WebGLRenderer: Context Restored.' );
//
//		_isContextLost = false;
//
//		initGLContext();
//
//	}
//
//	public onMaterialDispose( event ) {
//
//		const material = event.target;
//
//		material.removeEventListener( 'dispose', onMaterialDispose );
//
//		deallocateMaterial( material );
//
//	}
//
//	// Buffer deallocation
//
//	public deallocateMaterial( material ) {
//
//		releaseMaterialProgramReference( material );
//
//		properties.remove( material );
//
//	}
//
//
//	public releaseMaterialProgramReference( material ) {
//
//		const programInfo = properties.get( material ).program;
//
//		if ( programInfo !== undefined ) {
//
//			programCache.releaseProgram( programInfo );
//
//		}
//
//	}
//
//	// Buffer rendering
//
//	public renderObjectImmediate( object, program ) {
//
//		object.render( function ( object ) {
//
//			_this.renderBufferImmediate( object, program );
//
//		} );
//
//	}
//
//	this.renderBufferImmediate = function ( object, program ) {
//
//		bindingStates.initAttributes();
//
//		const buffers = properties.get( object );
//
//		if ( object.hasPositions && ! buffers.position ) buffers.position = _gl.createBuffer();
//		if ( object.hasNormals && ! buffers.normal ) buffers.normal = _gl.createBuffer();
//		if ( object.hasUvs && ! buffers.uv ) buffers.uv = _gl.createBuffer();
//		if ( object.hasColors && ! buffers.color ) buffers.color = _gl.createBuffer();
//
//		const programAttributes = program.getAttributes();
//
//		if ( object.hasPositions ) {
//
//			_gl.bindBuffer( _gl.ARRAY_BUFFER, buffers.position );
//			_gl.bufferData( _gl.ARRAY_BUFFER, object.positionArray, _gl.DYNAMIC_DRAW );
//
//			bindingStates.enableAttribute( programAttributes.position );
//			_gl.vertexAttribPointer( programAttributes.position, 3, _gl.FLOAT, false, 0, 0 );
//
//		}
//
//		if ( object.hasNormals ) {
//
//			_gl.bindBuffer( _gl.ARRAY_BUFFER, buffers.normal );
//			_gl.bufferData( _gl.ARRAY_BUFFER, object.normalArray, _gl.DYNAMIC_DRAW );
//
//			bindingStates.enableAttribute( programAttributes.normal );
//			_gl.vertexAttribPointer( programAttributes.normal, 3, _gl.FLOAT, false, 0, 0 );
//
//		}
//
//		if ( object.hasUvs ) {
//
//			_gl.bindBuffer( _gl.ARRAY_BUFFER, buffers.uv );
//			_gl.bufferData( _gl.ARRAY_BUFFER, object.uvArray, _gl.DYNAMIC_DRAW );
//
//			bindingStates.enableAttribute( programAttributes.uv );
//			_gl.vertexAttribPointer( programAttributes.uv, 2, _gl.FLOAT, false, 0, 0 );
//
//		}
//
//		if ( object.hasColors ) {
//
//			_gl.bindBuffer( _gl.ARRAY_BUFFER, buffers.color );
//			_gl.bufferData( _gl.ARRAY_BUFFER, object.colorArray, _gl.DYNAMIC_DRAW );
//
//			bindingStates.enableAttribute( programAttributes.color );
//			_gl.vertexAttribPointer( programAttributes.color, 3, _gl.FLOAT, false, 0, 0 );
//
//		}
//
//		bindingStates.disableUnusedAttributes();
//
//		_gl.drawArrays( _gl.TRIANGLES, 0, object.count );
//
//		object.count = 0;
//
//	};
//
//	this.renderBufferDirect = function ( camera, scene, geometry, material, object, group ) {
//
//		if ( scene === null ) scene = _emptyScene; // renderBufferDirect second parameter used to be fog (could be null)
//
//		const frontFaceCW = ( object.isMesh && object.matrixWorld.determinant() < 0 );
//
//		const program = setProgram( camera, scene, material, object );
//
//		state.setMaterial( material, frontFaceCW );
//
//		//
//
//		let index = geometry.index;
//		const position = geometry.attributes.position;
//
//		//
//
//		if ( index === null ) {
//
//			if ( position === undefined || position.count === 0 ) return;
//
//		} else if ( index.count === 0 ) {
//
//			return;
//
//		}
//
//		//
//
//		double rangeFactor = 1;
//
//		if ( material.wireframe === true ) {
//
//			index = geometries.getWireframeAttribute( geometry );
//			rangeFactor = 2;
//
//		}
//
//		if ( material.morphTargets || material.morphNormals ) {
//
//			morphtargets.update( object, geometry, material, program );
//
//		}
//
//		bindingStates.setup( object, material, program, geometry, index );
//
//		let attribute;
//		let renderer = bufferRenderer;
//
//		if ( index !== null ) {
//
//			attribute = attributes.get( index );
//
//			renderer = indexedBufferRenderer;
//			renderer.setIndex( attribute );
//
//		}
//
//		//
//
//		const dataCount = ( index !== null ) ? index.count : position.count;
//
//		const rangeStart = geometry.drawRange.start * rangeFactor;
//		const rangeCount = geometry.drawRange.count * rangeFactor;
//
//		const groupStart = group !== null ? group.start * rangeFactor : 0;
//		const groupCount = group !== null ? group.count * rangeFactor : Infinity;
//
//		const drawStart = Math.max( rangeStart, groupStart );
//		const drawEnd = Math.min( dataCount, rangeStart + rangeCount, groupStart + groupCount ) - 1;
//
//		const drawCount = Math.max( 0, drawEnd - drawStart + 1 );
//
//		if ( drawCount === 0 ) return;
//
//		//
//
//		if ( object.isMesh ) {
//
//			if ( material.wireframe === true ) {
//
//				state.setLineWidth( material.wireframeLinewidth * getTargetPixelRatio() );
//				renderer.setMode( _gl.LINES );
//
//			} else {
//
//				renderer.setMode( _gl.TRIANGLES );
//
//			}
//
//		} else if ( object.isLine ) {
//
//			let lineWidth = material.linewidth;
//
//			if ( lineWidth === undefined ) lineWidth = 1; // Not using Line*Material
//
//			state.setLineWidth( lineWidth * getTargetPixelRatio() );
//
//			if ( object.isLineSegments ) {
//
//				renderer.setMode( _gl.LINES );
//
//			} else if ( object.isLineLoop ) {
//
//				renderer.setMode( _gl.LINE_LOOP );
//
//			} else {
//
//				renderer.setMode( _gl.LINE_STRIP );
//
//			}
//
//		} else if ( object.isPoints ) {
//
//			renderer.setMode( _gl.POINTS );
//
//		} else if ( object.isSprite ) {
//
//			renderer.setMode( _gl.TRIANGLES );
//
//		}
//
//		if ( object.isInstancedMesh ) {
//
//			renderer.renderInstances( drawStart, drawCount, object.count );
//
//		} else if ( geometry.isInstancedBufferGeometry ) {
//
//			const instanceCount = Math.min( geometry.instanceCount, geometry._maxInstanceCount );
//
//			renderer.renderInstances( drawStart, drawCount, instanceCount );
//
//		} else {
//
//			renderer.render( drawStart, drawCount );
//
//		}
//
//	};
//
//	// Compile
//
//	this.compile = function ( scene, camera ) {
//
//		currentRenderState = renderStates.get( scene );
//		currentRenderState.init();
//
//		scene.traverseVisible( function ( object ) {
//
//			if ( object.isLight && object.layers.test( camera.layers ) ) {
//
//				currentRenderState.pushLight( object );
//
//				if ( object.castShadow ) {
//
//					currentRenderState.pushShadow( object );
//
//				}
//
//			}
//
//		} );
//
//		currentRenderState.setupLights();
//
//		const compiled = new WeakMap();
//
//		scene.traverse( function ( object ) {
//
//			const material = object.material;
//
//			if ( material ) {
//
//				if ( Array.isArray( material ) ) {
//
//					for ( let i = 0; i < material.length; i ++ ) {
//
//						const material2 = material[ i ];
//
//						if ( compiled.has( material2 ) === false ) {
//
//							initMaterial( material2, scene, object );
//							compiled.set( material2 );
//
//						}
//
//					}
//
//				} else if ( compiled.has( material ) === false ) {
//
//					initMaterial( material, scene, object );
//					compiled.set( material );
//
//				}
//
//			}
//
//		} );
//
//	};
//
//	// Animation Loop
//
//	let onAnimationFrameCallback = null;
//
//	public onAnimationFrame( time ) {
//
//		if ( xr.isPresenting ) return;
//		if ( onAnimationFrameCallback ) onAnimationFrameCallback( time );
//
//	}
//
//	const animation = new WebGLAnimation();
//	animation.setAnimationLoop( onAnimationFrame );
//
//	if ( typeof window !== 'undefined' ) animation.setContext( window );
//
	public interface AnimationCallback {
		public void callback(long time);
	}

	public WebGLRenderer setAnimationLoop(AnimationCallback callback) {

//		onAnimationFrameCallback = callback;
//		xr.setAnimationLoop( callback );
//
//		( callback === null ) ? animation.stop() : animation.start();
		return this;

	}

//
//	// Rendering
//
	public void render(Scene scene, Camera camera) {

//		let renderTarget, forceClear;
//
//		if ( arguments[ 2 ] !== undefined ) {
//
//			console.warn( 'THREE.WebGLRenderer.render(): the renderTarget argument has been removed. Use .setRenderTarget() instead.' );
//			renderTarget = arguments[ 2 ];
//
//		}
//
//		if ( arguments[ 3 ] !== undefined ) {
//
//			console.warn( 'THREE.WebGLRenderer.render(): the forceClear argument has been removed. Use .clear() instead.' );
//			forceClear = arguments[ 3 ];
//
//		}
//
//		if ( camera !== undefined && camera.isCamera !== true ) {
//
//			console.error( 'THREE.WebGLRenderer.render: camera is not an instance of THREE.Camera.' );
//			return;
//
//		}
//
//		if ( _isContextLost === true ) return;
//
//		// reset caching for this frame
//
//		bindingStates.resetDefaultState();
//		_currentMaterialId = - 1;
//		_currentCamera = null;
//
//		// update scene graph
//
//		if ( scene.autoUpdate === true ) scene.updateMatrixWorld();
//
//		// update camera matrices and frustum
//
//		if ( camera.parent === null ) camera.updateMatrixWorld();
//
//		if ( xr.enabled === true && xr.isPresenting === true ) {
//
//			camera = xr.getCamera( camera );
//
//		}
//
//		//
//		if ( scene.isScene === true ) scene.onBeforeRender( _this, scene, camera, renderTarget || _currentRenderTarget );
//
//		currentRenderState = renderStates.get( scene, renderStateStack.length );
//		currentRenderState.init();
//
//		renderStateStack.push( currentRenderState );
//
//		_projScreenMatrix.multiplyMatrices( camera.projectionMatrix, camera.matrixWorldInverse );
//		_frustum.setFromProjectionMatrix( _projScreenMatrix );
//
//		_localClippingEnabled = this.localClippingEnabled;
//		_clippingEnabled = clipping.init( this.clippingPlanes, _localClippingEnabled, camera );
//
//		currentRenderList = renderLists.get( scene, camera );
//		currentRenderList.init();
//
//		projectObject( scene, camera, 0, _this.sortObjects );
//
//		currentRenderList.finish();
//
//		if ( _this.sortObjects === true ) {
//
//			currentRenderList.sort( _opaqueSort, _transparentSort );
//
//		}
//
//		//
//
//		if ( _clippingEnabled === true ) clipping.beginShadows();
//
//		const shadowsArray = currentRenderState.state.shadowsArray;
//
//		shadowMap.render( shadowsArray, scene, camera );
//
//		currentRenderState.setupLights();
//		currentRenderState.setupLightsView( camera );
//
//		if ( _clippingEnabled === true ) clipping.endShadows();
//
//		//
//
//		if ( this.info.autoReset === true ) this.info.reset();
//
//		if ( renderTarget !== undefined ) {
//
//			this.setRenderTarget( renderTarget );
//
//		}
//
//		//
//
//		background.render( currentRenderList, scene, camera, forceClear );
//
//		// render scene
//
//		const opaqueObjects = currentRenderList.opaque;
//		const transparentObjects = currentRenderList.transparent;
//
//		if ( opaqueObjects.length > 0 ) renderObjects( opaqueObjects, scene, camera );
//		if ( transparentObjects.length > 0 ) renderObjects( transparentObjects, scene, camera );
//
//		//
//
//		if ( scene.isScene === true ) scene.onAfterRender( _this, scene, camera );
//
//		//
//
//		if ( _currentRenderTarget !== null ) {
//
//			// Generate mipmap if we're using any kind of mipmap filtering
//
//			textures.updateRenderTargetMipmap( _currentRenderTarget );
//
//			// resolve multisample renderbuffers to a single-sample texture if necessary
//
//			textures.updateMultisampleRenderTarget( _currentRenderTarget );
//
//		}
//
//		// Ensure depth buffer writing is enabled so it can be cleared on next render
//
//		state.buffers.depth.setTest( true );
//		state.buffers.depth.setMask( true );
//		state.buffers.color.setMask( true );
//
//		state.setPolygonOffset( false );
//
//		// _gl.finish();
//
//		renderStateStack.pop();
//		if ( renderStateStack.length > 0 ) {
//
//			currentRenderState = renderStateStack[ renderStateStack.length - 1 ];
//
//		} else {
//
//			currentRenderState = null;
//
//		}
//
//		currentRenderList = null;
//
	}
//
//	public projectObject( object, camera, groupOrder, sortObjects ) {
//
//		if ( object.visible === false ) return;
//
//		const visible = object.layers.test( camera.layers );
//
//		if ( visible ) {
//
//			if ( object.isGroup ) {
//
//				groupOrder = object.renderOrder;
//
//			} else if ( object.isLOD ) {
//
//				if ( object.autoUpdate === true ) object.update( camera );
//
//			} else if ( object.isLight ) {
//
//				currentRenderState.pushLight( object );
//
//				if ( object.castShadow ) {
//
//					currentRenderState.pushShadow( object );
//
//				}
//
//			} else if ( object.isSprite ) {
//
//				if ( ! object.frustumCulled || _frustum.intersectsSprite( object ) ) {
//
//					if ( sortObjects ) {
//
//						_vector3.setFromMatrixPosition( object.matrixWorld )
//							.applyMatrix4( _projScreenMatrix );
//
//					}
//
//					const geometry = objects.update( object );
//					const material = object.material;
//
//					if ( material.visible ) {
//
//						currentRenderList.push( object, geometry, material, groupOrder, _vector3.z, null );
//
//					}
//
//				}
//
//			} else if ( object.isImmediateRenderObject ) {
//
//				if ( sortObjects ) {
//
//					_vector3.setFromMatrixPosition( object.matrixWorld )
//						.applyMatrix4( _projScreenMatrix );
//
//				}
//
//				currentRenderList.push( object, null, object.material, groupOrder, _vector3.z, null );
//
//			} else if ( object.isMesh || object.isLine || object.isPoints ) {
//
//				if ( object.isSkinnedMesh ) {
//
//					// update skeleton only once in a frame
//
//					if ( object.skeleton.frame !== info.render.frame ) {
//
//						object.skeleton.update();
//						object.skeleton.frame = info.render.frame;
//
//					}
//
//				}
//
//				if ( ! object.frustumCulled || _frustum.intersectsObject( object ) ) {
//
//					if ( sortObjects ) {
//
//						_vector3.setFromMatrixPosition( object.matrixWorld )
//							.applyMatrix4( _projScreenMatrix );
//
//					}
//
//					const geometry = objects.update( object );
//					const material = object.material;
//
//					if ( Array.isArray( material ) ) {
//
//						const groups = geometry.groups;
//
//						for ( let i = 0, l = groups.length; i < l; i ++ ) {
//
//							const group = groups[ i ];
//							const groupMaterial = material[ group.materialIndex ];
//
//							if ( groupMaterial && groupMaterial.visible ) {
//
//								currentRenderList.push( object, geometry, groupMaterial, groupOrder, _vector3.z, group );
//
//							}
//
//						}
//
//					} else if ( material.visible ) {
//
//						currentRenderList.push( object, geometry, material, groupOrder, _vector3.z, null );
//
//					}
//
//				}
//
//			}
//
//		}
//
//		const children = object.children;
//
//		for ( let i = 0, l = children.length; i < l; i ++ ) {
//
//			projectObject( children[ i ], camera, groupOrder, sortObjects );
//
//		}
//
//	}
//
//	public renderObjects( renderList, scene, camera ) {
//
//		const overrideMaterial = scene.isScene === true ? scene.overrideMaterial : null;
//
//		for ( let i = 0, l = renderList.length; i < l; i ++ ) {
//
//			const renderItem = renderList[ i ];
//
//			const object = renderItem.object;
//			const geometry = renderItem.geometry;
//			const material = overrideMaterial === null ? renderItem.material : overrideMaterial;
//			const group = renderItem.group;
//
//			if ( camera.isArrayCamera ) {
//
//				const cameras = camera.cameras;
//
//				for ( let j = 0, jl = cameras.length; j < jl; j ++ ) {
//
//					const camera2 = cameras[ j ];
//
//					if ( object.layers.test( camera2.layers ) ) {
//
//						state.viewport( _currentViewport.copy( camera2.viewport ) );
//
//						currentRenderState.setupLightsView( camera2 );
//
//						renderObject( object, scene, camera2, geometry, material, group );
//
//					}
//
//				}
//
//			} else {
//
//				renderObject( object, scene, camera, geometry, material, group );
//
//			}
//
//		}
//
//	}
//
//	public renderObject( object, scene, camera, geometry, material, group ) {
//
//		object.onBeforeRender( _this, scene, camera, geometry, material, group );
//
//		object.modelViewMatrix.multiplyMatrices( camera.matrixWorldInverse, object.matrixWorld );
//		object.normalMatrix.getNormalMatrix( object.modelViewMatrix );
//
//		if ( object.isImmediateRenderObject ) {
//
//			const program = setProgram( camera, scene, material, object );
//
//			state.setMaterial( material );
//
//			bindingStates.reset();
//
//			renderObjectImmediate( object, program );
//
//		} else {
//
//			_this.renderBufferDirect( camera, scene, geometry, material, object, group );
//
//		}
//
//		object.onAfterRender( _this, scene, camera, geometry, material, group );
//
//	}
//
//	public initMaterial( material, scene, object ) {
//
//		if ( scene.isScene !== true ) scene = _emptyScene; // scene could be a Mesh, Line, Points, ...
//
//		const materialProperties = properties.get( material );
//
//		const lights = currentRenderState.state.lights;
//		const shadowsArray = currentRenderState.state.shadowsArray;
//
//		const lightsStateVersion = lights.state.version;
//
//		const parameters = programCache.getParameters( material, lights.state, shadowsArray, scene, object );
//		const programCacheKey = programCache.getProgramCacheKey( parameters );
//
//		let program = materialProperties.program;
//		let programChange = true;
//
//		if ( program === undefined ) {
//
//			// new material
//			material.addEventListener( 'dispose', onMaterialDispose );
//
//		} else if ( program.cacheKey !== programCacheKey ) {
//
//			// changed glsl or parameters
//			releaseMaterialProgramReference( material );
//
//		} else if ( materialProperties.lightsStateVersion !== lightsStateVersion ) {
//
//			programChange = false;
//
//		} else if ( parameters.shaderID !== undefined ) {
//
//			// same glsl and uniform list, envMap still needs the update here to avoid a frame-late effect
//
//			const environment = material.isMeshStandardMaterial ? scene.environment : null;
//			materialProperties.envMap = cubemaps.get( material.envMap || environment );
//
//			return;
//
//		} else {
//
//			// only rebuild uniform list
//			programChange = false;
//
//		}
//
//		if ( programChange ) {
//
//			parameters.uniforms = programCache.getUniforms( material );
//
//			material.onBeforeCompile( parameters, _this );
//
//			program = programCache.acquireProgram( parameters, programCacheKey );
//
//			materialProperties.program = program;
//			materialProperties.uniforms = parameters.uniforms;
//			materialProperties.outputEncoding = parameters.outputEncoding;
//
//		}
//
//		const uniforms = materialProperties.uniforms;
//
//		if ( ! material.isShaderMaterial &&
//			! material.isRawShaderMaterial ||
//			material.clipping === true ) {
//
//			materialProperties.numClippingPlanes = clipping.numPlanes;
//			materialProperties.numIntersection = clipping.numIntersection;
//			uniforms.clippingPlanes = clipping.uniform;
//
//		}
//
//		materialProperties.environment = material.isMeshStandardMaterial ? scene.environment : null;
//		materialProperties.fog = scene.fog;
//		materialProperties.envMap = cubemaps.get( material.envMap || materialProperties.environment );
//
//		// store the light setup it was created for
//
//		materialProperties.needsLights = materialNeedsLights( material );
//		materialProperties.lightsStateVersion = lightsStateVersion;
//
//		if ( materialProperties.needsLights ) {
//
//			// wire up the material to this renderer's lighting state
//
//			uniforms.ambientLightColor.value = lights.state.ambient;
//			uniforms.lightProbe.value = lights.state.probe;
//			uniforms.directionalLights.value = lights.state.directional;
//			uniforms.directionalLightShadows.value = lights.state.directionalShadow;
//			uniforms.spotLights.value = lights.state.spot;
//			uniforms.spotLightShadows.value = lights.state.spotShadow;
//			uniforms.rectAreaLights.value = lights.state.rectArea;
//			uniforms.ltc_1.value = lights.state.rectAreaLTC1;
//			uniforms.ltc_2.value = lights.state.rectAreaLTC2;
//			uniforms.pointLights.value = lights.state.point;
//			uniforms.pointLightShadows.value = lights.state.pointShadow;
//			uniforms.hemisphereLights.value = lights.state.hemi;
//
//			uniforms.directionalShadowMap.value = lights.state.directionalShadowMap;
//			uniforms.directionalShadowMatrix.value = lights.state.directionalShadowMatrix;
//			uniforms.spotShadowMap.value = lights.state.spotShadowMap;
//			uniforms.spotShadowMatrix.value = lights.state.spotShadowMatrix;
//			uniforms.pointShadowMap.value = lights.state.pointShadowMap;
//			uniforms.pointShadowMatrix.value = lights.state.pointShadowMatrix;
//			// TODO (abelnation): add area lights shadow info to uniforms
//
//		}
//
//		const progUniforms = materialProperties.program.getUniforms();
//		const uniformsList = WebGLUniforms.seqWithValue( progUniforms.seq, uniforms );
//
//		materialProperties.uniformsList = uniformsList;
//
//	}
//
//	public setProgram( camera, scene, material, object ) {
//
//		if ( scene.isScene !== true ) scene = _emptyScene; // scene could be a Mesh, Line, Points, ...
//
//		textures.resetTextureUnits();
//
//		const fog = scene.fog;
//		const environment = material.isMeshStandardMaterial ? scene.environment : null;
//		const encoding = ( _currentRenderTarget === null ) ? _this.outputEncoding : _currentRenderTarget.texture.encoding;
//		const envMap = cubemaps.get( material.envMap || environment );
//
//		const materialProperties = properties.get( material );
//		const lights = currentRenderState.state.lights;
//
//		if ( _clippingEnabled === true ) {
//
//			if ( _localClippingEnabled === true || camera !== _currentCamera ) {
//
//				const useCache =
//					camera === _currentCamera &&
//					material.id === _currentMaterialId;
//
//				// we might want to call this function with some ClippingGroup
//				// object instead of the material, once it becomes feasible
//				// (#8465, #8379)
//				clipping.setState( material, camera, useCache );
//
//			}
//
//		}
//
//		if ( material.version === materialProperties.__version ) {
//
//			if ( material.fog && materialProperties.fog !== fog ) {
//
//				initMaterial( material, scene, object );
//
//			} else if ( materialProperties.environment !== environment ) {
//
//				initMaterial( material, scene, object );
//
//			} else if ( materialProperties.needsLights && ( materialProperties.lightsStateVersion !== lights.state.version ) ) {
//
//				initMaterial( material, scene, object );
//
//			} else if ( materialProperties.numClippingPlanes !== undefined &&
//				( materialProperties.numClippingPlanes !== clipping.numPlanes ||
//				materialProperties.numIntersection !== clipping.numIntersection ) ) {
//
//				initMaterial( material, scene, object );
//
//			} else if ( materialProperties.outputEncoding !== encoding ) {
//
//				initMaterial( material, scene, object );
//
//			} else if ( materialProperties.envMap !== envMap ) {
//
//				initMaterial( material, scene, object );
//
//			}
//
//		} else {
//
//			initMaterial( material, scene, object );
//			materialProperties.__version = material.version;
//
//		}
//
//		let refreshProgram = false;
//		let refreshMaterial = false;
//		let refreshLights = false;
//
//		const program = materialProperties.program,
//			p_uniforms = program.getUniforms(),
//			m_uniforms = materialProperties.uniforms;
//
//		if ( state.useProgram( program.program ) ) {
//
//			refreshProgram = true;
//			refreshMaterial = true;
//			refreshLights = true;
//
//		}
//
//		if ( material.id !== _currentMaterialId ) {
//
//			_currentMaterialId = material.id;
//
//			refreshMaterial = true;
//
//		}
//
//		if ( refreshProgram || _currentCamera !== camera ) {
//
//			p_uniforms.setValue( _gl, 'projectionMatrix', camera.projectionMatrix );
//
//			if ( capabilities.logarithmicDepthBuffer ) {
//
//				p_uniforms.setValue( _gl, 'logDepthBufFC',
//					2.0 / ( Math.log( camera.far + 1.0 ) / Math.LN2 ) );
//
//			}
//
//			if ( _currentCamera !== camera ) {
//
//				_currentCamera = camera;
//
//				// lighting uniforms depend on the camera so enforce an update
//				// now, in case this material supports lights - or later, when
//				// the next material that does gets activated:
//
//				refreshMaterial = true;		// set to true on material change
//				refreshLights = true;		// remains set until update done
//
//			}
//
//			// load material specific uniforms
//			// (shader material also gets them for the sake of genericity)
//
//			if ( material.isShaderMaterial ||
//				material.isMeshPhongMaterial ||
//				material.isMeshToonMaterial ||
//				material.isMeshStandardMaterial ||
//				material.envMap ) {
//
//				const uCamPos = p_uniforms.map.cameraPosition;
//
//				if ( uCamPos !== undefined ) {
//
//					uCamPos.setValue( _gl,
//						_vector3.setFromMatrixPosition( camera.matrixWorld ) );
//
//				}
//
//			}
//
//			if ( material.isMeshPhongMaterial ||
//				material.isMeshToonMaterial ||
//				material.isMeshLambertMaterial ||
//				material.isMeshBasicMaterial ||
//				material.isMeshStandardMaterial ||
//				material.isShaderMaterial ) {
//
//				p_uniforms.setValue( _gl, 'isOrthographic', camera.isOrthographicCamera === true );
//
//			}
//
//			if ( material.isMeshPhongMaterial ||
//				material.isMeshToonMaterial ||
//				material.isMeshLambertMaterial ||
//				material.isMeshBasicMaterial ||
//				material.isMeshStandardMaterial ||
//				material.isShaderMaterial ||
//				material.isShadowMaterial ||
//				material.skinning ) {
//
//				p_uniforms.setValue( _gl, 'viewMatrix', camera.matrixWorldInverse );
//
//			}
//
//		}
//
//		// skinning uniforms must be set even if material didn't change
//		// auto-setting of texture unit for bone texture must go before other textures
//		// otherwise textures used for skinning can take over texture units reserved for other material textures
//
//		if ( material.skinning ) {
//
//			p_uniforms.setOptional( _gl, object, 'bindMatrix' );
//			p_uniforms.setOptional( _gl, object, 'bindMatrixInverse' );
//
//			const skeleton = object.skeleton;
//
//			if ( skeleton ) {
//
//				const bones = skeleton.bones;
//
//				if ( capabilities.floatVertexTextures ) {
//
//					if ( skeleton.boneTexture === null ) {
//
//						// layout (1 matrix = 4 pixels)
//						//      RGBA RGBA RGBA RGBA (=> column1, column2, column3, column4)
//						//  with  8x8  pixel texture max   16 bones * 4 pixels =  (8 * 8)
//						//       16x16 pixel texture max   64 bones * 4 pixels = (16 * 16)
//						//       32x32 pixel texture max  256 bones * 4 pixels = (32 * 32)
//						//       64x64 pixel texture max 1024 bones * 4 pixels = (64 * 64)
//
//
//						let size = Math.sqrt( bones.length * 4 ); // 4 pixels needed for 1 matrix
//						size = MathUtils.ceilPowerOfTwo( size );
//						size = Math.max( size, 4 );
//
//						const boneMatrices = new Float32Array( size * size * 4 ); // 4 floats per RGBA pixel
//						boneMatrices.set( skeleton.boneMatrices ); // copy current values
//
//						const boneTexture = new DataTexture( boneMatrices, size, size, RGBAFormat, FloatType );
//
//						skeleton.boneMatrices = boneMatrices;
//						skeleton.boneTexture = boneTexture;
//						skeleton.boneTextureSize = size;
//
//					}
//
//					p_uniforms.setValue( _gl, 'boneTexture', skeleton.boneTexture, textures );
//					p_uniforms.setValue( _gl, 'boneTextureSize', skeleton.boneTextureSize );
//
//				} else {
//
//					p_uniforms.setOptional( _gl, skeleton, 'boneMatrices' );
//
//				}
//
//			}
//
//		}
//
//		if ( refreshMaterial || materialProperties.receiveShadow !== object.receiveShadow ) {
//
//			materialProperties.receiveShadow = object.receiveShadow;
//			p_uniforms.setValue( _gl, 'receiveShadow', object.receiveShadow );
//
//		}
//
//		if ( refreshMaterial ) {
//
//			p_uniforms.setValue( _gl, 'toneMappingExposure', _this.toneMappingExposure );
//
//			if ( materialProperties.needsLights ) {
//
//				// the current material requires lighting info
//
//				// note: all lighting uniforms are always set correctly
//				// they simply reference the renderer's state for their
//				// values
//				//
//				// use the current material's .needsUpdate flags to set
//				// the GL state when required
//
//				markUniformsLightsNeedsUpdate( m_uniforms, refreshLights );
//
//			}
//
//			// refresh uniforms common to several materials
//
//			if ( fog && material.fog ) {
//
//				materials.refreshFogUniforms( m_uniforms, fog );
//
//			}
//
//			materials.refreshMaterialUniforms( m_uniforms, material, _pixelRatio, _height );
//
//			WebGLUniforms.upload( _gl, materialProperties.uniformsList, m_uniforms, textures );
//
//		}
//
//		if ( material.isShaderMaterial && material.uniformsNeedUpdate === true ) {
//
//			WebGLUniforms.upload( _gl, materialProperties.uniformsList, m_uniforms, textures );
//			material.uniformsNeedUpdate = false;
//
//		}
//
//		if ( material.isSpriteMaterial ) {
//
//			p_uniforms.setValue( _gl, 'center', object.center );
//
//		}
//
//		// common matrices
//
//		p_uniforms.setValue( _gl, 'modelViewMatrix', object.modelViewMatrix );
//		p_uniforms.setValue( _gl, 'normalMatrix', object.normalMatrix );
//		p_uniforms.setValue( _gl, 'modelMatrix', object.matrixWorld );
//
//		return program;
//
//	}
//
//	// If uniforms are marked as clean, they don't need to be loaded to the GPU.
//
//	public markUniformsLightsNeedsUpdate( uniforms, value ) {
//
//		uniforms.ambientLightColor.needsUpdate = value;
//		uniforms.lightProbe.needsUpdate = value;
//
//		uniforms.directionalLights.needsUpdate = value;
//		uniforms.directionalLightShadows.needsUpdate = value;
//		uniforms.pointLights.needsUpdate = value;
//		uniforms.pointLightShadows.needsUpdate = value;
//		uniforms.spotLights.needsUpdate = value;
//		uniforms.spotLightShadows.needsUpdate = value;
//		uniforms.rectAreaLights.needsUpdate = value;
//		uniforms.hemisphereLights.needsUpdate = value;
//
//	}
//
//	public materialNeedsLights( material ) {
//
//		return material.isMeshLambertMaterial || material.isMeshToonMaterial || material.isMeshPhongMaterial ||
//			material.isMeshStandardMaterial || material.isShadowMaterial ||
//			( material.isShaderMaterial && material.lights === true );
//
//	}
//
//	//
//	this.setFramebuffer = function ( value ) {
//
//		if ( _framebuffer !== value && _currentRenderTarget === null ) _gl.bindFramebuffer( _gl.FRAMEBUFFER, value );
//
//		_framebuffer = value;
//
//	};
//
//	this.getActiveCubeFace = function () {
//
//		return _currentActiveCubeFace;
//
//	};
//
//	this.getActiveMipmapLevel = function () {
//
//		return _currentActiveMipmapLevel;
//
//	};
//
//	this.getRenderList = function () {
//
//		return currentRenderList;
//
//	};
//
//	this.setRenderList = function ( renderList ) {
//
//		currentRenderList = renderList;
//
//	};
//
//	this.getRenderTarget = function () {
//
//		return _currentRenderTarget;
//
//	};
//
//	this.setRenderTarget = function ( renderTarget, activeCubeFace = 0, activeMipmapLevel = 0 ) {
//
//		_currentRenderTarget = renderTarget;
//		_currentActiveCubeFace = activeCubeFace;
//		_currentActiveMipmapLevel = activeMipmapLevel;
//
//		if ( renderTarget && properties.get( renderTarget ).__webglFramebuffer === undefined ) {
//
//			textures.setupRenderTarget( renderTarget );
//
//		}
//
//		let framebuffer = _framebuffer;
//		let isCube = false;
//
//		if ( renderTarget ) {
//
//			const __webglFramebuffer = properties.get( renderTarget ).__webglFramebuffer;
//
//			if ( renderTarget.isWebGLCubeRenderTarget ) {
//
//				framebuffer = __webglFramebuffer[ activeCubeFace ];
//				isCube = true;
//
//			} else if ( renderTarget.isWebGLMultisampleRenderTarget ) {
//
//				framebuffer = properties.get( renderTarget ).__webglMultisampledFramebuffer;
//
//			} else {
//
//				framebuffer = __webglFramebuffer;
//
//			}
//
//			_currentViewport.copy( renderTarget.viewport );
//			_currentScissor.copy( renderTarget.scissor );
//			_currentScissorTest = renderTarget.scissorTest;
//
//		} else {
//
//			_currentViewport.copy( _viewport ).multiplyScalar( _pixelRatio ).floor();
//			_currentScissor.copy( _scissor ).multiplyScalar( _pixelRatio ).floor();
//			_currentScissorTest = _scissorTest;
//
//		}
//
//		if ( _currentFramebuffer !== framebuffer ) {
//
//			_gl.bindFramebuffer( _gl.FRAMEBUFFER, framebuffer );
//			_currentFramebuffer = framebuffer;
//
//		}
//
//		state.viewport( _currentViewport );
//		state.scissor( _currentScissor );
//		state.setScissorTest( _currentScissorTest );
//
//		if ( isCube ) {
//
//			const textureProperties = properties.get( renderTarget.texture );
//			_gl.framebufferTexture2D( _gl.FRAMEBUFFER, _gl.COLOR_ATTACHMENT0, _gl.TEXTURE_CUBE_MAP_POSITIVE_X + activeCubeFace, textureProperties.__webglTexture, activeMipmapLevel );
//
//		}
//
//	};
//
//	this.readRenderTargetPixels = function ( renderTarget, x, y, width, height, buffer, activeCubeFaceIndex ) {
//
//		if ( ! ( renderTarget && renderTarget.isWebGLRenderTarget ) ) {
//
//			console.error( 'THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not THREE.WebGLRenderTarget.' );
//			return;
//
//		}
//
//		let framebuffer = properties.get( renderTarget ).__webglFramebuffer;
//
//		if ( renderTarget.isWebGLCubeRenderTarget && activeCubeFaceIndex !== undefined ) {
//
//			framebuffer = framebuffer[ activeCubeFaceIndex ];
//
//		}
//
//		if ( framebuffer ) {
//
//			let restore = false;
//
//			if ( framebuffer !== _currentFramebuffer ) {
//
//				_gl.bindFramebuffer( _gl.FRAMEBUFFER, framebuffer );
//
//				restore = true;
//
//			}
//
//			try {
//
//				const texture = renderTarget.texture;
//				const textureFormat = texture.format;
//				const textureType = texture.type;
//
//				if ( textureFormat !== RGBAFormat && utils.convert( textureFormat ) !== _gl.getParameter( _gl.IMPLEMENTATION_COLOR_READ_FORMAT ) ) {
//
//					console.error( 'THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not in RGBA or implementation defined format.' );
//					return;
//
//				}
//
//				if ( textureType !== UnsignedByteType && utils.convert( textureType ) !== _gl.getParameter( _gl.IMPLEMENTATION_COLOR_READ_TYPE ) && // IE11, Edge and Chrome Mac < 52 (#9513)
//					! ( textureType === FloatType && ( capabilities.isWebGL2 || extensions.get( 'OES_texture_float' ) || extensions.get( 'WEBGL_color_buffer_float' ) ) ) && // Chrome Mac >= 52 and Firefox
//					! ( textureType === HalfFloatType && ( capabilities.isWebGL2 ? extensions.get( 'EXT_color_buffer_float' ) : extensions.get( 'EXT_color_buffer_half_float' ) ) ) ) {
//
//					console.error( 'THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not in UnsignedByteType or implementation defined type.' );
//					return;
//
//				}
//
//				if ( _gl.checkFramebufferStatus( _gl.FRAMEBUFFER ) === _gl.FRAMEBUFFER_COMPLETE ) {
//
//					// the following if statement ensures valid read requests (no out-of-bounds pixels, see #8604)
//
//					if ( ( x >= 0 && x <= ( renderTarget.width - width ) ) && ( y >= 0 && y <= ( renderTarget.height - height ) ) ) {
//
//						_gl.readPixels( x, y, width, height, utils.convert( textureFormat ), utils.convert( textureType ), buffer );
//
//					}
//
//				} else {
//
//					console.error( 'THREE.WebGLRenderer.readRenderTargetPixels: readPixels from renderTarget failed. Framebuffer not complete.' );
//
//				}
//
//			} finally {
//
//				if ( restore ) {
//
//					_gl.bindFramebuffer( _gl.FRAMEBUFFER, _currentFramebuffer );
//
//				}
//
//			}
//
//		}
//
//	};
//
//	this.copyFramebufferToTexture = function ( position, texture, level = 0 ) {
//
//		const levelScale = Math.pow( 2, - level );
//		const width = Math.floor( texture.image.width * levelScale );
//		const height = Math.floor( texture.image.height * levelScale );
//		const glFormat = utils.convert( texture.format );
//
//		textures.setTexture2D( texture, 0 );
//
//		_gl.copyTexImage2D( _gl.TEXTURE_2D, level, glFormat, position.x, position.y, width, height, 0 );
//
//		state.unbindTexture();
//
//	};
//
//	this.copyTextureToTexture = function ( position, srcTexture, dstTexture, level = 0 ) {
//
//		const width = srcTexture.image.width;
//		const height = srcTexture.image.height;
//		const glFormat = utils.convert( dstTexture.format );
//		const glType = utils.convert( dstTexture.type );
//
//		textures.setTexture2D( dstTexture, 0 );
//
//		// As another texture upload may have changed pixelStorei
//		// parameters, make sure they are correct for the dstTexture
//		_gl.pixelStorei( _gl.UNPACK_FLIP_Y_WEBGL, dstTexture.flipY );
//		_gl.pixelStorei( _gl.UNPACK_PREMULTIPLY_ALPHA_WEBGL, dstTexture.premultiplyAlpha );
//		_gl.pixelStorei( _gl.UNPACK_ALIGNMENT, dstTexture.unpackAlignment );
//
//		if ( srcTexture.isDataTexture ) {
//
//			_gl.texSubImage2D( _gl.TEXTURE_2D, level, position.x, position.y, width, height, glFormat, glType, srcTexture.image.data );
//
//		} else {
//
//			if ( srcTexture.isCompressedTexture ) {
//
//				_gl.compressedTexSubImage2D( _gl.TEXTURE_2D, level, position.x, position.y, srcTexture.mipmaps[ 0 ].width, srcTexture.mipmaps[ 0 ].height, glFormat, srcTexture.mipmaps[ 0 ].data );
//
//			} else {
//
//				_gl.texSubImage2D( _gl.TEXTURE_2D, level, position.x, position.y, glFormat, glType, srcTexture.image );
//
//			}
//
//		}
//
//		// Generate mipmaps only when copying level 0
//		if ( level === 0 && dstTexture.generateMipmaps ) _gl.generateMipmap( _gl.TEXTURE_2D );
//
//		state.unbindTexture();
//
//	};
//
//	this.initTexture = function ( texture ) {
//
//		textures.setTexture2D( texture, 0 );
//
//		state.unbindTexture();
//
//	};
//
//	this.resetState = function () {
//
//		state.reset();
//		bindingStates.reset();
//
//	};
//
//	if ( typeof __THREE_DEVTOOLS__ !== 'undefined' ) {
//
//		__THREE_DEVTOOLS__.dispatchEvent( new CustomEvent( 'observe', { detail: this } ) ); // eslint-disable-line no-undef
//
//	}

}
