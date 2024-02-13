CREATE TABLE auction
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    title             VARCHAR(255)          NOT NULL,
    asset_id          BIGINT                NOT NULL,
    status            VARCHAR(255)          NOT NULL,
    auction_config_id BIGINT                NOT NULL,
    owner_id          BIGINT                NOT NULL,
    created_at        DATETIME              NOT NULL,
    updated_at        DATETIME              NOT NULL,
    CONSTRAINT pk_auction PRIMARY KEY (id),
    CONSTRAINT fk_auction_asset_id FOREIGN KEY (asset_id) REFERENCES business_asset (id),
    CONSTRAINT fk_auction_auction_config_id FOREIGN KEY (auction_config_id) REFERENCES auction_config (id),
    CONSTRAINT fk_auction_owner_id FOREIGN KEY (owner_id) REFERENCES user (id)
);

