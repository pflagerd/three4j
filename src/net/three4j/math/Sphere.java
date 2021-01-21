package net.three4j.math;

import static net.three4j.THREE.console;

public class Sphere {

	public final static Box3 _box = /* @__PURE__ */ new Box3();

	public Sphere() {
		this(new Vector3());
	}

	public Sphere(Vector3 center) {
		this(center, -1);
	}

	public Sphere(Vector3 center, double radius) {

		this._center = (center != null) ? center : new Vector3();
		this._radius = radius;

	}

	private Vector3 _center = new Vector3();

	public Vector3 center() {
		return _center;
	}

	public Sphere center(Vector3 center) {
		this._center = center;
		return this;
	}

	private double _radius;

	public double radius() {
		return _radius;
	}

	public Sphere radius(double radius) {
		this._radius = radius;
		return this;
	}

	public Sphere set(Vector3 center, double radius) {

		this._center.copy(center);
		this._radius = radius;

		return this;

	}

	public Sphere setFromPoints(Vector3[] points) {
		return setFromPoints(points, null);
	}

	public Sphere setFromPoints(Vector3[] points, Vector3 optionalCenter) {

		Vector3 center = this._center;

		if (optionalCenter != null) {

			center.copy(optionalCenter);

		} else {

			_box.setFromPoints(points).getCenter(center);

		}

		double maxRadiusSq = 0;

		for (int i = 0, il = points.length; i < il; i++) {

			maxRadiusSq = Math.max(maxRadiusSq, center.distanceToSquared(points[i]));

		}

		this._radius = Math.sqrt(maxRadiusSq);

		return this;

	}

	public Sphere clone() {

		return new Sphere().copy(this);

	}

	public Sphere copy(Sphere sphere) {

		this._center.copy(sphere._center);
		this._radius = sphere._radius;

		return this;

	}

	public boolean isEmpty() {

		return this._radius < 0;

	}

	public Sphere makeEmpty() {

		this._center.set(0, 0, 0);
		this._radius = -1;

		return this;

	}

	public boolean containsPoint(Vector3 point) {

		return point.distanceToSquared(this._center) <= (this._radius * this._radius);

	}

	public double distanceToPoint(Vector3 point) {

		return (point.distanceTo(this._center) - this._radius);

	}

	public boolean intersectsSphere(Sphere sphere) {

		final double radiusSum = this._radius + sphere._radius;

		return sphere._center.distanceToSquared(this._center) <= (radiusSum * radiusSum);

	}

	public boolean intersectsBox(Box3 box) {

		return box.intersectsSphere(this);

	}

	public boolean intersectsPlane(Plane plane) {

		return Math.abs(plane.distanceToPoint(this._center)) <= this._radius;

	}

	public Vector3 clampPoint(Vector3 point, Vector3 target) {

		final double deltaLengthSq = this._center.distanceToSquared(point);

		if (target == null) {

			console.warn("THREE.Sphere.clampPoint(point, null) is not allowed");
			target = new Vector3();

		}

		target.copy(point);

		if (deltaLengthSq > (this._radius * this._radius)) {

			target.sub(this._center).normalize();
			target.multiplyScalar(this._radius).add(this._center);

		}

		return target;

	}

//	public double getBoundingBox( target ) {
//
//		if ( target === undefined ) {
//
//			console.warn( 'THREE.Sphere: .getBoundingBox() target is now required' );
//			target = new Box3();
//
//		}
//
//		if ( this.isEmpty() ) {
//
//			// Empty sphere produces empty bounding box
//			target.makeEmpty();
//			return target;
//
//		}
//
//		target.set( this._center, this._center );
//		target.expandByScalar( this._radius );
//
//		return target;
//
//	}
//
//	public Sphere applyMatrix4( matrix ) {
//
//		this._center.applyMatrix4( matrix );
//		this._radius = this._radius * matrix.getMaxScaleOnAxis();
//
//		return this;
//
//	}
//
//	public Sphere translate( offset ) {
//
//		this._center.add( offset );
//
//		return this;
//
//	}
//
//	public double equals( sphere ) {
//
//		return sphere._center.equals( this._center ) && ( sphere._radius === this._radius );
//
//	}
//
}
