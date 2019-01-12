package com.captivatelabs.i18n.md

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class I18nTagLibSpec extends Specification implements TagLibUnitTest<I18nTagLib> {

    def setup() {
        tagLib.i18nFileService = Stub(I18nFileService) {
            getMarkdown(_, _) >> { String key, Locale locale -> return "markdown" }
        }
        tagLib.markdownService = Stub(MarkdownService) {
            render(_, _) >> { String content, Appendable out -> out.append("converted " + content) }
        }
    }

    def cleanup() {
    }

    void "test render"() {
        when:
        String content = tagLib.render(key: "test")

        then:
        content == "converted markdown"
    }

    void "test missing key"() {
        when:
        String content = tagLib.render()

        then:
        !content
        thrown IllegalArgumentException
    }
}
