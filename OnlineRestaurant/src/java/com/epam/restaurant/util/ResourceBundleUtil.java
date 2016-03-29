package com.epam.restaurant.util;

import java.util.ResourceBundle;

/**
 * Util for language internationalization.
 */
public class ResourceBundleUtil {

    private static final String UNDERLINE = "_";
    private static final String EN = "en";

    /**
     * Get resource bundle for specific language
     *
     * @param language language of resource bundle
     * @return resource bundle for some language
     */
    public static ResourceBundle getResourceBundle(String language) {
        String path = "i18n.restaurant";

        if (language != null && !language.equals(EN)) {
            path += UNDERLINE + language;
        }

        return ResourceBundle.getBundle(path);
    }
}
