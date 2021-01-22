package net.three4j.math;

import static net.three4j.THREE.console;

public class Triangle {
	private Vector3 _a = new Vector3();

	public Vector3 a() {
		return _a;
	}

	public Triangle a(Vector3 a) {
		this._a = a;
		return this;
	}

	private Vector3 _b = new Vector3();

	public Vector3 b() {
		return _b;
	}

	public Triangle b(Vector3 b) {
		this._b = b;
		return this;
	}

	private Vector3 _c = new Vector3();

	public Vector3 c() {
		return _c;
	}

	public Triangle c(Vector3 c) {
		this._c = c;
		return this;
	}
	
	public Triangle() {
		
	}

	public Triangle(Vector3 a, Vector3 b, Vector3 c) {

		this._a = (a != null) ? a : new Vector3();
		this._b = (b != null) ? b : new Vector3();
		this._c = (c != null) ? c : new Vector3();

	}

	final static Vector3 _v0 = /* @__PURE__ */ new Vector3();
	final static Vector3 _v1 = /* @__PURE__ */ new Vector3();
	final static Vector3 _v2 = /* @__PURE__ */ new Vector3();
	final static Vector3 _v3 = /* @__PURE__ */ new Vector3();

	final static Vector3 _vab = /* @__PURE__ */ new Vector3();
	final static Vector3 _vac = /* @__PURE__ */ new Vector3();
	final static Vector3 _vbc = /* @__PURE__ */ new Vector3();
	final static Vector3 _vap = /* @__PURE__ */ new Vector3();
	final static Vector3 _vbp = /* @__PURE__ */ new Vector3();
	final static Vector3 _vcp = /* @__PURE__ */ new Vector3();

	public static Vector3 getNormal( Vector3 a, Vector3 b, Vector3 c, Vector3 target ) {

		if ( target == null ) {

			console.warn( "THREE.Triangle.getNormal(a, b, c, null) is not allowed" );
			target = new Vector3();

		}

		target.subVectors( c, b );
		_v0.subVectors( a, b );
		target.cross( _v0 );

		double targetLengthSq = target.lengthSq();
		if ( targetLengthSq > 0 ) {

			return target.multiplyScalar( 1 / Math.sqrt( targetLengthSq ) );

		}

		return target.set( 0, 0, 0 );

	}

	// static/instance method to calculate barycentric coordinates
	// based on: http://www.blackpawn.com/texts/pointinpoly/default.html
	public static Vector3 getBarycoord( Vector3 point, Vector3 a, Vector3 b, Vector3 c, Vector3 target ) {

		_v0.subVectors( c, a );
		_v1.subVectors( b, a );
		_v2.subVectors( point, a );

		final double dot00 = _v0.dot( _v0 );
		final double dot01 = _v0.dot( _v1 );
		final double dot02 = _v0.dot( _v2 );
		final double dot11 = _v1.dot( _v1 );
		final double dot12 = _v1.dot( _v2 );

		final double denom = ( dot00 * dot11 - dot01 * dot01 );

		if ( target == null ) {

			console.warn( "THREE.Triangle: .getBarycoord() target is now required" );
			target = new Vector3();

		}

		// collinear or singular triangle
		if ( denom == 0 ) {

			// arbitrary location outside of triangle?
			// not sure if this is the best idea, maybe should be returning null
			return target.set( - 2, - 1, - 1 );

		}

		final double invDenom = 1 / denom;
		final double u = ( dot11 * dot02 - dot01 * dot12 ) * invDenom;
		final double v = ( dot00 * dot12 - dot01 * dot02 ) * invDenom;

		// barycentric coordinates must always sum to 1
		return target.set( 1 - u - v, v, u );

	}

	static public boolean containsPoint( Vector3 point, Vector3 a, Vector3 b, Vector3 c ) {

		Triangle.getBarycoord( point, a, b, c, _v3 );

		return ( _v3.x() >= 0 ) && ( _v3.y() >= 0 ) && ( ( _v3.x() + _v3.y() ) <= 1 );

	}

	static public Vector3 getUV( Vector3 point, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 uv1, Vector3 uv2, Vector3 uv3, Vector3 target ) {

		Triangle.getBarycoord( point, p1, p2, p3, _v3 );

		target.set( 0, 0 );
		target.addScaledVector( uv1, _v3.x() );
		target.addScaledVector( uv2, _v3.y() );
		target.addScaledVector( uv3, _v3.z() );

		return target;

	}

	static public boolean isFrontFacing( Vector3 a, Vector3 b, Vector3 c, Vector3 direction ) {

		_v0.subVectors( c, b );
		_v1.subVectors( a, b );

		// strictly front facing
		return ( _v0.cross( _v1 ).dot( direction ) < 0 ) ? true : false;
	}

	public Triangle set( Vector3 a, Vector3 b, Vector3 c ) {

		this._a.copy( a );
		this._b.copy( b );
		this._c.copy( c );

		return this;

	}

