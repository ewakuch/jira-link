package jiralink.configurables

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.*

class PluginDirectoryConfigurable(private val project: Project) : Configurable {
    private var panel: JPanel? = null
    private var trigramField: JTextField? = null
    private var baseUrlField: JTextField? = null

    override fun createComponent(): JComponent? {
        panel = JPanel().apply {
            layout = GroupLayout(this).apply {
                autoCreateGaps = true
                autoCreateContainerGaps = true
            }
        }

        val trigramLabel = JLabel("Jira Project Key:")
        trigramField = JTextField(10)
        val trigramHint =
            JLabel("<html>Upper case Project key e.g.: ABC, SNC<br>For multiple project keys, separate them with \";\"</html>").apply {
                foreground = UIManager.getColor("Label.disabledForeground") // Lighter gray color
                font = font.deriveFont(font.size2D - 1) // Slightly smaller font
                maximumSize = java.awt.Dimension(300, 40) // Fixed width, unlimited height
                horizontalAlignment = SwingConstants.LEFT
            }

        val baseUrlLabel = JLabel("Jira Base URL:")
        baseUrlField = JTextField(10)

        val baseUrlHint = JLabel("(e.g.: https://example.atlassian.net/browse/)").apply {
            foreground = UIManager.getColor("Label.disabledForeground") // Lighter gray color
            font = font.deriveFont(font.size2D - 1) // Slightly smaller font
        }

        val layout = panel!!.layout as GroupLayout
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(baseUrlLabel)
                        .addComponent(baseUrlHint)
                        .addComponent(trigramLabel)
                        .addComponent(trigramHint)
                )
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(baseUrlField)
                        .addComponent(trigramField)
                )
        )
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(baseUrlLabel)
                        .addComponent(baseUrlField)
                )
                .addComponent(baseUrlHint) // Add the hint below the Base URL field
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(trigramLabel)
                        .addComponent(trigramField)
                )
                .addComponent(trigramHint) // Add the hint below the Project Key field
        )

        return panel
    }

    override fun isModified(): Boolean {
        val settings = PluginSettings.getInstance(project)
        return trigramField?.text != settings.jiraTrigram || baseUrlField?.text != settings.baseUrl
    }

    override fun apply() {
        val settings = PluginSettings.getInstance(project)
        settings.jiraTrigram = trigramField?.text ?: settings.jiraTrigram
        settings.baseUrl = baseUrlField?.text ?: settings.baseUrl
    }

    override fun reset() {
        val settings = PluginSettings.getInstance(project)
        trigramField?.text = settings.jiraTrigram
        baseUrlField?.text = settings.baseUrl
    }

    override fun getDisplayName(): String = "Jira Link Settings"

    override fun disposeUIResources() {
        panel = null
        trigramField = null
        baseUrlField = null
    }
}