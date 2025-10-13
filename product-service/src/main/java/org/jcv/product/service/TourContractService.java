package org.jcv.product.service;

import org.jcv.common.product.dto.TourContractDto;
import org.jcv.product.model.tour.TourContract;
import org.jcv.product.repository.TourContractRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourContractService implements IProductService<TourContractDto> {

    @Autowired
    private TourContractRepository contractRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TourContractDto createContract(TourContractDto dto) {
        TourContract contract = contractRepository.save( modelMapper.map( dto, TourContract.class ) );
        return modelMapper.map( contract, TourContractDto.class );
    }

    @Override
    public TourContractDto getContractById(String id) {
        TourContract contract = contractRepository.getReferenceById( id );
        return modelMapper.map( contract, TourContractDto.class );
    }

    @Override
    public List<TourContractDto> getAllContracts() {
        List<TourContract> contractList = contractRepository.findAll();
        return modelMapper.map( contractList, new TypeToken<List<TourContractDto>>()
        {
        }.getType() );
    }

    @Override
    public TourContractDto updateContract(TourContractDto dto) {
        TourContract contract = contractRepository.save( modelMapper.map( dto, TourContract.class ) );
        return modelMapper.map( contract, TourContractDto.class );
    }

    @Override
    public String deleteContract(String id) {
        contractRepository.deleteById( id );
        return "Product deleted";
    }
}
