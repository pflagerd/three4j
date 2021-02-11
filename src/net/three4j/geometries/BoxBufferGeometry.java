package net.three4j.geometries;

import org.apache.commons.lang3.ArrayUtils;

import net.three4j.core.BufferGeometry;
import net.three4j.core.Float32BufferAttribute;
import net.three4j.math.Vector3;

public class BoxBufferGeometry extends BufferGeometry {

	public static class Parameters {
		public Parameters(double width, double height, double depth, int widthSegments, int heightSegments, int depthSegments) {
			this._width = width;
			this._height = height;
			this._depth = depth;
			this._widthSegments = widthSegments;
			this._heightSegments = heightSegments;
			this._depthSegments = depthSegments;
		}

		private double _width;

		public double width() {
			return _width;
		}

		public Parameters width(double width) {
			this._width = width;
			return this;
		}

		private double _height;

		public double height() {
			return _height;
		}

		public Parameters height(double height) {
			this._height = height;
			return this;
		}

		private double _depth;

		public double depth() {
			return _depth;
		}

		public Parameters depth(double depth) {
			this._depth = depth;
			return this;
		}

		private int _widthSegments;

		public int widthSegments() {
			return _widthSegments;
		}

		public Parameters widthSegments(int widthSegments) {
			this._widthSegments = widthSegments;
			return this;
		}

		private int _heightSegments;

		public int heightSegments() {
			return _heightSegments;
		}

		public Parameters heightSegments(int heightSegments) {
			this._heightSegments = heightSegments;
			return this;
		}

		private int _depthSegments;

		public int depthSegments() {
			return _depthSegments;
		}

		public Parameters depthSegments(int depthSegments) {
			this._depthSegments = depthSegments;
			return this;
		}

	}

	private Parameters _parameters;

	public Parameters parameters() {
		return _parameters;
	}

	public BoxBufferGeometry parameters(Parameters parameters) {
		this._parameters = parameters;
		return this;
	}

	public BoxBufferGeometry() {
		this(1, 1, 1, 1, 1, 1);
	}

	public BoxBufferGeometry(double width, double height, double depth) {
		this(width, height, depth, 1, 1, 1);
	}

	private double[] _indices;
	private double[] _vertices;
	private double[] _normals;
	private double[] _uvs;

	public BoxBufferGeometry(double width, double height, double depth, int widthSegments, int heightSegments, int depthSegments) {

		super();

		this._parameters = new Parameters(width, height, depth, widthSegments, heightSegments, depthSegments);

		// segments

		this._widthSegments = widthSegments;
		this._heightSegments = heightSegments;
		this._depthSegments = depthSegments;

		// buffers

		_indices = new double[0];
		_vertices = new double[0];
		_normals = new double[0];
		_uvs = new double[0];

		numberOfVertices = 0;
		groupStart = 0;

		// build each side of the box geometry

		buildPlane("z", "y", "x", -1, -1, depth, height, width, depthSegments, heightSegments, 0); // px
		buildPlane("z", "y", "x",  1, -1, depth, height, -width, depthSegments, heightSegments, 1); // nx
		buildPlane("x", "z", "y",  1,  1, width, depth, height, widthSegments, depthSegments, 2); // py
		buildPlane("x", "z", "y",  1, -1, width, depth, -height, widthSegments, depthSegments, 3); // ny
		buildPlane("x", "y", "z",  1, -1, width, height, depth, widthSegments, heightSegments, 4); // pz
		buildPlane("x", "y", "z", -1, -1, width, height, -depth, widthSegments, heightSegments, 5); // nz

		// build geometry

		this.setIndex( _indices );
		this.setAttribute( "position", new Float32BufferAttribute( _vertices, 3 ) );
		this.setAttribute( "normal", new Float32BufferAttribute( _normals, 3 ) );
		this.setAttribute( "uv", new Float32BufferAttribute( _uvs, 2 ) );
	}

	private int numberOfVertices = 0;
	private int groupStart = 0;

