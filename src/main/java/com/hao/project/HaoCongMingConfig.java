package com.hao.project;


import com.hao.project.client.HaoCongMingClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties ("hao.client")
@Data
@ComponentScan
public class HaoCongMingConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public HaoCongMingClient haoCongMingClient() {
        return new HaoCongMingClient(accessKey, secretKey);
    }
}
