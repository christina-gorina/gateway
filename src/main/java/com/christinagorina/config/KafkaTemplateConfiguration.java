package com.christinagorina.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaTemplateConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    public RequestReplyingKafkaTemplate requestReplyingKafkaTemplate(
            @Qualifier("gatewayProducerFactory") ProducerFactory<String, Object> producerFactory,
            @Qualifier("gatewayReplyContainer") KafkaMessageListenerContainer<String, String> replyContainer
            ){
        return new RequestReplyingKafkaTemplate(producerFactory, replyContainer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> gatewayReplyContainer(
            @Qualifier("gatewayConsumerFactory") ConsumerFactory<String, String> consumerFactory){
        ContainerProperties containerProperties = new ContainerProperties(
                kafkaProperties.getGatewayChannelProperties().getConsumerTopicName()
        );
        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    @Bean
    public ConsumerFactory<String, String> gatewayConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(gatewayConsumerConfigs());
    }

    public Map<String, Object> gatewayConsumerConfigs(){

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", kafkaProperties.getBootstrapServers()));
        GatewayChannelProperties gatewayChannelProperties = kafkaProperties.getGatewayChannelProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, gatewayChannelProperties.getConsumerTopicName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, gatewayChannelProperties.getDeserializerClassConfig());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, gatewayChannelProperties.getDeserializerClassConfig());
        return props;

    }

    @Bean
    public ProducerFactory<String, Object> gatewayProducerFactory(){
        return new DefaultKafkaProducerFactory<>(gatewayProducerConfigs());
    }

    public Map<String, Object> gatewayProducerConfigs(){

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", kafkaProperties.getBootstrapServers()));
        GatewayChannelProperties gatewayChannelProperties = kafkaProperties.getGatewayChannelProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, gatewayChannelProperties.getSerializerClassConfig());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, gatewayChannelProperties.getSerializerClassConfig());
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, kafkaProperties.getMaxRequestSize());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, kafkaProperties.getCompressionType());
        return props;

    }


}
