package com.electronicbusiness.bidmaster.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.electronicbusiness.bidmaster.api.request.BidRequest;
import com.electronicbusiness.bidmaster.exception.EntityNotFoundException;
import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.Bid;
import com.electronicbusiness.bidmaster.model.User;
import com.electronicbusiness.bidmaster.security.JwtService;
import com.electronicbusiness.bidmaster.service.AuctionService;
import com.electronicbusiness.bidmaster.service.BidService;
import com.electronicbusiness.bidmaster.service.UserService;
import com.electronicbusiness.bidmaster.thirdparty.pusher.service.PusherService;
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
  private final PusherService pusherService;

  @PostMapping("/{auctionId}")
  @ResponseStatus(CREATED)
  public void createBid(
      @RequestHeader("Authorization") String authHeader,
      @RequestBody BidRequest bidRequest,
      @PathVariable long auctionId) {
    Auction auction =
        auctionService
            .findById(auctionId)
            .orElseThrow(
                () -> new EntityNotFoundException(Auction.class, String.valueOf(auctionId)));
    String username = jwtService.extractUsername(authHeader.substring(7));
    User bidder =
        userService
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(User.class, username));
    Bid savedBid = bidService.create(auction, bidRequest, bidder);
    pusherService.sendBidCreatedNotification(savedBid);
  }
}
