package org.nette.neon.psi.impl.elements

import com.intellij.lang.ASTNode
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.nette.neon.lexer.NeonTokenTypes
import org.nette.neon.psi.elements.NeonScalar

class NeonScalarImpl(astNode: ASTNode) : NeonPsiElementImpl(astNode), NeonScalar {
    override fun toString(): String {
        return "Neon scalar"
    }

    override val valueText: String
        get() {
            var text = firstChild.text
            if (firstChild is LeafPsiElement && (firstChild as LeafPsiElement).elementType === NeonTokenTypes.NEON_STRING) {
                text = text.substring(1, text.length - 1)
            }

            return text
        }

    override fun getName(): String {
        return this.valueText
    }
}
