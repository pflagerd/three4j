package net.three4j.math;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

//import { MathUtils } from './MathUtils.js';

public class Spherical {
	private double _radius;
	private double _phi;
	private double _theta;

	public Spherical() {
		this(1, 0, 0);
	}

	public double radius() {
		return _radius;
	}

	public Spherical radius(double radius) {
		_radius = radius;
		return this;
	}

	public double phi() {
		return _phi;
	}

	public Spherical phi(double phi) {
		_phi = phi;
		return this;
	}

	public double theta() {
		return _theta;
	}

	public Spherical theta(double theta) {
		_theta = theta;
		return this;
	}

	public Spherical( double radius, double phi, double theta ) {

		this._radius = radius;
		this._phi = phi; // polar angle
		this._theta = theta; // azimuthal angle
	}

	public boolean equals(Spherical s) {
		return s._radius == _radius && s._phi == _phi && s._theta == _theta;
	}

	public Spherical set( double radius, double phi, double theta ) {

		this._radius = radius;
		this._phi = phi;
		this._theta = theta;

		return this;

	}

	public Spherical clone() {

		return new Spherical(this._radius, this._phi, this._theta);

	}

	public Spherical copy( Spherical other ) {

		this._radius = other._radius;
		this._phi = other._phi;
		this._theta = other._theta;

		return this;

	}

	// restrict phi to be betwee EPS and PI-EPS
	public Spherical makeSafe() {

		final double EPS = 0.000001;
		this._phi = Math.max( EPS, Math.min( Math.PI - EPS, this._phi ) );

		return this;
	}

	public Spherical setFromVector3( Vector3 v ) {

		return this.setFromCartesianCoords( v.x(), v.y(), v.z() );

	}

	public Spherical setFromCartesianCoords( double x, double y, double z ) {

		this._radius = Math.sqrt( x * x + y * y + z * z );

		if ( this._radius == 0 ) {

			this._theta = 0;
			this._phi = 0;

		} else {

			this._theta = Math.atan2( x, z );
			this._phi = Math.acos( MathUtils.clamp( y / this._radius, - 1, 1 ) );

		}

		return this;

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		//sortedReflectionToStringBuilder.setExcludeFieldNames("_onChangeCallback");
		return sortedReflectionToStringBuilder.toString();
	}


}
