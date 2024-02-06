CREATE TABLE user
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    username              VARCHAR(255) UNIQUE   NOT NULL,
    date_of_establishment DATE                  NOT NULL,
    password              VARCHAR(255)          NOT NULL,
    company_id            VARCHAR(255) UNIQUE   NOT NULL,
    role                  VARCHAR(255)          NOT NULL,
    created_at            DATETIME              NOT NULL,
    updated_at            DATETIME              NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);