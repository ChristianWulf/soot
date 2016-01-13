package soot.javaToJimple.jj.extension;

import soot.jimple.Stmt;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class RefersToTag implements Tag {

	private final Stmt stmt;

	public RefersToTag(final Stmt stmt) {
		this.stmt = stmt;
	}

	public Stmt getStmt() {
		return stmt;
	}

	@Override
	public String getName() {
		return "RefersToTag";
	}

	@Override
	public byte[] getValue() throws AttributeValueException {
		throw new RuntimeException("RefersToTag has no value for bytecode");
	}

}
