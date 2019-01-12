package com.captivatelabs.i18n.md

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class I18nFileServiceSpec extends Specification implements ServiceUnitTest<I18nFileService> {

    def setup() {
    }

    def cleanup() {
    }

    void "test file resolution"() {
        when:
        Locale locale = new Locale(language, country)

        then:
        service.getMarkdown('test-content', locale) == content

        where:
        language || country || content
        "es"     || 'ES'    || "es_ES"
        "es"     || 'MX'    || "es"
        "en"     || 'US'    || "default"
    }
}
