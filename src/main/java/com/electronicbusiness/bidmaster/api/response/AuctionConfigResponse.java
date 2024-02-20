package com.electronicbusiness.bidmaster.api.response;

import lombok.Builder;

@Builder
public record AuctionConfigResponse(
    long id,
    double startingPrice,
    double minimalBiddingStep,
    String startTime,
    String endTime) {}
