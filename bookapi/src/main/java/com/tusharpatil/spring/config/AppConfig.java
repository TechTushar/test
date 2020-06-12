package com.tusharpatil.spring.config;

import java.sql.Driver;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
		@ComponentScan("com.tusharpatil.spring.dao"),
		@ComponentScan("com.tusharpatil.spring.service")
})
public class AppConfig {
	
	@Autowired
	private org.springframework.core.env.Environment env;
	
	@Bean	
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		//setting jdbc properties
		props.put(DRIVER, env.getProperty("oracle.driver"));
		props.put(URL, env.getProperty("oracle.url"));
		props.put(USER, env.getProperty("oracle.user"));
		props.put(PASS, env.getProperty("oracle.password"));
		
		//setting hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		//setting c3p0 property
		props.put(C3P0_MIN_SIZE,env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE,env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT,env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT,env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS,env.getProperty("hibernate.c3p0.max_statements"));
		
		//put this factory bean
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.tusharpatil.spring.model");
		
		return factoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
