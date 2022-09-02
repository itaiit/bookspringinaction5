package io.itaiit.web.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author itaiit
 * @date 2022/9/2 23:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "taco")
public class TacoPageProp {
    private int page;
    private int size;
}
