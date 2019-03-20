package br.com.vfs.providermock.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import products.wsdl.TypeWS;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    private String id;
    private Provider provider;
    private String name;
    private String description;
    private BigDecimal value;
    private TypeWS type;


    public enum Provider {
        SAMSUNG,
        DELL,
        LG,
        ACER
    }
}
