package org.nette.neon.editor;

import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider;
import org.nette.neon.NeonLanguage;
import org.nette.neon.psi.elements.NeonEntity;
import org.nette.neon.psi.elements.NeonKeyValPair;
import org.jetbrains.annotations.NotNull;

/**
 * Breadcrumbs info about which section are we editing now (just above the editor, below tabs)
 */
public class NeonBreadcrumbsInfoProvider implements BreadcrumbsProvider {
	private final Language[] ourLanguages = { NeonLanguage.INSTANCE};

	@Override
	public Language[] getLanguages() {
		return ourLanguages;
	}

	@Override
	public boolean acceptElement(@NotNull PsiElement e) {
		return (e instanceof NeonKeyValPair) || (e instanceof NeonEntity);
	}

	@NotNull
	@Override
	public String getElementInfo(@NotNull PsiElement e) {
		if (e instanceof NeonKeyValPair) {
			return ((NeonKeyValPair) e).getKeyText();

		} else if (e instanceof NeonEntity) {
			String name = ((NeonEntity) e).getName();
			return name != null ? name : "??";

		} else {
			return "??";
		}
	}

	@Override
	public String getElementTooltip(@NotNull PsiElement e) {
		return e.toString();
	}

}
