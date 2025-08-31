package org.nette.neon.parser;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.nette.neon.NeonLanguage;
import org.nette.neon.lexer.NeonTokenTypes;

/**
 * Types of elements returned from parser
 */
public interface NeonElementTypes {
	public static final IFileElementType FILE = new IFileElementType(NeonLanguage.INSTANCE);

	public static final NeonElementType KEY_VALUE_PAIR = new NeonElementType("Key value pair");
	public static final NeonElementType KEY = new NeonElementType("Key");
	public static final NeonElementType COMPOUND_KEY = new NeonElementType("Compound key");
	public static final NeonElementType HASH = new NeonElementType("Hash");
	public static final NeonElementType ITEM = new NeonElementType("Item");
	public static final NeonElementType ENTITY = new NeonElementType("Entity");
	public static final NeonElementType CHAINED_ENTITY = new NeonElementType("Chained entity");
	public static final NeonElementType ARRAY = new NeonElementType("Array");
	public static final NeonElementType ARGS = new NeonElementType("Args");
	public static final NeonElementType COMPOUND_VALUE = new NeonElementType("Compound value");
	public static final NeonElementType SCALAR_VALUE = new NeonElementType("Scalar value");
	public static final NeonElementType REFERENCE = new NeonElementType("Reference");

	public static final TokenSet SCALAR_VALUES = TokenSet.create(
		NeonTokenTypes.NEON_STRING,
		NeonTokenTypes.NEON_NUMBER,
		NeonTokenTypes.NEON_REFERENCE,
		NeonTokenTypes.NEON_IDENTIFIER,
		NeonTokenTypes.NEON_LITERAL,
		NeonTokenTypes.NEON_VARIABLE,
		NeonTokenTypes.NEON_DOUBLE_COLON, NeonTokenTypes.NEON_DOLLAR, NeonTokenTypes.NEON_AT
	);
}
