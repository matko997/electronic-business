package com.electronicbusiness.bidmaster.api.service;

import com.electronicbusiness.bidmaster.api.response.*;
import com.electronicbusiness.bidmaster.model.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuctionPresentationService {

  public AuctionResponse createAuctionResponse(Auction auction) {
    BusinessAsset auctionAsset = auction.getAsset();
    AuctionConfig auctionConfig = auction.getConfig();
    User owner = auction.getOwner();

    AssetResponse assetResponse = assetResponse(auction, auctionAsset);

    AuctionConfigResponse auctionConfigResponse = assetConfigResponse(auctionConfig);

    UserResponse userResponse = new UserResponse(owner.getUsername());

    return new AuctionResponse(
        auction.getId(),
        auction.getTitle(),
        auction.highestBidAmount(),
        bidHistoryResponse(auction),
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
        auctionConfig.getStartTime(),
        auctionConfig.getEndTime());
  }

  private AssetResponse assetResponse(Auction auction, BusinessAsset auctionAsset) {
    return new AssetResponse(
        auction.getId(),
        auctionAsset.getTitle(),
        auctionAsset.getShortDescription(),
        auctionAsset.getDetailedDescription(),
        auctionAsset.getImages().stream().map(BusinessAssetImage::getImage).toList());
  }

  private List<BidResponse> bidHistoryResponse(Auction auction) {
    List<BidResponse> bidHistory = new ArrayList<>();

    List<Bid> bids = auction.getBids();

    double previousBidAmount = auction.getConfig().getStartingPrice();

    for (Bid bid : bids) {
      double bidDifference = Math.abs(bid.getAmount() - previousBidAmount);
      BidResponse bidResponse =
          new BidResponse(bid.getBidder().getUsername(), bid.getAmount(), bidDifference);
      bidHistory.add(bidResponse);
      previousBidAmount = bid.getAmount();
    }

    return bidHistory;
  }
}
