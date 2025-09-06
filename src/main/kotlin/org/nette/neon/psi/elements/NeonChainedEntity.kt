package org.nette.neon.psi.elements

interface NeonChainedEntity : NeonValue {
    val values: MutableList<NeonEntity?>?
}
