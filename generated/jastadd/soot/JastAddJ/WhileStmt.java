/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

import java.util.Iterator;

import soot.javaToJimple.jj.extension.HigherLevelStructureTags;
import soot.javaToJimple.jj.extension.LoopIdTag;
import soot.jimple.internal.JEndNopStmt;
import soot.tagkit.Tag;

/**
 * @production WhileStmt : {@link BranchTargetStmt} ::= <span class="component">Condition:{@link Expr}</span>
 *             <span class="component">{@link Stmt}</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/java.ast:208
 */
public class WhileStmt extends BranchTargetStmt implements Cloneable {

	/**
	 * @author chw
	 */
	private static boolean alwaysProduceEndWhileStmt = Boolean.getBoolean("alwaysProduceEndWhileStmt");
	/**
	 * @author chw
	 */
	private final Tag loopIdTag = new LoopIdTag();

	/**
	 * @apilevel low-level
	 */
	@Override
	public void flushCache() {
		super.flushCache();
		targetOf_ContinueStmt_values = null;
		targetOf_BreakStmt_values = null;
		isDAafter_Variable_values = null;
		isDUafter_Variable_values = null;
		isDUbeforeCondition_Variable_values = null;
		canCompleteNormally_computed = false;
		cond_label_computed = false;
		cond_label_value = null;
		end_label_computed = false;
		end_label_value = null;
		stmt_label_computed = false;
		stmt_label_value = null;
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
	public WhileStmt clone() throws CloneNotSupportedException {
		WhileStmt node = (WhileStmt) super.clone();
		node.targetOf_ContinueStmt_values = null;
		node.targetOf_BreakStmt_values = null;
		node.isDAafter_Variable_values = null;
		node.isDUafter_Variable_values = null;
		node.isDUbeforeCondition_Variable_values = null;
		node.canCompleteNormally_computed = false;
		node.cond_label_computed = false;
		node.cond_label_value = null;
		node.end_label_computed = false;
		node.end_label_value = null;
		node.stmt_label_computed = false;
		node.stmt_label_value = null;
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public WhileStmt copy() {
		try {
			WhileStmt node = clone();
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
	public WhileStmt fullCopy() {
		WhileStmt tree = copy();
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
	 * @aspect PrettyPrint
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/PrettyPrint.jadd:587
	 */
	@Override
	public void toString(final StringBuffer s) {
		s.append(indent());
		s.append("while(");
		getCondition().toString(s);
		s.append(")");
		getStmt().toString(s);
	}

	/**
	 * @ast method
	 * @aspect TypeCheck
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/TypeCheck.jrag:322
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:146
	 */
	@Override
	public void jimplify2(final Body b) {
		soot.jimple.Stmt condLabel = cond_label();
		b.addLabel(condLabel);
		getCondition().emitEvalBranch(b);
		b.addLabel(stmt_label());
		if (getCondition().canBeTrue()) {
			getStmt().jimplify2(b);
			if (getStmt().canCompleteNormally()) {
				b.setLine(this);
				b.add(b.newGotoStmt(condLabel, this));
			}
		}

		if (alwaysProduceEndWhileStmt || canCompleteNormally()) {
			b.addLabel(end_label(condLabel));
		}
	}

	/**
	 * @ast method
	 *
	 */
	public WhileStmt() {
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
		children = new ASTNode[2];
	}

	/**
	 * @ast method
	 *
	 */
	public WhileStmt(final Expr p0, final Stmt p1) {
		setChild(p0, 0);
		setChild(p1, 1);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@Override
	protected int numChildren() {
		return 2;
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
	 * Replaces the Stmt child.
	 *
	 * @param node
	 *            The new node to replace the Stmt child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setStmt(final Stmt node) {
		setChild(node, 1);
	}

	/**
	 * Retrieves the Stmt child.
	 *
	 * @return The current node used as the Stmt child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public Stmt getStmt() {
		return (Stmt) getChild(1);
	}

	/**
	 * Retrieves the Stmt child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the Stmt child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public Stmt getStmtNoTransform() {
		return (Stmt) getChildNoTransform(1);
	}

	protected java.util.Map targetOf_ContinueStmt_values;

	/**
	 * @attribute syn
	 * @aspect BranchTarget
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/BranchTarget.jrag:69
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean targetOf(final ContinueStmt stmt) {
		Object _parameters = stmt;
		if (targetOf_ContinueStmt_values == null) {
			targetOf_ContinueStmt_values = new java.util.HashMap(4);
		}
		if (targetOf_ContinueStmt_values.containsKey(_parameters)) {
			return ((Boolean) targetOf_ContinueStmt_values.get(_parameters)).booleanValue();
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		boolean targetOf_ContinueStmt_value = targetOf_compute(stmt);
		if (isFinal && num == state().boundariesCrossed) {
			targetOf_ContinueStmt_values.put(_parameters, Boolean.valueOf(targetOf_ContinueStmt_value));
		}
		return targetOf_ContinueStmt_value;
	}

	/**
	 * @apilevel internal
	 */
	private boolean targetOf_compute(final ContinueStmt stmt) {
		return !stmt.hasLabel();
	}

	protected java.util.Map targetOf_BreakStmt_values;

	/**
	 * @attribute syn
	 * @aspect BranchTarget
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/BranchTarget.jrag:77
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean targetOf(final BreakStmt stmt) {
		Object _parameters = stmt;
		if (targetOf_BreakStmt_values == null) {
			targetOf_BreakStmt_values = new java.util.HashMap(4);
		}
		if (targetOf_BreakStmt_values.containsKey(_parameters)) {
			return ((Boolean) targetOf_BreakStmt_values.get(_parameters)).booleanValue();
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		boolean targetOf_BreakStmt_value = targetOf_compute(stmt);
		if (isFinal && num == state().boundariesCrossed) {
			targetOf_BreakStmt_values.put(_parameters, Boolean.valueOf(targetOf_BreakStmt_value));
		}
		return targetOf_BreakStmt_value;
	}

	/**
	 * @apilevel internal
	 */
	private boolean targetOf_compute(final BreakStmt stmt) {
		return !stmt.hasLabel();
	}

	protected java.util.Map isDAafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DA
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:576
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
		if (!getCondition().isDAafterFalse(v)) {
			return false;
		}
		for (Iterator iter = targetBreaks().iterator(); iter.hasNext();) {
			BreakStmt stmt = (BreakStmt) iter.next();
			if (!stmt.isDAafterReachedFinallyBlocks(v)) {
				return false;
			}
		}
		return true;
	}

	protected java.util.Map isDUafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1032
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
		if (!isDUbeforeCondition(v)) {
			return false;
		}
		if (!getCondition().isDUafterFalse(v)) {
			return false;
		}
		for (Iterator iter = targetBreaks().iterator(); iter.hasNext();) {
			BreakStmt stmt = (BreakStmt) iter.next();
			if (!stmt.isDUafterReachedFinallyBlocks(v)) {
				return false;
			}
		}
		return true;
	}

	protected java.util.Map isDUbeforeCondition_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1049
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public boolean isDUbeforeCondition(final Variable v) {
		Object _parameters = v;
		if (isDUbeforeCondition_Variable_values == null) {
			isDUbeforeCondition_Variable_values = new java.util.HashMap(4);
		}
		ASTNode$State.CircularValue _value;
		if (isDUbeforeCondition_Variable_values.containsKey(_parameters)) {
			Object _o = isDUbeforeCondition_Variable_values.get(_parameters);
			if (!(_o instanceof ASTNode$State.CircularValue)) {
				return ((Boolean) _o).booleanValue();
			} else {
				_value = (ASTNode$State.CircularValue) _o;
			}
		} else {
			_value = new ASTNode$State.CircularValue();
			isDUbeforeCondition_Variable_values.put(_parameters, _value);
			_value.value = Boolean.valueOf(true);
		}
		ASTNode$State state = state();
		if (!state.IN_CIRCLE) {
			state.IN_CIRCLE = true;
			int num = state.boundariesCrossed;
			boolean isFinal = this.is$Final();
			boolean new_isDUbeforeCondition_Variable_value;
			do {
				_value.visited = new Integer(state.CIRCLE_INDEX);
				state.CHANGE = false;
				new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
				if (new_isDUbeforeCondition_Variable_value != ((Boolean) _value.value).booleanValue()) {
					state.CHANGE = true;
					_value.value = Boolean.valueOf(new_isDUbeforeCondition_Variable_value);
				}
				state.CIRCLE_INDEX++;
			} while (state.CHANGE);
			if (isFinal && num == state().boundariesCrossed) {
				isDUbeforeCondition_Variable_values.put(_parameters, new_isDUbeforeCondition_Variable_value);
			} else {
				isDUbeforeCondition_Variable_values.remove(_parameters);
				state.RESET_CYCLE = true;
				isDUbeforeCondition_compute(v);
				state.RESET_CYCLE = false;
			}
			state.IN_CIRCLE = false;
			return new_isDUbeforeCondition_Variable_value;
		}
		if (!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
			_value.visited = new Integer(state.CIRCLE_INDEX);
			boolean new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
			if (state.RESET_CYCLE) {
				isDUbeforeCondition_Variable_values.remove(_parameters);
			} else if (new_isDUbeforeCondition_Variable_value != ((Boolean) _value.value).booleanValue()) {
				state.CHANGE = true;
				_value.value = new_isDUbeforeCondition_Variable_value;
			}
			return new_isDUbeforeCondition_Variable_value;
		}
		return ((Boolean) _value.value).booleanValue();
	}

	/**
	 * @apilevel internal
	 */
	private boolean isDUbeforeCondition_compute(final Variable v) {
		// 1st
		if (!isDUbefore(v)) {
			return false;
		} else if (!getStmt().isDUafter(v)) {
			return false;
		} else {
			for (Iterator iter = targetContinues().iterator(); iter.hasNext();) {
				ContinueStmt stmt = (ContinueStmt) iter.next();
				if (!stmt.isDUafterReachedFinallyBlocks(v)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @attribute syn
	 * @aspect NameCheck
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NameCheck.jrag:401
	 */
	@Override
	public boolean continueLabel() {
		ASTNode$State state = state();
		try {
			return true;
		} finally {
		}
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:85
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
		return reachable() && (!getCondition().isConstant() || !getCondition().isTrue()) || reachableBreak();
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
	protected boolean cond_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt cond_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:142
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public soot.jimple.Stmt cond_label() {
		if (cond_label_computed) {
			return cond_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		cond_label_value = cond_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			cond_label_computed = true;
		}
		return cond_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt cond_label_compute() {
		soot.jimple.Stmt label = newLabel(HigherLevelStructureTags.WHILE_COND);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean end_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt end_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:143
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public soot.jimple.Stmt end_label(soot.jimple.Stmt beginCond) {
		if (end_label_computed) {
			return end_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		end_label_value = end_label_compute(beginCond);
		if (isFinal && num == state().boundariesCrossed) {
			end_label_computed = true;
		}
		return end_label_value;
	}

	/**
	 * @param beginCond 
	 * @apilevel internal
	 */
	private soot.jimple.Stmt end_label_compute(soot.jimple.Stmt beginCond) {
		soot.jimple.Stmt label = new JEndNopStmt(beginCond); // added by chw
		label.addTag(HigherLevelStructureTags.WHILE_END);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean stmt_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt stmt_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:144
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public soot.jimple.Stmt stmt_label() {
		if (stmt_label_computed) {
			return stmt_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		stmt_label_value = stmt_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			stmt_label_computed = true;
		}
		return stmt_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt stmt_label_compute() {
		soot.jimple.Stmt label = newLabel(HigherLevelStructureTags.WHILE_BODY);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:200
	 */
	@Override
	public soot.jimple.Stmt break_label() {
		ASTNode$State state = state();
		try {
			return end_label(null);
		} finally {
		}
	}

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:225
	 */
	@Override
	public soot.jimple.Stmt continue_label() {
		ASTNode$State state = state();
		try {
			return cond_label();
		} finally {
		}
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
			return getStmt().modifiedInScope(var);
		} finally {
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:587
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDAbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getStmtNoTransform()) {
			return getCondition().isDAafterTrue(v);
		} else if (caller == getConditionNoTransform()) {
			return isDAbefore(v);
		} else {
			return getParent().Define_boolean_isDAbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1066
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDUbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getStmtNoTransform()) {
			return getCondition().isDUafterTrue(v);
		} else if (caller == getConditionNoTransform()) {
			return isDUbeforeCondition(v);
		} else {
			return getParent().Define_boolean_isDUbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NameCheck.jrag:371
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_insideLoop(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return true;
		} else {
			return getParent().Define_boolean_insideLoop(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:86
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reachable(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return reachable() && !getCondition().isFalse();
		} else {
			return getParent().Define_boolean_reachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:153
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reportUnreachable(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return reachable();
		} else {
			return getParent().Define_boolean_reportUnreachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             40
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_false_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionNoTransform()) {
			return end_label(null);
		} else {
			return getParent().Define_soot_jimple_Stmt_condition_false_label(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             41
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_true_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionNoTransform()) {
			return stmt_label();
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
