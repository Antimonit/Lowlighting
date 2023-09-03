// This is a generated file. Not intended for manual editing.
package me.khol.intellij.lowlighting.language.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.khol.intellij.lowlighting.language.psi.*;

public class LowlightingRecordImpl extends ASTWrapperPsiElement implements LowlightingRecord {

  public LowlightingRecordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LowlightingVisitor visitor) {
    visitor.visitRecord(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LowlightingVisitor) accept((LowlightingVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LowlightingKey getKey() {
    return findNotNullChildByClass(LowlightingKey.class);
  }

  @Override
  @NotNull
  public LowlightingSeverity getSeverity() {
    return findNotNullChildByClass(LowlightingSeverity.class);
  }

}
