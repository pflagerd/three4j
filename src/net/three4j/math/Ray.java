package net.three4j.math;



public class Ray {

	private static final Vector3 _vector = /*@__PURE__*/ new Vector3();
	private static final Vector3 _segCenter = /*@__PURE__*/ new Vector3();
	private static final Vector3 _segDir = /*@__PURE__*/ new Vector3();
	private static final Vector3 _diff = /*@__PURE__*/ new Vector3();

	private static final Vector3 _edge1 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _edge2 = /*@__PURE__*/ new Vector3();
	private static final Vector3 _normal = /*@__PURE__*/ new Vector3();
	
	private Vector3 _origin = new Vector3();

	public Vector3 origin() {
	  return _origin;
	}

	public Ray origin(Vector3 origin) {
	  this._origin = origin;
	  return this;
	}


	private Vector3 _direction = new Vector3(0, 0, -1);

	public Vector3 direction() {
	  return _direction;
	}

	public Ray direction(Vector3 direction) {
	  this._direction = direction;
	  return this;
	}


	public Ray ( Vector3 origin, Vector3 direction ) {

		this._origin = ( origin != null ) ? origin : new Vector3();
		this._direction = ( direction != null ) ? direction : new Vector3( 0, 0, - 1 );

	}
	
	public Ray() {
		
	}

	public Ray set( Vector3 origin, Vector3 direction ) {

		this._origin.copy( origin );
		this._direction.copy( direction );

		return this;

	}

	public Ray clone() {

		return new Ray().copy( this );

	}

	public Ray copy( Ray ray ) {

		this._origin.copy( ray._origin );
		this._direction.copy( ray._direction );

		return this;

	}

	public Vector3 at( double t, Vector3 target ) {

		if ( target == null ) {
			target = new Vector3();
		}

		return target.copy( this._direction ).multiplyScalar( t ).add( this._origin );

	}

	public Ray lookAt( Vector3 v ) {

		this._direction.copy( v ).sub( this._origin ).normalize();

		return this;

	}

	public Ray recast( double t ) {

		this._origin.copy( this.at( t, _vector ) );

		return this;

	}

	public Vector3 closestPointToPoint( Vector3 point, Vector3 target ) {

		if ( target == null ) {
			target = new Vector3();
		}

		target.subVectors( point, this._origin );

		double directionDistance = target.dot( this._direction );

		if ( directionDistance < 0 ) {

			return target.copy( this._origin );

		}

		return target.copy( this._direction ).multiplyScalar( directionDistance ).add( this._origin );

	}

	public double distanceToPoint( Vector3 point ) {

		return Math.sqrt( this.distanceSqToPoint( point ) );

	}

	public double distanceSqToPoint( Vector3 point ) {

		double directionDistance = _vector.subVectors( point, this._origin ).dot( this._direction );

		// point behind the ray

		if ( directionDistance < 0 ) {

			return this._origin.distanceToSquared( point );

		}

		_vector.copy( this._direction ).multiplyScalar( directionDistance ).add( this._origin );

		return _vector.distanceToSquared( point );

	}

