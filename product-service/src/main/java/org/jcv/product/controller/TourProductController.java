package org.jcv.product.controller;

import org.jcv.common.product.dto.TourContractDto;
import org.jcv.product.service.TourContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-service/tour")
public class TourProductController implements IBaseProductController<TourContractDto> {

    @Autowired
    private TourContractService contractService;

    @Override
    @PostMapping("/contract")
    public TourContractDto createContract(@RequestBody TourContractDto contractDto) {
        return contractService.createContract(contractDto);
    }

    @Override
    @GetMapping("/contract/{contractId}")
    public TourContractDto getContractById(@PathVariable String contractId) {
        return contractService.getContractById(contractId);
    }

    @Override
    @PutMapping("/contract/{contractId}")
    public TourContractDto updateContract(@RequestBody TourContractDto contractDto, @PathVariable String contractId) {
        return contractService.updateContract(contractDto);
    }

    @Override
    @DeleteMapping("/contract/{contractId}")
    public String deleteContract(String contractId) {
        return contractService.deleteContract(contractId);
    }

    @Override
    @GetMapping("/contracts")
    public List<TourContractDto> getContracts() {
        return contractService.getAllContracts();
    }
}
