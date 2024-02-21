package com.electronicbusiness.bidmaster.api.service;

import com.electronicbusiness.bidmaster.api.response.BidResponse;
import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.Bid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BidPresentationService {

  public List<BidResponse> bidList(Auction auction) {
    List<BidResponse> bidHistory = new ArrayList<>();

    List<Bid> bids = auction.getBids();

    double previousBidAmount = auction.getConfig().getStartingPrice();

    for (Bid bid : bids) {
      double bidDifference = Math.abs(bid.getAmount() - previousBidAmount);
      BidResponse bidResponse =
          new BidResponse(
              bid.getId(),
              bid.getAuction().getId(),
              bid.getBidder().getUsername(),
              bid.getAmount(),
              bidDifference);
      bidHistory.add(bidResponse);
      previousBidAmount = bid.getAmount();
    }

    return bidHistory;
  }

  public BidResponse bidSingle(Bid bid) {
    return new BidResponse(
        bid.getId(),
        bid.getAuction().getId(),
        bid.getBidder().getUsername(),
        bid.getAmount(),
        (bidDifference(bid.getAuction())));
  }

  private double bidDifference(Auction auction) {
    if (auction.getBids().isEmpty()) {
      return 0;
    }
    if (auction.getBids().size() == 1) {
      return Math.abs(
          auction.getBids().get(0).getAmount() - auction.getConfig().getStartingPrice());
    }
    return Math.abs(
        auction.getBids().get(auction.getBids().size() - 1).getAmount()
            - auction.getBids().get(auction.getBids().size() - 2).getAmount());
  }
}
