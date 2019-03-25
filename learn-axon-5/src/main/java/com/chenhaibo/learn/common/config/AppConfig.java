package com.chenhaibo.learn.common.config;

import com.chenhaibo.learn.command.config.OrderConfig;
import com.chenhaibo.learn.command.config.ProductConfig;
import com.chenhaibo.learn.query.config.QueryDbConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AxonConfiguration.class,
        QueryDbConfiguration.class,
        OrderConfig.class,
        ProductConfig.class
})
public class AppConfig {
}
