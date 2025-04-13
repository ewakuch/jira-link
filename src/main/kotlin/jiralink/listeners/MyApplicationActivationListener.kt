package jiralink.listeners

import com.intellij.openapi.application.ApplicationActivationListener

internal class MyApplicationActivationListener : ApplicationActivationListener {

//  override fun applicationActivated(ideFrame: IdeFrame) {
//    if (null != ideFrame.project) {
//      val project = ideFrame.project!!
//      thisLogger().info("ProjectService initialized")
//      DumbService.getInstance(ideFrame.project!!).runWhenSmart {
//        thisLogger().info("Project is smart, starting fetchAllClasses")
//        if (PluginDirectoryConfigurable(project).isPathSet()) {
//          val searchEngine = SearchEngine.getInstance()
//          if (searchEngine.getCache().isEmpty()) {
//            searchEngine.fetchAllClasses(project, true)
//          }
//          thisLogger().info("fetchAllClasses completed")
//        }
//      }
//    }
//  }
}
