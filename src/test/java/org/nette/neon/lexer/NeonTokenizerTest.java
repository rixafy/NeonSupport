package org.nette.neon.lexer;

import com.intellij.openapi.util.Pair;
import com.intellij.psi.tree.IElementType;
import com.intellij.testFramework.UsefulTestCase;
import org.junit.Test;

import java.util.ArrayList;


/**
 *
 */
public class NeonTokenizerTest extends UsefulTestCase {

	@Test
	public void testParse() {
		ArrayList<Pair<IElementType,String>> expected = new ArrayList<Pair<IElementType,String>>();
		expected.add(new Pair<IElementType, String>(NeonTokenTypes.NEON_LITERAL, "key"));
		expected.add(new Pair<IElementType, String>(NeonTokenTypes.NEON_SYMBOL, ":"));
		expected.add(new Pair<IElementType, String>(NeonTokenTypes.NEON_WHITESPACE, " "));
		expected.add(new Pair<IElementType, String>(NeonTokenTypes.NEON_STRING, "'value'"));

		assertOrderedEquals(NeonTokenizer.parse("key: 'value'"), expected);
	}

}
