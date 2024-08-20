package com.sohaib.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.opentelemetry.OpenTelemetryAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@EntityScan("com.sohaib.app.entity")
@EnableJpaRepositories("com.sohaib.app.repository")
@EnableFeignClients("com.sohaib.app.feignclient")
@EnableDiscoveryClient
@SpringBootApplication
public class StudentServiceApplication {

    @Value("${address.Service.url}")
    public String addressServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(addressServiceUrl).build();
    }
}
