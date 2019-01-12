package com.captivatelabs.i18n.md

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.nio.charset.StandardCharsets

@CompileStatic
@Slf4j
class I18nFileService {
    private static final String PATH_PREFIX = "grails-app/i18n/md/"
    private static final String DEFAULT_FILE = "default"
    private static final String EXTENSION = ".md"

    String getMarkdown(String key, Locale locale) {
        if (!key) {
            throw new IllegalArgumentException("Key can't be null or empty.")
        }
        final List<String> resolutionPaths = [
                getPath(key, locale.toLanguageTag().replaceFirst('-', '_')),
                getPath(key, locale.getLanguage()),
                getDefaultPath(key)
        ]
        File file
        for (String path : resolutionPaths) {
            file = new File(path)
            if (file.exists()) {
                break
            } else if (log.debugEnabled) {
                log.debug("Localized markdown file not found at ${path}.")
            }
        }
        if (!file.exists()) {
            return ""
        }
        return file.getText(StandardCharsets.UTF_8.toString())
    }


    static String getDefaultPath(String key) {
        return getPath(key, DEFAULT_FILE)
    }

    static String getPath(String key, String locale) {
        return "${PATH_PREFIX}${key}/${locale}${EXTENSION}"
    }
}
