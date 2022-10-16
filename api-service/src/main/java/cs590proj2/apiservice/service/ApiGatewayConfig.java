package cs590proj2.apiservice.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Value("${rest.auth-service-url}")
    private String authServiceUrl;
    @Value("${rest.product-service-url}")
    private String productServiceUrl;
    @Value("${rest.order-service-url}")
    private String orderServiceUrl;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**").uri(authServiceUrl))
                .route("product-service", r -> r.path("/products/**").uri(productServiceUrl))
                .route("order-service", r -> r.path("/orders/**").uri(orderServiceUrl))
                .build();
    }
}
