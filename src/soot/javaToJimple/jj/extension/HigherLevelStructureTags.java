package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public enum HigherLevelStructureTags implements Tag {

	FOR_INIT, FOR_COND, FOR_UPDATE, FOR_BODY, FOR_END,

	FOREACH_INIT, FOREACH_COND, FOREACH_BODY, FOREACH_END,

	IF_COND, IF_END,

	SWITCH_COND, SWITCH_CASE, SWITCH_END,

	TRY_BEGIN,

	WHILE_COND, WHILE_BODY, WHILE_END,

	DO_WHILE_BODY, DO_WHILE_COND, DO_WHILE_END,

	;

	@Override
	public String getName() {
		return "HigherLevelStructureTag";
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("HigherLevelStructureTags has no value for bytecode");
	}

}
