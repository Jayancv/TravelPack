package org.jcv.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jcv.product.dto.ProductDto;
import org.jcv.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
class ProductControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetProducts() throws Exception
    {
        ProductDto p1 = new ProductDto(1, 101, "TOU", "Tour1", 10);
        ProductDto p2 = new ProductDto(2, 102, "TOU", "Tour2", 15);

        List<ProductDto> productDtos = Arrays.asList( p1, p2 );

        Mockito.when( productService.getAllProducts() ).thenReturn( productDtos );

        mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/product-service/products" ) )
               .andExpect( MockMvcResultMatchers.status().isOk() )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.size()" ).value( productDtos.size() ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$[0].productName" ).value( "Tour1" ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$[1].productName" ).value( "Tour2" ) );
    }

    @Test
    void testGetProductById() throws Exception
    {
        ProductDto product = new ProductDto( 1, 2, "TOU", "Tour2" , 20 );

        Mockito.when( productService.getProductById( 1 ) ).thenReturn( product );

        mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/product-service/product/1" ) )
               .andExpect( MockMvcResultMatchers.status().isOk() )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.id" ).value( 1 ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.productName" ).value( "Tour2" ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.productCode" ).value( "TOU" ) );
    }

    @Test
    void testSaveProduct() throws Exception
    {
        ProductDto request = new ProductDto( 2, 102, "TOU", "Tour2", 10 );
        ProductDto savedObject = new ProductDto( 2, 102, "TOU", "Tour2", 15 );

        Mockito.when( productService.saveProduct( Mockito.any( ProductDto.class ) ) ).thenReturn( savedObject );

        mockMvc.perform( MockMvcRequestBuilders.post( "/api/v1/product-service/product" )
                                               .contentType( MediaType.APPLICATION_JSON )
                                               .content( objectMapper.writeValueAsString( request ) ) )
               .andExpect( MockMvcResultMatchers.status().isOk() )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.productName" ).value( "Tour2" ) );


    }

    @Test
    void testUpdateProduct() throws Exception
    {
        ProductDto updatedProduct = new ProductDto( 1, 2, "TOU", "Berlin tour", 10 );

        Mockito.when( productService.updateProduct( Mockito.any( ProductDto.class ) ) ).thenReturn( updatedProduct );

        mockMvc.perform( MockMvcRequestBuilders.put( "/api/v1/product-service/product" )
                                               .contentType( MediaType.APPLICATION_JSON )
                                               .content( objectMapper.writeValueAsString( updatedProduct ) ) )
               .andExpect( MockMvcResultMatchers.status().isOk() )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.id" ).value( 1 ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.productName" ).value( "Berlin tour" ) )
               .andExpect( MockMvcResultMatchers.jsonPath( "$.productCode" ).value( "TOU" ) );
    }

    @Test
    void testDeleteProduct() throws Exception
    {
        Mockito.when( productService.deleteProduct( 1 ) ).thenReturn( "Product deleted successfully" );

        mockMvc.perform( MockMvcRequestBuilders.delete( "/api/v1/product-service/product/1" ) )
               .andExpect( MockMvcResultMatchers.status().isOk() )
               .andExpect( MockMvcResultMatchers.content().string( "Product deleted successfully" ) );
    }
}