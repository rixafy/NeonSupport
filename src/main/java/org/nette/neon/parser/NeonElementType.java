package org.nette.neon.parser;

import com.intellij.psi.tree.IElementType;
import org.nette.neon.NeonLanguage;
import org.jetbrains.annotations.NotNull;


public class NeonElementType extends IElementType {
	public NeonElementType(@NotNull String debugName) {
		super(debugName, NeonLanguage.INSTANCE);
	}

	public String toString() {
		return "[Neon] " + super.toString();
	}
}
