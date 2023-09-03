// This is a generated file. Not intended for manual editing.
package me.khol.intellij.lowlighting.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LowlightingVisitor extends PsiElementVisitor {

  public void visitKey(@NotNull LowlightingKey o) {
    visitNamedElement(o);
  }

  public void visitRecord(@NotNull LowlightingRecord o) {
    visitPsiElement(o);
  }

  public void visitSeverity(@NotNull LowlightingSeverity o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull LowlightingNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
