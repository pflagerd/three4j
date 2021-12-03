package net.three4j;


public interface Document {
	static class DocumentClass implements Document {

	}

	public default Element createElementNS(String ns, String tag) {
		return new Element();
	}

	public final static Document document = new DocumentClass();

}