	public double distanceSqToSegment( Vector3 v0, Vector3 v1, Vector3 optionalPointOnRay, Vector3 optionalPointOnSegment ) {

		// from http://www.geometrictools.com/GTEngine/Include/Mathematics/GteDistRaySegment.h
		// It returns the min distance between the ray and the segment
		// defined by v0 and v1
		// It can also set two optional targets :
		// - The closest point on the ray
		// - The closest point on the segment

		_segCenter.copy( v0 ).add( v1 ).multiplyScalar( 0.5 );
		_segDir.copy( v1 ).sub( v0 ).normalize();
		_diff.copy( this._origin ).sub( _segCenter );

		double segExtent = v0.distanceTo( v1 ) * 0.5;
		double a01 = - this._direction.dot( _segDir );
		double b0 = _diff.dot( this._direction );
		double b1 = - _diff.dot( _segDir );
		double c = _diff.lengthSq();
		double det = Math.abs( 1 - a01 * a01 );
		double s0, s1, sqrDist, extDet;

		if ( det > 0 ) {

			// The ray and segment are not parallel.

			s0 = a01 * b1 - b0;
			s1 = a01 * b0 - b1;
			extDet = segExtent * det;

			if ( s0 >= 0 ) {

				if ( s1 >= - extDet ) {

					if ( s1 <= extDet ) {

						// region 0
						// Minimum at interior points of ray and segment.

						double invDet = 1 / det;
						s0 *= invDet;
						s1 *= invDet;
						sqrDist = s0 * ( s0 + a01 * s1 + 2 * b0 ) + s1 * ( a01 * s0 + s1 + 2 * b1 ) + c;

					} else {

						// region 1

						s1 = segExtent;
						s0 = Math.max( 0, - ( a01 * s1 + b0 ) );
						sqrDist = - s0 * s0 + s1 * ( s1 + 2 * b1 ) + c;

					}

				} else {

					// region 5

					s1 = - segExtent;
					s0 = Math.max( 0, - ( a01 * s1 + b0 ) );
					sqrDist = - s0 * s0 + s1 * ( s1 + 2 * b1 ) + c;

				}

			} else {

				if ( s1 <= - extDet ) {

					// region 4

					s0 = Math.max( 0, - ( - a01 * segExtent + b0 ) );
					s1 = ( s0 > 0 ) ? - segExtent : Math.min( Math.max( - segExtent, - b1 ), segExtent );
					sqrDist = - s0 * s0 + s1 * ( s1 + 2 * b1 ) + c;

				} else if ( s1 <= extDet ) {

					// region 3

					s0 = 0;
					s1 = Math.min( Math.max( - segExtent, - b1 ), segExtent );
					sqrDist = s1 * ( s1 + 2 * b1 ) + c;

				} else {

					// region 2

					s0 = Math.max( 0, - ( a01 * segExtent + b0 ) );
					s1 = ( s0 > 0 ) ? segExtent : Math.min( Math.max( - segExtent, - b1 ), segExtent );
					sqrDist = - s0 * s0 + s1 * ( s1 + 2 * b1 ) + c;

				}

			}

		} else {

			// Ray and segment are parallel.

			s1 = ( a01 > 0 ) ? - segExtent : segExtent;
			s0 = Math.max( 0, - ( a01 * s1 + b0 ) );
			sqrDist = - s0 * s0 + s1 * ( s1 + 2 * b1 ) + c;

		}

		if ( optionalPointOnRay != null ) {

			optionalPointOnRay.copy( this._direction ).multiplyScalar( s0 ).add( this._origin );

		}

		if ( optionalPointOnSegment != null ) {

			optionalPointOnSegment.copy( _segDir ).multiplyScalar( s1 ).add( _segCenter );

		}

		return sqrDist;

	}

	public Vector3 intersectSphere( Sphere sphere, Vector3 target ) {

		_vector.subVectors( sphere.center(), this._origin );
		double tca = _vector.dot( this._direction );
		double d2 = _vector.dot( _vector ) - tca * tca;
		double radius2 = sphere.radius() * sphere.radius();

		if ( d2 > radius2 ) return null;

		double thc = Math.sqrt( radius2 - d2 );

		// t0 = first intersect point - entrance on front of sphere
		double t0 = tca - thc;

		// t1 = second intersect point - exit point on back of sphere
		double t1 = tca + thc;

		// test to see if both t0 and t1 are behind the ray - if so, return null
		if ( t0 < 0 && t1 < 0 ) return null;

		// test to see if t0 is behind the ray:
		// if it is, the ray is inside the sphere, so return the second exit point scaled by t1,
		// in order to always return an intersect point that is in front of the ray.
		if ( t0 < 0 ) return this.at( t1, target );

		// else t0 is in front of the ray, so return the first collision point scaled by t0
		return this.at( t0, target );

	}

	public boolean intersectsSphere( Sphere sphere ) {

		return this.distanceSqToPoint( sphere.center() ) <= ( sphere.radius() * sphere.radius() );

	}

	public double distanceToPlane( Plane plane ) {

		double denominator = plane.normal().dot( this._direction );

		if ( denominator == 0 ) {

			// line is coplanar, return origin
			if ( plane.distanceToPoint( this._origin ) == 0 ) {

				return 0;

			}

			// Null is preferable to null since null means.... it is null

			return Double.NaN;

		}

		double t = - ( this._origin.dot( plane.normal() ) + plane.constant() ) / denominator;

		// Return if the ray never intersects the plane

		return t >= 0 ? t : Double.NaN;

	}

	public Vector3 intersectPlane( Plane plane, Vector3 target ) {

		double t = this.distanceToPlane( plane );

		if ( Double.isNaN(t) ) {

			return null;

		}

		return this.at( t, target );

	}

	public boolean intersectsPlane( Plane plane ) {

		// check if the ray lies on the plane first

		double distToPoint = plane.distanceToPoint( this._origin );

		if ( distToPoint == 0 ) {

			return true;

		}

		double denominator = plane.normal().dot( this._direction );

		if ( denominator * distToPoint < 0 ) {

			return true;

		}

		// ray origin is behind the plane (and is pointing behind it)

		return false;

	}

