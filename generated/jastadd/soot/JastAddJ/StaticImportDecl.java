/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 7.5 Import Declarations
 *
 * @production StaticImportDecl : {@link ImportDecl};
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.ast:4
 */
public abstract class StaticImportDecl extends ImportDecl implements Cloneable {
	/**
	 * @apilevel low-level
	 */
	@Override
	public void flushCache() {
		super.flushCache();
		importedTypes_String_values = null;
		importedFields_String_values = null;
		importedMethods_String_values = null;
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
	public StaticImportDecl clone() throws CloneNotSupportedException {
		StaticImportDecl node = (StaticImportDecl) super.clone();
		node.importedTypes_String_values = null;
		node.importedFields_String_values = null;
		node.importedMethods_String_values = null;
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @ast method
	 *
	 */
	public StaticImportDecl() {
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
		children = new ASTNode[1];
	}

	/**
	 * @ast method
	 *
	 */
	public StaticImportDecl(final Access p0) {
		setChild(p0, 0);
	}

	/**
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@Override
	protected int numChildren() {
		return 1;
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
	 * Replaces the Access child.
	 *
	 * @param node
	 *            The new node to replace the Access child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@Override
	public void setAccess(final Access node) {
		setChild(node, 0);
	}

	/**
	 * Retrieves the Access child.
	 *
	 * @return The current node used as the Access child.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	@Override
	public Access getAccess() {
		return (Access) getChild(0);
	}

	/**
	 * Retrieves the Access child.
	 * <p>
	 * <em>This method does not invoke AST transformations.</em>
	 * </p>
	 *
	 * @return The current node used as the Access child.
	 * @apilevel low-level
	 * @ast method
	 *
	 */
	@Override
	public Access getAccessNoTransform() {
		return (Access) getChildNoTransform(0);
	}

	/**
	 * @attribute syn
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:53
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public abstract TypeDecl type();

	protected java.util.Map importedTypes_String_values;

	/*
	 * 7.5.3 A single-static-import declaration imports all accessible (\ufffd6.6) static members with a given simple
	 * name from a type. This makes these static members available under their simple name in the class and interface
	 * declarations of the compilation unit in which the single-static import declaration appears.* @attribute syn
	 *
	 * @aspect StaticImports
	 *
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:21
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public SimpleSet importedTypes(final String name) {
		Object _parameters = name;
		if (importedTypes_String_values == null) {
			importedTypes_String_values = new java.util.HashMap(4);
		}
		if (importedTypes_String_values.containsKey(_parameters)) {
			return (SimpleSet) importedTypes_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		SimpleSet importedTypes_String_value = importedTypes_compute(name);
		if (isFinal && num == state().boundariesCrossed) {
			importedTypes_String_values.put(_parameters, importedTypes_String_value);
		}
		return importedTypes_String_value;
	}

	/**
	 * @apilevel internal
	 */
	private SimpleSet importedTypes_compute(final String name) {
		SimpleSet set = SimpleSet.emptySet;
		for (Iterator iter = type().memberTypes(name).iterator(); iter.hasNext();) {
			TypeDecl decl = (TypeDecl) iter.next();
			if (decl.isStatic() && decl.accessibleFromPackage(packageName())) {
				set = set.add(decl);
			}
		}
		return set;
	}

	protected java.util.Map importedFields_String_values;

	/**
	 * @attribute syn
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:31
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public SimpleSet importedFields(final String name) {
		Object _parameters = name;
		if (importedFields_String_values == null) {
			importedFields_String_values = new java.util.HashMap(4);
		}
		if (importedFields_String_values.containsKey(_parameters)) {
			return (SimpleSet) importedFields_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		SimpleSet importedFields_String_value = importedFields_compute(name);
		if (isFinal && num == state().boundariesCrossed) {
			importedFields_String_values.put(_parameters, importedFields_String_value);
		}
		return importedFields_String_value;
	}

	/**
	 * @apilevel internal
	 */
	protected SimpleSet importedFields_compute(final String name) {
		SimpleSet set = SimpleSet.emptySet;
		for (Iterator iter = type().memberFields(name).iterator(); iter.hasNext();) {
			FieldDeclaration decl = (FieldDeclaration) iter.next();
			boolean accessible = decl.isPublic()
					|| (!decl.isPrivate() && decl.hostType().topLevelType().packageName().equals(packageName()));
			if (decl.isStatic() && accessible) {
				set = set.add(decl);
			}
		}
		return set;
	}

	protected java.util.Map importedMethods_String_values;

	/**
	 * @attribute syn
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:42
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public Collection importedMethods(final String name) {
		Object _parameters = name;
		if (importedMethods_String_values == null) {
			importedMethods_String_values = new java.util.HashMap(4);
		}
		if (importedMethods_String_values.containsKey(_parameters)) {
			return (Collection) importedMethods_String_values.get(_parameters);
		}
		ASTNode$State state = state();
		int num = state.boundariesCrossed;
		boolean isFinal = this.is$Final();
		Collection importedMethods_String_value = importedMethods_compute(name);
		if (isFinal && num == state().boundariesCrossed) {
			importedMethods_String_values.put(_parameters, importedMethods_String_value);
		}
		return importedMethods_String_value;
	}

	/**
	 * @apilevel internal
	 */
	private Collection importedMethods_compute(final String name) {
		Collection set = new HashSet();
		for (Iterator iter = type().memberMethods(name).iterator(); iter.hasNext();) {
			MethodDecl decl = (MethodDecl) iter.next();
			if (decl.isStatic() && (decl.isPublic()
					|| (!decl.isPrivate() && decl.hostType().topLevelType().packageName().equals(packageName())))) {
				set.add(decl);
			}
		}
		return set;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	public ASTNode rewriteTo() {
		return super.rewriteTo();
	}
}
