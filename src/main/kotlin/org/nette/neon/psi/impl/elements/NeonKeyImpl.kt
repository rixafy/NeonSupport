package org.nette.neon.psi.impl.elements

import com.intellij.lang.ASTNode
import org.nette.neon.psi.elements.NeonKey

class NeonKeyImpl(astNode: ASTNode) : NeonPsiElementImpl(astNode), NeonKey {
    override fun toString(): String {
        return "Neon key"
    }

    override val keyText: String
        get() = node.text

    override fun getName(): String {
        return this.keyText
    }
}
