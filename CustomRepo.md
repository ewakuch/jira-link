# Intellij IDEA custom plugin repository

Below guide shows how to create Custom Plugin Repository for [Intellij IDEA](https://jetbrains.com) using [AWS S3](https://aws.amazon.com/s3/) buckets

### Minimum required:
1. Built .jar or .zip with plugin
2. `updatePlugin.xml` with proper reference to given plugin (see example in repository: [src/main/resources/META-INF/repository/updatePlugin.xml](src/main/resources/META-INF/repository/updatePlugin.xml) 
   - file name MUST be updatePlugin.xml
   - plugin version in .zip/.jar must match version declared in updatePlugin.xml
   - plugin URL must point to proper .zip/.jar file
3. Both above items must have public read access so Intellij can access them.

### Custom Repository setup:

1. Upload items from Minimum required section to your S3 bucket and make them public
2. Copy public link to `updatePlugin.xml`file
3. Open Intellij IDEA: <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Manage Plugin Repositories...</kbd> > <kbd>+</kbd> > <kbd>URL</kbd> >`https://{your-bucket-name}.s3.{aws-region}.amazonaws.com/plugin/updatePlugin.xml`
4. Then search for plugin in Marketplace: <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>
       Marketplace</kbd> > <kbd>Search for "jira link"</kbd> > <kbd>
       Install</kbd>
 

There is a sample JenkinsFile with Jenkins pipeline that builds and uploads necessary files to S3 bucket to a `plugin` folder in provided S3
bucket. [JenkinsFile](JenkinsFile)

Pipeline uses Kubernetes with Docker image but you can change `agent` part to anything you want, as long as there will be Java 21, Gradle in the same version as this project and awscli installed.
This file assumes Jenkins is configured with AWS credentials and has access to S3 bucket.

JenkinsFile also contains post-deploy action to send message
to Slack channel with latest notes from CHANGELOG.md file.
Plugin build lib is configured to have name: `jira-link.zip` so there can be fixed path in updatePlugin.xml regardless of the version, however when version is changed, `updatePlugin.xml` version section requires manual update.

