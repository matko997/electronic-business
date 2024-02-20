package com.electronicbusiness.bidmaster.api.response;

public record BidResponse(
    long id, long auctionId, String bidder, double amount, double bidDifference) {}
