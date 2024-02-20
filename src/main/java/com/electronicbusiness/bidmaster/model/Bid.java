package com.electronicbusiness.bidmaster.model;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "bid")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bid {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  private Auction auction;

  @ManyToOne(fetch = LAZY)
  private User bidder;

  @Column(columnDefinition = "decimal")
  private double amount;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  public Bid(Auction auction, User bidder, double amount) {
    this.auction = auction;
    this.bidder = bidder;
    this.amount = amount;
  }
}
