package soot.javaToJimple.jj.extension;

import java.util.ArrayList;
import java.util.List;

import soot.JastAddJ.TypeVariable;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class TypeParametersTag implements Tag {

	private final List<TypeVariable> typeParameters = new ArrayList<TypeVariable>();

	public TypeParametersTag(final soot.JastAddJ.List<TypeVariable> typeParameters) {
		for (int i = 0; i < typeParameters.getNumChild(); i++) {
			TypeVariable typeVariable = typeParameters.getChild(i);
			this.typeParameters.add(typeVariable);
		}
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
