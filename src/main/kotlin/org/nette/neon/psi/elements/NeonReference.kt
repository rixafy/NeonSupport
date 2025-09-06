package org.nette.neon.psi.elements

/**
 * Reference to an service: @database
 * TODO: for future version
 */
interface NeonReference : NeonValue {
    val serviceName: String?
}
