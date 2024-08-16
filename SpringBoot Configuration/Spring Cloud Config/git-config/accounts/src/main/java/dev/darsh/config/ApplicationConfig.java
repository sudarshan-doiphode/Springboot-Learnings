package dev.darsh.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "user")
public class ApplicationConfig {
    private String email;
    private Map<String, String> address;
    private List<Long> mobile;
}
