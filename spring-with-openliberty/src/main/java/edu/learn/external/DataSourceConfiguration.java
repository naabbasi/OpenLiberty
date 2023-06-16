package edu.learn.external;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import edu.learn.configuration.ApplicationProperties;
import edu.learn.utils.DatabaseConnectionInfo;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@Profile(value = { "production", "integration" })
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager")
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final ApplicationProperties applicationProperties;

    @Bean(name = "postgresDataSource")
    public DataSource postgresDataSource() {
        DatabaseConnectionInfo postgresDbProperties = this.applicationProperties.getExternal().getPostgresDatabase();
        try {
            DataSource jndiDataSource = (DataSource) new JndiTemplate().lookup(postgresDbProperties.getJndiName());
            System.out.println("#######################################");
            System.out.println("Production Database");
            System.out.println("#######################################");
            System.out.println(jndiDataSource);
            return jndiDataSource;
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return null;
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
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(postgresEntityManagerFactory));
    }
}
