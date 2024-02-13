package com.electronicbusiness.bidmaster.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auction_config")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionConfig {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(columnDefinition = "decimal")
  private double startingPrice;

  @Column(columnDefinition = "decimal")
  private double minimalBiddingStep;

  private LocalDateTime startTime;

  private LocalDateTime endTime;
}
