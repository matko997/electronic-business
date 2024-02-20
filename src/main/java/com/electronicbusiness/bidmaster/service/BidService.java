package com.electronicbusiness.bidmaster.service;

import com.electronicbusiness.bidmaster.api.request.BidRequest;
import com.electronicbusiness.bidmaster.exception.BidValidationException;
import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.AuctionConfig;
import com.electronicbusiness.bidmaster.model.Bid;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.repository.BidRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidService {
  private final BidRepository bidRepository;

  public Bid create(Auction auction, BidRequest bidRequest, User bidder) {
    AuctionConfig auctionConfig = auction.getConfig();

    if (!auction.isAuctionInProgress()) {
      throw new BidValidationException("You can not bid for an auction that is not in progress");
    }

    if (bidRequest.amount() <= auctionConfig.getStartingPrice()) {
      throw new BidValidationException("Bid amount must be greater than starting price");
    }

    Optional<Bid> highestBid = bidRepository.findFirstByOrderByAmountDesc();

    if (highestBid.isPresent() && bidRequest.amount() <= highestBid.get().getAmount()) {
      throw new BidValidationException("Bid amount must be greater than highest current bid");
    }

    if (highestBid.isPresent()
        && (Math.abs(highestBid.get().getAmount() - bidRequest.amount()))
            < auctionConfig.getMinimalBiddingStep()) {
      throw new BidValidationException(
          "Bid amount must be equal or greater than defined minimal bidding step");
    }

    Bid createdBid = new Bid(auction, bidder, bidRequest.amount());
    return bidRepository.save(createdBid);
  }
}
