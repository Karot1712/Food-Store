package com.karot.food.backend.service.interf;

import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.Response;

public interface DippingSauceService {
    Response createDippingSauce(String name, Integer price);
    Response deleteDippingSauce(Long sauceId);
    Response getAllDippingSauce();
    Response getSauceById(Long sauceId);
}
