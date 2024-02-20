package com.electronicbusiness.bidmaster.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.electronicbusiness.bidmaster.api.request.BidRequest;
import com.electronicbusiness.bidmaster.api.response.BidResponse;
import com.electronicbusiness.bidmaster.api.service.BidPresentationService;
import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.Bid;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.security.JwtService;
import com.electronicbusiness.bidmaster.service.AuctionService;
import com.electronicbusiness.bidmaster.service.BidService;
import com.electronicbusiness.bidmaster.service.UserService;
import com.pusher.rest.Pusher;
import com.pusher.rest.data.Event;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
public class BidController {

  private final AuctionService auctionService;
  private final BidService bidService;
  private final JwtService jwtService;
  private final UserService userService;
  private final BidPresentationService bidPresentationService;
  private final Pusher pusher;

  @PostMapping("/{auctionId}")
  @ResponseStatus(CREATED)
  public void createBid(
      @RequestHeader("Authorization") String authHeader,
      @RequestBody BidRequest bidRequest,
      @PathVariable long auctionId) {
    Auction auction = auctionService.findById(auctionId).orElseThrow(EntityNotFoundException::new);
    String username = jwtService.extractUsername(authHeader.substring(7));
    User bidder = userService.findByUsername(username).orElseThrow();
    Bid savedBid = bidService.create(auction, bidRequest, bidder);
    List<BidResponse> bidResponseList = bidPresentationService.bidList(savedBid.getAuction());
    Event pusherEvent = new Event("bids", "bidCreated", bidResponseList);
    pusher.trigger(List.of(pusherEvent));
  }
}
