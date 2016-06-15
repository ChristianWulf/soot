package soot.JastAddJ;

import java.util.ArrayList;
import java.util.Arrays;

import beaver.Symbol;
import soot.ArrayType;
import soot.Local;
import soot.PatchingChain;
import soot.RefType;
import soot.SootFieldRef;
import soot.SootMethodRef;
import soot.Trap;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.CaughtExceptionRef;
import soot.jimple.IdentityStmt;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;
import soot.jimple.ReturnVoidStmt;
/**
 * @ast class
 *
 */
public class Body extends java.lang.Object {

	int nextTempIndex = 0;


	protected final soot.jimple.JimpleBody body;	// private final  added by chw


	protected final java.util.Stack chains;// private final  added by chw


	private final TypeDecl typeDecl;// private final  added by chw


	public Body(final TypeDecl typeDecl, final soot.jimple.JimpleBody body, final ASTNode container) {
		this.typeDecl = typeDecl;
		this.body = body;
		chains = new java.util.Stack();
		chains.push(body.getUnits());
		setLine(container);
		if(!body.getMethod().isStatic()) {
			emitThis(typeDecl);
		}
	}


	public Local getParam(final int i)
	{
		return body.getParameterLocal(i);
	}


	public Local newTemp(final soot.Type type) {
		Local local = Jimple.v().newLocal("temp$" + nextTempIndex++, type);
		body.getLocals().add(local);
		return local;
	}


	public Local newTemp(final soot.Value v) {
		if (v == NullConstant.v()) {
			throw new UnsupportedOperationException(
					"Cannot create a temporary local for null literal");
		}
		Local local = newTemp(v.getType());
		if(v instanceof soot.jimple.ParameterRef) {
			add(newIdentityStmt(local, v, null));
		}
		else {
			add(newAssignStmt(local, v, null));
		}
		copyLocation(v, local);
		return local;
	}


	public Local newLocal(final String name, final soot.Type type) {
		Local local = Jimple.v().newLocal(name, type);
		body.getLocals().add(local);
		if(name.equals("this") && thisName == null) {
			thisName = local;
		}
		return local;
	}



	private soot.tagkit.Tag lineTag;


	public void setLine(final ASTNode node)
	{
		if(node.getStart() != 0 && node.getEnd() != 0) {
			int line = Symbol.getLine(node.getStart());
			int column = Symbol.getColumn(node.getStart());
			int endLine = Symbol.getLine(node.getEnd());
			int endColumn = Symbol.getColumn(node.getEnd());
			String s = node.sourceFile();
			s = s != null ? s.substring(s.lastIndexOf(java.io.File.separatorChar)+1) : "Unknown";
			lineTag = new soot.tagkit.SourceLnNamePosTag(s, line, endLine, column, endColumn);
		}
		else {
			lineTag = new soot.tagkit.LineNumberTag(node.lineNumber());
		}
	}


	private soot.tagkit.Tag currentSourceRangeTag()
	{
		return lineTag;
	}



	public Body add(final soot.jimple.Stmt stmt) {
		if(list != null) {
			list.add(stmt);
			list = null;
		}
		stmt.addTag(currentSourceRangeTag());
		soot.PatchingChain<Unit> chain = (soot.PatchingChain<Unit>)chains.peek();
		if(stmt instanceof IdentityStmt && chain.size() != 0) {
			IdentityStmt idstmt = (IdentityStmt) stmt;
			if(!(idstmt.getRightOp() instanceof CaughtExceptionRef)) {
				soot.Unit s = chain.getFirst();
				while(s instanceof IdentityStmt) {
					s = chain.getSuccOf(s);
				}
				if(s != null) {
					chain.insertBefore(stmt, s);
					return this;
				}
			}
		}
		chain.add(stmt);
		return this;
	}


	public void pushBlock(final soot.PatchingChain c) {
		chains.push(c);
	}


	public void popBlock() {
		chains.pop();
	}



	public soot.jimple.Stmt newLabel() {
		return soot.jimple.Jimple.v().newNopStmt();
	}


	public Body addLabel(final soot.jimple.Stmt label) {
		add(label);
		return this;
	}



