package net.three4j.geometries;

import net.three4j.core.Geometry;

public class BoxGeometry extends Geometry {

	private double _width;

	public double width() {
		return _width;
	}

	public BoxGeometry width(double width) {
		this._width = width;
		return this;
	}

	private double _height;

	public double height() {
		return _height;
	}

	public BoxGeometry height(double height) {
		this._height = height;
		return this;
	}

	private double _depth;

	public double depth() {
		return _depth;
	}

	public BoxGeometry depth(double depth) {
		this._depth = depth;
		return this;
	}

	private int _widthSegments;

	public int widthSegments() {
		return _widthSegments;
	}

	public BoxGeometry widthSegments(int widthSegments) {
		this._widthSegments = widthSegments;
		return this;
	}

	private int _heightSegments;

	public int heightSegments() {
		return _heightSegments;
	}

	public BoxGeometry heightSegments(int heightSegments) {
		this._heightSegments = heightSegments;
		return this;
	}

	private int _depthSegments;

	public int depthSegments() {
		return _depthSegments;
	}

	public BoxGeometry depthSegments(int depthSegments) {
		this._depthSegments = depthSegments;
		return this;
	}

	public BoxGeometry(double width, double height, double depth, int widthSegments, int heightSegments, int depthSegments) {
		super();

		_width = width;
		_height = height;
		_depth = depth;
		_widthSegments = widthSegments;
		_heightSegments = heightSegments;
		_depthSegments = depthSegments;

//		this.fromBufferGeometry(new BoxBufferGeometry(width, height, depth, widthSegments, heightSegments, depthSegments));
//		this.mergeVertices();

	}

}
