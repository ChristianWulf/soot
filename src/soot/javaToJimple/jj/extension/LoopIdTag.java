package soot.javaToJimple.jj.extension;

import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;
import soot.util.IdGenerator;

public class LoopIdTag implements Tag {

	private final long loopId;

	public LoopIdTag() {
		loopId = IdGenerator.getInstance().generateId();
	}

	public long getLoopId() {
		return loopId;
	}

	@Override
	public String getName() {
		return "LoopIdTag";
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("LoopIdTag has no value for bytecode");
	}

}
