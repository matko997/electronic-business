package com.electronicbusiness.bidmaster.api.response;

import java.util.List;

public record AssetResponse(
    long id, String title, String shortDescription, String detailedDescription, List<byte[]> images) {}
