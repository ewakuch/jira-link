package jiralink.annotations

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import jiralink.configurables.PluginSettings
import jiralink.icons.JiraGutterIconRenderer
import java.util.regex.Pattern

class JiraAnnotator : Annotator {
    companion object {
        private val JIRA_HIGHLIGHT_KEY = TextAttributesKey.createTextAttributesKey(
            "JIRA_HIGHLIGHT_KEY",
            DefaultLanguageHighlighterColors.STRING
        )
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is LeafPsiElement) {
            return
        }

        val project = element.project
        val settings = PluginSettings.getInstance(project)
        val trigramKeys = settings.jiraTrigram.split(";").map { it.trim() } // Split and trim keys
        val baseUrl = settings.baseUrl

        if (trigramKeys.isEmpty() || baseUrl.isBlank()) {
            return
        }

        for (key in trigramKeys) {
            if (key.isBlank()) continue // Skip empty keys

            val pattern = Pattern.compile("\\b$key-\\d+\\b")
            val matcher = pattern.matcher(element.text)

            while (matcher.find()) {
                val startOffset = element.textRange.startOffset + matcher.start()
                val endOffset = element.textRange.startOffset + matcher.end()
                val range = TextRange(startOffset, endOffset)

                val url = "$baseUrl${matcher.group()}"
                holder.newAnnotation(HighlightSeverity.INFORMATION, url)
                    .range(range)
                    .textAttributes(JIRA_HIGHLIGHT_KEY)
                    .tooltip(url)
                    .gutterIconRenderer(JiraGutterIconRenderer(url))
                    .create()
            }
        }
    }
}