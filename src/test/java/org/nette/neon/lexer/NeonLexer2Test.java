package org.nette.neon.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.testFramework.UsefulTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class NeonLexer2Test extends UsefulTestCase {

	@Test
	public void test01() {
		Lexer l = createLexer();
		l.start("key: 'val'");

		assertEquals(NeonTokenTypes.NEON_LITERAL, l.getTokenType());
		assertEquals(0, l.getTokenStart());
		assertEquals(3, l.getTokenEnd());
		assertEquals("key", l.getTokenText());
		l.advance();

		assertEquals(NeonTokenTypes.NEON_COLON, l.getTokenType());
		assertEquals(3, l.getTokenStart());
		assertEquals(4, l.getTokenEnd());
		assertEquals(":", l.getTokenText());
		l.advance();

		assertEquals(NeonTokenTypes.NEON_WHITESPACE, l.getTokenType());
		assertEquals(4, l.getTokenStart());
		assertEquals(5, l.getTokenEnd());
		assertEquals(" ", l.getTokenText());
		l.advance();

		assertEquals(NeonTokenTypes.NEON_STRING, l.getTokenType());
		assertEquals(5, l.getTokenStart());
		assertEquals(10, l.getTokenEnd());
		assertEquals("'val'", l.getTokenText());
		l.advance();

		assertEquals(null, l.getTokenType());
	}

	@Test
	public void test02() {
		Lexer l = createLexer();
		l.start("key: 'val'", 4, 5);

		assertEquals(NeonTokenTypes.NEON_INDENT, l.getTokenType());
		assertEquals(4, l.getTokenStart());
		assertEquals(5, l.getTokenEnd());
		assertEquals(" ", l.getTokenText());
		l.advance();

		assertEquals(null, l.getTokenType());
	}

	private Lexer createLexer() {
		return new NeonLexer();
	}

}
