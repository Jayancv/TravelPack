package org.jcv.cart.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart implements Serializable {
    private long cartId;
    List<CartItem> cartItems = new ArrayList<>();
    private Double totalPrice;

    public Cart() {

    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public void removeItem(String itemKey ) {
        Iterator<CartItem> it = cartItems.iterator();
        while (it.hasNext()) {
            if ( it.next().getItemKey().equals(itemKey)   ) {
                it.remove();
                return;
            }
        }
    }

    public void clear() {
        cartItems.clear();
    }

    public Double getTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem::getPrice).sum();
    }
}
