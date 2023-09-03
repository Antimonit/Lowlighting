// This is a generated file. Not intended for manual editing.
package me.khol.intellij.lowlighting.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static me.khol.intellij.lowlighting.language.psi.LowlightingTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LowlightingParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return lowlightingFile(b, l + 1);
  }

  /* ********************************************************** */
  // record | COMMENT | EOL
  static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    r = record(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, EOL);
    return r;
  }

  /* ********************************************************** */
  // KEY_TOKEN
  public static boolean key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_TOKEN);
    exit_section_(b, m, KEY, r);
    return r;
  }

  /* ********************************************************** */
  // expression*
  static boolean lowlightingFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lowlightingFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "lowlightingFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // key ASSIGNMENT severity
  public static boolean record(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key(b, l + 1);
    r = r && consumeToken(b, ASSIGNMENT);
    r = r && severity(b, l + 1);
    exit_section_(b, m, RECORD, r);
    return r;
  }

  /* ********************************************************** */
  // SEVERITY_TOKEN
  public static boolean severity(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "severity")) return false;
    if (!nextTokenIs(b, SEVERITY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEVERITY_TOKEN);
    exit_section_(b, m, SEVERITY, r);
    return r;
  }

}
