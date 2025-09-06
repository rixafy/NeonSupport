package org.nette.neon.psi.impl.elements

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.nette.neon.NeonLanguage
import org.nette.neon.file.NeonFileType
import org.nette.neon.psi.elements.NeonFile
import org.nette.neon.psi.elements.NeonPsiElement

class NeonFileImpl(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, NeonLanguage.INSTANCE), NeonFile {
    override fun getFileType(): FileType {
        return NeonFileType.INSTANCE
    }

    override fun toString(): String {
        return "NeonFile: $name"
    }

    override val value: NeonPsiElement?
        get() {
            for (el in children) {
                if (el is NeonPsiElement) {
                    return el
                }
            }
            return null
        }

    override val neonFile: NeonFile
        get() = this
}
