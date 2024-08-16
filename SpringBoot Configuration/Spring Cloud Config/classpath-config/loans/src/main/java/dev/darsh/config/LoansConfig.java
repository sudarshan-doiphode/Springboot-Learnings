package dev.darsh.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "user")
@Setter
@Getter
public class LoansConfig {
    private String name;
    private List<Long> mobile;
    private Map<String, String> address;
    private Double amount;
}
