package com.electronicbusiness.bidmaster.api.service;

import com.electronicbusiness.bidmaster.api.response.*;
import com.electronicbusiness.bidmaster.model.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionPresentationService {
  private final BidPresentationService bidPresentationService;

  public AuctionResponse createAuctionResponse(Auction auction) {
    BusinessAsset auctionAsset = auction.getAsset();
    AuctionConfig auctionConfig = auction.getConfig();
    User owner = auction.getOwner();

    AssetResponse assetResponse = assetResponse(auction, auctionAsset);

    AuctionConfigResponse auctionConfigResponse = assetConfigResponse(auctionConfig);

    UserResponse userResponse = new UserResponse(owner.getUsername());

    List<BidResponse> bidResponseList = bidPresentationService.bidList(auction);

    return new AuctionResponse(
        auction.getId(),
        auction.getTitle(),
        auction.highestBidAmount(),
        bidResponseList,
        assetResponse,
        auction.getStatus(),
        auctionConfigResponse,
        userResponse);
  }

  private AuctionConfigResponse assetConfigResponse(AuctionConfig auctionConfig) {
    return new AuctionConfigResponse(
        auctionConfig.getId(),
        auctionConfig.getStartingPrice(),
        auctionConfig.getMinimalBiddingStep(),
        auctionConfig.getStartTime().toString(),
        auctionConfig.getEndTime().toString());
  }

  private AssetResponse assetResponse(Auction auction, BusinessAsset auctionAsset) {
    return new AssetResponse(
        auction.getId(),
        auctionAsset.getTitle(),
        auctionAsset.getShortDescription(),
        auctionAsset.getDetailedDescription(),
        auctionAsset.getImages().stream().map(BusinessAssetImage::getImage).toList());
  }
}
