package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public enum HigherLevelStructureTags implements Tag {

	FOR_INIT, FOR_COND, FOR_BODY, FOR_UPDATE, FOR_END,

	IF_END, ;

	@Override
	public String getName() {
		return name();
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("HigherLevelStructureTags has no value for bytecode");
	}

}
