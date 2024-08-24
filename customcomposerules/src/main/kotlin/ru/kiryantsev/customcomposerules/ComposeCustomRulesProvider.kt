package ru.kiryantsev.customcomposerules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class ComposeCustomRulesProvider : RuleSetProvider {

    override val ruleSetId: String = "CustomComposeRules"

    override fun instance(config: Config): RuleSet = RuleSet(
        id = ruleSetId,
        rules = listOf(
            RequirePreviewRule(config)
        )
    )
}