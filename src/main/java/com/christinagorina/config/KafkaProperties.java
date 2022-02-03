package com.christinagorina.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConfigurationProperties("kafka")
@ConstructorBinding
@Data
public class KafkaProperties {

    private final List<String> bootstrapServers;

    private final GatewayChannelProperties gatewayChannelProperties;

    private final Integer maxRequestSize = 52428800;

    private final String compressionType = "gzip";

}
