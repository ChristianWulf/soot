package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public final class SyntheticLocalVarTag implements Tag {

	public static final SyntheticLocalVarTag INSTANCE = new SyntheticLocalVarTag();
	public static final String NAME = "SyntheticLocalVarTag";

	private SyntheticLocalVarTag() {}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException(NAME + " has no value for bytecode");
	}

}
