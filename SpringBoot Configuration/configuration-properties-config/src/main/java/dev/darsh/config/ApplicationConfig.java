package dev.darsh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class ApplicationConfig {
    private String email;
    private Map<String, String> address;
    private List<Long> mobile;
}
