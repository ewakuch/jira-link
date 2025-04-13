# jira-link

<!-- Plugin description -->
Simple plugin to generate annotation Jira Links for configured pattern in any type of file.

More details and description available [HERE](https://github.com/ewakuch/jira-link)
<!-- Plugin description end -->

## Installation

- Manually:

  Download the [latest release](https://github.com/ewakuch/jira-link/releases) and install it
  manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


- Using the IDE built-in plugin system with your own plugin repository: [See this tutorial](CustomRepo.md)

### Setup:

Go to <kbd>Settings</kbd> > <kbd>Jira Link</kbd> and provide your base Jira link e.g.
`https://example.atlassian.net/browse/` and project key e.g. `ABC`.

You can provide multiple project keys separated by `;` if needed.

## Usage:

### Jira Issue Link generation:

Plugin recognizes Jira issue pattern `ABC-XXXX` and creates a hyperlink to the issue in the editor:

![img.png](src/main/resources/readmeResources/jiraLinkAnnotation.png)

When you hover over Jira icon, generated link will be shown:
![img.png](src/main/resources/readmeResources/annotationTooltip.png)

When you click the icon it will open the issue in the browser.

---
Custom Plugin 

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

