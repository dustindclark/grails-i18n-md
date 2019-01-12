package com.captivatelabs.i18n.md

import groovy.transform.CompileStatic
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.node.Node

@CompileStatic
class MarkdownService {
    static final Parser parser = Parser.builder().build()
    static final HtmlRenderer renderer = HtmlRenderer.builder().build()

    void render(String markdownContent, Appendable out) {
        if (!markdownContent) {
            return
        }
        Node document = parser.parse(markdownContent)
        renderer.render(document, out)
    }
}
