package io.itaiit.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "taco")
public class TacoPageProp {
    private int page;
    private int size;
}
