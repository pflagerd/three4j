package net.three4j;

public class Element {
	public static class Style {
		public String display;
	}

	public final Style style = new Style();

	private int _width;

	public int width() {
	  return _width;
	}

	public Element width(int width) {
	  this._width = width;
	  return this;
	}

	private int _height;

	public int height() {
	  return _height;
	}

	public Element height(int height) {
	  this._height = height;
	  return this;
	}

}
