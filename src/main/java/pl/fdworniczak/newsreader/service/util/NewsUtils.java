package pl.fdworniczak.newsreader.service.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Util class for News
 *
 * Created by filip on 05.01.19
 */
public class NewsUtils {

    /**
     * Gets JsonNode value child's text property by propertyName provided as parameter.
     * Returns "" if property doesn't exist or is not textual
     *
     * @param node
     * @param propertyName
     * @return text property
     */
    public static String getTextProperty(final JsonNode node, final String propertyName) {
        JsonNode valueNode = node.get(propertyName);
        if (valueNode == null || !valueNode.isTextual()) {
            return "";
        }
        return valueNode.asText("");
    }
}
