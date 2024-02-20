package com.electronicbusiness.bidmaster.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.electronicbusiness.bidmaster.api.request.AuctionRequest;
import com.electronicbusiness.bidmaster.api.response.*;
import com.electronicbusiness.bidmaster.api.service.AuctionPresentationService;
import com.electronicbusiness.bidmaster.exception.EntityNotFoundException;
import com.electronicbusiness.bidmaster.model.*;
import com.electronicbusiness.bidmaster.security.JwtService;
import com.electronicbusiness.bidmaster.service.AuctionService;
import com.electronicbusiness.bidmaster.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

  private final AuctionService auctionService;
  private final JwtService jwtService;
  private final UserService userService;
  private final AuctionPresentationService auctionPresentationService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(CREATED)
  public AuctionResponse create(
      @RequestHeader("Authorization") String authHeader,
      @RequestPart("auction") AuctionRequest auctionRequest,
      @RequestPart("imageFile") List<MultipartFile> assetImages)
      throws IOException {
    String username = jwtService.extractUsername(authHeader.substring(7));
    User user =
        userService
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(User.class, String.valueOf(username)));
    List<byte[]> images = new ArrayList<>();
    for (MultipartFile imageFile : assetImages) {
      images.add(imageFile.getBytes());
    }
    auctionRequest.asset().setImages(images);
    Auction auction = auctionService.create(auctionRequest, user);
    return auctionPresentationService.createAuctionResponse(auction);
  }

  @GetMapping("/{auctionId}")
  public AuctionResponse getOne(@PathVariable long auctionId) {
    Auction auction =
        auctionService
            .findById(auctionId)
            .orElseThrow(
                () -> new EntityNotFoundException(Auction.class, String.valueOf(auctionId)));
    return auctionPresentationService.createAuctionResponse(auction);
  }

  @GetMapping
  public List<AuctionResponse> getAll() {
    List<Auction> auctions = auctionService.findAll();
    return auctions.stream().map(auctionPresentationService::createAuctionResponse).toList();
  }
}
