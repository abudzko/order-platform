package com.budzko.orderplatform.config;

import com.budzko.orderplatform.controller.OrderController;
import com.budzko.orderplatform.controller.ProductController;
import com.budzko.orderplatform.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Documentation: http://localhost:9999/swagger-ui/index.html
 */
@EnableWebMvc
@Configuration
public class SwaggerConfig {
    public static final String USER_TAG = "Users";
    public static final String PRODUCT_TAG = "Products";
    public static final String ORDER_TAG = "Order";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(
                        new Tag(USER_TAG, "API to manage users"),
                        new Tag(PRODUCT_TAG, "API to manage products"),
                        new Tag(ORDER_TAG, "API to manage orders")
                )
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage(UserController.class.getPackageName())
                                .or(RequestHandlerSelectors.basePackage(ProductController.class.getPackageName()))
                                .or(RequestHandlerSelectors.basePackage(OrderController.class.getPackageName())))
                .paths(PathSelectors.any())
                .build();
    }
}
