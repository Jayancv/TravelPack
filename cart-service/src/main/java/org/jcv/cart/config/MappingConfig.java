package org.jcv.cart.config;

import org.jcv.cart.dto.CartItemDto;
import org.jcv.cart.model.CartItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(CartItem.class, CartItemDto.class).addMappings(m -> {
            m.map(CartItem::getItemKey, CartItemDto::setItemKey);
        });

        return mapper;
    }
}
