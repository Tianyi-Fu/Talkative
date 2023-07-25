package com.demo.data;

import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

public interface Data {

    /**
     * Front end
     */
    @lombok.Data
    class QueryParam {

        // query content
        private String content;
    }


    /**
     * Return to front end
     */
    @lombok.Data
    class Vo {
        //it can contain different information as demo class
        private String id;
        // content
        private String content;
    }

    /**
     * From front end
     */
    @lombok.Data
    class SaveParam {
        private String id;
        // content
        private String content;
    }

    @lombok.Data
    class ChatParam {
        private String content;
    }


    @lombok.Data
    class QuestionVo {
        private String agentName;
        private List<String> questions;
    }

    @lombok.Data
    class FeedbackSaveParam {

        private String question;

        private String answer;
    }

    @lombok.Data
    class FeedbackSaveParamAndChatId{
        private String chatRecordId;

        private List<FeedbackSaveParam> list;
    }
}
