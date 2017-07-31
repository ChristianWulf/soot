/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

/**
 * 7.5.3 A single-static-import declaration imports all accessible (\ufffd\ufffd6.6) static members with a given simple
 * name from a type. This makes these static members available under their simple name in the class and interface
 * declarations of the compilation unit in which the single-static import declaration appears.
 *
 * @production SingleStaticImportDecl : {@link StaticImportDecl} ::= <span class="component">&lt;ID:String&gt;</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.ast:12
 */
public class SingleStaticImportDecl extends StaticImportDecl implements Cloneable {
	/**
	 * @apilevel low-level
	 */
	@Override
	public void flushCache() {
		super.flushCache();
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
	public SingleStaticImportDecl clone() throws CloneNotSupportedException {
		SingleStaticImportDecl node = (SingleStaticImportDecl) super.clone();
		node.in$Circle(false);
		node.is$Final(false);
		return node;
	}

	/**
	 * @apilevel internal
	 */
	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public SingleStaticImportDecl copy() {
		try {
			SingleStaticImportDecl node = clone();
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
	public SingleStaticImportDecl fullCopy() {
		SingleStaticImportDecl tree = copy();
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
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:61
	 */
	@Override
	public void typeCheck() {
		if (!getAccess().type().typeName().equals(typeName()) && !getAccess().type().isUnknown()) {
			error("Single-type import " + typeName() + " is not the canonical name of type "
					+ getAccess().type().typeName());
		}
	}

	/**
	 * @ast method
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:93
	 */
	@Override
	public void nameCheck() {
		if (importedFields(name()).isEmpty() && importedMethods(name()).isEmpty() && importedTypes(name()).isEmpty()
				&& !getAccess().type().isUnknown()) {
			error("Semantic Error: At least one static member named " + name()
					+ " must be available in static imported type " + type().fullName());
		}
	}

	/**
	 * @ast method
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:207
	 */
	@Override
	public void toString(final StringBuffer s) {
		s.append("import static ");
		getAccess().toString(s);
		s.append("." + getID());
		s.append(";\n");
	}

	/**
	 * @ast method
	 *
	 */
	public SingleStaticImportDecl() {
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
	public SingleStaticImportDecl(final Access p0, final String p1) {
		setChild(p0, 0);
		setID(p1);
	}

	/**
	 * @ast method
	 *
	 */
	public SingleStaticImportDecl(final Access p0, final beaver.Symbol p1) {
		setChild(p0, 0);
		setID(p1);
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
	 * Replaces the lexeme ID.
	 *
	 * @param value
	 *            The new value for the lexeme ID.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public void setID(final String value) {
		tokenString_ID = value;
	}

	/**
	 * @apilevel internal
	 * @ast method
	 *
	 */

	/**
	 * @apilevel internal
	 */
	protected String tokenString_ID;
	/**
	 * @ast method
	 *
	 */

	public int IDstart;
	/**
	 * @ast method
	 *
	 */

	public int IDend;

	/**
	 * JastAdd-internal setter for lexeme ID using the Beaver parser.
	 *
	 * @apilevel internal
	 * @ast method
	 *
	 */
	public void setID(final beaver.Symbol symbol) {
		if (symbol.value != null && !(symbol.value instanceof String)) {
			throw new UnsupportedOperationException("setID is only valid for String lexemes");
		}
		tokenString_ID = (String) symbol.value;
		IDstart = symbol.getStart();
		IDend = symbol.getEnd();
	}

	/**
	 * Retrieves the value for the lexeme ID.
	 *
	 * @return The value for the lexeme ID.
	 * @apilevel high-level
	 * @ast method
	 *
	 */
	public String getID() {
		return tokenString_ID != null ? tokenString_ID : "";
	}

	/**
	 * @attribute syn
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:53
	 */
	@Override
	public TypeDecl type() {
		ASTNode$State state = state();
		try {
			return getAccess().type();
		} finally {
		}
	}

	/**
	 * @attribute syn
	 * @aspect StaticImports
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:99
	 */
	public String name() {
		ASTNode$State state = state();
		try {
			return getID();
		} finally {
		}
	}

	/**
	 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/StaticImports.jrag:203
	 * @apilevel internal
	 */
	@Override
	public NameType Define_NameType_nameType(final ASTNode caller, final ASTNode child) {
		if (caller == getAccessNoTransform()) {
			return NameType.TYPE_NAME;
		} else {
			return getParent().Define_NameType_nameType(this, caller);
		}
	}

	/**
	 * @apilevel internal
	 */
	@Override
	public ASTNode rewriteTo() {
		return super.rewriteTo();
	}

	/**
	 * @apilevel internal
	 */
	@Override
	protected SimpleSet importedFields_compute(final String name) {
		if (!name.equals(name())) {
			return SimpleSet.emptySet;
		}

		return super.importedFields_compute(name);
	}
}
