package com.productmanagement.productmanagementapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Documentation - Product Management API",
                description = "API documentation for Management Product",
                version = "1.0.0",
                contact = @Contact(
                        name = "plongRoth",
                        email = "mrr.rothabetta31@gmail.com",
                        url = "https://rothaporfolio.vercel.app/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class OpenAPIConfig {

}
