CREATE TABLE bid
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    auction_id BIGINT                NOT NULL,
    bidder_id  BIGINT                NULL,
    amount     DECIMAL(10, 2)        NOT NULL,
    created_at DATETIME              NOT NULL,
    updated_at DATETIME              NOT NULL,
    CONSTRAINT pk_bid PRIMARY KEY (id),
    CONSTRAINT fk_bid_auction_id FOREIGN KEY (auction_id) REFERENCES auction (id),
    CONSTRAINT fk_bid_bidder_id FOREIGN KEY (bidder_id) REFERENCES user (id)
);