	public soot.Local emitThis(final TypeDecl typeDecl) {
		if(thisName == null) {
			thisName = newLocal("this", typeDecl.getSootType());
			if(body.getMethod().isStatic()) {
				add(Jimple.v().newIdentityStmt(thisName, Jimple.v().newParameterRef(typeDecl.getSootType(), 0)));
			} else {
				add(Jimple.v().newIdentityStmt(thisName, Jimple.v().newThisRef(typeDecl.sootRef())));
			}
		}
		return thisName;
	}


	Local thisName;



	public Body addTrap(final TypeDecl type, final soot.jimple.Stmt firstStmt, final soot.jimple.Stmt lastStmt, final soot.jimple.Stmt handlerStmt) {
		Trap newTrap = Jimple.v().newTrap(type.getSootClassDecl(), firstStmt, lastStmt, handlerStmt);
		body.getTraps().add(newTrap);
		return this;
	}



	public soot.jimple.Stmt previousStmt() {
		PatchingChain<Unit> o = (PatchingChain<Unit>)chains.lastElement();
		return (soot.jimple.Stmt)o.getLast();
	}


	public void addNextStmt(final java.util.List<soot.jimple.Stmt> list) {
		this.list = list;
	}


	java.util.List list = null;


	public soot.jimple.BinopExpr newXorExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newXorExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newUshrExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newUshrExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newSubExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newSubExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newShrExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newShrExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newShlExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newShlExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newRemExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newRemExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newOrExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newOrExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newNeExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newNeExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newMulExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newMulExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newLeExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newLeExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newGeExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newGeExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newEqExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newEqExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newDivExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newDivExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newCmplExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newCmplExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newCmpgExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newCmpgExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newCmpExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newCmpExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newGtExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newGtExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newLtExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newLtExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newAddExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newAddExpr(op1, op2), location, op1, op2); }


	public soot.jimple.BinopExpr newAndExpr(final soot.Value op1, final soot.Value op2, final ASTNode location) { return updateTags(Jimple.v().newAndExpr(op1, op2), location, op1, op2); }



	public soot.jimple.UnopExpr newNegExpr(final soot.Value op, final ASTNode location) { return updateTags(Jimple.v().newNegExpr(op), location, op); }


	public soot.jimple.UnopExpr newLengthExpr(final soot.Value op, final ASTNode location) { return updateTags(Jimple.v().newLengthExpr(op), location, op); }



	public soot.jimple.CastExpr newCastExpr(final Value op1, final Type t, final ASTNode location) {
		soot.jimple.CastExpr expr = Jimple.v().newCastExpr(op1, t);
		createTag(expr, location);
		soot.tagkit.Tag op1tag = getTag(op1);
		if(op1tag != null) {
			expr.getOpBox().addTag(op1tag);
		}
		return expr;
	}



	public soot.jimple.InstanceOfExpr newInstanceOfExpr(final Value op1, final Type t, final ASTNode location) {
		soot.jimple.InstanceOfExpr expr = Jimple.v().newInstanceOfExpr(op1, t);
		createTag(expr, location);
		soot.tagkit.Tag op1tag = getTag(op1);
		if(op1tag != null) {
			expr.getOpBox().addTag(op1tag);
		}
		return expr;
	}



	public soot.jimple.NewExpr newNewExpr(final RefType type, final ASTNode location) {
		soot.jimple.NewExpr expr = Jimple.v().newNewExpr(type);
		createTag(expr, location);
		return expr;
	}



	public soot.jimple.NewArrayExpr newNewArrayExpr(final Type type, final Value size, final ASTNode location) {
		soot.jimple.NewArrayExpr expr = Jimple.v().newNewArrayExpr(type, size);
		createTag(expr, location);
		soot.tagkit.Tag tag = getTag(size);
		if(tag != null) {
			expr.getSizeBox().addTag(tag);
		}
		return expr;
	}



	public soot.jimple.NewMultiArrayExpr newNewMultiArrayExpr(final ArrayType type, final java.util.List sizes, final ASTNode location) {
		soot.jimple.NewMultiArrayExpr expr = Jimple.v().newNewMultiArrayExpr(type, sizes);
		createTag(expr, location);
		for(int i = 0; i < sizes.size(); i++) {
			soot.tagkit.Tag tag = getTag((Value)sizes.get(i));
			if(tag != null) {
				expr.getSizeBox(i).addTag(tag);
			}
		}
		return expr;
	}



	public soot.jimple.StaticInvokeExpr newStaticInvokeExpr(final SootMethodRef method, final java.util.List args, final ASTNode location) {
		soot.jimple.StaticInvokeExpr expr = Jimple.v().newStaticInvokeExpr(method, args);
		createTag(expr, location);
		for(int i = 0; i < args.size(); i++) {
			soot.tagkit.Tag tag = getTag((Value)args.get(i));
			if(tag != null) {
				expr.getArgBox(i).addTag(tag);
			}
		}
		return expr;
	}



	public soot.jimple.SpecialInvokeExpr newSpecialInvokeExpr(final Local base, final SootMethodRef method, final java.util.List args, final ASTNode location) {
		soot.jimple.SpecialInvokeExpr expr = Jimple.v().newSpecialInvokeExpr(base, method, args);
		createTag(expr, location);
		for(int i = 0; i < args.size(); i++) {
			soot.tagkit.Tag tag = getTag((Value)args.get(i));
			if(tag != null) {
				expr.getArgBox(i).addTag(tag);
			}
		}
		return expr;
	}



	public soot.jimple.VirtualInvokeExpr newVirtualInvokeExpr(final Local base, final SootMethodRef method, final java.util.List args, final ASTNode location) {
		soot.jimple.VirtualInvokeExpr expr = Jimple.v().newVirtualInvokeExpr(base, method, args);
		createTag(expr, location);
		for(int i = 0; i < args.size(); i++) {
			soot.tagkit.Tag tag = getTag((Value)args.get(i));
			if(tag != null) {
				expr.getArgBox(i).addTag(tag);
			}
		}
		return expr;
	}



	public soot.jimple.InterfaceInvokeExpr newInterfaceInvokeExpr(final Local base, final SootMethodRef method, final java.util.List args, final ASTNode location) {
		soot.jimple.InterfaceInvokeExpr expr = Jimple.v().newInterfaceInvokeExpr(base, method, args);
		createTag(expr, location);
		for(int i = 0; i < args.size(); i++) {
			soot.tagkit.Tag tag = getTag((Value)args.get(i));
			if(tag != null) {
				expr.getArgBox(i).addTag(tag);
			}
		}
		return expr;
	}



	public soot.jimple.StaticInvokeExpr newStaticInvokeExpr(final SootMethodRef method, final ASTNode location) {
		return newStaticInvokeExpr(method, new ArrayList(), location);
	}


	public soot.jimple.SpecialInvokeExpr newSpecialInvokeExpr(final Local base, final SootMethodRef method, final ASTNode location) {
		return newSpecialInvokeExpr(base, method, new ArrayList(), location);
	}


	public soot.jimple.VirtualInvokeExpr newVirtualInvokeExpr(final Local base, final SootMethodRef method, final ASTNode location) {
		return newVirtualInvokeExpr(base, method, new ArrayList(), location);
	}


	public soot.jimple.InterfaceInvokeExpr newInterfaceInvokeExpr(final Local base, final SootMethodRef method, final ASTNode location) {
		return newInterfaceInvokeExpr(base, method, new ArrayList(), location);
	}


	public soot.jimple.StaticInvokeExpr newStaticInvokeExpr(final SootMethodRef method, final Value arg, final ASTNode location) {
		return newStaticInvokeExpr(method, Arrays.asList(new Value[] { arg }), location);
	}


	public soot.jimple.SpecialInvokeExpr newSpecialInvokeExpr(final Local base, final SootMethodRef method, final Value arg, final ASTNode location) {
		return newSpecialInvokeExpr(base, method, Arrays.asList(new Value[] { arg }), location);
	}


	public soot.jimple.VirtualInvokeExpr newVirtualInvokeExpr(final Local base, final SootMethodRef method, final Value arg, final ASTNode location) {
		return newVirtualInvokeExpr(base, method, Arrays.asList(new Value[] { arg }), location);
	}


	public soot.jimple.InterfaceInvokeExpr newInterfaceInvokeExpr(final Local base, final SootMethodRef method, final Value arg, final ASTNode location) {
		return newInterfaceInvokeExpr(base, method, Arrays.asList(new Value[] { arg }), location);
	}



	public soot.jimple.ThrowStmt newThrowStmt(final Value op, final ASTNode location) {
		soot.jimple.ThrowStmt stmt = Jimple.v().newThrowStmt(op);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getOpBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.ExitMonitorStmt newExitMonitorStmt(final Value op, final ASTNode location) {
		soot.jimple.ExitMonitorStmt stmt = Jimple.v().newExitMonitorStmt(op);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getOpBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.EnterMonitorStmt newEnterMonitorStmt(final Value op, final ASTNode location) {
		soot.jimple.EnterMonitorStmt stmt = Jimple.v().newEnterMonitorStmt(op);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getOpBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.GotoStmt newGotoStmt(final Unit target, final ASTNode location) {
		soot.jimple.GotoStmt stmt = Jimple.v().newGotoStmt(target);
		return stmt;
	}



	public soot.jimple.ReturnVoidStmt newReturnVoidStmt(final ASTNode location) {
		ReturnVoidStmt returnVoidStmt = Jimple.v().newReturnVoidStmt();
		return returnVoidStmt;
	}



	public soot.jimple.ReturnStmt newReturnStmt(final Value op, final ASTNode location) {
		soot.jimple.ReturnStmt stmt = Jimple.v().newReturnStmt(op);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getOpBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.IfStmt newIfStmt(final Value op, final Unit target, final ASTNode location) {
		soot.jimple.IfStmt stmt = Jimple.v().newIfStmt(op, target);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getConditionBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.IdentityStmt newIdentityStmt(final Value local, final Value identityRef, final ASTNode location) {
		soot.jimple.IdentityStmt stmt = Jimple.v().newIdentityStmt(local, identityRef);
		soot.tagkit.Tag left = getTag(local);
		if(left != null) {
			stmt.getLeftOpBox().addTag(left);
		}
		soot.tagkit.Tag right = getTag(identityRef);
		if(right != null) {
			stmt.getRightOpBox().addTag(right);
		}
		return stmt;
	}



	public soot.jimple.AssignStmt newAssignStmt(final Value variable, final Value rvalue, final ASTNode location) {
		soot.jimple.AssignStmt stmt = Jimple.v().newAssignStmt(variable, rvalue);
		soot.tagkit.Tag left = getTag(variable);
		if(left != null) {
			stmt.getLeftOpBox().addTag(left);
		}
		soot.tagkit.Tag right = getTag(rvalue);
		if(right != null) {
			stmt.getRightOpBox().addTag(right);
		}
		return stmt;
	}



	public soot.jimple.InvokeStmt newInvokeStmt(final Value op, final ASTNode location) {
		soot.jimple.InvokeStmt stmt = Jimple.v().newInvokeStmt(op);
		soot.tagkit.Tag tag = getTag(op);
		if(tag != null) {
			stmt.getInvokeExprBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.TableSwitchStmt newTableSwitchStmt(final Value key, final int lowIndex, final int highIndex, final java.util.List targets, final Unit defaultTarget, final ASTNode location) {
		soot.jimple.TableSwitchStmt stmt = Jimple.v().newTableSwitchStmt(key, lowIndex, highIndex, targets, defaultTarget);
		soot.tagkit.Tag tag = getTag(key);
		if(tag != null) {
			stmt.getKeyBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.LookupSwitchStmt newLookupSwitchStmt(final Value key, final java.util.List lookupValues, final java.util.List targets, final Unit defaultTarget, final ASTNode location) {
		soot.jimple.LookupSwitchStmt stmt = Jimple.v().newLookupSwitchStmt(key, lookupValues, targets, defaultTarget);
		soot.tagkit.Tag tag = getTag(key);
		if(tag != null) {
			stmt.getKeyBox().addTag(tag);
		}
		return stmt;
	}



	public soot.jimple.StaticFieldRef newStaticFieldRef(final SootFieldRef f, final ASTNode location) {
		soot.jimple.StaticFieldRef ref = Jimple.v().newStaticFieldRef(f);
		createTag(ref, location);
		return ref;
	}



	public soot.jimple.ThisRef newThisRef(final RefType t, final ASTNode location) {
		soot.jimple.ThisRef ref = Jimple.v().newThisRef(t);
		createTag(ref, location);
		return ref;
	}



	public soot.jimple.ParameterRef newParameterRef(final Type paramType, final int number, final ASTNode location) {
		soot.jimple.ParameterRef ref = Jimple.v().newParameterRef(paramType, number);
		createTag(ref, location);
		return ref;
	}



	public soot.jimple.InstanceFieldRef newInstanceFieldRef(final Value base, final SootFieldRef f, final ASTNode location) {
		soot.jimple.InstanceFieldRef ref = Jimple.v().newInstanceFieldRef(base, f);
		createTag(ref, location);
		soot.tagkit.Tag tag = getTag(base);
		if(tag != null) {
			ref.getBaseBox().addTag(tag);
		}
		return ref;
	}



	public soot.jimple.CaughtExceptionRef newCaughtExceptionRef(final ASTNode location) {
		soot.jimple.CaughtExceptionRef ref = Jimple.v().newCaughtExceptionRef();
		createTag(ref, location);
		return ref;
	}



	public soot.jimple.ArrayRef newArrayRef(final Value base, final Value index, final ASTNode location) {
		soot.jimple.ArrayRef ref = Jimple.v().newArrayRef(base, index);
		createTag(ref, location);
		soot.tagkit.Tag baseTag = getTag(base);
		if(baseTag != null) {
			ref.getBaseBox().addTag(baseTag);
		}
		soot.tagkit.Tag indexTag = getTag(index);
		if(indexTag != null) {
			ref.getIndexBox().addTag(indexTag);
		}
		return ref;
	}



	private soot.jimple.BinopExpr updateTags(final soot.jimple.BinopExpr binary, final ASTNode binaryLocation, final soot.Value op1, final soot.Value op2) {
		createTag(binary, binaryLocation);
		soot.tagkit.Tag op1tag = getTag(op1);
		if(op1tag != null) {
			binary.getOp1Box().addTag(op1tag);
		}
		soot.tagkit.Tag op2tag = getTag(op2);
		if(op2tag != null) {
			binary.getOp2Box().addTag(op2tag);
		}
		return binary;
	}


	private soot.jimple.UnopExpr updateTags(final soot.jimple.UnopExpr unary, final ASTNode unaryLocation, final soot.Value op) {
		createTag(unary, unaryLocation);
		soot.tagkit.Tag optag = getTag(op);
		if(optag != null) {
			unary.getOpBox().addTag(optag);
		}
		return unary;
	}



	private final java.util.HashMap<soot.Value, soot.tagkit.Tag> tagMap = new java.util.HashMap<soot.Value, soot.tagkit.Tag>();


	private soot.tagkit.Tag getTag(final soot.Value value) {
		return tagMap.get(value);
	}


	private void createTag(final soot.Value value, final ASTNode node) {
		if(node == null || tagMap.containsKey(value)) {
			return;
		}
		if(node.getStart() != 0 && node.getEnd() != 0) {
			int line = Symbol.getLine(node.getStart());
			int column = Symbol.getColumn(node.getStart());
			int endLine = Symbol.getLine(node.getEnd());
			int endColumn = Symbol.getColumn(node.getEnd());
			String s = node.sourceFile();
			s = s != null ? s.substring(s.lastIndexOf(java.io.File.separatorChar)+1) : "Unknown";
			tagMap.put(value, new soot.tagkit.SourceLnNamePosTag(s, line, endLine, column, endColumn));
		}
		else {
			tagMap.put(value, new soot.tagkit.LineNumberTag(node.lineNumber()));
		}
	}


	public void copyLocation(final soot.Value fromValue, final soot.Value toValue) {
		soot.tagkit.Tag tag = tagMap.get(fromValue);
		if(tag != null) {
			tagMap.put(toValue, tag);
		}
	}


}