	private void buildPlane(String u, String v, String w, double udir, double vdir, double width, double height, double depth, int gridX, int gridY, int materialIndex) {

		double segmentWidth = width / gridX;
		double segmentHeight = height / gridY;

		double widthHalf = width / 2;
		double heightHalf = height / 2;
		double depthHalf = depth / 2;

		int gridX1 = gridX + 1;
		int gridY1 = gridY + 1;

		int vertexCounter = 0;
		int groupCount = 0;

		Vector3 vector = new Vector3();

		// generate vertices, normals and uvs

		for (int iy = 0; iy < gridY1; iy++) {

			double y = iy * segmentHeight - heightHalf;

			for (int ix = 0; ix < gridX1; ix++) {

				double x = ix * segmentWidth - widthHalf;

				// set values to correct vector component

				switch (u) {
					case "x":
						vector.x(x * udir);
						break;

					case "y":
						vector.y(x * udir);
						break;

					case "z":
						vector.z(x * udir);
						break;
				}

				switch (v) {
					case "x":
						vector.x(y * vdir);
						break;

					case "y":
						vector.y(y * vdir);
						break;

					case "z":
						vector.z(y * vdir);
						break;
				}

				switch (w) {
					case "x":
						vector.x(depthHalf);
						break;

					case "y":
						vector.y(depthHalf);
						break;

					case "z":
						vector.z(depthHalf);
						break;
				}

				// now apply vector to vertex buffer

				_vertices = ArrayUtils.addAll(_vertices, vector.x(), vector.y(), vector.z());

				// set values to correct vector component
				switch (u) {
					case "x":
						vector.x(0);
						break;

					case "y":
						vector.y(0);
						break;

					case "z":
						vector.z(0);
						break;
				}

				switch (v) {
					case "x":
						vector.x(0);
						break;

					case "y":
						vector.y(0);
						break;

					case "z":
						vector.z(0);
						break;
				}

				switch (w) {
					case "x":
						vector.x(depth > 0 ? 1 : -1);
						break;

					case "y":
						vector.y(depth > 0 ? 1 : -1);
						break;

					case "z":
						vector.z(depth > 0 ? 1 : -1);
						break;
				}

				// now apply vector to normal buffer

				_normals = ArrayUtils.addAll(_normals, vector.x(), vector.y(), vector.z());

				// uvs
				_uvs = ArrayUtils.addAll(_uvs, ix / gridX, 1 - (iy / gridY));

				// counters

				vertexCounter += 1;

			}
		}

		// indices

		// 1. you need three indices to draw a single face
		// 2. a single segment consists of two faces
		// 3. so we need to generate six (2*3) indices per segment

		for (int iy = 0; iy < gridY; iy++) {

			for (int ix = 0; ix < gridX; ix++) {

				double a = numberOfVertices + ix + gridX1 * iy;
				double b = numberOfVertices + ix + gridX1 * (iy + 1);
				double c = numberOfVertices + (ix + 1) + gridX1 * (iy + 1);
				double d = numberOfVertices + (ix + 1) + gridX1 * iy;

				// faces

				_indices = ArrayUtils.addAll(_indices, a, b, d, b, c, d);

				// increase counter

				groupCount += 6;

			}

		}

		// add a group to the geometry. this will ensure multi material support

		this.addGroup(groupStart, groupCount, materialIndex);

		// calculate new start value for groups

		groupStart += groupCount;

		// update total number of vertices

		numberOfVertices += vertexCounter;

	}

	private int _widthSegments;

	public int widthSegments() {
		return _widthSegments;
	}

	public BoxBufferGeometry widthSegments(int widthSegments) {
		this._widthSegments = widthSegments;
		return this;
	}

	private int _heightSegments;

	public int heightSegments() {
		return _heightSegments;
	}

	public BoxBufferGeometry heightSegments(int heightSegments) {
		this._heightSegments = heightSegments;
		return this;
	}

	private int _depthSegments;

	public int depthSegments() {
		return _depthSegments;
	}

	public BoxBufferGeometry depthSegments(int depthSegments) {
		this._depthSegments = depthSegments;
		return this;
	}

}
