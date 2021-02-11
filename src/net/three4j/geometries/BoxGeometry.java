package net.three4j.geometries;

import org.apache.commons.lang3.builder.SortedReflectionToStringBuilder;
import org.apache.commons.lang3.builder.Three4jToStringStyle;

import net.three4j.core.Geometry;

public class BoxGeometry extends Geometry {

	private String _type;

	static class Parameters {
		public Parameters(double width, double height, double depth, int widthSegments, int heightSegments, int depthSegments) {
			_width = width;
			_height = height;
			_depth = depth;
			_widthSegments = widthSegments;
			_heightSegments = heightSegments;
			_depthSegments = depthSegments;
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

		@Override
		public String toString() {
			SortedReflectionToStringBuilder sortedReflectionToStringBuilder = new SortedReflectionToStringBuilder(this, Three4jToStringStyle.THREE4J_STYLE);
			// sortedReflectionToStringBuilder.setExcludeFieldNames("_m1", "_q1", "_v1", "isObject3D", "_geometry", "listeners", "_id", "_modelViewMatrix", "_normalMatrix", "_target", "_xAxis", "_yAxis", "_zAxis");
			return sortedReflectionToStringBuilder.toString();
		}


	}

	private Parameters _parameters;

	public BoxGeometry(double width, double height, double depth) {
		this(width, height, depth, 1, 1, 1);
	}

	public BoxGeometry(double width, double height, double depth, int widthSegments, int heightSegments, int depthSegments) {
		super();

		_type = "BoxGeometry";

		_parameters = new Parameters(width, height, depth, widthSegments, heightSegments, depthSegments);

		this.fromBufferGeometry(new BoxBufferGeometry(width, height, depth, widthSegments, heightSegments, depthSegments));
//		this.mergeVertices();

	}

}
