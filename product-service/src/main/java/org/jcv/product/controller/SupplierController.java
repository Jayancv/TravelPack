package org.jcv.product.controller;

import org.jcv.common.supplier.dto.SupplierDto;
import org.jcv.product.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-service")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/suppliers")
    public List<SupplierDto> getSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/supplier/{supplierId}")
    public SupplierDto getSupplierById(@PathVariable Long supplierId) {
        return supplierService.getSupplierById(supplierId);
    }

    @PostMapping("/supplier")
    public SupplierDto createSupplier(@RequestBody SupplierDto supplierDto) {
        return supplierService.createSupplier(supplierDto);
    }

    @PutMapping("/supplier/{supplierId}")
    public SupplierDto updateSupplier(@RequestBody SupplierDto supplierDto, @PathVariable Long supplierId) {
        // TODO need to use supplierId
        return supplierService.updateSupplier(supplierDto);
    }

    @DeleteMapping("/supplier/{supplierId}")
    public String deleteSupplier(@PathVariable Long supplierId) {
        return supplierService.deleteSupplier(supplierId);
    }

}
