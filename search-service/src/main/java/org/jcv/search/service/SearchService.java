package org.jcv.search.service;

import org.jcv.search.dto.Result;
import org.jcv.search.model.Product;
import org.jcv.search.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Result> searchProducts( List<Integer> ids, List<String> codes, String name )
    {
        Specification<Product> spec = Specification.where( null );
        if( ids != null && !ids.isEmpty() )
        {
            spec = spec.and( ( root, query, cb ) -> root.get( "productId" ).in( ids ) );
        }
        if( codes != null && !codes.isEmpty() )
        {
            spec = spec.and( ( root, query, cb ) -> root.get( "productCode" ).in( codes ) );
        }
        if( name != null && !name.isBlank() )
        {
            spec = spec.and( ( root, query, cb ) ->
                                     cb.like( cb.lower( root.get( "productName" ) ), "%" + name.toLowerCase() + "%" ) );
        }

        List<Product> dtoList = productRepository.findAll( spec );
        return modelMapper.map( dtoList, new TypeToken<List<Result>>()
        {
        }.getType() );

    }
}