	public Vector3 intersectBox( Box3 box, Vector3 target ) {

		double tmin, tmax, tymin, tymax, tzmin, tzmax;

		double invdirx = 1. / this._direction.x(),
			invdiry = 1. / this._direction.y(),
			invdirz = 1. / this._direction.z();

		Vector3 origin = this._origin;

		if ( invdirx >= 0 ) {

			tmin = ( box.min().x() - origin.x() ) * invdirx;
			tmax = ( box.max().x() - origin.x() ) * invdirx;

		} else {

			tmin = ( box.max().x() - origin.x() ) * invdirx;
			tmax = ( box.min().x() - origin.x() ) * invdirx;

		}

		if ( invdiry >= 0 ) {

			tymin = ( box.min().y() - origin.y() ) * invdiry;
			tymax = ( box.max().y() - origin.y() ) * invdiry;

		} else {

			tymin = ( box.max().y() - origin.y() ) * invdiry;
			tymax = ( box.min().y() - origin.y() ) * invdiry;

		}

		if ( ( tmin > tymax ) || ( tymin > tmax ) ) return null;

		// These lines also handle the case where tmin or tmax is NaN
		// (result of 0 * Infinity). x != x returns true if x is NaN

		if ( tymin > tmin || tmin != tmin ) tmin = tymin;

		if ( tymax < tmax || tmax != tmax ) tmax = tymax;

		if ( invdirz >= 0 ) {

			tzmin = ( box.min().z() - origin.z() ) * invdirz;
			tzmax = ( box.max().z() - origin.z() ) * invdirz;

		} else {

			tzmin = ( box.max().z() - origin.z() ) * invdirz;
			tzmax = ( box.min().z() - origin.z() ) * invdirz;

		}

		if ( ( tmin > tzmax ) || ( tzmin > tmax ) ) return null;

		if ( tzmin > tmin || tmin != tmin ) tmin = tzmin;

		if ( tzmax < tmax || tmax != tmax ) tmax = tzmax;

		//return point closest to the ray (positive side)

		if ( tmax < 0 ) return null;

		return this.at( tmin >= 0 ? tmin : tmax, target );

	}

	public boolean intersectsBox( Box3 box ) {

		return this.intersectBox( box, _vector ) != null;

	}

	public Vector3 intersectTriangle( Vector3 a, Vector3 b, Vector3 c, boolean backfaceCulling, Vector3 target ) {

		// Compute the offset origin, edges, and normal.

		// from http://www.geometrictools.com/GTEngine/Include/Mathematics/GteIntrRay3Triangle3.h

		_edge1.subVectors( b, a );
		_edge2.subVectors( c, a );
		_normal.crossVectors( _edge1, _edge2 );

		// Solve Q + t*D = b1*E1 + b2*E2 (Q = kDiff, D = ray direction,
		// E1 = kEdge1, E2 = kEdge2, N = Cross(E1,E2)) by
		//   |Dot(D,N)|*b1 = sign(Dot(D,N))*Dot(D,Cross(Q,E2))
		//   |Dot(D,N)|*b2 = sign(Dot(D,N))*Dot(D,Cross(E1,Q))
		//   |Dot(D,N)|*t = -sign(Dot(D,N))*Dot(Q,N)
		double DdN = this._direction.dot( _normal );
		double sign;

		if ( DdN > 0 ) {

			if ( backfaceCulling ) return null;
			sign = 1;

		} else if ( DdN < 0 ) {

			sign = - 1;
			DdN = - DdN;

		} else {

			return null;

		}

		_diff.subVectors( this._origin, a );
		double DdQxE2 = sign * this._direction.dot( _edge2.crossVectors( _diff, _edge2 ) );

		// b1 < 0, no intersection
		if ( DdQxE2 < 0 ) {

			return null;

		}

		double DdE1xQ = sign * this._direction.dot( _edge1.cross( _diff ) );

		// b2 < 0, no intersection
		if ( DdE1xQ < 0 ) {

			return null;

		}

		// b1+b2 > 1, no intersection
		if ( DdQxE2 + DdE1xQ > DdN ) {

			return null;

		}

		// Line intersects triangle, check if ray does.
		double QdN = - sign * _diff.dot( _normal );

		// t < 0, no intersection
		if ( QdN < 0 ) {

			return null;

		}

		// Ray intersects triangle.
		return this.at( QdN / DdN, target );

	}

	public Ray applyMatrix4( Matrix4 matrix4 ) {

		this._origin.applyMatrix4( matrix4 );
		this._direction.transformDirection( matrix4 );

		return this;

	}

	public boolean equals( Ray ray ) {

		return ray._origin.equals( this._origin ) && ray._direction.equals( this._direction );

	}

}


