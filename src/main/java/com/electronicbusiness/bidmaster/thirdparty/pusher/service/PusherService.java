package com.electronicbusiness.bidmaster.thirdparty.pusher.service;

import com.electronicbusiness.bidmaster.api.response.BidResponse;
import com.electronicbusiness.bidmaster.api.service.AuctionPresentationService;
import com.electronicbusiness.bidmaster.api.service.BidPresentationService;
import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.Bid;
import com.electronicbusiness.bidmaster.thirdparty.pusher.request.AuctionFinishedEvent;
import com.electronicbusiness.bidmaster.thirdparty.pusher.request.AuctionStartedEvent;
import com.pusher.rest.Pusher;
import com.pusher.rest.data.Event;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PusherService {

  private final Pusher pusher;
  private final BidPresentationService bidPresentationService;
  private final AuctionPresentationService auctionPresentationService;

  public void sendAuctionStartedNotification(List<Auction> startedAuctions) {
    List<AuctionStartedEvent> auctionStartedEvents =
        auctionPresentationService.buildAuctionStartedEvents(startedAuctions);
    List<Event> eventList =
        auctionStartedEvents.stream().map(AuctionStartedEvent::toEvent).toList();
    pusher.trigger(eventList);
  }

  public void sendAuctionFinishedNotification(List<Auction> finishedAuctions) {
    List<AuctionFinishedEvent> auctionFinishedEvents =
        auctionPresentationService.buildAuctionFinishedEvents(finishedAuctions);
    List<Event> eventList =
        auctionFinishedEvents.stream().map(AuctionFinishedEvent::toEvent).toList();
    pusher.trigger(eventList);
  }

  public void sendBidCreatedNotification(Bid bid) {
    BidResponse bidResponse = bidPresentationService.bidSingle(bid);
    pusher.trigger("bids", "bidCreated", bidResponse);
  }
}
