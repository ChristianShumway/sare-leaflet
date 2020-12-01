/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_db.config;

import java.util.Arrays;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Configuration
public class DBConfig {
    @Autowired
    public Environment env;
    
    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource dataSourceDev() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.test.driver"));
        dS.setUrl(env.getProperty("db.test.url"));
        dS.setUsername(env.getProperty("db.test.user"));
        dS.setPassword(env.getProperty("db.test.password"));
        dS.setMaxActive(50);
        dS.setMaxIdle(8);
        dS.setMinIdle(3);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("select version()");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(10000L);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
        
    }
    @Bean(name = "jdbcTemplate")
    @Profile("dev")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSourceDev());
    }
    
    @Bean(name = "dataSource")
    @Profile("prod")
    public DataSource dataSourceProd() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver"));
        dS.setUrl(env.getProperty("db.prod.url"));
        dS.setUsername(env.getProperty("db.prod.user"));
        dS.setPassword(env.getProperty("db.prod.password"));
        dS.setMaxActive(50);
        dS.setMaxIdle(15);
        dS.setMinIdle(8);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("select version()");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(30000);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplate")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProd() {
        return new JdbcTemplate(dataSourceProd());
    }
    
    /*DataSource Oracle*/
    @Bean(name = "dataSourceOcl")
    @Profile("dev")
    public DataSource dataSourceDevOcl() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.test.driver.ocl"));
        dS.setUrl(env.getProperty("db.test.url.ocl"));
        dS.setUsername(env.getProperty("db.test.user.ocl"));
        dS.setPassword(env.getProperty("db.test.password.ocl"));
        dS.setMaxActive(50);
        dS.setMaxIdle(8);
        dS.setMinIdle(3);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(10000L);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplateOcl")
    @Profile("dev")
    public JdbcTemplate jdbcTemplateOcl() {
        return new JdbcTemplate(dataSourceDevOcl());
    }
    
    /*DataSource Oracle*/
    @Bean(name = "dataSourceOclEge")
    @Profile("dev")
    public DataSource dataSourceDevOclEge() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.test.driver.ocl.ege"));
        dS.setUrl(env.getProperty("db.test.url.ocl.ege"));
        dS.setUsername(env.getProperty("db.test.user.ocl.ege"));
        dS.setPassword(env.getProperty("db.test.password.ocl.ege"));
        dS.setMaxActive(50);
        dS.setMaxIdle(8);
        dS.setMinIdle(3);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(10000L);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplateOclEge")
    @Profile("dev")
    public JdbcTemplate jdbcTemplateOclEge() {
        return new JdbcTemplate(dataSourceDevOclEge());
    }
    
    @Bean(name = "dataSourceOcl")
    @Profile("prod")
    public DataSource dataSourceProdOcl() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver.ocl"));
        dS.setUrl(env.getProperty("db.prod.url.ocl"));
        dS.setUsername(env.getProperty("db.prod.user.ocl"));
        dS.setPassword(env.getProperty("db.prod.password.ocl"));
        dS.setMaxActive(50);
        dS.setMaxIdle(15);
        dS.setMinIdle(8);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(30000);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "dataSourceOclUEEPA")
    @Profile("prod")
    public DataSource dataSourceProdOclUEEPA() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver.ocl.ueepa"));
        dS.setUrl(env.getProperty("db.prod.url.ocl.ueepa"));
        dS.setUsername(env.getProperty("db.prod.user.ocl.ueepa"));
        dS.setPassword(env.getProperty("db.prod.password.ocl.ueepa"));
        dS.setMaxActive(50);
        dS.setMaxIdle(15);
        dS.setMinIdle(8);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(30000);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "dataSourceOclUEEPA")
    @Profile("dev")
    public DataSource dataSourceDevOclUEEPA() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver.ocl.ueepa"));
        dS.setUrl(env.getProperty("db.prod.url.ocl.ueepa"));
        dS.setUsername(env.getProperty("db.prod.user.ocl.ueepa"));
        dS.setPassword(env.getProperty("db.prod.password.ocl.ueepa"));
        dS.setMaxActive(50);
        dS.setMaxIdle(15);
        dS.setMinIdle(8);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(30000);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "dataSourceOclUEEPA")
    @Profile("dev")
    public DataSource dataSourceProdOclEge() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver.ocl.ueepa"));
        dS.setUrl(env.getProperty("db.prod.url.ocl.ueepa"));
        dS.setUsername(env.getProperty("db.prod.user.ocl.ueepa"));
        dS.setPassword(env.getProperty("db.prod.password.ocl.ueepa"));
        dS.setMaxActive(50);
        dS.setMaxIdle(15);
        dS.setMinIdle(8);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("SELECT * FROM V$VERSION ");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(30000);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplateOclUEEPA")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProdOclUEEPA() {
        return new JdbcTemplate(dataSourceProdOclUEEPA());
    }
    @Bean(name = "jdbcTemplateOclUEEPA")
    @Profile("dev")
    public JdbcTemplate jdbcTemplateDevOclUEEPA() {
        return new JdbcTemplate(dataSourceDevOclUEEPA());
    }
    @Bean(name = "jdbcTemplateOclEge")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProdOclEGe() {
        return new JdbcTemplate(dataSourceProdOclEge());
    }
    @Bean(name = "jdbcTemplateOcl")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProdOcl() {
        return new JdbcTemplate(dataSourceProdOcl());
    }
    @Bean(name = "jdbcTemplateOcl")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProdOclEge() {
        return new JdbcTemplate(dataSourceProdOcl());
    }
    
    /*DataSource MDM*/
    @Bean(name = "dataSourcemdm")
    @Profile("dev")
    public DataSource dataSourceDevMdm() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.test.driver.mdm"));
        dS.setUrl(env.getProperty("db.test.url.mdm"));
        dS.setUsername(env.getProperty("db.test.user.mdm"));
        dS.setPassword(env.getProperty("db.test.password.mdm"));
        dS.setMaxActive(50);
        dS.setMaxIdle(8);
        dS.setMinIdle(3);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("select version()");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(10000L);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplatemdm")
    @Profile("dev")
    public JdbcTemplate jdbcTemplateMdm() {
        return new JdbcTemplate(dataSourceDevMdm());
    }
    
    @Bean(name = "dataSourcemdmprod")
    @Profile("prod")
    public DataSource dataSourceProdMdm() {
        BasicDataSource dS = new BasicDataSource();
        dS.setDriverClassName(env.getProperty("db.prod.driver.mdm"));
        dS.setUrl(env.getProperty("db.prod.url.mdm"));
        dS.setUsername(env.getProperty("db.prod.user.mdm"));
        dS.setPassword(env.getProperty("db.prod.password.mdm"));
        dS.setMaxActive(50);
        dS.setMaxIdle(8);
        dS.setMinIdle(3);
        dS.setTestWhileIdle(true);
        dS.setValidationQuery("select version()");
        dS.setRemoveAbandonedTimeout(15);
        dS.setRemoveAbandoned(true);
        dS.setTimeBetweenEvictionRunsMillis(10000L);
        dS.setLogAbandoned(false);
        dS.setDefaultReadOnly(false); 
        return dS;
    }
    @Bean(name = "jdbcTemplatemdm")
    @Profile("prod")
    public JdbcTemplate jdbcTemplateProdMdm() {
        return new JdbcTemplate(dataSourceProdMdm());
    }
    
    
    /* Esquemas en pruebas */
    @Bean(name = "schemaSarePG")
    @Profile({"dev", "sarePG"})
    public String getSchemasarePG() {
        return env.getProperty("db.test.schema");
    }
     /* Esquemas en pruebas Oracle */
    @Bean(name = "schemaSareOcl")
    @Profile({"dev", "sareOcl"})
    public String getSchemasareOcl() {
        return env.getProperty("db.test.schema.ocl");
    }
    
     /* Esquemas en pruebas Mdm */
    @Bean(name = "schemaSaremdm")
    @Profile({"dev", "saremdm"})
    public String getSchemasareMdm() {
        return env.getProperty("db.test.schema.mdm");
    }
    
    /* Esquemas en produccion */
    
    @Bean(name = "schemaSarePG")
    @Profile({"prod", "sarePGProd"})
    public String getSchemasarePGProd() {
        return env.getProperty("db.prod.schema");
    }
    /* Esquemas en produccion  Oracle*/
    @Bean(name = "schemaSareOcl")
    @Profile({"prod", "sareOclProd"})
    public String getSchemasareOclProd() {
        return env.getProperty("db.prod.schema.ocl");
    }
    /* Esquemas en produccion  Mdm*/
    @Bean(name = "schemaSaremdm")
    @Profile({"prod", "sareMdmProd"})
    public String getSchemasareMdmProd() {
        return env.getProperty("db.prod.schema.mdm");
    }
    
}
