package soot.javaToJimple.jj.extension;

import soot.Body;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class BodyTag implements Tag {

	public static final String NAME = "BodyTag";

	private final Body body;

	public BodyTag(Body body) {
		this.body = body;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException(NAME + " has no value for bytecode");
	}
}
