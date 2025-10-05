package org.jcv.product.controller;

import org.jcv.common.product.dto.ContractDto;

import java.util.List;

public interface IBaseProductController<T extends ContractDto> {

    T createContract(T contractDto);

    T getContractById(String contractId);

    T updateContract(T contractDto, String contractId);

    String deleteContract(String contractId);

    List<T> getContracts();
}
