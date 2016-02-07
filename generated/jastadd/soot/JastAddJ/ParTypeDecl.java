package soot.JastAddJ;

import java.util.ArrayList;
import java.util.HashMap;
/**
  * @ast interface
 *
 */
public interface ParTypeDecl extends Parameterization {

    //syn String name();

    //syn String name();
    int getNumArgument();


    Access getArgument(int index);

    /**
     * @author Christian Wulf (chw)
     */
    public List<Access> getArguments();

    public String typeName();


    SimpleSet localFields(String name);


    HashMap localMethodsSignatureMap();
@Override
public TypeDecl substitute(TypeVariable typeVariable);

public int numTypeParameter();

public TypeVariable typeParameter(int index);

public Access substitute(Parameterization parTypeDecl);

public Access createQualifiedAccess();

public void transformation();

  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:244
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isParameterizedType();
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:245
   */
  @Override
@SuppressWarnings({"unchecked", "cast"})
  public boolean isRawType();
  /**
   * @attribute syn
   * @aspect GenericsTypeCheck
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:380
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean sameArgument(ParTypeDecl decl);
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:577
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean sameSignature(Access a);
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:612
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean sameSignature(ArrayList list);
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/GenericsParTypeDecl.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String nameWithArgs();
  /**
   * @attribute inh
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/GenericsParTypeDecl.jrag:45
   */
  public TypeDecl genericDecl();
}
