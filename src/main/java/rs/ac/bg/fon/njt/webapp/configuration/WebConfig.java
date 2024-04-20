/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.configuration;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rs.ac.bg.fon.njt.webapp.controller.customRequestBodyAnotations.JsonArgEmployeeSubjectListResolver;
import rs.ac.bg.fon.njt.webapp.controller.customRequestBodyAnotations.JsonArgHistoryItemsListResolver;

/**
 *
 * @author aleks
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        JsonArgHistoryItemsListResolver argHistoryItemsListResolver = new JsonArgHistoryItemsListResolver();
        argumentResolvers.add(argHistoryItemsListResolver);
        JsonArgEmployeeSubjectListResolver argEmployeeSubjectListResolver = new JsonArgEmployeeSubjectListResolver();
        argumentResolvers.add(argEmployeeSubjectListResolver);
    }
}
