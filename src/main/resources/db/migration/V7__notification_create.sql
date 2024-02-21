CREATE TABLE notification
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    message    VARCHAR(255)          NOT NULL,
    user_id    BIGINT                NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id),
    CONSTRAINT fk_notification_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

