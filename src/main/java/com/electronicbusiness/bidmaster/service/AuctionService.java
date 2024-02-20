package com.electronicbusiness.bidmaster.service;

import static com.electronicbusiness.bidmaster.model.enumeration.AuctionStatus.*;

import com.electronicbusiness.bidmaster.api.request.AssetRequest;
import com.electronicbusiness.bidmaster.api.request.AuctionConfigRequest;
import com.electronicbusiness.bidmaster.api.request.AuctionRequest;
import com.electronicbusiness.bidmaster.model.*;
import com.electronicbusiness.bidmaster.repository.AuctionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionService {

  private final AuctionRepository auctionRepository;

  public Auction create(AuctionRequest auctionRequest, User user) {
    BusinessAsset asset = createAsset(auctionRequest);
    AuctionConfig auctionConfig = createAuctionConfig(auctionRequest);
    Auction auction = createAuction(auctionRequest, user, auctionConfig, asset);
    return auctionRepository.save(auction);
  }

  public Optional<Auction> findById(long auctionId) {
    return auctionRepository.findById(auctionId);
  }

  public List<Auction> findAll() {
    return auctionRepository.findAll();
  }

  public void startAuctions() {
    List<Auction> readyToStartAuctions = findReadyToStartAuctions();
    readyToStartAuctions.forEach(auction -> auction.setStatus(IN_PROGRESS));
    auctionRepository.saveAll(readyToStartAuctions);
  }

  public void closeAuctions() {
    List<Auction> readyToCloseAuctions = findReadyToCloseAuctions();
    readyToCloseAuctions.forEach(auction -> auction.setStatus(CLOSED));
    auctionRepository.saveAll(readyToCloseAuctions);
  }

  private List<Auction> findReadyToStartAuctions() {
    return auctionRepository.findAllByStatusAndConfigStartTimeBefore(CREATED, LocalDateTime.now());
  }

  private List<Auction> findReadyToCloseAuctions() {
    return auctionRepository.findAllByStatusAndConfigEndTimeBefore(IN_PROGRESS, LocalDateTime.now());
  }

  private Auction createAuction(
      AuctionRequest auctionRequest, User user, AuctionConfig auctionConfig, BusinessAsset asset) {
    return Auction.builder()
        .owner(user)
        .config(auctionConfig)
        .status(CREATED)
        .bids(List.of())
        .title(auctionRequest.title())
        .asset(asset)
        .build();
  }

  private AuctionConfig createAuctionConfig(AuctionRequest auctionRequest) {
    AuctionConfigRequest auctionConfigRequest = auctionRequest.config();
    return AuctionConfig.builder()
        .startTime(auctionConfigRequest.startTime())
        .endTime(auctionConfigRequest.endTime())
        .startingPrice(
            auctionConfigRequest.startingPrice() == null ? 0 : auctionConfigRequest.startingPrice())
        .minimalBiddingStep(
            auctionConfigRequest.minimalBiddingStep() == null
                ? 0
                : auctionConfigRequest.minimalBiddingStep())
        .build();
  }

  private BusinessAsset createAsset(AuctionRequest auctionRequest) {
    AssetRequest assetRequest = auctionRequest.asset();
    BusinessAsset businessAsset =
        BusinessAsset.builder()
            .title(assetRequest.getTitle())
            .shortDescription(assetRequest.getShortDescription())
            .detailedDescription(assetRequest.getDetailedDescription())
            .build();
    List<BusinessAssetImage> businessAssetImages =
        assetRequest.getImages().stream()
            .map(imageBytes -> new BusinessAssetImage(businessAsset, imageBytes))
            .toList();
    businessAsset.setImages(businessAssetImages);
    return businessAsset;
  }
}
