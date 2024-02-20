package com.electronicbusiness.bidmaster.repository;

import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByStatusAndConfigStartTimeBefore(AuctionStatus status, LocalDateTime beforeTime);
    List<Auction> findAllByStatusAndConfigEndTimeBefore(AuctionStatus status, LocalDateTime beforeTime);
}
