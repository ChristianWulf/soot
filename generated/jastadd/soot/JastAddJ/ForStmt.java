/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

import java.util.Iterator;

import soot.javaToJimple.jj.extension.HigherLevelStructureTags;
import soot.javaToJimple.jj.extension.LoopIdTag;
import soot.jimple.internal.JEndNopStmt;
import soot.tagkit.Tag;

/**
 * @production ForStmt : {@link BranchTargetStmt} ::= <span class="component">InitStmt:{@link Stmt}*</span>
 *             <span class="component">[Condition:{@link Expr}]</span> <span class="component">UpdateStmt:{@link Stmt}
 *             *</span> <span class="component">{@link Stmt}</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/java.ast:210
 */
public class ForStmt extends BranchTargetStmt implements Cloneable, VariableScope {

	/**
	 * @author chw
	 */
	private static boolean alwaysProduceEndForStmt = Boolean.getBoolean("alwaysProduceEndForStmt");
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
		localLookup_String_values = null;
		localVariableDeclaration_String_values = null;
		canCompleteNormally_computed = false;
		cond_label_computed = false;
		cond_label_value = null;
		begin_label_computed = false;
		begin_label_value = null;
		update_label_computed = false;
		update_label_value = null;
		end_label_computed = false;
		end_label_value = null;
		lookupVariable_String_values = null;
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
	public ForStmt clone() throws CloneNotSupportedException {
		ForStmt node = (ForStmt) super.clone();
		node.targetOf_ContinueStmt_values = null;
		node.targetOf_BreakStmt_values = null;
		node.isDAafter_Variable_values = null;
		node.isDUafter_Variable_values = null;
		node.isDUbeforeCondition_Variable_values = null;
		node.localLookup_String_values = null;
		node.localVariableDeclaration_String_values = null;
		node.canCompleteNormally_computed = false;
		node.cond_label_computed = false;
		node.cond_label_value = null;
		node.begin_label_computed = false;
		node.begin_label_value = null;
		node.update_label_computed = false;
		node.update_label_value = null;
		node.end_label_computed = false;
		node.end_label_value = null;
		node.lookupVariable_String_values = null;
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public ForStmt copy() {
		try {
			ForStmt node = clone();
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
	public ForStmt fullCopy() {
		ForStmt tree = copy();
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/PrettyPrint.jadd:604
	 */
	@Override
	public void toString(final StringBuffer s) {
		s.append(indent());
		s.append("for(");
		if (getNumInitStmt() > 0) {
			if (getInitStmt(0) instanceof VariableDeclaration) {
				int minDimension = Integer.MAX_VALUE;
				for (int i = 0; i < getNumInitStmt(); i++) {
					VariableDeclaration v = (VariableDeclaration) getInitStmt(i);
					minDimension = Math.min(minDimension, v.type().dimension());
				}
				VariableDeclaration v = (VariableDeclaration) getInitStmt(0);
				v.getModifiers().toString(s);
				s.append(v.type().elementType().typeName());
				for (int i = minDimension; i > 0; i--) {
					s.append("[]");
				}

				for (int i = 0; i < getNumInitStmt(); i++) {
					if (i != 0) {
						s.append(",");
					}
					v = (VariableDeclaration) getInitStmt(i);
					s.append(" " + v.name());
					for (int j = v.type().dimension() - minDimension; j > 0; j--) {
						s.append("[]");
					}
					if (v.hasInit()) {
						s.append(" = ");
						v.getInit().toString(s);
					}
				}
			} else if (getInitStmt(0) instanceof ExprStmt) {
				ExprStmt stmt = (ExprStmt) getInitStmt(0);
				stmt.getExpr().toString(s);
				for (int i = 1; i < getNumInitStmt(); i++) {
					s.append(", ");
					stmt = (ExprStmt) getInitStmt(i);
					stmt.getExpr().toString(s);
				}
			} else {
				throw new Error("Unexpected initializer in for loop: " + getInitStmt(0));
			}
		}

		s.append("; ");
		if (hasCondition()) {
			getCondition().toString(s);
		}
		s.append("; ");

		if (getNumUpdateStmt() > 0) {
			ExprStmt stmt = (ExprStmt) getUpdateStmt(0);
			stmt.getExpr().toString(s);
			for (int i = 1; i < getNumUpdateStmt(); i++) {
				s.append(", ");
				stmt = (ExprStmt) getUpdateStmt(i);
				stmt.getExpr().toString(s);
			}
		}

		s.append(") ");
		getStmt().toString(s);
	}

	/**
	 * @ast method
	 * @aspect TypeCheck
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/TypeCheck.jrag:334
	 */
	@Override
	public void typeCheck() {
		if (hasCondition()) {
			TypeDecl cond = getCondition().type();
			if (!cond.isBoolean()) {
				error("the type of \"" + getCondition() + "\" is " + cond.name() + " which is not boolean");
			}
		}
	}

	/**
	 * @ast method
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:180
	 */
	@Override
	public void jimplify2(final Body b) {
		b.addLabel(init_label());
		for (int i = 0; i < getNumInitStmt(); i++) {
			getInitStmt(i).jimplify2(b);
		}
		soot.jimple.Stmt condLabel = cond_label();
		b.addLabel(condLabel);
		getCondition().emitEvalBranch(b);
		if (getCondition().canBeTrue()) {
			b.addLabel(begin_label());
			getStmt().jimplify2(b);
			b.addLabel(update_label());
			for (int i = 0; i < getNumUpdateStmt(); i++) {
				getUpdateStmt(i).jimplify2(b);
			}
			b.setLine(this);
			b.add(b.newGotoStmt(condLabel, this));
		}

		if (alwaysProduceEndForStmt || canCompleteNormally()) {
			b.addLabel(endLoopLabel(condLabel));
		}
	}

	/**
	 * @ast method
	 *
	 */
	public ForStmt() {
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
		children = new ASTNode[4];
		setChild(new List(), 0);
		setChild(new Opt(), 1);
		setChild(new List(), 2);
	}

	/**
	 * @ast method
	 *
	 */
	public ForStmt(final List<Stmt> p0, final Opt<Expr> p1, final List<Stmt> p2, final Stmt p3) {
		setChild(p0, 0);
		setChild(p1, 1);
		setChild(p2, 2);
		setChild(p3, 3);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@Override
	protected int numChildren() {
		return 4;
	}

	/**
	 * @apilevel internal
	 * @ast method
	 *
	 */
	@Override
	public boolean mayHaveRewrite() {
		return true;
	}

	/**
	 * Replaces the InitStmt list.
	 *
	 * @param list
	 *            The new list node to be used as the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setInitStmtList(final List<Stmt> list) {
		setChild(list, 0);
	}

	/**
	 * Retrieves the number of children in the InitStmt list.
	 *
	 * @return Number of children in the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public int getNumInitStmt() {
		return getInitStmtList().getNumChild();
	}

	/**
	 * Retrieves the number of children in the InitStmt list. Calling this method will not trigger rewrites..
	 *
	 * @return Number of children in the InitStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public int getNumInitStmtNoTransform() {
		return getInitStmtListNoTransform().getNumChildNoTransform();
	}

	/**
	 * Retrieves the element at index {@code i} in the InitStmt list..
	 *
	 * @param i
	 *            Index of the element to return.
	 * @return The element at position {@code i} in the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Stmt getInitStmt(final int i) {
		return getInitStmtList().getChild(i);
	}

	/**
	 * Append an element to the InitStmt list.
	 *
	 * @param node
	 *            The element to append to the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void addInitStmt(final Stmt node) {
		List<Stmt> list = (parent == null || state == null) ? getInitStmtListNoTransform() : getInitStmtList();
		list.addChild(node);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public void addInitStmtNoTransform(final Stmt node) {
		List<Stmt> list = getInitStmtListNoTransform();
		list.addChild(node);
	}

	/**
	 * Replaces the InitStmt list element at index {@code i} with the new node {@code node}.
	 *
	 * @param node
	 *            The new node to replace the old list element.
	 * @param i
	 *            The list index of the node to be replaced.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setInitStmt(final Stmt node, final int i) {
		List<Stmt> list = getInitStmtList();
		list.setChild(node, i);
	}

	/**
	 * Retrieves the InitStmt list.
	 *
	 * @return The node representing the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public List<Stmt> getInitStmts() {
		return getInitStmtList();
	}

	/**
	 * Retrieves the InitStmt list.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The node representing the InitStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public List<Stmt> getInitStmtsNoTransform() {
		return getInitStmtListNoTransform();
	}

	/**
	 * Retrieves the InitStmt list.
	 *
	 * @return The node representing the InitStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public List<Stmt> getInitStmtList() {
		List<Stmt> list = (List<Stmt>) getChild(0);
		list.getNumChild();
		return list;
	}

	/**
	 * Retrieves the InitStmt list.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The node representing the InitStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public List<Stmt> getInitStmtListNoTransform() {
		return (List<Stmt>) getChildNoTransform(0);
	}

	/**
	 * Replaces the optional node for the Condition child. This is the {@code Opt} node containing the child Condition,
	 * not the actual child!
	 *
	 * @param opt
	 *            The new node to be used as the optional node for the Condition child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public void setConditionOpt(final Opt<Expr> opt) {
		setChild(opt, 1);
	}

	/**
	 * Check whether the optional Condition child exists.
	 *
	 * @return {@code true} if the optional Condition child exists, {@code false} if it does not.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public boolean hasCondition() {
		return getConditionOpt().getNumChild() != 0;
	}

	/**
	 * Retrieves the (optional) Condition child.
	 *
	 * @return The Condition child, if it exists. Returns {@code null} otherwise.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Expr getCondition() {
		return getConditionOpt().getChild(0);
	}

	/**
	 * Replaces the (optional) Condition child.
	 *
	 * @param node
	 *            The new node to be used as the Condition child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setCondition(final Expr node) {
		getConditionOpt().setChild(node, 0);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Opt<Expr> getConditionOpt() {
		return (Opt<Expr>) getChild(1);
	}

	/**
	 * Retrieves the optional node for child Condition. This is the {@code Opt} node containing the child Condition, not
	 * the actual child!
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The optional node for child Condition.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Opt<Expr> getConditionOptNoTransform() {
		return (Opt<Expr>) getChildNoTransform(1);
	}

	/**
	 * Replaces the UpdateStmt list.
	 *
	 * @param list
	 *            The new list node to be used as the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setUpdateStmtList(final List<Stmt> list) {
		setChild(list, 2);
	}

	/**
	 * Retrieves the number of children in the UpdateStmt list.
	 *
	 * @return Number of children in the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public int getNumUpdateStmt() {
		return getUpdateStmtList().getNumChild();
	}

	/**
	 * Retrieves the number of children in the UpdateStmt list. Calling this method will not trigger rewrites..
	 *
	 * @return Number of children in the UpdateStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public int getNumUpdateStmtNoTransform() {
		return getUpdateStmtListNoTransform().getNumChildNoTransform();
	}

	/**
	 * Retrieves the element at index {@code i} in the UpdateStmt list..
	 *
	 * @param i
	 *            Index of the element to return.
	 * @return The element at position {@code i} in the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public Stmt getUpdateStmt(final int i) {
		return getUpdateStmtList().getChild(i);
	}

	/**
	 * Append an element to the UpdateStmt list.
	 *
	 * @param node
	 *            The element to append to the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void addUpdateStmt(final Stmt node) {
		List<Stmt> list = (parent == null || state == null) ? getUpdateStmtListNoTransform() : getUpdateStmtList();
		list.addChild(node);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public void addUpdateStmtNoTransform(final Stmt node) {
		List<Stmt> list = getUpdateStmtListNoTransform();
		list.addChild(node);
	}

	/**
	 * Replaces the UpdateStmt list element at index {@code i} with the new node {@code node}.
	 *
	 * @param node
	 *            The new node to replace the old list element.
	 * @param i
	 *            The list index of the node to be replaced.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setUpdateStmt(final Stmt node, final int i) {
		List<Stmt> list = getUpdateStmtList();
		list.setChild(node, i);
	}

	/**
	 * Retrieves the UpdateStmt list.
	 *
	 * @return The node representing the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public List<Stmt> getUpdateStmts() {
		return getUpdateStmtList();
	}

	/**
	 * Retrieves the UpdateStmt list.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The node representing the UpdateStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public List<Stmt> getUpdateStmtsNoTransform() {
		return getUpdateStmtListNoTransform();
	}

	/**
	 * Retrieves the UpdateStmt list.
	 *
	 * @return The node representing the UpdateStmt list.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public List<Stmt> getUpdateStmtList() {
		List<Stmt> list = (List<Stmt>) getChild(2);
		list.getNumChild();
		return list;
	}

	/**
	 * Retrieves the UpdateStmt list.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The node representing the UpdateStmt list.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public List<Stmt> getUpdateStmtListNoTransform() {
		return (List<Stmt>) getChildNoTransform(2);
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
		setChild(node, 3);
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
		return (Stmt) getChild(3);
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
		return (Stmt) getChildNoTransform(3);
	}

	protected java.util.Map targetOf_ContinueStmt_values;

	/**
	 * @attribute syn
	 * @aspect BranchTarget
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/BranchTarget.jrag:71
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/BranchTarget.jrag:79
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:612
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
		if (!(!hasCondition() || getCondition().isDAafterFalse(v))) {
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

	/**
	 * @attribute syn
	 * @aspect DA
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:625
	 */
	public boolean isDAafterInitialization(final Variable v) {
		ASTNode$State state = state();
		try {
			return getNumInitStmt() == 0 ? isDAbefore(v) : getInitStmt(getNumInitStmt() - 1).isDAafter(v);
		} finally {
		}
	}

	protected java.util.Map isDUafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1096
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
		if (!(!hasCondition() || getCondition().isDUafterFalse(v))) {
			return false;
		}
		for (Iterator iter = targetBreaks().iterator(); iter.hasNext();) {
			BreakStmt stmt = (BreakStmt) iter.next();
			if (!stmt.isDUafterReachedFinallyBlocks(v)) {
				return false;
			}
		}
		// if(!isDUafterUpdate(v))
		// return false;
		return true;
	}

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1116
	 */
	public boolean isDUafterInit(final Variable v) {
		ASTNode$State state = state();
		try {
			return getNumInitStmt() == 0 ? isDUbefore(v) : getInitStmt(getNumInitStmt() - 1).isDUafter(v);
		} finally {
		}
	}

	protected java.util.Map isDUbeforeCondition_Variable_values;

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1118
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
		if (!isDUafterInit(v)) {
			return false;
		} else if (!isDUafterUpdate(v)) {
			return false;
		}
		return true;
	}

	/**
	 * @attribute syn
	 * @aspect DU
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1129
	 */
	public boolean isDUafterUpdate(final Variable v) {
		ASTNode$State state = state();
		try {
			if (!isDUbeforeCondition(v)) {
				return false;
			}
			if (getNumUpdateStmt() > 0) {
				return getUpdateStmt(getNumUpdateStmt() - 1).isDUafter(v);
			}
			if (!getStmt().isDUafter(v)) {
				return false;
			}
			for (Iterator iter = targetContinues().iterator(); iter.hasNext();) {
				ContinueStmt stmt = (ContinueStmt) iter.next();
				if (!stmt.isDUafterReachedFinallyBlocks(v)) {
					return false;
				}
			}
			return true;
		} finally {
		}
	}

	protected java.util.Map localLookup_String_values;

	/**
	 * @attribute syn
	 * @aspect VariableScope
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/LookupVariable.jrag:91
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public SimpleSet localLookup(final String name) {
		Object _parameters = name;
		if (localLookup_String_values == null) {
			localLookup_String_values = new java.util.HashMap(4);
		}
		if (localLookup_String_values.containsKey(_parameters)) {
			return (SimpleSet) localLookup_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		SimpleSet localLookup_String_value = localLookup_compute(name);
		if (isFinal && num == state().boundariesCrossed) {
			localLookup_String_values.put(_parameters, localLookup_String_value);
		}
		return localLookup_String_value;
	}

	/**
	 * @apilevel internal
	 */
	private SimpleSet localLookup_compute(final String name) {
		VariableDeclaration v = localVariableDeclaration(name);
		if (v != null) {
			return v;
		}
		return lookupVariable(name);
	}

	protected java.util.Map localVariableDeclaration_String_values;

	/**
	 * @attribute syn
	 * @aspect VariableScope
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/LookupVariable.jrag:123
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public VariableDeclaration localVariableDeclaration(final String name) {
		Object _parameters = name;
		if (localVariableDeclaration_String_values == null) {
			localVariableDeclaration_String_values = new java.util.HashMap(4);
		}
		if (localVariableDeclaration_String_values.containsKey(_parameters)) {
			return (VariableDeclaration) localVariableDeclaration_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		VariableDeclaration localVariableDeclaration_String_value = localVariableDeclaration_compute(name);
		if (isFinal && num == state().boundariesCrossed) {
			localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
		}
		return localVariableDeclaration_String_value;
	}

	/**
	 * @apilevel internal
	 */
	private VariableDeclaration localVariableDeclaration_compute(final String name) {
		for (int i = 0; i < getNumInitStmt(); i++) {
			if (getInitStmt(i).declaresVariable(name)) {
				return (VariableDeclaration) getInitStmt(i);
			}
		}
		return null;
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:102
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
		return reachable() && hasCondition() && (!getCondition().isConstant() || !getCondition().isTrue())
				|| reachableBreak();
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:175
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
		soot.jimple.Stmt label = newLabel();
		label.addTag(HigherLevelStructureTags.FOR_COND);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean init_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt init_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @author chw
	 */
	@SuppressWarnings({ "cast" })
	public soot.jimple.Stmt init_label() {
		if (init_label_computed) {
			return init_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		init_label_value = init_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			init_label_computed = true;
		}
		return init_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt init_label_compute() {
		soot.jimple.Stmt label = newLabel();
		label.addTag(HigherLevelStructureTags.FOR_INIT);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean begin_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt begin_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:176
	 */
	@SuppressWarnings({ "cast" })
	public soot.jimple.Stmt begin_label() {
		if (begin_label_computed) {
			return begin_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		begin_label_value = begin_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			begin_label_computed = true;
		}
		return begin_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt begin_label_compute() {
		soot.jimple.Stmt label = newLabel();
		label.addTag(HigherLevelStructureTags.FOR_BODY);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean update_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt update_label_value;

	/**
	 * @attribute syn
	 * @aspect Statements
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:177
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public soot.jimple.Stmt update_label() {
		if (update_label_computed) {
			return update_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		update_label_value = update_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			update_label_computed = true;
		}
		return update_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt update_label_compute() {
		soot.jimple.Stmt label = newLabel();
		label.addTag(HigherLevelStructureTags.FOR_UPDATE);
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/Statements.jrag:178
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public soot.jimple.Stmt end_label() {
		if (end_label_computed) {
			return end_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		end_label_value = end_label_compute();
		if (isFinal && num == state().boundariesCrossed) {
			end_label_computed = true;
		}
		return end_label_value;
	}

	/**
	 * @apilevel internal
	 */
	private soot.jimple.Stmt end_label_compute() {
		soot.jimple.Stmt label = newLabel();
		return label;
	}

	/**
	 * @apilevel internal
	 * @author Christian Wulf (chw)
	 * @param beginCond
	 */
	private soot.jimple.Stmt endLoopLabel(final soot.jimple.Stmt beginCond) {
//		soot.jimple.Stmt label = newLabel();
		soot.jimple.Stmt label = new JEndNopStmt(beginCond);	// added by chw
		label.addTag(HigherLevelStructureTags.FOR_END);
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
			return end_label();
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
			return update_label();
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
			for (Stmt stmt : getInitStmtList()) {
				if (stmt.modifiedInScope(var)) {
					return true;
				}
			}
			for (Stmt stmt : getUpdateStmtList()) {
				if (stmt.modifiedInScope(var)) {
					return true;
				}
			}
			return getStmt().modifiedInScope(var);
		} finally {
		}
	}

	protected java.util.Map lookupVariable_String_values;

	/**
	 * @attribute inh
	 * @aspect VariableScope
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/LookupVariable.jrag:18
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public SimpleSet lookupVariable(final String name) {
		Object _parameters = name;
		if (lookupVariable_String_values == null) {
			lookupVariable_String_values = new java.util.HashMap(4);
		}
		if (lookupVariable_String_values.containsKey(_parameters)) {
			return (SimpleSet) lookupVariable_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
		if (isFinal && num == state().boundariesCrossed) {
			lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
		}
		return lookupVariable_String_value;
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:636
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDAbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getUpdateStmtListNoTransform()) {
			int childIndex = caller.getIndexOfChild(child);
			{
				if (!getStmt().isDAafter(v)) {
					return false;
				}
				for (Iterator iter = targetContinues().iterator(); iter.hasNext();) {
					ContinueStmt stmt = (ContinueStmt) iter.next();
					if (!stmt.isDAafterReachedFinallyBlocks(v)) {
						return false;
					}
				}
				return true;
			}
		} else if (caller == getStmtNoTransform()) {
			if (hasCondition() && getCondition().isDAafterTrue(v)) {
				return true;
			}
			if (!hasCondition() && isDAafterInitialization(v)) {
				return true;
			}
			return false;
		} else if (caller == getConditionOptNoTransform()) {
			return isDAafterInitialization(v);
		} else if (caller == getInitStmtListNoTransform()) {
			int i = caller.getIndexOfChild(child);
			return i == 0 ? isDAbefore(v) : getInitStmt(i - 1).isDAafter(v);
		} else {
			return getParent().Define_boolean_isDAbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1145
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDUbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getUpdateStmtListNoTransform()) {
			int i = caller.getIndexOfChild(child);
			{
				if (!isDUbeforeCondition(v)) {
					return false;
				}
				if (i == 0) {
					if (!getStmt().isDUafter(v)) {
						return false;
					}
					for (Iterator iter = targetContinues().iterator(); iter.hasNext();) {
						ContinueStmt stmt = (ContinueStmt) iter.next();
						if (!stmt.isDUafterReachedFinallyBlocks(v)) {
							return false;
						}
					}
					return true;
				} else {
					return getUpdateStmt(i - 1).isDUafter(v);
				}
			}
		} else if (caller == getStmtNoTransform()) {
			return isDUbeforeCondition(v) && (hasCondition() ? getCondition().isDUafterTrue(v) : isDUafterInit(v));
		} else if (caller == getConditionOptNoTransform()) {
			return isDUbeforeCondition(v);
		} else if (caller == getInitStmtListNoTransform()) {
			int childIndex = caller.getIndexOfChild(child);
			return childIndex == 0 ? isDUbefore(v) : getInitStmt(childIndex - 1).isDUafter(v);
		} else {
			return getParent().Define_boolean_isDUbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/LookupVariable.jrag:90
	 * @apilevel internal
	 */
	@Override
	public SimpleSet Define_SimpleSet_lookupVariable(final ASTNode caller, final ASTNode child, final String name) {
		if (caller == getStmtNoTransform()) {
			return localLookup(name);
		} else if (caller == getUpdateStmtListNoTransform()) {
			int childIndex = caller.getIndexOfChild(child);
			return localLookup(name);
		} else if (caller == getConditionOptNoTransform()) {
			return localLookup(name);
		} else if (caller == getInitStmtListNoTransform()) {
			int childIndex = caller.getIndexOfChild(child);
			return localLookup(name);
		} else {
			return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NameCheck.jrag:299
	 * @apilevel internal
	 */
	@Override
	public VariableScope Define_VariableScope_outerScope(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return this;
		} else if (caller == getInitStmtListNoTransform()) {
			int childIndex = caller.getIndexOfChild(child);
			return this;
		} else {
			return getParent().Define_VariableScope_outerScope(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/NameCheck.jrag:370
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:103
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reachable(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return reachable() && (!hasCondition() || (!getCondition().isConstant() || !getCondition().isFalse()));
		} else {
			return getParent().Define_boolean_reachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/UnreachableStatements.jrag:151
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
	 *             44
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_false_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionOptNoTransform()) {
			return end_label();
		} else {
			return getParent().Define_soot_jimple_Stmt_condition_false_label(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/JimpleBackend/BooleanExpressions.jrag:
	 *             45
	 * @apilevel internal
	 */
	@Override
	public soot.jimple.Stmt Define_soot_jimple_Stmt_condition_true_label(final ASTNode caller, final ASTNode child) {
		if (caller == getConditionOptNoTransform()) {
			return begin_label();
		} else {
			return getParent().Define_soot_jimple_Stmt_condition_true_label(this, caller);
		}
	}

	/**
	 * @apilevel internal
	 */
	@Override
	public ASTNode rewriteTo() {
		// Declared in /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag at
		// line 1163
		if (!hasCondition()) {
			state().duringDU++;
			ASTNode result = rewriteRule0();
			state().duringDU--;
			return result;
		}

		return super.rewriteTo();
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/DefiniteAssignment.jrag:1163
	 * @apilevel internal
	 */
	private ForStmt rewriteRule0() {
		{
			setCondition(new BooleanLiteral("true"));
			return this;
		}
	}
}
