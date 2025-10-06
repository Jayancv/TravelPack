package org.jcv.search.model.data;

import jakarta.persistence.*;
import lombok.Data;
import org.jcv.common.ProductType;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    private String contractName;
    private String contractCode;

    private Date validFrom;
    private Date validTo;
    private boolean salesEnabled;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private String cityCode;
    private String countryCode;

    public abstract ProductType getProductType();
}
