package br.com.vfs.providermock.resource;

import br.com.vfs.providermock.dto.Product;
import br.com.vfs.providermock.soa.client.ProductWSClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.wsdl.ProductWSResponse;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductWSClient productWSClient;
    

    @PostMapping
    public ResponseEntity addProducts(
            @RequestBody List<Product> products){

        ProductWSResponse response = productWSClient.createProducts(products);

        return ResponseEntity.ok().build();
    }
}
