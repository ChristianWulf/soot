/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

import java.util.ArrayList;
import java.util.Iterator;

import soot.javaToJimple.jj.extension.HigherLevelStructureTags;
import soot.javaToJimple.jj.extension.LoopIdTag;
import soot.javaToJimple.jj.extension.VariableDeclarationTag;
import soot.jimple.internal.JEndNopStmt;
import soot.tagkit.Tag;

/**
 * @production EnhancedForStmt : {@link BranchTargetStmt} ::= <span class="component">{@link VariableDeclaration}</span>
 *             <span class="component">{@link Expr}</span> <span class="component">{@link Stmt}</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.ast:1
 */
public class EnhancedForStmt extends BranchTargetStmt implements Cloneable, VariableScope {

	/**
	 * @apilevel internal
	 */
	protected boolean init_label_computed;

	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt init_label_value;

	/**
	 * @apilevel internal
	 */
	protected boolean begin_label_computed;

	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt begin_label_value;

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
		canCompleteNormally_computed = false;
		isDAafter_Variable_values = null;
		isDUafter_Variable_values = null;
		cond_label_computed = false;
		cond_label_value = null;
		update_label_computed = false;
		update_label_value = null;
		end_label_computed = false;
		end_label_value = null;
		extraLocalIndex_computed = false;
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
	public EnhancedForStmt clone() throws CloneNotSupportedException {
		EnhancedForStmt node = (EnhancedForStmt) super.clone();
		node.targetOf_ContinueStmt_values = null;
		node.targetOf_BreakStmt_values = null;
		node.canCompleteNormally_computed = false;
		node.isDAafter_Variable_values = null;
		node.isDUafter_Variable_values = null;
		node.cond_label_computed = false;
		node.cond_label_value = null;
		node.update_label_computed = false;
		node.update_label_value = null;
		node.end_label_computed = false;
		node.end_label_value = null;
		node.extraLocalIndex_computed = false;
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public EnhancedForStmt copy() {
		try {
			EnhancedForStmt node = clone();
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
	public EnhancedForStmt fullCopy() {
		EnhancedForStmt tree = copy();
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
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:15
	 */
	@Override
	public void typeCheck() {
		if (!getExpr().type().isArrayDecl() && !getExpr().type().isIterable()) {
			error("type " + getExpr().type().name()
					+ " of expression in foreach is neither array type nor java.lang.Iterable");
		} else if (getExpr().type().isArrayDecl()
				&& !getExpr().type().componentType().assignConversionTo(getVariableDeclaration().type(), null)) {
			error("parameter of type " + getVariableDeclaration().type().typeName()
					+ " can not be assigned an element of type " + getExpr().type().componentType().typeName());
		} else if (getExpr().type().isIterable() && !getExpr().type().isUnknown()) {
			MethodDecl iterator = (MethodDecl) getExpr().type().memberMethods("iterator").iterator().next();
			MethodDecl next = (MethodDecl) iterator.type().memberMethods("next").iterator().next();
			TypeDecl componentType = next.type();
			if (!componentType.assignConversionTo(getVariableDeclaration().type(), null)) {
				error("parameter of type " + getVariableDeclaration().type().typeName()
						+ " can not be assigned an element of type " + componentType.typeName());
			}
		}
	}

	/**
	 * @ast method
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:58
	 */
	@Override
	public void toString(final StringBuffer s) {
		s.append(indent());
		s.append("for (");
		getVariableDeclaration().getModifiers().toString(s);
		getVariableDeclaration().getTypeAccess().toString(s);
		s.append(" " + getVariableDeclaration().name());
		s.append(" : ");
		getExpr().toString(s);
		s.append(") ");
		getStmt().toString(s);
	}

	/**
	 * @ast method
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:24
	 */
	@Override
	public void jimplify2(final Body b) {
		soot.jimple.Stmt init_label = init_label();
		b.addLabel(init_label);
		if (getExpr().type().isArrayDecl()) {
			jimplify2ArrayLoop(b, init_label);
		} else {
			jimplify2Iterable(b, init_label);
		}
	}

	private void jimplify2ArrayLoop(final Body b, final soot.jimple.Stmt init_label) {
		soot.Local array = asLocal(b, getExpr().eval(b));
		soot.Local index = asLocal(b, soot.jimple.IntConstant.v(0));
		soot.Local parameter = b.newLocal(getVariableDeclaration().name(),
				getVariableDeclaration().type().getSootType());
		getVariableDeclaration().local = parameter;
		b.setLine(this);
		soot.jimple.Stmt condLabel = cond_label();
		b.addLabel(condLabel);
		b.add(b.newIfStmt(
				b.newGeExpr(asImmediate(b, index), asImmediate(b, b.newLengthExpr(asImmediate(b, array), this)), this),
				end_label(init_label), this));
		soot.jimple.Stmt loopVariable = b.newAssignStmt(parameter, asRValue(b, getExpr().type().elementType().emitCastTo(b,
				asLocal(b, b.newArrayRef(array, index, this)), getVariableDeclaration().type(), this)), this);
		// added by chw
		loopVariable.addTag(VariableDeclarationTag.INSTANCE);
		b.add(loopVariable);
		b.addLabel(begin_label());
		getStmt().jimplify2(b);
		b.addLabel(update_label());
		b.add(b.newAssignStmt(index, b.newAddExpr(index, soot.jimple.IntConstant.v(1), this), this));
		b.add(b.newGotoStmt(condLabel, this));
		b.addLabel(end_label(init_label));
	}

	/**
	 * @author chw
	 */
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
		label.addTag(HigherLevelStructureTags.FOREACH_INIT);
		label.addTag(loopIdTag);
		return label;
	}

	private void jimplify2Iterable(final Body b, final soot.jimple.Stmt init_label) {
		soot.Local iterator = asLocal(b, b.newInterfaceInvokeExpr(asLocal(b, getExpr().eval(b)),
				iteratorMethod().sootRef(), new ArrayList(), this));
		soot.Local parameter = b.newLocal(getVariableDeclaration().name(),
				getVariableDeclaration().type().getSootType());
		getVariableDeclaration().local = parameter;
		soot.jimple.Stmt condLabel = cond_label();
		b.addLabel(condLabel);
		b.add(b.newIfStmt(b.newEqExpr(
				asImmediate(b, b.newInterfaceInvokeExpr(iterator, hasNextMethod().sootRef(), new ArrayList(), this)),
				BooleanType.emitConstant(false), this),
				end_label(init_label),
				this));
		b.add(b.newAssignStmt(parameter,
				nextMethod().type().emitCastTo(b,
						b.newInterfaceInvokeExpr(iterator, nextMethod().sootRef(), new ArrayList(), this),
						getVariableDeclaration().type(), this),
				this));
		b.addLabel(begin_label());
		getStmt().jimplify2(b);
		b.addLabel(update_label());
		b.add(b.newGotoStmt(condLabel, this));
		b.addLabel(end_label(init_label));

		/*
		 * getExpr().createBCode(gen); iteratorMethod().emitInvokeMethod(gen, lookupType("java.lang", "Iterable"));
		 * gen.emitStoreReference(extraLocalIndex()); gen.addLabel(cond_label());
		 * gen.emitLoadReference(extraLocalIndex()); hasNextMethod().emitInvokeMethod(gen, lookupType("java.util",
		 * "Iterator")); gen.emitCompare(Bytecode.IFEQ, end_label()); gen.emitLoadReference(extraLocalIndex());
		 * nextMethod().emitInvokeMethod(gen, lookupType("java.util", "Iterator"));
		 * gen.emitCheckCast(getVariableDeclaration().type());
		 * gen.emitStoreReference(getVariableDeclaration().localNum()); getStmt().createBCode(gen);
		 * gen.addLabel(update_label()); gen.emitGoto(cond_label()); gen.addLabel(end_label());
		 */
	}

	/**
	 * @ast method
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:151
	 */
	private MethodDecl iteratorMethod() {
		TypeDecl typeDecl = lookupType("java.lang", "Iterable");
		for (Iterator iter = typeDecl.memberMethods("iterator").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl) iter.next();
			if (m.getNumParameter() == 0) {
				return m;
			}
		}
		throw new Error("Could not find java.lang.Iterable.iterator()");
	}

	/**
	 * @ast method
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:161
	 */
	private MethodDecl hasNextMethod() {
		TypeDecl typeDecl = lookupType("java.util", "Iterator");
		for (Iterator iter = typeDecl.memberMethods("hasNext").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl) iter.next();
			if (m.getNumParameter() == 0) {
				return m;
			}
		}
		throw new Error("Could not find java.util.Collection.hasNext()");
	}

	/**
	 * @ast method
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:171
	 */
	private MethodDecl nextMethod() {
		TypeDecl typeDecl = lookupType("java.util", "Iterator");
		for (Iterator iter = typeDecl.memberMethods("next").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl) iter.next();
			if (m.getNumParameter() == 0) {
				return m;
			}
		}
		throw new Error("Could not find java.util.Collection.next()");
	}

