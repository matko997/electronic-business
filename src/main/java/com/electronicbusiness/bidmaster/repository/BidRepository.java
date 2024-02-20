package com.electronicbusiness.bidmaster.repository;

import com.electronicbusiness.bidmaster.model.Bid;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
  Optional<Bid> findFirstByOrderByAmountDesc();
}
