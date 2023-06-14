package edu.learn.external;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import edu.learn.configuration.ApplicationProperties;
import edu.learn.utils.DatabaseConnectionInfo;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@Profile(value = { "development" })
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager")
@RequiredArgsConstructor
public class DataSourceConfiguration_Development {
    private final ApplicationProperties applicationProperties;

    @Bean(name = "postgresDataSource")
    public DataSource postgresDataSource() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        DatabaseConnectionInfo postgresDbProperties = this.applicationProperties.getExternal().getPostgresDatabase();

        dataSourceProperties.setDriverClassName(postgresDbProperties.getDriverClassName());
        dataSourceProperties.setUrl(postgresDbProperties.getUrl());
        dataSourceProperties.setUsername(postgresDbProperties.getUsername());
        dataSourceProperties.setPassword(postgresDbProperties.getPassword());
        //dataSourceProperties.getXa().setDataSourceClassName(postgresDbProperties.getDriverClassName());

        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(@Qualifier("postgresDataSource") DataSource dataSource, EntityManagerFactoryBuilder postgresEntityManagerFactoryBuilder) {
        DatabaseConnectionInfo postgresDbProperties = this.applicationProperties.getExternal().getPostgresDatabase();

        Map<String, String> postgresJpaProperties = new HashMap<>();

        postgresJpaProperties.put("hibernate.connection.driver_class", postgresDbProperties.getDriverClassName());
        postgresJpaProperties.put("hibernate.dialect", postgresDbProperties.getDialect());

        EntityManagerFactoryBuilder entityManagerFactoryBuilder = postgresEntityManagerFactoryBuilder;
        EntityManagerFactoryBuilder.Builder builder = entityManagerFactoryBuilder.dataSource(postgresDataSource());
        return builder
                .properties(postgresJpaProperties)
                .persistenceUnit("postgreDatabase")
                .packages("edu.learn.entities")
                .build();
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager todosTransactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresEntityManagerFactory));
    }
}
