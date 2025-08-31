package org.nette.neon.psi.impl.elements;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import org.nette.neon.NeonLanguage;
import org.nette.neon.file.NeonFileType;
import org.nette.neon.psi.elements.NeonFile;
import org.nette.neon.psi.elements.NeonPsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NeonFileImpl extends PsiFileBase implements NeonFile {
	public NeonFileImpl(FileViewProvider viewProvider) {
		super(viewProvider, NeonLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return NeonFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "NeonFile:" + getName();
	}

	@Override
	public NeonPsiElement getValue() {
		for (PsiElement el : getChildren()) {
			if (el instanceof NeonPsiElement) {
				return (NeonPsiElement) el;
			}
		}
		return null;
	}

	@Override
	public @Nullable NeonFile getNeonFile() {
		return this;
	}
}
