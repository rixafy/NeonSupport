package org.nette.neon.completion

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import org.nette.neon.lexer.NeonTokenTypes
import org.nette.neon.parser.NeonElementTypes
import org.nette.neon.parser.NeonParser
import org.nette.neon.psi.elements.NeonArray
import org.nette.neon.psi.elements.NeonFile
import org.nette.neon.psi.elements.NeonKeyValPair
import org.nette.neon.psi.elements.NeonScalar

object CompletionUtil {
    fun isIncompleteKey(el: PsiElement): Boolean {
        if (!NeonTokenTypes.STRING_LITERALS.contains(el.node.elementType)) {
            return false
        }

        //first scalar in file
        if (el.parent is NeonScalar && el.parent.parent is NeonFile) {
            return true
        }

        //error element
        if (el.parent is NeonArray
            && el.prevSibling is PsiErrorElement
            && (el.prevSibling as PsiErrorElement).errorDescription == NeonParser.EXPECTED_ARRAY_ITEM
        ) {
            return true
        }

        //new key after new line
        return el.parent is NeonScalar &&
            (el.parent.parent is NeonKeyValPair || el.parent.parent.node.elementType == NeonElementTypes.ITEM) &&
            el.parent.prevSibling.node.elementType == NeonTokenTypes.NEON_INDENT
    }
}
