package org.jcv.reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemId implements Serializable {

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "item_no")
    private Integer itemNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingItemId)) return false;
        BookingItemId that = (BookingItemId) o;
        return Objects.equals(bookingId, that.bookingId)
                && Objects.equals(productType, that.productType)
                && Objects.equals(itemNo, that.itemNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, productType, itemNo);
    }
}
