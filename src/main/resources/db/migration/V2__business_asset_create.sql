CREATE TABLE business_asset
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    title                VARCHAR(255)          NOT NULL,
    short_description    VARCHAR(255)          NOT NULL,
    detailed_description VARCHAR(1000)         NOT NULL,
    created_at           datetime              NOT NULL,
    updated_at           datetime              NOT NULL,
    CONSTRAINT pk_business_asset PRIMARY KEY (id)
);