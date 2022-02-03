package com.christinagorina.config;

import lombok.Data;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

@Data
public class GatewayChannelProperties {

    private final Class<Serializer<?>> serializerClassConfig;

    private final Class<Deserializer<?>> deserializerClassConfig;

    private final String consumerTopicName;

}
