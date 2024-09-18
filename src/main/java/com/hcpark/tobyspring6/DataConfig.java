package com.hcpark.tobyspring6;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.hcpark.tobyspring6.order.OrderRepository;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    // LocalContainerEntityManagerFactoryBean는 당장 EntityManagerFactory는 아니지만, 빈 생성 시 EntityManagerFactory로 등록됨
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.hcpark.tobyspring6");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
            // 인스턴스 초기화 기법
            setDatabase(Database.H2);
            setGenerateDdl(true);
            setShowSql(true);
        }});

        return emf;
    }

    // EntityManagerFactory 빈을 인자로 주입받음
    @Bean
    public OrderRepository orderRepository(EntityManagerFactory emf) {
        return new OrderRepository(emf);
    }
}
