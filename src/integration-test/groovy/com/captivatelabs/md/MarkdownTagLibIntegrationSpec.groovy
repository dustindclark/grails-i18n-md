package com.captivatelabs.md

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class MarkdownTagLibIntegrationSpec extends GebSpec {


    def setup() {
    }

    def cleanup() {
    }

    void "test i18n rendering"() {
        when:
        go "/test/render?key=test-content&lang=${lang}"

        then:
        $(".markdown").text() == content

        where:
        lang    || content
        'es'    || 'hola'
        'it'    || 'ciao'
        null    || 'hello'
        'en_US' || 'hello'
        'en'    || 'hello'
    }

    void "test markdown text rendering"() {
        when:
        go "/test/render?text=%2A%2Atest%2A%2A"

        then:
        $(".markdown p strong").text() == "test"
    }

    void "test message code rendering"() {
        when:
        go "/test/message?code=message.with.md"

        then:
        $(".markdown p strong").text() == "Some text"
    }

}
