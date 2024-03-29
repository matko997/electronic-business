package com.electronicbusiness.bidmaster.model;

import static com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus.IN_PROGRESS;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "auction")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auction {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String title;

  @OneToOne(cascade = ALL)
  @JoinColumn(name = "asset_id", referencedColumnName = "id")
  private BusinessAsset asset;

  @Enumerated(STRING)
  @Column(columnDefinition = "varchar")
  private AuctionStatus status;

  @OneToMany(mappedBy = "auction")
  private List<Bid> bids = new ArrayList<>();

  @OneToOne(cascade = ALL)
  @JoinColumn(name = "auction_config_id", referencedColumnName = "id")
  private AuctionConfig config;

  @ManyToOne(fetch = LAZY)
  private User owner;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  public double highestBidAmount() {
    return bids.stream()
        .max(Comparator.comparingDouble(Bid::getAmount))
        .map(Bid::getAmount)
        .orElse(config.getStartingPrice());
  }

  public boolean isAuctionInProgress() {
    return status == IN_PROGRESS;
  }

  public List<User> bidders() {
    return bids.stream().map(Bid::getBidder).toList();
  }

  public Optional<User> highestBidder() {
    return bids.stream().max(Comparator.comparingDouble(Bid::getAmount)).map(Bid::getBidder);
  }
}
