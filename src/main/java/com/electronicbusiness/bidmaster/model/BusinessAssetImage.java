package com.electronicbusiness.bidmaster.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "business_asset_image")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAssetImage {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne private BusinessAsset businessAsset;

  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] image;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  public BusinessAssetImage(BusinessAsset businessAsset, byte[] image) {
    this.businessAsset = businessAsset;
    this.image = image;
  }
}
