package com.electronicbusiness.bidmaster.exception;

import java.util.List;

public record ErrorResponse(List<String> errors) {}
