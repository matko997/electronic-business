package com.electronicbusiness.bidmaster.repository;

import com.electronicbusiness.bidmaster.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {}
