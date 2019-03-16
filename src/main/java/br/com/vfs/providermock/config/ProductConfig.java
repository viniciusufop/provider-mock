package br.com.vfs.providermock.config;

import br.com.vfs.providermock.soa.client.ProductWSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ProductConfig {

    @Value("${marketplace.host}")
    private String host;

    @Value("${marketplace.port}")
    private Integer port;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("products.wsdl");
        return marshaller;
    }

    @Bean
    public ProductWSClient productWSClient(Jaxb2Marshaller marshaller) {
        ProductWSClient client = new ProductWSClient();
        client.setDefaultUri(String.format("http://%s:%d/ws",host,port));
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
