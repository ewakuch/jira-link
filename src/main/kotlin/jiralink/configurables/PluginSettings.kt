package jiralink.configurables

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
@State(name = "JiraLinkSettings", storages = [Storage("jiraLink.xml")])
class PluginSettings : PersistentStateComponent<PluginSettings.State> {

    data class State(var jiraTrigram: String = "ABC", var baseUrl: String = "https://example.atlassian.net/browse/")

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    var jiraTrigram: String
        get() = state.jiraTrigram
        set(value) {
            state.jiraTrigram = value
        }

    var baseUrl: String
        get() = state.baseUrl
        set(value) {
            state.baseUrl = value
        }

    companion object {
        fun getInstance(project: Project): PluginSettings {
            return project.getService(PluginSettings::class.java)
        }
    }
}