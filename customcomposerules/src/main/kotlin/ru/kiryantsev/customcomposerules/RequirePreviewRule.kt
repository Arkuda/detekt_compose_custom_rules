package ru.kiryantsev.customcomposerules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Metric
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.ThresholdedCodeSmell
import io.gitlab.arturbosch.detekt.api.internal.ActiveByDefault
import org.jetbrains.kotlin.com.intellij.psi.PsiFile
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction

class RequirePreviewRule(config: Config = Config.empty) : Rule(config) {

    companion object {
        private const val READ_ONLY_COMPOSABLE_KEY = "ReadOnlyComposable"
        private const val PREVIEW_KEY = "Preview"
        private const val COMPOSABLE_KEY = "Composable"
    }

    override val issue: Issue = Issue(
        id = "RequirePreviewRule",
        severity = Severity.Maintainability,
        description = "Each Composable function must have a corresponding Preview function, " +
                "unless it is marked as ReadOnlyComposable.",
        debt = Debt.TEN_MINS
    )

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)
        val functions = file.declarations.filterIsInstance<KtNamedFunction>()
        val composableFunctions = functions.filter { it.hasAnnotation(COMPOSABLE_KEY) }

        val composableFunRequirePreview = composableFunctions.firstOrNull {
            !it.hasAnnotation(PREVIEW_KEY) &&
                    !it.hasAnnotation(READ_ONLY_COMPOSABLE_KEY)
        }

        if (composableFunRequirePreview != null) {
            val hasPreviewFun = composableFunctions.any { it.hasAnnotation(PREVIEW_KEY) }
            if (!hasPreviewFun) {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(composableFunRequirePreview),
                        "Composable function '${composableFunRequirePreview.name}' does " +
                                "not have a corresponding Preview function."
                    )
                )
            }
        }
    }

    private fun KtAnnotated.hasAnnotation(annotation: String): Boolean {
        return this.annotationEntries.any { it.calleeExpression?.text == annotation }
    }
}