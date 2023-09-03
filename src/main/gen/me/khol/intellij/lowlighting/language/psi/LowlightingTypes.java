// This is a generated file. Not intended for manual editing.
package me.khol.intellij.lowlighting.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.khol.intellij.lowlighting.language.psi.impl.*;

public interface LowlightingTypes {

  IElementType KEY = new LowlightingElementType("KEY");
  IElementType RECORD = new LowlightingElementType("RECORD");
  IElementType SEVERITY = new LowlightingElementType("SEVERITY");

  IElementType ASSIGNMENT = new LowlightingTokenType("ASSIGNMENT");
  IElementType COMMENT = new LowlightingTokenType("COMMENT");
  IElementType EOL = new LowlightingTokenType("EOL");
  IElementType KEY_TOKEN = new LowlightingTokenType("KEY_TOKEN");
  IElementType SEVERITY_TOKEN = new LowlightingTokenType("SEVERITY_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == KEY) {
        return new LowlightingKeyImpl(node);
      }
      else if (type == RECORD) {
        return new LowlightingRecordImpl(node);
      }
      else if (type == SEVERITY) {
        return new LowlightingSeverityImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
