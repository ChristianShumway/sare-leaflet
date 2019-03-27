/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.sare.sare_services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/**
 *
 * @author LIDIA.VAZQUEZ
 */
@Configuration
public class ServiceConfig {
    @Autowired
    public Environment env;
    
    
    
    @Bean(name = "projectName")
    @Profile("sare")
    public String getProjectNameCA(){
        return env.getProperty("services.name.sare");
    }
}
