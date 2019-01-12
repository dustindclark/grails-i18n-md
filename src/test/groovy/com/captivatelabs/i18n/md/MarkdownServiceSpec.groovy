package com.captivatelabs.i18n.md

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
}
