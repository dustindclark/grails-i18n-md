package com.captivatelabs.i18n.md

import grails.plugins.*

class I18nMdGrailsPlugin extends Plugin {
    def grailsVersion = "3.3.9 > *"
    def pluginExcludes = [
        "grails-app/i18n/md/**/*"
    ]
    def title = "Grails i18n Markdown Plugin"
    def author = "Dustin Clark"
    def authorEmail = "team@captivatelabs.com"
    def description = 'See readme.'
    def profiles = ['web']
    def documentation = "https://github.com/dustindclark/grails-i18n-md"
    def license = "MIT"
    def organization = [ name: "Captivate Labs, Inc.", url: "https://www.captivatelabs.com/" ]
    def issueManagement = [ system: "github", url: "https://github.com/dustindclark/grails-i18n-md/issues" ]
    def scm = [ url: "https://github.com/dustindclark/grails-i18n-md" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
        }
    }
}
