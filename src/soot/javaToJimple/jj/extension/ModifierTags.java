package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public enum ModifierTags implements Tag {

	FINAL, SYNTHETIC;

	public static final String NAME = "ModifierTags";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException(NAME + " has no value for bytecode");
	}
}
