/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services.config;

import mx.org.inegi.sare.sare_db.config.DBRootConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Configuration
@ComponentScan({"mx.org.inegi.sare"})
@Import({DBRootConfig.class, ServiceConfig.class})
@PropertySource(value = "classpath:/services.properties")
public class RootServiceConfig {

}
