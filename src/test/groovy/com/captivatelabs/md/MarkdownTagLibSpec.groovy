package com.captivatelabs.md

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class MarkdownTagLibSpec extends Specification implements TagLibUnitTest<MarkdownTagLib> {
    private static final TAG_PREFIX = '<div class="markdown">'
    private static final TAG_SUFFIX = '</div>'
    def setup() {
        tagLib.markdownService = Stub(MarkdownService) {
            render(_, _, _) >> { String content, Locale locale, Appendable out -> out.append("converted " + content) }
            render(_, _) >> { String markdown, Appendable out -> out.append("markdown text") }
        }
    }

    def cleanup() {
    }

    void "test render by key"() {
        when:
        String content = tagLib.render(key: "test")

        then:
        content == "${TAG_PREFIX}converted test${TAG_SUFFIX}"
    }

    void "test render text"() {
        when:
        String content = tagLib.render(text: "some text")

        then:
        content == "${TAG_PREFIX}markdown text${TAG_SUFFIX}"
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
