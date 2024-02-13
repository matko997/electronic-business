package com.electronicbusiness.bidmaster.api.response;

import com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus;
import java.util.List;

public record AuctionResponse(
    long id,
    String title,
    double currentHighestBid,
    List<BidResponse> bidHistory,
    AssetResponse asset,
    AuctionStatus status,
    AuctionConfigResponse config,
    UserResponse owner) {}
