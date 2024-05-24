package org.example.store.config;

import org.example.store.processor.IOProcessor;
import org.example.store.processor.StoreIOProcessor;
import org.example.store.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Main configuration bean of the application
 * Configures data source connection
 * @author Ilya Kuzmichev aka wilmerno
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("org.example.store")
public class ApplicationConfig {
    private final Environment env;

    /**
     * Constructor
     * @param env Application environment variable
     */
    public ApplicationConfig(Environment env) {
        this.env = env;
    }

    /**
     * Datasource bean creation
     * @return Datasource bean
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    /**
     * Jdbc bean creation
     * @param dataSource Dependent datasource
     * @return JdbcTemplate bean
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * IOProcessor bean creation
     * @param productService Service implementation
     * @return IOProcessor bean
     */
    @Bean
    public IOProcessor ioProcessor(ProductService productService) {
        return new StoreIOProcessor(productService, System.in);
    }
}
