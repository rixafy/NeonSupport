package org.nette.neon.psi.impl.elements

import com.intellij.lang.ASTNode
import org.nette.neon.parser.NeonElementTypes
import org.nette.neon.psi.elements.NeonChainedEntity
import org.nette.neon.psi.elements.NeonEntity

class NeonChainedEntityImpl(astNode: ASTNode) : NeonPsiElementImpl(astNode), NeonChainedEntity {
    override fun toString(): String {
        return "Neon chained entity"
    }

    override val values: MutableList<NeonEntity?>?
        get() = findChildrenByType<NeonEntity?>(NeonElementTypes.ENTITY)
}
