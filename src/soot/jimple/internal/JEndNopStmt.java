package soot.jimple.internal;

import soot.jimple.Stmt;

/**
 * Represents a JNopStmt which holds the condition statement of the associated loop.
 *
 * @author Christian Wulf (chw)
 */
public class JEndNopStmt extends JNopStmt {

	private static final long serialVersionUID = 9058642543692764987L;
	private final Stmt beginCond;

	public JEndNopStmt(Stmt beginCond) {
		this.beginCond = beginCond;
	}

	public Stmt getBeginCond() {
		return beginCond;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder()
			.append(super.toString())
			.append(": ")
			.append("[")
			.append("beginCond: ")
			.append(beginCond)
			.append("]");
		return builder.toString();
	}
}
