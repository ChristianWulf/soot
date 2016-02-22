package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public final class VariableDeclarationTag implements Tag {

	public static final VariableDeclarationTag INSTANCE = new VariableDeclarationTag();

	private VariableDeclarationTag() {}

	@Override
	public String getName() {
		return "VariableDeclarationTag";
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("VariableDeclarationTag has no value for bytecode");
	}

}
