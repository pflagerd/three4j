package net.three4j.cameras;

//import net.three4j.core;
//import net.three4j.math;

public class PerspectiveCamera extends Camera {
	private double _fov;
	private double _zoom;
	private double _near;
	private double _far;
	private double _focus;
	private double _aspect;
	private Object _view;
	private double _filmGauge;
	private double _filmOffset;

	public PerspectiveCamera() {
		this(50, 1, 0.1, 2000);
	}

	public PerspectiveCamera(double fov, double aspect, double near, double far) {
		super();

		this._fov = fov;
		this._zoom = 1;

		this._near = near;
		this._far = far;
		this._focus = 10;

		this._aspect = aspect;
		this._view = null;

		this._filmGauge = 35; // width of the film (default in millimeters)
		this._filmOffset = 0; // horizontal film offset (same unit as gauge)

		this.updateProjectionMatrix();
	}

	public PerspectiveCamera copy(PerspectiveCamera source, PerspectiveCamera recursive) {

//		Camera.prototype.copy.call( this, source, recursive );
//
//		this._fov = source.fov;
//		this._zoom = source.zoom;
//
//		this._near = source.near;
//		this._far = source.far;
//		this._focus = source.focus;
//
//		this._aspect = source.aspect;
//		this._view = source.view === null ? null : Object.assign( {}, source.view );
//
//		this._filmGauge = source.filmGauge;
//		this._filmOffset = source.filmOffset;

		return this;
	}

	/**
	 * Sets the FOV by focal length in respect to the current .filmGauge.
	 *
	 * The default film gauge is 35, so that the focal length can be specified for
	 * a 35mm (full frame) camera.
	 *
	 * Values for focal length and film gauge must have the same unit.
	 */
	public void setFocalLength  ( double focalLength ) {

//		// see http://www.bobatkins.com/photography/technical/field_of_view.html
//		const vExtentSlope = 0.5 * this.getFilmHeight() / focalLength;
//
//		this._fov = MathUtils.RAD2DEG * 2 * Math.atan( vExtentSlope );
//		this.updateProjectionMatrix();

	}

	/**
	 * Calculates the focal length from the current .fov and .filmGauge.
	 */
	public double getFocalLength() {

//		const vExtentSlope = Math.tan( MathUtils.DEG2RAD * 0.5 * this.fov );
//
//		return 0.5 * this.getFilmHeight() / vExtentSlope;
		return 0;
	}

	public double getEffectiveFOV() {

//		return MathUtils.RAD2DEG * 2 * Math.atan(
//			Math.tan( MathUtils.DEG2RAD * 0.5 * this.fov ) / this.zoom );
		return 0;
	}

	public double getFilmWidth() {

		// film not completely covered in portrait format (aspect < 1)
		// return this.filmGauge * Math.min( this.aspect, 1 );
		return 0;
	}

	public double getFilmHeight() {

		// film not completely covered in landscape format (aspect > 1)
		// return this.filmGauge / Math.max( this.aspect, 1 );
		return 0;
	}

	/**
	 * Sets an offset in a larger frustum. This is useful for multi-window or
	 * multi-monitor/multi-machine setups.
	 *
	 * For example, if you have 3x2 monitors and each monitor is 1920x1080 and the
	 * monitors are in grid like this
	 *
	 * +---+---+---+ | A | B | C | +---+---+---+ | D | E | F | +---+---+---+
	 *
	 * then for each monitor you would call it like this
	 *
	 * const w = 1920; const h = 1080; const fullWidth = w * 3; const fullHeight = h
	 * * 2;
	 *
	 * --A-- camera.setViewOffset( fullWidth, fullHeight, w * 0, h * 0, w, h );
	 * --B-- camera.setViewOffset( fullWidth, fullHeight, w * 1, h * 0, w, h );
	 * --C-- camera.setViewOffset( fullWidth, fullHeight, w * 2, h * 0, w, h );
	 * --D-- camera.setViewOffset( fullWidth, fullHeight, w * 0, h * 1, w, h );
	 * --E-- camera.setViewOffset( fullWidth, fullHeight, w * 1, h * 1, w, h );
	 * --F-- camera.setViewOffset( fullWidth, fullHeight, w * 2, h * 1, w, h );
	 *
	 * Note there is no reason monitors have to be the same size or in a grid.
	 */
	public void setViewOffset(double fullWidth, double fullHeight, double x, double y, double width, double height) {

//		this._aspect = fullWidth / fullHeight;
//
//		if ( this.view === null ) {
//
//			this._view = {
//				enabled: true,
//				fullWidth: 1,
//				fullHeight: 1,
//				offsetX: 0,
//				offsetY: 0,
//				width: 1,
//				height: 1
//			};
//
//		}
//
//		this.view.enabled = true;
//		this.view.fullWidth = fullWidth;
//		this.view.fullHeight = fullHeight;
//		this.view.offsetX = x;
//		this.view.offsetY = y;
//		this.view.width = width;
//		this.view.height = height;
//
//		this.updateProjectionMatrix();

	}

	public void clearViewOffset() {
//
//		if ( this.view !== null ) {
//
//			this.view.enabled = false;
//
//		}
//
//		this.updateProjectionMatrix();

	}

	public void updateProjectionMatrix() {
//
//		const near = this.near;
//		let top = near * Math.tan( MathUtils.DEG2RAD * 0.5 * this.fov ) / this.zoom;
//		let height = 2 * top;
//		let width = this.aspect * height;
//		let left = - 0.5 * width;
//		const view = this.view;
//
//		if ( this.view !== null && this.view.enabled ) {
//
//			const fullWidth = view.fullWidth,
//				fullHeight = view.fullHeight;
//
//			left += view.offsetX * width / fullWidth;
//			top -= view.offsetY * height / fullHeight;
//			width *= view.width / fullWidth;
//			height *= view.height / fullHeight;
//
//		}
//
//		const skew = this.filmOffset;
//		if ( skew !== 0 ) left += near * skew / this.getFilmWidth();
//
//		this.projectionMatrix.makePerspective( left, left + width, top, top - height, near, this.far );
//
//		this.projectionMatrixInverse.copy( this.projectionMatrix ).invert();

	}

//	public toJSON( meta ) {
//
//		const data = Object3D.prototype.toJSON.call( this, meta );
//
//		data.object.fov = this.fov;
//		data.object.zoom = this.zoom;
//
//		data.object.near = this.near;
//		data.object.far = this.far;
//		data.object.focus = this.focus;
//
//		data.object.aspect = this.aspect;
//
//		if ( this.view !== null ) data.object.view = Object.assign( {}, this.view );
//
//		data.object.filmGauge = this.filmGauge;
//		data.object.filmOffset = this.filmOffset;
//
//		return data;
//
//	}

}
