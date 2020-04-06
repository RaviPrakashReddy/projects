package com.candela.forte.integration.db.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:dbconfigprops.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "forteEntityManagerFactory",
		transactionManagerRef = "forteTransactionManager", 
		basePackages = {"com.candela.forte.integration.db.repository"})
public class FORTEDataSourceConfig {
	
	private static Logger log	=	 LoggerFactory.getLogger(FORTEDataSourceConfig.class);
	
	 @Value("${forte.hibernate.jpa.properties.hibernate.default_schema}")
	 private String forteSchemaName;
	
	  @Value("${forte.datasource.jndi.name}")
	  private String forteJndiName;
	  
	  @Value("${forte.datasource.jndi.use}")
	  private String jndiUse;
	  
	 
	  @Autowired
	  MBeanExporter mBeanExporter ;
	  
	
	  @Bean(name = "forteDataSource")
	  @Primary
	  @ConfigurationProperties(prefix = "forte.datasource") 
	  public  DataSource  forteDataSource()throws Exception {
		  log.info("---> creating Gelm forte DataSource  <---"); 
		  try {
				  if(jndiUse!=null && jndiUse.equalsIgnoreCase("Yes")) {
					  log.info("-----> Looking for forte Jndi Datasource ["+forteJndiName+"] <-------");
						JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
						  	bean.setJndiName(forteJndiName);
							bean.setProxyInterface(DataSource.class);
							bean.setLookupOnStartup(false);
							bean.afterPropertiesSet();
						log.info("-----> completed binding forte Jndi Datasource ["+forteJndiName+"] <-------");
					return (DataSource) bean.getObject();
				  }else {
					  log.info("-----> creating forte Datasource based on properties <-------");
					  mBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
					  return DataSourceBuilder.create().build();
				  }
		  }catch(Exception xe) {
			  log.error("Exception while executing forteDataSource",xe);
			  throw xe;
		  }catch(Error error) {
			  log.error("Exception while executing forteDataSource",error);
			  throw new Exception(error.fillInStackTrace());
		  }
		}
	
	@Bean(name = "forteEntityManager")
    public EntityManager entityManager(@Qualifier("forteEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
	
	@Bean(name = "forteEntityManagerFactory")
	@Primary
	public EntityManagerFactory  entityManagerFactory(@Qualifier("forteDataSource") final DataSource dataSource) {

		HibernateJpaVendorAdapter jpaTxnVendorAdapter = new HibernateJpaVendorAdapter();
		jpaTxnVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan("com.candela.forte.integration.db.entity");
		factoryBean.setJpaVendorAdapter(jpaTxnVendorAdapter);
		factoryBean.setPersistenceUnitName("fortePersistenceUnit");
		factoryBean.setJpaProperties(forteAdditionalProperties());
		factoryBean.afterPropertiesSet();
        
		return factoryBean.getObject();
	}

	@Bean(name = "forteTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("forteEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	private Properties forteAdditionalProperties() {
		  Properties properties = new Properties();
		  	properties.setProperty("hibernate.default_schema", forteSchemaName);
		  	properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
		  properties.setProperty("hibernate.show_sql", "true");
		  return properties;
		}
	
}
