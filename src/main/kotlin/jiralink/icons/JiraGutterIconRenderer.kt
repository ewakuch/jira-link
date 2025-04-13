package jiralink.icons

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.IconLoader
import java.awt.Desktop
import java.net.URI
import javax.swing.Icon

class JiraGutterIconRenderer(private val url: String) : GutterIconRenderer() {

    override fun getIcon(): Icon {
        return IconLoader.getIcon("/icons/jira.svg", javaClass)
    }

    override fun equals(other: Any?): Boolean {
        return other is JiraGutterIconRenderer && other.url == url
    }

    override fun hashCode(): Int {
        return url.hashCode()
    }

    override fun getTooltipText(): String {
        return url
    }

    override fun getAlignment(): Alignment {
        return Alignment.LEFT
    }

    override fun getClickAction(): AnAction {
        return object : AnAction() {
            override fun actionPerformed(e: AnActionEvent) {
                try {
                    Desktop.getDesktop().browse(URI(url))
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
}