/**
 * Ref: https://en.wikipedia.org/wiki/Cylindrical_coordinate_system
 */

package net.three4j.math;

public class Cylindrical {
	
	private double _radius;
	private double _theta;
	private double _y;

	public Cylindrical() {
		this(1, 0, 0);
	}
	
	public Cylindrical ( double radius, double theta, double y ) {

		this._radius = radius; // distance from the origin to a point in the x-z plane
		this._theta = theta; // counterclockwise angle in the x-z plane measured in radians from the positive z-axis
		this._y = y; // height above the x-z plane

	}
	
	public double radius() {
		return _radius;
	}
	
	public double theta() {
		return _theta;
	}
	
	public double y() {
		return _y;
	}
	
	public Cylindrical radius(double radius) {
		_radius = radius;
		return this;
	}
	
	public Cylindrical theta(double theta) {
		_theta = theta;
		return this;
	}
	
	public Cylindrical y(double y) {
		_y = y;
		return this;
	}
	
	public boolean equals(Cylindrical s) {
		return s._radius == _radius && s._theta == _theta && s._y == _y;
	}
	
	public Cylindrical set( double radius, double theta, double y ) {

		this._radius = radius;
		this._theta = theta;
		this._y = y;

		return this;

	}

	public Cylindrical clone() {

		return new Cylindrical(_radius, _theta, _y);

	}

	public Cylindrical copy( Cylindrical other ) {

		this._radius = other._radius;
		this._theta = other._theta;
		this._y = other._y;

		return this;

	}

	public Cylindrical setFromVector3( Vector3 v ) {

		return this.setFromCartesianCoords( v.x(), v.y(), v.z() );

	}

	public Cylindrical setFromCartesianCoords( double x, double y, double z ) {

		this._radius = Math.sqrt( x * x + z * z );
		this._theta = Math.atan2( x, z );
		this._y = y;

		return this;
	}

}



