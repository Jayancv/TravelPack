package org.jcv.common.event.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.cart.CartDto;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartCheckOutEvent {
    private Long cartId;
    private Long userId;
    private CartDto cart;
    private LocalDateTime checkedOutAt;
}