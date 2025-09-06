package org.nette.neon.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import com.jetbrains.php.PhpIndex
import com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPropertyImpl
import org.nette.neon.psi.elements.NeonArray
import org.nette.neon.psi.elements.NeonFile
import org.nette.neon.psi.elements.NeonReference
import java.util.*

class ServiceCompletionProvider : CompletionProvider<CompletionParameters?>() {
    var curr: PsiElement? = null

    override fun addCompletions(
        params: CompletionParameters,
        context: ProcessingContext,
        results: CompletionResultSet
    ) {
        curr = params.position.originalElement
        if (curr!!.parent is NeonReference) {
            for (service in this.availableServices) {
                results.addElement(LookupElementBuilder.create(service))
            }
        }
    }


    private val availableServices: MutableList<String>
        get() {
            val services: MutableList<String> = LinkedList<String>()

            getAvailableServicesFromSystemContainer(services)

            if (curr!!.containingFile is NeonFile) getServicesFromNeonFile(
                services,
                curr!!.containingFile as NeonFile
            )

            return services
        }


    /**
     * Scans class SystemContainer to find all services in it
     */
    private fun getAvailableServicesFromSystemContainer(result: MutableList<String>) {
        val container = PhpIndex.getInstance(curr!!.project).getClassByName("SystemContainer")
        if (container != null) {
            for (field in container.fields) {
                if (field is PhpDocPropertyImpl) {
                    result.add(field.name)
                }
            }
        }
    }

    private fun getServicesFromNeonFile(result: MutableList<String>, file: NeonFile) {
        if (file.value is NeonArray) {
            val map = (file.value as NeonArray).map
            if (map!!.containsKey("services")) {
                addServiceFromNeonArray(result, (map.get("services") as NeonArray?)!!)
            }
        }
    }

    private fun addServiceFromNeonArray(result: MutableList<String>, hash: NeonArray) {
        for (key in hash.keys!!) {
            result.add(key!!.keyText!!)
        }
    }
}
