package org.jcv.product.service;

import org.jcv.common.supplier.dto.SupplierDto;
import org.jcv.product.model.supplier.Supplier;
import org.jcv.product.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return modelMapper.map(supplierList, new TypeToken<List<SupplierDto>>() {
        }.getType());
    }

    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierRepository.save(modelMapper.map(supplierDto, Supplier.class));
        return modelMapper.map(supplier, SupplierDto.class);
    }

    public SupplierDto getSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.getReferenceById(supplierId);
        return modelMapper.map(supplier, SupplierDto.class);
    }

    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierRepository.save(modelMapper.map(supplierDto, Supplier.class));
        return modelMapper.map(supplier, SupplierDto.class);
    }

    public String deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
        return "Product deleted";
    }
}
