package com.electronicbusiness.bidmaster.api.request;

public record AuctionRequest(String title, AssetRequest asset, AuctionConfigRequest config) {}
