package me.khol.intellij.lowlighting.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import me.khol.intellij.lowlighting.language.psi.LowlightingFile
import me.khol.intellij.lowlighting.language.parser.LowlightingParser
import me.khol.intellij.lowlighting.language.psi.LowlightingTypes

class LowlightingParserDefinition : ParserDefinition {

    override fun createLexer(project: Project): Lexer = LowlightingLexerAdapter()

    override fun getWhitespaceTokens(): TokenSet = Util.WHITE_SPACES

    override fun getCommentTokens(): TokenSet = Util.COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createParser(project: Project): PsiParser = LowlightingParser()

    override fun getFileNodeType(): IFileElementType = Util.FILE

    override fun createFile(viewProvider: FileViewProvider): PsiFile = LowlightingFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode) = SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = LowlightingTypes.Factory.createElement(node)

    private object Util {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(LowlightingTypes.COMMENT)
        val FILE = IFileElementType(LowlightingLanguage)
    }
}
