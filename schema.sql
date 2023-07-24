CREATE
    DATABASE IF NOT EXISTS talkative;
USE talkative;

DROP TABLE IF EXISTS feedback_record;
DROP TABLE IF EXISTS chat_record;
DROP TABLE IF EXISTS users;

CREATE TABLE chat_record
(
    chat_record_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    transcript         TEXT,
    agent_id           INTEGER,
    agent_name         VARCHAR(255)
#     feedback_record_id INTEGER
);

CREATE TABLE feedback_record
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    question_id    INTEGER,
    question       VARCHAR(255),
    answer         VARCHAR(255),
    created_at     VARCHAR(255),
    chat_record_id INTEGER,
    FOREIGN KEY (chat_record_id) REFERENCES chat_record (chat_record_id)
);

CREATE TABLE feed_back_user_info
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    agent_name VARCHAR(255),
    created_at     VARCHAR(255),
    chat_record_id INTEGER
);


CREATE TABLE users
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255),
    password   TEXT,
    created_at VARCHAR(255)
);