package com.electronicbusiness.bidmaster.api.service;

import com.electronicbusiness.bidmaster.api.response.*;
import com.electronicbusiness.bidmaster.model.*;
import com.electronicbusiness.bidmaster.thirdparty.pusher.request.AuctionFinishedEvent;
import com.electronicbusiness.bidmaster.thirdparty.pusher.request.AuctionStartedEvent;
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

  public List<AuctionFinishedEvent> buildAuctionFinishedEvents(List<Auction> auctions) {
    return auctions.stream().map(this::buildAuctionFinishedEvent).toList();
  }

  private AuctionFinishedEvent buildAuctionFinishedEvent(Auction auction) {
    return new AuctionFinishedEvent(
        auction.bidders().stream().map(User::getUsername).toList(),
        auction.getOwner().getUsername(),
        auction.highestBidder().map(User::getUsername).orElse(null),
        auction.getTitle());
  }

  public List<AuctionStartedEvent> buildAuctionStartedEvents(List<Auction> auctions) {
    return auctions.stream().map(this::buildAuctionStartedEvent).toList();
  }

  private AuctionStartedEvent buildAuctionStartedEvent(Auction auction) {
    return new AuctionStartedEvent(auction.getOwner().getUsername(), auction.getTitle());
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
