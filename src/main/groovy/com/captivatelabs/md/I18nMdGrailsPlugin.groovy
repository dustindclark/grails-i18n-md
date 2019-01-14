package com.captivatelabs.md

import grails.plugins.Plugin
import groovy.util.logging.Slf4j

@Slf4j
class I18nMdGrailsPlugin extends Plugin {
    def grailsVersion = "3.3.9 > *"
    def pluginExcludes = [
            "grails-app/**/*.md",
            "grails-app/controllers/**/*",
            "grails-app/views/**/*",
            "grails-app/i18n/*",
    ]
    def title = "Grails i18n Markdown Plugin"
    def author = "Dustin Clark"
    def authorEmail = "team@captivatelabs.com"
    def description = 'See readme.'
    def profiles = ['web']
    def documentation = "https://github.com/dustindclark/grails-i18n-md"
    def license = "MIT"
    def organization = [name: "Captivate Labs, Inc.", url: "https://www.captivatelabs.com/"]
    def issueManagement = [system: "github", url: "https://github.com/dustindclark/grails-i18n-md/issues"]
    def scm = [url: "https://github.com/dustindclark/grails-i18n-md"]
}
