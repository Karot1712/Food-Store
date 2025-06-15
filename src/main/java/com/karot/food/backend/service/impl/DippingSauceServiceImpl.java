package com.karot.food.backend.service.impl;

import com.karot.food.backend.DTO.DippingSauceDto;
import com.karot.food.backend.DTO.Response;
import com.karot.food.backend.exception.NotFoundException;
import com.karot.food.backend.mapper.EntityDtoMapper;
import com.karot.food.backend.model.DippingSauce;
import com.karot.food.backend.repositories.DippingSauceRepo;
import com.karot.food.backend.service.interf.DippingSauceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DippingSauceServiceImpl implements DippingSauceService {
    private final DippingSauceRepo dippingSauceRepo;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createDippingSauce(String name, Integer price) {
        DippingSauce dippingSauce = new DippingSauce();

        dippingSauce.setName(name);
        dippingSauce.setPrice(price);

        dippingSauceRepo.save(dippingSauce);
        return Response.builder()
                .status(200)
                .message("Create successfully")
                .build();
    }

    @Override
    public Response deleteDippingSauce(Long sauceId) {
        DippingSauce dippingSauce = dippingSauceRepo.findById(sauceId).orElseThrow(()-> new NotFoundException("Sauce not exist"));
        dippingSauceRepo.delete(dippingSauce);

        return Response.builder()
                .status(200)
                .message("Delete successfully")
                .build();
    }

    @Override
    public Response getAllDippingSauce() {
        List<DippingSauceDto> sauceList = dippingSauceRepo.findAll().stream()
                .map(entityDtoMapper::mapDippingSauceToDto)
                .toList();

        return Response.builder()
                .sauceList(sauceList)
                .build();
    }

    @Override
    public Response getSauceById(Long sauceId) {
        DippingSauce dippingSauce = dippingSauceRepo.findById(sauceId).orElseThrow(()-> new NotFoundException("Sauce not exist"));
        DippingSauceDto dto = entityDtoMapper.mapDippingSauceToDto(dippingSauce);
        return Response.builder()
                .status(200)
                .sauce(dto)
                .build();
    }
}
