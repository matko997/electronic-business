package com.electronicbusiness.bidmaster.repository;

import com.electronicbusiness.bidmaster.model.Auction;
import com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

  @EntityGraph(attributePaths = {"owner", "bids", "bids.bidder"})
  List<Auction> findAllByStatusAndConfigStartTimeBefore(
      @Param("status") AuctionStatus status, @Param("beforeTime") LocalDateTime beforeTime);

  @EntityGraph(attributePaths = {"owner", "bids", "bids.bidder"})
  List<Auction> findAllByStatusAndConfigEndTimeBefore(
      @Param("status") AuctionStatus status, @Param("beforeTime") LocalDateTime beforeTime);
}
