package com.captivatelabs.i18n.md

import groovy.transform.CompileStatic
import org.springframework.web.servlet.support.RequestContextUtils

@CompileStatic
class MarkdownTagLib {
    static namespace = 'md'

    I18nFileService i18nFileService
    MarkdownService markdownService

    static defaultEncodeAs = [taglib: 'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def render = { Map attrs, body ->
        final String key = attrs.remove('key')
        final String text = attrs.remove('text')
        if (!key && !text) {
            throw new IllegalArgumentException("'key' or 'text' attribute is required.")
        } else if (key && text) {
            throw new IllegalArgumentException("Set either 'key' or 'text' attributes.  Not both.")
        }
        final String markdown = text ?: i18nFileService.getMarkdown(key, RequestContextUtils.getLocale(request))
        markdownService.render(markdown, out)
    }
}
