package br.com.vfs.providermock.soa.client;

import br.com.vfs.providermock.dto.Product;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import products.wsdl.ProductWS;
import products.wsdl.ProductWSRequest;
import products.wsdl.ProductWSResponse;

public class ProductWSClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductWSClient.class);

    public ProductWSResponse createProducts(final List<Product> products) {
        ProductWSRequest request = new ProductWSRequest();

        products.forEach( product -> {
            ProductWS productWS = new ProductWS();
            productWS.setId(product.getId());
            productWS.setName(product.getName());
            productWS.setAuthProvider(product.getProvider().name());
            productWS.setDescription(product.getDescription());
            productWS.setValue(product.getValue());
            productWS.setType(product.getType());
            request.getProductsWS().add(productWS);
        });

        LOGGER.info("Executando requisicao par incluir novos produtos. ");

        final ProductWSResponse response = (ProductWSResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:9000/ws/products", request,
                        new SoapActionCallback(
                                "dto.soap.marketplacebackend.vfs.com.br.ProductWSRequest"));
        LOGGER.info("Fim da requisicao par incluir novos produtos. ");
        return response;
    }
}