	/**
	 * @ast method
	 *
	 */
	public EnhancedForStmt() {
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
	}

	/**
	 * @ast method
	 *
	 */
	public EnhancedForStmt(final VariableDeclaration p0, final Expr p1, final Stmt p2) {
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
	 * Replaces the VariableDeclaration child.
	 *
	 * @param node
	 *            The new node to replace the VariableDeclaration child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setVariableDeclaration(final VariableDeclaration node) {
		setChild(node, 0);
	}

	/**
	 * Retrieves the VariableDeclaration child.
	 *
	 * @return The current node used as the VariableDeclaration child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public VariableDeclaration getVariableDeclaration() {
		return (VariableDeclaration) getChild(0);
	}

	/**
	 * Retrieves the VariableDeclaration child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the VariableDeclaration child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public VariableDeclaration getVariableDeclarationNoTransform() {
		return (VariableDeclaration) getChildNoTransform(0);
	}

	/**
	 * Replaces the Expr child.
	 *
	 * @param node
	 *            The new node to replace the Expr child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setExpr(final Expr node) {
		setChild(node, 1);
	}

	/**
	 * Retrieves the Expr child.
	 *
	 * @return The current node used as the Expr child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public Expr getExpr() {
		return (Expr) getChild(1);
	}

	/**
	 * Retrieves the Expr child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the Expr child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	public Expr getExprNoTransform() {
		return (Expr) getChildNoTransform(1);
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
		setChild(node, 2);
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
		return (Stmt) getChild(2);
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
		return (Stmt) getChildNoTransform(2);
	}

	/**
	 * @attribute syn
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:50
	 */
	public SimpleSet localLookupVariable(final String name) {
		ASTNode$State state = state();
		try {
			if (getVariableDeclaration().name().equals(name)) {
				return SimpleSet.emptySet.add(getVariableDeclaration());
			}
			return lookupVariable(name);
		} finally {
		}
	}

	protected java.util.Map targetOf_ContinueStmt_values;

	/**
	 * @attribute syn
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:75
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
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:76
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
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:79
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
		return reachable();
	}

	protected java.util.Map isDAafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:83
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
		if (!getExpr().isDAafter(v)) {
			return false;
		}
		/*
		 * for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) { BreakStmt stmt = (BreakStmt)iter.next();
		 * if(!stmt.isDAafterReachedFinallyBlocks(v)) return false; }
		 */
		return true;
	}

