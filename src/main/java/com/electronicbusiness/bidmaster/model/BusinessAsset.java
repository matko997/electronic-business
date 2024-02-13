package com.electronicbusiness.bidmaster.model;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "business_asset")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAsset {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String title;

  private String shortDescription;

  private String detailedDescription;

  @OneToMany(mappedBy = "businessAsset", cascade = ALL)
  private List<BusinessAssetImage> images = new ArrayList<>();

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;
}
