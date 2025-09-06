package org.nette.neon.lexer

import com.intellij.psi.tree.IElementType
import org.nette.neon.NeonLanguage

class NeonTokenType(debugName: String) : IElementType(debugName, NeonLanguage.INSTANCE) {
    override fun toString(): String {
        return "[Neon] " + super.toString()
    }
}
