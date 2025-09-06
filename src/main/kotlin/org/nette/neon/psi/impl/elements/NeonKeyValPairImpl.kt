package org.nette.neon.psi.impl.elements

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import com.intellij.util.IncorrectOperationException
import org.jetbrains.annotations.NonNls
import org.nette.neon.parser.NeonElementTypes
import org.nette.neon.psi.elements.NeonKey
import org.nette.neon.psi.elements.NeonKeyValPair
import org.nette.neon.psi.elements.NeonValue

class NeonKeyValPairImpl(astNode: ASTNode) : NeonPsiElementImpl(astNode), NeonKeyValPair {
    override fun toString(): String {
        return "Neon key-val pair"
    }

    override val key: NeonKey?
        get() {
            val keys: Array<ASTNode?>?

            val compoundKeys =
                node.getChildren(TokenSet.create(NeonElementTypes.COMPOUND_KEY))
            keys = if (compoundKeys.size > 0) {
                compoundKeys[0]!!.getChildren(TokenSet.create(NeonElementTypes.KEY))
            } else {
                node.getChildren(TokenSet.create(NeonElementTypes.KEY))
            }

            return if (keys.isNotEmpty()) keys[0]!!.psi as NeonKey? else null
        }

    override val keyText: String?
        get() = this.key?.text

    override val value: NeonValue?
        get() {
            if (lastChild is NeonValue) {
                return lastChild as NeonValue
            }
            return null
        }

    @Throws(IncorrectOperationException::class)
    override fun setName(s: @NonNls String): PsiElement? {
        // TODO: needed for refactoring
        return null
    }

    override fun getName(): String? {
        return this.keyText
    }
}
