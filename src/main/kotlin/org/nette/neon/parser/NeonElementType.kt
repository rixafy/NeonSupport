package org.nette.neon.parser

import com.intellij.psi.tree.IElementType
import org.nette.neon.NeonLanguage

class NeonElementType(debugName: String) : IElementType(debugName, NeonLanguage.INSTANCE) {
    override fun toString(): String {
        return "[Neon] " + super.toString()
    }
}
