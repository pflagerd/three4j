package net.three4j.core;

public class Layers {
	
	public int mask;

	public Layers() {

		this.mask = 1;

	}

	public Layers set( int channel ) {

		this.mask = 1 << channel;
		return this;
	}

	public Layers enable( int channel ) {

		this.mask |= 1 << channel;
		return this;
	}

	public Layers enableAll() {

		this.mask = 0xffffffff;
		return this;
	}

	public Layers toggle( int channel ) {

		this.mask ^= 1 << channel;
		return this;
	}

	public Layers disable( int channel ) {

		this.mask &= ~ ( 1 << channel );
		return this;
	}

	public Layers disableAll() {

		this.mask = 0;
		return this;
	}

	public boolean test( Layers layers ) {

		return ( this.mask & layers.mask ) != 0;
	}
	
	@Override
	public String toString() {
		return super.toString() + "() {mask:" + mask + "}";
	}

}
