package com.captivatelabs.i18n.md

import groovy.transform.CompileStatic
import org.springframework.web.servlet.support.RequestContextUtils

@CompileStatic
class I18nTagLib {
    I18nFileService i18nFileService
    MarkdownService markdownService

    static defaultEncodeAs = [taglib: 'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def render = { Map attrs, body ->
        final String key = attrs.remove('key')
        if (!key) {
            throw new IllegalArgumentException("'key' attribute is required.")
        }
        final String markdown = i18nFileService.getMarkdown(key, RequestContextUtils.getLocale(request))
        markdownService.render(markdown, out)
    }
}
