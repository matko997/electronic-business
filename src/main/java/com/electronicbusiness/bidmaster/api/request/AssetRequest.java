package com.electronicbusiness.bidmaster.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class AssetRequest {
  private String title;
  private String shortDescription;
  private String detailedDescription;
  private List<byte[]> images;
}
