package org.jcv.product.controller;

import org.jcv.common.product.dto.HotelContractDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-service/hotel")
public class HotelProductController implements IBaseProductController<HotelContractDto> {

    @Override
    @PostMapping("/contract")
    public HotelContractDto createContract(HotelContractDto contractDto) {
        return null;
    }

    @Override
    @GetMapping("/contract/{contractId}")
    public HotelContractDto getContractById(String contractId) {
        return null;
    }

    @Override
    @PutMapping("/contract/{contractId}")
    public HotelContractDto updateContract(HotelContractDto contractDto, String contractId) {
        return null;
    }

    @Override
    @DeleteMapping("/contract/{contractId}")
    public String deleteContract(String contractId) {
        return "";
    }

    @Override
    @GetMapping("/contracts")
    public List<HotelContractDto> getContracts() {
        return List.of();
    }
}
