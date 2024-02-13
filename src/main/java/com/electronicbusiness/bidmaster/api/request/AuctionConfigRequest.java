package com.electronicbusiness.bidmaster.api.request;

import java.time.LocalDateTime;

public record AuctionConfigRequest(
    Double startingPrice,
    Double minimalBiddingStep,
    LocalDateTime startTime,
    LocalDateTime endTime) {}
