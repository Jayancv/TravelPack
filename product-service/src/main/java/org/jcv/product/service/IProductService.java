package org.jcv.product.service;

import org.jcv.common.product.dto.ContractDto;

import java.util.List;

public interface IProductService<T extends ContractDto> {

    T createContract(T dto);

    T getContractById(String id);

    List<T> getAllContracts();

    T updateContract(T dto);

    String deleteContract(String id);

}
