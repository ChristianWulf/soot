package soot.javaToJimple.jj.extension;

import soot.JastAddJ.List;
import soot.JastAddJ.TypeVariable;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class TypeParametersTag implements Tag {

	private final List<TypeVariable> typeParameters;

	public TypeParametersTag(final List<TypeVariable> typeParameters) {
		this.typeParameters = typeParameters;
	}

	public List<TypeVariable> getTypeParameters() {
		return typeParameters;
	}

	@Override
	public String getName() {
		return "TypeParametersTag";
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("TypeParametersTag has no value for bytecode");
	}

}
