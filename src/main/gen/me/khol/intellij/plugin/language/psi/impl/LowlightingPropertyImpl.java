// This is a generated file. Not intended for manual editing.
package me.khol.intellij.plugin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.khol.intellij.plugin.language.psi.LowlightingTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.khol.intellij.plugin.language.psi.*;

public class LowlightingPropertyImpl extends ASTWrapperPsiElement implements LowlightingProperty {

  public LowlightingPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LowlightingVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LowlightingVisitor) accept((LowlightingVisitor)visitor);
    else super.accept(visitor);
  }

}
