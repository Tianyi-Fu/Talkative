CREATE
    DATABASE IF NOT EXISTS talkative;
USE talkative;
DROP TABLE IF EXISTS feedback_record;
DROP TABLE IF EXISTS feedback_analysis;
DROP TABLE IF EXISTS review_analysis;
DROP TABLE IF EXISTS chat_record;
DROP TABLE IF EXISTS feedback_user_info;
DROP TABLE IF EXISTS user;

CREATE TABLE chat_record
(
    feedback_record_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    chat_record_id     INTEGER unique,
    transcript         TEXT,
    agent_id           INTEGER,
    agent_name         VARCHAR(255)
#     feedback_record_id INTEGER
);

CREATE TABLE feedback_record
(
    id                 INTEGER AUTO_INCREMENT PRIMARY KEY,
    question_id        INTEGER,
    question           VARCHAR(255),
    answer             VARCHAR(255),
    created_at         VARCHAR(255),
    feedback_record_id INTEGER,
    FOREIGN KEY (feedback_record_id) REFERENCES chat_record (feedback_record_id)
);

CREATE TABLE feedback_user_info
(
    id             INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    email          VARCHAR(255),
    agent_name     VARCHAR(255),
    created_at     VARCHAR(255),
    chat_record_id INTEGER
);

CREATE TABLE feedback_analysis
(
    id             INTEGER AUTO_INCREMENT PRIMARY KEY,
    question       VARCHAR(600),
    suggestion     VARCHAR(600),
    created_at     VARCHAR(255),
    feedback_record_id INTEGER
);


CREATE TABLE user
(
    uid           int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username      varchar(30) NOT NULL,
    password      varchar(255) NOT NULL,
    user_role     varchar(10) NOT NULL DEFAULT 'USER',
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE review_analysis
(
    id                 INTEGER AUTO_INCREMENT PRIMARY KEY,
    review_text        TEXT,
    sentiment_ntlk     VARCHAR(50),
    sentiment_flair    VARCHAR(50),
    sentiment_bert   VARCHAR(50),
    topic              TEXT,
    chat_record_id     INTEGER,
    FOREIGN KEY (chat_record_id) REFERENCES chat_record (chat_record_id)
);