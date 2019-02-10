package com.captivatelabs.md

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.springframework.web.servlet.support.RequestContextUtils

@CompileStatic
class MarkdownTagLib {
    private static final String OPEN_TAG = '<div class="markdown">'
    private static final String CLOSE_TAG = '</div>'
    private static final String ENCODE_AS = 'raw'

    MarkdownService markdownService

    static defaultEncodeAs = [taglib: ENCODE_AS]
    static namespace = 'md'

    def render = { Map attrs ->
        final String key = attrs.remove('key')
        final String text = attrs.remove('text')
        if (!key && !text) {
            throw new IllegalArgumentException("'key' or 'text' attribute is required.")
        } else if (key && text) {
            throw new IllegalArgumentException("Set either 'key' or 'text' attributes.  Not both.")
        }
        out << OPEN_TAG
        if (text) {
            markdownService.render(text, out)
        } else {
            markdownService.render(key, RequestContextUtils.getLocale(request), out)
        }
        out << CLOSE_TAG
    }

    def message = { Map attrs ->
        //TODO: remove this nonsense: https://github.com/michelf/php-markdown/issues/230
        markdownService.render(getMessageValue(attrs), out, true)
    }

    @CompileDynamic
    private String getMessageValue(Map attrs) {
        attrs.encodeAs = ENCODE_AS
        return g.message(attrs)
    }
}
