package br.com.vfs.providermock.dto;

import br.com.vfs.providermock.dto.Product.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Image {
    private String id;
    private Provider provider;
    private boolean primary;
}
