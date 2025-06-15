package com.karot.food.backend.DTO.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SauceRequest {
    private Long sauceId;
    private Integer quantity;

}
