# Grails Markdown Internationalization Plugin

##grails-i18n-md

This plugin applies the i18n/messages.properties concept and applies it to
larger files formatted in markdown.  The idea is to be able to internationalize
larger files (think paragraphs, privacy policies, terms and conditions, etc).  You
can also use this plugin to extend messages.properties with markdown formatting.

To use this plugin, first add the following dependency to your Grails 3 project:

```java
repositories {
    mavenCentral()
    maven {
        url "https://dl.bintray.com/captivatelabs/plugins"
    }
    ...
}
dependencies {
    compile 'com.captivatelabs.grails.plugins:i18n-md:0.x.x'
    ...
}
```
Note the custom repo for this plugin, and the commonmark plugin is found in 
Maven Central.  Find the latest version # here:
https://bintray.com/captivatelabs/plugins/i18n-md

Then, create the following structure in your Grails project:

```
grails-app\i18n\md\<key>\default.md
grails-app\i18n\md\<key>\es.md
grails-app\i18n\md\<key>\pt_BR.md
...etc, etc.
```

Add your internationalized content with markdown formatting to this file structure,
where `<key>` is the key name for your content.  Then, in your GSPs, use the following
tag to render your internationalized content:

```gsp
<md:render key="<key>" />
```

This tag looks up your internationalized markdown based on the user's locale, and
makes use of the [commonmark-java](https://github.com/atlassian/commonmark-java)
library to convert this content into HTML.  Additionally, the content will be wrapped in a
`<div class='i18n-md'>...</div>` so that you can contextually style your md accordingly.

#TODO
- Tag for rendering markdown from messages.properties
- Add md injectable MD pre-processor and HTML post-processor.
- Potentially make use of other markdown processors (or extend for customization)
- Support Grails link building inside of .md files.

  


