package net.three4j.core;

import net.three4j.math.Color;
import net.three4j.math.Vector3;

public class Face3 {

	public Face3() {

	}

	public Face3(int a, int b, int c, Vector3 normal, Color color) {
		this(a, b, c, normal, color, 0);
	}

	public Face3(int a, int b, int c, Vector3[] normals, Color color, int materialIndex) {
		this._a = a;
		this._b = b;
		this._c = c;

		this._vertexNormals = normals != null ? normals : new Vector3[0];

		this._color = color != null ? color : new Color();

		this._materialIndex = materialIndex;

	}

	public Face3(int a, int b, int c, Vector3 normal, Color[] colors, int materialIndex) {
		this._a = a;
		this._b = b;
		this._c = c;

		this._normal = normal != null ? normal : new Vector3();

		this._vertexColors = colors != null ? colors : new Color[0];

		this._materialIndex = materialIndex;

	}

	public Face3(int a, int b, int c, Vector3[] normals, Color[] colors, int materialIndex) {
		this._a = a;
		this._b = b;
		this._c = c;

		this._vertexNormals = normals != null ? normals : new Vector3[0];

		this._vertexColors = colors != null ? colors : new Color[0];

		this._materialIndex = materialIndex;

	}

	public Face3(int a, int b, int c, Vector3 normal, Color color, int materialIndex) {

		this._a = a;
		this._b = b;
		this._c = c;

		this._normal = normal != null ? normal : new Vector3();
//		this.vertexNormals = Array.isArray( normal ) ? normal : [];

		this._color = color != null ? color : new Color();
//		this.vertexColors = Array.isArray( color ) ? color : [];

		this._materialIndex = materialIndex;

	}

	private int _a = 0;

	public int a() {
		return _a;
	}

	public Face3 a(int a) {
		this._a = a;
		return this;
	}

	private int _b = 0;

	public int b() {
		return _b;
	}

	public Face3 b(int b) {
		this._b = b;
		return this;
	}

	private int _c = 0;

	public int c() {
		return _c;
	}

	public Face3 c(int c) {
		this._c = c;
		return this;
	}

	private Vector3 _normal = new Vector3();

	public Vector3 normal() {
		return _normal;
	}

	public Face3 normal(Vector3 normal) {
		this._normal = normal;
		return this;
	}

	private Color _color = new Color();

	public Color color() {
		return _color;
	}

	public Face3 color(Color color) {
		this._color = color;
		return this;
	}

	private int _materialIndex;

	public int materialIndex() {
		return _materialIndex;
	}

	public Face3 materialIndex(int materialIndex) {
		this._materialIndex = materialIndex;
		return this;
	}

	private Vector3[] _vertexNormals = new Vector3[0];

	public Vector3[] vertexNormals() {
		return _vertexNormals;
	}

	public Face3 vertexNormals(Vector3[] vertexNormals) {
		this._vertexNormals = vertexNormals;
		return this;
	}

	private Color[] _vertexColors = new Color[0];

	public Color[] vertexColors() {
		return _vertexColors;
	}

	public Face3 vertexColors(Color[] vertexColors) {
		this._vertexColors = vertexColors;
		return this;
	}

	public Face3 clone() {

		return new Face3().copy( this );

	}

	public Face3 copy( Face3 source ) {

		this._a = source._a;
		this._b = source._b;
		this._c = source._c;

		this._normal.copy( source._normal );
		this._color.copy( source._color );

		this._materialIndex = source._materialIndex;

		this._vertexNormals = new Vector3[source._vertexNormals.length];
		for ( int i = 0, il = source._vertexNormals.length; i < il; i ++ ) {

			this._vertexNormals[ i ] = source._vertexNormals[ i ].clone();

		}

		this._vertexColors = new Color[source._vertexColors.length];
		for ( int i = 0, il = source._vertexColors.length; i < il; i ++ ) {

			this._vertexColors[ i ] = source._vertexColors[ i ].clone();

		}

		return this;

	}

}
