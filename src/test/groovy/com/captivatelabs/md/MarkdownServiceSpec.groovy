package com.captivatelabs.md

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MarkdownServiceSpec extends Specification implements ServiceUnitTest<MarkdownService> {

    def setup() {
    }

    def cleanup() {
    }

    void "test md parse"() {
        given:
        String md = "This is *Sparta*"
        StringBuffer buffer = new StringBuffer()
        service.render(md, buffer)

        expect:
        buffer.toString() == "<p>This is <em>Sparta</em></p>\n"
    }

    void "test markdown file resolution"() {
        when:
        Locale locale = new Locale(language, country)

        then:
        service.getMarkdown('test-content', locale) == content

        where:
        language || country || content
        "es"     || 'ES'    || "hola España"
        "es"     || 'MX'    || "hola"
        "en"     || 'US'    || "hello"
        "it"     || 'IT'    || "ciao"
    }
}
