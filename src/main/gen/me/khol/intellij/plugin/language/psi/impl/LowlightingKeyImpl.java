// This is a generated file. Not intended for manual editing.
package me.khol.intellij.plugin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.khol.intellij.plugin.language.psi.LowlightingTypes.*;
import me.khol.intellij.plugin.language.psi.*;

public class LowlightingKeyImpl extends LowlightingNamedElementImpl implements LowlightingKey {

  public LowlightingKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LowlightingVisitor visitor) {
    visitor.visitKey(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LowlightingVisitor) accept((LowlightingVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public String getName() {
    return LowlightingPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@Nullable String newName) {
    return LowlightingPsiImplUtil.setName(this, newName);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return LowlightingPsiImplUtil.getNameIdentifier(this);
  }

}
