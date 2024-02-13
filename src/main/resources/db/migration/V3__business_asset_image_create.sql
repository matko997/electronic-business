CREATE TABLE business_asset_image
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    business_asset_id BIGINT                NOT NULL,
    image             LONGBLOB              NOT NULL,
    created_at        DATETIME              NOT NULL,
    updated_at        DATETIME              NOT NULL,
    CONSTRAINT pk_business_asset_image PRIMARY KEY (id),
    CONSTRAINT fk_business_asset_id FOREIGN KEY (business_asset_id) REFERENCES business_asset (id)
);

