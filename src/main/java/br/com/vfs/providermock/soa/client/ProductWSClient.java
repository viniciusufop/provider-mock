package br.com.vfs.providermock.soa.client;

import br.com.vfs.providermock.dto.Image;
import br.com.vfs.providermock.dto.Product;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import products.wsdl.ImageWSRequest;
import products.wsdl.ImageWSResponse;
import products.wsdl.ProductWS;
import products.wsdl.ProductWSRequest;
import products.wsdl.ProductWSResponse;

public class ProductWSClient extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductWSClient.class);

    @Value("${marketplace.host}")
    private String host;

    @Value("${marketplace.port}")
    private Integer port;

    public ProductWSResponse createProducts(final List<Product> products) {
        ProductWSRequest request = new ProductWSRequest();

        products.forEach( product -> {
            ProductWS productWS = new ProductWS();
            productWS.setId(product.getId());
            productWS.setName(product.getName());
            productWS.setProvider(product.getProvider().name());
            productWS.setDescription(product.getDescription());
            productWS.setValue(product.getValue());
            productWS.setType(product.getType());
            request.getProductsWS().add(productWS);
        });

        LOGGER.info("Executando requisicao para incluir novos produtos. ");

        final ProductWSResponse response = (ProductWSResponse) getWebServiceTemplate()
                .marshalSendAndReceive(String.format("http://%s:%d/ws/products",host,port), request,
                        new SoapActionCallback(
                                "dto.soap.marketplacebackend.vfs.com.br.ProductWSRequest"));
        LOGGER.info("Fim da requisicao par incluir novos produtos. ");
        return response;
    }

    public ImageWSResponse uploadImage(final Image image, final MultipartFile file) throws IOException {
        ImageWSRequest request = new ImageWSRequest();
        request.setId(image.getId());
        request.setProvider(image.getProvider().name());
        request.setPrimary(image.isPrimary());
        request.setType(FilenameUtils.getExtension(file.getOriginalFilename()));
        request.setImage(file.getBytes());
        LOGGER.info("Executando requisicao par incluir nova image. ");

        final ImageWSResponse response = (ImageWSResponse) getWebServiceTemplate()
                .marshalSendAndReceive(String.format("http://%s:%d/ws/products",host,port), request,
                        new SoapActionCallback(
                                "dto.soap.marketplacebackend.vfs.com.br.ImageWSRequest"));
        LOGGER.info("Fim da requisicao para incluir nova imagem. ");
        return response;
    }
}
