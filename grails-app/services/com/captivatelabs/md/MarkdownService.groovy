package com.captivatelabs.md

import grails.io.IOUtils
import groovy.transform.CompileStatic
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader

@CompileStatic
class MarkdownService implements ResourceLoaderAware {
    private static final Parser parser = Parser.builder().build()
    private static final HtmlRenderer renderer = HtmlRenderer.builder().build()

    private static final String MARKDOWN_DIR = "markdown"
    private static final String CLASSPATH_PREFIX = "classpath:${MARKDOWN_DIR}/"
    private static final String FILE_PREFIX = "file:grails-app/i18n/${MARKDOWN_DIR}/"
    private static final String DEFAULT_FILE = "default"
    private static final String EXTENSION = ".md"

    ResourceLoader resourceLoader

    private static String getDefaultPath(String key, boolean file = false) {
        return getPath(key, DEFAULT_FILE, file)
    }

    private static String getPath(String key, String locale, boolean file = false) {
        return "${file ? FILE_PREFIX : CLASSPATH_PREFIX}${key}/${locale}${EXTENSION}"
    }

    String getMarkdown(String key, Locale locale) {
        Reader reader = getMarkdownReader(key, locale)
        if (reader == null) {
            return null
        }
        return IOUtils.toString(reader)
    }

    Reader getMarkdownReader(String key, Locale locale) {
        if (!key) {
            throw new IllegalArgumentException("Key can't be null or empty.")
        }
        final String languageKey = locale.toLanguageTag().replaceFirst('-', '_')
        final List<Closure<String>> resolutionPaths = [
                { getPath(key, languageKey) },
                { getPath(key, locale.getLanguage()) },
                { getDefaultPath(key) },
                { getPath(key, languageKey, true) },
                { getPath(key, locale.getLanguage(), true) },
                { getDefaultPath(key, true) }
        ]
        Resource resource
        String path
        for (Closure<String> closure : resolutionPaths) {
            path = closure.call()
            resource = resourceLoader.getResource(path)
            if (resource.exists()) {
                log.debug("Found resource at path ${path}.")
                break
            } else if (log.debugEnabled) {
                log.debug("Localized markdown file not found at ${path}.")
            }
        }
        if (!resource.exists()) {
            return null
        }
        return new InputStreamReader(resource.inputStream)
    }

    void render(String key, Locale locale, Appendable out) {
        Reader reader = getMarkdownReader(key, locale)
        render(reader, out)
    }

    void render(String markdownContent, Appendable out) {
        if (!markdownContent) {
            return
        }
        Node document = parser.parse(markdownContent)
        renderer.render(document, out)
    }

    void render(Reader markdownContent, Appendable out) {
        if (!markdownContent) {
            return
        }
        Node document = parser.parseReader(markdownContent)
        renderer.render(document, out)
    }
}
