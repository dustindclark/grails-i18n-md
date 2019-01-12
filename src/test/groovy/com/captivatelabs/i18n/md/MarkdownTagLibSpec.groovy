package com.captivatelabs.i18n.md

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class MarkdownTagLibSpec extends Specification implements TagLibUnitTest<MarkdownTagLib> {

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

    void "test render by key"() {
        when:
        String content = tagLib.render(key: "test")

        then:
        content == "converted markdown"
    }

    void "test render text"() {
        when:
        String content = tagLib.render(text: "some text")

        then:
        content == "converted some text"
    }

    void "test missing key and text"() {
        when:
        String content = tagLib.render()

        then:
        !content
        thrown IllegalArgumentException
    }

    void "test both key and text"() {
        when:
        String content = tagLib.render(key: 'test', text: 'test')

        then:
        !content
        thrown IllegalArgumentException
    }
}
