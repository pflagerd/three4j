package net.three4j.cameras;

import net.three4j.math.MathUtils;

public class PerspectiveCamera extends Camera {
	private double _fov;
	private double _zoom;
	private double _near;
	private double _far;
	private double _focus;
	private double _aspect;
	private View _view;
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

	public double fov() {
		return _fov;
	}

	public PerspectiveCamera fov(double fov) {
		this._fov = fov;
		return this;
	}

	public double zoom() {
		return _zoom;
	}

	public PerspectiveCamera zoom(double zoom) {
		this._zoom = zoom;
		return this;
	}

	public double near() {
		return _near;
	}

	public PerspectiveCamera near(double near) {
		this._near = near;
		return this;
	}

	public double far() {
		return _far;
	}

	public PerspectiveCamera far(double far) {
		this._far = far;
		return this;
	}

	public double focus() {
		return _focus;
	}

	public PerspectiveCamera focus(double focus) {
		this._focus = focus;
		return this;
	}

	public double aspect() {
		return _aspect;
	}

	public PerspectiveCamera aspect(double aspect) {
		this._aspect = aspect;
		return this;
	}

	public View view() {
		return _view;
	}

	public PerspectiveCamera view(View view) {
		if (view == null)
			new IllegalArgumentException("You may not pass a null for view");
		
		this._view = view;
		return this;
	}

	public double filmGauge() {
		return _filmGauge;
	}

	public PerspectiveCamera filmGauge(double filmGauge) {
		this._filmGauge = filmGauge;
		return this;
	}

	public double filmOffset() {
		return _filmOffset;
	}

	public PerspectiveCamera filmOffset(double filmOffset) {
		this._filmOffset = filmOffset;
		return this;
	}

	public PerspectiveCamera clone() {
		return new PerspectiveCamera().copy(this);
	}
	
	public PerspectiveCamera copy(PerspectiveCamera source) {
		return copy(source, true);
	}

	public PerspectiveCamera copy(PerspectiveCamera source, boolean recursive) {

		super.copy( source, recursive );

		this._fov = source._fov;
		this._zoom = source._zoom;

		this._near = source._near;
		this._far = source._far;
		this._focus = source._focus;

		this._aspect = source._aspect;
		this._view = source._view;

		this._filmGauge = source._filmGauge;
		this._filmOffset = source._filmOffset;

		return this;
	}

	/**
	 * Sets the FOV by focal length in respect to the current .filmGauge.
	 *
	 * The default film gauge is 35, so that the focal length can be specified for a
	 * 35mm (full frame) camera.
	 *
	 * Values for focal length and film gauge must have the same unit.
	 */
	public void setFocalLength(double focalLength) {

		// see http://www.bobatkins.com/photography/technical/field_of_view.html
		final double vExtentSlope = 0.5 * this.getFilmHeight() / focalLength;

		this._fov = MathUtils.RAD2DEG * 2 * Math.atan( vExtentSlope );
		this.updateProjectionMatrix();

	}

	/**
	 * Calculates the focal length from the current .fov and .filmGauge.
	 */
	public double getFocalLength() {

		final double vExtentSlope = Math.tan( MathUtils.DEG2RAD * 0.5 * this._fov );

		return 0.5 * this.getFilmHeight() / vExtentSlope;
	}

	public double getEffectiveFOV() {

		return MathUtils.RAD2DEG * 2 * Math.atan(
			Math.tan( MathUtils.DEG2RAD * 0.5 * this._fov ) / this._zoom );
	}

	public double getFilmWidth() {

		// film not completely covered in portrait format (aspect < 1)
		return this._filmGauge * Math.min( this._aspect, 1 );
	}

	public double getFilmHeight() {

		// film not completely covered in landscape format (aspect > 1)
		return this._filmGauge / Math.max( this._aspect, 1 );
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

		this._aspect = fullWidth / fullHeight;

		this._view._enabled = true;
		this._view._fullWidth = fullWidth;
		this._view._fullHeight = fullHeight;
		this._view._offsetX = x;
		this._view._offsetY = y;
		this._view._width = width;
		this._view._height = height;

		this.updateProjectionMatrix();

	}

	public void clearViewOffset() {

		this._view._enabled = false;

		this.updateProjectionMatrix();

	}

	public void updateProjectionMatrix() {

		double near = this._near;
		double top = near * Math.tan( MathUtils.DEG2RAD * 0.5 * this._fov ) / this._zoom;
		double height = 2 * top;
		double width = this._aspect * height;
		double left = - 0.5 * width;

		if ( this._view != null && this._view.enabled() ) {

			double fullWidth = _view._fullWidth,
				fullHeight = _view._fullHeight;

			left += _view._offsetX * width / fullWidth;
			top -= _view._offsetY * height / fullHeight;
			width *= _view._width / fullWidth;
			height *= _view._height / fullHeight;

		}

		final double skew = this._filmOffset;
		if ( skew != 0 ) left += near * skew / this.getFilmWidth();

		this._projectionMatrix.makePerspective( left, left + width, top, top - height, near, this._far );

		this._projectionMatrixInverse.copy( this._projectionMatrix ).invert();

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
