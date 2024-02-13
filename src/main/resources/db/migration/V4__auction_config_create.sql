CREATE TABLE auction_config
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    starting_price       DECIMAL(10, 2)        NOT NULL,
    minimal_bidding_step DECIMAL(10, 2)        NOT NULL,
    start_time           DATETIME              NOT NULL,
    end_time             DATETIME              NOT NULL,
    CONSTRAINT pk_auction_config PRIMARY KEY (id)
);