package com.electronicbusiness.bidmaster.thirdparty.pusher.request;

import com.pusher.rest.data.Event;

import java.util.List;

public record AuctionFinishedEvent(
    List<String> bidders, String auctionCreator, String winner, String item) {
  public Event toEvent() {
    return new Event("auctions", "auctionFinished", this);
  }
}
