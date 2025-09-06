package org.nette.neon.lexer

import com.intellij.lexer.Lexer
import com.intellij.testFramework.UsefulTestCase
import junit.framework.TestCase
import org.junit.Test

class NeonLexer2Test : UsefulTestCase() {
    private fun createLexer(): Lexer {
        return NeonLexer
    }

    @Test
    fun test01() {
        val l = createLexer()
        l.start("key: 'val'")

        assertEquals(NeonTokenTypes.NEON_LITERAL, l.tokenType)
        TestCase.assertEquals(0, l.tokenStart)
        TestCase.assertEquals(3, l.tokenEnd)
        TestCase.assertEquals("key", l.tokenText)
        l.advance()

        assertEquals(NeonTokenTypes.NEON_COLON, l.tokenType)
        TestCase.assertEquals(3, l.tokenStart)
        TestCase.assertEquals(4, l.tokenEnd)
        TestCase.assertEquals(":", l.tokenText)
        l.advance()

        assertEquals(NeonTokenTypes.NEON_WHITESPACE, l.tokenType)
        TestCase.assertEquals(4, l.tokenStart)
        TestCase.assertEquals(5, l.tokenEnd)
        TestCase.assertEquals(" ", l.tokenText)
        l.advance()

        assertEquals(NeonTokenTypes.NEON_STRING, l.tokenType)
        TestCase.assertEquals(5, l.tokenStart)
        TestCase.assertEquals(10, l.tokenEnd)
        TestCase.assertEquals("'val'", l.tokenText)
        l.advance()

        assertEquals(null, l.tokenType)
    }

    @Test
    fun test02() {
        val l = createLexer()
        l.start("key: 'val'", 4, 5)

        assertEquals(NeonTokenTypes.NEON_INDENT, l.tokenType)
        TestCase.assertEquals(4, l.tokenStart)
        TestCase.assertEquals(5, l.tokenEnd)
        TestCase.assertEquals(" ", l.tokenText)
        l.advance()

        assertEquals(null, l.tokenType)
    }
}
