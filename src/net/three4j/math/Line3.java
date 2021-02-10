package net.three4j.math;

import static net.three4j.THREE.console;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

public class Line3 {

	final static Vector3 _startP = /* @__PURE__ */ new Vector3();
	final static Vector3 _startEnd = /* @__PURE__ */ new Vector3();

	private Vector3 _start = new Vector3();

	public Vector3 start() {
		return _start;
	}

	public Line3 start(Vector3 start) {
		this._start = start;
		return this;
	}

	private Vector3 _end = new Vector3();

	public Vector3 end() {
		return _end;
	}

	public Line3 end(Vector3 end) {
		this._end = end;
		return this;
	}

	public Line3() {
	}

	public Line3(Vector3 start, Vector3 end) {

		this._start = (start != null) ? start : new Vector3();
		this._end = (end != null) ? end : new Vector3();

	}

	public Line3 set(Vector3 start, Vector3 end) {

		this._start.copy(start);
		this._end.copy(end);

		return this;

	}

	public Line3 clone() {

		return new Line3().copy(this);

	}

	public Line3 copy(Line3 line) {

		this._start.copy(line._start);
		this._end.copy(line._end);

		return this;

	}

	public Vector3 getCenter(Vector3 target) {

		if (target == null) {

			console.warn("THREE.Line3.getCenter() target is now required");
			target = new Vector3();

		}

		return target.addVectors(this._start, this._end).multiplyScalar(0.5);

	}

	public Vector3 delta(Vector3 target) {

		if (target == null) {

			console.warn("THREE.Line3: .delta() target is now required");
			target = new Vector3();

		}

		return target.subVectors(this._end, this._start);

	}

	public double distanceSq() {

		return this._start.distanceToSquared(this._end);

	}

	public double distance() {

		return this._start.distanceTo(this._end);

	}

	public Vector3 at(double t, Vector3 target) {

		if (target == null) {

			console.warn("THREE.Line3.at(t, null) is not allowed");
			target = new Vector3();

		}

		return this.delta(target).multiplyScalar(t).add(this._start);

	}

	public double closestPointToPointParameter(Vector3 point) {
		return closestPointToPointParameter(point, false);
	}

	public double closestPointToPointParameter(Vector3 point, boolean clampToLine) {

		_startP.subVectors(point, this._start);
		_startEnd.subVectors(this._end, this._start);

		final double startEnd2 = _startEnd.dot(_startEnd);
		final double startEnd_startP = _startEnd.dot(_startP);

		double t = startEnd_startP / startEnd2;

		if (clampToLine) {

			t = MathUtils.clamp(t, 0, 1);

		}

		return t;

	}

	public Vector3 closestPointToPoint(Vector3 point, boolean clampToLine, Vector3 target) {

		double t = this.closestPointToPointParameter(point, clampToLine);

		if (target == null) {

			console.warn("THREE.Line3.closestPointToPoint(point, null) is not allowed");
			target = new Vector3();

		}

		return this.delta(target).multiplyScalar(t).add(this._start);

	}

	public Line3 applyMatrix4(Matrix4 matrix) {

		this._start.applyMatrix4(matrix);
		this._end.applyMatrix4(matrix);

		return this;

	}

	public boolean equals(Line3 line) {

		return line._start.equals(this._start) && line._end.equals(this._end);

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		//sortedReflectionToStringBuilder.setExcludeFieldNames("isVector3");
		return sortedReflectionToStringBuilder.toString();
	}

}
