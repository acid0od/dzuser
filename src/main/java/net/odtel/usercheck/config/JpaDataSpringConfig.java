package net.odtel.usercheck.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
@Configuration
@EnableJpaRepositories(basePackages = {"net.odtel.usercheck.repository"})
@EnableTransactionManagement
*/
public class JpaDataSpringConfig {

    @Value("${dataSource.driverClassName}")
    private String driver;

    @Value("${dataSource.url}")
    private String databaseURL;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public FactoryBean<SessionFactory> sessionFactory() {
        HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
        factory.setEntityManagerFactory(entityManagerFactory());
        factory.setPersistenceUnitName("punit");
        return factory;
    }


    @Bean
    public EntityManager entityManger() {
        EntityManager manager = entityManagerFactory().createEntityManager();
        manager.unwrap(Session.class);
        return manager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        transactionManager.setDataSource(dataSource());
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        if (driver != null) System.out.println(">>>>>>>>>>>>>>>>>Driver:" + driver);
        if (databaseURL != null) System.out.println(">>>>>>>>>>>>>>>>>DatabaseURL:" + databaseURL);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(databaseURL);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName("punit");

        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        factory.setPackagesToScan("net.odtel.knowledgetests");

        Map<String, String> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaPropertyMap.put("hibernate.hbm2ddl.auto", "update");
        jpaPropertyMap.put("hibernate.format_sql", "true");
        jpaPropertyMap.put("hibernate.enable_lazy_load_no_trans", "true");

        factory.setJpaPropertyMap(jpaPropertyMap);
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

}
