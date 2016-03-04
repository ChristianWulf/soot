package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public final class FinalTag implements Tag {

	public static final FinalTag INSTANCE = new FinalTag();
	public static final String NAME = "FinalTag";

	private FinalTag() {}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException(NAME + " has no value for bytecode");
	}

}
