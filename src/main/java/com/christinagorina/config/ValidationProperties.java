package com.christinagorina.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("validation")
@Data
@ConstructorBinding
//@Refreshscope
public class ValidationProperties {

    private boolean enabled = true;
}
