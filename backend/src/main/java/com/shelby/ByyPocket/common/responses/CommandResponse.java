package com.shelby.ByyPocket.common.responses;

import lombok.Builder;

/**
 * Data Transfer Object used for returning id value of the saved entity
 */
@Builder
public record CommandResponse(String id) {
}