	protected java.util.Map isDUafter_Variable_values;

	/**
	 * @attribute syn
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:99
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
		if (!getExpr().isDUafter(v)) {
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
	protected boolean cond_label_computed = false;
	/**
	 * @apilevel internal
	 */
	protected soot.jimple.Stmt cond_label_value;

	/**
	 * @attribute syn
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:12
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
		label.addTag(HigherLevelStructureTags.FOREACH_COND);
		label.addTag(loopIdTag);
		return label;
	}

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
		label.addTag(HigherLevelStructureTags.FOREACH_BODY);
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
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:13
	 */
	@SuppressWarnings({ "cast" })
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
		return newLabel();
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
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:14
	 */
	@SuppressWarnings({  "cast" })
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
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @param init_label
	 * @attribute syn
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:14
	 */
	@SuppressWarnings({  "cast" })
	public soot.jimple.Stmt end_label(final soot.jimple.Stmt init_label) {
		if (end_label_computed) {
			return end_label_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		end_label_value = end_label_compute(init_label);
		if (isFinal && num == state().boundariesCrossed) {
			end_label_computed = true;
		}
		return end_label_value;
	}

	/**
	 * @param init_label
	 * @apilevel internal
	 */
	private soot.jimple.Stmt end_label_compute(final soot.jimple.Stmt init_label) {
//		soot.jimple.Stmt label = newLabel();
		soot.jimple.Stmt label = new JEndNopStmt(init_label); // added by chw
		label.addTag(HigherLevelStructureTags.FOREACH_END);
		label.addTag(loopIdTag);
		return label;
	}

	/**
	 * @apilevel internal
	 */
	protected boolean extraLocalIndex_computed = false;
	/**
	 * @apilevel internal
	 */
	protected int extraLocalIndex_value;

	/**
	 * @attribute syn
	 * @aspect EnhancedForToBytecode
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:16
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public int extraLocalIndex() {
		if (extraLocalIndex_computed) {
			return extraLocalIndex_value;
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		extraLocalIndex_value = extraLocalIndex_compute();
		if (isFinal && num == state().boundariesCrossed) {
			extraLocalIndex_computed = true;
		}
		return extraLocalIndex_value;
	}

	/**
	 * @apilevel internal
	 */
	private int extraLocalIndex_compute() {
		return localNum();
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
			return getStmt().modifiedInScope(var);
		} finally {
		}
	}

