package com.demo.data;

import lombok.Data;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

public interface DemoData {

    /**
     * Front end
     */
    @Data
    class QueryParam {

        // query content
        private String content;
    }


    /**
     * Return to front end
     */
    @Data
    class Vo {
        private String id;
        // content
        private String content;
    }

    /**
     * From front end
     */
    @Data
    class SaveParam {
        private String id;
        // content
        private String content;
    }
}
