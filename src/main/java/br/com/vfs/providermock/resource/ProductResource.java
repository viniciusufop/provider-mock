package br.com.vfs.providermock.resource;

import br.com.vfs.providermock.dto.Image;
import br.com.vfs.providermock.dto.Product;
import br.com.vfs.providermock.soa.client.ProductWSClient;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import products.wsdl.ImageWSResponse;
import products.wsdl.ProductWSResponse;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductWSClient productWSClient;

    @PostMapping
    public ResponseEntity addProducts(
            @RequestBody List<Product> products) {
        ProductWSResponse response = productWSClient.createProducts(products);
        return ResponseEntity.ok(response.getStatus());
    }

    @PostMapping(value = "/images")
    public ResponseEntity addProducts(
            @RequestHeader(name = "idproduct") final String id,
            @RequestHeader(name = "provider") final Product.Provider provider,
            @RequestHeader(name = "primary") final boolean primary,
            @RequestParam(name = "file") final MultipartFile file) {
        try {

            ImageWSResponse response = productWSClient
                    .uploadImage(Image.builder().id(id).provider(provider).primary(primary).build(), file);
            return ResponseEntity.ok(response.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
