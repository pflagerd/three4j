package net.three4j.math;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

import net.three4j.core.Geometry;
import net.three4j.core.Object3D;
import net.three4j.objects.Sprite;

//import { Vector3 } from './Vector3.js';
//import { Sphere } from './Sphere.js';
//import { Plane } from './Plane.js';

public class Frustum {

	private Plane _planes[] = new Plane[] {new Plane(), new Plane(), new Plane(), new Plane(), new Plane(), new Plane()};

	public Frustum() {
	}

	public Frustum( Plane p0, Plane p1, Plane p2, Plane p3, Plane p4, Plane p5 ) {
		_planes[0] = p0;
		_planes[1] = p1;
		_planes[2] = p2;
		_planes[3] = p3;
		_planes[4] = p4;
		_planes[5] = p5;
	}

	public Frustum set( Plane p0, Plane p1, Plane p2, Plane p3, Plane p4, Plane p5 ) {
		_planes[ 0 ].copy( p0 );
		_planes[ 1 ].copy( p1 );
		_planes[ 2 ].copy( p2 );
		_planes[ 3 ].copy( p3 );
		_planes[ 4 ].copy( p4 );
		_planes[ 5 ].copy( p5 );

		return this;
	}

	public Frustum clone() {

		return new Frustum().copy( this );

	}

	public Frustum copy( Frustum frustum ) {

		for ( int i = 0; i < _planes.length; i ++ ) {

			_planes[ i ].copy( frustum._planes[ i ] );

		}

		return this;
	}

	public Frustum setFromProjectionMatrix( Matrix4 m ) {

		double[] me = m.elements;
		double me0 = me[ 0 ], me1 = me[ 1 ], me2 = me[ 2 ], me3 = me[ 3 ];
		double me4 = me[ 4 ], me5 = me[ 5 ], me6 = me[ 6 ], me7 = me[ 7 ];
		double me8 = me[ 8 ], me9 = me[ 9 ], me10 = me[ 10 ], me11 = me[ 11 ];
		double me12 = me[ 12 ], me13 = me[ 13 ], me14 = me[ 14 ], me15 = me[ 15 ];

		_planes[ 0 ].setComponents( me3 - me0, me7 - me4, me11 - me8, me15 - me12 ).normalize();
		_planes[ 1 ].setComponents( me3 + me0, me7 + me4, me11 + me8, me15 + me12 ).normalize();
		_planes[ 2 ].setComponents( me3 + me1, me7 + me5, me11 + me9, me15 + me13 ).normalize();
		_planes[ 3 ].setComponents( me3 - me1, me7 - me5, me11 - me9, me15 - me13 ).normalize();
		_planes[ 4 ].setComponents( me3 - me2, me7 - me6, me11 - me10, me15 - me14 ).normalize();
		_planes[ 5 ].setComponents( me3 + me2, me7 + me6, me11 + me10, me15 + me14 ).normalize();

		return this;

	}

	public boolean intersectsObject( Object3D object ) {

		Geometry geometry = object.geometry();

		if ( geometry.boundingSphere() == null )
			geometry.computeBoundingSphere();

		_sphere.copy( geometry.boundingSphere() ).applyMatrix4( object.matrixWorld() );

		return this.intersectsSphere( _sphere );

	}

	public boolean intersectsSprite( Sprite sprite ) {

		_sphere.center().set( 0, 0, 0 );
		_sphere.radius(0.7071067811865476);
		_sphere.applyMatrix4( sprite.matrixWorld() );

		return this.intersectsSphere( _sphere );

	}

	public boolean intersectsSphere( Sphere sphere ) {

		Vector3 center = sphere.center();
		double negRadius = - sphere.radius();

		for ( int i = 0; i < _planes.length; i++ ) {

			double distance = _planes[ i ].distanceToPoint( center );

			if ( distance < negRadius ) {

				return false;

			}

		}

		return true;

	}

	public boolean intersectsBox( Box3 box ) {

		for ( int i = 0; i < _planes.length; i ++ ) {

			// corner at max distance
			_vector.x(_planes[i].normal().x() > 0 ? box.max().x() : box.min().x());
			_vector.y(_planes[i].normal().y() > 0 ? box.max().y() : box.min().y());
			_vector.z(_planes[i].normal().z() > 0 ? box.max().z() : box.min().z());

			if ( _planes[i].distanceToPoint( _vector ) < 0 ) {

				return false;

			}

		}

		return true;

	}

	public boolean containsPoint( Vector3 point ) {

		for ( int i = 0; i < _planes.length; i ++ ) {

			if ( _planes[ i ].distanceToPoint( point ) < 0 ) {

				return false;

			}

		}

		return true;

	}

	@Override
	public String toString() {
		SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
		sortedReflectionToStringBuilder.setExcludeFieldNames("isVector3");
		return sortedReflectionToStringBuilder.toString();
	}

	private final static Vector3 _vector = /*@__PURE__*/ new Vector3();
	private final static Sphere _sphere = /*@__PURE__*/ new Sphere();

}
