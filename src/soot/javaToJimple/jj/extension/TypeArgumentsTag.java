package soot.javaToJimple.jj.extension;

import java.util.ArrayList;
import java.util.List;

import soot.JastAddJ.Access;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class TypeArgumentsTag implements Tag {

	public static final String NAME = "TypeArgumentsTag";

	private final List<Access> typeArguments = new ArrayList<Access>();

	public TypeArgumentsTag(final soot.JastAddJ.List<Access> list) {
		for (int i = 0; i < list.getNumChild(); i++) {
			Access typeArgument = list.getChild(i);
			this.typeArguments.add(typeArgument);
		}
	}

	public List<Access> getTypeArguments() {
		return typeArguments;
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
