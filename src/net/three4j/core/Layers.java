package net.three4j.core;

import org.apache.commons.lang3.builder.Three4jToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.UnsortedReflectionToStringBuilder;

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

	// DPP: later
	// public int hashCode() {}

	public boolean equals(Layers layers) {
		return mask == layers.mask;
	}

	@Override
	public String toString() {
		UnsortedReflectionToStringBuilder unsortedReflectionToStringBuilder = new UnsortedReflectionToStringBuilder(this, new Three4jToStringStyle());
		//unsortedReflectionToStringBuilder.setExcludeFieldNames("isMatrix4");
		return unsortedReflectionToStringBuilder.toString();
	}

}