	/**
	 * @attribute inh
	 * @aspect EnhancedFor
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:38
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public SimpleSet lookupVariable(final String name) {
		ASTNode$State state = state();
		SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
		return lookupVariable_String_value;
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:41
	 * @apilevel internal
	 */
	@Override
	public SimpleSet Define_SimpleSet_lookupVariable(final ASTNode caller, final ASTNode child, final String name) {
		if (caller == getStmtNoTransform()) {
			return localLookupVariable(name);
		} else if (caller == getExprNoTransform()) {
			return localLookupVariable(name);
		} else if (caller == getVariableDeclarationNoTransform()) {
			return localLookupVariable(name);
		} else {
			return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:43
	 * @apilevel internal
	 */
	@Override
	public NameType Define_NameType_nameType(final ASTNode caller, final ASTNode child) {
		if (caller == getVariableDeclarationNoTransform()) {
			return NameType.TYPE_NAME;
		} else {
			return getParent().Define_NameType_nameType(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:48
	 * @apilevel internal
	 */
	@Override
	public VariableScope Define_VariableScope_outerScope(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return this;
		} else if (caller == getExprNoTransform()) {
			return this;
		} else if (caller == getVariableDeclarationNoTransform()) {
			return this;
		} else {
			return getParent().Define_VariableScope_outerScope(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:71
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isMethodParameter(final ASTNode caller, final ASTNode child) {
		if (caller == getVariableDeclarationNoTransform()) {
			return false;
		} else {
			return getParent().Define_boolean_isMethodParameter(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:72
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isConstructorParameter(final ASTNode caller, final ASTNode child) {
		if (caller == getVariableDeclarationNoTransform()) {
			return false;
		} else {
			return getParent().Define_boolean_isConstructorParameter(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:73
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isExceptionHandlerParameter(final ASTNode caller, final ASTNode child) {
		if (caller == getVariableDeclarationNoTransform()) {
			return false;
		} else {
			return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:80
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_reachable(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return reachable();
		} else {
			return getParent().Define_boolean_reachable(this, caller);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:97
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDAbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getStmtNoTransform()) {
			return getExpr().isDAafter(v);
		} else if (caller == getExprNoTransform()) {
			return v == getVariableDeclaration() || isDAbefore(v);
		} else {
			return getParent().Define_boolean_isDAbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:111
	 * @apilevel internal
	 */
	@Override
	public boolean Define_boolean_isDUbefore(final ASTNode caller, final ASTNode child, final Variable v) {
		if (caller == getStmtNoTransform()) {
			return getExpr().isDUafter(v);
		} else if (caller == getExprNoTransform()) {
			return v != getVariableDeclaration() && isDUbefore(v);
		} else {
			return getParent().Define_boolean_isDUbefore(this, caller, v);
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/EnhancedFor.jrag:113
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
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddExtensions/Jimple1.5Backend/EnhancedForCodegen.
	 *             jrag:18
	 * @apilevel internal
	 */
	@Override
	public int Define_int_localNum(final ASTNode caller, final ASTNode child) {
		if (caller == getStmtNoTransform()) {
			return getVariableDeclaration().localNum() + getVariableDeclaration().type().size();
		} else if (caller == getVariableDeclarationNoTransform()) {
			return localNum() + (getExpr().type().isArrayDecl() ? 2 : 1);
		} else {
			return getParent().Define_int_localNum(this, caller);
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
