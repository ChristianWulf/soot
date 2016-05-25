package soot.jimple.internal;

import soot.jimple.Stmt;

/**
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
}
