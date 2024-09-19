package com.hcpark.tobyspring6;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    //    // LocalContainerEntityManagerFactoryBean는 당장 EntityManagerFactory는 아니지만, 빈 생성 시 EntityManagerFactory로 등록됨
    //    @Bean
    //    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    //        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    //        emf.setDataSource(dataSource());
    //        emf.setPackagesToScan("com.hcpark.tobyspring6");
    //        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
    //            // 인스턴스 초기화 기법
    //            setDatabase(Database.H2);
    //            setGenerateDdl(true);
    //            setShowSql(true);
    //        }});
    //
    //        return emf;
    //    }
    //
    //    // JPA @PersistenceContext 관련 후처리
    //    @Bean
    //    public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
    //        return new PersistenceAnnotationBeanPostProcessor();
    //    }

    // Transaction begin commit 자동으로 해주도록 셋팅
    @Bean
    //    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    public PlatformTransactionManager transactionManager() {
        //        return new JpaTransactionManager(emf);
        return new JdbcTransactionManager(dataSource());
    }
}
