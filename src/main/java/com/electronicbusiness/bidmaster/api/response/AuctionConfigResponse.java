package com.electronicbusiness.bidmaster.api.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AuctionConfigResponse(
    long id,
    double startingPrice,
    double minimalBiddingStep,
    LocalDateTime startTime,
    LocalDateTime endTime) {}
