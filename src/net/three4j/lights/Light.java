package net.three4j.lights;

import net.three4j.core.Object3D;
import net.three4j.math.Color;

public class Light extends Object3D {
	private Color _color;
	private double _intensity;

	public Light(Color color) {
		this(color, 1);
	}

	public Light(Color color, double intensity) {
		super();

		this._color = new Color(color);
		this._intensity = intensity;

	}

	public Light copy(Light source) {

		super.copy(source);

		this._color.copy(source._color);
		this._intensity = source._intensity;

		return this;

	}

//	public toJSON  ( meta ) {
//
//		const data = Object3D.prototype.toJSON.call( this, meta );
//
//		data.object.color = this.color.getHex();
//		data.object.intensity = this.intensity;
//
//		if ( this.groundColor !== undefined ) data.object.groundColor = this.groundColor.getHex();
//
//		if ( this.distance !== undefined ) data.object.distance = this.distance;
//		if ( this.angle !== undefined ) data.object.angle = this.angle;
//		if ( this.decay !== undefined ) data.object.decay = this.decay;
//		if ( this.penumbra !== undefined ) data.object.penumbra = this.penumbra;
//
//		if ( this.shadow !== undefined ) data.object.shadow = this.shadow.toJSON();
//
//		return data;
//
//	}

}
