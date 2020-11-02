// This is a generated file. Not intended for manual editing.
package me.khol.intellij.plugin.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.khol.intellij.plugin.language.psi.impl.*;

public interface LowlightingTypes {

  IElementType PROPERTY = new LowlightingElementType("PROPERTY");

  IElementType COMMENT = new LowlightingTokenType("COMMENT");
  IElementType CRLF = new LowlightingTokenType("CRLF");
  IElementType KEY = new LowlightingTokenType("KEY");
  IElementType SEPARATOR = new LowlightingTokenType("SEPARATOR");
  IElementType VALUE = new LowlightingTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new LowlightingPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
