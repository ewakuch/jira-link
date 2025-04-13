package jiralink.listeners

import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.openapi.diagnostic.thisLogger

class MyDynamicPluginListener : DynamicPluginListener {

    override fun pluginUnloaded(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
        if (pluginDescriptor.pluginId.idString == "com.github.jira-link") {
            thisLogger().info("Jira Link plugin unloaded")
            // Perform any additional cleanup here
        }
    }

    override fun pluginLoaded(pluginDescriptor: IdeaPluginDescriptor) {
        if (pluginDescriptor.pluginId.idString == "com.github.jira-link") {
            thisLogger().info("Jira Link plugin loaded")
            // Perform initialization here
        }
    }

}