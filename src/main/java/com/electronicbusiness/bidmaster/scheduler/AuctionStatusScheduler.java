package com.electronicbusiness.bidmaster.scheduler;

import com.electronicbusiness.bidmaster.service.AuctionService;
import javax.swing.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuctionStatusScheduler {
  private final AuctionService auctionService;

  @Scheduled(fixedDelay = 1000)
  public void startAuctionsTask() {
    auctionService.startAuctions();
  }

  @Scheduled(fixedDelay = 1000)
  public void closeAuctionsTask() {
    auctionService.closeAuctions();
  }
}
