CREATE
DATABASE IF NOT EXISTS talkative;
USE talkative;

DROP TABLE IF EXISTS chat_record;
DROP TABLE IF EXISTS feedback_record;
DROP TABLE IF EXISTS users;

CREATE TABLE feedback_record
(
    feedback_record_id INTEGER ,
    question           VARCHAR(255),
    answer             VARCHAR(255),
    created_at         VARCHAR(255)
);

CREATE TABLE chat_record
(
    chat_record_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    transcript         TEXT,
    agent_id           INTEGER,
    agent_name         VARCHAR(255),
    feedback_record_id INTEGER
);


CREATE TABLE users
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255),
    password   TEXT,
    created_at VARCHAR(255)
);