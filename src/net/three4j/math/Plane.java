package net.three4j.math;

import static net.three4j.THREE.console;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

public class Plane {
	private final Vector3 _vector1 = /* @__PURE__ */ new Vector3();
	private final Vector3 _vector2 = /* @__PURE__ */ new Vector3();
	private final Matrix3 _normalMatrix = /* @__PURE__ */ new Matrix3();

	private Vector3 _normal = new Vector3(1, 0, 0);

	public Vector3 normal() {
		return _normal;
	}

	public Plane normal(Vector3 normal) {
		this._normal = normal;
		return this;
	}

	private double _constant = 0;

	public double constant() {
		return _constant;
	}

	public Plane constant(double constant) {
		this._constant = constant;
		return this;
	}

	public Plane() {
		new Vector3(1, 0, 0);
	}

	public Plane(Vector3 normal) {
		// normal is assumed to be normalized
		this._normal = (normal != null) ? normal : new Vector3(1, 0, 0);
	}

	public Plane(Vector3 normal, double constant) {
		// normal is assumed to be normalized
		this._normal = (normal != null) ? normal : new Vector3(1, 0, 0);
		this._constant = constant;
	}

	public Plane set(Vector3 normal, double constant) {

		this._normal.copy(normal);
		this._constant = constant;

		return this;

	}

	public Plane setComponents(double x, double y, double z, double w) {

		this._normal.set(x, y, z);
		this._constant = w;

		return this;

	}

	public Plane setFromNormalAndCoplanarPoint(Vector3 normal, Vector3 point) {

		this._normal.copy(normal);
		this._constant = -point.dot(this._normal);

		return this;

	}

	public Plane setFromCoplanarPoints(Vector3 a, Vector3 b, Vector3 c) {

		final Vector3 normal = _vector1.subVectors(c, b).cross(_vector2.subVectors(a, b)).normalize();

		// Q: should an error be thrown if normal is zero (e.g. degenerate plane)?

		this.setFromNormalAndCoplanarPoint(normal, a);

		return this;

	}

	public Plane clone() {

		return new Plane().copy(this);

	}

	public Plane copy(Plane plane) {

		this._normal.copy(plane._normal);
		this._constant = plane._constant;

		return this;

	}

	public Plane normalize() {

		// Note: will lead to a divide by zero if the plane is invalid.

		final double inverseNormalLength = 1.0 / this._normal.length();
		this._normal.multiplyScalar(inverseNormalLength);
		this._constant *= inverseNormalLength;

		return this;

	}

	public Plane negate() {

		this._constant *= -1;
		this._normal.negate();

		return this;

	}

	public double distanceToPoint(Vector3 point) {

		return this._normal.dot(point) + this._constant;

	}

	public double distanceToSphere(Sphere sphere) {

		return this.distanceToPoint(sphere.center()) - sphere.radius();

	}

	public Vector3 projectPoint(Vector3 point, Vector3 target) {

		if (target == null) {
			console.warn("THREE.Plane.projectPoint(point, null) is not allowed");
			target = new Vector3();

		}

		return target.copy(this._normal).multiplyScalar(-this.distanceToPoint(point)).add(point);

	}

	public Vector3 intersectLine(Line3 line, Vector3 target) {

		if (target == null) {

			console.warn("THREE.Plane.intersectLine(line, null) is not allowed");
			target = new Vector3();

		}

		Vector3 direction = line.delta(_vector1);

		double denominator = this._normal.dot(direction);

		if (denominator == 0) {

			// line is coplanar, return origin
			if (this.distanceToPoint(line.start()) == 0) {

				return target.copy(line.start());

			}

			// Unsure if this is the correct method to handle this case. // DPP: ?
			return null;

		}

		final double t = -(line.start().dot(this._normal) + this._constant) / denominator;

		if (t < 0 || t > 1) {

			return null;

		}

		return target.copy(direction).multiplyScalar(t).add(line.start());

	}

	public boolean intersectsLine(Line3 line) {

		// Note: this tests if a line intersects the plane, not whether it (or its
		// end-points) are coplanar with it.

		double startSign = this.distanceToPoint(line.start());
		double endSign = this.distanceToPoint(line.end());

		return (startSign < 0 && endSign > 0) || (endSign < 0 && startSign > 0);

	}

	public boolean intersectsBox(Box3 box) {

		return box.intersectsPlane(this);

	}

	public boolean intersectsSphere( Sphere sphere ) {

		return sphere.intersectsPlane( this );

	}

	public Vector3 coplanarPoint( Vector3 target ) {

		if ( target == null ) {

			console.warn( "THREE.Plane.coplanarPoint(null) is not allowed" );
			target = new Vector3();

		}

		return target.copy( this._normal ).multiplyScalar( - this._constant );

	}

	public Plane applyMatrix4( Matrix4 matrix ) {
		return applyMatrix4(matrix, null);
	}


	public Plane applyMatrix4( Matrix4 matrix, Matrix3 optionalNormalMatrix ) {

		Matrix3 normalMatrix = optionalNormalMatrix != null ? optionalNormalMatrix : _normalMatrix.getNormalMatrix( matrix );

		Vector3 referencePoint = this.coplanarPoint( _vector1 ).applyMatrix4( matrix );

		Vector3 normal = this._normal.applyMatrix3( normalMatrix ).normalize();

		this._constant = - referencePoint.dot( normal );

		return this;

	}

	public Plane translate( Vector3 offset ) {

		this._constant -= offset.dot( this._normal );

		return this;

	}

	public boolean equals( Plane plane ) {

		return plane._normal.equals( this._normal ) && ( plane._constant == this._constant );

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("isPlane");
		return sortedReflectionToStringBuilder.toString();
	}

}
