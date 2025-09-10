package org.jcv.product.service;


import org.jcv.product.dto.ProductDto;
import org.jcv.product.dto.ProductEventDto;
import org.jcv.product.model.Product;
import org.jcv.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventPublisher publisher;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDto> getAllProducts()
    {
        List<Product> productList = productRepository.findAll();
        return modelMapper.map( productList, new TypeToken<List<ProductDto>>()
        {
        }.getType() );
    }

    public ProductDto saveProduct( ProductDto productDTO )
    {
        productRepository.save( modelMapper.map( productDTO, Product.class ) );

        publisher.publish( new ProductEventDto( ProductEventDto.Type.CREATED, productDTO.getId(), Instant.now(), productDTO ) );

        return productDTO;
    }

    public ProductDto getProductById( Integer productId )
    {
        Product product = productRepository.getProductById( productId );
        return modelMapper.map( product, ProductDto.class );
    }

    public ProductDto updateProduct( ProductDto productDTO )
    {
        productRepository.save( modelMapper.map( productDTO, Product.class ) );
        return productDTO;
    }

    public String deleteProduct( Integer productId )
    {
        productRepository.deleteById( productId );
        return "Product deleted";
    }
}
