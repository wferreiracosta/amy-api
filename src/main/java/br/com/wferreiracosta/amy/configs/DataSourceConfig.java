package br.com.wferreiracosta.amy.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Configuration
public class DataSourceConfig {

    @Bean
    public GeneratedKeyHolder generatedKeyHolder() {
        return new GeneratedKeyHolder();
    }

}
