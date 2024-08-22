package ru.kiryantsev.customcomposerules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider
import ru.kiryantsev.customcomposerules.rules.RequirePreviewRule

class ComposeCustomRulesProvider : RuleSetProvider {
    override val ruleSetId: String = "custom-compose-rules"

    override fun instance(config: Config): RuleSet = RuleSet(
        id = ruleSetId,
        rules = listOf(
            RequirePreviewRule(config)
        )
    )
}