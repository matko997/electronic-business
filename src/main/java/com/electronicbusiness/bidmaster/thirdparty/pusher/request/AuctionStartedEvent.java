package com.electronicbusiness.bidmaster.thirdparty.pusher.request;

import com.pusher.rest.data.Event;

public record AuctionStartedEvent(String creator, String item) {
  public Event toEvent() {
    return new Event("auctions", "auctionStarted", this);
  }
}
