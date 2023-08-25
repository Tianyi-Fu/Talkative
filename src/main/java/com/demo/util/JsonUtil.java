package com.demo.util;

import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;

/**
 * @author Tianyi Fu
 * @since 2023/7/20
 */


public class JsonUtil {

    private static String removeQuotesAndUnescape(String uncleanJson) {
        String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");

        return StringEscapeUtils.unescapeJava(noQuotes);
    }

    /**
     * json convert to class object
     *
     * @param json json
     * @param t    the class want to be converted
     * @param <T>  class type
     * @return converted class
     */
    public static <T> T toClass(String json, Class<T> t) {
        Gson gson = new Gson();
        System.out.println("json："+json);
        return gson.fromJson(removeQuotesAndUnescape(json), t);
    }
}
