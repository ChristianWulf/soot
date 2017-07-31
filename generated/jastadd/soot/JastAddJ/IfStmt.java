/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

import soot.javaToJimple.jj.extension.HigherLevelStructureTags;
import soot.jimple.internal.JEndNopStmt;

/**
 * @production IfStmt : {@link Stmt} ::= <span class="component">Condition:{@link Expr}</span>
 *             <span class="component">Then:{@link Stmt}</span> <span class="component">[Else:{@link Stmt}]</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/java.ast:207
 */
public class IfStmt extends Stmt implements Cloneable {

	/**
	 * @author chw
	 */
	private static boolean alwaysProduceEndBranchStmt = Boolean.getBoolean("alwaysProduceEndBranchStmt");

	/**
	 * @apilevel low-level
	 */
	@Override
	public void flushCache() {
		super.flushCache();
		isDAafter_Variable_values = null;
		isDUafter_Variable_values = null;
		canCompleteNormally_computed = false;
		else_branch_label_computed = false;
		else_branch_label_value = null;
		then_branch_label_computed = false;
		then_branch_label_value = null;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	public void flushCollectionCache() {
		super.flushCollectionCache();
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public IfStmt clone() throws CloneNotSupportedException {
		IfStmt node = (IfStmt) super.clone();
		node.isDAafter_Variable_values = null;
		node.isDUafter_Variable_values = null;
		node.canCompleteNormally_computed = false;
		node.else_branch_label_computed = false;
		node.else_branch_label_value = null;
		node.then_branch_label_computed = false;
		node.then_branch_label_value = null;
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public IfStmt copy() {
		try {
			IfStmt node = clone();
			node.parent = null;
			if (children != null) {
				node.children = children.clone();
			}
			return node;
		} catch (CloneNotSupportedException e) {
			throw new Error("Error: clone not supported for " + getClass().getName());
		}
	}

	/**
	 * Create a deep copy of the AST subtree at this node. The copy is dangling, i.e. has no parent.
	 *
	 * @return dangling copy of the subtree at this node
	 * @apilevel low-level
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public IfStmt fullCopy() {
		IfStmt tree = copy();
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				ASTNode child = children[i];
				if (child != null) {
					child = child.fullCopy();
					tree.setChild(child, i);
				}
			}
		}
		return tree;
	}

	/**
	 * @ast method
	 * @aspect NodeConstructors
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NodeConstructors.jrag:66
	 */
	public IfStmt(final Expr cond, final Stmt thenBranch) {
		this(cond, thenBranch, new Opt());
	}

	/**
	 * @ast method
	 * @aspect NodeConstructors
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NodeConstructors.jrag:70
	 */
	public IfStmt(final Expr cond, final Stmt thenBranch, final Stmt elseBranch) {
		this(cond, thenBranch, new Opt(elseBranch));
	}

	/**
	 * @ast method
	 * @aspect PrettyPrint
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/PrettyPrint.jadd:574
	 */
	@Override
	public void toString(final StringBuffer s) {
		s.append(indent());
		s.append("if(");
		getCondition().toString(s);
		s.append(") ");
		getThen().toString(s);
		if (hasElse()) {
			s.append(indent());
			s.append("else ");
			getElse().toString(s);
		}
	}

	/**
	 * @ast method
	 * @aspect TypeCheck
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/TypeCheck.jrag:316
	 */
	@Override
	public void typeCheck() {
		TypeDecl cond = getCondition().type();
		if (!cond.isBoolean()) {
			error("the type of \"" + getCondition() + "\" is " + cond.name() + " which is not boolean");
		}
	}

	/**
	 * @ast method
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:115
	 */
	@Override
	public void jimplify2(final Body b) {
		soot.jimple.Stmt beginCond = beginCondLabel();
		b.addLabel(beginCond);
		// chw: this optimization should not be made while constructing the
		// jimple representation;
		// instead it should be performed afterwards to separate construction
		// from optimization
		// if (getCondition().isConstant()) {
		// if (getCondition().isTrue()) {
		// getThen().jimplify2(b);
		// } else if (getCondition().isFalse() && hasElse()) {
		// getElse().jimplify2(b);
		// }
		// } else {

		getCondition().emitEvalBranch(b); // calls else_branch_label()

		soot.jimple.Stmt thenBranch = then_branch_label();
		b.addLabel(thenBranch);
		getThen().jimplify2(b);

		soot.jimple.Stmt endBranch = endBranchLabel(beginCond);

		// first: finalize "then"-branch
		// canCompleteNormally means: whether the 'then' contains a return or a throw statement
		if (/* alwaysProduceEndBranchStmt || */ getThen().canCompleteNormally()) {
			b.setLine(this);
			b.add(b.newGotoStmt(endBranch, this)); // link to endBranch node
		}

		soot.jimple.Stmt ifElseBranch = else_branch_label();
		// if (hasElse()) {
		//// ifElseBranch.addTag(t); // IF_ELSE
		// } else {
		//// ifElseBranch.addTag(t); // IF_END
		//// ifElseBranch.addTag(t); // ref to beginCond
		// }
		/*
		 * must always be added to the body since else_branch_label is called internally by the
		 * getCondition().emitEvalBranch(b)
		 */
		b.addLabel(ifElseBranch);

		if (hasElse()) {
			// then: begin "else"-branch
			getElse().jimplify2(b);
		}

		// canCompleteNormally means: whether the 'then' contains a return or a throw statement
		if (alwaysProduceEndBranchStmt || getThen().canCompleteNormally() || hasElse()) {
			b.setLine(this);
			b.addLabel(endBranch);
		}
	}

	/**
	 * @apilevel internal
	 * @author chw
	 */
	private soot.jimple.Stmt beginCondLabel() {
		soot.jimple.Stmt label = newLabel();
		label.addTag(HigherLevelStructureTags.IF_COND);
		return label;
	}

	/**
	 * @apilevel internal
	 * @author chw
	 * @param beginCond
	 */
	private soot.jimple.Stmt endBranchLabel(final soot.jimple.Stmt beginCond) {
		// soot.jimple.Stmt label = newLabel();
		soot.jimple.Stmt label = new JEndNopStmt(beginCond); // added by chw
		label.addTag(HigherLevelStructureTags.IF_END);
		return label;
	}

	/**
	 * @ast method
	 *
	 */
	public IfStmt() {
		super();

	}

	/**
	 * Initializes the child array to the correct size. Initializes List and Opt nta children.
	 *
	 * @apilevel internal
	 * @ast method
	 * @ast method
	 *
	 */
	@Override
	public void init$Children() {
		children = new ASTNode[3];
		setChild(new Opt(), 2);
	}

	/**
	 * @ast method
	 *
	 */
	public IfStmt(final Expr p0, final Stmt p1, final Opt<Stmt> p2) {
		setChild(p0, 0);
		setChild(p1, 1);
		setChild(p2, 2);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@Override
	protected int numChildren() {
		return 3;
	}

	/**
	 * @apilevel internal
	 * @ast method
	 *
	 */
	@Override
	public boolean mayHaveRewrite() {
		return false;
	}

	/**
	 * Replaces the Condition child.
	 *
	 * @param node
	 *            The new node to replace the Condition child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setCondition(final Expr node) {
		setChild(node, 0);
	}

	/**
	 * Retrieves the Condition child.
	 *
	 * @return The current node used as the Condition child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public Expr getCondition() {
		return (Expr) getChild(0);
	}

	/**
	 * Retrieves the Condition child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the Condition child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public Expr getConditionNoTransform() {
		return (Expr) getChildNoTransform(0);
	}

	/**
	 * Replaces the Then child.
	 *
	 * @param node
	 *            The new node to replace the Then child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setThen(final Stmt node) {
		setChild(node, 1);
	}

	/**
	 * Retrieves the Then child.
	 *
	 * @return The current node used as the Then child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public Stmt getThen() {
		return (Stmt) getChild(1);
	}

	/**
	 * Retrieves the Then child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the Then child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public Stmt getThenNoTransform() {
		return (Stmt) getChildNoTransform(1);
	}

	/**
	 * Replaces the optional node for the Else child. This is the {@code Opt} node containing the child Else, not the
	 * actual child!
	 *
	 * @param opt
	 *            The new node to be used as the optional node for the Else child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public void setElseOpt(final Opt<Stmt> opt) {
		setChild(opt, 2);
	}

	/**
	 * Check whether the optional Else child exists.
	 *
	 * @return {@code true} if the optional Else child exists, {@code false} if it does not.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public boolean hasElse() {
		return getElseOpt().getNumChild() != 0;
	}

	/**
	 * Retrieves the (optional) Else child.
	 *
	 * @return The Else child, if it exists. Returns {@code null} otherwise.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Stmt getElse() {
		return getElseOpt().getChild(0);
	}

	/**
	 * Replaces the (optional) Else child.
	 *
	 * @param node
	 *            The new node to be used as the Else child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setElse(final Stmt node) {
		getElseOpt().setChild(node, 0);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Opt<Stmt> getElseOpt() {
		return (Opt<Stmt>) getChild(2);
	}

	/**
	 * Retrieves the optional node for child Else. This is the {@code Opt} node containing the child Else, not the
	 * actual child!
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The optional node for child Else.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Opt<Stmt> getElseOptNoTransform() {
		return (Opt<Stmt>) getChildNoTransform(2);
	}

	protected java.util.Map isDAafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DA
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:525
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean isDAafter(final Variable v) {
		Object _parameters = v;
		if (isDAafter_Variable_values == null) {
			isDAafter_Variable_values = new java.util.HashMap(4);
		}
		if (isDAafter_Variable_values.containsKey(_parameters)) {
			return ((Boolean) isDAafter_Variable_values.get(_parameters)).booleanValue();
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		boolean isDAafter_Variable_value = isDAafter_compute(v);
		if (isFinal && num == state().boundariesCrossed) {
			isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
		}
		return isDAafter_Variable_value;
	}

	/**
	 * @apilevel internal
	 */
	private boolean isDAafter_compute(final Variable v) {
		return hasElse() ? getThen().isDAafter(v) && getElse().isDAafter(v)
				: getThen().isDAafter(v) && getCondition().isDAafterFalse(v);
	}

	protected java.util.Map isDUafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:994
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean isDUafter(final Variable v) {
		Object _parameters = v;
		if (isDUafter_Variable_values == null) {
			isDUafter_Variable_values = new java.util.HashMap(4);
		}
		if (isDUafter_Variable_values.containsKey(_parameters)) {
			return ((Boolean) isDUafter_Variable_values.get(_parameters)).booleanValue();
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		boolean isDUafter_Variable_value = isDUafter_compute(v);
		if (isFinal && num == state().boundariesCrossed) {
			isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
		}
		return isDUafter_Variable_value;
	}

	/**
	 * @apilevel internal
	 */
	private boolean isDUafter_compute(final Variable v) {
		return hasElse() ? getThen().isDUafter(v) && getElse().isDUafter(v)
				: getThen().isDUafter(v) && getCondition().isDUafterFalse(v);
	}

	/**
	 * @apilevel internal
	 */
	protected boolean canCompleteNormally_computed = false;
	/**
	 * @apilevel internal
	 */
	protected boolean canCompleteNormally_value;

	/**
	 * @attribute syn
	 * @aspect UnreachableStatements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:141
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean canCompleteNormally() {
		if (canCompleteNormally_computed) {
			return canCompleteNormally_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		canCompleteNormally_value = canCompleteNormally_compute();
		if (isFinal && num == state().boundariesCrossed) {
			canCompleteNormally_computed = true;
		}
		return canCompleteNormally_value;
	}

	/**
	 * @apilevel internal
	 */
	private boolean canCompleteNormally_compute() {
		return (reachable() && !hasElse())
				|| (getThen().canCompleteNormally() || (hasElse() && getElse().canCompleteNormally()));
	}

	/**
	 * @attribute syn
	 * @aspect BooleanExpressions
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             21
	 */
	@Override
	public boolean definesLabel() {
		ASTNode$State state = state();
		try {
			return true;
		} finally {
		}
	}

	/**
	 * @apilevel internal
	 */
	protected boolean else_branch_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt else_branch_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:113
	 */
	@SuppressWarnings({ "cast" })
	public soot.jimple.Stmt else_branch_label() {
		if (else_branch_label_computed) {
			return else_branch_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		else_branch_label_value = else_branch_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			else_branch_label_computed = true;
		}
		return else_branch_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt else_branch_label_compute() {
		return newLabel(HigherLevelStructureTags.IF_ELSE); // changed by chw
	}

	/**
	 * @apilevel internal
	 */
	protected boolean then_branch_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt then_branch_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:114
	 */
	@SuppressWarnings({ "cast" })
	public soot.jimple.Stmt then_branch_label() {
		if (then_branch_label_computed) {
			return then_branch_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		then_branch_label_value = then_branch_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			then_branch_label_computed = true;
		}
		return then_branch_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt then_branch_label_compute() {
		return newLabel(HigherLevelStructureTags.IF_THEN); // changed by chw
	}

	/**
	 * @attribute syn
	 * @aspect PreciseRethrow
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java7Frontend/PreciseRethrow.jrag:55
	 */
	@Override
	public boolean modifiedInScope(final Variable var) {
		ASTNode$State state = state();
		try {
			if (getThen().modifiedInScope(var)) {
				return true;
			}
			return hasElse() && getElse().modifiedInScope(var);
		} finally {
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:528
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDAbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getElseOptNoTransform()) {
			return getCondition().isDAafterFalse(v);
		} else if (caller == getThenNoTransform()) {
			return getCondition().isDAafterTrue(v);
		} else if (caller == getConditionNoTransform()) {
			return isDAbefore(v);
		} else {
			return getParent().Define_boolean_isDAbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:997
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDUbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getElseOptNoTransform()) {
			return getCondition().isDUafterFalse(v);
		} else if (caller == getThenNoTransform()) {
			return getCondition().isDUafterTrue(v);
		} else if (caller == getConditionNoTransform()) {
			return isDUbefore(v);
		} else {
			return getParent().Define_boolean_isDUbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:144
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reachable(final ASTNode caller, final ASTNode child) {
		if (caller == getElseOptNoTransform()) {
			return reachable();
		} else if (caller == getThenNoTransform()) {
			return reachable();
		} else {
			return getParent().Define_boolean_reachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:150
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reportUnreachable(final ASTNode caller, final ASTNode child) {
		if (caller == getElseOptNoTransform()) {
			return reachable();
		} else if (caller == getThenNoTransform()) {
			return reachable();
		} else {
			return getParent().Define_boolean_reportUnreachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             38
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_false_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionNoTransform()) {
			return else_branch_label();
		} else {
			return getParent().Define_soot_jimple_Stmt_condition_false_label(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             39
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_true_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionNoTransform()) {
			return then_branch_label();
		} else {
			return getParent().Define_soot_jimple_Stmt_condition_true_label(this, caller);
		}
	}

	/**
	 * @apilevel internal
	 */
	@Override
	public ASTNode rewriteTo() {
		return super.rewriteTo();
	}
}
