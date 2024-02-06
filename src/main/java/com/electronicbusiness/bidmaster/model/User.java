package com.electronicbusiness.bidmaster.model;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.electronicbusiness.bidmaster.model.enumeration.Role;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
@Getter
@Builder
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String username;
  private LocalDate dateOfEstablishment;
  private String password;
  private String companyId;

  @Enumerated(STRING)
  @Column(columnDefinition = "varchar")
  private Role role;

  @CreationTimestamp private LocalDateTime createdAt;
  @UpdateTimestamp private LocalDateTime updatedAt;

  public User() {}
}