	public Triangle setFromPointsAndIndices( Vector3[] points, int i0, int i1, int i2 ) {

		this._a.copy( points[ i0 ] );
		this._b.copy( points[ i1 ] );
		this._c.copy( points[ i2 ] );

		return this;

	}

	public Triangle clone() {

		return new Triangle().copy( this );

	}

	public Triangle copy( Triangle triangle ) {

		this._a.copy( triangle._a );
		this._b.copy( triangle._b );
		this._c.copy( triangle._c );

		return this;

	}

	public double getArea() {

		_v0.subVectors( this._c, this._b );
		_v1.subVectors( this._a, this._b );

		return _v0.cross( _v1 ).length() * 0.5;

	}

	public Vector3  getMidpoint( Vector3 target ) {

		if ( target == null ) {
			console.warn( "THREE.TrianglegetMidpoint(null) is not allowed" );
			target = new Vector3();
		}

		return target.addVectors( this._a, this._b ).add( this._c ).multiplyScalar( 1. / 3 );

	}

	public Vector3 getNormal( Vector3 target ) {

		return Triangle.getNormal( this._a, this._b, this._c, target );

	}

	public Plane getPlane( Plane target ) {

		if ( target == null ) {
			target = new Plane();
		}

		return target.setFromCoplanarPoints( this._a, this._b, this._c );

	}

	public Vector3 getBarycoord( Vector3 point, Vector3 target ) {

		return Triangle.getBarycoord( point, this._a, this._b, this._c, target );

	}

	public Vector3 getUV( Vector3 point, Vector3 uv1, Vector3 uv2, Vector3 uv3, Vector3 target ) {

		return Triangle.getUV( point, this._a, this._b, this._c, uv1, uv2, uv3, target );

	}

	public boolean containsPoint( Vector3 point ) {

		return Triangle.containsPoint( point, this._a, this._b, this._c );

	}

	public boolean isFrontFacing( Vector3 direction ) {

		return Triangle.isFrontFacing( this._a, this._b, this._c, direction );

	}

	public boolean intersectsBox( Box3 box ) {

		return box.intersectsTriangle( this );

	}

	public Vector3 closestPointToPoint( Vector3 p, Vector3 target ) {

		if ( target == null ) {
			target = new Vector3();
		}

		Vector3 a = this._a, b = this._b, c = this._c;
		double v, w;

		// algorithm thanks to Real-Time Collision Detection by Christer Ericson,
		// published by Morgan Kaufmann Publishers, (c) 2005 Elsevier Inc.,
		// under the accompanying license; see chapter 5.1.5 for detailed explanation.
		// basically, we're distinguishing which of the voronoi regions of the triangle
		// the point lies in with the minimum amount of redundant computation.

		_vab.subVectors( b, a );
		_vac.subVectors( c, a );
		_vap.subVectors( p, a );
		double d1 = _vab.dot( _vap );
		double d2 = _vac.dot( _vap );
		if ( d1 <= 0 && d2 <= 0 ) {

			// vertex region of A; barycentric coords (1, 0, 0)
			return target.copy( a );

		}

		_vbp.subVectors( p, b );
		double d3 = _vab.dot( _vbp );
		double d4 = _vac.dot( _vbp );
		if ( d3 >= 0 && d4 <= d3 ) {

			// vertex region of B; barycentric coords (0, 1, 0)
			return target.copy( b );

		}

		double vc = d1 * d4 - d3 * d2;
		if ( vc <= 0 && d1 >= 0 && d3 <= 0 ) {
			v = d1 / ( d1 - d3 );
			// edge region of AB; barycentric coords (1-v, v, 0)
			return target.copy( a ).addScaledVector( _vab, v );

		}

		_vcp.subVectors( p, c );
		double d5 = _vab.dot( _vcp );
		double d6 = _vac.dot( _vcp );
		if ( d6 >= 0 && d5 <= d6 ) {

			// vertex region of C; barycentric coords (0, 0, 1)
			return target.copy( c );

		}

		double vb = d5 * d2 - d1 * d6;
		if ( vb <= 0 && d2 >= 0 && d6 <= 0 ) {

			w = d2 / ( d2 - d6 );
			// edge region of AC; barycentric coords (1-w, 0, w)
			return target.copy( a ).addScaledVector( _vac, w );

		}

		double va = d3 * d6 - d5 * d4;
		if ( va <= 0 && ( d4 - d3 ) >= 0 && ( d5 - d6 ) >= 0 ) {

			_vbc.subVectors( c, b );
			w = ( d4 - d3 ) / ( ( d4 - d3 ) + ( d5 - d6 ) );
			// edge region of BC; barycentric coords (0, 1-w, w)
			return target.copy( b ).addScaledVector( _vbc, w ); // edge region of BC

		}

		// face region
		double denom = 1 / ( va + vb + vc );
		// u = va * denom
		v = vb * denom;
		w = vc * denom;

		return target.copy( a ).addScaledVector( _vab, v ).addScaledVector( _vac, w );

	}

	public boolean equals( Triangle triangle ) {

		return triangle._a.equals( this._a ) && triangle._b.equals( this._b ) && triangle._c.equals( this._c );

	}

}
